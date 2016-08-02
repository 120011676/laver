package com.github.laver.base64.handle;

import com.github.laver.core.config.LaverConfig;
import com.github.laver.core.handle.ResponseHandle;
import org.apache.commons.codec.binary.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by say on 7/22/16.
 */
public class Base64EncodeResponseHandle implements ResponseHandle {
    @Override
    public void init(LaverConfig laverConfig) throws ServletException {

    }

    @Override
    public byte[] handle(byte[] bs, HttpServletRequest req, HttpServletResponse resp) {
        return new Base64().encode(bs);
    }

    @Override
    public String handle(String value, HttpServletRequest req, HttpServletResponse resp) {
        return new Base64().encodeToString(value.getBytes());
    }

    @Override
    public void destroy() {

    }
}
