package com.ontrack.test;

import org.junit.Test;

import com.sappe.ontrack.model.issues.IssueStatus;

public class IssueStatusTest extends BaseTest{
	
	@Test
	public void test(){
		IssueStatus status = new IssueStatus();
		status.setDescription("TODO");
		em.getTransaction().begin();
		em.persist(status);
		em.getTransaction().commit();
	}

}
