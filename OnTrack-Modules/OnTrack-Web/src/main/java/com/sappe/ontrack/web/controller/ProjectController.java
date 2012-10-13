package com.sappe.ontrack.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.sappe.ontrack.model.issues.Issue;
import com.sappe.ontrack.model.issues.IssueStatus;
import com.sappe.ontrack.model.issues.IssueType;
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
	
	private Issue currentIssue = new Issue();

	public void search(){
		System.out.println(projects.size());
	}
	
	public void retrieveProjectsByUser(){
		
	}
	
	public List<Project> retrieveAllProjects(){
		return createProject();
//		projectService.getAllProjects();
	}
	
	public List<Issue> retrieveIssuesByProject(){
		return createIssues();
	}
	
	public List<Project> createProject(){
		List<Project> projects = new ArrayList<Project>();
		for(long i = 0; i<100; i++){
			Project project = new Project();
			project.setId(i);
			project.setName("Titulo "+i);
			projects.add(project);
		}
		return projects;
	}
	
	public List<Issue> createIssues(){
		List<Issue> issues = new ArrayList<Issue>();
		for(long i  = 0; i<100; i++){
			Issue issue = new Issue();
			issue.setId(i);
			IssueStatus is = new IssueStatus();
			is.setDescription("Open");
			issue.setCurrentStatus(is);
			IssueType it = new IssueType();
			it.setDescription("Bug");
			issue.setIssueType(it);
			it.setDescription("Descripción");
			issue.setTitle("Error al guardar proyectos");
			
			issues.add(issue);
			
		}
		return issues;
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

	public Issue getCurrentIssue() {
		return currentIssue;
	}

	public void setCurrentIssue(Issue currentIssue) {
		this.currentIssue = currentIssue;
	}

	
	
	
	

}
