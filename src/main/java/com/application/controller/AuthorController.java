package com.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.application.service.GrantAuthorService;

@RestController
public class AuthorController {
	@Autowired
	private GrantAuthorService authorService;

	@GetMapping("/api/auth/grant/{user}/{id}")
	public String grant(@PathVariable("id") Integer id, @PathVariable("user") String user) {
		return authorService.grant(id, user) == 1 ? "Thành công" : "Có lỗi";

	}

	@GetMapping("/api/auth/cancel/{user}/{id}")
	public String cancel(@PathVariable("id") Integer id, @PathVariable("user") String user) {
		return authorService.cancel(id, user) == 1 ? "Thành công" : "Có lỗi";
	}
}
