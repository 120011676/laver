package com.github.laver.example;

import com.github.laver.rsa.util.OpenSSHRSAFile;
import com.github.laver.rsa.util.RSA;

/**
 * Created by say on 8/8/16.
 */
public class RSAFileTest {
    public static void main(String[] args) {
        String v = "abc";
        byte[] bs = RSA.encrypt(v.getBytes(), OpenSSHRSAFile.getPrivateKey("/Users/say/git/laver/laver-examples/src/main/resources/10000"));
        byte[] ws = RSA.decrypt(bs, OpenSSHRSAFile.getPublicKey("/Users/say/git/laver/laver-examples/src/main/resources/10000.pub"));
        System.out.println(new String(ws));
    }
}
