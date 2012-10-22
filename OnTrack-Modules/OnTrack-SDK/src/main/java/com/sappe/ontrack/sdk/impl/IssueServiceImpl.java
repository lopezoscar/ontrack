package com.sappe.ontrack.sdk.impl;

import java.io.IOException;
import java.io.Serializable;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.sappe.ontrack.model.issues.Issue;
import com.sappe.ontrack.sdk.interfaces.IssueService;

public class IssueServiceImpl implements IssueService,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3793743647346252618L;

	public void mergeIssue(Issue issue) {
		StringBuilder url = new StringBuilder();
		url.append("mergeissue");
		
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
