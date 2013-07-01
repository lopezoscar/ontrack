package com.sappe.ontrack.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sappe.ontrack.model.users.User;
import com.sappe.ontrack.sdk.interfaces.UserService;

public class UserDetailService implements UserDetailsService{
	
	@Autowired
	private UserService userSrv;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		User user = userSrv.userByUserName(username);
		UserDetailsViewModel vm = new UserDetailsViewModel();
		vm.setUser(user);
		return vm;
	}

}
