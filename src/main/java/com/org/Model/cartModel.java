package com.org.Model;

public class cartModel {
    private int cid;             // cart id
    private int uid;             // user id
    private int pid;             // product id
    private int itemQuantity;    // item quantity
    private String status;       // status

    // Default Constructor
    public cartModel() {}

    // Parameterized Constructor
    public cartModel(int cid, int uid, int pid, int itemQuantity) {
        this.cid = cid;
        this.uid = uid;
        this.pid = pid;
        this.itemQuantity = itemQuantity;
    }

    // Getters and Setters
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString Method
    @Override
    public String toString() {
        return "CartModel{" +
                "cid=" + cid +
                ", uid=" + uid +
                ", pid=" + pid +
                ", itemQuantity=" + itemQuantity +
                ", status='" + status + '\'' +
                '}';
    }
}
