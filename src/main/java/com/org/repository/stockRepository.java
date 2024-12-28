package com.org.repository;

public interface stockRepository {

	public boolean isAddStockOfProduct(String productname,int stock_quantity);
	 public boolean isUpdateStock(String productName,int update_stock);
}
