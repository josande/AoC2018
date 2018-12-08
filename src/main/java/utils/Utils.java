package utils;


import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class Utils {
    public String readFile(String fileName) {

        String result = "";

        ClassLoader classLoader = getClass().getClassLoader();
        try {
            result = IOUtils.toString(classLoader.getResourceAsStream(fileName), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
