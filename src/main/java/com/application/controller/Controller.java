package com.application.controller;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.domain.Account;
import com.application.model.MyBean;
import com.application.repository.AccountRepo;

@RestController
public class Controller {
	@Autowired
	MyBean bean;
	@Autowired
	AccountRepo accountRepo;

	@PermitAll
	@RequestMapping("/getBean")
	public String get() {
		System.err.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		return bean.toString();
	}

	@PreAuthorize("authorizeBean.check(#input)")
	@GetMapping("/getAccount")
	public Account getString(@RequestParam(name = "input", defaultValue = "", required = false) String input) {
		return accountRepo.findById(1l).get();
	}

//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
//	@PreAuthorize("hasRole('ADMIN')") 
//	@RolesAllowed("ADMIN")
//	@Secured("ADMIN") X
	@RequestMapping("/hello/{id}")
	@PreAuthorize("makeDecision(#p) && hasRole('ADMIN')")
	@PostAuthorize("#p == 'teonv' && returnObject == 'hello'")
//	@PreAuthorize("makeDecision(#name) && hasRole('ADMIN')") // do not use @P
	public String login(
//			@RequestParam(name = "id", defaultValue = "", required = false) String name,
			@P("p") @PathVariable("id") String name) {
		SecurityContextHolder.getContext().getAuthentication().getAuthorities().forEach(System.out::print);
		return "hello";
	}
}
