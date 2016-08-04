package com.github.laver.aes.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by say on 8/4/16.
 */
public class AES {

    public static byte[] encrypt(byte[] content, String password) {
        return aes(content, password, Cipher.ENCRYPT_MODE);
    }

    public static byte[] decrypt(byte[] content, String password) {
        return aes(content, password, Cipher.DECRYPT_MODE);
    }

    private static byte[] aes(byte[] content, String password, int mode) {
        try {
            SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(mode, key);
            return cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
