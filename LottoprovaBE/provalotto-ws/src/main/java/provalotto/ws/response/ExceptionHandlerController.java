package provalotto.ws.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import provalotto.datalayer.exceptions.DataBaseException;
import provalotto.datalayer.exceptions.InconsistentDataException;

@RestControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler({ DataBaseException.class })
	public Answer<?> handleDataBaseException(final DataBaseException e) {
		return Answer.ko(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ InconsistentDataException.class })
	public Answer<?> handleInconsistentDataException(final InconsistentDataException e) {
		return Answer.ko(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

}