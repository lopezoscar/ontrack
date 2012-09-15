package com.sappe.ontrack.model.issues;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="issue")
public class Issue {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_issue")
	private Long id;
	
	@Column(name="description")
	private String description;
	
	@JoinColumn(name="issue_status")
	private IssueStatus currentState;
	
	@JoinColumn(name="issue_type")
	private IssueType issueType;
	
	@JoinColumn(name="id_issue")
	private Issue parent;
	
	@OneToMany
	@JoinColumn(name="id_issue")
	private List<Issue> childs;
	
	@JoinColumn(name="id_project")
	private Project project;
	
	
	
	

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
		Issue other = (Issue) obj;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public IssueStatus getCurrentState() {
		return currentState;
	}

	public void setCurrentState(IssueStatus currentState) {
		this.currentState = currentState;
	}

	public IssueType getIssueType() {
		return issueType;
	}

	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}

	public Issue getParent() {
		return parent;
	}

	public void setParent(Issue parent) {
		this.parent = parent;
	}

	public List<Issue> getChilds() {
		return childs;
	}

	public void setChilds(List<Issue> childs) {
		this.childs = childs;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	
}
