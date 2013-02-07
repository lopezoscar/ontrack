package com.sappe.ontrack.sdk.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.type.TypeReference;

import com.sappe.ontrack.model.issues.IssueType;
import com.sappe.ontrack.model.issues.Project;
import com.sappe.ontrack.sdk.interfaces.IssueTypeService;

public class IssueTypeServiceImpl implements IssueTypeService,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3266628937232992762L;

	public List<IssueType> getIssueTypesByProject(Long project) {
		
		List<IssueType> types = new ArrayList<IssueType>();
		StringBuilder url = new StringBuilder();
		url.append("/issuetypesrv/getissuetypesbyprojectid/");
		url.append(project);
		String response = HTTPManager.get(url.toString());
		types.addAll(Mapper.fromJSON(new TypeReference<List<IssueType>>(){}, response));
		return types;
	}

}
