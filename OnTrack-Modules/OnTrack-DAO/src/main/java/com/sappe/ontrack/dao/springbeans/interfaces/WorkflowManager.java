package com.sappe.ontrack.dao.springbeans.interfaces;

import java.util.List;

import com.sappe.ontrack.model.issues.IssueStatusByWorkflow;
import com.sappe.ontrack.model.issues.Workflow;
import com.sappe.ontrack.model.users.User;

public interface WorkflowManager extends CRUD<Workflow>{
	
	List<Workflow> listWorkflowsByUser(User user);
	
	IssueStatusByWorkflow saveIssueStatusByWorkfow(IssueStatusByWorkflow wf);

}
