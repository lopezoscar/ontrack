package com.sappe.ontrack.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

public class LoginGoogleFilter extends AbstractPreAuthenticatedProcessingFilter {

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		if (request.getParameterMap().size() == 2) {
	        return true;
	    }
	    return false;
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		String[] credentials = new String[2];
	    credentials[0] = request.getParameter("state");
	    credentials[1] = request.getParameter("code");
	    return credentials;
	}

}
