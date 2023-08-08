package com.xkyii.spry.web.service;

import com.xkyii.spry.web.entity.SysUser;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MockTokenService {

    @Inject
    TokenService tokenService;

    public String generateToken() {
        SysUser user = new SysUser();
        user.setUserName("admin");
        return tokenService.generateToken(user);
    }
}
