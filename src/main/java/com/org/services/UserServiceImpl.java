package com.org.services;

import java.util.List;

import com.org.Model.UserModel;
import com.org.repository.DBState;
import com.org.repository.UserRepository;
import com.org.repository.UserRepositoryImpl;

public class UserServiceImpl extends DBState implements UserService {

	UserRepository uRepository=new UserRepositoryImpl();
	@Override
	public boolean isAddUser(UserModel umodel) {
		return uRepository.isAddUser(umodel);
	}
	@Override

	public UserModel login(String useremail, String password) {
		return uRepository.login(useremail, password);
	}
	@Override
	public String role(String useremail) {
		return uRepository.role(useremail);
	}
	@Override
	public List<UserModel> viewList() {
		return uRepository.viewList();
	}
	@Override
	public boolean isDeleteCustomer(String custemail) {
		return uRepository.isDeleteCustomer(custemail);
	}
	@Override
	public boolean isUpdateCustomer(String oldUserEmail, String newUserEmail) {
		return uRepository.isUpdateCustomer(oldUserEmail, newUserEmail);
	}
	

}
