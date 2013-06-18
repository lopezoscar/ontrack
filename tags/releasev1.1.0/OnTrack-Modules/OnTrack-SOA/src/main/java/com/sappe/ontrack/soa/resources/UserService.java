package com.sappe.ontrack.soa.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.gdata.util.ServiceException;
import com.sappe.ontrack.dao.springbeans.interfaces.UserManager;
import com.sappe.ontrack.model.users.Member;
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
	
	@Path("/userbyusername/")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public User userByUserName(String userName){
		User user = userManager.userByUserName(userName);
		return user;
	}
	
	@Path("/contacts")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response contactsByUserName(User user){
		
		List<Member> contacts = new ArrayList<Member>();
		try {
			contacts = userManager.contactsByUserName(user.getMail(),user.getPassword());
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(Status.OK).entity(contacts).header("Access-Control-Allow-Origin", "*").build();
		
//		return contacts;
	}
	
	

}
