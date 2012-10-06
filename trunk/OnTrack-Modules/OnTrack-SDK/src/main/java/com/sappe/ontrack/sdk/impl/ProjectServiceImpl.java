package com.sappe.ontrack.sdk.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sappe.ontrack.model.issues.Project;
import com.sappe.ontrack.sdk.interfaces.ProjectService;

public class ProjectServiceImpl implements ProjectService,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6699689920003295758L;

	public List<Project> getAllProjects() {
		List<Project> projects = new ArrayList<Project>();
//		String response = HTTPManager.get("");
		return projects;
	}

}
