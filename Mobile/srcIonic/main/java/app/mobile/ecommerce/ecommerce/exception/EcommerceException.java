package app.mobile.ecommerce.ecommerce.exception;

import org.springframework.http.HttpStatus;

public class EcommerceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private HttpStatus httpStatus;
	
	public EcommerceException() {
		super();
	}
	
	public EcommerceException(HttpStatus httpStatus) {
		super();
		this.httpStatus = httpStatus;
	}

	public EcommerceException(String message, Throwable t) {
		super(message, t);
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

}
