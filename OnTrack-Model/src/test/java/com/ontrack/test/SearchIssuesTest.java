package com.ontrack.test;

import java.util.List;

import org.junit.Test;

import com.sappe.ontrack.model.issues.Issue;

public class SearchIssuesTest extends BaseTest{
	
	@Test
	public void test(){
		List<Issue> issues = em.createNamedQuery("getIssueByReporter").setParameter("reporter", "Oscar").getResultList();
		toJson(issues);
		
	}

}
