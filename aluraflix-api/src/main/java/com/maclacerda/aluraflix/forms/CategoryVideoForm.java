package com.maclacerda.aluraflix.forms;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.maclacerda.aluraflix.models.Category;
import com.maclacerda.aluraflix.repositories.CategoryRepository;

public class CategoryVideoForm {
	
	@Length(min = 5, max = 30)
	private String title;

	@Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$")
	private String color;

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

	public Category update(Long id, CategoryRepository repository) {
		Category category = repository.getOne(id);

		if (title != null) {
			category.setTitle(this.title);
		}

		if (color != null) {
			category.setColor(color);
		}

		return category;
	}

}