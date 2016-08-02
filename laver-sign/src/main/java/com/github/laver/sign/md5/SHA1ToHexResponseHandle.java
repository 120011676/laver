package com.github.laver.sign.md5;

import com.github.laver.core.config.LaverConfig;
import com.github.laver.core.handle.ResponseHandle;
import com.github.laver.sign.util.Sign;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;

/**
 * Created by say on 8/2/16.
 */
public class SHA1ToHexResponseHandle implements ResponseHandle {
    @Override
    public void init(LaverConfig laverConfig) throws ServletException {

    }

    @Override
    public byte[] handle(byte[] bs, HttpServletRequest req, HttpServletResponse resp) {
        this.sign(bs, resp);
        return bs;
    }

    @Override
    public String handle(String value, HttpServletRequest req, HttpServletResponse resp) {
        this.sign(value.getBytes(), resp);
        return value;
    }

    @Override
    public void destroy() {

    }

    private void sign(byte[] bs, HttpServletResponse resp) {
        try {
            resp.setHeader("sha1", Sign.toHex(Sign.sha1(bs)));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
