package com.github.laver.core.util;

import com.github.laver.core.config.LaverConfig;
import com.github.laver.core.config.LaverConfigImpl;
import com.github.laver.core.handle.HandleType;
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
        return this.init(new LaverConfigImpl(servletConfig));
    }

    public List<ResponseHandle> init(FilterConfig filterConfig) {
        return this.init(new LaverConfigImpl(filterConfig));
    }

    public List<ResponseHandle> init(LaverConfig laverConfig) {
        String responseHandlesStr = laverConfig.getInitParameter("response");
        if (responseHandlesStr != null && !"".equals(responseHandlesStr.trim())) {
            List<ResponseHandle> responseHandles = new ArrayList<>();
            String[] rhs = responseHandlesStr.split(",");
            for (String rh : rhs) {
                try {
                    ResponseHandle o = (ResponseHandle) Class.forName(rh.trim()).newInstance();
                    o.init(laverConfig);
                    responseHandles.add(o);
                } catch (Exception e) {
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
                    bs = responseHandle.handle(bs, req, responseWrapper, HandleType.BYTE);
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
            byte[] valueBytes = value.getBytes();
            if (responseHandles != null) {
                for (ResponseHandle responseHandle : responseHandles) {
                    valueBytes = responseHandle.handle(valueBytes, req, responseWrapper, HandleType.CHARACTER);
                }
            }
            if (valueBytes != null) {
                try (Writer writer = resp.getWriter()) {
                    writer.write(new String(valueBytes));
                }
            }
        }
    }

    public void destroy(List<ResponseHandle> responseHandles) {
        if (responseHandles != null && responseHandles.size() > 0) {
            for (ResponseHandle o : responseHandles) {
                o.destroy();
            }
        }
    }
}
