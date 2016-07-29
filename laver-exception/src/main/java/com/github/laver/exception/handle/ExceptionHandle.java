package com.github.laver.exception.handle;

import com.github.laver.exception.entity.ExceptionEntity;

/**
 * Created by say on 7/28/16.
 */
public interface ExceptionHandle {
    String handle(ExceptionEntity ee);
}
