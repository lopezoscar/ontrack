package com.sappe.ontrack.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;

import com.sappe.ontrack.model.issues.IssueProperty;
import com.sappe.ontrack.model.issues.IssuePropertyType;
import com.sappe.ontrack.model.issues.IssueStatus;
import com.sappe.ontrack.model.issues.IssueType;
import com.sappe.ontrack.model.issues.Project;
import com.sappe.ontrack.model.issues.Workflow;
import com.sappe.ontrack.model.users.Role;
import com.sappe.ontrack.model.users.User;
import com.sappe.ontrack.sdk.interfaces.IssuePropertyService;
import com.sappe.ontrack.sdk.interfaces.IssueStatusService;
import com.sappe.ontrack.sdk.interfaces.IssueTypeService;
import com.sappe.ontrack.sdk.interfaces.ProjectService;
import com.sappe.ontrack.sdk.interfaces.RoleService;
import com.sappe.ontrack.sdk.interfaces.UserService;
import com.sappe.ontrack.web.converters.GenericListConverter;

@ManagedBean(name="newprojectctrl")
@ViewScoped
public class NewProjectController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 481369567132756107L;
	
	@ManagedProperty(value="#{usersrv}")
	private UserService userService;
	
	@ManagedProperty(value="#{rolesrv}")
	private RoleService roleService;
	
	@ManagedProperty(value="#{issuepropertysrv}")
	private IssuePropertyService issuePropertyService;
	
	@ManagedProperty(value="#{issuestatussrv}")
	private IssueStatusService issueStatusService;
	
	@ManagedProperty(value="#{projectsrv}")
	private ProjectService projectSrv;
	
	@ManagedProperty(value="#{issuetypesrv}")
	IssueTypeService issueTypeService;
	
	
	private List<User> allUsers = new ArrayList<User>();
	private List<User> selectedUser = new ArrayList<User>();
	
	private IssueType currentIssueType = new IssueType();
	private List<IssueType> createdIssuesTypes = new ArrayList<IssueType>();
	
	private List<Role> allRoles = new ArrayList<Role>();
	private List<Role> selectedRoles = new ArrayList<Role>();
	
	private Project project = new Project();
	
	private User currentUser;
	
	private IssueProperty currentIssueProperty = new IssueProperty();
	
	
	private IssuePropertyType currentIssuePropertyType = new IssuePropertyType();
	
	private Map<IssueType,List<IssueProperty>> propertiesForIssueType = new HashMap<IssueType,List<IssueProperty>>();
	
	private List<IssueProperty> propertyTypes = new ArrayList<IssueProperty>();
	
	private List<IssueStatus> targetIssueStatus = new ArrayList<IssueStatus>();
	
	private List<IssueStatus> issueStatus = new ArrayList<IssueStatus>();
	
	private IssueStatus currentStatus;
	
	
	public void addStatusToTarget(IssueStatus is){
		targetIssueStatus.add(is);
	}
	
	public List<User> retrieveAllUsers(){
		allUsers = userService.getAllUsers();
		return allUsers;
	}
	
	public List<Role> retrieveAllRoles(){
		allRoles = roleService.getAllRoles();
		return allRoles;
	}
	
	public void updateCurrentIssueType(IssueType issueType){
		currentIssueType = issueType;
	}
	
	public void removeIssueType(IssueType it){
		if(it!=null && createdIssuesTypes.contains(it)){
			createdIssuesTypes.remove(it);
		}
	}
	
	public void saveIssueType(){
		if(!createdIssuesTypes.contains(currentIssueType) && currentIssueType != null){
			createdIssuesTypes.add(currentIssueType);	
		}
		currentIssueType = new IssueType();
	}
	
	public List<IssuePropertyType> getAllTypes(){
		List<IssuePropertyType> types = new ArrayList<IssuePropertyType>();
		types.addAll(issuePropertyService.getAllIssuePropertyTypes());
		return types;
	}
	
	public void upStatus(IssueStatus is){
		int currentIndex = issueStatus.indexOf(is);
		if(currentIndex < 1){
			return;
		}
		issueStatus.set(currentIndex-1, is);
	}
	
	/**
	 * Algoritmo
	 * 
	 * if(idx == 1) 
	 * 
	 * 
	 * @param is
	 */
	public void downStatus(IssueStatus is){
		int currentIndex = issueStatus.indexOf(is);
		if(currentIndex > issueStatus.size()){
			return;
		}
		issueStatus.set(currentIndex+1, is);
	}
	
	public void upStatus(){
		System.out.println("#################### UP STATUS ###########");
	}
	
	public void downStatus(){
		System.out.println("#################### UP STATUS ###########");
	}
	
	public Set<IssueType> retrieveIssueTypesByProject(){
		if(project.getId()!=null){
			issueTypeService.getIssueTypesByProject(project.getId());
		}
		return new HashSet<IssueType>();
	}
	
	public void saveProperty(){
//		propertiesForIssueType.put(currentIssueType, propertyTypes);
		currentIssueProperty.setType(currentIssuePropertyType);
		propertyTypes.add(currentIssueProperty);
		currentIssueProperty = new IssueProperty();
		currentIssuePropertyType = new IssuePropertyType();
	}
	
	public Workflow buildWorflow(IssueType issueType, List<IssueStatus>issueStatus, Project project){
		Workflow workflow = new Workflow();
		workflow.setIssueType(issueType);
		workflow.setIssueStatus(issueStatus);
		workflow.setProject(project);
		
		return workflow;
	}
	
	public List<IssueStatus> getAllIssueStatus(){
		issueStatus = issueStatusService.getAllIssueStatus();
		return issueStatus;
	}
	
	public Converter converter(){
		return new GenericListConverter(allUsers, "name");
	}
	
	public Converter propertyConverter(){
		return new GenericListConverter(getAllTypes(), "id");
	}
	
	public Converter issueStatusConverter(){
		return new GenericListConverter(getAllIssueStatus(), "id");
	}
	
	
	public void createProject(){
		projectSrv.saveProject(project);
	}


	public UserService getUserService() {
		return userService;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public List<User> getAllUsers() {
		return allUsers;
	}


	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}


	public List<User> getSelectedUser() {
		return selectedUser;
	}


	public void setSelectedUser(List<User> selectedUser) {
		this.selectedUser = selectedUser;
	}


	public Project getProject() {
		return project;
	}


	public void setProject(Project project) {
		this.project = project;
	}


	public IssueType getCurrentIssueType() {
		return currentIssueType;
	}


	public void setCurrentIssueType(IssueType currentIssueType) {
		this.currentIssueType = currentIssueType;
	}


	public List<IssueType> getCreatedIssuesTypes() {
		return createdIssuesTypes;
	}
	
	public List<SelectItem> selectCreatedIssueTypes(){
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (IssueType item : createdIssuesTypes) {
			items.add(new SelectItem(item,item.getDescription()));
		}
		return items;
	}


	public void setCreatedIssuesTypes(List<IssueType> createdIssuesTypes) {
		this.createdIssuesTypes = createdIssuesTypes;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public List<Role> getAllRoles() {
		return allRoles;
	}

	public void setAllRoles(List<Role> allRoles) {
		this.allRoles = allRoles;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public List<Role> getSelectedRoles() {
		return selectedRoles;
	}

	public void setSelectedRoles(List<Role> selectedRoles) {
		this.selectedRoles = selectedRoles;
	}

	public IssueProperty getCurrentIssueProperty() {
		return currentIssueProperty;
	}

	public void setCurrentIssueProperty(IssueProperty currentIssueProperty) {
		this.currentIssueProperty = currentIssueProperty;
	}

	public Map<IssueType, List<IssueProperty>> getPropertiesForIssueType() {
		return propertiesForIssueType;
	}

	public void setPropertiesForIssueType(
			Map<IssueType, List<IssueProperty>> propertiesForIssueType) {
		this.propertiesForIssueType = propertiesForIssueType;
	}

	public IssuePropertyService getIssuePropertyService() {
		return issuePropertyService;
	}

	public void setIssuePropertyService(IssuePropertyService issuePropertyService) {
		this.issuePropertyService = issuePropertyService;
	}

	public IssuePropertyType getCurrentIssuePropertyType() {
		return currentIssuePropertyType;
	}

	public void setCurrentIssuePropertyType(
			IssuePropertyType currentIssuePropertyType) {
		this.currentIssuePropertyType = currentIssuePropertyType;
	}

	public List<IssueProperty> getPropertyTypes() {
		return propertyTypes;
	}

	public void setPropertyTypes(List<IssueProperty> propertyTypes) {
		this.propertyTypes = propertyTypes;
	}

	public IssueStatusService getIssueStatusService() {
		return issueStatusService;
	}

	public void setIssueStatusService(IssueStatusService issueStatusService) {
		this.issueStatusService = issueStatusService;
	}

	public List<IssueStatus> getTargetIssueStatus() {
		return targetIssueStatus;
	}

	public void setTargetIssueStatus(List<IssueStatus> targetIssueStatus) {
		this.targetIssueStatus = targetIssueStatus;
	}

	public ProjectService getProjectSrv() {
		return projectSrv;
	}

	public void setProjectSrv(ProjectService projectSrv) {
		this.projectSrv = projectSrv;
	}

	public IssueTypeService getIssueTypeService() {
		return issueTypeService;
	}

	public void setIssueTypeService(IssueTypeService issueTypeService) {
		this.issueTypeService = issueTypeService;
	}

	public List<IssueStatus> getIssueStatus() {
		return issueStatus;
	}

	public void setIssueStatus(List<IssueStatus> issueStatus) {
		this.issueStatus = issueStatus;
	}

	public IssueStatus getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(IssueStatus currentStatus) {
		this.currentStatus = currentStatus;
	}
	
	

}
