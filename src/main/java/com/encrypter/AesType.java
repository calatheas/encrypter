package com.encrypter;

public enum AesType {
    AES128(128),
    AES256(256);

    int keyLenth;

    AesType(int keyLenth) {
        this.keyLenth = keyLenth;
    }
}
