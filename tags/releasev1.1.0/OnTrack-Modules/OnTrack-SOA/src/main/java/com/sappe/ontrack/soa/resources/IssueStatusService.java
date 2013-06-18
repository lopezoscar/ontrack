package com.sappe.ontrack.soa.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sappe.ontrack.dao.springbeans.interfaces.IssueStatusManager;
import com.sappe.ontrack.model.issues.IssueStatus;
import com.sappe.ontrack.model.issues.IssueType;

@Component
@Path("issuestatussrv")
public class IssueStatusService {

	@Qualifier("issuestatusbean")
	@Autowired
	IssueStatusManager issueStatusManager;
	
	static final Logger logger = Logger.getLogger(IssueStatusService.class);
	
	
	@POST
	@Path("createissuestatus")
	@Produces(MediaType.APPLICATION_JSON)
	public IssueStatus createIssueStatus(IssueStatus issueStatus){
		IssueStatus is = issueStatusManager.create(issueStatus);	
		return is;
		
	}
	
	@GET
	@Path("getissuestatusbyid/{pk}")
	@Produces(MediaType.APPLICATION_JSON)
	public IssueStatus getIssueStatusById(@PathParam("pk") Long primaryKey){
		IssueStatus issueStatus = issueStatusManager.read(primaryKey);
		logger.info("Se obtuvo el id: "+primaryKey+" de Issue Status");
		return issueStatus;		
	}
	
	@GET
	@Path("getallissuestatus")
	@Produces(MediaType.APPLICATION_JSON)
	public List<IssueStatus> getAllIssueStatus(){
		List<IssueStatus> issuesStatus = issueStatusManager.getAllIssueStatus();
		return issuesStatus;
	}
	
	@POST
	@Path("updateissuestatus")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateIssueStatus(IssueStatus issueStatusJson) {
		issueStatusManager.update(issueStatusJson);
	}
	
	@POST
	@Path("deleteissuestatus")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteIssueStatus(IssueStatus issueStatusJson) {
		issueStatusManager.delete(issueStatusJson);
	}
	
	@POST
	@Path("getissuestatusbyissuetype")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<IssueStatus> getIssueStatusByIssueType(IssueType issueType) {
		return issueStatusManager.getIssueStatusByIssueType(issueType);
	}
	
	
	
}
