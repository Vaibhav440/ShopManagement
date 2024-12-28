package com.org.services;

import java.util.List;

import com.org.Model.UserModel;
import com.org.Model.cartModel;
import com.org.repository.*;

public interface cartService {

	 public boolean addProductToCart(UserModel cutlogin, String productname, int quantity);
	 public List<cartModel> viewCart(UserModel cUserModel);
}
