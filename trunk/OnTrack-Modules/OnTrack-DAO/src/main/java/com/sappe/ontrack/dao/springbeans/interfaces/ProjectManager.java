package com.sappe.ontrack.dao.springbeans.interfaces;

import java.util.List;

import com.sappe.ontrack.model.issues.Project;

public interface ProjectManager extends CRUD<Project>{
	
	public List<Project> getAllProjects();

}
