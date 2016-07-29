package com.github.laver.exception.entity;

/**
 * Created by say on 7/28/16.
 */
public class ExceptionEntity {
    private String forwardRequestUri;
    private String contextPath;
    private String servletPath;
    private Integer statusCode;
    private Class exceptionType;
    private String message;
    private String servletName;
    private String errorRequestUri;
    private Throwable exception;

    public String getForwardRequestUri() {
        return forwardRequestUri;
    }

    public void setForwardRequestUri(String forwardRequestUri) {
        this.forwardRequestUri = forwardRequestUri;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getServletPath() {
        return servletPath;
    }

    public void setServletPath(String servletPath) {
        this.servletPath = servletPath;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Class getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(Class exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getServletName() {
        return servletName;
    }

    public void setServletName(String servletName) {
        this.servletName = servletName;
    }

    public String getErrorRequestUri() {
        return errorRequestUri;
    }

    public void setErrorRequestUri(String errorRequestUri) {
        this.errorRequestUri = errorRequestUri;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }
}
