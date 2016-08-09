package com.github.laver.exception.exception;

import com.github.laver.exception.uitl.ExceptionProperties;

import java.text.MessageFormat;

/**
 * Created by say on 8/9/16.
 */
public class LaverMessageException extends LaverException {
    public LaverMessageException(String code) {
        super(code, ExceptionProperties.getResourceBundle().getString(code));
    }

    public LaverMessageException(String code, Object... objects) {
        super(code, MessageFormat.format(ExceptionProperties.getResourceBundle().getString(code), objects));
    }
}
