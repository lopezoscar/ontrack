package com.sappe.ontrack.soa.resources;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sappe.ontrack.dao.springbeans.interfaces.WorkflowManager;
import com.sappe.ontrack.model.issues.Workflow;

@Component
@Path("workflowsrv")
public class WorkflowService implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5729521218282629339L;
	
	@Autowired
	private WorkflowManager wfManager;

	@Path("createworkflow")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createWorkflow(Workflow workflow){
		Workflow wf = wfManager.create(workflow);
		return Response.ok().entity(wf).build();
	}
	
	@Path("createworkflowbylist")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createWorkflow(List<Workflow> workflows){
		try{
			for (Workflow wf : workflows) {
				Workflow createdWF = wfManager.create(wf);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return Response.ok().build();
	}

}
