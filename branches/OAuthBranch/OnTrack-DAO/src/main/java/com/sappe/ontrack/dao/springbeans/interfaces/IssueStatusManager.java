package com.sappe.ontrack.dao.springbeans.interfaces;

import java.util.List;

import com.sappe.ontrack.model.issues.IssueStatus;
import com.sappe.ontrack.model.issues.IssueStatusByWorkflow;
import com.sappe.ontrack.model.issues.IssueType;

public interface IssueStatusManager extends CRUD<IssueStatus>{

	public List<IssueStatus> getAllIssueStatus();
	
	List<IssueStatusByWorkflow> getIssueStatusByIssueType(IssueType issueType);
	
	List<IssueStatus> getIssueStatusByDesc(String desc);
	
	
}
