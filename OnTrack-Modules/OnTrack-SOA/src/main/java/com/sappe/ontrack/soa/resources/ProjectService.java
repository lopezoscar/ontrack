package com.sappe.ontrack.soa.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sappe.ontrack.dao.springbeans.interfaces.IssueManager;
import com.sappe.ontrack.dao.springbeans.interfaces.ProjectManager;
import com.sappe.ontrack.model.issues.Issue;
import com.sappe.ontrack.model.issues.Project;

@Path("/projectsrv")
@Component
public class ProjectService {
	
	@Qualifier("projectbean")
	@Autowired
	ProjectManager projectManager;
	
	@Autowired
	IssueManager issueManager;
	
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
	


	public ProjectManager getProjectManager() {
		return projectManager;
	}


	public void setProjectManager(ProjectManager projectManager) {
		this.projectManager = projectManager;
	}
	
	

}
