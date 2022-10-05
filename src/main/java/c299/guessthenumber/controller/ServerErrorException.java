package c299.guessthenumber.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerErrorException extends RuntimeException {
 
	public ServerErrorException() {
		this("Internal Server Error");
	}

	public ServerErrorException(String message) {
		super(message);
	}

	public ServerErrorException(Throwable e) {
		this(e.getMessage(), e);
	}

	public ServerErrorException(String message, Throwable e) {
		super(message, e);
	}
}
