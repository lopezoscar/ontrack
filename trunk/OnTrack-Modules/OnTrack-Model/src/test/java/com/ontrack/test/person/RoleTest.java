package com.ontrack.test.person;

import java.util.List;

import org.junit.Test;

import com.ontrack.test.BaseTest;
import com.sappe.ontrack.model.users.Role;
import com.sappe.ontrack.model.users.User;

public class RoleTest extends BaseTest{

	@Test
	public void createRolesTest(){
		
		Role roleTest = new Role();
		roleTest.setRoleName("Invitado");
		em.getTransaction().begin();
		em.persist(roleTest);
		em.getTransaction().commit();
		
	}
	@Test
	public void assignRolesToUserTest(){
		User user = (User)em.find(User.class, 1l);
		user.setRoles(getAllRoles());
		em.getTransaction().begin();
		em.merge(user);
		em.getTransaction().commit();
		
	}
	
	public Role getRoleById(){
		Role role = (Role)em.find(Role.class, 1l);
		return role;
		
	}
	
	@SuppressWarnings("unchecked")
	private List<Role> getAllRoles(){
		List<Role> roles = em.createQuery("select r from Role r").getResultList();
		return roles;
		
	}
}

