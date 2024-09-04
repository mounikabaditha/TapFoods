package com.tapfoods.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tapfoods.dao.OrderHisDAO;
import com.tapfoods.dbutils.DbUtils;
import com.tapfoods.models.OrderHistory;

public class OrderHisDaoImpl implements OrderHisDAO {

    Connection con;
    OrderHistory orderHistory;
    private Statement stmt;
    private ResultSet resultSet;
    private PreparedStatement pstmt;
    
    private static final String ADD_ORDER_HISTORY = "insert into `order_history`(`orderid`, `userid`, `orderdate`, `total_amount`, `status`) values(?,?,?,?,?)";
    private static final String SELECT_ALL = "select * from order_history";
    private static final String SELECT_ON_ID = "select * from `order_history` where `orderhisid`=?";
    
    int status = 0;
    
    ArrayList<OrderHistory> orderHistoryList = new ArrayList<OrderHistory>();
    
    public OrderHisDaoImpl() {
        try {
            DbUtils.myConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public int addOrderHis(OrderHistory o) {
        try {
            pstmt = con.prepareStatement(ADD_ORDER_HISTORY);
            pstmt.setInt(1, o.getOrderId());
            pstmt.setInt(2, o.getUserId());
            pstmt.setString(3, o.getOrderDate());
            pstmt.setFloat(4, o.getTotalAmount());
            pstmt.setString(5, o.getStatus());
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
    
    @Override
    public ArrayList<OrderHistory> getAllOrderHis() {
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(SELECT_ALL);
            orderHistoryList = extractOrderHistoryFromResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderHistoryList;
    }
    
    @Override
    public OrderHistory getOrderHis(int orderhisId) {
        try {
            pstmt = con.prepareStatement(SELECT_ON_ID);
            pstmt.setInt(1, orderhisId);
            resultSet = pstmt.executeQuery();
            orderHistoryList = extractOrderHistoryFromResultSet(resultSet);
            orderHistory = orderHistoryList.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderHistory;
    }
    
    ArrayList<OrderHistory> extractOrderHistoryFromResultSet(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                orderHistoryList.add(new OrderHistory(
                    resultSet.getInt("orderhisid"),
                    resultSet.getInt("orderid"),
                    resultSet.getInt("userid"),
                    resultSet.getString("orderdate"),
                    resultSet.getFloat("total_amount"),
                    resultSet.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistoryList;
    }
}
