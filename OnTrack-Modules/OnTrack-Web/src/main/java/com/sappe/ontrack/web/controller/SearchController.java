package com.sappe.ontrack.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.sappe.ontrack.model.issues.Issue;
import com.sappe.ontrack.model.users.User;
import com.sappe.ontrack.sdk.interfaces.IssueService;
import com.sappe.ontrack.web.search.filters.CurrentStatusFilter;
import com.sappe.ontrack.web.search.filters.IssueCodeFilter;
import com.sappe.ontrack.web.search.filters.OwnerFilter;
import com.sappe.ontrack.web.search.filters.ProjectFilter;
import com.sappe.ontrack.web.search.filters.ReporterFilter;
import com.sappe.ontrack.web.search.filters.TypeFilter;
import com.sappe.ontrack.web.search.strategy.ISearchFilter;

@ManagedBean(name="searchctrl")
@ViewScoped
public class SearchController implements Serializable{


	private static final long serialVersionUID = 1858693929201627187L;
	
	@ManagedProperty(value="#{issuesrv}")
	private IssueService isrv;
	
	private String keyword;
	
	ISearchFilter selectedFilter;
	
	List<Issue> issues = new ArrayList<Issue>();
	
	List<ISearchFilter> filters = new ArrayList<ISearchFilter>();
	
	@PostConstruct
	public void initMB(){
		retrieveAllFilters();
	}
	
	public void saveSelectedFilter(ISearchFilter filter){
		selectedFilter = filter;
	}
	
	public void search(){
		issues.addAll(selectedFilter.search(isrv));
	}
	
	public List<Issue> getIssueByReporter(String reporter){
		return isrv.getIssuesByReporter(reporter);
	}
	
	public List<ISearchFilter> retrieveAllFilters(){
		filters.add(new TypeFilter());
		filters.add(new CurrentStatusFilter());
		filters.add(new ReporterFilter());
		filters.add(new OwnerFilter());
		filters.add(new ProjectFilter());
		filters.add(new IssueCodeFilter());
		return filters;
	}
	
	public List<Issue> getIssueByOwner(User owner){
		User user = new User();
		user.setId(1l);
		return isrv.getIssuesByOwner(user);
	}
	
	public List<ISearchFilter> getAllSearchFilters(){
		 List<ISearchFilter> filters = new ArrayList<ISearchFilter>();
		 return filters;
	}
	
	public void searchFilter(){
		issues.addAll(selectedFilter.search(isrv));
	}
	

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}



	public IssueService getIsrv() {
		return isrv;
	}



	public void setIsrv(IssueService isrv) {
		this.isrv = isrv;
	}

	public ISearchFilter getSelectedFilter() {
		return selectedFilter;
	}

	public void setSelectedFilter(ISearchFilter selectedFilter) {
		this.selectedFilter = selectedFilter;
	}

	public List<Issue> getIssues() {
		return issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}

	public List<ISearchFilter> getFilters() {
		return filters;
	}

	public void setFilters(List<ISearchFilter> filters) {
		this.filters = filters;
	}
	
	

	
	
	
}
