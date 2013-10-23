package com.sappe.ontrack.dao.springbeans.interfaces;

import com.sappe.ontrack.model.issues.Issue;
import com.sappe.ontrack.model.issues.IssueAction;
import com.sappe.ontrack.model.issues.LogIssue;

public interface LogIssueManager extends CRUD<LogIssue>{
	
	void addLogToIssue(Issue issue,IssueAction action);

}
