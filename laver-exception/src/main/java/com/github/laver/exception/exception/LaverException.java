package com.github.laver.exception.exception;

/**
 * Created by say on 7/28/16.
 */
public class LaverException extends Exception {
    private String code;

    public LaverException() {
        super();
    }

    public LaverException(String code, String message) {
        super(message);
        this.code = code;
    }

    public LaverException(String code, Exception e) {
        super(e);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
