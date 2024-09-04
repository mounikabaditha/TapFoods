package com.tapfoods.models;

public class OrderHistory {
    private int orderHisId;
    private int orderId;
    private int userId;
    private String orderDate;
    private float totalAmount;
    private String status;

    // Default constructor
    public OrderHistory() {
    }

    // Parameterized constructor
    public OrderHistory(int orderHisId, int orderId, int userId, String orderDate, float totalAmount, String status) {
        this.orderHisId = orderHisId;
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }
    
    

    public OrderHistory(int orderId, int userId, String orderDate, float totalAmount, String status) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.status = status;
	}

	// Getter and Setter methods
    public int getOrderHisId() {
        return orderHisId;
    }

    public void setOrderHisId(int orderHisId) {
        this.orderHisId = orderHisId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
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

    // toString method
    @Override
    public String toString() {
        return "OrderHistory{" +
                "orderHisId=" + orderHisId +
                ", orderId=" + orderId +
                ", userId=" + userId +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                ", status='" + status + '\'' +
                '}';
    }
}

