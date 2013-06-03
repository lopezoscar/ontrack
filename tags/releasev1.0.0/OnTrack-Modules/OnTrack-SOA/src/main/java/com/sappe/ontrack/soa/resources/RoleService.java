package com.sappe.ontrack.soa.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sappe.ontrack.dao.springbeans.interfaces.RoleManager;
import com.sappe.ontrack.model.users.Role;

@Component
@Path("rolesrv")

//rolesrv/getrolebyid/1
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
	
	@POST
	@Path("createrole")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createRole(Role roleJson) {
		roleManager.create(roleJson);
		return Response.status(200).entity("Success").build();
	} 
	
	@POST
	@Path("updaterole")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateRole(Role roleJson) {
		roleManager.update(roleJson);
	}
	
	@POST
	@Path("deleterole")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteRole(Role roleJson) {
		roleManager.delete(roleJson);
	}
	
	@GET
	@Path("getallroles")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Role> getAllRoles(){
		return roleManager.getAllRoles();
	}

}
