package com.sappe.ontrack.web.search.filters;

public abstract class BaseFilter {
	
	public boolean isCurrentStatusFilter(){
		return false;
	}
	
	public boolean isIssueCodeFilter(){
		return false;
	}
	
	public boolean isOwnerFilter(){
		return false;
	}
	
	public boolean isProjectFilter(){
		return false;
	}
	
	public boolean isReporterFilter(){
		return false;
	}
	
	public boolean isTypeFilter(){
		return false;
	}

}
