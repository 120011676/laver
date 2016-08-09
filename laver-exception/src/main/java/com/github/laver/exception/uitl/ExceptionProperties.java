package com.github.laver.exception.uitl;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by say on 8/9/16.
 */
public class ExceptionProperties {
    private static ResourceBundle RB;

    public static void read(String baseName) {
        RB = ResourceBundle.getBundle(baseName, Locale.getDefault());
    }

    public static ResourceBundle getResourceBundle() {
        return RB;
    }
}
