package com.sauloaraujo.timesheet.web.configuration.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
		exception.printStackTrace();
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	}
}