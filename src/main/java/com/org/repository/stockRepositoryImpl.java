package com.org.repository;

import org.apache.log4j.Logger;

public class stockRepositoryImpl extends DBState implements stockRepository {

	private static final Logger logger = Logger.getLogger(stockRepositoryImpl.class);
	int value=0;
	@Override
	public boolean isAddStockOfProduct(String productname, int stock_quantity) {
		try 
		{
			logger.info("Adding stock for product: " + productname + " with quantity: " + stock_quantity);
			stmt=conn.prepareStatement("select pid from product where pname=?");
			stmt.setString(1, productname);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				stmt=conn.prepareStatement("insert into stock values('0',?,?)");
				stmt.setInt(1, stock_quantity);
				stmt.setInt(2, rs.getInt(1));
				
				value=stmt.executeUpdate();
			}
			return value>0?true:null;
		} catch (Exception e) {
			 logger.error("Error while adding stock for product: " + productname, e);
			return false;
		}
	}
	@Override
	public boolean isUpdateStock(String productName,int update_stock) {
		try 
		{
			logger.info("Updating stock for product: " + productName + " to quantity: " + update_stock);
			stmt=conn.prepareStatement("select pid from product where pname=?");
			stmt.setString(1, productName);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				    stmt = conn.prepareStatement("update stock set stock_quantity=? where pid=?");
	                stmt.setInt(1, update_stock);
	                stmt.setInt(2, rs.getInt(1)); 
				
				value=stmt.executeUpdate();
			}
			return value>0?true:null;
		} catch (Exception e) {
			logger.error("Error while updating stock for product: " + productName, e);
			return false;
		}
	}

}
