package com.example.onlinemarketbe.payload.request;

import io.swagger.annotations.ApiModelProperty;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Some javadoc. // OK
 *
 * @author Vuong
 * @since 20/11/2022
 * @deprecated Some javadoc.
 */
@SuppressWarnings("checkstyle:Indentation")
public class SignupRequest {

    @NotNull(message = "This is username")
    @ApiModelProperty(notes = "username, this username must e more than 5 char", example = "adminserver", required = true)
    @Size(min = 5, max = 50, message = "this username must e more than 5 char")
    private String username;

    @NotNull(message = "This is password, this password must e more than 8 char")
    @ApiModelProperty(notes = "password", example = "adminserver", required = true)
    @Size(min = 8, max = 40, message = "this password must e more than 8 char")
    private String password;

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
