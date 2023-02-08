package com.application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(
//		  prePostEnabled = true, 
//		  securedEnabled = true, 
//		  jsr250Enabled = true)
public class SecurityConfig{
	private EntryPoint entryPoint;
	private BCryptPasswordEncoder encoder;
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	private AuthenticationProvider authenticationProvider;
//	private CustomAuthenticationProvider customAuthenticationProvider;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
		.antMatchers("/api/auth/**").permitAll()
		.anyRequest().authenticated();
//		.anyRequest().authenticated();
//		.and().formLogin()
//				.and().httpBasic();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authenticationProvider(authenticationProvider);
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		http.exceptionHandling().authenticationEntryPoint(entryPoint);
		return http.build();
	}

	@Autowired
	public SecurityConfig(EntryPoint entryPoint, BCryptPasswordEncoder encoder,
			JwtAuthenticationFilter jwtAuthenticationFilter, AuthenticationProvider authenticationProvider) {
		super();
		this.entryPoint = entryPoint;
		this.encoder = encoder;
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		this.authenticationProvider = authenticationProvider;
	}

//	@Bean
//	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
//		System.err.println(encoder.encode("123123"));
//		AuthenticationManagerBuilder authenticationManagerBuilder = http
//				.getSharedObject(AuthenticationManagerBuilder.class);
////		authenticationManagerBuilder.inMemoryAuthentication().withUser("sa2")
////				.password(encoder.encode("123123")).roles("USER");
////		authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider);
//		return authenticationManagerBuilder.build();
//	}
//	@Bean 
//	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//		// AuthenticationProvider with Inmemory
//		UserDetails userDetails = User.builder().username("admin1").password(encoder.encode("myPassword")).authorities("USER","ADMIN").build();
//		return new InMemoryUserDetailsManager(userDetails);
//	}
}
