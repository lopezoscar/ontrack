package com.sappe.ontrack.model.users;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.sappe.ontrack.model.issues.Project;

@Entity
@Table(name="users")
@NamedQueries(
		{ 
			@NamedQuery(name="selectAllUser", query = "select u from User u"),
			@NamedQuery(name="selectUserById", query = "select u from User u where u.id = :id"),
			@NamedQuery(name="userByUserName", query= "select u from User u where u.userName = :userName"),
			@NamedQuery(name="selectUserByEmail", query = "select u from User u where u.mail = :email"),
			@NamedQuery(name="selectUsernameByUsername", query="select count(u) from User u where u.userName = :userName")
		}
)
public class User extends Person implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4073644744794579371L;
	
	@Transient
	private String token;

	@Column(name="user_name")
	private String userName;

	@Column(name="password")
	private String password;

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



	//	public List<Permission> getPermissions() {
	//		return permissions;
	//	}
	//
	//	public void setPermissions(List<Permission> permissions) {
	//		this.permissions = permissions;
	//	}

	@JsonIgnore
	public List<Project> getProjects() {
		if(projects == null || projects.isEmpty()){
			projects = new ArrayList<Project>();
		}
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	



}
