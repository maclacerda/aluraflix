package com.maclacerda.aluraflix.dtos;

public class VideoDeletedDTO {
	
	private String message;
	
	public VideoDeletedDTO(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
