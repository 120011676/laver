package com.github.laver.example.handle;

import com.github.laver.core.config.LaverConfig;
import com.github.laver.core.handle.RequestHandle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by say on 7/22/16.
 */
public class ConsoleRequestHandle implements RequestHandle {
    @Override
    public void init(LaverConfig laverConfig) throws ServletException {

    }

    @Override
    public byte[] handle(byte[] bs, HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("===================request===================");
        System.out.println(new String(bs));
        return bs;
    }

    @Override
    public void destroy() {

    }
}
