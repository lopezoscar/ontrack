package com.sappe.ontrack.model.issues;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="issue_status")
@NamedQueries(
		{ 
			@NamedQuery(name="selectAllIssueStatus", query = "select iss from IssueStatus iss"),
			@NamedQuery(name="getIssueStatusByDesc",query="select iss from IssueStatus iss where iss.description = :description")
		}
			
		)
	
public class IssueStatus implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5020016430273085302L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_issue_status")
	private Long id;
	
	@Column(name="description")
	private String description;
	
	@Transient
	private int position;

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
		IssueStatus other = (IssueStatus) obj;
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
	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return description;
	}
	
	
	

}
