package com.sappe.ontrack.dao.springbeans.interfaces;

import java.util.List;

import com.sappe.ontrack.model.issues.Issue;

public interface IssueManager extends CRUD<Issue>{
	
	List<Issue> getAllElements();
}
