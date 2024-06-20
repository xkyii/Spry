package com.xkyii.spry.resource;

import com.xkyii.spry.dto.auth.LoginDto;
import com.xkyii.spry.dto.auth.LoginReq;
import com.xkyii.spry.entity.User;
import com.xkyii.spry.repository.UserRepository;
import io.quarkus.security.Authenticated;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.jwt.Claims;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Path("/api/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    UserRepository userRepository;

    @POST
    @Path("login")
    public LoginDto login(LoginReq req) {
        User user = userRepository.findByEmail(req.getEmail());
        if (user == null) {
            return null;
        }

        if (Objects.equals(user.getPassword(), req.getPassword())) {
            LoginDto dto = new LoginDto();
            dto.setUsername(user.getUsername());
            dto.setToken(token(user));
            return dto;
        }

        return null;
    }

    @GET
    @RolesAllowed("admin")
    @Path("hello")
    public String hello() {
        return "hello";
    }

    @GET
    @DenyAll
    @Path("deny")
    public String deny() {
        return "deny";
    }

    @GET
    @PermitAll
    @Path("permit")
    public String permit() {
        return "permit";
    }

    String token(User user) {
        JwtClaimsBuilder claims = Jwt.claims();
        claims.issuer("https://xkyii.com/issuer");
        claims.upn(user.getUsername());
        claims.claim(Claims.email, user.getEmail());
        claims.claim(Claims.jti, UUID.randomUUID().toString());
        claims.groups(userRepository.queryRoleCodes(user.getId()));
        claims.expiresIn(86400);
        return claims.sign();
    }
}
