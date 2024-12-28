package com.org.services;

public interface stockService {
	
  public boolean isAddStockOfProduct(String productname,int stock_quantity);
  public boolean isUpdateStock(String productName,int update_stock);

}
