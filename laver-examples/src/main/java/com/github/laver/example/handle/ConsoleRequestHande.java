package com.github.laver.example.handle;

import com.github.laver.core.handle.RequestHandle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by say on 7/22/16.
 */
public class ConsoleRequestHande implements RequestHandle {
    @Override
    public byte[] handle(byte[] bs, HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("===================request===================");
        System.out.println(new String(bs));
        return bs;
    }
}
