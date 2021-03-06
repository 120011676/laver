package com.github.laver.exception.servlet;

import com.github.laver.core.handle.HandleType;
import com.github.laver.core.handle.ResponseHandle;
import com.github.laver.core.util.ResponseUtil;
import com.github.laver.exception.entity.ExceptionEntity;
import com.github.laver.exception.handle.ExceptionHandle;
import com.github.laver.exception.uitl.ExceptionProperties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by say on 7/28/16.
 */
public class ExceptionServlet extends HttpServlet {
    private ExceptionHandle exceptionHandle;
    private List<ResponseHandle> responseHandles;
    private String message = "exception";

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            String msg = config.getInitParameter("message");
            if (msg != null) {
                this.message = msg;
            }
            ExceptionProperties.read(this.message);
            this.exceptionHandle = (ExceptionHandle) Class.forName(config.getInitParameter("exception")).newInstance();
            this.responseHandles = new ResponseUtil().init(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ExceptionEntity ee = new ExceptionEntity();
        ee.setForwardRequestUri((String) req.getAttribute("javax.servlet.forward.request_uri"));
        ee.setContextPath((String) req.getAttribute("javax.servlet.forward.context_path"));
        ee.setServletPath((String) req.getAttribute("javax.servlet.forward.servlet_path"));
        ee.setStatusCode((Integer) req.getAttribute("javax.servlet.error.status_code"));
        ee.setExceptionType((Class) req.getAttribute("javax.servlet.error.exception_type"));
        ee.setMessage((String) req.getAttribute("javax.servlet.error.message"));
        ee.setServletName((String) req.getAttribute("javax.servlet.error.servlet_name"));
        ee.setErrorRequestUri((String) req.getAttribute("javax.servlet.error.request_uri"));
        ee.setException((Throwable) req.getAttribute("javax.servlet.error.exception"));
        String result = this.exceptionHandle.handle(ee);
        if (result != null) {
            byte[] resultBytes = result.getBytes();
            if (this.exceptionHandle != null) {
                for (ResponseHandle rh : this.responseHandles) {
                    try {
                        resultBytes = rh.handle(resultBytes, req, resp, HandleType.CHARACTER);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            try (PrintWriter pw = resp.getWriter()) {
                pw.write(new String(resultBytes));
                pw.flush();
            }
        }
    }
}
