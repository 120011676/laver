package com.github.laver.rsa.handle;

import com.github.laver.core.config.LaverConfig;
import com.github.laver.core.handle.RequestHandle;
import com.github.laver.core.handle.ResponseHandle;
import com.github.laver.exception.exception.LaverMessageRuntimeException;
import com.github.laver.rsa.util.OpenSSHRSAFile;
import com.github.laver.rsa.util.RSA;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
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
            String serverPrivateKeyFilePath = laverConfig.getInitParameter("serverPrivateKeyFilePath");
            InputStream in = laverConfig.getServletContext().getResourceAsStream(serverPrivateKeyFilePath);
            if (in == null) {
                throw new LaverMessageRuntimeException("rsa_server_private_error", serverPrivateKeyFilePath);
            }
            serverRSAPrivateKey = OpenSSHRSAFile.getPrivateKey(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] handle(byte[] bs, HttpServletRequest req, HttpServletResponse resp, String type) {
        try {
            String appkey = req.getParameter(this.appKeyName);
            if (appkey == null || "".equals(appkey)) {
                throw new LaverMessageRuntimeException("laver_appkey_error");
            }
            String filepath = (this.keysPath.endsWith(File.separator) ? this.keysPath : this.keysPath + File.separator) + appkey + ".pub";
            InputStream in = req.getServletContext().getResourceAsStream(filepath);
            if (in == null) {
                throw new LaverMessageRuntimeException("laver_appkey_file_error", appkey, filepath);
            }
            return RSA.encrypt(bs, OpenSSHRSAFile.getPublicKey(in));
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
