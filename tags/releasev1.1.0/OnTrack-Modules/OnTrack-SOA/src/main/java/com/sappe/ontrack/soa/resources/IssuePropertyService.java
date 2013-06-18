package com.sappe.ontrack.soa.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sappe.ontrack.dao.springbeans.interfaces.IssuePropertyManager;
import com.sappe.ontrack.model.issues.IssuePropertyType;

@Component
@Path("/issuepropertysrv")
public class IssuePropertyService {
	
	@Qualifier("issuepropertybean")
	@Autowired
	private IssuePropertyManager issuePropertyManager;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/allissuepropertytypes")
	public List<IssuePropertyType> getAllIssuePropertyTypes(){
		return issuePropertyManager.getAllIssuePropertyTypes();
	}

	public IssuePropertyManager getIssuePropertyManager() {
		return issuePropertyManager;
	}

	public void setIssuePropertyManager(IssuePropertyManager issuePropertyManager) {
		this.issuePropertyManager = issuePropertyManager;
	}
	
	

}
