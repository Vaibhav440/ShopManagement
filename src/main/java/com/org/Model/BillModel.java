package com.org.Model;

import java.sql.Date;

public class BillModel {
	private String productName;
    private double unitPrice;
    private int itemQuantity;
    private Date orderDate;
    private double totalPrice;
    private double totalPrivenew;

    public double getTotalPrivenew() {
		return totalPrivenew;
	}

	public void setTotalPrivenew(double totalPrivenew) {
		this.totalPrivenew = totalPrivenew;
	}

	// Getters and setters
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}