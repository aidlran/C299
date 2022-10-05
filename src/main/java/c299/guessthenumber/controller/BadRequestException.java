package c299.guessthenumber.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

	public BadRequestException() {
		this("Bad Request");
	}

	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(Throwable e) {
		this(e.getMessage(), e);
	}

	public BadRequestException(String message, Throwable e) {
		super(message, e);
	}
}
