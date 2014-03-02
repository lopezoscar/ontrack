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
import com.sappe.ontrack.dao.springbeans.interfaces.IssueManager;
import com.sappe.ontrack.dao.springbeans.interfaces.UserManager;
import com.sappe.ontrack.model.issues.Issue;
import com.sappe.ontrack.model.issues.Project;
import com.sappe.ontrack.model.users.Member;
import com.sappe.ontrack.model.users.User;



@Component
@Path("usersrv")
public class UserService {
	
	@Qualifier("userbean")
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private IssueManager issueManager;
	
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
	@Path("/userbyemail/")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public User userByUserEmail(String email){
		User user = userManager.userByEmail(email);
		return user;
	}
	
	@Path("/contacts")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response contactsByUserName(User user){
		
		List<Member> contacts = new ArrayList<Member>();
		try {
			contacts = userManager.contactsByUserName(user.getToken());
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
	
	@Path("/createuser")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User createUser(User user){
		User userResponse = userManager.create(user);
		return userResponse;
	}
	
	@Path("/updateuser")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User updateUser(User user){
		if(user != null && user.getId() != null){
			Response response = projectService.getProjectsByUser(user);
			List<Project> projectsUser = (List<Project>)response.getEntity();
			user.setProjects(projectsUser);
			User userResponse = userManager.update(user);
			return userResponse;
		}
		return null;
	}
	
	
	@Path("/existusername/{username}")
	@GET
	public Response existUserName(@PathParam("username")String userName){
		boolean result = userManager.existUserName(userName);
		return Response.ok(String.valueOf(result)).build(); 
	}
	
	@Path("/removeMember/{email}/{projectId}")
	@GET
	public Response removeMember(@PathParam("email")String email,@PathParam("projectId")Long projectId){
		if(email != null && email.trim().length() > 0  && projectId != null) {
			Project project = projectService.projectById(projectId);
			User user = userManager.userByEmail(email);
			
			//Si es admin no se permite borrar el usuario del proyecto
			if(user.equals(project.getAdmin())){
				return Response.ok().entity("isAdmin").build();
			}
			
			if(user != null && user.getId()!= null){
				user.getProjects().remove(project);
				userManager.update(user);
				
				List<Issue> issues = issueManager.getIssuesByProjectIdAndOwnerId(projectId, user.getId());
				for (Issue issue : issues) {
					if(!issue.getOwner().equals(project.getAdmin())){
						issue.setOwner(issue.getProject().getAdmin());
						issueManager.update(issue);
					}
				}
				
				return Response.ok().entity(user).build();
			}
		}
		
		return Response.ok().status(Status.INTERNAL_SERVER_ERROR).build();
	}
	

}
