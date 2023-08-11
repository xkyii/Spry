package com.xkyii.spry.web.exception;

import com.xkyss.quarkus.server.error.ServerException;

public class LoginException extends ServerException {

    final private String username;

    public LoginException(String username, Integer code) {
        super(code);
        this.username = username;
    }

    public LoginException(String username, Integer code, String message) {
        super(code, message);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
