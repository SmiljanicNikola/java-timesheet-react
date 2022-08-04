package vega.it.TimeSheetApp.security;

public class JWTResponse {
	
	private String token;
	
	private String type="Bearer";
	
	private String username;

	public JWTResponse(String token, String type, String username) {
		super();
		this.token = token;
		this.type = type;
		this.username = username;
	}

	public JWTResponse(String token, String username) {
		super();
		this.token = token;
		this.username = username;
	}

	public JWTResponse() {
		super();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	

}
