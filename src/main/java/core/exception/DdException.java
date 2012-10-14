package core.exception;

public class DdException extends Exception {
	/*
	 * Code convention: 
	 * err0xx - Runtime 
	 * err1xx - Security 
	 * err2xx - Validation
	 * err3xx - Data
	 */

	public static final String VALIDATION_EXCEPTION = "Validattion exception";
	public static final String DATA_EXCEPTION = "Data exception";

	private static final long serialVersionUID = 1L;
	private String type;
	private String code;
	private String message;
	private Exception exception;

	public DdException(String type, String code, String message,
			Exception exception) {
		this.type = type;
		this.code = code;
		this.message = message;
		this.exception = exception;
	}

	public DdException(String type, String code, String message) {
		this.type = type;
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public Exception getException() {
		return exception;
	}

	public String getType() {
		return type;
	}

}
