package com.ontrack.test;

import org.junit.Test;

import com.sappe.ontrack.model.users.User;

public class UserTest extends BaseTest{

	@Test
	public void test(){
		User user = (User)em.createNamedQuery("userByUserName").setParameter("userName", "https://www.google.com/accounts/o8/id?id=AItOawkMAYTsPU9NHMnyriu6ija1u-qkqW5mS3I");
		System.out.println(user.getFirstName());
	}
}
