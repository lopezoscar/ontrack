package com.ontrack.test;

import java.util.List;

import org.junit.Test;

import com.sappe.ontrack.model.issues.Project;

public class ProjectTest extends BaseTest{
	
	@Test
	public void test(){
		List<Project> projects = em.createNamedQuery("getAllProjects").getResultList();
		System.out.println(projects);
		System.out.println(projects.size());
		
	}

}
