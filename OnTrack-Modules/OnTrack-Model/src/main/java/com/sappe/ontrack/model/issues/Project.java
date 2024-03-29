package com.sappe.ontrack.model.issues;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
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
			@NamedQuery(name="getAllProjects",query="SELECT NEW  com.sappe.ontrack.model.issues.Project (p.id,p.name) from Project as p "),
			@NamedQuery(name="projectByUser",query="SELECT p from Project p where :user in elements (p.users)"),
			@NamedQuery(name="projectByAdmin",query="SELECT p from Project p where p.admin = :admin")
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
	private List<Issue> issues;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_project")
	private List<IssueType> issueTypes;
	
	
	@ManyToMany(mappedBy="projects")
	private List<User> users;
	
	@ManyToOne
	@JoinColumn(name="admin")
	private User admin;
	
	public Project(){}

	public Project(Long id, String name, List<Issue> issues,
			List<IssueType> issueTypes) {
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
	public List<Issue> getIssues() {
		return issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}

	@JsonIgnore
	public List<IssueType> getIssueTypes() {
		return issueTypes;
	}

	public void setIssueTypes(List<IssueType> issueTypes) {
		this.issueTypes = issueTypes;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}
	
	
	
	

}
