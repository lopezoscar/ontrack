package com.sappe.ontrack.soa.resources;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sappe.ontrack.dao.springbeans.interfaces.IssuePropertyTypeManager;
import com.sappe.ontrack.model.issues.IssuePropertyType;


@Component
@Path("issuepropertytypesrv")
public class IssuePropertyTypeService {
	
	@Qualifier("issuepropertytypebean")
	@Autowired
	IssuePropertyTypeManager issuePropertyTypeManager;
	
	@POST
	@Path("createissuepropertytype")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createIssuePropertyType (IssuePropertyType issuePropertyType) {
		IssuePropertyType ipt = issuePropertyTypeManager.create(issuePropertyType);
		return Response.ok().entity(ipt).build();
//		return ipt;
	}
	
	@GET
	@Path("getissuepropertytypebyid/{pk}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getIssuePropertyTypeByID (@PathParam("pk") Long primaryKey) {
		IssuePropertyType issuePropertyType = issuePropertyTypeManager.read(primaryKey);
		return toJson(issuePropertyType);
	}
	
	@POST
	@Path("getallissuepropertytypes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<IssuePropertyType> getAllIssuePropertyTypes() {
		List<IssuePropertyType> issuePropertyTypes = issuePropertyTypeManager.getAllIssuePropertyTypes();
		return issuePropertyTypes;
	}
	
	@POST
	@Path("updateissuepropertytype")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateIssuePropertyType (IssuePropertyType issuePropertyTypeJson) {
		issuePropertyTypeManager.update(issuePropertyTypeJson);
	}
	
	@POST
	@Path("deleteissuepropertytype")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteIssuePropertyType (IssuePropertyType issuePropertyTypeJson) {
		issuePropertyTypeManager.delete(issuePropertyTypeJson);
	}
	
	private String toJson(Object o){
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(o);
			System.out.println(json);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

}
