package com.github.laver.example;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by say on 8/3/16.
 */
public class M {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        String m = "中文";
        String password = "m4!23$#@0D#@12an";
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128, new SecureRandom(password.getBytes()));
        SecretKey secretKey = kgen.generateKey();
//            byte[] enCodeFormat = secretKey.getEncoded();
//            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
//        byte[] s = cipher.doFinal(m.getBytes());
        byte[] s = cipher.doFinal(m.getBytes());

       String mb= new String(new Base64().encode(s));
        System.out.println(mb);

        Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, secretKey);
//        byte[] s1 = c.doFinal(s);
        byte[] s1 = c.doFinal(new String(s, "ISO8859-1").getBytes("ISO8859-1"));

        System.out.println(new String(s1));
    }
}
