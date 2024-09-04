<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="com.tapfoods.models.Cart" %>
<%@ page import="com.tapfoods.models.CartItem" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Shopping Cart</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        .button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
        }
        .button:hover {
            background-color: #0056b3;
        }
        .remove-button {
            background-color: #dc3545;
        }
        .remove-button:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Your Shopping Cart</h2>
    <%
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null && !cart.getItems().isEmpty()) {
        	// Set the restaurantId in the session
        	

    %>
    <table>
        <tr>
            <th>Item Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Subtotal</th>
            <th>Actions</th>
        </tr>
        <%
            for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
                CartItem item = entry.getValue();
        %>
        <tr>
            <td><%= item.getName() %></td>
            <td>$<%= item.getPrice() %></td>
            <td>
                <form action="cart" method="post" style="display:inline-block;">
                    <input type="hidden" name="itemid" value="<%= item.getItemid() %>">
                    <input type="number" name="quantity" value="<%= item.getQuantity() %>" min="1">
                    <input type="hidden" name="action" value="update">
                    <input type="submit" class="button" value="Update">
                </form>
            </td>
            <td>$<%= item.getSubtotal() %></td>
            <td>
                <form action="cart" method="post" style="display:inline-block;">
                    <input type="hidden" name="itemid" value="<%= item.getItemid() %>">
                    <input type="hidden" name="action" value="remove">
                    <input type="submit" class="button remove-button" value="Remove">
                </form>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <a href="home.jsp" class="button">Continue Shopping</a>
    <form action="checkout.jsp" method="post" style="text-align: center; margin-top: 20px;">
        <input type="submit" class="button" value="Proceed to Checkout">
    </form>
    <%
        } else {
    %>
    <p>Your cart is empty. <a href="home.jsp" class="button">Go back to the menu</a></p>
    <%
        }
    %>
</div>

</body>
</html>
