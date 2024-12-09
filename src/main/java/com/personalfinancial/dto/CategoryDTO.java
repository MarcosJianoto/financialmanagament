package com.personalfinancial.dto;

import com.personalfinancial.entities.CategoryColor;

public class CategoryDTO {

	private Long id;

	private String name;

	private CategoryColor categoryColor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CategoryColor getCategoryColor() {
		return categoryColor;
	}

	public void setCategoryColor(CategoryColor categoryColor) {
		this.categoryColor = categoryColor;
	}
}
