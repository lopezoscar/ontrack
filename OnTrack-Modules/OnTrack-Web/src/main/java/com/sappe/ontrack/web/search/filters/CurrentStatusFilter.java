package com.sappe.ontrack.web.search.filters;

import java.util.List;

import com.sappe.ontrack.model.issues.Issue;
import com.sappe.ontrack.model.issues.IssueStatus;
import com.sappe.ontrack.sdk.interfaces.IssueService;
import com.sappe.ontrack.web.search.strategy.ISearchFilter;

public class CurrentStatusFilter extends BaseFilter implements ISearchFilter{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2232555605404981399L;

	public static final String FILTER_NAME = "Búsqueda por estado actual";
	
	private IssueStatus is;

	@Override
	public List<Issue> search(IssueService is) {
		return is.getIssuesByStatus(this.is);
	}

	@Override
	public String getFilterName() {
		return FILTER_NAME;
	}

	public IssueStatus getIs() {
		return is;
	}

	public void setIs(IssueStatus is) {
		this.is = is;
	}
	
	public boolean isCurrentStatusFilter(){
		return true;
	}
	

}
