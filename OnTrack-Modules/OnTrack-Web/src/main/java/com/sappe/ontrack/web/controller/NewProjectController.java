package com.sappe.ontrack.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.convert.Converter;

import com.sappe.ontrack.model.issues.IssueType;
import com.sappe.ontrack.model.users.Role;
import com.sappe.ontrack.model.users.User;
import com.sappe.ontrack.sdk.interfaces.RoleService;
import com.sappe.ontrack.sdk.interfaces.UserService;
import com.sappe.ontrack.web.converters.GenericListConverter;
import com.sappe.ontrack.web.view.model.NewProjectDTO;

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
	
	private List<User> allUsers = new ArrayList<User>();
	private List<User> selectedUser = new ArrayList<User>();
	
	private IssueType currentIssueType = new IssueType();
	private List<IssueType> createdIssuesTypes = new ArrayList<IssueType>();
	
	private List<Role> allRoles = new ArrayList<Role>();
	private List<Role> selectedRoles = new ArrayList<Role>();
	
	private NewProjectDTO project = new NewProjectDTO();
	
	private User currentUser;
	
	public List<User> retrieveAllUsers(){
		allUsers = userService.getAllUsers();
		return allUsers;
	}
	
	public List<Role> retrieveAllRoles(){
		allRoles = roleService.getAllRoles();
		return allRoles;
	}
	
	public void saveIssueType(){
		if(!createdIssuesTypes.contains(currentIssueType)){
			createdIssuesTypes.add(currentIssueType);			
		}
	}
	
	public Converter converter(){
		return new GenericListConverter(allUsers, "name");
	}
	
	
	public void createProject(){
		System.out.println(project);
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


	public NewProjectDTO getProject() {
		return project;
	}


	public void setProject(NewProjectDTO project) {
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
	
	

	
	

}
