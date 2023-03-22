package provalotto.datalayer.exceptions;

public class InconsistentDataException extends RuntimeException {

	private static final long serialVersionUID = 1220836618136045049L;

	public InconsistentDataException() {
		super();
	}

	public InconsistentDataException(final String message) {
		super(message);
	}

	public InconsistentDataException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public InconsistentDataException(final Throwable cause) {
		super(cause);
	}
}