package com.sappe.ontrack.model.users;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="users")
@NamedQueries(
		{ 
			@NamedQuery(name="selectAllUser", query = "select u from User u"),
			@NamedQuery(name="selectUserById", query = "select u from User u where u.id = :id")
		}
			
		)


public class User extends Person implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4073644744794579371L;

	@Column(name="user_name")
	private String userName;
	
	@Column(name="password")
	private String password;
	


	@ManyToMany
	@JoinTable(name="user_role", 
		joinColumns={@JoinColumn(name="id_person")}, 
		inverseJoinColumns={@JoinColumn(name="id_role")})		
	/**
	 * relaci�n N a N entre user y roles 
	 * un user es una person y posee igual id, se expresa con la tabla relacional user_role
	 * que contiene id_person de la tabla person e id_role de la tabla role
	 */
	private List<Role> roles;
		
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
