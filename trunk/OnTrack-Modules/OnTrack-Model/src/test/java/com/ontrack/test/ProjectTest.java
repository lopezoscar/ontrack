package com.ontrack.test;

import java.util.List;

import org.junit.Test;

import com.sappe.ontrack.model.issues.Project;
import com.sappe.ontrack.model.users.User;

public class ProjectTest extends BaseTest{
	
	@Test
	public void test(){
		@SuppressWarnings("unchecked")
//		List<Project> projects = em.createNamedQuery("getAllProjects").getResultList();
		List<Project> projects = em.createQuery("SELECT p from Project p where :user in elements (p.users) ")
		.setParameter("user", em.find(User.class, 1l))
		.getResultList();
		
		System.out.println(projects);
		System.out.println(projects.size());
		
	}

}
