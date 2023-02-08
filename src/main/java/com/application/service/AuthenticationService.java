package com.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.domain.Account;
import com.application.domain.Role;
import com.application.model.EnumRole;
import com.application.model.request.AuthenticationRequest;
import com.application.model.request.RegisterRequest;
import com.application.model.response.AuthenticationResponse;
import com.application.repository.AccountRepo;
import com.application.security.JwtService;

@Service
public class AuthenticationService {
	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;

	public AuthenticationResponse authentcate(AuthenticationRequest authenticationRequest) {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
					authenticationRequest.getPassword()));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		String jwtToken = jwtService.genarateToken(authenticationRequest.getUsername());
		return new AuthenticationResponse(jwtToken);
	}

	public AuthenticationResponse register(RegisterRequest registerRequest) {
		Account account = new Account(null, encoder.encode(registerRequest.getPassword()), registerRequest.getUsername(), registerRequest.getGender(),registerRequest.getDob(), 
				List.of(new Role(1L,EnumRole.CLIENT)));
//		,null,null);
		accountRepo.save(account);
		
		return null;
	}
}
