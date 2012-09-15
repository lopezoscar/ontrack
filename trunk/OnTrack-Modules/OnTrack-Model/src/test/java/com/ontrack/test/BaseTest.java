package com.ontrack.test;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;

public class BaseTest {
	
	static protected EntityManagerFactory emFactory;

	static protected EntityManager em;

	
	@BeforeClass
	static public void setUp() throws Exception {
		try {
			Map<String, String> configOverrides = new HashMap<String, String>();
	    	configOverrides.put("hibernate.connection.username","root");
			configOverrides.put("hibernate.connection.password","admin");
			configOverrides.put("hibernate.connection.url","jdbc:mysql://localhost:3306/ontrack?autoReconnect=true");
			emFactory = Persistence.createEntityManagerFactory("ontrack",configOverrides);
			em = emFactory.createEntityManager();
		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.fail("Exception during JPA EntityManager instanciation.");
		}
	}
	
	public EntityManager createEntityManager(){
		try {
			Map<String, String> configOverrides = new HashMap<String, String>();
	    	configOverrides.put("hibernate.connection.username","root");
			configOverrides.put("hibernate.connection.password","admin");
			configOverrides.put("hibernate.connection.url","jdbc:mysql://localhost:3306/ontrack?autoReconnect=true");
			emFactory = Persistence.createEntityManagerFactory("ontrack",configOverrides);
			em = emFactory.createEntityManager();
		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.fail("Exception during JPA EntityManager instanciation.");
		}
		
		return em;
	}
	
	
	public void killEntityManager(){
		if (em != null) {
			em.close();
		}
		if (emFactory != null) {
			emFactory.close();
		}
	}
	
	

	@AfterClass
	static public void tearDown() throws Exception {
		if (em != null) {
			em.close();
		}
		if (emFactory != null) {
			emFactory.close();
		}
	}

}
