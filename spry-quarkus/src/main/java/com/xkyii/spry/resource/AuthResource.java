package com.xkyii.spry.resource;

import com.xkyii.spry.domain.auth.LoginDto;
import com.xkyii.spry.domain.auth.LoginReq;
import com.xkyii.spry.entity.User;
import com.xkyii.spry.repository.UserRepository;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.jwt.Claims;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Path("/auth")
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

    String token(User user) {
        JwtClaimsBuilder claims = Jwt.claims();
        claims.issuer("https://xkyii.com/issuer");
        claims.upn(user.getUsername());
        claims.claim(Claims.email, user.getEmail());
        claims.claim(Claims.jti, UUID.randomUUID().toString());
        // TODO: roles
        claims.groups(Set.of("permission_1", "permission_2"));
        claims.expiresIn(86400);
        return claims.sign();
    }
}
