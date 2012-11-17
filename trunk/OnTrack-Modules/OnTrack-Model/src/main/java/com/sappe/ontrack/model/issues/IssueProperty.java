package com.sappe.ontrack.model.issues;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="issue_property")
public class IssueProperty implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6666486014096028205L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_issue_property")
	private Long id;
	
	@Column(name="description")
	private String description;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="type")
	private IssuePropertyType type;
	
	
	
	

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

	public IssuePropertyType getType() {
		return type;
	}

	public void setType(IssuePropertyType type) {
		this.type = type;
	}
	
	
	
	
	
	

}
