package com.sappe.ontrack.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.event.ValueChangeEvent;

import com.sappe.ontrack.model.issues.Issue;
import com.sappe.ontrack.model.issues.IssueEntry;
import com.sappe.ontrack.model.issues.IssueStatus;
import com.sappe.ontrack.model.issues.IssueType;
import com.sappe.ontrack.model.issues.Project;
import com.sappe.ontrack.model.users.User;
import com.sappe.ontrack.sdk.interfaces.IssueService;
import com.sappe.ontrack.sdk.interfaces.IssueStatusService;
import com.sappe.ontrack.sdk.interfaces.IssueTypeService;
import com.sappe.ontrack.sdk.interfaces.ProjectService;
import com.sappe.ontrack.web.converters.GenericListConverter;

@ManagedBean(name="projectctrl")
@ViewScoped
public class ProjectController implements Serializable{

	@ManagedProperty(value="#{projectsrv}")
	private ProjectService projectService;

	@ManagedProperty(value="#{issuesrv}")
	private IssueService issueService;

	@ManagedProperty(value="#{issuetypesrv}")
	private IssueTypeService issueTypeService;

	@ManagedProperty(value="#{issuestatussrv}")
	private IssueStatusService issueStatusService;


	List<User> users = new ArrayList<User>();
	/**
	 * 
	 */
	private static final long serialVersionUID = 9096287486562988851L;

	List<Project> projects = new ArrayList<Project>();

	private Issue currentIssue = new Issue();

	private Project currentProject = new Project();

	private IssueEntry currentEntry = new IssueEntry();

	private List<IssueType> typesByProject = new ArrayList<IssueType>();

	private List<IssueStatus> statusByType = new ArrayList<IssueStatus>();

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

	public String createIssue(){
		issueService.saveIssue(currentIssue);
		return "home";
	}

	public List<User> usersHC(){
		users = new ArrayList<User>();
		User user = new User();
		user.setId(1l);
		user.setUserName("murreli");
		user.setFirstName("Marcelo");
		user.setLastName("Urreli");
		users.add(user);
		return users;
	}

	public Converter getUsersConverter(){
		return new GenericListConverter(users, "id");
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

	public boolean renderedIssuePanelForNewIssue(){
		if(currentIssue !=null && currentIssue.getProject() != null){
			return true;
		}
		return false;
	}

	public List<IssueType> retrieveIssueTypesByProject(){
		if(currentProject != null && currentProject.getId()!=null){
			typesByProject =  issueTypeService.getIssueTypesByProject(currentProject.getId());
		}
		return typesByProject;

	}

	public void valueChanged(ValueChangeEvent event) {
		IssueType type =(IssueType)event.getNewValue();
		currentIssue.setIssueType(type);

	}

	public void updateOwner(ValueChangeEvent event){
		User u = (User)event.getNewValue();
		currentIssue.setOwner(u);
	}

	public Converter genericListConverter(){
		return new GenericListConverter(typesByProject, "id");
	}

	public Converter statusListConverter(){
		return new GenericListConverter(statusByType, "id");
	}

	public List<IssueStatus> retrieveStatusByProject(){
		if(currentProject != null && currentProject.getId()!=null && currentIssue != null && currentIssue.getIssueType()!=null){
			statusByType = issueStatusService.getIssueStatusByIssueType(currentIssue.getIssueType());
		}
		return statusByType;
	}

	public void updateCurrentProjectForNewIssue(Project project){
		currentProject = project;
		currentIssue.setProject(currentProject);
	}

	public void saveSelectedIssue(Issue issue){
		currentIssue = issue;
		//		return "issueview.xhtml";
	}

	public String navigateToIssue(){
		//		Object o = getRequestParameter("selectedIssue");
		//		Object os = getRequestParameter("selectedProject");
		//		
		//		projectService.getIssuesByProject(project);
		//		Issue value = (Issue)requestMap.get("currentIssue");    
		return "issueview.xhtml";
	}

	public static String getRequestParameter(String name) {
		return (String)FacesContext.getCurrentInstance()
				.getExternalContext()
				.getRequestParameterMap()
				.get(name);    
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

	public IssueTypeService getIssueTypeService() {
		return issueTypeService;
	}

	public void setIssueTypeService(IssueTypeService issueTypeService) {
		this.issueTypeService = issueTypeService;
	}

	public List<IssueType> getTypesByProject() {
		return typesByProject;
	}

	public void setTypesByProject(List<IssueType> typesByProject) {
		this.typesByProject = typesByProject;
	}

	public List<IssueStatus> getStatusByType() {
		return statusByType;
	}

	public void setStatusByType(List<IssueStatus> statusByType) {
		this.statusByType = statusByType;
	}

	public IssueStatusService getIssueStatusService() {
		return issueStatusService;
	}

	public void setIssueStatusService(IssueStatusService issueStatusService) {
		this.issueStatusService = issueStatusService;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}



}
