package com.sappe.ontrack.sdk.interfaces;

import java.util.List;

import com.sappe.ontrack.model.issues.Issue;
import com.sappe.ontrack.model.issues.Project;

public interface ProjectService {
	
	
	List<Project> getAllProjects();
	
	List<Issue> getIssuesByProject(Project project);
	
	void saveProject(Project project);
	

}
