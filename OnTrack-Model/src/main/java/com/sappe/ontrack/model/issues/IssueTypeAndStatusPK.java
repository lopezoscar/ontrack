package com.sappe.ontrack.model.issues;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IssueTypeAndStatusPK implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5691371889126535257L;

	@Column(name="id_issue_type")
	private Long issueType;
	
	@Column(name="id_issue_status")
	private Long issueStatus;
	
	@Column(name="id_workflow")
	private Long workflow;
	
	

	public Long getWorkflow() {
		return workflow;
	}

	public void setWorkflow(Long workflow) {
		this.workflow = workflow;
	}

	public Long getIssueType() {
		return issueType;
	}

	public void setIssueType(Long issueType) {
		this.issueType = issueType;
	}

	public Long getIssueStatus() {
		return issueStatus;
	}

	public void setIssueStatus(Long issueStatus) {
		this.issueStatus = issueStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((issueStatus == null) ? 0 : issueStatus.hashCode());
		result = prime * result
				+ ((issueType == null) ? 0 : issueType.hashCode());
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
		IssueTypeAndStatusPK other = (IssueTypeAndStatusPK) obj;
		if (issueStatus == null) {
			if (other.issueStatus != null)
				return false;
		} else if (!issueStatus.equals(other.issueStatus))
			return false;
		if (issueType == null) {
			if (other.issueType != null)
				return false;
		} else if (!issueType.equals(other.issueType))
			return false;
		return true;
	}

	
	
	
	

}
