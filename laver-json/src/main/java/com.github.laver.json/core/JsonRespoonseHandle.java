package com.github.laver.json.core;

import com.github.laver.core.handle.ResponseHandle;
import com.github.laver.json.uitl.LaverJson;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by say on 7/27/16.
 */
public class JsonRespoonseHandle implements ResponseHandle {
    @Override
    public byte[] handle(byte[] bs, HttpServletRequest req, HttpServletResponse resp) {
        return bs;
    }

    @Override
    public char[] handle(char[] chars, HttpServletRequest req, HttpServletResponse resp) {
        String results = new String(chars);
        try {
            return LaverJson.success("http_" + resp.getStatus(), new JSONObject(results)).toString().toCharArray();
        } catch (Exception e) {
            try {
                return LaverJson.success("http_" + resp.getStatus(), new JSONArray(results)).toString().toCharArray();
            } catch (Exception e1) {
                return LaverJson.success("http_" + resp.getStatus(), results).toString().toCharArray();
            }
        }
    }
}
