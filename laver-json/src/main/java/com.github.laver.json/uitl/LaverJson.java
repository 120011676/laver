package com.github.laver.json.uitl;

import org.json.JSONObject;

/**
 * Created by say on 7/29/16.
 */
public class LaverJson {
//    public static JSONObject success(String code, JSONObject data) {
//        JSONObject json = new JSONObject();
//        json.put("status", true);
//        json.put("code", code);
//        json.put("data", data);
//        return json;
//    }
//
//    public static JSONObject success(String code, JSONArray data) {
//        JSONObject json = new JSONObject();
//        json.put("status", true);
//        json.put("code", code);
//        json.put("data", data);
//        return json;
//    }
//
//    public static JSONObject success(String code, String data) {
//        JSONObject json = new JSONObject();
//        json.put("status", true);
//        json.put("code", code);
//        json.put("data", data);
//        return json;
//    }

    public static JSONObject success(int httpCode, String code, Object data) {
        JSONObject json = new JSONObject();
        json.put("status", true);
        json.put("httpCode", httpCode);
        json.put("code", code);
        json.put("data", data);
        return json;
    }

    public static JSONObject error(int httpCode, String code, Object data) {
        JSONObject json = new JSONObject();
        json.put("status", false);
        json.put("httpCode", httpCode);
        json.put("code", code);
        json.put("data", data);
        return json;
    }
}
