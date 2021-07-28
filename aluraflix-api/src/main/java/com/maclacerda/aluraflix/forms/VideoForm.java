package com.maclacerda.aluraflix.forms;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.maclacerda.aluraflix.models.Category;
import com.maclacerda.aluraflix.models.Video;
import com.maclacerda.aluraflix.repositories.CategoryRepository;

public class VideoForm {
	
	@NotNull
	@NotEmpty
	@Length(min = 5, max = 30)
	private String title;
	
	@NotNull
	@NotEmpty
	@Length(min = 10, max = 30)
	private String description;
	
	@NotNull
	@NotEmpty
	@Pattern(regexp="((([A-Za-z]{3,9}:(?:\\/\\/)?)(?:[-;:&=\\+\\$,\\w]+@)?[A-Za-z0-9.-]+|(?:www.|[-;:&=\\+\\$,\\w]+@)[A-Za-z0-9.-]+)((?:\\/[\\+~%\\/.\\w-_]*)?\\??(?:[-\\+=&;%@.\\w_]*)#?(?:[\\w]*))?)")
	private String url;
	
	private Long categoryID;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Long categoryID) {
		this.categoryID = categoryID;
	}

	public Video parse(CategoryRepository repository) {
		Long category = 1L;
		
		if (categoryID != null) {
			category = categoryID;
		}
		
		Category videoCategory = repository.getOne(category);
		
		return new Video(title, description, url, videoCategory);
	}

}
