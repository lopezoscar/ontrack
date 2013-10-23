package com.sappe.ontrack.security;

import java.io.IOException;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sappe.ontrack.model.issues.GoogleUser;
import com.sappe.ontrack.model.users.User;
import com.sappe.ontrack.sdk.impl.Mapper;


public class PreAuthUserDetailsService implements AuthenticationUserDetailsService {

	@Override
	public UserDetails loadUserDetails(Authentication token)
			throws UsernameNotFoundException {
		UserDetails userDetails = null;

		String[] credentials = (String[])token.getCredentials();
		boolean principal =  Boolean.valueOf(token.getPrincipal().toString());
		String loginURL = "";
		if (credentials != null && principal == true) {
			String tokenGoogle = credentials[1];
			UserDetailsViewModel vm = new UserDetailsViewModel();
			User user = new User();
//				user.setFirstName(gUser.getGiven_name());
//				user.setLastName(gUser.getFamily_name());
//				user.setMail(gUser.getEmail());
			user.setToken(tokenGoogle);
			vm.setUser(user);
			
			userDetails = vm;
		}

		if (userDetails == null) {
			throw new UsernameNotFoundException("Login URL: "+loginURL);
		}

		return userDetails;
	}

}
