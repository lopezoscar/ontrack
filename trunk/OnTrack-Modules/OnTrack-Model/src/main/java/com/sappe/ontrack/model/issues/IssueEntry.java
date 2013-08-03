package com.sappe.ontrack.model.issues;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sappe.ontrack.model.users.User;

@Entity
@Table(name="issue_entry")
public class IssueEntry implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3690492788180803305L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_issue_entry")
	private Long id;
	
	@Column(name="value")
	private String value;
	
	@ManyToOne
	@JoinColumn(name="owner")
	private User owner;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_issue_entry")
	private List<EntryDocumentFile> files;
	
	@ManyToOne
	@JoinColumn(name="id_issue_property")
	private IssueProperty property;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<EntryDocumentFile> getFiles() {
		return files;
	}

	public void setFiles(List<EntryDocumentFile> files) {
		this.files = files;
	}

	public IssueProperty getProperty() {
		return property;
	}

	public void setProperty(IssueProperty property) {
		this.property = property;
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
		IssueEntry other = (IssueEntry) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	

}
