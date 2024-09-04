package com.tapfoods.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tapfoods.dao.OrderDAO;
import com.tapfoods.dbutils.DbUtils;
import com.tapfoods.models.Order;

public class OrderDaoImpl implements OrderDAO {
    
    Connection con;
    Order order;
    private Statement stmt;
    private ResultSet resultSet;
    private PreparedStatement pstmt;
    private static final String ADD_ORDER = "insert into `orders`(`restaurantid`, `order_time`, `total_amount`, `status`, `userid`) values(?,?,?,?,?)";
    private static final String SELECT_ALL = "select * from orders";
    private static final String SELECT_ON_ID = "select * from `orders` where `orderid`=?";
    int status = 0;
    
    ArrayList<Order> orderList = new ArrayList<Order>();
    
    public OrderDaoImpl() {
        try {
            DbUtils.myConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public int addOrder(Order o) {
        try {
            pstmt = con.prepareStatement(ADD_ORDER);
            pstmt.setInt(1, o.getRestaurantId());
            pstmt.setString(2, o.getOrderTime());
            pstmt.setFloat(3, o.getTotalAmount());
            pstmt.setString(4, o.getStatus());
            pstmt.setInt(5, o.getUserId());
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
    
    @Override
    public ArrayList<Order> getAllOrders() {
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(SELECT_ALL);
            orderList = extractOrderFromResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderList;
    }
    
    @Override
    public Order getOrder(int orderId) {
        try {
            pstmt = con.prepareStatement(SELECT_ON_ID);
            pstmt.setInt(1, orderId);
            resultSet = pstmt.executeQuery();
            orderList = extractOrderFromResultSet(resultSet);
            order = orderList.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }
    
    ArrayList<Order> extractOrderFromResultSet(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                orderList.add(new Order(
                    resultSet.getInt("orderid"),
                    resultSet.getInt("restaurantid"),
                    resultSet.getString("order_time"),
                    resultSet.getFloat("total_amount"),
                    resultSet.getString("status"),
                    resultSet.getInt("userid")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }
}
