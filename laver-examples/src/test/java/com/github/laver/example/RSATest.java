package com.github.laver.example;

import com.github.laver.aes.util.AES;
import com.github.laver.rsa.util.OpenSSHRSAFile;
import com.github.laver.rsa.util.RSA;
import org.apache.commons.codec.binary.Base64;

/**
 * Created by say on 8/8/16.
 */
public class RSATest {
    public static void main(String[] args) throws Exception {
        String m = "i/LpKh1vVtMAE2f5MRie+47QioSH1rWQxBVp+TqQGHgzeWVXzl7ubkn+cWl1iqrhfCOLON/nCtHKUKNDLFtVTJpl4Bdm6nQb0mM//Nb5YKvmQ+v9kozGP8AA6AK3kLXKl2HMVqQS9rHw50Coh4AH3aUZXDh4oLL8S+mo2DisxPS1XTYFIU2WAr8+R8URxWkrTwY6oZ35/dcVTn5ZuK372xAsK1qwNe+qLAiCQRq9aasY10HxPwyXBSkuf2C5LmjbD4WCO6pBygknbI2ysWVJ+KHOtt4kxvEv8XCUWCgIuvlaWQ3eiujzV/ybWylbeBzM8I8EvHSrx399Q4qEOjYUdQ==";
        byte[] bs = new Base64().decode(m);
        byte[] ws = RSA.decrypt(bs, OpenSSHRSAFile.getPrivateKey("/Users/say/git/laver/laver-examples/src/main/resources/10000"));

        String p = "m4!23$#@0D#@12an";
        byte[] dbs = AES.decrypt(ws, p);
        System.out.println(new String(dbs));
    }
}
