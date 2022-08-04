package vega.it.TimeSheetApp.exceptions;

public class ProjectNotFoundException extends RuntimeException {
	
	private String message="Not found";

	public ProjectNotFoundException(String message) {
		super();
		this.message = message;
	}

	public ProjectNotFoundException() {
		super();
	}
	
	
	

}
