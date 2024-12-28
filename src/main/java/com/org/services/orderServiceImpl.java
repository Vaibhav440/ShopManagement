package com.org.services;

import java.util.List;

import com.org.Model.BillModel;
import com.org.Model.UserModel;
import com.org.Model.orderModel;
import com.org.repository.orderRepository;
import com.org.repository.orderRepostioryImpl;

public class orderServiceImpl implements orderService {

	orderRepository oRepository=new orderRepostioryImpl();

	@Override
	public boolean placeOrder(UserModel umodel,String str) {
		return oRepository.placeOrder(umodel,str);
	}

	@Override
	public List viewCustomerSprcificOrders(UserModel umodel) {
		return oRepository.viewCustomerSprcificOrders(umodel);
	}

	@Override
	public List viewTotalOrders(UserModel umodel) {
		return oRepository.viewTotalOrders(umodel);
	}

	@Override
	public List<BillModel> getBill(UserModel umodel,orderModel order) {
		return oRepository.getBill(umodel,order);
	}

	@Override
	public List<Integer> DisplayOrderId(String Order_Date) {
		return oRepository.DisplayOrderId(Order_Date);
	}

	@Override
	public List viewCustomerSprcificOrdersnew(int user_id) {
		return oRepository.viewCustomerSprcificOrdersnew(user_id);
	}

	@Override
	public List<orderModel> PlacedOrder() {
		return oRepository.PlacedOrder();
	}

	@Override
	public List<orderModel> UnPlacedOrder() {
		return oRepository.UnPlacedOrder();
	}

	@Override
	public String BillStatus(int order_id) {
		return oRepository.BillStatus(order_id);
	}

	
}
