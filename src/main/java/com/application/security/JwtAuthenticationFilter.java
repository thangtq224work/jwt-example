package com.application.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.application.error.ErrorResponse;
import com.application.exception.ClaimsIsNullException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	private JwtService jwtService;
	private AccountDetailService accountDetailService;

	@Autowired
	public JwtAuthenticationFilter(JwtService jwtService, AccountDetailService accountDetailService) {
		super();
		this.jwtService = jwtService;
		this.accountDetailService = accountDetailService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		String username = null;
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response); // chay filter bên dưới
			return; // ngưng ko chạy code bên dưới
		}
		jwt = authHeader.substring(7);// "Bearer " co 7 ki tu
		try {
			System.err.println("get jwt");
			username = jwtService.getUsernameFromJwtToken(jwt);
			System.err.println("has authen jwt");
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpStatus.FORBIDDEN.value());
			response.getWriter().write(new ObjectMapper().writeValueAsString(new ErrorResponse(HttpStatus.FORBIDDEN.value(),"JWT is invalid or has experied")));
			return;
		}
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = accountDetailService.loadUserByUsername(username);
			if (jwtService.isValidToken(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null,
						userDetails.getAuthorities());
				token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(token);
			}
		}
		filterChain.doFilter(request, response);
	}
}
