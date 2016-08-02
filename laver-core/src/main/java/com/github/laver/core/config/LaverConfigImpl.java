package com.github.laver.core.config;

import javax.servlet.FilterConfig;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import java.util.Enumeration;

/**
 * Created by say on 8/2/16.
 */
public class LaverConfigImpl implements LaverConfig {

    private FilterConfig filterConfig;
    private ServletConfig servletConfig;

    public LaverConfigImpl(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public LaverConfigImpl(ServletConfig servletConfig) {
        this.servletConfig = servletConfig;
    }

    @Override
    public String getname() {
        return this.filterConfig == null ? this.getServletName() : this.getFilterName();
    }

    @Override
    public String getFilterName() {
        return this.filterConfig == null ? null : this.filterConfig.getFilterName();
    }

    @Override
    public String getServletName() {
        return this.servletConfig == null ? null : this.servletConfig.getServletName();
    }

    @Override
    public ServletContext getServletContext() {
        return this.filterConfig == null ? this.servletConfig.getServletContext() : this.filterConfig.getServletContext();
    }

    @Override
    public String getInitParameter(String s) {
        return this.filterConfig == null ? this.servletConfig.getInitParameter(s) : this.filterConfig.getInitParameter(s);
    }

    @Override
    public Enumeration<String> getInitParameterNames() {
        return this.filterConfig == null ? this.servletConfig.getInitParameterNames() : this.filterConfig.getInitParameterNames();
    }
}
