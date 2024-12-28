package com.org.Model;

public class productModel {
	int pid;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(int unit_price) {
		this.unit_price = unit_price;
	}
	
	String pname;
	int unit_price;
	int cid;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getStock_id() {
		return stock_id;
	}
	public void setStock_id(int stock_id) {
		this.stock_id = stock_id;
	}
	int stock_id;
	
	public productModel()
	{
		
	}
	public productModel(int pid,String pname,int unit_price,int cid,int stock_id)
	{
		this.pid=pid;
		this.pname=pname;
		this.unit_price=unit_price;
		this.cid=cid;
		this.stock_id=stock_id;
		
	}
	public productModel(String pname,int unit_price)
	{
		this.pname=pname;
		this.unit_price=unit_price;	
	}
	
	public String toString()
	{
		return "productid-"+pid+" productname-"+pname+" unit_pice-"+unit_price+" cid-->"+cid+" stock_id-->"+stock_id;
	}

}
