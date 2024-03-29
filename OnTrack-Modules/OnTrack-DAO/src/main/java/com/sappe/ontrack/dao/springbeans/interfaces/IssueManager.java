package com.sappe.ontrack.dao.springbeans.interfaces;

import java.util.List;

import com.sappe.ontrack.model.issues.Issue;
import com.sappe.ontrack.model.issues.IssueComment;
import com.sappe.ontrack.model.issues.IssueStatus;
import com.sappe.ontrack.model.issues.IssueType;
import com.sappe.ontrack.model.users.User;

public interface IssueManager extends CRUD<Issue>{
	
	List<Issue> getAllElements();
	
	List<Issue> getIssuesByProjectId(Long id);
	
	List<Issue> getIssuesByOwnerId(Long id);
	
	List<Issue> getIssuesByReporter(String reporter);
	
	List<Issue> getIssuesByStatus(IssueStatus status);
	
	List<Issue> getIssuesByType(IssueType type);
	
	List<Issue> getIssuesByCode(String code);
	
	List<Issue> getIssuesByUserFromProjects(User user);
	
	List<Issue> getIssuesByProjectIdAndOwnerId(Long projectId,Long ownerId);
	
	List<IssueComment> addCommentToIssue(IssueComment comment);
	
	void reassignOwner(User currentOwner, User newOwner);
}
