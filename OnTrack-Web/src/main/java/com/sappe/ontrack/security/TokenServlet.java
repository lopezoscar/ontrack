package com.sappe.ontrack.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.HttpRequestHandler;

public class TokenServlet  implements HttpRequestHandler{

	@Override
	public void handleRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String token = request.getParameter("token");
		HttpSession session = request.getSession(true);
		session.setAttribute("token", token);
		
	}

}
