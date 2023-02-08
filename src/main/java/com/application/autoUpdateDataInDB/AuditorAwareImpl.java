package com.application.autoUpdateDataInDB;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.application.repository.AccountRepo;

public class AuditorAwareImpl implements AuditorAware<String> {
	// T phu thuoc vao kieu du lieu cua updateBy va createBy
//	@Autowired
//	private AccountRepo accountRepo;
	@Override
	public Optional<String> getCurrentAuditor() {
//		System.err.println("Accountrepo" + (accountRepo == null));
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication == null ? Optional.of("khong xac dinh") : Optional.ofNullable(authentication.getName());
	}

}
