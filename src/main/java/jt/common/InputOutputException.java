package jt.common;

public class InputOutputException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InputOutputException() {
		super();
	}

	public InputOutputException(String message, Throwable cause) {
		super(message, cause);
	}

	public InputOutputException(String message) {
		super(message);
	}

	public InputOutputException(Throwable cause) {
		super(cause);
	}

}
