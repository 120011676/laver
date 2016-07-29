package com.github.laver.core.util;

import com.github.laver.core.handle.RequestHandle;

import javax.servlet.FilterConfig;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by say on 7/26/16.
 */
public class RequsetUtil {

    public List<RequestHandle> init(FilterConfig filterConfig) {
        String requestHandlesStr = filterConfig.getInitParameter("request");
        if (requestHandlesStr != null && !"".equals(requestHandlesStr.trim())) {
            List<RequestHandle> requestHandles = new ArrayList<>();
            String[] rhs = requestHandlesStr.split(",");
            for (String rh : rhs) {
                try {
                    requestHandles.add((RequestHandle) Class.forName(rh.trim()).newInstance());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return requestHandles;
        }
        return null;
    }
}
