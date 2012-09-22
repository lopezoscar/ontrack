package com.sappe.ontrack.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.sappe.ontrack.dto.LoginDTO;

@ViewScoped
@ManagedBean(name="loginctrl")
public class LoginController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9087240528691315568L;
	
	
	private LoginDTO login = new LoginDTO();
	
	public void login(){
		login.getUser();
		login.getPassword();
	}
	
	public String viewLoginUser(){
		StringBuilder sb = new StringBuilder();
		sb.append("Bienvenido \n");
		sb.append(login.getUser());
		return sb.toString();
	}

	public LoginDTO getLogin() {
		return login;
	}

	public void setLogin(LoginDTO login) {
		this.login = login;
	}
	
	

}
