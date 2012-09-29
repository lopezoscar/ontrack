package com.sappe.ontrack.soa.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sappe.ontrack.dao.springbeans.interfaces.IssueStatusManager;
import com.sappe.ontrack.model.issues.IssueStatus;

@Component
@Path("issuestatussrv")
public class IssueStatusService {

	@Qualifier("issuestatusbean")
	@Autowired
	IssueStatusManager issueStatusManager;
	
	@GET
	@Path("getissuestatusbyid/{pk}")
	@Produces(MediaType.APPLICATION_JSON)
	public IssueStatus getIssueStatusById(@PathParam("pk") Long primaryKey){
		IssueStatus issueStatus = issueStatusManager.read(primaryKey);
		return issueStatus;		
	}
	
	@POST
	@Path("getallissuestatus")
	@Produces(MediaType.APPLICATION_JSON)
	public List<IssueStatus> getAllIssueStatus(){
		List<IssueStatus> issuesStatus = issueStatusManager.getAllIssueStatus();
		return issuesStatus;
	}
}
