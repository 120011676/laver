package com.github.laver.example.servlet;

import com.github.laver.exception.exception.LaverRuntimeException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by say on 7/29/16.
 */
public class ExceptionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        throw new LaverRuntimeException("runtimeerror", "this is runtime error.");
//        try {
//
//            throw new LaverException("error", "this is error.");
//        } catch (LaverException e) {
//            e.printStackTrace();
//        }
    }
}
