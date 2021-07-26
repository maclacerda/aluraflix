package com.maclacerda.aluraflix.forms;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.maclacerda.aluraflix.models.Category;

public class CategoryForm {

	@NotNull
	@NotEmpty
	@Length(min = 5, max = 30)
	private String title;

	@NotNull
	@NotEmpty
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

	public Category parse() {
		return new Category(title, color);
	}

}
