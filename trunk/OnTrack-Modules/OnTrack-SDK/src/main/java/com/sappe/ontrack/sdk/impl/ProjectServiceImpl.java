package com.sappe.ontrack.sdk.impl;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.sappe.ontrack.model.issues.Issue;
import com.sappe.ontrack.model.issues.Project;
import com.sappe.ontrack.sdk.interfaces.ProjectService;

public class ProjectServiceImpl implements ProjectService,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6699689920003295758L;

	public List<Project> getAllProjects() {
		List<Project> projects = new ArrayList<Project>();
		String response = HTTPManager.get("/projectsrv/getallprojects");
		projects.addAll(Mapper.fromJSON(new TypeReference<List<Project>>() {}, response)) ;
		return projects;
	}

	public List<Issue> getIssuesByProject(Project project) {
		List<Issue> issues = new ArrayList<Issue>();
		if(project == null || project.getId() == null){
			return issues;
		}
		StringBuilder url = new StringBuilder();
		url.append("/projectsrv/getissuesbyprojectid/");
		url.append(project.getId());
		String response = HTTPManager.get(url.toString());
		issues.addAll(Mapper.fromJSON(new TypeReference<List<Issue>>(){}, response));
		return issues;
		
	}

	public void saveProject(Project project) {
		StringBuilder url = new StringBuilder();
		url.append("/projectsrv/saveproject");

		ObjectMapper mapper = new ObjectMapper();
		String content;
		try {
			content = mapper.writeValueAsString(project);
			HTTPManager.post(url.toString(), content, false);


		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
