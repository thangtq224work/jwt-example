package com.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.domain.Role;

public interface RoleRepo extends JpaRepository<Role, Long>{

}
