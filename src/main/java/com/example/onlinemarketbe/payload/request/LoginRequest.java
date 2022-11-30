package com.example.onlinemarketbe.payload.request;

//import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class LoginRequest {
	@NotNull(message = "This is username")
	@Schema(title = "username", example = "adminserver")
	private String username;

	@NotNull(message = "This is password")
	@Schema(title = "password", example = "adminserver")
	private String password;

	public LoginRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
