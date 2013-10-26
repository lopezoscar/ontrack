package com.ontrack.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Test;

import com.sappe.ontrack.model.issues.IssueStatus;
import com.sappe.ontrack.model.issues.IssueStatusByWorkflow;
import com.sappe.ontrack.model.issues.IssueStatusByWorkflowPK;
import com.sappe.ontrack.model.issues.IssueType;
import com.sappe.ontrack.model.issues.IssueTypeAndStatus;
import com.sappe.ontrack.model.issues.IssueTypeAndStatusPK;
import com.sappe.ontrack.model.issues.Project;
import com.sappe.ontrack.model.issues.Workflow;
import com.sappe.ontrack.model.users.User;

public class WorkflowTest extends BaseTest{
	
	@Test
	public void test6(){
		List<IssueStatusByWorkflow> list = em.createQuery("select i from IssueStatusByWorkflow i where i.pk.workflow = 1").getResultList();
		System.out.println(list.size());
	}
	
//	@Test
	public void test5(){
		List<IssueStatusByWorkflow> iswf = new ArrayList<IssueStatusByWorkflow>();
		
		IssueStatusByWorkflow position = new IssueStatusByWorkflow();
		IssueStatusByWorkflowPK pk = new IssueStatusByWorkflowPK();
		
		Workflow w1 = em.find(Workflow.class, 1l);
		IssueStatus s1 = em.find(IssueStatus.class, 2l);
		
		pk.setWorkflow(w1.getId());
		pk.setStatus(s1.getId());
		position.setIs(s1);
		position.setWf(w1);
		position.setPosition(1);
		position.setPk(pk);
		
		persiste(position);
		
		iswf.add(position);
		
//		w1.setIssueStatus(iswf);
		
		
		em.getTransaction().begin();
		em.merge(w1);
		em.getTransaction().commit();
		
		
	}
	
//	@Test
	public void test2() throws JsonParseException, JsonMappingException, IOException{
		File file = new File("C:/workflow.json");
		
		ObjectMapper mapper = new ObjectMapper();
//		List<Workflow> wfs = mapper.readValue(file, Workflow[].class);
		List<Workflow> wfs = mapper.readValue(file, new TypeReference<List<Workflow>>() {});
		System.out.println(wfs.size());
	}
	
//	@Test
	public void test3(){
//		Query p = em.createQuery("select p from Project p where :user in elements( p.users)");
//		User u = em.find(User.class, 1l);
//		p.setParameter("user", u);
//		List<Workflow> wfsss = p.getResultList();
//		System.out.println(wfsss.size());
//		Query q = em.createQuery("select wf from Workflow wf where :username in elements( wf.project.users)");
//		q.setParameter("username", "https://www.google.com/accounts/o8/id?id=AItOawkk783CXOx_P9FJJXcPqEHrOwkjYXhs3g8");
//		List<Workflow> workflows = q.getResultList();
//		System.out.println(workflows.size());
//		List<Workflow> wfs = em.createQuery("FROM Workflow").getResultList();
//		for (Workflow workflow : wfs) {
//			System.out.println(workflow);
//		}
	}
	
	
//	@Test
	public void test(){
//		Workflow workflow = new Workflow();
//		List<Project> projects = em.createQuery("select p from Project p ").getResultList();
//		for (Project project : projects) {
//			Workflow workflow = new Workflow();
//			workflow.setProject(project);
//			persiste(workflow);
//		}
		Project pj = em.find(Project.class, 1l);
//		Workflow workflow = new Workflow();
//		workflow.setProject(pj);
//		persiste(workflow);
		
//{"id":12,"issueTypesAndStatus":[{"pk":{"issueType":1,"issueStatus":1,"workflow":12},"issueType":{"id":1,"description":"ISSUE"},"issueStatus":{"id":1,"description":"OPEN"}}],"project":{"id":26,"name":"Project","roles":[],"users":[]}}

		
		//"[{"project":{"id":13,"name":"SAPPE","roles":null,"users":null},"issueType":{"description":"Issue"},"issueStatus":[{"description":"TODO"},{"description":"CLOSE"}]},{"project":{"id":13,"name":"SAPPE","roles":null,"users":null},"issueType":{"description":"Bug"},"issueStatus":[{"description":"TODO"},{"description":"FIX"}]},{"project":{"id":13,"name":"SAPPE","roles":null,"users":null},"issueType":{"description":"Task"},"issueStatus":[{"description":"TODO"},{"description":"CLOSE"}]}]"
		
		List<IssueType> its = em.createQuery("select it from IssueType it").getResultList();
		List<IssueStatus> iss = em.createQuery("select iss from IssueStatus iss").getResultList();
		for (IssueType issueType : its) {
			Workflow workflow = new Workflow();
			workflow.setProject(pj);
//			persiste(workflow);
			for (IssueStatus issueStatus : iss) {
				IssueTypeAndStatusPK pk = new IssueTypeAndStatusPK();
				pk.setIssueStatus(issueStatus.getId());
				pk.setIssueType(issueType.getId());
				pk.setWorkflow(workflow.getId());
				
				IssueTypeAndStatus itns = new IssueTypeAndStatus();
				itns.setPk(pk);
				itns.setIssueType(issueType);
				itns.setIssueStatus(issueStatus);
				itns.setWorkflow(workflow);
				
//				persiste(itns);
				
			}
		}
		
//		IssueType issueType = new IssueType();
//		issueType.setId(1l);
//		issueType.setDescription("ISSUE");
//		
//		List<IssueType> issueTypes = new ArrayList<IssueType>();
//		issueTypes.add(issueType);
////		workflow.setIssueTypes(issueTypes);
		
//		Project project = em.find(Project.class, 26l);
//		workflow.setProject(project);
		
//		List<IssueStatus> issueStatus = new ArrayList<IssueStatus>();
//		IssueStatus is = new IssueStatus();
//		is.setDescription("TODO");
//		is.setId(1l);
//		issueStatus.add(is);
		
		
//		persiste(workflow);
		
//		IssueTypeAndStatus itns = new IssueTypeAndStatus();
//		itns.setIssueStatus(is);
//		itns.setIssueType(issueType);
//		itns.setWorkflow(workflow);
//		
//		IssueTypeAndStatusPK pk = new IssueTypeAndStatusPK();
//		pk.setIssueStatus(is.getId());
//		pk.setIssueType(issueType.getId());
//		pk.setWorkflow(workflow.getId());
		
//		itns.setPk(pk);
//		
//		List<IssueTypeAndStatus> issueTypesAndStatus = new ArrayList<IssueTypeAndStatus>();
//		issueTypesAndStatus.add(itns);
//		
//		workflow.setIssueTypesAndStatus(issueTypesAndStatus);
		
//		em.getTransaction().begin();
//		em.merge(workflow);
//		em.getTransaction().commit();
		
//		toJson(workflow);
		
//		workflow.setIssueStatus(issueStatus);
//		{"id":null,"issueType":{"id":null,"description":"ISSUE"},"issueStatus":[{"id":null,"description":"TODO"}],"project":{"id":26,"name":"Project","roles":[],"users":[]}}

//		"{"project":{"id":26,"name":"Project","roles":null,"users":null},"issueType":{"description":"Issue"},"issueStatus":[{"description":"TODO"}]}";
		
//		toJson(workflow);
		
	}
	
	@Test
	public void test2323(){
		User u = (User)em.find(User.class, 1l);
		List<Workflow> wfs = em.createQuery("select wf from Workflow wf where :user in elements( wf.project.users)").setParameter("user", u).getResultList();
		
		
	}
	

	private List<IssueType> createIssueTypes(){
		List<IssueType> types = new ArrayList<IssueType>();
		for (int i = 1; i <= 10; i++) {
			IssueType it = new IssueType();
			it.setDescription("IssueType "+i);
			persiste(it);
			types.add(it);
		}
		return types;
	}
	
	private List<IssueStatus> createIssueStatus(){
		List<IssueStatus> status = new ArrayList<IssueStatus>();
		for (int i = 1; i <= 10; i++) {
			IssueStatus it = new IssueStatus();
			it.setDescription("IssueStatus "+i);
			persiste(it);
			status.add(it);
		}
		return status;
	}
	
	private void createProjects(){
		for (int i = 1; i <= 10; i++) {
			Project it = new Project();
			it.setName("Project "+i);
			persiste(it);
		}
	}

}
