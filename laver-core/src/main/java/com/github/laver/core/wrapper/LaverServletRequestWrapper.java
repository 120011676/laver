package com.github.laver.core.wrapper;

import com.github.laver.core.handle.RequestHandle;
import com.github.laver.core.io.LaverServletInputStream;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

public class LaverServletRequestWrapper extends HttpServletRequestWrapper {

    private ServletInputStream sin = new LaverServletInputStream(new ByteArrayInputStream(new byte[0]));
    private Map<String, String[]> paramsMap = new HashMap<>();

    public LaverServletRequestWrapper(HttpServletRequest request, List<RequestHandle> requestHandles, HttpServletResponse response) throws IOException {
        super(request);
        ServletInputStream servletInputStream = request.getInputStream();
        if (servletInputStream != null && requestHandles != null) {
            ByteArrayOutputStream baout = new ByteArrayOutputStream();
            try (BufferedInputStream bin = new BufferedInputStream(servletInputStream)) {
                byte[] bs = new byte[1024];
                int index;
                while ((index = bin.read(bs)) != -1) {
                    baout.write(bs, 0, index);
                }
            }
            for (RequestHandle rh : requestHandles) {
                byte[] bs = rh.handle(baout.toByteArray(), this, response);
                baout.reset();
                baout.write(bs);
            }
            paramsMap.putAll(request.getParameterMap());
            if (baout.size() > 0) {
                if ("application/x-www-form-urlencoded".equalsIgnoreCase(request.getContentType())) {
                    StringTokenizer data = new StringTokenizer(request.getCharacterEncoding() != null ? baout.toString(request.getCharacterEncoding()) : baout.toString(), "&");
                    while (data.hasMoreElements()) {
                        String[] kv = data.nextToken().split("=");
                        String[] vs;
                        if (this.paramsMap.containsKey(kv[0])) {
                            String[] ovs = this.paramsMap.get(kv[1]);
                            String[] nvs = new String[ovs.length + 1];
                            System.arraycopy(ovs, 0, nvs, 0, nvs.length);
                            System.arraycopy(new String[]{kv[1]}, 0, nvs, nvs.length, 1);
                            vs = nvs;
                        } else {
                            vs = new String[]{kv[1]};
                        }
                        this.paramsMap.put(kv[0], vs);
                    }
                    sin = new LaverServletInputStream(new ByteArrayInputStream(baout.toByteArray()));
                }
            }
        }
    }

    @Override
    public ServletRequest getRequest() {
        return this;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return paramsMap;
    }

    @Override
    public String getParameter(String name) {// 重写getParameter，代表参数从当前类中的map获取
        String[] values = paramsMap.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    @Override
    public String[] getParameterValues(String name) {// 同上
        return paramsMap.get(name);
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return Collections.enumeration(paramsMap.keySet());
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return sin;
    }

}
