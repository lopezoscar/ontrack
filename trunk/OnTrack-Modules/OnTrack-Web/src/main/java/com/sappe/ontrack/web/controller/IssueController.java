package com.sappe.ontrack.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.sappe.ontrack.model.issues.Issue;
import com.sappe.ontrack.model.issues.Project;
import com.sappe.ontrack.model.users.User;
import com.sappe.ontrack.sdk.interfaces.IssueService;

@ManagedBean(name="issuectrl")
@ViewScoped
public class IssueController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2636617360364200664L;

	private Issue currentIssue = new Issue();
	
	private Project currentProject;
	
	@ManagedProperty(value="#{projectctrl}")
	private ProjectController projectCtrl;
	
	@ManagedProperty(value="#{issuesrv}")
	private IssueService issueService;
	
	public boolean renderedIssuePanel(){
		if(currentIssue.getId() !=null){
			return true;
		}
		return false;
	}
	
	public void saveSelectedIssue(Issue issue){
		currentIssue = issue;
	}
	
	public List<Issue> issuesByOwner(){
		User owner = new User();
		owner.setId(1l);
		return issueService.getIssuesByOwner(owner);
		
	}
	
	public void createIssue(){
		
	}
	
	public void updateCurrentProject(Project project){
		currentProject = project;
		currentIssue.setProject(currentProject);
		projectCtrl.setCurrentIssue(currentIssue);
	}
	
	public void getStatusByProject(){
		
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

	public ProjectController getProjectCtrl() {
		return projectCtrl;
	}

	public void setProjectCtrl(ProjectController projectCtrl) {
		this.projectCtrl = projectCtrl;
	}

	public IssueService getIssueService() {
		return issueService;
	}

	public void setIssueService(IssueService issueService) {
		this.issueService = issueService;
	}
	
	
	

}
