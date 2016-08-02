package com.github.laver.sign.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by say on 8/2/16.
 */
public class Sign {

    public static byte[] md5(byte[] bs) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance("MD5").digest(bs);
    }

    public static byte[] sha1(byte[] bs) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance("SHA-1").digest(bs);
    }

    public static String toHex(byte[] bs) {
        if (bs != null && bs.length > 0) {
            StringBuilder result = new StringBuilder();
            for (byte b : bs) {
                String r = Integer.toHexString(b & 0xFF);
                if (r.length() == 1) {
                    result.append("0");
                }
                result.append(r);
            }
            return result.toString();
        }
        return null;
    }
}
