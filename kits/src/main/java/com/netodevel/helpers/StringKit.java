package com.netodevel.helpers;

import java.util.function.Consumer;

/**
 * @author NetoDevel
 */
public class StringKit {

    private StringKit() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isNotBlank(String... str) {
        if (str == null) return false;
        for (String s : str) {
            if (isBlank(s)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isBlank(String str) {
        return null == str || "".equals(str.trim());
    }

    public static void isBlankThen(String str, Consumer<String> consumer) {
        if (isBlank(str)) {
            consumer.accept(str);
        }
    }

    public static boolean isNumber(String value) {
        try {
            Double.parseDouble(value);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static String toCapitalize(String s) {
        if (s == null) {
            return null;
        }
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

}
