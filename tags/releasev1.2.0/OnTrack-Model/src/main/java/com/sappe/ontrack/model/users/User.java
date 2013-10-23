package com.sappe.ontrack.model.users;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.sappe.ontrack.model.issues.Project;

@Entity
@Table(name="users")
@NamedQueries(
		{ 
			@NamedQuery(name="selectAllUser", query = "select u from User u"),
			@NamedQuery(name="selectUserById", query = "select u from User u where u.id = :id"),
			@NamedQuery(name="userByUserName", query= "select u from User u where u.userName = :userName")
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

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="user_role", 
			joinColumns={@JoinColumn(name="id_person")}, 
			inverseJoinColumns={@JoinColumn(name="id_role")})		
			/**
			 * relacion entre user y roles 
			 * un user es una person y posee igual id, se expresa con la tabla relacional user_role
			 * que contiene id_person de la tabla person e id_role de la tabla role
			 */
			private List<Role> roles;

	//	@ManyToMany(fetch=FetchType.LAZY)
	//	@JoinTable(name="role_permission", 
	//		joinColumns={@JoinColumn(name="id_role")}, 
	//		inverseJoinColumns={@JoinColumn(name="id_permission")})
	//	private List<Permission> permissions;


	@ManyToMany
	@JoinTable(
			name = "users_by_project", 
			joinColumns = @JoinColumn(name = "id_user"), 
			inverseJoinColumns = @JoinColumn(name = "id_project")
	)
	private List<Project> projects;

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



	//	public List<Permission> getPermissions() {
	//		return permissions;
	//	}
	//
	//	public void setPermissions(List<Permission> permissions) {
	//		this.permissions = permissions;
	//	}

	@JsonIgnore
	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public String completeName(){
		return getLastName()+" "+getFirstName();
	}

	@Override
	public String toString() {
		return completeName();
	}




}
