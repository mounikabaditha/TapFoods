<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.tapfoods.daoimpl.MenuDaoImpl" %>
<%@ page import="com.tapfoods.models.Menu" %>
<%@ page import="java.io.StringWriter" %>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menu List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 1200px;
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
        .menu-grid {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
        }
        .menu-card {
            border: 1px solid #ddd;
            border-radius: 8px;
            overflow: hidden;
            width: calc(33.33% - 20px);
            background-color: #f9f9f9;
            box-sizing: border-box;
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 10px;
        }
        .menu-image {
            width: 100%;
            height: 150px;
            object-fit: cover;
            margin-bottom: 10px;
        }
        .menu-info {
            text-align: center;
        }
        .menu-info h3 {
            margin: 0 0 10px;
            color: #555;
        }
        .menu-info p {
            margin: 0 0 10px;
            color: #777;
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
            margin-top: 20px;
            display: inline-block;
        }
        .back-button:hover {
            background-color: #0056b3;
        }
        .add-to-cart-button {
            background-color: #28a745;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            margin-top: 10px;
            display: inline-block;
        }
        .add-to-cart-button:hover {
            background-color: #218838;
        }
        .quantity-box {
            margin-top: 10px;
            margin-bottom: 10px;
        }
        .quantity-input {
            width: 60px;
            padding: 5px;
            font-size: 16px;
            text-align: center;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Menu List</h2>

    <%
        int restaurantId = Integer.parseInt(request.getParameter("restaurantid"));
        MenuDaoImpl menuDao = new MenuDaoImpl();
        ArrayList<Menu> menuList = null;
        try {
            menuList = menuDao.getMenu(restaurantId);
        } catch (Exception e) {
            out.println("Error retrieving menu data: " + e.getMessage());

            // Capture the stack trace and print it
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            out.println(sw.toString());
        }
    %>

    <div class="menu-grid">
        <%
            if (menuList != null) {
                for (Menu menu : menuList) {
        %>
        <div class="menu-card">
            <img class="menu-image" src="<%= request.getContextPath() + "/" + menu.getImgPath() %>" alt="Menu Image">
            <div class="menu-info">
                <h3><%= menu.getMenuName() %></h3>
                <p><strong>Price:</strong> $<%= menu.getPrice() %></p>
                <p><strong>Description:</strong> <%= menu.getItemDescription() %></p>
                <form action="cart" method="post">
                    <input type="hidden" name="menuId" value="<%= menu.getMenuId() %>">
                    <input type="hidden" name="name" value="<%= menu.getMenuName() %>">
                    <input type="hidden" name="price" value="<%= menu.getPrice() %>">
                    <div class="quantity-box">
                        <label for="quantity_<%= menu.getMenuId() %>">Quantity:</label>
                        <input type="number" id="quantity_<%= menu.getMenuId() %>" name="quantity" class="quantity-input" value="1" min="1">
                    </div>
                    <input type="submit" class="add-to-cart-button" value="Add to Cart">
                    <input type="hidden" name="action" value="add">
                </form>
            </div>
        </div>
        <%
                }
            } else {
        %>
        <div class="menu-card">
            <p>No menu data available for this restaurant.</p>
        </div>
        <%
            }
        %>
    </div>

    <a href="home.jsp" class="back-button">Back to Restaurant List</a>
</div>

</body>
</html>
