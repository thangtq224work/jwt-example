package com.application.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.model.request.AuthenticationRequest;
import com.application.model.request.RegisterRequest;
import com.application.model.response.AuthenticationResponse;
import com.application.service.AuthenticationService;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/auth")
public class RestController {
	@Autowired
	private AuthenticationService authenticationService;
	
	@RequestMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(
			@RequestBody AuthenticationRequest authenticationRequest,HttpServletRequest request) {
		 String remoteIp = request.getHeader("X-FORWARDED-FOR");
		 if(remoteIp == null) {
			 remoteIp = request.getRemoteAddr();
			 //
		 }
		 System.err.println(remoteIp);
		return ResponseEntity.ok(authenticationService.authentcate(authenticationRequest));
	}
	@RequestMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(
			@RequestBody RegisterRequest registerRequest) {
		return ResponseEntity.ok(authenticationService.register(registerRequest));
	}
}
