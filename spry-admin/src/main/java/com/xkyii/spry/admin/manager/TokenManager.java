package com.xkyii.spry.admin.manager;

import com.xkyii.spry.admin.entity.SysUser;
import io.quarkus.runtime.util.StringUtil;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.jwt.Claims;

import java.util.Arrays;
import java.util.HashSet;

@ApplicationScoped
public class TokenManager {

    public String generateToken(SysUser user) {
        JwtClaimsBuilder claims = Jwt.claims();
        claims.issuer("https://xkyii.com/issuer");
        claims.upn(user.getUsername());
        claims.claim(Claims.email, StringUtil.isNullOrEmpty(user.getEmail())
                ? (user.getUsername() + "@xkyii.com")
                : user.getEmail());
        claims.groups(new HashSet<>(Arrays.asList("User", "Admin")));
        // 过期时间
        claims.expiresIn(86400);
        return claims.sign();
    }
}
