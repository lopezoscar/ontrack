package com.ontrack.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.sappe.ontrack.model.issues.IssueStatus;
import com.sappe.ontrack.model.issues.IssueType;
import com.sappe.ontrack.model.issues.Project;
import com.sappe.ontrack.model.issues.Workflow;

public class WorkflowTest extends BaseTest{
	
	
	@Test
	public void test(){
		Workflow workflow = new Workflow();
		IssueType issueType = new IssueType();
		issueType.setDescription("ISSUE");
		workflow.setIssueType(issueType);
		
		Project project = em.find(Project.class, 26l);
		workflow.setProject(project);
		
		List<IssueStatus> issueStatus = new ArrayList<IssueStatus>();
		IssueStatus is = new IssueStatus();
		is.setDescription("TODO");
		issueStatus.add(is);
		workflow.setIssueStatus(issueStatus);
//		{"id":null,"issueType":{"id":null,"description":"ISSUE"},"issueStatus":[{"id":null,"description":"TODO"}],"project":{"id":26,"name":"Project","roles":[],"users":[]}}

//		"{"project":{"id":26,"name":"Project","roles":null,"users":null},"issueType":{"description":"Issue"},"issueStatus":[{"description":"TODO"}]}";
		
//		toJson(workflow);
		persiste(workflow);
	}

}
