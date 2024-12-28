package com.org.services;

import java.util.List;

import com.org.Model.productModel;

public interface ProductService {
	
	public boolean isAddProduct(String prodCategoryString,String productnameString,int u_price,int stock_quant);
	List<productModel> getProductDetailByCategoryList(String productcategory);
	List<productModel>list(String prodname);
	public boolean isDeleteProduct(String prodname);
	public boolean isUpdateProduct(String oldprodutname,String newprodutname);
	List<productModel> getProductDetailByCategoryList1(String productcategory);
	public List<productModel> list1(String prodname);
	
	

}
