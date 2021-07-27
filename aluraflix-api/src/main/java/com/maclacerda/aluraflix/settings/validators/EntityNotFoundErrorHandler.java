package com.maclacerda.aluraflix.settings.validators;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.maclacerda.aluraflix.dtos.EntityNotFoundErrorDTO;

@RestControllerAdvice
public class EntityNotFoundErrorHandler {
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<EntityNotFoundErrorDTO> handler(EntityNotFoundException exception) {
		String message = exception.getMessage();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new EntityNotFoundErrorDTO(message));
	}

}
