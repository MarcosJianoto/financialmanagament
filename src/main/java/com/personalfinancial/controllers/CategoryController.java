package com.personalfinancial.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.personalfinancial.dto.CategoryColorDTO;
import com.personalfinancial.dto.CategoryDTO;
import com.personalfinancial.services.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/financialcategory")
	public ResponseEntity<Void> saveCategory(@RequestBody CategoryDTO categoryDTO) {
		categoryService.saveCategory(categoryDTO);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/financialcategory/{id}")
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long id) {

		CategoryDTO categoryDTO = categoryService.getCategory(id);
		return ResponseEntity.ok().body(categoryDTO);
	}

	@PutMapping("/financialcategory/{id}")
	public ResponseEntity<Void> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {

		categoryService.updateCategory(id, categoryDTO);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/editcategorycolor/{id}")
	public ResponseEntity<Void> updateColorCategory(@PathVariable Long id,
			@RequestBody CategoryColorDTO categoryColorDTO) {
		categoryService.updateColorCategory(id, categoryColorDTO);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/financialcategory/{id}")
	public void deleteCategory(@PathVariable() Long id) {

		categoryService.deleteCategory(id);
	}

}