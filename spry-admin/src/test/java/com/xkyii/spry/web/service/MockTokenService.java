package com.xkyii.spry.web.service;

import com.xkyii.spry.web.entity.SysUser;
import com.xkyii.spry.web.model.LoginUser;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Set;

@ApplicationScoped
public class MockTokenService {

    @Inject
    TokenService tokenService;

    public String generateToken() {
        SysUser user = new SysUser();
        user.setUserName("admin");
        LoginUser loginUser = new LoginUser(user).withPermissions(Set.of("User", "Admin"));
        return tokenService.generateToken(loginUser);
    }
}
