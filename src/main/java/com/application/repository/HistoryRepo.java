package com.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.domain.History;
@Repository
public interface HistoryRepo extends JpaRepository<History, Long>{
	
}
