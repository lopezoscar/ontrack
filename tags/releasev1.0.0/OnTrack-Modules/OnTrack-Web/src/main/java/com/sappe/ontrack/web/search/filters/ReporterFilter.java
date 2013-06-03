package com.sappe.ontrack.web.search.filters;

import java.util.List;

import com.sappe.ontrack.model.issues.Issue;
import com.sappe.ontrack.sdk.interfaces.IssueService;
import com.sappe.ontrack.web.search.strategy.ISearchFilter;

public class ReporterFilter extends BaseFilter implements ISearchFilter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3945750832712143271L;
	
	public static final String FILTER_NAME = "Búsqueda por Reporter";
	
	private String reporter;

	@Override
	public List<Issue> search(IssueService is) {
		return is.getIssuesByReporter(reporter);
	}

	@Override
	public String getFilterName() {
		return FILTER_NAME;
	}

	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	
	public boolean isReporterFilter(){
		return true;
	}
	
	
	
	

}
