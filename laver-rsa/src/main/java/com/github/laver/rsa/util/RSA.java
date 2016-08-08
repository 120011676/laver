package com.github.laver.rsa.util;

import javax.crypto.Cipher;
import java.security.Key;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * Created by say on 8/8/16.
 */
public class RSA {
    public static byte[] decrypt(byte[] bs, RSAPrivateKey privateKey) {
        return rsa(bs, privateKey, Cipher.DECRYPT_MODE);
    }

    public static byte[] encrypt(byte[] bs, RSAPrivateKey privateKey) {
        return rsa(bs, privateKey, Cipher.ENCRYPT_MODE);
    }

    public static byte[] decrypt(byte[] bs, RSAPublicKey privateKey) {
        return rsa(bs, privateKey, Cipher.DECRYPT_MODE);
    }

    public static byte[] encrypt(byte[] bs, RSAPublicKey privateKey) {
        return rsa(bs, privateKey, Cipher.ENCRYPT_MODE);
    }

    private static byte[] rsa(byte[] bs, Key key, int mode) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(mode, key);
            return cipher.doFinal(bs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
