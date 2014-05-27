package com.ontrack.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.sappe.ontrack.model.issues.Issue;
import com.sappe.ontrack.model.issues.IssueEntry;
import com.sappe.ontrack.model.issues.IssueStatus;
import com.sappe.ontrack.model.issues.IssueType;
import com.sappe.ontrack.model.issues.Project;



public class IssueTest extends BaseTest{
	
	@Test
	public void test(){
		Issue issue = new Issue();
		IssueStatus currentStatus = em.find(IssueStatus.class, 1l);
		issue.setCurrentStatus(currentStatus);
		
		List<IssueEntry> entries = new ArrayList<IssueEntry>();
		IssueEntry entry  = em.find(IssueEntry.class, 1l);
		entries.add(entry);
//		issue.setEntries(entries);
		IssueType issueType = em.find(IssueType.class, 1l);
		issue.setIssueType(issueType);
		
		Project project = new Project();
		
		List<IssueType> issueTypes = new ArrayList<IssueType>();
		issueTypes.add(issueType);
//		project.setIssueTypes(issueTypes);
		
		project.setName("Proyecto");
		persiste(project);
		
		issue.setProject(project);
		
		persiste(issue);
		
		
		List<Issue> issues = new ArrayList<Issue>();
		issues.add(issue);
		
		
//		project.setIssues(issues);
		em.getTransaction().begin();
		em.merge(project);
		em.getTransaction().commit();
		
	}

}
