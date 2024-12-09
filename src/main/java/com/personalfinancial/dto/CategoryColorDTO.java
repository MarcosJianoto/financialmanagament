package com.personalfinancial.dto;

import com.personalfinancial.entities.CategoryColor;

public class CategoryColorDTO {

	private Long id;

	private CategoryColor categoryColor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CategoryColor getCategoryColor() {
		return categoryColor;
	}

	public void setCategoryColor(CategoryColor categoryColor) {
		this.categoryColor = categoryColor;
	}
}
