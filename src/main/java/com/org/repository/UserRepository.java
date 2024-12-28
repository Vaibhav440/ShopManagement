package com.org.repository;

import java.util.List;

import com.org.Model.UserModel;

public interface UserRepository {

	public boolean isAddUser(UserModel umodel);
	UserModel login(String useremail, String password);
	public String role(String useremail);
	List<UserModel>viewList();
	public boolean isDeleteCustomer(String custemail);
	public boolean isUpdateCustomer(String oldUserEmail,String newUserEmail);
	
}
