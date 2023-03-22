package provalotto.datalayer.exceptions;

public class DataBaseException extends RuntimeException {

	private static final long serialVersionUID = -4877616572146308464L;

	public DataBaseException() {
		super();
	}

	public DataBaseException(final String message) {
		super(message);
	}

	public DataBaseException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public DataBaseException(final Throwable cause) {
		super(cause);
	}
}