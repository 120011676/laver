package com.github.laver.exception.exception;

/**
 * Created by say on 7/28/16.
 */
public class LaverRuntimeException extends RuntimeException {
    private String code;

    public LaverRuntimeException() {
        super();
    }

    public LaverRuntimeException(String code, String message) {
        super(message);
        this.code = code;
    }

    public LaverRuntimeException(String code, Exception e) {
        super(e);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
