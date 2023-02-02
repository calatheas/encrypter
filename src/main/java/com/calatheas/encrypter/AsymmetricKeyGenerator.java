package com.calatheas.encrypter;

import javax.crypto.SecretKey;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class AsymmetricKeyGenerator {
    private static KeyPair _generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        return generator.generateKeyPair();
    }

    public static void generateAndPrintKey() throws NoSuchAlgorithmException {
        KeyPair kp = _generateKeyPair();
        System.out.println(String.format("public.key - %s", Base64.getEncoder().encodeToString(kp.getPublic().getEncoded())));
        System.out.println(String.format("private.key - %s", Base64.getEncoder().encodeToString(kp.getPrivate().getEncoded())));
    }

    public static void generateAndSaveKey() {
        try {
            KeyPair kp = _generateKeyPair();
            String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

            FileOutputStream fos = new FileOutputStream(now+"-public.key");
            fos.write(Base64.getEncoder().encodeToString(kp.getPublic().getEncoded()).getBytes(StandardCharsets.UTF_8));

            FileOutputStream fos2 = new FileOutputStream(now+"-private.key");
            fos2.write(Base64.getEncoder().encodeToString(kp.getPrivate().getEncoded()).getBytes(StandardCharsets.UTF_8));

            fos.close();
            fos2.close();
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }
}
