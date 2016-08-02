package com.github.laver.aes;

import com.github.laver.core.handle.RequestHandle;

import javax.crypto.Cipher;

/**
 * Created by say on 7/22/16.
 */
public class AESDecodeRequestHandle extends AESHeandle implements RequestHandle {
    public AESDecodeRequestHandle() {
        super(Cipher.DECRYPT_MODE);
    }
}