package com.github.laver.core.filter;

import com.github.laver.core.handle.RequestHandle;
import com.github.laver.core.util.RequsetUtil;
import com.github.laver.core.wrapper.LaverServletRequestWrapper;
import com.github.laver.core.handle.ResponseHandle;
import com.github.laver.core.util.ResponseUtil;
import com.github.laver.core.wrapper.LaverServletResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LaverFilter implements Filter {

    private List<RequestHandle> requestHandles;

    private List<ResponseHandle> responseHandles;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.requestHandles = new RequsetUtil().init(filterConfig);
        this.responseHandles = new ResponseUtil().init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LaverServletResponseWrapper responseWrapper = new LaverServletResponseWrapper((HttpServletResponse) response);
        LaverServletRequestWrapper requestWrapper = new LaverServletRequestWrapper((HttpServletRequest) request, this.requestHandles, responseWrapper);
        chain.doFilter(requestWrapper, responseWrapper);
        ResponseUtil.send(this.responseHandles, responseWrapper, requestWrapper, (HttpServletResponse) response);
    }

    @Override
    public void destroy() {
        new RequsetUtil().destroy(this.requestHandles);
        new ResponseUtil().destroy(this.responseHandles);
    }
}
