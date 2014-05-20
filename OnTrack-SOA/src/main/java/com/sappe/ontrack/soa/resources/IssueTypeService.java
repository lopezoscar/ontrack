package com.sappe.ontrack.soa.resources;

import java.util.ArrayList;
import java.util.List;

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

import com.sappe.ontrack.dao.springbeans.interfaces.IssueTypeManager;
import com.sappe.ontrack.model.issues.IssueType;

@Component
@Path("issuetypesrv")
public class IssueTypeService {
	
	@Qualifier("issuetypebean")
	@Autowired
	IssueTypeManager issueTypeManager;
	
	@POST
	@Path("createissuetype")
	@Produces(MediaType.APPLICATION_JSON)
	public IssueType createIssueType(IssueType issueType) {
		List<IssueType> it = issueTypeManager.getIssueTypesByDesc(issueType.getDescription());
		if(it != null && !it.isEmpty()){
			//Existe al menos un IssueType con este nombre, retornar el primero.
			return it.iterator().next();
		}
		IssueType newIssueType = issueTypeManager.create(issueType);
		return newIssueType;
	}
	
	@POST
	@Path("createissuetypebylist")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createIssueTypeByList(List<IssueType> issueTypes) {
		List<IssueType> response = new ArrayList<IssueType>();
		for (IssueType type : issueTypes) {
			IssueType it = issueTypeManager.create(type);
			response.add(it);
		}
		return Response.ok().entity(response).build();
	}
	
	@GET
	@Path("getissuetypebyid/{pk}")
	@Produces(MediaType.APPLICATION_JSON)
	public IssueType getIssueTypeById(@PathParam("pk") Long primaryKey) {
		IssueType issueType = issueTypeManager.read(primaryKey);
		return issueType;
	}
	
	@POST
	@Path("getallissuetype")
	@Produces(MediaType.APPLICATION_JSON)
	public List<IssueType> getAllIssueType(){
		List<IssueType> issueType = issueTypeManager.getAllIssueType();
		return issueType; 
	}
	
	@POST
	@Path("updateissuetype")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateIssueType (IssueType issueTypeJson) {
		issueTypeManager.update(issueTypeJson);
	}
	
	@POST
	@Path("deleteissuestatus")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteIssueType (IssueType issueTypeJson) {
		issueTypeManager.delete(issueTypeJson);
	}
	
	@GET
	@Path("getissuetypesbyprojectid/{projectid}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<IssueType> getIssueTypeByProject(@PathParam("projectid")long project){
		return issueTypeManager.getIssueTypesByProjectId(project);
	}

}
