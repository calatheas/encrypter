package com.encrypter;

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

public class KeyGenerator {
    static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    static String byteArrayToHexString(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for(byte b : bytes){
            sb.append(String.format("%02x", b&0xff));
        }
        return sb.toString();
    }

    private static byte[] generateKey(int n) throws NoSuchAlgorithmException {
        javax.crypto.KeyGenerator keyGenerator = javax.crypto.KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        SecretKey key = keyGenerator.generateKey();
        return key.getEncoded();
    }

    public static void generateAndPrintKey() {
        try {
            // 1. Generate key byte array
            byte[] secretKey = generateKey(128);
            String filepath = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".key";

            // 2. Save key to file (byte -> hex string(utf-8))
            FileOutputStream fos = new FileOutputStream(filepath);
            fos.write(byteArrayToHexString(secretKey).getBytes(StandardCharsets.UTF_8));
            fos.close();

            // 3. Load and print file (hex string(utf-8) -> byte)
            String hexString = Files.readAllLines(Paths.get(filepath), StandardCharsets.UTF_8).get(0);

            System.out.println("Generated "+filepath);
            System.out.println("hexString : "+hexString);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static KeyPair _generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        return generator.generateKeyPair();
    }

    public static void generateKeyPair() {
        try {
            KeyPair kp = _generateKeyPair();
            String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

            FileOutputStream fos = new FileOutputStream(now+"-public.key");
            fos.write(byteArrayToHexString(kp.getPublic().getEncoded()).getBytes(StandardCharsets.UTF_8));

            FileOutputStream fos2 = new FileOutputStream(now+"-private.key");
            fos2.write(byteArrayToHexString(kp.getPrivate().getEncoded()).getBytes(StandardCharsets.UTF_8));

            fos.close();
            fos2.close();
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }
}
