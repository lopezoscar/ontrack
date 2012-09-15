package com.sappe.ontrack.model.issues;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="document_file")
public class DocumentFile {
	
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


}
