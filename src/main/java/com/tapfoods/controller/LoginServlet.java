package com.tapfoods.controller;

import com.tapfoods.dbutils.DbUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish the connection
            con = DbUtils.myConnect();

            // Query to check the user in the database
            String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);

            // Execute the query
            resultSet = statement.executeQuery();

            // If user exists
            if (resultSet.next()) {
                // Get the userId from the ResultSet
                int userId = resultSet.getInt("userid");  // Assuming the column is named "id"
                
                // Create a session
                HttpSession session = request.getSession();
                
                // Store the userId in session
                session.setAttribute("userId", userId);
                session.setAttribute("userEmail", email);  // You can also store additional info like email if needed
                
                // Redirect to the home page after successful login
                response.sendRedirect("home.jsp");
            } else {
                // Redirect to signup page if login fails
                response.sendRedirect("signup.jsp");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("An error occurred during login: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("An unexpected error occurred: " + e.getMessage());
        } finally {
            // Close resources to prevent memory leaks
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
