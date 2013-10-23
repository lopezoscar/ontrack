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

@Entity
@Table(name="issue_property_type")
@NamedQueries(
		{
			@NamedQuery(name="allIssuePropertyTypes",query="select ipt from IssuePropertyType ipt")
			
		}
)
public class IssuePropertyType implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7952543669760278819L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_property")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	
	
	

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
		IssuePropertyType other = (IssuePropertyType) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

	@Override
	public String toString() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
