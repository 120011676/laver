package com.github.laver.example;

import com.github.laver.aes.util.AES;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by say on 8/2/16.
 */
public class AESTest {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String v = "4qWicnsRBPKQiRpzMliw33di2dgGhr9bwr+USZagXu1qhJYJj7D9kdPxIlR1B6zo";
        String p = "m4!23$#@0D#@12an";
        byte[] bs = new Base64().decode(v.getBytes());
        byte[] dbs = AES.decrypt(bs, p);
        System.out.println(new String(dbs));;
    }
}
