package com.github.laver.aes;

import com.github.laver.aes.util.AES;
import com.github.laver.aes.util.FileUtil;
import com.github.laver.core.config.LaverConfig;
import com.github.laver.core.handle.RequestHandle;
import com.github.laver.core.handle.ResponseHandle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * Created by say on 8/2/16.
 */
public class AESHeandle implements RequestHandle, ResponseHandle {
    private String appKey = "appKey";
    private String keysPath;

    @Override
    public void init(LaverConfig laverConfig) throws ServletException {
        String appKayName = laverConfig.getInitParameter("appKayName");
        if (appKayName != null && !"".equals(appKayName)) {
            appKey = appKayName;
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
        return FileUtil.read(req.getServletContext().getResourceAsStream((this.keysPath.endsWith(File.separator) ? this.keysPath : this.keysPath + File.separator) + req.getParameter(this.appKey) + ".aes"));
    }

    @Override
    public void destroy() {

    }

}
