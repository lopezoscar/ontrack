package com.sappe.ontrack.model.issues;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="entry_document_file")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class EntryDocumentFile extends DocumentFile implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -50771334742642805L;

	
	@ManyToOne
	@JoinColumn(name="id_issue_entry")
	private IssueEntry issueEntry;
	
	

	public IssueEntry getIssueEntry() {
		return issueEntry;
	}

	public void setIssueEntry(IssueEntry issueEntry) {
		this.issueEntry = issueEntry;
	}
	
	


}
