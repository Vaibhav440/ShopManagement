package com.org.services;

import java.util.List;

import com.org.Model.UserModel;
import com.org.Model.cartModel;
import com.org.repository.*;

public class cartServiceImpl implements cartService{
	
	cartRepository crepo=new cartRepositoryImpl();

	@Override
	public boolean addProductToCart(UserModel cutlogin, String productname, int quantity) {
	  return crepo.addProductToCart(cutlogin, productname, quantity);
	}

	@Override
	public List<cartModel> viewCart(UserModel cUserModel) {
		return crepo.viewCart(cUserModel);
	}

}
