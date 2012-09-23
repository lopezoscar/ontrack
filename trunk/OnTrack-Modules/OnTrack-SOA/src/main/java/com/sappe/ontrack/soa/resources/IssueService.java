package com.sappe.ontrack.soa.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sappe.ontrack.dao.springbeans.interfaces.IssueManager;

@Component
@Path("issueservice")
public class IssueService {
	
	@Qualifier("issuebean")
	@Autowired
	IssueManager issueManager;
	
	@GET
	@Path("helloworld")
	@Produces(MediaType.APPLICATION_JSON)
	public String helloWorld(){
		issueManager.getAllElements();
		return "Hola";
	}

		
}
