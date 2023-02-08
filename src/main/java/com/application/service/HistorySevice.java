package com.application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.domain.History;
import com.application.model.request.HistoryDto;
import com.application.repository.AccountRepo;
import com.application.repository.HistoryRepo;

@Service
public class HistorySevice {
	@Autowired
	private HistoryRepo historyRepo;

	@Autowired
	private AccountRepo accountRepo;

	public com.application.model.response.HistoryDto insert(HistoryDto dto) {
		History history = new History();
		history.setValue(dto.getValue());
		history = historyRepo.save(history);
		com.application.model.response.HistoryDto response = new com.application.model.response.HistoryDto(
				history.getId(), history.getValue(), accountRepo.findByUsername(history.getCreatedBy()).get(),
				accountRepo.findByUsername(history.getUpdatedBy()).get(), history.getUpdateTime(),
				history.getCreateTime());
		return response;
	}

	public com.application.model.response.HistoryDto updae(HistoryDto dto) {
		History history = historyRepo.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Cannot found"));
		history.setValue(dto.getValue());
		history = historyRepo.save(history);
		com.application.model.response.HistoryDto response = new com.application.model.response.HistoryDto(
				history.getId(), history.getValue(), accountRepo.findByUsername(history.getCreatedBy()).get(),
				accountRepo.findByUsername(history.getUpdatedBy()).get(), history.getUpdateTime(),
				history.getCreateTime());
		return response;
	}

	public com.application.model.response.HistoryDto findById(Long id) {
		History history = historyRepo.findById(id).orElseThrow(() -> new RuntimeException("Cannot found"));
		com.application.model.response.HistoryDto response = new com.application.model.response.HistoryDto(
				history.getId(), history.getValue(), accountRepo.findByUsername(history.getCreatedBy()).get(),
				accountRepo.findByUsername(history.getUpdatedBy()).get(), history.getUpdateTime(),
				history.getCreateTime());
		return response;
	}
}
