package com.org.services;

import java.util.List;

import com.org.Model.productModel;
import com.org.repository.ProductRepository;
import com.org.repository.ProductRepositoryImpl;

public class ProductServiceImpl implements ProductService{

	ProductRepository prepo=new ProductRepositoryImpl();

	@Override
	public boolean isAddProduct(String prodCategoryString,String productnameString,int u_price,int stock_quant) {
	 return prepo.isAddProduct(prodCategoryString,productnameString,u_price,stock_quant);
	}

	@Override
	public List<productModel> getProductDetailByCategoryList(String productcategory) {
		return prepo.getProductDetailByCategoryList(productcategory);
	}

	@Override
	public List<productModel> list(String prodname) {
		return prepo.list(prodname);
	}

	@Override
	public boolean isDeleteProduct(String prodname) {
		return prepo.isDeleteProduct(prodname);
	}

	@Override
	public boolean isUpdateProduct(String oldprodutname,String newprodutname) {
		return prepo.isUpdateProduct(oldprodutname,newprodutname);
	}

	@Override
	public List<productModel> getProductDetailByCategoryList1(String productcategory) {
		return prepo.getProductDetailByCategoryList1(productcategory);
	}

	@Override
	public List<productModel> list1(String prodname) {
		return prepo.list1(prodname);
	}

	

	
	

}
