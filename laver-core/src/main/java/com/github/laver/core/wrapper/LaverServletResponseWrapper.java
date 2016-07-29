package com.github.laver.core.wrapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LaverServletResponseWrapper extends HttpServletResponseWrapper {

    private ByteArrayOutputStream out = new ByteArrayOutputStream();
    private CharArrayWriter writer = new CharArrayWriter();

    public LaverServletResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public ServletResponse getResponse() {
        return this;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new ServletOutputStream() {
            @Override
            public void write(int b) throws IOException {
                out.write(b);
            }

            @Override
            public void flush() throws IOException {
                out.flush();
            }

            @Override
            public void close() throws IOException {
                out.close();
            }
        };
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(writer);
    }

    public byte[] toOutputStream() {
        return this.out.toByteArray();
    }

    public char[] toWriter() {
        return this.writer.toCharArray();
    }
}
