package com.maclacerda.aluraflix.dtos;

public class EntityNotFoundDTO {
	
	private String message;
	
	public EntityNotFoundDTO(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
