package com.ontrack.test;

import org.junit.Test;

import com.sappe.ontrack.model.Issue;

public class IssueTest extends BaseTest{
	
	@Test
	public void test(){
		Issue issue = new Issue();
		issue.setDescription("Soy un issue");
		
		em.getTransaction().begin();
		em.persist(issue);
		em.getTransaction().commit();
		
	}

}
