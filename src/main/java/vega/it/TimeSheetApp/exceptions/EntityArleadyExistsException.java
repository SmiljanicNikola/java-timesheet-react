package vega.it.TimeSheetApp.exceptions;

@SuppressWarnings("serial")
public class EntityArleadyExistsException extends RuntimeException {
	
	private String message;

	public EntityArleadyExistsException(String message) {
		super();
		this.message = message;
	}

	public EntityArleadyExistsException() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
	
}
