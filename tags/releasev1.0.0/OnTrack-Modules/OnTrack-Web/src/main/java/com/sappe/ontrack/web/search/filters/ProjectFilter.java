package com.sappe.ontrack.web.search.filters;

import java.util.List;

import com.sappe.ontrack.model.issues.Issue;
import com.sappe.ontrack.sdk.interfaces.IssueService;
import com.sappe.ontrack.web.search.strategy.ISearchFilter;

public class ProjectFilter extends BaseFilter implements ISearchFilter{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3011099657820864902L;
	
	public static final String FILTER_NAME = "Búsqueda por Proyecto";
	
	private Long projectId; 

	@Override
	public List<Issue> search(IssueService is) {
		return is.getIssuesByProjectId(projectId);
	}

	@Override
	public String getFilterName() {
		return FILTER_NAME;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	
	public boolean isProjectFilter(){
		return true;
	}
	
	

}
