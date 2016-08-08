package com.github.laver.rsa.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.io.pem.PemReader;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Arrays;

public class OpenSSHRSAFile {

    private static KeyFactory KEY_FACTORY = null;

    static {
        Security.addProvider(new BouncyCastleProvider());
        try {
            KEY_FACTORY = KeyFactory.getInstance("RSA", "BC");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static byte[] getContext(File file) {
        try {
            return getContext(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static byte[] getContext(InputStream in) {
        try (PemReader pemReader = new PemReader(new InputStreamReader(in))) {
            return pemReader.readPemObject().getContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static RSAPrivateKey getPrivateKey(String filePath) {
        return getPrivateKey(new File(filePath));
    }

    public static RSAPrivateKey getPrivateKey(File file) {
        try {
            return getPrivateKey(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static RSAPrivateKey getPrivateKey(InputStream in) {
        byte[] bs = getContext(in);
        try {
            return bs != null ? (RSAPrivateKey) KEY_FACTORY.generatePrivate(new PKCS8EncodedKeySpec(bs)) : null;
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static int decodeUInt32(byte[] key, int startIndex) {
        byte[] test = Arrays.copyOfRange(key, startIndex, startIndex + 4);
        return new BigInteger(test).intValue();
    }

    public static RSAPublicKey getPublicKey(String filePath) {
        return getPublicKey(new File(filePath));
    }

    public static RSAPublicKey getPublicKey(File file) {
        try {
            return getPublicKey(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static RSAPublicKey getPublicKey(InputStream in) {
        try (InputStream fout = in; ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            byte[] bs = new byte[8196];
            for (int n; (n = fout.read(bs)) != -1; ) {
                baos.write(bs, 0, n);
            }
            String publicStr = new String(baos.toByteArray());
            byte[] key = new BASE64Decoder().decodeBuffer(publicStr.split(" ")[1]);
            byte[] sshrsa = new byte[]{0, 0, 0, 7, 's', 's', 'h', '-', 'r', 's',
                    'a'};
            int startIndex = sshrsa.length;
            int len = decodeUInt32(key, startIndex);
            startIndex += 4;
            byte[] peb = new byte[len];
            for (int i = 0; i < len; i++) {
                peb[i] = key[startIndex++];
            }
            BigInteger pe = new BigInteger(peb);
            len = decodeUInt32(key, startIndex);
            startIndex += 4;
            byte[] mdb = new byte[len];
            for (int i = 0; i < len; i++) {
                mdb[i] = key[startIndex++];
            }
            BigInteger md = new BigInteger(mdb);
            KeySpec ks = new RSAPublicKeySpec(md, pe);
            return (RSAPublicKey) KEY_FACTORY.generatePublic(ks);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
