package com.github.laver.core.config;

import javax.servlet.FilterConfig;
import javax.servlet.ServletConfig;

/**
 * Created by say on 8/2/16.
 */
public interface LaverConfig extends FilterConfig, ServletConfig {
    String getname();
}
