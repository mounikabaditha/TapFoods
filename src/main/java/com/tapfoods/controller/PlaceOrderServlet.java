package com.tapfoods.controller;

import com.tapfoods.models.Cart;
import com.tapfoods.models.CartItem;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Logger;

@WebServlet("/placeOrder")
public class PlaceOrderServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/tapfoods";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "system@123";

    private static final Logger logger = Logger.getLogger(PlaceOrderServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        logger.info("Session ID: " + session.getId());
        logger.info("Cart: " + (cart != null ? "Not null" : "Null"));
        if (cart != null) {
            logger.info("Cart items count: " + cart.getItems().size());
        }

        if (cart != null && !cart.getItems().isEmpty()) {
            float totalAmount;
            try {
                totalAmount = Float.parseFloat(request.getParameter("totalAmount"));
                logger.info("Total amount retrieved: " + totalAmount);
            } catch (NumberFormatException e) {
                logger.severe("NumberFormatException: Invalid total amount: " + request.getParameter("totalAmount"));
                response.getWriter().println("Error: Invalid total amount");
                return;
            }

            Connection conn = null;
            PreparedStatement orderStmt = null;
            PreparedStatement itemStmt = null;
            PreparedStatement historyStmt = null;
            PreparedStatement menuStmt = null;
            try {
                logger.info("Connecting to database with URL: " + DB_URL);
                conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                logger.info("Database connection established");

                conn.setAutoCommit(false); // Start transaction

                // Insert into ordertable with restaurantid
                String insertOrderTable = "INSERT INTO ordertable (userid, total_amount, restaurantid) VALUES (?, ?, ?)";
                orderStmt = conn.prepareStatement(insertOrderTable, PreparedStatement.RETURN_GENERATED_KEYS);
                Integer userId = (Integer) session.getAttribute("userId");
                if (userId == null) {
                    logger.severe("User ID is missing from session");
                    throw new SQLException("User ID is missing from session");
                }
                logger.info("Inserting into ordertable: userId = " + userId + ", totalAmount = " + totalAmount);
                orderStmt.setInt(1, userId);
                orderStmt.setFloat(2, totalAmount);

                // Fetch restaurantid based on cart items
                int restaurantId = 0;
                for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
                    CartItem item = entry.getValue();
                    if (restaurantId == 0) {
                        // Fetch the restaurantId from the menu table
                        String selectMenuQuery = "SELECT restaurantid FROM menu WHERE menuid = ?";
                        menuStmt = conn.prepareStatement(selectMenuQuery);
                        menuStmt.setInt(1, item.getItemid());
                        var resultSet = menuStmt.executeQuery();
                        if (resultSet.next()) {
                            restaurantId = resultSet.getInt("restaurantid");
                        }
                        menuStmt.close();
                    }
                }
                if (restaurantId == 0) {
                    throw new SQLException("No valid restaurant found for cart items");
                }
                orderStmt.setInt(3, restaurantId); // Set restaurantid for the order
                int affectedRows = orderStmt.executeUpdate();
                logger.info("Rows affected in ordertable: " + affectedRows);
                if (affectedRows == 0) {
                    throw new SQLException("Failed to insert order into ordertable");
                }

                int orderId = 0;
                try (var generatedKeys = orderStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        orderId = generatedKeys.getInt(1);
                        logger.info("Generated order ID: " + orderId);
                    } else {
                        throw new SQLException("Failed to retrieve order ID");
                    }
                }

                // Insert into orderitem table
                String insertOrderItem = "INSERT INTO orderitem (orderid, menuid, quantity, subtotal) VALUES (?, ?, ?, ?)";
                itemStmt = conn.prepareStatement(insertOrderItem);
                logger.info("Inserting items into orderitem table");
                for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
                    CartItem item = entry.getValue();
                    // Log the parameters for each item being inserted
                    logger.info("Inserting into orderitem: orderId = " + orderId + ", itemId = " + item.getItemid() + ", quantity = " + item.getQuantity() + ", subtotal = " + item.getSubtotal());
                    itemStmt.setInt(1, orderId);
                    itemStmt.setInt(2, item.getItemid());
                    itemStmt.setInt(3, item.getQuantity());
                    itemStmt.setFloat(4, item.getSubtotal());
                    itemStmt.addBatch();
                }
                int[] updateCounts = itemStmt.executeBatch();
                logger.info("Batch insert result in orderitem: " + java.util.Arrays.toString(updateCounts));

                // Insert into orderhistory table
                String insertOrderHistory = "INSERT INTO orderhistory (userid, orderid, total_amount) VALUES (?, ?, ?)";
                historyStmt = conn.prepareStatement(insertOrderHistory);
                logger.info("Inserting into orderhistory: userId = " + userId + ", orderId = " + orderId + ", totalAmount = " + totalAmount);
                historyStmt.setInt(1, userId);
                historyStmt.setInt(2, orderId);
                historyStmt.setFloat(3, totalAmount);

                int historyAffectedRows = historyStmt.executeUpdate();
                logger.info("Rows affected in orderhistory: " + historyAffectedRows);
                if (historyAffectedRows == 0) {
                    throw new SQLException("Failed to insert order into orderhistory");
                }

                // Update order status to delivered
                String updateOrderStatus = "UPDATE ordertable SET status = 'delivered' WHERE orderid = ?";
                try (PreparedStatement statusStmt = conn.prepareStatement(updateOrderStatus)) {
                    statusStmt.setInt(1, orderId);
                    int rowsUpdated = statusStmt.executeUpdate();
                    logger.info("Rows updated in ordertable (status set to delivered): " + rowsUpdated);
                }

                conn.commit(); // Commit transaction
                logger.info("Transaction committed successfully");
                session.removeAttribute("cart"); // Clear the cart from session
                response.getWriter().println("Order placed successfully");
            } catch (SQLException e) {
                logger.severe("SQLException occurred: " + e.getMessage());
                e.printStackTrace(response.getWriter());
                if (conn != null) {
                    try {
                        conn.rollback(); // Rollback transaction on error
                        logger.severe("Transaction rolled back due to error: " + e.getMessage());
                    } catch (SQLException ex) {
                        logger.severe("Failed to rollback transaction: " + ex.getMessage());
                    }
                }
                response.getWriter().println("Error: Database error during order placement");
            } finally {
                if (orderStmt != null) try { orderStmt.close(); } catch (SQLException e) { logger.severe("Failed to close orderStmt: " + e.getMessage()); }
                if (itemStmt != null) try { itemStmt.close(); } catch (SQLException e) { logger.severe("Failed to close itemStmt: " + e.getMessage()); }
                if (historyStmt != null) try { historyStmt.close(); } catch (SQLException e) { logger.severe("Failed to close historyStmt: " + e.getMessage()); }
                if (menuStmt != null) try { menuStmt.close(); } catch (SQLException e) { logger.severe("Failed to close menuStmt: " + e.getMessage()); }
                if (conn != null) try { conn.close(); } catch (SQLException e) { logger.severe("Failed to close connection: " + e.getMessage()); }
            }
        } else {
            logger.info("Cart is empty or null, no order placed");
            response.getWriter().println("Error: Your cart is empty");
        }
    }
}
