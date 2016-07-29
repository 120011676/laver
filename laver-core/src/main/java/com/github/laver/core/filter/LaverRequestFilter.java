package com.github.laver.core.filter;

import com.github.laver.core.handle.RequestHandle;
import com.github.laver.core.util.RequsetUtil;
import com.github.laver.core.wrapper.LaverServletRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LaverRequestFilter implements Filter {

    private List<RequestHandle> requestHandles;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.requestHandles = new RequsetUtil().init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LaverServletRequestWrapper requestWrapper = new LaverServletRequestWrapper((HttpServletRequest) request, this.requestHandles, (HttpServletResponse) response);
        chain.doFilter(requestWrapper, response);
    }

    @Override
    public void destroy() {

    }
}
