package com.sappe.ontrack.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.sappe.ontrack.model.issues.Project;
import com.sappe.ontrack.sdk.interfaces.ProjectService;

@ManagedBean(name="projectctrl")
@ViewScoped
public class ProjectController implements Serializable{
	
	@ManagedProperty(value="#{projectsrv}")
	private ProjectService projectService;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9096287486562988851L;
	
	List<Project> projects = new ArrayList<Project>();

	public void search(){
		System.out.println(projects.size());
	}
	
	public void retrieveProjectsByUser(){
		
	}
	
	public void retrieveAllProjects(){
		
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	
	
	
	

}
