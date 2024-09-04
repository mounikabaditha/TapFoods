<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="com.tapfoods.models.Cart" %>
<%@ page import="com.tapfoods.models.CartItem" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Checkout</title>
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
        .total-row {
            font-weight: bold;
        }
        .button {
            background-color: #28a745;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            display: block;
            width: 100%;
            margin-top: 20px;
        }
        .button:hover {
            background-color: #218838;
        }
        .back-button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            display: block;
            width: 100%;
            margin-top: 20px;
        }
        .back-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Checkout</h2>
    <%
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null && !cart.getItems().isEmpty()) {
        	

            float totalAmount = 0;
    %>
    <table>
        <tr>
            <th>Item Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Subtotal</th>
        </tr>
        <%
            for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
                CartItem item = entry.getValue();
                totalAmount += item.getSubtotal();
        %>
        <tr>
            <td><%= item.getName() %></td>
            <td>$<%= item.getPrice() %></td>
            <td><%= item.getQuantity() %></td>
            <td>$<%= item.getSubtotal() %></td>
        </tr>
        <%
            }
        %>
        <tr class="total-row">
            <td colspan="3">Total Amount</td>
            <td>$<%= totalAmount %></td>
        </tr>
    </table>

    <form action="placeOrder" method="post">
        <input type="hidden" name="totalAmount" value="<%= totalAmount %>">
        <input type="submit" class="button" value="Place Order">
    </form>
    <a href="home.jsp" class="back-button">Continue Shopping</a>
    <%
        } else {
    %>
    <p>Your cart is empty. <a href="home.jsp" class="back-button">Go back to shopping</a></p>
    <%
        }
    %>
</div>

</body>
</html>
