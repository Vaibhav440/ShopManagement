package com.org.repository;

import java.util.List;

import com.org.Model.BillModel;
import com.org.Model.UserModel;
import com.org.Model.orderModel;

public interface orderRepository {

	 public boolean placeOrder(UserModel umodel,String str);
	 List viewCustomerSprcificOrders(UserModel umodel);
	 List viewTotalOrders(UserModel umodel);
	 public List<BillModel> getBill(UserModel umodel,orderModel order);
	 public List<Integer>DisplayOrderId(String Order_Date);
	 public List viewCustomerSprcificOrdersnew(int user_id);
	 List<orderModel>PlacedOrder();
	 List<orderModel>UnPlacedOrder();
	 public String BillStatus(int order_id);
	
}
