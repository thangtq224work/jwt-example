package com.application.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.application.domain.Account;
import com.application.repository.AccountRepo;

@Service
public class AccountDetailService implements UserDetailsService {
	@Autowired
	private AccountRepo accountRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found ", username)));
		return new AccountDetail(account);
	}

}
