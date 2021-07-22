package com.maclacerda.aluraflix.settings.validators;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.maclacerda.aluraflix.dtos.EntityNotFoundDTO;

@RestControllerAdvice
public class NotFoundErrorHandler {

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(EntityNotFoundException.class)
	public EntityNotFoundDTO handler(EntityNotFoundException exception) {
		return new EntityNotFoundDTO("Video not found");
	}

}