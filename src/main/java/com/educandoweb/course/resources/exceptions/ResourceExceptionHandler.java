package com.educandoweb.course.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException ex, HttpServletRequest request){
		String error = "Resource not Found"; 
		HttpStatus status = HttpStatus.NOT_FOUND; 
		StandardError standardError = new StandardError
		(Instant.now(), status.value(), error, ex.getMessage(), request.getRequestURI()); 
		return ResponseEntity.status(status).body(standardError); 	
	}
}
