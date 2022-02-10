package com.task.backend.payload.response;


public class UserInfoResponse {
	private Long id;
	private String userName;
	private String email;

	public UserInfoResponse(Long id, String userName, String email) {
		this.id = id;
		this.userName = userName;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return userName;
	}

	public void setUsername(String userName) {
		this.userName = userName;
	}

}
