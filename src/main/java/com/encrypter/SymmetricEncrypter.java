package com.encrypter;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import java.util.Base64;

public class SymmetricEncrypter {
    private String charsetName = "UTF-8";
    private String algorithm = "AES";
    private String transformation = "AES/CBC/PKCS5Padding";
    private final SecretKeySpec secretKey;
    private final AlgorithmParameterSpec initializationVector;
    private final Cipher cipher;
    private final Base64.Encoder base64Encoder = Base64.getEncoder();
    private final Base64.Decoder base64Decoder = Base64.getDecoder();

    public SymmetricEncrypter(String keyEncodedBase64) {
        byte[] key = base64Decoder.decode(keyEncodedBase64);

        secretKey = new SecretKeySpec(key, algorithm);
        initializationVector = new IvParameterSpec(Arrays.copyOfRange(key, 0, 16));

        try {
            cipher = Cipher.getInstance(transformation);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create encrypter");
        }
    }

    public String encrypt(String normalString) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, initializationVector);
            return base64Encoder.encodeToString(cipher.doFinal(normalString.getBytes(charsetName)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String decrypt(String encrypted) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKey, initializationVector);
            return new String(cipher.doFinal(base64Decoder.decode(encrypted)), charsetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
