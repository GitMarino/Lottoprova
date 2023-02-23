package provalotto.datalayer.manager.impl;

public class ServiceErrorException extends Exception {

	private static final long serialVersionUID = 2404200401477539657L;

	public ServiceErrorException(final String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ServiceErrorException(final String message, final Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ServiceErrorException(final Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}