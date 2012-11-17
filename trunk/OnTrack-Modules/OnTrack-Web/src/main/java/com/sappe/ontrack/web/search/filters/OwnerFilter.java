package com.sappe.ontrack.web.search.filters;

import java.util.List;

import com.sappe.ontrack.model.issues.Issue;
import com.sappe.ontrack.model.users.User;
import com.sappe.ontrack.sdk.interfaces.IssueService;
import com.sappe.ontrack.web.search.strategy.ISearchFilter;

public class OwnerFilter extends BaseFilter implements ISearchFilter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4062739664620254082L;
	
	public static final String FILTER_NAME = "Búsqueda por Owner";
	
	private User owner;

	@Override
	public List<Issue> search(IssueService is) {
		return is.getIssuesByOwner(owner);
	}

	@Override
	public String getFilterName() {
		return FILTER_NAME;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public boolean isOwnerFilter(){
		return true;
	}

	
	

}
