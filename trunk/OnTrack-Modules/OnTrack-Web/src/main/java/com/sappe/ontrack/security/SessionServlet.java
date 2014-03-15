package com.sappe.ontrack.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.HttpRequestHandler;

import com.sappe.ontrack.model.users.User;
import com.sappe.ontrack.sdk.interfaces.UserService;
/**
 * Servlet para manejar la session del usuario. Extiende de HttpRequestHandlerServlet
 *  porque necesito que se inyecte el servlet en el applicactionContext
 * @author Oscar
 *
 */
public class SessionServlet implements HttpRequestHandler{


	@Autowired
	private UserService userService;
	
	@Override
	public void handleRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails user = (UserDetails)auth.getPrincipal();
		WebAuthenticationDetails details = (WebAuthenticationDetails)auth.getDetails();
		details.getSessionId();
		UserDetailsViewModel vm = (UserDetailsViewModel)user;
		User u = null;
		if(vm.getUser() != null && vm.getUser().getMail() != null){
			u = userService.userByEmail(vm.getUser().getMail());
		}else{
			u = vm.getUser();
		}
		
		String userInJson = toJson(u);
		response.setContentType("application/json");
		response.getWriter().write(userInJson);
	}

	private String toJson(Object o){
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(o);
			System.out.println(json);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}


	

}
