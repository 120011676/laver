package com.github.laver.core.filter;

import com.github.laver.core.handle.ResponseHandle;
import com.github.laver.core.util.ResponseUtil;
import com.github.laver.core.wrapper.LaverServletResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LaverResponseFilter implements Filter {

    private List<ResponseHandle> responseHandles;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.responseHandles = new ResponseUtil().init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LaverServletResponseWrapper responseWrapper = new LaverServletResponseWrapper((HttpServletResponse) response);
        chain.doFilter(request, responseWrapper);
        ResponseUtil.send(this.responseHandles, responseWrapper, (HttpServletRequest) request, (HttpServletResponse) response);
    }

    @Override
    public void destroy() {

    }
}
