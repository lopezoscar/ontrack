package com.sappe.ontrack.soa.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sappe.ontrack.dao.springbeans.interfaces.RoleManager;
import com.sappe.ontrack.model.users.Role;

@Component
@Path("rolesrv")
public class RoleService {
	
	@Qualifier("rolebean")
	@Autowired
	RoleManager roleManager;
	
	@GET
	@Path("getrolebyid/{pk}")
	@Produces(MediaType.APPLICATION_JSON)
	public Role getRoleById(@PathParam("pk") Integer primaryKey) {
		Role role = roleManager.read(primaryKey);
		return role; 
	}
}