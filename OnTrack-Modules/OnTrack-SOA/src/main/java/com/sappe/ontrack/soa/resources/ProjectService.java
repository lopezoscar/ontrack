package com.sappe.ontrack.soa.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sappe.ontrack.dao.exceptions.NotificatorException;
import com.sappe.ontrack.dao.springbeans.interfaces.IssueManager;
import com.sappe.ontrack.dao.springbeans.interfaces.NotificationManager;
import com.sappe.ontrack.dao.springbeans.interfaces.ProjectManager;
import com.sappe.ontrack.dao.springbeans.interfaces.UserManager;
import com.sappe.ontrack.model.issues.Issue;
import com.sappe.ontrack.model.issues.Project;
import com.sappe.ontrack.model.notifications.NotificationDTO;
import com.sappe.ontrack.model.users.User;

@Path("/projectsrv")
@Component
public class ProjectService {
	
	@Qualifier("projectbean")
	@Autowired
	ProjectManager projectManager;
	
	@Autowired
	IssueManager issueManager;
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private NotificationManager notificationManager;
	
	@Context
	private UriInfo uri;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/projectbyid/{id}")
	public Project projectById(@PathParam("id")Long id){
		return projectManager.read(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getallprojects")
	public List<Project> getAllProjects(){
		 List<Project> projects = projectManager.getAllProjects();
		 return projects;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getissuesbyprojectid/{id}")
	public List<Issue> getIssuesByProject(@PathParam("id")Long id){
		List<Issue> issues = issueManager.getIssuesByProjectId(id);
		return issues;
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/saveproject")
	public Response saveProject(Project project){
		Project savedProject = null;
		boolean projectModify = false;
		if(project.getId() == null){
			savedProject = projectManager.create(project);
			project.getAdmin().getProjects().add(savedProject);
			userManager.update(project.getAdmin());
//			projectManager.update(savedProject);
		}else{
			projectModify = true;
			savedProject = projectManager.update(project);
		}
		List<String> mailsToNotify = new ArrayList<String>();
		NotificationDTO dto = new NotificationDTO();
		dto.setFrom("noreply@ontrack.com.ar");
		dto.setSubject("OnTrack - Proyecto Actualizado Exitosamente");

		URI url = uri.getBaseUri();
		
		StringBuffer sb = new StringBuffer();
		sb.append("Para empezar a utilizar OnTrack en necesario que ingreses en la siguiente url: ");
		sb.append("http://");
		sb.append(url.getHost());
		sb.append(":");
		sb.append(url.getPort());
		sb.append("/OnTrack/profile.html");

		
		dto.setBody("Se guardó correctamente el proyecto: "+project.getName());
		
		if(savedProject != null){
			if(project.getUsers() != null && !project.getUsers().isEmpty()) {
				for (User user : project.getUsers()) {
					user.setMail(user.getMail().trim());
					if(user.getUserName()  == null){
						user.setUserName(user.getMail());
					}
					if(user.getId() != null && !savedProject.getUsers().contains(user)){
						user.getProjects().add(savedProject);
						userManager.update(user);
					}else{
						if(user.getMail() != null){
							User existedUser = userManager.userByEmail(user.getMail());
							if(existedUser != null){
								user = existedUser;
							}else{
								user = userManager.create(user);
							}
						}else{
							user = userManager.create(user);
						}
						if(user.getMail() != null){
							mailsToNotify.add(user.getMail());
							
						}
						if(!savedProject.getAdmin().equals(user)){
							user.getProjects().add(savedProject);
							userManager.update(user);
						}
					}
				}
				
				try {
					dto.setTo(mailsToNotify);
					notificationManager.sendEmails(dto);
				} catch (NotificatorException e) {
					e.printStackTrace();
				}
				
				return Response.ok().entity(savedProject).build();
			}
		}
		return Response.status(Status.INTERNAL_SERVER_ERROR).build();
	}
	
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/projectsbyuser")
	public Response getProjectsByUser(User user){
		List<Project> projects = projectManager.projectsByUser(user);
		if(projects != null && !projects.isEmpty()){
			return Response.ok(projects).build();
		}
		return Response.status(Status.INTERNAL_SERVER_ERROR).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/projectsbyadmin")
	public Response getProjectsByAdmin(User admin){
		List<Project> projects = projectManager.projectsByAdmin(admin);
		if(projects != null && !projects.isEmpty()){
			return Response.ok(projects).build();
		}
		return Response.status(Status.INTERNAL_SERVER_ERROR).build();
	}
	
	
	
	public ProjectManager getProjectManager() {
		return projectManager;
	}


	public void setProjectManager(ProjectManager projectManager) {
		this.projectManager = projectManager;
	}
	

}
