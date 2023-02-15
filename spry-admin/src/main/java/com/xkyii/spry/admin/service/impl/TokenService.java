package com.xkyii.spry.admin.service.impl;

import com.xkyii.spry.admin.entity.SysUser;
import com.xkyii.spry.admin.service.ITokenService;
import io.quarkus.runtime.util.StringUtil;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Arrays;
import java.util.HashSet;

@ApplicationScoped
public class TokenService implements ITokenService {

    @Override
    public String generateToken(SysUser user) {
        JwtClaimsBuilder claims = Jwt.claims();
        claims.issuer("https://xkyii.com/issuer");
        claims.upn(StringUtil.isNullOrEmpty(user.getEmail())
            ? (user.getUsername() + "@xkyii.com")
            : user.getEmail());
        claims.groups(new HashSet<>(Arrays.asList("User", "Admin")));
        // 过期时间
        claims.expiresIn(86400);
        return claims.sign();
    }
}
