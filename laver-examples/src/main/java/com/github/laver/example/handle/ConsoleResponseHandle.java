package com.github.laver.example.handle;

import com.github.laver.core.config.LaverConfig;
import com.github.laver.core.handle.ResponseHandle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * Created by say on 7/22/16.
 */
public class ConsoleResponseHandle implements ResponseHandle {
    @Override
    public void init(LaverConfig laverConfig) throws ServletException {

    }

    @Override
    public byte[] handle(byte[] bs, HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("===================bs===================");
        System.out.println(Arrays.toString(bs));
        return bs;
    }

    @Override
    public String handle(String value, HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("===================chars===================");
        System.out.println(value);
        return value;
    }

    @Override
    public void destroy() {

    }
}
