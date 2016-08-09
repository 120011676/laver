package com.github.laver.rsa.handle;

import com.github.laver.core.config.LaverConfig;
import com.github.laver.core.handle.RequestHandle;
import com.github.laver.core.handle.ResponseHandle;
import com.github.laver.exception.exception.LaverRuntimeException;
import com.github.laver.rsa.util.OpenSSHRSAFile;
import com.github.laver.rsa.util.RSA;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.security.interfaces.RSAPrivateKey;

public class RSAHeandle implements RequestHandle, ResponseHandle {

    private String appKeyName = "appKey";
    private String keysPath;
    private RSAPrivateKey serverRSAPrivateKey;

    @Override
    public void init(LaverConfig laverConfig) throws ServletException {
        String appKayName = laverConfig.getInitParameter("appKayName");
        if (appKayName != null && !"".equals(appKayName)) {
            appKeyName = appKayName;
        }
        keysPath = laverConfig.getInitParameter("keysPath");
        try {
            serverRSAPrivateKey = OpenSSHRSAFile.getPrivateKey(laverConfig.getServletContext().getResourceAsStream(laverConfig.getInitParameter("serverPrivateKeyFilePath")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] handle(byte[] bs, HttpServletRequest req, HttpServletResponse resp, String type) {
        try {
            String appkey = req.getParameter(this.appKeyName);
            if (appkey == null || "".equals(appkey)) {
                throw new LaverRuntimeException("rsa_appkey_error", " appkey parameter not exists.");
            }
            return RSA.encrypt(bs, OpenSSHRSAFile.getPublicKey(req.getServletContext().getResourceAsStream((this.keysPath.endsWith(File.separator) ? this.keysPath : this.keysPath + File.separator) + appkey + ".pub")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bs;
    }

    @Override
    public byte[] handle(byte[] bs, HttpServletRequest req, HttpServletResponse resp) {
        try {
            return RSA.decrypt(bs, this.serverRSAPrivateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bs;
    }

    @Override
    public void destroy() {

    }
}
