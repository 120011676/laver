package com.github.laver.core.handle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ResponseHandle {

    byte[] handle(byte[] bs, HttpServletRequest req, HttpServletResponse resp);

    char[] handle(char[] chars, HttpServletRequest req, HttpServletResponse resp);
}
