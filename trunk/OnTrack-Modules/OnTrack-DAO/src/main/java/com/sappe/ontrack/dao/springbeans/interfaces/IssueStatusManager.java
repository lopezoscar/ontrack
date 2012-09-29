package com.sappe.ontrack.dao.springbeans.interfaces;

import java.util.List;

import com.sappe.ontrack.model.issues.IssueStatus;

public interface IssueStatusManager extends CRUD<IssueStatus>{

	public List<IssueStatus> getAllIssueStatus();
	
	
}
