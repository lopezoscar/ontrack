package com.sappe.ontrack.web.search.filters;

import java.util.List;

import com.sappe.ontrack.model.issues.Issue;
import com.sappe.ontrack.sdk.interfaces.IssueService;
import com.sappe.ontrack.web.search.strategy.ISearchFilter;

public class IssueCodeFilter extends BaseFilter implements ISearchFilter{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7996264730334037614L;
	
	public static final String FILTER_NAME = "Búsqueda por Issue Code";
	
	private String issueCode;

	@Override
	public List<Issue> search(IssueService is) {
		return is.getIssuesByCode(issueCode);
	}

	@Override
	public String getFilterName() {
		return FILTER_NAME;
	}

	public String getIssueCode() {
		return issueCode;
	}

	public void setIssueCode(String issueCode) {
		this.issueCode = issueCode;
	}
	
	public boolean isIssueCodeFilter(){
		return true;
	}
	
	

}
