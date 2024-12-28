package com.org.services;

import java.util.List;

import com.org.repository.productCategoryRepositoryImpl;
import com.org.repository.productcategoryRepository;

public class productCategoryServiceImpl implements productCategoryService{
	
	productcategoryRepository pRepository=new productCategoryRepositoryImpl();
	
	@Override
	public boolean isAddCategory(String categoryname) {
		return pRepository.isAddCategory(categoryname);
	}

	@Override
	public List<String> productCatList() {
		return pRepository.productCatList();
	}

	@Override
	public boolean isDeleteProdCategory(String prodCategory) {
		return pRepository.isDeleteProdCategory(prodCategory);
	}

	@Override
	public boolean isUpdatedProdCategory(String old_productCategoryName, String New_productCategoryName) {
		return pRepository.isUpdatedProdCategory(old_productCategoryName, New_productCategoryName);
	}

	

}
