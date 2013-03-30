package com.sappe.ontrack.web.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.sappe.ontrack.model.users.User;
import com.sappe.ontrack.sdk.interfaces.UserService;

@ManagedBean(name="loginctrl")
@ViewScoped
public class LoginController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1491887551804684531L;

	User login = new User();
	
	@ManagedProperty(value="#{usersrv}")
	private UserService userService;
	
	public void login(){
		userService.login();
		
	}
	
	public String logout(){
//		SecurityContextHolder.clearContext();
		return "logout.xhtml";
	}
	
	public String viewLoginUser(){
		return login.getUserName();
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getLogin() {
		return login;
	}

	public void setLogin(User login) {
		this.login = login;
	}

	
}
