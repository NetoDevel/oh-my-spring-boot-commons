package com.netodevel.helpers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * @author NetoDevel
 */
public class FileKit {

    private FileKit() {
        throw new IllegalStateException("Utility class");
    }

    public static String inputStreamToString(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
    }

    public static String inputStreamToString(InputStream inputStream, String delimiter) {
        return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining(delimiter));
    }

}
