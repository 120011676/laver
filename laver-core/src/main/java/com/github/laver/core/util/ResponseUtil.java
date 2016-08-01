package com.github.laver.core.util;

import com.github.laver.core.handle.ResponseHandle;
import com.github.laver.core.wrapper.LaverServletResponseWrapper;

import javax.servlet.FilterConfig;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by say on 7/26/16.
 */
public class ResponseUtil {

    public List<ResponseHandle> init(ServletConfig servletConfig) {
        return this.init(servletConfig.getInitParameter("response"));
    }

    public List<ResponseHandle> init(FilterConfig filterConfig) {
        return this.init(filterConfig.getInitParameter("response"));
    }

    public List<ResponseHandle> init(String responseHandlesStr) {
        if (responseHandlesStr != null && !"".equals(responseHandlesStr.trim())) {
            List<ResponseHandle> responseHandles = new ArrayList<>();
            String[] rhs = responseHandlesStr.split(",");
            for (String rh : rhs) {
                try {
                    responseHandles.add((ResponseHandle) Class.forName(rh.trim()).newInstance());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return responseHandles;
        }
        return null;
    }

    public static void send(List<ResponseHandle> responseHandles, LaverServletResponseWrapper responseWrapper, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        byte[] bs = responseWrapper.toOutputStream();
        if (bs != null && bs.length > 0) {
            if (responseHandles != null) {
                for (ResponseHandle responseHandle : responseHandles) {
                    bs = responseHandle.handle(bs, req, responseWrapper);
                }
            }
            if (bs != null) {
                try (OutputStream out = resp.getOutputStream()) {
                    out.write(bs);
                }
            }
        }
        String value = responseWrapper.toWriter();
        if (value != null && value.length() > 0) {
            if (responseHandles != null) {
                for (ResponseHandle responseHandle : responseHandles) {
                    value = responseHandle.handle(value, req, responseWrapper);
                }
            }
            if (value != null) {
                try (Writer writer = resp.getWriter()) {
                    writer.write(value);
                }
            }
        }
    }
}
