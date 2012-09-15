package com.sappe.ontrack.model.issues;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="issue_property")
public class IssueProperty {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_issue_property")
	private Long id;
	
	@Column(name="description")
	private String description;
	
	@JoinColumn(name="id_property")
	private IssuePropertyType jsType;
	
	
	
	

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
		IssueProperty other = (IssueProperty) obj;
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

	public IssuePropertyType getJsType() {
		return jsType;
	}

	public void setJsType(IssuePropertyType jsType) {
		this.jsType = jsType;
	}
	
	
	
	
	
	

}
