package com.sappe.ontrack.dao.springbeans.interfaces;

import java.util.List;

import com.sappe.ontrack.model.issues.Project;
import com.sappe.ontrack.model.users.User;

public interface ProjectManager extends CRUD<Project>{
	
	public List<Project> getAllProjects();
	
	public List<Project> projectsByUser(User user);
	
	List<Project> projectsByAdmin(User user);

}
