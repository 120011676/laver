package com.github.laver.json.exception;

import com.github.laver.exception.entity.ExceptionEntity;
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
        String exceptionInfo = null;
        if (ee.getException() != null) {
            try (CharArrayWriter writer = new CharArrayWriter(); PrintWriter pw = new PrintWriter(writer)) {
                ee.getException().printStackTrace(pw);
                exceptionInfo = writer.toString();
            }
        }
        return LaverJson.error("http_" + ee.getStatusCode(), new JSONObject(ee).put("exceptionInfo", exceptionInfo)).toString();
    }
}
