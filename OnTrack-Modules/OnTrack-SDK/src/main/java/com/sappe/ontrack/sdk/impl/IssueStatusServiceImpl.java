package com.sappe.ontrack.sdk.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Component;

import com.sappe.ontrack.model.issues.IssueStatus;
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
		status.addAll(Mapper.fromJSON(new TypeReference<List<IssueStatus>>() {}, response)) ;
		return status;
	}

}
