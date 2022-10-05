package c299.guessthenumber.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
 
	public NotFoundException() {
		this("Resource Not Found");
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(Throwable e) {
		this(e.getMessage(), e);
	}

	public NotFoundException(String message, Throwable e) {
		super(message, e);
	}
}
