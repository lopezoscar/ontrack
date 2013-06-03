package com.sappe.ontrack.web.search.filters;

import java.util.List;

import com.sappe.ontrack.model.issues.Issue;
import com.sappe.ontrack.model.issues.IssueType;
import com.sappe.ontrack.sdk.interfaces.IssueService;
import com.sappe.ontrack.web.search.strategy.ISearchFilter;

public class TypeFilter extends BaseFilter implements ISearchFilter{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8603077539200551079L;

	public static final String FILTER_NAME = "Búsqueda por tipo";
	
	private IssueType type;
	
	private String project;

	@Override
	public List<Issue> search(IssueService is) {
		return is.getIssuesByType(type);
	}

	public IssueType getType() {
		return type;
	}

	public void setType(IssueType type) {
		this.type = type;
	}

	@Override
	public String getFilterName() {
		return FILTER_NAME;
	}
	
	public boolean isTypeFilter(){
		return true;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}
	

}
