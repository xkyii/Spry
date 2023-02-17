package com.xkyii.spry.admin.dto.user.login;


import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Token出参")
public class LoginDto {

    private String token;

    public LoginDto() {

    }

    public LoginDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
