package com.org.Model;


import java.sql.Date;


public class orderModel {
    private int orderId;         // order_id
    private int uid;             // uid
    private String orderDate; // order_date
    private int totalPrice;      // total_price

    // Default Constructor
    public orderModel() {}

    // Parameterized Constructor
    public orderModel(int orderId, int uid, String orderDate, int totalPrice) {
        this.orderId = orderId;
        this.uid = uid;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String order_date) {
        this.orderDate = order_date;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    // toString Method
    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", uid=" + uid +
                ", orderDate=" + orderDate +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
