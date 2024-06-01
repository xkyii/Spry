package com.xkyii.spry.resource;

import com.xkyii.spry.domain.LoginReq;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;
import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.jwt.Claims;
import org.jboss.resteasy.reactive.RestQuery;

import java.util.Set;
import java.util.UUID;

@Path("/auth")
public class AuthResource {

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String login(LoginReq req) {
        return JsonObject.of()
            .put("name", "Jhon")
            .put("email", "jhon@example.com")
            .put("password", "password")
            .put("token", token("Jhon", "jhon@example.com"))
            .toString();
    }

    String token(String username, String email) {
        JwtClaimsBuilder claims = Jwt.claims();
        claims.issuer("https://xkyii.com/issuer");
        claims.upn(username);
        claims.claim(Claims.email, email);
        claims.claim(Claims.jti, UUID.randomUUID().toString());
        claims.groups(Set.of("permission_1", "permission_2"));
        // 过期时间
        claims.expiresIn(86400);
        return claims.sign();
    }
}
