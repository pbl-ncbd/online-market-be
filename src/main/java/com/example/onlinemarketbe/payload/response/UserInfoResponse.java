package com.example.onlinemarketbe.payload.response;

import com.example.onlinemarketbe.model.User;


import lombok.Getter;
import lombok.Setter;

/**
 * Some javadoc. // OK
 *
 * @author Vuong
 * @since 20/11/2022
 * @deprecated Some javadoc.
 */
@SuppressWarnings({"checkstyle:Indentation", "checkstyle:FileTabCharacter"})
@Getter
@Setter

public class UserInfoResponse {
	private Integer userId;

	private String username;

	private String accessToken;

	private String tokenType = "Bearer";


	/**
	 * Some javadoc. // OK
	 *
	 * @author Vuong
	 * @since 20/11/2022
	 * @deprecated Some javadoc.
	 */
	public UserInfoResponse(User user, String accessToken) {
		this.userId = user.getId();
		this.username = user.getUsername();
		this.accessToken = accessToken;
	}
}
