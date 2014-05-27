package com.sappe.ontrack.sdk.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.type.TypeReference;

import com.sappe.ontrack.model.issues.IssuePropertyType;
import com.sappe.ontrack.sdk.interfaces.IssuePropertyService;

public class IssuePropertyServiceImpl implements IssuePropertyService,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5053232719488475175L;

	public List<IssuePropertyType> getAllIssuePropertyTypes() {
		List<IssuePropertyType> properties = new ArrayList<IssuePropertyType>();
		StringBuilder url = new StringBuilder();
		url.append("/issuepropertysrv/allissuepropertytypes");
		String response = HTTPManager.get(url.toString());
		properties.addAll(Mapper.fromJSON(new TypeReference<List<IssuePropertyType>>(){}, response));
		return properties;
	}

}
