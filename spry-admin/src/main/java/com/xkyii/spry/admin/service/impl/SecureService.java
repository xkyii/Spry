package com.xkyii.spry.admin.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.xkyii.spry.admin.constant.AdminError;
import com.xkyii.spry.admin.service.ISecureService;
import com.xkyii.spry.common.error.ApiException;
import io.smallrye.jwt.util.KeyUtils;
import io.smallrye.jwt.util.ResourceUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.PublicKey;

@ApplicationScoped
public class SecureService implements ISecureService {

    @Inject
    Logger logger;

    @ConfigProperty(name = "mp.jwt.verify.publickey.location")
    String publicKeyLocation;

    @ConfigProperty(name = "smallrye.jwt.sign.key.location")
    String privateKeyLocation;

    public String decrypt(String password) {
        try {
            PrivateKey privateKey = KeyUtils.readPrivateKey(privateKeyLocation.trim());
            RSA rsa = SecureUtil.rsa(privateKey.getEncoded(), null);
            byte[] decrypt = rsa.decrypt(Base64.decode(password), KeyType.PrivateKey);
            String decryptPassword = StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8);
            logger.info("解出明文: " + decryptPassword);
            return decryptPassword;

        } catch (Exception e) {
            throw new ApiException(AdminError.解密失败);
        }
    }

    public String encrypt(String plain) {
        try {
            PublicKey publicKey = KeyUtils.readPublicKey(publicKeyLocation.trim());
            RSA rsa = SecureUtil.rsa(null, publicKey.getEncoded());
            byte[] encrypt = rsa.encrypt(plain, KeyType.PublicKey);
            String encryptString = StrUtil.str(Base64.encode(encrypt), CharsetUtil.CHARSET_UTF_8);
            return encryptString;
        }
        catch (Exception e) {
            throw new ApiException(AdminError.加密失败);
        }
    }

    public String publicKeyInfo() throws IOException, GeneralSecurityException {

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
        String encoded = java.util.Base64.getEncoder().encodeToString(publicKey.getEncoded());
        sb.append("Base64 encoded: \n");
        sb.append(encoded);
        sb.append("\n\n");

        return sb.toString();
    }

    public String privateKeyInfo() throws IOException, GeneralSecurityException {
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
        String encoded = java.util.Base64.getEncoder().encodeToString(privateKey.getEncoded());
        sb.append("Base64 encoded: \n");
        sb.append(encoded);
        sb.append("\n\n");

        return sb.toString();
    }
}
