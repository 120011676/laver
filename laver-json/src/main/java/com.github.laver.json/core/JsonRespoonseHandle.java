package com.github.laver.json.core;

import com.github.laver.core.config.LaverConfig;
import com.github.laver.core.handle.HandleType;
import com.github.laver.core.handle.ResponseHandle;
import com.github.laver.json.uitl.LaverJson;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by say on 7/27/16.
 */
public class JsonRespoonseHandle implements ResponseHandle {
    @Override
    public void init(LaverConfig laverConfig) throws ServletException {

    }

    @Override
    public byte[] handle(byte[] bs, HttpServletRequest req, HttpServletResponse resp, String type) {
        if (type.equals(HandleType.CHARACTER)) {
            String value = new String(bs);
            try {
                return LaverJson.success(resp.getStatus(), null, new JSONObject(value)).toString().getBytes();
            } catch (Exception e) {
                try {
                    return LaverJson.success(resp.getStatus(), null, new JSONArray(value)).toString().getBytes();
                } catch (Exception e1) {
                    return LaverJson.success(resp.getStatus(), null, value).toString().getBytes();
                }
            }
        }
        return bs;
    }

    @Override
    public void destroy() {

    }
}
