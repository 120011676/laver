package com.github.laver.aes;

import com.github.laver.aes.util.FileUtil;
import com.github.laver.core.config.LaverConfig;
import com.github.laver.core.handle.RequestHandle;
import com.github.laver.core.handle.ResponseHandle;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SecureRandom;

/**
 * Created by say on 8/2/16.
 */
public class AESHeandle implements RequestHandle, ResponseHandle {
    private static String APP_KEY = "appKey";
    private static Integer KEY_SIZE;
    private static String KEYS_PATH;
    private int cipherMode;

    public AESHeandle(int cipherMode) {
        this.cipherMode = cipherMode;
    }

    @Override
    public void init(LaverConfig laverConfig) throws ServletException {
        String appKayName = laverConfig.getInitParameter("appKayName");
        if (appKayName != null && !"".equals(appKayName)) {
            APP_KEY = appKayName;
        }
        KEY_SIZE = Integer.parseInt(laverConfig.getInitParameter("keySize"));
        KEYS_PATH = laverConfig.getInitParameter("keysPath");
    }

    @Override
    public byte[] handle(byte[] bs, HttpServletRequest req, HttpServletResponse resp) {
        String appKey = req.getParameter(APP_KEY);
        String handKey = req.getHeader(APP_KEY);
        if (handKey != null && !"".equals(handKey)) {
            appKey = handKey;
        }
        String password = FileUtil.read(req.getServletContext().getResourceAsStream(KEYS_PATH + appKey + ".aes"));
        if (password == null) {
            throw new RuntimeException("file path '" + KEYS_PATH + appKey + ".aes' not exists.");
        }
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(KEY_SIZE, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
//            byte[] enCodeFormat = secretKey.getEncoded();
//            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(cipherMode, secretKey);
            return cipher.doFinal(bs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bs;
    }

    @Override
    public String handle(String value, HttpServletRequest req, HttpServletResponse resp) {
        return new String(this.handle(value.getBytes(), req, resp));
    }

    @Override
    public void destroy() {

    }
}
