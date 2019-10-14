package com.nhduong29.panasiada.dashboard.rest.response;

public class GeneralResponse {
	private Boolean success;
	private String message;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public GeneralResponse(Boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

}
