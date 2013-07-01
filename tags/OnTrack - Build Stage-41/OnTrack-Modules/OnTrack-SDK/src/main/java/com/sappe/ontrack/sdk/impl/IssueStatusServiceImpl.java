package com.sappe.ontrack.sdk.impl;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Component;

import com.sappe.ontrack.model.issues.IssueStatus;
import com.sappe.ontrack.model.issues.IssueType;
import com.sappe.ontrack.sdk.interfaces.IssueStatusService;

@Component
public class IssueStatusServiceImpl implements IssueStatusService,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5237147147429570227L;

	public List<IssueStatus> getAllIssueStatus() {
		List<IssueStatus> status = new ArrayList<IssueStatus>();
		String response = HTTPManager.get("/issuestatussrv/getallissuestatus");
		if(response!=null){
			status.addAll(Mapper.fromJSON(new TypeReference<List<IssueStatus>>() {}, response)) ;
		}
		return status;
	}
	
	public List<IssueStatus> getIssueStatusByIssueType(IssueType issueType){
		List<IssueStatus> status = new ArrayList<IssueStatus>();
		StringBuilder url = new StringBuilder();
		url.append("/issuestatussrv/getissuestatusbyissuetype/");
		
		ObjectMapper mapper = new ObjectMapper();
		String content;
		try {
			content = mapper.writeValueAsString(issueType);
			String response = HTTPManager.post(url.toString(), content, false);
			if(response!=null){
				status.addAll(Mapper.fromJSON(new TypeReference<List<IssueStatus>>() {}, response)) ;
			}

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
		return status;
	}
	
	

}
