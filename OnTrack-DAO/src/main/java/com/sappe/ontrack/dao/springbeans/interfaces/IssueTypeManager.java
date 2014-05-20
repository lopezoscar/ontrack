package com.sappe.ontrack.dao.springbeans.interfaces;

import java.util.List;

import com.sappe.ontrack.model.issues.IssueType;

public interface IssueTypeManager extends CRUD<IssueType>{
		
	public List<IssueType> getAllIssueType();
	
	List<IssueType> getIssueTypesByProjectId(long projectId);		
	
	List<IssueType> getIssueTypesByDesc(String desc);
	
}
