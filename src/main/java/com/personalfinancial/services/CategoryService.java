package com.personalfinancial.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personalfinancial.dto.CategoryColorDTO;
import com.personalfinancial.dto.CategoryDTO;
import com.personalfinancial.entities.Category;
import com.personalfinancial.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Long saveCategory(CategoryDTO categoryDTO) {

		Category category = new Category();
		category.setName(categoryDTO.getName());
		category.setCategoryColor(categoryDTO.getCategoryColor());

		categoryRepository.save(category);
		return category.getId();
	}

	public void updateCategory(Long id, CategoryDTO categoryDTO) {

		Optional<Category> category = categoryRepository.findById(id);
		if (category.isPresent()) {
			category.get().setName(categoryDTO.getName());
			category.get().setCategoryColor(categoryDTO.getCategoryColor());
			categoryRepository.save(category.get());
		}
	}

	public void updateColorCategory(Long id, CategoryColorDTO categoryColorDTO) {

		Optional<Category> category = categoryRepository.findById(id);

		if (category.isPresent()) {

			category.get().setName(category.get().getName());
			category.get().setCategoryColor(categoryColorDTO.getCategoryColor());
			categoryRepository.save(category.get());
		}

	}

	public CategoryDTO getCategory(Long id) {

		Optional<Category> category = categoryRepository.findById(id);
		CategoryDTO categoryDTO = new CategoryDTO();

		if (category.isPresent()) {

			categoryDTO.setId(category.get().getId());
			categoryDTO.setName(category.get().getName());
			categoryDTO.setCategoryColor(category.get().getCategoryColor());
		}

		return categoryDTO;

	}

	public void deleteCategory(Long id) {

		categoryRepository.deleteById(id);
	}

}
