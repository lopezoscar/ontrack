package com.sappe.ontrack.model.issues;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="entry_document_file")
public class EntryDocumentFile implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -50771334742642805L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id_document_file")
	private Long id;
	
	@Column(name="file_name")
	private String name;
	
	@Lob
	@Column(name="file_content")
	private byte[] content;
	
	@Column(name="file_length")
	private long length;

	
	@Column(name="file_type")
	private String fileType;
	
	@ManyToOne
	@JoinColumn(name="issue_entry")
	private IssueEntry issueEntry;

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

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public IssueEntry getIssueEntry() {
		return issueEntry;
	}

	public void setIssueEntry(IssueEntry issueEntry) {
		this.issueEntry = issueEntry;
	}
	
	


}
