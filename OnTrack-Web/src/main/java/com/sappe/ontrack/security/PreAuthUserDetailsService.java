package com.sappe.ontrack.security;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sappe.ontrack.model.issues.GoogleUser;
import com.sappe.ontrack.model.users.User;
import com.sappe.ontrack.sdk.interfaces.UserService;


public class PreAuthUserDetailsService implements AuthenticationUserDetailsService {
	
	@Autowired
	private UserService userService;

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
			GoogleAuthHelper helper = new GoogleAuthHelper();
			String response;
			try {
				response = helper.getUserInfoJson(tokenGoogle);
				GoogleUser gUser = fromJSON(new TypeReference<GoogleUser>() {},response);
				
				User user = userService.userByEmail(gUser.getEmail());
				if(user != null){
					user.setToken(tokenGoogle);
					vm.setUser(user);
					userDetails = vm;
				}else{
					User userModel = new User();
					userModel.setFirstName(gUser.getGiven_name());
					userModel.setLastName(gUser.getFamily_name());
					userModel.setMail(gUser.getEmail());
					userModel.setToken(tokenGoogle);
					User u = userService.createUser(userModel);
					vm.setUser(u);
					userDetails = vm;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		if (userDetails == null) {
			throw new UsernameNotFoundException("Login URL: "+loginURL);
		}

		return userDetails;
	}
	
	public static <T> T fromJSON(final TypeReference<T> type,final String jsonPacket) {
		   T data = null;
	
		   try {
		      data = new ObjectMapper().readValue(jsonPacket, type);
		   } catch (Exception e) {
		      e.printStackTrace();
		   }
		   return data;
	}
	
	

}
