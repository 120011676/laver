package com.github.laver.aes;

import com.github.laver.core.handle.ResponseHandle;

import javax.crypto.Cipher;

/**
 * Created by say on 7/22/16.
 */
public class AESEncodeResponseHandle extends AESHeandle implements ResponseHandle {
    public AESEncodeResponseHandle() {
        super(Cipher.ENCRYPT_MODE);
    }
}
