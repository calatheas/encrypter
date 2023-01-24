package com.encrypter;

import javax.crypto.SecretKey;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class SymmetricKeyGenerator {
    private static byte[] generateKey(int n) throws NoSuchAlgorithmException {
        javax.crypto.KeyGenerator keyGenerator = javax.crypto.KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        SecretKey key = keyGenerator.generateKey();
        return key.getEncoded();
    }

    public static void generateAndPrintKey(AesType aesType) {
        generateAndPrintKey(aesType.keyLenth);
    }

    public static void generateAndPrintKey(Integer size) {
        try {
            byte[] secretKey = generateKey(size == null ? AesType.AES128.keyLenth : size);
            System.out.println("Base64 key : " + Base64.getEncoder().encodeToString(secretKey));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static void generateAndSaveKey() {
        try {
            // 1. Generate key byte array
            byte[] secretKey = generateKey(128);
            String filepath = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".key";

            // 2. Save key to file (byte -> base64 string(utf-8))
            FileOutputStream fos = new FileOutputStream(filepath);
            fos.write(Base64.getEncoder().encodeToString(secretKey).getBytes(StandardCharsets.UTF_8));
            fos.close();

            // 3. Load and print file (base64 string(utf-8) -> byte)
            String keyEncodedBase64 = Files.readAllLines(Paths.get(filepath), StandardCharsets.UTF_8).get(0);

            System.out.println("Generated " + filepath);
            System.out.println("Base64 String : " + keyEncodedBase64);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
