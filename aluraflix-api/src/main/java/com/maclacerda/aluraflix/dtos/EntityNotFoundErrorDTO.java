package com.maclacerda.aluraflix.dtos;

public class EntityNotFoundErrorDTO {
	
	private String message;
	
	public EntityNotFoundErrorDTO(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

}
