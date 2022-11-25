package com.example.onlinemarketbe.payload.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Some javadoc. // OK
 *
 * @author Vuong
 * @since 20/11/2022
 * @deprecated Some javadoc.
 */
@Data
@SuppressWarnings({"checkstyle:Indentation"})
public class LoginRequest {
	@NotNull(message = "This is username")
	@ApiModelProperty(notes = "username", example = "adminserver", required = true)
	private String username;

	@NotNull(message = "This is password")
	@ApiModelProperty(notes = "password", example = "adminserver", required = true)
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
