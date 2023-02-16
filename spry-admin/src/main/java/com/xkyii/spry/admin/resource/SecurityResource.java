package com.xkyii.spry.admin.resource;

import cn.hutool.crypto.digest.DigestUtil;
import com.xkyii.spry.admin.service.SecureService;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Path("security")
@RolesAllowed("Admin")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
@Tag(description = "应用入口点")
@SuppressWarnings("DuplicatedCode")
public class SecurityResource {

    @Inject
    SecureService secureService;

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
    @Path("rsa-encrypt")
    @PermitAll
    @Operation(summary = "明文转换为密文(RSA)")
    public String rsaEncrypt(@QueryParam("text") String text) {
        return secureService.encrypt(text);
    }

    @POST
    @Path("rsa-decrypt")
    @PermitAll
    @Operation(summary = "密文转换为明文(RSA)")
    public String rsaDecrypt(String password) {
        return secureService.decrypt(password);
    }
    
    @GET
    @Path("bc-hash")
    @PermitAll
    @Operation(summary = "明文转换为密文(BCrypt)")
    public String bcEncrypt(@QueryParam("text") String text, @QueryParam("salt") String salt) {
        StringBuilder sb = new StringBuilder();
        String saltMd5 = DigestUtil.md5Hex16(salt).toUpperCase();
        sb.append("Salt Md5:\n");
        sb.append(saltMd5);
        sb.append("\n\n");
        sb.append("BCrypt Hash:\n");
        sb.append(BcryptUtil.bcryptHash(text, 10, saltMd5.getBytes()));
        return sb.toString();
    }
}
