package com.github.laver.base64.handle;

import com.github.laver.core.config.LaverConfig;
import com.github.laver.core.handle.RequestHandle;
import org.apache.commons.codec.binary.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by say on 7/22/16.
 */
public class Base64DecodeRequestHandle implements RequestHandle {
    @Override
    public void init(LaverConfig laverConfig) throws ServletException {

    }

    @Override
    public byte[] handle(byte[] bs, HttpServletRequest req, HttpServletResponse resp) {
       return new Base64().decode(bs);
    }

    @Override
    public void destroy() {

    }
}