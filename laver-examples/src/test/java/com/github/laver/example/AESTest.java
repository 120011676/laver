package com.github.laver.example;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by say on 8/2/16.
 */
public class AESTest {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String m = "dMObw7E5PcK3woJawpsWfCXCqcKDw4zDkcKGTnjCscOYwoYYw63DmzDCoGAnIgLDhXHCo8OFw7XCgcKKFU7CpnNgIMOew71Gw6g=";
        String password = "m4!23$#@0D#@12an";
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128, new SecureRandom(password.getBytes()));
        SecretKey secretKey = kgen.generateKey();
//            byte[] enCodeFormat = secretKey.getEncoded();
//            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] s = cipher.doFinal(new Base64().decode(m));
        System.out.println(new String(s));
    }
}
