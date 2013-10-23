package com.sappe.ontrack.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sappe.ontrack.dao.springbeans.interfaces.UserManager;
import com.sappe.ontrack.model.users.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml"})
public class UserBeanTest {
	
	@Autowired
	private UserManager userManager;
	
	@Test
	public void test(){
		
		String userName = "https://www.google.com/accounts/o8/id?id=AItOawkMAYTsPU9NHMnyriu6ija1u-qkqW5mS3I";
		User user = userManager.userByUserName(userName);
		System.out.println(user.getMail());
	}

}
