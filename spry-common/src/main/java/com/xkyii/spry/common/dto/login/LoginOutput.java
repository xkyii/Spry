package com.xkyii.spry.common.dto.login;


import jakarta.validation.constraints.NotBlank;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Token出参")
public class LoginOutput {

    @NotBlank
    private String token;

    public LoginOutput() {

    }

    public LoginOutput(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
