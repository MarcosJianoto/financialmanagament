package com.personalfinancial.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_category")
	@SequenceGenerator(name = "seq_category", sequenceName = "seq_category", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "color")
	@Enumerated(EnumType.STRING)
	private CategoryColor color;

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
		return color;
	}

	public void setCategoryColor(CategoryColor color) {
		this.color = color;
	}

}
