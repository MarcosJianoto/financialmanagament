package com.personalfinancial.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.personalfinancial.dto.RevenuesDTO;
import com.personalfinancial.services.RevenuesService;

@RestController
public class RevenuesController {

	@Autowired
	private RevenuesService revenuesService;

	@PostMapping("/saverevenues")
	public ResponseEntity<Void> saveRevenues(@RequestBody RevenuesDTO revenuesDTO) {

		revenuesService.saveRevenues(revenuesDTO);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/updaterevenues/{id}")
	public ResponseEntity<Void> updateRevenues(@PathVariable Long id, @RequestBody RevenuesDTO revenuesDTO) {

		revenuesService.updateRevenues(id, revenuesDTO);
		return ResponseEntity.noContent().build();

	}

	@GetMapping("/getrevenues")
	public ResponseEntity<List<RevenuesDTO>> getRevenues(@PathVariable Long id) {

		List<RevenuesDTO> revenuesDTO = revenuesService.getRevenues(id);
		return ResponseEntity.ok().body(revenuesDTO);
	}

	@DeleteMapping("/deleterevenues/{id}")
	public ResponseEntity<Void> deleteRevenues(@PathVariable Long id) {

		revenuesService.deleteRevenues(id);
		return ResponseEntity.noContent().build();

	}

}
