package com.calatheas.encrypter;

import javax.crypto.Cipher;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class AsymmetricEncrypter {
    private String charsetName = "UTF-8";
    private String algorithm = "RSA";
    private String transformation = "RSA/ECB/PKCS1PADDING";
    private String provider = "SunJCE";
    private final PublicKey publicKey;
    private final PrivateKey privateKey;
    private final Cipher cipher;
    private final Base64.Encoder base64Encoder = Base64.getEncoder();
    private final Base64.Decoder base64Decoder = Base64.getDecoder();

    public AsymmetricEncrypter(String publicKeyBase64Encoded, String privateKeyBase64Encoded) {
        try {
            cipher = Cipher.getInstance(transformation, provider);
            KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
            publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(base64Decoder.decode(publicKeyBase64Encoded)));
            privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(base64Decoder.decode(privateKeyBase64Encoded)));
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create encrypter");
        }
    }

    public String encrypt(String normalString) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return base64Encoder.encodeToString(cipher.doFinal(normalString.getBytes(charsetName)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String decrypt(String encrypted) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(cipher.doFinal(base64Decoder.decode(encrypted)), charsetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
