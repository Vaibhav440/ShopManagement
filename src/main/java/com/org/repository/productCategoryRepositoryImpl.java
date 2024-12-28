package com.org.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.cj.protocol.ValueEncoder;

public class productCategoryRepositoryImpl extends DBState implements productcategoryRepository {
	
	private static final Logger logger = Logger.getLogger(productCategoryRepositoryImpl.class);
	int value=0;
	@Override
	public boolean isAddCategory(String categoryname) {
		try 
		{
			 logger.info("Adding new product category: " + categoryname);
			stmt=conn.prepareStatement("insert into  product_category values('0',?)");
			stmt.setString(1, categoryname);
			value=stmt.executeUpdate();
			logger.info("Category added successfully: " + categoryname);
			return value>0?true:null;
		} catch (Exception e) {
			logger.error("Error while adding category: " + categoryname, e);
			return false;
		}
	}
	@Override
	public List<String> productCatList() {
		try 
		{
			logger.info("Fetching product category list...");
			List<String> prodCategoryList=new ArrayList<String>();
			stmt=conn.prepareStatement("select category_name from product_category");
			rs=stmt.executeQuery();
			String prodCategoryNameString="";
			while(rs.next())
			{
				prodCategoryNameString=rs.getString(1);
				prodCategoryList.add(prodCategoryNameString);
			}
			logger.info("Product category list fetched successfully.");
			return prodCategoryList;
		} catch (Exception e) {
			logger.error("Error while fetching product category list", e);
			return null;
		}
	}
	@Override
	public boolean isDeleteProdCategory(String prodCategory) {
		try 
		{
			logger.info("Deleting product category: " + prodCategory);
			stmt=conn.prepareStatement("select cid from product_category where category_name =?");
			stmt.setString(1, prodCategory);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				stmt=conn.prepareStatement("delete from  product_category where cid =?");
				stmt.setInt(1, rs.getInt(1));
				value=stmt.executeUpdate();
			}
			return value>0?true:null;
		} catch (Exception e) {
			logger.error("Error while deleting product category: " + prodCategory, e);
			return false;
		}
	}
	@Override
	public boolean isUpdatedProdCategory(String old_productCategoryName, String New_productCategoryName) {
		try 
		{
			 logger.info("Updating product category from '" + old_productCategoryName + "' to '" + New_productCategoryName + "'");
			stmt=conn.prepareStatement("select cid from product_category where category_name =?");
			stmt.setString(1, old_productCategoryName);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				stmt=conn.prepareStatement("update product_category set category_name=? where cid =?");
				stmt.setString(1, New_productCategoryName);
				stmt.setInt(2, rs.getInt(1));
				value=stmt.executeUpdate();
			}
			return value>0?true:null;
		} catch (Exception e) {
			logger.error("Error while updating product category from '" + old_productCategoryName + "' to '" + New_productCategoryName + "'", e);
			return false;
		}
	}
	
	

}
