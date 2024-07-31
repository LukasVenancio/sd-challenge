package com.sd.challenge.infra.utils;

import com.sd.challenge.application.utils.PasswordUtil;
import jakarta.enterprise.context.RequestScoped;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@RequestScoped
public class PasswordUtilImpl implements PasswordUtil {
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;

    public String generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public String encrypt(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), Base64.getDecoder().decode(salt), ITERATIONS, KEY_LENGTH);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return Base64.getEncoder().encodeToString(hash) + ":" + salt;

    }


    public Boolean validate(String encryptedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
       var exploded = encryptedPassword.split(":");
       var hash = this.encrypt(exploded[0], exploded[2]);
       return hash.equals(encryptedPassword);
    }
}
