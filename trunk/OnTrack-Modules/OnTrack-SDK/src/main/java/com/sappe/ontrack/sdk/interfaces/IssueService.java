package com.sappe.ontrack.sdk.interfaces;

import java.util.List;

import com.sappe.ontrack.model.issues.Issue;
import com.sappe.ontrack.model.issues.IssueStatus;
import com.sappe.ontrack.model.issues.IssueType;
import com.sappe.ontrack.model.users.User;

public interface IssueService {
	
	void mergeIssue(Issue issue);
	
	List<Issue> getIssuesByProjectId(Long id);
	
	List<Issue> getIssuesByOwner(User owner);
	
	List<Issue> getIssuesByReporter(String reporter);
	
	List<Issue> getIssuesByStatus(IssueStatus issueStatus);
	
	List<Issue> getIssuesByType(IssueType issueType);
	
	List<Issue> getIssuesByCode(String code);
	
	void saveIssue(Issue issue);

}
