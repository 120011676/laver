package com.github.laver.core.handle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface RequestHandle {

    byte[] handle(byte[] bs, HttpServletRequest req, HttpServletResponse resp);
}
