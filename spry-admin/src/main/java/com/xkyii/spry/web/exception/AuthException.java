package com.xkyii.spry.web.exception;

import com.xkyss.quarkus.server.error.ServerException;
import io.quarkus.security.AuthenticationException;

/**
 * 认证异常
 *
 * 实现 io.quarkus.security.AuthenticationException.AuthenticationException 很有必要, 否则不能正常捕捉
 * @see io.quarkus.resteasy.reactive.server.runtime.ResteasyReactiveRecorder#failureHandler
 *
 * @author xkyii
 */
public class AuthException extends ServerException implements AuthenticationException {

    final private String username;

    public AuthException(String username, Integer code) {
        super(code);
        this.username = username;
    }

    public AuthException(String username, Integer code, String message) {
        super(code, message);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
