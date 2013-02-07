package com.sappe.ontrack.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.convert.Converter;

import com.sappe.ontrack.model.issues.IssueProperty;
import com.sappe.ontrack.model.issues.IssuePropertyType;
import com.sappe.ontrack.model.issues.IssueStatus;
import com.sappe.ontrack.model.issues.IssueType;
import com.sappe.ontrack.model.issues.Project;
import com.sappe.ontrack.model.users.Role;
import com.sappe.ontrack.model.users.User;
import com.sappe.ontrack.sdk.interfaces.IssuePropertyService;
import com.sappe.ontrack.sdk.interfaces.IssueStatusService;
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
	
	public void saveIssueType(){
		if(!createdIssuesTypes.contains(currentIssueType)){
			createdIssuesTypes.add(currentIssueType);	
			currentIssueType = new IssueType();
		}
	}
	
	public List<IssuePropertyType> getAllTypes(){
		List<IssuePropertyType> types = new ArrayList<IssuePropertyType>();
		types.addAll(issuePropertyService.getAllIssuePropertyTypes());
		return types;
	}
	
	public void saveProperty(){
//		propertiesForIssueType.put(currentIssueType, propertyTypes);
		currentIssueProperty.setType(currentIssuePropertyType);
		propertyTypes.add(currentIssueProperty);
		currentIssueProperty = new IssueProperty();
		currentIssuePropertyType = new IssuePropertyType();
		
	}
	
	public List<IssueStatus> getAllIssueStatus(){
		return issueStatusService.getAllIssueStatus();
	}
	
	public Converter converter(){
		return new GenericListConverter(allUsers, "name");
	}
	
	public Converter propertyConverter(){
		return new GenericListConverter(getAllTypes(), "id");
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
	
	

	

}
