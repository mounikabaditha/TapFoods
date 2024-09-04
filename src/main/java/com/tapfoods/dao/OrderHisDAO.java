package com.tapfoods.dao;

import java.util.ArrayList;

import com.tapfoods.models.OrderHistory;



public interface OrderHisDAO {
	
int addOrderHis(OrderHistory o);
	
	ArrayList<OrderHistory> getAllOrderHis();
	
	OrderHistory getOrderHis(int orderhisid);

}
