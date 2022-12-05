package com.example.onlinemarketbe.payload.request;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
public class SignupRequest {
    @NotNull(message = "This is username")
    @Schema(title = "username, this username must e more than 5 char", example = "adminserver")
    @Size(min = 5, max = 50, message = "this username must e more than 5 char")
    private String username;
    @NotNull(message = "This is password, this password must e more than 8 char")
    @Schema(title = "password", example = "adminserver")
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
