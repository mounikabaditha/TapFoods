package com.tapfoods.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tapfoods.dbutils.DbUtils;


@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phono");

        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        
        try {
        	con = DbUtils.myConnect();
        	String sql = "INSERT INTO user (username, email, password, address, phono) VALUES (?, ?, ?, ?, ?)";
            statement = con.prepareStatement(sql); 
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setString(4, address);
            statement.setString(5, phoneNumber);

            int result = statement.executeUpdate();

            if (result > 0) {
                
                response.sendRedirect("login.jsp");
            } else {
                
                response.getWriter().println("Registration failed, please try again.");
            }

        } catch (SQLException e) {
           
            e.printStackTrace();
            response.getWriter().println("An error occurred during registration: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("An unexpected error occurred: " + e.getMessage());
        }
    }
}