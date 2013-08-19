package com.ontrack.test;

import java.util.List;

import javax.persistence.Query;

import org.junit.Test;

import com.sappe.ontrack.model.issues.IssueStatusByWorkflow;
import com.sappe.ontrack.model.issues.IssueType;

public class IssueStatusByTypeTest extends BaseTest{
	
	
	@Test
	public void test(){
		Query q = em.createQuery("select isw from IssueStatusByWorkflow isw where isw.wf.issueType = :it");
		IssueType type = em.find(IssueType.class, 2l);
		q.setParameter("it", type);
		List<IssueStatusByWorkflow> status = q.getResultList();
		toJson(status);
		System.out.println(status.size());
		
	}

}
