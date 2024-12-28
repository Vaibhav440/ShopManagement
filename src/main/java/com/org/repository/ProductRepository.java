package com.org.repository;

import java.util.List;

import com.org.Model.productModel;

public interface ProductRepository {

	public boolean isAddProduct(String prodCategoryString,String productnameString,int u_price,int stock_quant);
	List<productModel> getProductDetailByCategoryList(String productcategory);
	List<productModel>list(String productname);
	public boolean isDeleteProduct(String prodname);
	public boolean isUpdateProduct(String oldprodutname,String newprodutname);
	List<productModel> getProductDetailByCategoryList1(String productcategory);
	public List<productModel> list1(String prodname);
	
	
}
