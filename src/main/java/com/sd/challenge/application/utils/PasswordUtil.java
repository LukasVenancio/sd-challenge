package com.sd.challenge.application.utils;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface PasswordUtil {
    String generateSalt();
    String encrypt(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException;
    Boolean validate(String encryptedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException;
}
