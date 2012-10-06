package com.sappe.ontrack.soa.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sappe.ontrack.dao.springbeans.interfaces.UserManager;
import com.sappe.ontrack.model.users.User;



@Component
@Path("usersrv")
public class UserService {
	
	@Qualifier("userbean")
	@Autowired
	private UserManager userManager;
	
	
	//getuserbyid/100
	@Path("getuserbyid/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
	public User getUserById(@PathParam("id") Long idUser){
//		System.out.println(idUser);
		User user = userManager.read(idUser);
		return user;
	}
	
	@Path("getallusers")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUsers(){
		List<User> users = userManager.getAllUser();
		return users;
	}
	
	

}
