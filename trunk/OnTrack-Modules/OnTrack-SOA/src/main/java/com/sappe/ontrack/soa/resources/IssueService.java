package com.sappe.ontrack.soa.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sappe.ontrack.dao.springbeans.interfaces.IssueActionManager;
import com.sappe.ontrack.dao.springbeans.interfaces.IssueManager;
import com.sappe.ontrack.dao.springbeans.interfaces.LogIssueManager;
import com.sappe.ontrack.dao.springbeans.interfaces.ProcessHistoryManager;
import com.sappe.ontrack.model.issues.Issue;
import com.sappe.ontrack.model.issues.IssueAction;
import com.sappe.ontrack.model.issues.IssueComment;
import com.sappe.ontrack.model.issues.IssueEntry;
import com.sappe.ontrack.model.issues.IssueStatus;
import com.sappe.ontrack.model.issues.IssueType;
import com.sappe.ontrack.model.issues.LogIssue;

@Component
@Path("issuesrv")
public class IssueService {
	
	@Qualifier("issuebean")
	@Autowired
	private IssueManager issueManager;
	
	@Autowired
	private IssueActionManager issueActionManager;
	
	@Autowired
	private LogIssueManager logIssueManager;
	
	@Autowired
	private ProcessHistoryManager processHistoryManager;
	
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
	public List<IssueEntry> getEntriesByIssueId(@PathParam("issueid")Long issueId){
		Issue issue = issueManager.read(issueId);
		List<IssueEntry> entries =  issue.getEntries();
		return entries;
	}
	
	@POST
	@Path("saveissue")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveIssue(String issueJson){
		Issue issue = fromJSON( new TypeReference<Issue>() {},issueJson);
		IssueAction action = null;
		if(issue.getId()!=null){
			Issue toUpdate = issueManager.read(issue.getId());
			issueManager.update(toUpdate);
			action = issueActionManager.read(LogIssue.MERGED_ISSUE_CODE);
		}else{
			Issue result = issueManager.create(issue);
			if(result.getId() != null){
				action = issueActionManager.read(LogIssue.CREATED_ISSUE_CODE);
				
			}
		}
		processHistoryManager.addEntryToHistory(issue);
		logIssueManager.addLogToIssue(issue, action);
		return Response.ok().build();
//		return Response.status(Status.NOT_FOUND).build();
	}
	
	
	
	@POST
	@Path("mergeissue")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response mergeIssue(Issue issue){
		issueManager.update(issue);
		return Response.ok().build();
	}
	
	@GET
	@Path("getissuesbyownerid/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Issue> getIssuesByOwnerId(@PathParam("id")Long ownerId){
		List<Issue> issues = issueManager.getIssuesByOwnerId(ownerId);
		return issues;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getissusbyreporter/{reporter}")
	public List<Issue> getIssuesByReporter(@PathParam("reporter")String reporter){
		return issueManager.getIssuesByReporter(reporter);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("getissusbystatus")
	public List<Issue> getIssuesByStatus(IssueStatus status){
		return issueManager.getIssuesByStatus(status);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("getissusbytype")
	public List<Issue> getIssuesByType(IssueType type){
		return issueManager.getIssuesByType(type);
	}
	
	@GET
	@Path("getissuesbycode/{code}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Issue> getIssuesByCodee(@PathParam("code")String code){
		return issueManager.getIssuesByCode(code);
	}
	
	@POST
	@Path("addcommenttoissue")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<IssueComment> addCommentToIssue(IssueComment comment){
		return issueManager.addCommentToIssue(comment);
	}

	public static <T> T fromJSON(final TypeReference<T> type,final String jsonPacket) {
		   T data = null;
	
		   try {
		      data = new ObjectMapper().readValue(jsonPacket, type);
		   } catch (Exception e) {
		      e.printStackTrace();
		   }
		   return data;
	}
		
}
