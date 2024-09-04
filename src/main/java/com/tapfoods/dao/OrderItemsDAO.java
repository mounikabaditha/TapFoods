package com.tapfoods.dao;

import java.util.ArrayList;

import com.tapfoods.models.OrderItem;

public interface OrderItemsDAO {
	
    int addOrderItems(OrderItem o);
	
	ArrayList<OrderItem> getAllOrderItems();
	
	OrderItem getOrderItem(int orderitem_id);

}
