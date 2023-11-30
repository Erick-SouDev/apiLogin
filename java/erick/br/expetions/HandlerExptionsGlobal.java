package erick.br.expetions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ch.qos.logback.core.status.Status;

@RestControllerAdvice
@ControllerAdvice
public class HandlerExptionsGlobal extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ DataIntegrityViolationException.class })
	protected ResponseEntity<Object> handlerExeptionDataIntegri(Exception e) {
		String msg = "";
		if (e instanceof DataIntegrityViolationException) {
			msg = e.getMessage();
		} else {
			msg = e.getMessage();
		}

		ObjetoErro erro = new ObjetoErro();
		erro.setCodigo(Status.ERROR);
		erro.setMenssagem(msg);
		return new ResponseEntity<Object>(erro, HttpStatus.FORBIDDEN);

	}

}
