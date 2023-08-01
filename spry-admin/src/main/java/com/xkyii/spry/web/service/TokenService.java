package com.xkyii.spry.web.service;

import com.xkyii.spry.web.entity.SysUser;
import io.quarkus.runtime.util.StringUtil;
import jakarta.enterprise.context.ApplicationScoped;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;
import org.eclipse.microprofile.jwt.Claims;

import java.util.Arrays;
import java.util.HashSet;

@ApplicationScoped
public class TokenService {

    /**
     * 生成一个token
     */
    public String generateToken(SysUser user) {
        JwtClaimsBuilder claims = Jwt.claims();
        claims.issuer("https://xkyii.com/issuer");
        claims.upn(user.getUserName());
        claims.claim(Claims.email, StringUtil.isNullOrEmpty(user.getEmail())
            ? (user.getUserName() + "@xkyii.com")
            : user.getEmail());
        claims.groups(new HashSet<>(Arrays.asList("User", "Admin")));
        // 过期时间
        claims.expiresIn(86400);
        return claims.sign();
    }

}
