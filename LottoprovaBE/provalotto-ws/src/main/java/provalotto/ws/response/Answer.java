package provalotto.ws.response;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Answer<T> extends ResponseEntity {

	public static <T> Answer<T> ko(final HttpStatus status) {
		return ko(CodeConstants.ERROR_CODE, null, status);
	}

	public static <T> Answer<T> ko(final Integer code, final String message, final HttpStatus status) {
		return take(null, code, message, status);
	}

	public static <T> Answer<T> ko(final String message, final HttpStatus status) {
		return ko(CodeConstants.ERROR_CODE, message, status);
	}

	public static <T> Answer<T> ok(final HttpStatus status) {
		return ok(null, CodeConstants.SUCCESS_CODE, null, status);
	}

	public static <T> Answer<T> ok(final Integer code, final String message, final HttpStatus status) {
		return ok(null, code, message, status);
	}

	public static <T> Answer<T> ok(final T body, final HttpStatus status) {
		return ok(body, CodeConstants.SUCCESS_CODE, null, status);
	}

	public static <T> Answer<T> ok(final T body, final Integer code, final String message, final HttpStatus status) {
		return take(body, code, message, status);
	}

	public static <T> Answer<T> take(final T body, final Integer code, final String message, final HttpStatus status) {
		return new Answer<>(body, code, message, status);
	}

	private Integer code;

	private String message;

	public Answer(final T body, final Integer code, final String message, final HttpStatus status) {
		super(body, status);
		this.code = code;
		this.message = message;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Answer other = (Answer) obj;
		return Objects.equals(code, other.code) && Objects.equals(message, other.message);
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(code, message);
		return result;
	}

	public void setCode(final Integer code) {
		this.code = code;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Answer [code=" + code + ", message=" + message + "]";
	}

}