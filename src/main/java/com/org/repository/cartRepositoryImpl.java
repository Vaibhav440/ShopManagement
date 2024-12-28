package com.org.repository;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.org.Model.*;


public class cartRepositoryImpl extends DBState implements cartRepository {

	private static Logger logger = Logger.getLogger(cartRepositoryImpl.class);
	    @Override
	    public boolean addProductToCart(UserModel cutlogin, String productname, int quantity) {
	        int value = 0;

	        try {
	        	
	        	logger.info("Attempting to add product to cart. Product: " + productname + ", Quantity: " + quantity);
	            stmt = conn.prepareStatement(
	                "SELECT p.pid, s.stock_quantity FROM product p " +
	                "INNER JOIN stock s ON p.pid = s.pid WHERE p.pname = ?"
	            );
	            stmt.setString(1, productname);
	            rs = stmt.executeQuery();

	            if (rs.next()) {
	                int productId = rs.getInt("p.pid");
	                int stockQuantity = rs.getInt("s.stock_quantity");

	                logger.info("Product found. ID: " + productId + ", Stock Quantity: " + stockQuantity);
	                if (stockQuantity >= quantity) {
	                    // Insert into cart with cart_group_id
	                    stmt = conn.prepareStatement(
	                        "INSERT INTO cart (cid, uid, pid, item_quantity,status) " +
	                        "VALUES ('0', ?, ?, ?, ?)"
	                    );
	                    stmt.setInt(1, cutlogin.getUid());
	                    stmt.setInt(2, productId);
	                    stmt.setInt(3, quantity);
	                    stmt.setString(4, "false");
	           

	                    value = stmt.executeUpdate();

	                    if (value > 0) {
	                        // Update stock quantity
	                        stmt = conn.prepareStatement(
	                            "UPDATE stock s " +
	                            "INNER JOIN product p ON p.pid = s.pid " +
	                            "SET s.stock_quantity = s.stock_quantity - ? WHERE p.pid = ?"
	                        );
	                        stmt.setInt(1, quantity);
	                        stmt.setInt(2, productId);
	                        int updateCount = stmt.executeUpdate();

	                        // Check if the stock update was successful
	                        if (updateCount > 0) {
	                            System.out.println("Product added to cart and stock updated successfully.");
	                        } else {
	                        	 logger.error("Failed to update stock for Product ID " + productId);
	                        }
	                    } else {
	                    	logger.error("Failed to add product to cart. Product ID: " + productId);
	                    }
	                } else {
	                	logger.warn("Insufficient stock for Product: " + productname);
	                }
	            } else {
	            	 logger.warn("Product not found: " + productname);
	            }

	            return value > 0;
	        } catch (Exception e) {
	        	logger.error("Error while adding product to cart. Product: " + productname + ", Quantity: " + quantity, e);
	            return false;
	        }
	    }
	


	@Override
	public List<cartModel> viewCart(UserModel cUserModel) {
		try 
		{
			 logger.info("Fetching cart details for User ID: " + cUserModel.getUid());
			List<cartModel> viewAllProductInCart=new ArrayList<cartModel>();
			stmt=conn.prepareStatement(" select c.cid,c.uid,c.pid,c.item_quantity from cart c inner join user u on c.uid=u.uid where u.uid=?");
			stmt.setInt(1, cUserModel.getUid());
			rs=stmt.executeQuery();
			while(rs.next())
			{
				cartModel model=new cartModel(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4));
				viewAllProductInCart.add(model);
			}
			logger.info("Cart details fetched successfully for User ID: " + cUserModel.getUid());
			return viewAllProductInCart;
		} catch (Exception e) {
			logger.error("Error while fetching cart details for User ID: " + cUserModel.getUid(), e);
			return null;
		}
	}

}
