package com.maclacerda.aluraflix.dtos;

import org.springframework.data.domain.Page;

import com.maclacerda.aluraflix.models.Category;

public class CategoryDTO {
	private Long id;
	private String title;
	private String color;

	public CategoryDTO() {}

	public CategoryDTO(Category category) {
		this.id = category.getId();
		this.title = category.getTitle();
		this.color= category.getColor();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public static Page<CategoryDTO> parse(Page<Category> categories) {
		return categories.map(CategoryDTO::new);
	}
	
}