package com.org.repository;

import java.util.List;

import com.org.Model.*;

public interface cartRepository {

	 public boolean addProductToCart(UserModel cutlogin, String productname, int quantity);
	 public List<cartModel> viewCart(UserModel cUserModel);
}
