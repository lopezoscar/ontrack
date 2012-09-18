package com.ontrack.test;

import org.junit.Test;

import com.sappe.ontrack.model.issues.Issue;



public class IssueTest extends BaseTest{
	
	@Test
	public void test(){
		Issue issue = new Issue();
		
		em.getTransaction().begin();
		em.persist(issue);
		em.getTransaction().commit();
		
	}

}
