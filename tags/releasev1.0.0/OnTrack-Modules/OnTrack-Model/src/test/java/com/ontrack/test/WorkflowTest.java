package com.ontrack.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.sappe.ontrack.model.issues.IssueStatus;
import com.sappe.ontrack.model.issues.IssueType;
import com.sappe.ontrack.model.issues.Project;
import com.sappe.ontrack.model.issues.Workflow;

public class WorkflowTest extends BaseTest{
	
	
	@Test
	public void test(){
		Workflow workflow = new Workflow();
		IssueType issueType = em.find(IssueType.class, 1l);
		workflow.setIssueType(issueType);
		Project project = em.find(Project.class, 1l);
		workflow.setProject(project);
		List<IssueStatus> issueStatus = new ArrayList<IssueStatus>();
		IssueStatus is = em.find(IssueStatus.class, 1l);
		issueStatus.add(is);
		workflow.setIssueStatus(issueStatus);
		
		persiste(workflow);
	}

}
