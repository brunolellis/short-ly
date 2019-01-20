package br.com.brunolellis.shortly.url.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ShortLyApiExceptionHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(ShortLyApiExceptionHandler.class);

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public void illegalArgumentExceptionHandler(IllegalArgumentException e) {
		LOG.error(e.getMessage(), e);
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public void defaultExceptionHandler(Exception e) {
		LOG.error(e.getMessage(), e);
	}
}
