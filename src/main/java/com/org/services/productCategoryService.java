package com.org.services;

import java.util.List;

public interface productCategoryService {

	public boolean isAddCategory(String categoryname);
	public boolean isDeleteProdCategory(String prodCategory);
	public boolean isUpdatedProdCategory(String old_productCategoryName,String New_productCategoryName);
	List<String> productCatList();
}

