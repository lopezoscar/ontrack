package com.sappe.ontrack.web.search.strategy;

import java.io.Serializable;
import java.util.List;

import com.sappe.ontrack.model.issues.Issue;
import com.sappe.ontrack.sdk.interfaces.IssueService;

public interface ISearchFilter extends Serializable{
	
	List<Issue> search(IssueService is);
	
	String getFilterName();

}
