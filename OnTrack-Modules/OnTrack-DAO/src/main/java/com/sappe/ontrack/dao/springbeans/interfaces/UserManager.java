package com.sappe.ontrack.dao.springbeans.interfaces;

import java.io.IOException;
import java.util.List;

import com.google.gdata.util.ServiceException;
import com.sappe.ontrack.model.users.Member;
import com.sappe.ontrack.model.users.User;

public interface UserManager extends CRUD<User>{
	
	
	List<User> getAllUser();
	
	User userByUserName(String userName);
	
	public List<Member> contactsByUserName(String userName,String password) throws ServiceException, IOException;

}
