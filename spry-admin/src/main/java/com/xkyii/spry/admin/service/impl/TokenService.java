package com.xkyii.spry.admin.service.impl;

import com.xkyii.spry.admin.entity.SysUser;
import com.xkyii.spry.admin.service.ITokenService;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Arrays;
import java.util.HashSet;

@ApplicationScoped
public class TokenService implements ITokenService {

    @Override
    public String generateToken(SysUser user) {
        return Jwt.claims()
                .issuer("https://xkyii.com/issuer")
                .groups(new HashSet<>(Arrays.asList("User", "Admin")))
                .expiresIn(86400)
                .sign();
    }
}
