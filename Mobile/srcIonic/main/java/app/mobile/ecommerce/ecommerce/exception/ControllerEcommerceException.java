package app.mobile.ecommerce.ecommerce.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerEcommerceException {
	
	/**
	 * responsável por manipular as excessões EcommerceException lançada,
	 * enviando a resposta com o status correto ao usuário
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = EcommerceException.class)
	public ResponseEntity<Void> handleEcommerceException(EcommerceException e) {
		return new ResponseEntity<Void>(e.getHttpStatus());
	}

}

