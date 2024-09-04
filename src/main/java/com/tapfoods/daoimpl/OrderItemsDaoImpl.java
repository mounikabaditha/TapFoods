package com.tapfoods.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tapfoods.dao.OrderItemsDAO;
import com.tapfoods.dbutils.DbUtils;
import com.tapfoods.models.OrderItem;

public class OrderItemsDaoImpl implements OrderItemsDAO {

    Connection con;
    OrderItem orderItem;
    private Statement stmt;
    private ResultSet resultSet;
    private PreparedStatement pstmt;
    
    private static final String ADD_ORDER_ITEM = "insert into `order_items`(`orderid`, `menuid`, `quantity`, `subtotal`) values(?,?,?,?)";
    private static final String SELECT_ALL = "select * from order_items";
    private static final String SELECT_ON_ID = "select * from `order_items` where `orderitem_id`=?";
    
    int status = 0;
    
    ArrayList<OrderItem> orderItemList = new ArrayList<OrderItem>();
    
    public OrderItemsDaoImpl() {
        try {
            DbUtils.myConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public int addOrderItems(OrderItem o) {
        try {
            pstmt = con.prepareStatement(ADD_ORDER_ITEM);
            pstmt.setInt(1, o.getOrderId());
            pstmt.setInt(2, o.getMenuId());
            pstmt.setInt(3, o.getQuantity());
            pstmt.setFloat(4, o.getSubTotal());
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
    
    public ArrayList<OrderItem> getAllOrderItems() {
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(SELECT_ALL);
            orderItemList = extractOrderItemsFromResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderItemList;
    }
    
    public OrderItem getOrderItem(int orderItemId) {
        try {
            pstmt = con.prepareStatement(SELECT_ON_ID);
            pstmt.setInt(1, orderItemId);
            resultSet = pstmt.executeQuery();
            orderItemList = extractOrderItemsFromResultSet(resultSet);
            orderItem = orderItemList.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderItem;
    }
    
    ArrayList<OrderItem> extractOrderItemsFromResultSet(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                orderItemList.add(new OrderItem(
                    resultSet.getInt("orderitem_id"),
                    resultSet.getInt("orderid"),
                    resultSet.getInt("menuid"),
                    resultSet.getInt("quantity"),
                    resultSet.getFloat("subtotal")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItemList;
    }
}
