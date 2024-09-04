package com.tapfoods.dao;

import java.util.ArrayList;

import com.tapfoods.models.Order;


public interface OrderDAO {
	
    int addOrder(Order o);
	
	ArrayList<Order> getAllOrders();
	
	Order getOrder(int orderid);
	

}
