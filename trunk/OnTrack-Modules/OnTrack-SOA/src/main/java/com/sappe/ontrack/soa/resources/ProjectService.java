package com.sappe.ontrack.soa.resources;

import java.util.List;

import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.collection.PersistentCollection;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sappe.ontrack.dao.springbeans.interfaces.ProjectManager;
import com.sappe.ontrack.model.issues.Project;

@Path("/projectsrv")
@Component
public class ProjectService {
	
	@Qualifier("projectbean")
	@Autowired
	ProjectManager projectManager;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getallprojects")
	public List<Project> getAllProjects(){
		 List<Project> projects = projectManager.getAllProjects();
		 return projects;
	}
	
	


	public ProjectManager getProjectManager() {
		return projectManager;
	}


	public void setProjectManager(ProjectManager projectManager) {
		this.projectManager = projectManager;
	}
	
	

}
