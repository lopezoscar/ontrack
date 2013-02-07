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
import com.sappe.ontrack.model.issues.IssueStatus;
import com.sappe.ontrack.model.issues.IssueType;
import com.sappe.ontrack.model.users.User;
import com.sappe.ontrack.sdk.interfaces.IssueService;

public class IssueServiceImpl implements IssueService,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3793743647346252618L;

	public void mergeIssue(Issue issue) {
		StringBuilder url = new StringBuilder();
		url.append("/issuesrv/mergeissue");

		ObjectMapper mapper = new ObjectMapper();
		String content;
		try {
			content = mapper.writeValueAsString(issue);
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

	public List<Issue> getIssuesByOwner(User owner) {
		List<Issue> issues = new ArrayList<Issue>();
		if(owner == null || owner.getId() == null){
			return issues;
		}
		StringBuilder url = new StringBuilder();
		url.append("/issuesrv/getissuesbyownerid/");
		url.append(owner.getId());
		String response = HTTPManager.get(url.toString());
		issues.addAll(Mapper.fromJSON(new TypeReference<List<Issue>>(){}, response));
		return issues;

	}
	
	public List<Issue> getIssuesByProjectId(Long id) {
		return null;
	}

	public List<Issue> getIssuesByReporter(String reporter) {
		List<Issue> issues = new ArrayList<Issue>();
		if(reporter == null){
			return issues;
		}
		StringBuilder url = new StringBuilder();
		url.append("issuesrv/getissusbyreporter/");
		url.append(reporter);
		String response = HTTPManager.get(url.toString());
		issues.addAll(Mapper.fromJSON(new TypeReference<List<Issue>>(){}, response));
		return issues;
	}

	public List<Issue> getIssuesByStatus(IssueStatus issueStatus) {
		List<Issue> issues = new ArrayList<Issue>();
		if(issueStatus == null){
			return issues;
		}
		StringBuilder url = new StringBuilder();
		url.append("issuesrv/getissusbystatus");

		ObjectMapper mapper = new ObjectMapper();
		String content;
		try {
			content = mapper.writeValueAsString(issueStatus);
			String response = HTTPManager.post(url.toString(), content, false);
			issues.addAll(Mapper.fromJSON(new TypeReference<List<Issue>>(){}, response));
			return issues;

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
		return null;
	}

	public List<Issue> getIssuesByType(IssueType issueType) {
		List<Issue> issues = new ArrayList<Issue>();
		if(issueType == null){
			return issues;
		}
		StringBuilder url = new StringBuilder();
		url.append("issuesrv/getissusbytype");

		ObjectMapper mapper = new ObjectMapper();
		String content;
		try {
			content = mapper.writeValueAsString(issueType);
			String response = HTTPManager.post(url.toString(), content, false);
			issues.addAll(Mapper.fromJSON(new TypeReference<List<Issue>>(){}, response));
			return issues;

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
		return null;
	}

	public List<Issue> getIssuesByCode(String code) {
		List<Issue> issues = new ArrayList<Issue>();
		if(code == null){
			return issues;
		}
		StringBuilder url = new StringBuilder();
		url.append("issuesrv/getissuesbycode/");
		url.append(code);
		String response = HTTPManager.get(url.toString());
		issues.addAll(Mapper.fromJSON(new TypeReference<List<Issue>>(){}, response));
		return issues;
		
	}

	public void saveIssue(Issue issue) {
		StringBuilder url = new StringBuilder();
		url.append("/issuesrv/saveissue");

		ObjectMapper mapper = new ObjectMapper();
		String content;
		try {
			content = mapper.writeValueAsString(issue);
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
