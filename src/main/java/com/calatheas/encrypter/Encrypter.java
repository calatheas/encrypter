package com.calatheas.encrypter;

public interface Encrypter {
    String encrypt(String normalString);
    String decrypt(String encrypted);
}
