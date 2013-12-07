package com.sappe.ontrack.soa.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sappe.ontrack.dao.exceptions.NotificatorException;
import com.sappe.ontrack.dao.springbeans.interfaces.IssueActionManager;
import com.sappe.ontrack.dao.springbeans.interfaces.IssueManager;
import com.sappe.ontrack.dao.springbeans.interfaces.LogIssueManager;
import com.sappe.ontrack.dao.springbeans.interfaces.NotificationManager;
import com.sappe.ontrack.dao.springbeans.interfaces.ProcessHistoryManager;
import com.sappe.ontrack.dao.springbeans.interfaces.ProjectManager;
import com.sappe.ontrack.dao.springbeans.interfaces.UserManager;
import com.sappe.ontrack.model.issues.Issue;
import com.sappe.ontrack.model.issues.IssueAction;
import com.sappe.ontrack.model.issues.IssueComment;
import com.sappe.ontrack.model.issues.IssueEntry;
import com.sappe.ontrack.model.issues.IssueStatus;
import com.sappe.ontrack.model.issues.IssueType;
import com.sappe.ontrack.model.issues.LogIssue;
import com.sappe.ontrack.model.issues.Project;
import com.sappe.ontrack.model.notifications.NotificationDTO;
import com.sappe.ontrack.model.users.User;

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

	@Autowired
	private ProjectManager projectManager;

	@Autowired
	private UserManager userManager;

	@Autowired
	private NotificationManager notificationManager;

	@Context
	private UriInfo uri;

	@POST
	@Path("listIssuesByUser")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Issue> listIssuesByUser(User user){
		List<Issue> issues = issueManager.getIssuesByOwnerId(user.getId());
		return issues;
	}

	@POST
	@Path("listIssuesByUserFromProject")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Issue> listIssuesByUserFromProject(User user){
		List<Issue> issues = issueManager.getIssuesByUserFromProjects(user);
		return issues;
	}

	@GET
	@Path("getissuebyid/{pk}")
	@Produces(MediaType.APPLICATION_JSON)
	public Issue getIssueById(@PathParam("pk") Long primaryKey){
		Issue issue = issueManager.read(primaryKey);
		Project project = projectManager.read(issue.getId());
		if(project!= null){
			issue.setProject(project);
		}
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
	public Response saveIssue(Issue issue){
		//		Issue issue = fromJSON( new TypeReference<Issue>() {},issueJson);
		IssueAction action = null;

		List<String> mailsToNotify = new ArrayList<String>();

		NotificationDTO dto = new NotificationDTO();
		dto.setFrom("noreply@ontrack.com.ar");
		dto.setSubject("OnTrack - Issue Actualizado Exitosamente");
		if(issue.getTitle()!= null){
			dto.setSubject("OnTrack - Issue: "+issue.getTitle());
		}

		URI url = uri.getBaseUri();
		StringBuffer sb = new StringBuffer();
		if(issue.getTitle()!= null){
			sb.append("Se guardó correctamente el issue: ");
			sb.append(issue.getTitle());
			sb.append("\n");
		}
		sb.append(issue);
		sb.append("\n");
		sb.append("Podés ver el detalle del issue en la siguiente url: ");
		sb.append("http://");
		sb.append(url.getHost());
		sb.append(":");
		sb.append(url.getPort());
		sb.append("/OnTrack/create-issue.html?issue=");
		sb.append(issue.getId());

		dto.setBody(sb.toString());
		
		
		if(issue.getId()!=null){
			Issue toUpdate = issueManager.read(issue.getId());
			toUpdate.setCurrentStatus(issue.getCurrentStatus());
			toUpdate.setOwner(issue.getOwner());
			toUpdate.setDescription(issue.getDescription());
			issueManager.update(toUpdate);
			action = issueActionManager.read(LogIssue.MERGED_ISSUE_CODE);

			if(toUpdate.getOwner().getMail() != null){
				mailsToNotify.add(toUpdate.getOwner().getMail());
			}

		}else{
			Issue result = issueManager.create(issue);
			if(result.getOwner().getMail() != null){
				mailsToNotify.add(result.getOwner().getMail());
			}
			if(result.getId() != null){
				action = issueActionManager.read(LogIssue.CREATED_ISSUE_CODE);

			}
		}
		if(!mailsToNotify.isEmpty()){
			dto.setTo(mailsToNotify);
		}

		try {
			notificationManager.sendEmails(dto);
		} catch (NotificatorException e) {
			e.printStackTrace();
		}


		processHistoryManager.addEntryToHistory(issue);
		logIssueManager.addLogToIssue(issue, action);
		return Response.ok().build();
		//		return Response.status(Status.NOT_FOUND).build();
	}

	//	private Issue replaceIssueData(Issue issueToReplace,Issue issueData){
	//		issueToReplace.setCurrentStatus(currentStatus);
	//		issueToReplace.setIssueType(issueType);
	//		return issueToReplace;
	//	}

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


	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	@Path("reassignOwner/{currentOwner}/{newOwner}")
	public Response reassign(@PathParam("currentOwner")Long currentOwner,@PathParam("newOwner")Long newOwner){
		if(currentOwner == null || newOwner == null){
			return Response.status(Status.BAD_REQUEST).build();
		}
		try{
			User oldOwner = userManager.read(currentOwner);
			User updateOwner = userManager.read(newOwner);
			issueManager.reassignOwner(oldOwner, updateOwner);
		}catch(Exception e){
			e.printStackTrace();
		}
		Response.status(Status.INTERNAL_SERVER_ERROR).build();
		return Response.ok().build();
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
