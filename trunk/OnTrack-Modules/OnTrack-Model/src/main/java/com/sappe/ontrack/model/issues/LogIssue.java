package com.sappe.ontrack.model.issues;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="log_issues")
public class LogIssue implements Serializable{
	
	public static final int CREATED_ISSUE_CODE = 1;
	public static final int MERGED_ISSUE_CODE = 2;
	public static final int FINISHED_ISSUE_CODE = 3;
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6892050620357259939L;

	@Id
	@Column(name="id_log")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="action")
	private IssueAction action;
	
	@Column(name="description")
	private String description;
	
	@Column(name="date")
	private Date date;
	
	@ManyToOne
	@JoinColumn(name="id_issue")
	private Issue issue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public IssueAction getAction() {
		return action;
	}

	public void setAction(IssueAction action) {
		this.action = action;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@JsonIgnore
	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
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
		LogIssue other = (LogIssue) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
