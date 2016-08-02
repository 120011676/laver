package com.github.laver.aes.util;

import java.io.*;

/**
 * Created by say on 8/2/16.
 */
public class FileUtil {
    public static String read(InputStream in) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
