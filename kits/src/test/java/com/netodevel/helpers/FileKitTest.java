package com.netodevel.helpers;

import org.junit.Test;

import java.io.InputStream;

import static java.lang.ClassLoader.getSystemResourceAsStream;
import static org.junit.Assert.assertEquals;

public class FileKitTest {

    @Test
    public void shouldReturnString() {
        InputStream inputStream = getSystemResourceAsStream("is.json");
        String value = FileKit.inputStreamToString(inputStream);

        String expected = "{\n" +
                "  \"input_stream\": \"example\",\n" +
                "  \"age\": 10\n" +
                "}";
        assertEquals(expected.trim(), value.trim());
    }

    @Test
    public void withDelimiterShouldReturnString() {
        InputStream inputStream = getSystemResourceAsStream("is-with-delimiter.json");
        String value = FileKit.inputStreamToString(inputStream, "_");

        String expected = "xpto_xpto_xpto";
        assertEquals(expected.trim(), value.trim());
    }

}