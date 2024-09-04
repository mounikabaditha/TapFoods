<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.tapfoods.daoimpl.RestuarantDaoImpl" %>
<%@ page import="com.tapfoods.models.Restuarant" %>
<%@ page import="java.io.StringWriter" %>
<%@ page import="java.io.PrintWriter" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Restaurant List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        
        
        .restaurant-grid {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
        }
        .restaurant-card {
            border: 1px solid #ddd;
            border-radius: 8px;
            overflow: hidden;
            width: calc(20% - 20px); /* 5 items per row with gap */
            background-color: #f9f9f9;
            box-sizing: border-box;
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 10px;
        }
        .restaurant-image {
            width: 100%;
            height: 150px;
            object-fit: cover;
            margin-bottom: 10px;
        }
        .restaurant-info {
            text-align: center;
        }
        .restaurant-info h3 {
            margin: 0 0 10px;
            color: #555;
        }
        .restaurant-info p {
            margin: 0 0 10px;
            color: #777;
        }
        .view-menu-button {
            background-color: #28a745;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
        }
        .view-menu-button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>



<div class="navbar">
    <div class="brand">TapFoods</div>
    
</div>

<div class="container">
   

    <%
        RestuarantDaoImpl dao = new RestuarantDaoImpl();
        ArrayList<Restuarant> restaurantList = null;
        try {
            restaurantList = dao.getAllResturants();
        } catch (Exception e) {
            out.println("Error retrieving restaurant data: " + e.getMessage());

            // Capture the stack trace and print it
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            out.println(sw.toString());
        }
    %>

    <div class="restaurant-grid">
        <%
            if (restaurantList != null) {
                int count = 0;
                for (Restuarant r : restaurantList) {
                    if (count % 5 == 0 && count > 0) {
                        // Insert a line break every 5 items
                        out.println("</div><div class='restaurant-grid'>");
                    }
        %>
        <div class="restaurant-card">
            <img class="restaurant-image" src="<%= request.getContextPath() + "/" + r.getImgpath() %>" alt="Restaurant Image">
            <div class="restaurant-info">
                <h3><%= r.getRestuarantname() %></h3>
                <p><strong>ID:</strong> <%= r.getRestuarantid() %></p>
                <p><strong>Delivery Time:</strong> <%= r.getDeliverytime() %> mins</p>
                <p><strong>Cuisine Type:</strong> <%= r.getCuisinetype() %></p>
                <p><strong>Ratings:</strong> <%= r.getRatings() %></p>
                <p><strong>Address:</strong> <%= r.getAddress() %></p>
                <p><strong>Active:</strong> <%= r.getIsactive() %></p>
                <a href="menu.jsp?restaurantid=<%= r.getRestuarantid() %>" class="view-menu-button">View Menu</a>
            </div>
        </div>
        <%
                    count++;
                }
            } else {
        %>
        <div class="restaurant-card">
            <p>No restaurant data available.</p>
        </div>
        <%
            }
        %>
    </div>
</div>

</body>
</html>
