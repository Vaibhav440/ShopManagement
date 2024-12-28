package com.org.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.org.Model.UserModel;

public class UserRepositoryImpl extends DBState implements UserRepository {

	private static final Logger logger = Logger.getLogger(UserRepositoryImpl.class);
	int value=0;
	@Override
	public boolean isAddUser(UserModel umodel) {
		try 
		{
			logger.info("Adding user: " + umodel.getUseremail()+ " with password: " + umodel.getPassword());
			stmt=conn.prepareStatement("insert into user values('0',?,?,?,?)");
			stmt.setString(1, umodel.getUseremail());
			stmt.setString(2, umodel.getPassword());
			stmt.setString(3, umodel.getRole());
			stmt.setString(4, umodel.getContact());
			value=stmt.executeUpdate();
			return value>0?true:null;
		} catch (Exception e) {
			logger.error("Error while adding user: " + umodel.getUseremail(), e);
			return false;
		}
	}
	@Override
	public UserModel login(String useremail, String password) {
		try 
		{
			logger.info("Login user: " + useremail+ " with password: " + password);
			stmt=conn.prepareStatement("select * from user where useremail=? and password=?");
			stmt.setString(1,useremail);
			stmt.setString(2, password);
			rs=stmt.executeQuery();
		    UserModel model=null;
			while(rs.next())
			{
				model=new UserModel(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
			}
			return model;
			
		} catch (Exception e) {
			logger.error("Error while logging user: " + useremail, e);
			return null;
		}
	}
	@Override
	public String role(String useremail) {
		try 
		{
			logger.info("Role of  user: " + useremail);
			stmt=conn.prepareStatement("select role from user where useremail=?");
			stmt.setString(1,useremail);
			rs=stmt.executeQuery();
		    String roleString=null;
			while(rs.next())
			{
				roleString=rs.getString(1);
			}
			return roleString;
			
		} catch (Exception e) {
			logger.error("Error while retriving role");
			return null;
		}
	}
	@Override
	public List<UserModel> viewList() {
		try 
		{
			logger.info("View  user:");
			List<UserModel> viewCustomersList=new ArrayList<UserModel>();
			stmt=conn.prepareStatement("select useremail,role,contact from user where role='customer'");
			rs=stmt.executeQuery();
			while(rs.next())
			{
				UserModel model=new UserModel(rs.getString(1),rs.getString(2),rs.getString(3));
				viewCustomersList.add(model);
			}
			return viewCustomersList;
			
		} catch (Exception e) {
			logger.error("Error while Rertiveing Viewlist");
			return null;
		}
	}
	@Override
	public boolean isDeleteCustomer(String custemail) {
		try 
		{
			logger.info("Delete  user:"+custemail);
			stmt=conn.prepareStatement("delete from user where useremail=?");
			stmt.setString(1, custemail);
			value=stmt.executeUpdate();
			return value>0?true:null;
			
		} catch (Exception e) {
			logger.error("Error while Deleting user"+custemail);
			return false;
		}
	}
	
	public boolean isUpdateCustomer(String oldUserEmail,String newUserEmail) {
		try 
		{
			logger.info("Update  user: " +" oldUserEmail-> to"+ oldUserEmail+" newUserEmail-->"+newUserEmail);
			stmt=conn.prepareStatement("select uid from user where useremail=?");
			stmt.setString(1, oldUserEmail);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				stmt=conn.prepareStatement("update user set useremail=? where uid=?");
				stmt.setString(1, newUserEmail);
				stmt.setInt(2,rs.getInt(1));
				value=stmt.executeUpdate();
			}
			return value>0?true:null;
			
			
		} catch (Exception e) {
			logger.error("Error while Updateing user"+" oldUserEmail-> to"+ oldUserEmail+" newUserEmail-->"+newUserEmail);
			return false;
		}
	}

}
