package com.sappe.ontrack.model.issues;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="workflow")
@NamedQueries(
		{ 
			@NamedQuery(name="selectWorkflowByIssueType", query = "select wf from Workflow wf where wf.issueType = :issueType")
		}
			
		)
public class Workflow {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_workflow")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_issue_type")
	private IssueType issueType;
	
	@ManyToMany
	@JoinTable(name="issue_status_by_workflow", 
				joinColumns={@JoinColumn(name="id_workflow")},
				inverseJoinColumns={@JoinColumn(name="id_issue_type")})
	private List<IssueStatus> issueStatus;
	
	@OneToOne
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
		Workflow other = (Workflow) obj;
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

	public IssueType getIssueType() {
		return issueType;
	}

	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}

	public List<IssueStatus> getIssueStatus() {
		return issueStatus;
	}

	public void setIssueStatus(List<IssueStatus> issueStatus) {
		this.issueStatus = issueStatus;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	

}
