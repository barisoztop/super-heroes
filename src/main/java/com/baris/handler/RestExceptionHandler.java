package com.baris.handler;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.baris.dto.error.ErrorDetail;
import com.baris.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException, HttpServletRequest request) {
		
		ErrorDetail errorDetail = new ErrorDetail();
		errorDetail.setTimeStamp(new Date().getTime());
		errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
		errorDetail.setTitle("Resource Not Found");
		errorDetail.setDetail(resourceNotFoundException.getMessage());
		errorDetail.setDeveloperMessage(resourceNotFoundException.getClass().getName());
		
		return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
	}	

}
