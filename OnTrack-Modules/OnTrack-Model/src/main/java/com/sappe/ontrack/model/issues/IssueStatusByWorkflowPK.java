package com.sappe.ontrack.model.issues;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IssueStatusByWorkflowPK implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7512661907729797130L;

	@Column(name="id_workflow")
	private Long workflow;
	
	@Column(name="id_issue_status")
	private Long status;

	public Long getWorkflow() {
		return workflow;
	}

	public void setWorkflow(Long workflow) {
		this.workflow = workflow;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((workflow == null) ? 0 : workflow.hashCode());
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
		IssueStatusByWorkflowPK other = (IssueStatusByWorkflowPK) obj;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (workflow == null) {
			if (other.workflow != null)
				return false;
		} else if (!workflow.equals(other.workflow))
			return false;
		return true;
	}
	
	
	
	

}
