package com.application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.application.domain.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long>{
	@Query("SELECT ac FROM Account ac WHERE ac.username=:user")
	public Optional<Account> findByUsername(@Param("user") String username);
}
