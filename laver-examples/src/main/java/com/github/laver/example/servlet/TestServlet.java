package com.github.laver.example.servlet;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by say on 7/27/16.
 */
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int s = 1 / 0;
        try (Writer w = resp.getWriter()) {
            w.write(new JSONArray().put(new JSONObject().put("b", true)).toString());
        }
    }

}
