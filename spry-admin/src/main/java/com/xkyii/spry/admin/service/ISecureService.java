package com.xkyii.spry.admin.service;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface ISecureService {
    String decrypt(String password);

    String encrypt(String plain);

    String publicKeyInfo() throws IOException, GeneralSecurityException;

    String privateKeyInfo() throws IOException, GeneralSecurityException;
}
