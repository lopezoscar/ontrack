package com.sappe.ontrack.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.sappe.ontrack.model.issues.Issue;
import com.sappe.ontrack.model.issues.IssueEntry;
import com.sappe.ontrack.model.issues.IssueStatus;
import com.sappe.ontrack.model.issues.IssueType;
import com.sappe.ontrack.model.issues.Project;
import com.sappe.ontrack.sdk.interfaces.IssueService;
import com.sappe.ontrack.sdk.interfaces.ProjectService;

@ManagedBean(name="projectctrl")
@ViewScoped
public class ProjectController implements Serializable{
	
	@ManagedProperty(value="#{projectsrv}")
	private ProjectService projectService;
	
	@ManagedProperty(value="#{issuesrv}")
	private IssueService issueService;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9096287486562988851L;
	
	List<Project> projects = new ArrayList<Project>();
	
	private Issue currentIssue = new Issue();
	
	private Project currentProject = new Project();
	
	private IssueEntry currentEntry = new IssueEntry();

	public void search(){
		System.out.println(projects.size());
	}
	
	public void retrieveProjectsByUser(){
		
	}
	
	public List<Project> retrieveAllProjects(){
//		return createProject();
		return projectService.getAllProjects();
	}
	
	public List<Issue> retrieveIssuesByProject(){
		return projectService.getIssuesByProject(currentProject);
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
	
	public List<IssueEntry> getEntriesComments(){
		List<IssueEntry> entries = new ArrayList<IssueEntry>();
		if(currentIssue != null && currentIssue.getEntries() !=null && !currentIssue.getEntries().isEmpty()){
			entries = new ArrayList<IssueEntry>(currentIssue.getEntries());
			return entries;
		}
		return entries;
	}
	
	public void addEntry(){
		if(currentIssue.getId() !=null && currentIssue.getEntries() == null){
			List<IssueEntry> entries = new ArrayList<IssueEntry>();
			entries.add(currentEntry);
			currentEntry = new IssueEntry();
			issueService.mergeIssue(currentIssue);
		}
	}
	
	public boolean renderedIssuePanel(){
		if(currentIssue.getId() !=null){
			return true;
		}
		return false;
	}
	
	public void updateCurrentProject(Project project){
		this.currentProject = project;
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

	public Project getCurrentProject() {
		return currentProject;
	}

	public void setCurrentProject(Project currentProject) {
		this.currentProject = currentProject;
	}

	public IssueEntry getCurrentEntry() {
		return currentEntry;
	}

	public void setCurrentEntry(IssueEntry currentEntry) {
		this.currentEntry = currentEntry;
	}

	public IssueService getIssueService() {
		return issueService;
	}

	public void setIssueService(IssueService issueService) {
		this.issueService = issueService;
	}

	
	
	
	

}
