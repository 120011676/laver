package com.github.laver.aes;

import com.github.laver.aes.util.AES;
import com.github.laver.aes.util.FileUtil;
import com.github.laver.core.config.LaverConfig;
import com.github.laver.core.handle.RequestHandle;
import com.github.laver.core.handle.ResponseHandle;
import com.github.laver.exception.exception.LaverRuntimeException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;

class AESHeandle implements RequestHandle, ResponseHandle {
    private String appKeyName = "appKey";
    private String keysPath;

    @Override
    public void init(LaverConfig laverConfig) throws ServletException {
        String appKayName = laverConfig.getInitParameter("appKayName");
        if (appKayName != null && !"".equals(appKayName)) {
            appKeyName = appKayName;
        }
        keysPath = laverConfig.getInitParameter("keysPath");
    }

    @Override
    public byte[] handle(byte[] bs, HttpServletRequest req, HttpServletResponse resp) {
        return AES.decrypt(bs, this.getPassword(req));
    }

    @Override
    public byte[] handle(byte[] bs, HttpServletRequest req, HttpServletResponse resp, String type) {
        return AES.encrypt(bs, this.getPassword(req));
    }

    private String getPassword(HttpServletRequest req) {
        String appKey = req.getParameter(this.appKeyName);
        if (appKey == null) {
            throw new LaverRuntimeException("laver_appkey_error", "appkey parameter not exists.");
        }
        String filepath = (this.keysPath.endsWith(File.separator) ? this.keysPath : this.keysPath + File.separator) + appKey + ".aes";
        InputStream in = req.getServletContext().getResourceAsStream(filepath);
        if (in == null) {
            throw new LaverRuntimeException("laver_appkey_error", "appkey '" + appKey + "' not exists,Can not find the '" + filepath + "' file.");
        }
        return FileUtil.read(in);
    }

    @Override
    public void destroy() {

    }

}
