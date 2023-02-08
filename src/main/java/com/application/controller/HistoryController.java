package com.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.application.model.request.HistoryDto;
import com.application.service.HistorySevice;

@RestController
public class HistoryController {
	@Autowired
	private HistorySevice historySevice;
	@PostMapping("/insert-history")
	public ResponseEntity<com.application.model.response.HistoryDto> insert(@RequestBody HistoryDto dto){
		return new ResponseEntity<com.application.model.response.HistoryDto>(historySevice.insert(dto), HttpStatus.CREATED);
	}
	@PostMapping("/update-history")
	public ResponseEntity<com.application.model.response.HistoryDto> update(@RequestBody HistoryDto dto){
		return new ResponseEntity<com.application.model.response.HistoryDto>(historySevice.updae(dto), HttpStatus.OK);
	}
	@GetMapping("/history/{id}")
	public ResponseEntity<com.application.model.response.HistoryDto> update(@PathVariable("id")Long id){
		return new ResponseEntity<com.application.model.response.HistoryDto>(historySevice.findById(id), HttpStatus.OK);
	}
}
