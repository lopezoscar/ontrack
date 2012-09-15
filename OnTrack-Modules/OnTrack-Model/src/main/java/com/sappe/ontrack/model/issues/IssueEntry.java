package com.sappe.ontrack.model.issues;

import java.util.List;

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
public class IssueEntry {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_issue_entry")
	private Long id;
	
	@Column(name="comment")
	private String comment;
	
	@ManyToOne
	@JoinColumn(name="owner")
	private User owner;
	
	@OneToMany
	@JoinColumn(name="issue_entry")
	private List<DocumentFile> files;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<DocumentFile> getFiles() {
		return files;
	}

	public void setFiles(List<DocumentFile> files) {
		this.files = files;
	}
	
	

}
