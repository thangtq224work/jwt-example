package com.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.application.domain.Account;
import com.application.domain.Role;
import com.application.repository.AccountRepo;

@Service
public class GrantAuthorService {
	@Autowired
	private AccountRepo service;
	@Transactional
	public int cancel(Integer id,String username) {
		Account ac =  service.findByUsername(username).orElse(null);
		if(ac == null) {
			return -1;			
		}
		for (int i = 0; i < ac.getRoles().size(); i++) {
			
			if(ac.getRoles().get(i).getId() == (long)id) {
				ac.getRoles().remove(i);
				return 1;
			}
		}
		return -1;
	}
	@Transactional
	public int grant(Integer id,String username) {
		Account ac =  service.findByUsername(username).orElseThrow(()->new RuntimeException("Username '"+ username +"' not found"));
		if(ac == null) {
			return -1;			
		}
		for (int i = 0; i < ac.getRoles().size(); i++) {
			
			if(ac.getRoles().get(i).getId() == (long)id) {
				return 1;
			}
		}
		
		ac.getRoles().add(new Role((long)id));
		return 1;
	}
}
