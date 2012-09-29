package com.sappe.ontrack.dao.springbeans.interfaces;

import java.util.List;

import com.sappe.ontrack.model.users.User;

public interface UserManager extends CRUD<User>{
	
	
	public List<User> getAllUser();

}
