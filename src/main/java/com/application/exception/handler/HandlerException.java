package com.application.exception.handler;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.application.error.ErrorResponse;
import com.application.exception.ClaimsIsNullException;

import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
public class HandlerException {
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorResponse> handlerAccessDeniedException(AccessDeniedException exception) {
		ErrorResponse response = new ErrorResponse(HttpStatus.FORBIDDEN.value(), exception.getMessage());
		return ResponseEntity.ok(response);
	}
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ErrorResponse> handlerException(BadCredentialsException exception) {
		ErrorResponse response = new ErrorResponse(100,exception.getMessage());
		return ResponseEntity.ok(response);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handlerException3(Exception exception) {
		ErrorResponse response = new ErrorResponse(98,exception.getMessage());
		return ResponseEntity.ok(response);
	}
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handlerException4(ConstraintViolationException exception) {
		ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),exception.getMessage() + "( Dữ liệu không hợp lệ )");
		
		return ResponseEntity.ok(response);
	}
//	private String prepareMessage(ConstraintViolationException exception) {
//        StringBuilder message = new StringBuilder();
////        for (ConstraintViolation<?> cv : exception.getConstraintViolations()) {
////            message.append(cv.getMessage()).append(", ");
////        }
//        return message.toString();
////        return exception.getLocalizedMessage();
//    }
}
