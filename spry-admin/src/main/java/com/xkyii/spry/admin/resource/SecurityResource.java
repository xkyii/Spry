package com.xkyii.spry.admin.resource;

import com.xkyii.spry.admin.service.ISecureService;
import io.smallrye.jwt.util.KeyUtils;
import io.smallrye.jwt.util.ResourceUtils;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

@Path("security")
@RolesAllowed("Admin")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
@Tag(description = "应用入口点")
@SuppressWarnings("DuplicatedCode")
public class SecurityResource {

    @Inject
    ISecureService secureService;

    @GET
    @Path("public-key")
    @Operation(summary = "获取公钥信息")
    public String publicKey() throws IOException, GeneralSecurityException {
        return secureService.publicKeyInfo();
    }

    @GET
    @Path("private-key")
    @Operation(summary = "获取私钥信息")
    public String privateKey() throws IOException, GeneralSecurityException {
        return secureService.privateKeyInfo();
    }

    @GET
    @Path("encrypt")
    @Operation(summary = "明文转换为密文")
    public String encrypt(@QueryParam("text") String text) {
        return secureService.encrypt(text);
    }

    @POST
    @Path("decrypt")
    @Operation(summary = "密文转换为明文")
    public String decrypt(String password) {
        return secureService.decrypt(password);
    }
}
