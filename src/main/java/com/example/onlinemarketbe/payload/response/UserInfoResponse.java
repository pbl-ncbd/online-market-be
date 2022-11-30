package com.example.onlinemarketbe.payload.response;

import com.example.onlinemarketbe.model.User;


import lombok.Getter;
import lombok.Setter;



@Getter
@Setter

public class UserInfoResponse {
	private Integer userId;

	private String username;

	private String accessToken;

	private String tokenType = "Bearer";

	public UserInfoResponse(User user, String accessToken) {
		this.userId = user.getId();
		this.username = user.getUsername();
		this.accessToken = accessToken;
	}
}
