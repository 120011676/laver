package com.github.laver.core.util;

import com.github.laver.core.config.LaverConfig;
import com.github.laver.core.config.LaverConfigImpl;
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
            LaverConfig laverConfig = new LaverConfigImpl(filterConfig);
            List<RequestHandle> requestHandles = new ArrayList<>();
            String[] rhs = requestHandlesStr.split(",");
            for (String rh : rhs) {
                try {
                    RequestHandle obj = (RequestHandle) Class.forName(rh.trim()).newInstance();
                    obj.init(laverConfig);
                    requestHandles.add(obj);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return requestHandles;
        }
        return null;
    }

    public void destroy(List<RequestHandle> requestHandles) {
        if (requestHandles != null && requestHandles.size() > 0) {
            for (RequestHandle o : requestHandles) {
                o.destroy();
            }
        }
    }
}
