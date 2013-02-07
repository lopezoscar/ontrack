package com.sappe.ontrack.sdk.interfaces;

import java.util.List;

import com.sappe.ontrack.model.issues.IssueType;
import com.sappe.ontrack.model.issues.Project;

public interface IssueTypeService {
	
	List<IssueType> getIssueTypesByProject(Long project);

}
