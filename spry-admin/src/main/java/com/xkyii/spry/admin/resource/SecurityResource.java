package com.xkyii.spry.admin.resource;

import io.smallrye.jwt.util.KeyUtils;
import io.smallrye.jwt.util.ResourceUtils;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
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

    @ConfigProperty(name = "mp.jwt.verify.publickey.location")
    String publicKeyLocation;

    @ConfigProperty(name = "smallrye.jwt.sign.key.location")
    String privateKeyLocation;

    @GET
    @Path("public-key")
    @Operation(summary = "获取公钥信息")
    public String publicKey() throws IOException, GeneralSecurityException {

        StringBuilder sb = new StringBuilder();

        // location
        sb.append("PublicKey Location: ");
        sb.append(publicKeyLocation);
        sb.append("\n\n");

        // pem content
        InputStream keyStream = ResourceUtils.getResourceStream(publicKeyLocation.trim());
        byte[] bytes = ResourceUtils.readBytes(keyStream);
        String keyString = new String(bytes);
        sb.append("PEM content: \n");
        sb.append(keyString);
        sb.append("\n\n");

        // base64 encoded
        PublicKey publicKey = KeyUtils.decodePublicKey(keyString);
        String encoded = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        sb.append("Base64 encoded: \n");
        sb.append(encoded);
        sb.append("\n\n");

        return sb.toString();
    }

    @GET
    @Path("private-key")
    @Operation(summary = "获取私钥信息")
    public String privateKey() throws IOException, GeneralSecurityException {
        StringBuilder sb = new StringBuilder();

        // location
        sb.append("PrivateKey Location: ");
        sb.append(privateKeyLocation);
        sb.append("\n\n");

        // pem content
        InputStream keyStream = ResourceUtils.getResourceStream(privateKeyLocation.trim());
        byte[] bytes = ResourceUtils.readBytes(keyStream);
        String keyString = new String(bytes);
        sb.append("PEM content: \n");
        sb.append(keyString);
        sb.append("\n\n");

        // base64 encoded
        PrivateKey privateKey = KeyUtils.decodePrivateKey(keyString);
        String encoded = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        sb.append("Base64 encoded: \n");
        sb.append(encoded);
        sb.append("\n\n");

        return sb.toString();
    }
}
