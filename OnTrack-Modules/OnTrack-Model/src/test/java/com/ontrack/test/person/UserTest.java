package com.ontrack.test.person;

import org.junit.Test;

import com.ontrack.test.BaseTest;
import com.sappe.ontrack.model.users.User;

public class UserTest extends BaseTest{

	@Test
	public void CreateUserTest(){
		User userTest = new User();
		userTest.setFirstName("Oscar");
		userTest.setLastName("Lopez");
		userTest.setMail("oscarlopez.job@gmail.com");
		userTest.setUserName("olopez");
		userTest.setPassword("Test");
		
		em.getTransaction().begin();
		em.persist(userTest);
		em.getTransaction().commit();
	}

}

