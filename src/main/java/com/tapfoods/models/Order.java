package com.tapfoods.models;

public class Order {
	

	
	    private int orderId;
	    private int restaurantId;
	    private String orderTime;
	    private float totalAmount;
	    private String status;
	    private int userId;

	    // Default constructor
	    public Order() {
	    }

	    // Parameterized constructor
	    public Order(int orderId, int restaurantId, String orderTime, float totalAmount, String status, int userId) {
	        this.orderId = orderId;
	        this.restaurantId = restaurantId;
	        this.orderTime = orderTime;
	        this.totalAmount = totalAmount;
	        this.status = status;
	        this.userId = userId;
	    }
	    

	    public Order(int restaurantId, String orderTime, float totalAmount, String status, int userId) {
			super();
			this.restaurantId = restaurantId;
			this.orderTime = orderTime;
			this.totalAmount = totalAmount;
			this.status = status;
			this.userId = userId;
		}

		// Getter and Setter methods
	    public int getOrderId() {
	        return orderId;
	    }

	    public void setOrderId(int orderId) {
	        this.orderId = orderId;
	    }

	    public int getRestaurantId() {
	        return restaurantId;
	    }

	    public void setRestaurantId(int restaurantId) {
	        this.restaurantId = restaurantId;
	    }

	    public String getOrderTime() {
	        return orderTime;
	    }

	    public void setOrderTime(String orderTime) {
	        this.orderTime = orderTime;
	    }

	    public float getTotalAmount() {
	        return totalAmount;
	    }

	    public void setTotalAmount(float totalAmount) {
	        this.totalAmount = totalAmount;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    public int getUserId() {
	        return userId;
	    }

	    public void setUserId(int userId) {
	        this.userId = userId;
	    }

	    // toString method
	    @Override
	    public String toString() {
	        return "OrderTable{" +
	                "orderId=" + orderId +
	                ", restaurantId=" + restaurantId +
	                ", orderTime=" + orderTime +
	                ", totalAmount=" + totalAmount +
	                ", status='" + status + '\'' +
	                ", userId=" + userId +
	                '}';
	    }
	}



