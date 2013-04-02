package com.sappe.ontrack.model.issues;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.sappe.ontrack.model.users.Role;
import com.sappe.ontrack.model.users.User;

@Entity
@Table(name="project")
@NamedQueries(
		{
			@NamedQuery(name="getAllProjects",query="SELECT NEW  com.sappe.ontrack.model.issues.Project (p.id,p.name) from Project as p ")
		}
)
public class Project implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3497077388248243138L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_project")
	private Long id;

	@Column(name="name")
	private String name;

	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="id_issue",nullable=true)
	private Set<Issue> issues;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_project")
	private Set<IssueType> issueTypes;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_project")
	private List<Role> roles;
	
	@ManyToMany(mappedBy="projects")
	private List<User> users;
	
	public Project(){}

	public Project(Long id, String name, Set<Issue> issues,
			Set<IssueType> issueTypes) {
		super();
		this.id = id;
		this.name = name;
		this.issues = issues;
		this.issueTypes = issueTypes;
	}

	public Project(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public Set<Issue> getIssues() {
		return issues;
	}

	public void setIssues(Set<Issue> issues) {
		this.issues = issues;
	}

	@JsonIgnore
	public Set<IssueType> getIssueTypes() {
		return issueTypes;
	}

	public void setIssueTypes(Set<IssueType> issueTypes) {
		this.issueTypes = issueTypes;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
	
	

}
