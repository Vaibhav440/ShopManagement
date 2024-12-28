package com.org.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class order_detailModel {
    private int orderDetailId; // Primary Key
    private int orderId;       // Foreign Key
    private int pid;           // Foreign Key
    private int quantity;      // Quantity of product
    private int unitPrice;     // Price per unit
    private int totalPrice;    // Total price for the product
    private int uid;           // User ID

    // No-arg constructor
    public order_detailModel() {
    }

    // Parameterized constructor
    public order_detailModel(int orderDetailId, int orderId, int pid, int quantity, int unitPrice, int totalPrice, int uid) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.pid = pid;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.uid = uid;
    }

   
    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderDetailId=" + orderDetailId +
                ", orderId=" + orderId +
                ", pid=" + pid +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", totalPrice=" + totalPrice +
                ", uid=" + uid +
                '}';
    }
}
