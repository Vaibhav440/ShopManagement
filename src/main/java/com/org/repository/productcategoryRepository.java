package com.org.repository;

import java.util.List;

public interface productcategoryRepository {
	
	public boolean isAddCategory(String categoryname);
	public boolean isDeleteProdCategory(String prodCategory);
	public boolean isUpdatedProdCategory(String old_productCategoryName,String New_productCategoryName);
	List<String> productCatList();

}
