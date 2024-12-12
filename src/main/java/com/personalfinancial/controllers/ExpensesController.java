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

import com.personalfinancial.dto.ExpensesDTO;
import com.personalfinancial.services.ExpensesService;

@RestController
public class ExpensesController {

	@Autowired
	private ExpensesService expensesService;

	@PostMapping("/saveexpenses")
	public ResponseEntity<Void> saveRevenues(@RequestBody ExpensesDTO expensesDTO) {

		expensesService.saveExpenses(expensesDTO);
		return ResponseEntity.noContent().build();

	}

	@GetMapping("/getexpensesun/{id}")
	public ResponseEntity<ExpensesDTO> getExpensesUn(@PathVariable Long id) {

		ExpensesDTO expensesDTO = expensesService.getExpensesUn(id);
		return ResponseEntity.ok().body(expensesDTO);
	}

	@GetMapping("/getexpenses")
	public ResponseEntity<List<ExpensesDTO>> getExpenses() {

		List<ExpensesDTO> expensesDTO = expensesService.getExpenses();
		return ResponseEntity.ok().body(expensesDTO);
	}

	@PutMapping("/updateexpenses/{id}")
	public ResponseEntity<ExpensesDTO> updateExpenses(@PathVariable Long id, @RequestBody ExpensesDTO expensesDTO) {

		expensesService.updateExpenses(id, expensesDTO);
		return ResponseEntity.noContent().build();

	}

	@DeleteMapping("/deleteexpenses/{id}")
	public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {

		expensesService.deleteExpenses(id);
		return ResponseEntity.noContent().build();
	}

}
