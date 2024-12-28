package com.org.services;

import com.org.repository.stockRepository;
import com.org.repository.stockRepositoryImpl;

public class stockServiceImpl implements stockService{
	
	stockRepository sRepository=new stockRepositoryImpl();
	
	public boolean isAddStockOfProduct(String productname,int stock_quantity)
	{
	  return sRepository.isAddStockOfProduct(productname, stock_quantity);
	}

	@Override
	public boolean isUpdateStock(String productName,int update_stock) {
		return sRepository.isUpdateStock(productName,update_stock);
	}

}
