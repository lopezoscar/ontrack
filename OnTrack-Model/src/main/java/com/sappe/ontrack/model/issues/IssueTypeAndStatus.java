package com.sappe.ontrack.model.issues;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="issue_types_and_status")
public class IssueTypeAndStatus implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4801456373390320607L;
	
	@EmbeddedId
	private IssueTypeAndStatusPK pk;
	
	@ManyToOne
	@JoinColumn(name="id_workflow",insertable=false ,updatable=false)
	private Workflow workflow;
	
	@ManyToOne
	@JoinColumn(name="id_issue_type",insertable=false ,updatable=false)
	private IssueType issueType;
	
	@ManyToOne
	@JoinColumn(name="id_issue_status",insertable=false, updatable=false)
	private IssueStatus issueStatus;
	
	
	
	@JsonIgnore
	public Workflow getWorkflow() {
		return workflow;
	}

	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}

	public IssueType getIssueType() {
		return issueType;
	}

	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}

	public IssueStatus getIssueStatus() {
		return issueStatus;
	}

	public void setIssueStatus(IssueStatus issueStatus) {
		this.issueStatus = issueStatus;
	}

	public IssueTypeAndStatusPK getPk() {
		return pk;
	}

	public void setPk(IssueTypeAndStatusPK pk) {
		this.pk = pk;
	}
	
	
	

}
