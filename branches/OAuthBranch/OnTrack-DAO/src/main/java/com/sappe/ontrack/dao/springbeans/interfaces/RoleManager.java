package com.sappe.ontrack.dao.springbeans.interfaces;

import java.util.List;

import com.sappe.ontrack.model.users.Role;

public interface RoleManager extends CRUD<Role>{
	
	List<Role> getAllRoles();

}