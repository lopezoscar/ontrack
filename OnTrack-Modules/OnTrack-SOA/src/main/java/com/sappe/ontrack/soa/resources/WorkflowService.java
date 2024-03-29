package com.sappe.ontrack.soa.resources;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sappe.ontrack.dao.springbeans.interfaces.IssuePropertyManager;
import com.sappe.ontrack.dao.springbeans.interfaces.IssuePropertyTypeManager;
import com.sappe.ontrack.dao.springbeans.interfaces.IssueStatusManager;
import com.sappe.ontrack.dao.springbeans.interfaces.IssueTypeManager;
import com.sappe.ontrack.dao.springbeans.interfaces.WorkflowManager;
import com.sappe.ontrack.model.issues.IssueProperty;
import com.sappe.ontrack.model.issues.IssuePropertyType;
import com.sappe.ontrack.model.issues.IssueStatus;
import com.sappe.ontrack.model.issues.IssueStatusByWorkflow;
import com.sappe.ontrack.model.issues.IssueStatusByWorkflowPK;
import com.sappe.ontrack.model.issues.IssueType;
import com.sappe.ontrack.model.issues.Project;
import com.sappe.ontrack.model.issues.Workflow;
import com.sappe.ontrack.model.users.User;

@Component
@Path("workflowsrv")
public class WorkflowService implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5729521218282629339L;
	
	@Autowired
	private WorkflowManager wfManager;
	
	@Autowired
	private IssueTypeManager issueTypeManager;
	
	@Autowired
	private IssueStatusManager issueStatusManager;
	
	@Autowired
	private IssuePropertyManager issuePropertyManager;
	
	@Autowired
	private IssuePropertyTypeManager issuePropertyTypeManager;

	@Path("createworkflow")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createWorkflow(Workflow workflow){
		Workflow wf = wfManager.create(workflow);
		return Response.ok().entity(wf).build();
	}
	
	@Path("listworkflowsbyuser")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response listWorkflowsByUser(User user){
		List<Workflow> workflows = wfManager.listWorkflowsByUser(user);
		return Response.ok().entity(workflows).build();
	}
	
	@Path("listworkflowsbyproject")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Workflow> listWorkflowsByProject(Project project){
		List<Workflow> workflows = wfManager.listWorkflowsByProject(project);
		return workflows;
	}
	
	@Path("createworkflowbylist")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createWorkflow(String workflowsinJson){
		List<Workflow> workflows = fromJSON(new TypeReference<List<Workflow>>(){}, workflowsinJson);
		try{
			for (Workflow wf : workflows) {
				
				List<IssueProperty> persistedIssueProperties = new ArrayList<IssueProperty>();
				for (IssueProperty property : wf.getIssueProperties()) {
					IssueProperty p = createIssueProperty(property);
					persistedIssueProperties.add(p);
				}
				
				IssueType issueType = createIssueTypes(wf.getIssueType());
				wf.setIssueType(issueType);
				wf.setIssueProperties(persistedIssueProperties);
				
				Workflow createdWF = wfManager.create(wf);
				
				List<IssueStatusByWorkflow> issueStatusByWorkflow  = new ArrayList<IssueStatusByWorkflow>();
				for (IssueStatus iss : wf.getIssueStatus()) {
					IssueStatus is = createIssueStatus(iss);

					IssueStatusByWorkflow statusByWorkflow = new IssueStatusByWorkflow();
					statusByWorkflow.setIs(is);
					statusByWorkflow.setPosition(iss.getPosition());
					statusByWorkflow.setWf(createdWF);
					
					IssueStatusByWorkflowPK pk = new IssueStatusByWorkflowPK();
					pk.setStatus(is.getId());
					pk.setWorkflow(createdWF.getId());
					
					statusByWorkflow.setPk(pk);
					statusByWorkflow = wfManager.saveIssueStatusByWorkfow(statusByWorkflow);
					
				}
				wf.setIssueStatusByWorkflow(issueStatusByWorkflow);
				
				
				wfManager.update(createdWF);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return Response.ok().build();
	}
	
	private IssueType createIssueTypes(IssueType issueType){
		List<IssueType> it = issueTypeManager.getIssueTypesByDesc(issueType.getDescription());
		if(it != null && !it.isEmpty()){
			//Existe al menos un IssueType con este nombre, retornar el primero.
			return it.iterator().next();
		}
		IssueType newIssueType = issueTypeManager.create(issueType);
		return newIssueType;
	}
	
	private IssueStatus createIssueStatus(IssueStatus issueStatus){
		List<IssueStatus> issueStatuses = issueStatusManager.getIssueStatusByDesc(issueStatus.getDescription());
		if(issueStatuses != null  && !issueStatuses.isEmpty()){
			return issueStatuses.iterator().next();
		}
		IssueStatus is = issueStatusManager.create(issueStatus);	
		return is;
	}
	
	private IssueProperty createIssueProperty(IssueProperty issueProperty){
		List<IssueProperty> issueProp = issuePropertyManager.getIssuePropertyByDesc(issueProperty.getDescription());
		if(issueProp != null  && !issueProp.isEmpty()){
			return issueProp.iterator().next();
		}
		IssueProperty ip = new IssueProperty();
		ip.setDescription(issueProperty.getDescription());
		IssuePropertyType type = issuePropertyTypeManager.read(issueProperty.getType().getId());
		ip.setType(type);
		ip = issuePropertyManager.create(ip);	
		return ip;
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
