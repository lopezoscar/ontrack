package com.sappe.ontrack.soa.resources;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sappe.ontrack.dao.springbeans.interfaces.IssueManager;
import com.sappe.ontrack.model.issues.Issue;
import com.sappe.ontrack.model.issues.IssueEntry;

@Component
@Path("issuesrv")
public class IssueService {
	
	@Qualifier("issuebean")
	@Autowired
	IssueManager issueManager;
	
	@GET
	@Path("getissuebyid/{pk}")
	@Produces(MediaType.APPLICATION_JSON)
	public Issue getIssueById(@PathParam("pk") Long primaryKey){
		Issue issue = issueManager.read(primaryKey);
		return issue;
	}
	
	@GET
	@Path("getentriesbyissueid/{issueid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Set<IssueEntry> getEntriesByIssueId(@PathParam("issueid")Long issueId){
		Issue issue = issueManager.read(issueId);
		Set<IssueEntry> entries =  issue.getEntries();
		return entries;
	}
	
	@POST
	@Path("mergeissue")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response mergeIssue(Issue issue){
		issueManager.update(issue);
		return Response.ok().build();
	}
	
	

		
}
