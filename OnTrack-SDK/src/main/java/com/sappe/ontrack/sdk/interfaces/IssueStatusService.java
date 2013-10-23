package com.sappe.ontrack.sdk.interfaces;

import java.util.List;

import com.sappe.ontrack.model.issues.IssueStatus;
import com.sappe.ontrack.model.issues.IssueType;

public interface IssueStatusService {
	
	List<IssueStatus> getAllIssueStatus();
	
	List<IssueStatus> getIssueStatusByIssueType(IssueType issueType);
	
}
