package com.github.laver.core.handle;

import com.github.laver.core.config.LaverConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ResponseHandle {

    void init(LaverConfig laverConfig) throws ServletException;

    byte[] handle(byte[] bs, HttpServletRequest req, HttpServletResponse resp);

    String handle(String value, HttpServletRequest req, HttpServletResponse resp);

    void destroy();
}
