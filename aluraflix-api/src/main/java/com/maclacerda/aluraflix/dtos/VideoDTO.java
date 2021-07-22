package com.maclacerda.aluraflix.dtos;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import com.maclacerda.aluraflix.models.Video;

public class VideoDTO {
	
	private Long id;
	private String title;
	private String description;
	private String url;
	private LocalDateTime createdAt;
	
	public VideoDTO() {}

	public VideoDTO(Video video) {
		this.id = video.getId();
		this.title = video.getTitle();
		this.description = video.getDescription();
		this.url = video.getUrl();
		this.createdAt = video.getCreatedAt();
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getUrl() {
		return url;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public static Page<VideoDTO> converter(Page<Video> videos) {
		return videos.map(VideoDTO::new);
	}

}
