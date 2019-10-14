package com.nhduong29.panasiada.dashboard.rest.response;

public class JwtAuthenticationResponse {
	private String token;
	private String tokenType = "Bearer";

	public JwtAuthenticationResponse(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

}
