package com.sappe.ontrack.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sappe.ontrack.model.issues.Issue;
import com.sappe.ontrack.soa.resources.IssueService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml"})
public class SearchServicesTest {
	
	@Autowired
	private IssueService issueSrv;
	
	@Test
	public void test(){
		List<Issue> issues = issueSrv.getIssuesByOwnerId(1l);
		System.out.println(issues);
	}

}
