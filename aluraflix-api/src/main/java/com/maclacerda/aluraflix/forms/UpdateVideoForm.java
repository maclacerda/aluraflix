package com.maclacerda.aluraflix.forms;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.maclacerda.aluraflix.models.Video;
import com.maclacerda.aluraflix.repositories.VideoRepository;

public class UpdateVideoForm {
	
	@Length(min = 5, max = 30)
	private String title;
	
	@Length(min = 10, max = 30)
	private String description;

	@Pattern(regexp="((([A-Za-z]{3,9}:(?:\\/\\/)?)(?:[-;:&=\\+\\$,\\w]+@)?[A-Za-z0-9.-]+|(?:www.|[-;:&=\\+\\$,\\w]+@)[A-Za-z0-9.-]+)((?:\\/[\\+~%\\/.\\w-_]*)?\\??(?:[-\\+=&;%@.\\w_]*)#?(?:[\\w]*))?)")
	private String url;

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

	public Video update(Long id, VideoRepository repository) {
		Video video = repository.getOne(id);
		
		if (title != null) {
			video.setTitle(this.title);
		}
		
		if (description != null) {
			video.setDescription(this.description);
		}
		
		if (url != null) {
			video.setUrl(this.url);
		}
		
		return video;
	}

}
