package com.github.laver.json.exception;

import com.github.laver.exception.entity.ExceptionEntity;
import com.github.laver.exception.exception.LaverRuntimeException;
import com.github.laver.exception.handle.ExceptionHandle;
import com.github.laver.json.uitl.LaverJson;
import org.json.JSONObject;

import java.io.CharArrayWriter;
import java.io.PrintWriter;

/**
 * Created by say on 7/28/16.
 */
public class JsonExceptionHandle implements ExceptionHandle {
    @Override
    public String handle(ExceptionEntity ee) {
        JSONObject error = new JSONObject(ee);
        if (ee.getException() != null) {
            try (CharArrayWriter writer = new CharArrayWriter(); PrintWriter pw = new PrintWriter(writer)) {
                ee.getException().printStackTrace(pw);
                error.put("exceptionInfo", writer.toString());
            }
        }
        String code = null;
        if (ee.getException() instanceof LaverRuntimeException) {
            LaverRuntimeException le = (LaverRuntimeException) ee.getException();
            code = le.getCode();
            error.put("msg", le.getMessage());
        }
        return LaverJson.error(ee.getStatusCode(), code, error).toString();
    }
}
