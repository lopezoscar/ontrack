package com.sappe.ontrack.model.issues;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Table(name="issue_status_by_workflow")
@Entity
public class IssueStatusByWorkflow implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6553149179270785267L;

	@EmbeddedId
	private IssueStatusByWorkflowPK pk;
	
	@ManyToOne
	@JoinColumn(name="id_issue_status",insertable=false ,updatable=false)
	private IssueStatus is;
	
	@ManyToOne
	@JoinColumn(name="id_workflow",insertable=false ,updatable=false)
	private Workflow wf;
	
	@Column(name="position")
	private int position;


	
	public IssueStatusByWorkflowPK getPk() {
		return pk;
	}

	public void setPk(IssueStatusByWorkflowPK pk) {
		this.pk = pk;
	}

	@JsonIgnore
	public IssueStatus getIs() {
		return is;
	}

	public void setIs(IssueStatus is) {
		this.is = is;
	}

	@JsonIgnore
	public Workflow getWf() {
		return wf;
	}

	public void setWf(Workflow wf) {
		this.wf = wf;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((is == null) ? 0 : is.hashCode());
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		result = prime * result + position;
		result = prime * result + ((wf == null) ? 0 : wf.hashCode());
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
		IssueStatusByWorkflow other = (IssueStatusByWorkflow) obj;
		if (is == null) {
			if (other.is != null)
				return false;
		} else if (!is.equals(other.is))
			return false;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		if (position != other.position)
			return false;
		if (wf == null) {
			if (other.wf != null)
				return false;
		} else if (!wf.equals(other.wf))
			return false;
		return true;
	}
	
	

	

}
