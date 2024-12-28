package com.org.repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.org.Model.*;

public class ProductRepositoryImpl extends DBState implements ProductRepository {

	private static final Logger logger = Logger.getLogger(ProductRepositoryImpl.class);
	int value;
	@Override
	public boolean isAddProduct(String prodCategoryString,String productnameString,int u_price,int stock_quant) {
		try 
		{
			logger.info("Adding product: " + productnameString + " under category: " + prodCategoryString);
		  cstmt=conn.prepareCall("call saveProduct(?,?,?,?)");
		  cstmt.setString(1, productnameString);
		  cstmt.setInt(2, u_price);
		  cstmt.setString(3, prodCategoryString);
		  cstmt.setInt(4, stock_quant);
		  System.out.println(prodCategoryString);
		  boolean b=cstmt.execute();
		  logger.info("Product added successfully: " + productnameString);
		  return !b;  
		}
		catch (Exception e) 
		{
			logger.error("Error while adding product: " + productnameString, e);
			return false;
		}
	}
	
	public List<productModel> getProductDetailByCategoryList(String productcategory) {
		try 
		{
			logger.info("Fetching product details for category: " + productcategory);
	      List<productModel> viewProductModels=new ArrayList<productModel>();
		  stmt=conn.prepareStatement("select cid from product_category where category_name=?");
		  stmt.setString(1, productcategory);
		  rs=stmt.executeQuery();
		  if(rs.next())
		  {
			  stmt=conn.prepareStatement("select pname,unit_price from product where cid=?");
			  stmt.setInt(1, rs.getInt(1));
			  rs=stmt.executeQuery();
			  while(rs.next())
			  {
				  productModel model = new productModel(rs.getString("pname"), rs.getInt("unit_price"));
				  viewProductModels.add(model);  
			  }
		  }
		  
		  logger.info("Product details fetched successfully for category: " + productcategory);
		  return viewProductModels;
		} catch (Exception e) {
			logger.error("Error while fetching product details for category: " + productcategory, e);
			return null;
		}
	}
	@Override
	public List<productModel> list(String prodname) {
		try 
		{
			logger.info("Fetching product details for product name: " + prodname);
			List<productModel>list =new ArrayList<productModel>();
			stmt=conn.prepareStatement("select pname,unit_price from product where pname=?");
			stmt.setString(1, prodname);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				productModel model = new productModel(rs.getString("pname"), rs.getInt("unit_price"));
				list.add(model);
			}
			logger.info("Product details fetched successfully for product name: " + prodname);
			return list;
		} catch (Exception e) {
			logger.error("Error while fetching product details for product name: " + prodname, e);
			return null;
		}
	}
	@Override
	public boolean isDeleteProduct(String prodname) {
		try
		{
			logger.info("Deleting product: " + prodname);
			stmt=conn.prepareStatement("select pid from product where pname=?");
			stmt.setString(1, prodname);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				stmt=conn.prepareStatement("delete from product where pid=?");
				stmt.setInt(1, rs.getInt(1));
				value=stmt.executeUpdate();
			}
			return value>0?true:null;
					
		}catch(Exception e)
		{
			logger.error("Error while deleting product: " + prodname, e);
			return false;
		}
	}
	@Override
	public boolean isUpdateProduct(String oldprodutname,String newprodutname) {
		try
		{
			logger.info("Updating product name from: " + oldprodutname + " to: " + newprodutname);
			stmt=conn.prepareStatement("select pid from product where pname=?");
			stmt.setString(1, oldprodutname);
			rs=stmt.executeQuery();
			if(rs.next())
			{
				stmt=conn.prepareStatement("Update product Set pname=? where pid=?");
				stmt.setString(1,newprodutname);
				stmt.setInt(2, rs.getInt(1));	
				value=stmt.executeUpdate();
			}
			return value>0?true:null;
					
		}catch(Exception e)
		{
			logger.error("Error while updating product name from: " + oldprodutname + " to: " + newprodutname, e);
			return false;
		}
	}

	@Override
	public List<productModel> getProductDetailByCategoryList1(String productcategory) {
		try 
		{
			logger.info("Fetching product details for category: " + productcategory + " with available stock.");
	      List<productModel> viewProductModels1=new ArrayList<productModel>();
		  stmt=conn.prepareStatement("select cid from product_category where category_name=?");
		  stmt.setString(1, productcategory);
		  rs=stmt.executeQuery();
		  if(rs.next())
		  {
			 
				  stmt=conn.prepareStatement("select p.pname,p.unit_price from product p inner join stock o on p.pid=o.pid where p.cid=? and o.stock_quantity>0");
				  stmt.setInt(1, rs.getInt(1));
				  rs=stmt.executeQuery();
				  while(rs.next())
				  {
					  productModel model = new productModel(rs.getString("pname"), rs.getInt("unit_price"));
					  viewProductModels1.add(model);  
				  }
				  logger.info("Product details with available stock fetched successfully for category: " + productcategory);
				  return viewProductModels1;
		  }
		  
	      else 
	      {
	    	  logger.warn("No stock available for category: " + productcategory);
		  }
		  
		 }
		 catch (Exception e) {
			 logger.error("Error while fetching product details with stock for category: " + productcategory, e);
		}
		return null;
	}

	@Override
	public List<productModel> list1(String prodname) {
		try 
		{
			logger.info("Fetching product details with available stock for product name: " + prodname);
			List<productModel>list1 =new ArrayList<productModel>();
			stmt=conn.prepareStatement("select pname,unit_price from product where pname=?");
			stmt.setString(1, prodname);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				productModel model =new productModel(rs.getString("pname"),rs.getInt("unit_price"));
				list1.add(model);
			}
			logger.info("Product details with available stock fetched successfully for product name: " + prodname);
			return list1;
		} catch (Exception e) {
			 logger.error("Error while fetching product details with stock for product name: " + prodname, e);
			return null;
			}
	}
	
	
	

}
