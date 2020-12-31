package com.netodevel.helpers;


import org.junit.Test;

import static com.netodevel.helpers.StringKit.*;
import static org.junit.Assert.*;

public class StringKitTest {

    @Test
    public void givenBlankShouldReturnTrue() {
        String value = "";
        assertTrue(isBlank(value));
    }

    @Test
    public void givenSpacesShouldReturnTrue() {
        String value = "  ";
        assertTrue(isBlank(value));
    }

    @Test
    public void givenValueShouldReturnFalse() {
        String value = "it's works";
        assertFalse(isBlank(value));
    }

    @Test
    public void givenListIfOneIsBlankShouldReturnFalse() {
        String valueOne = "";
        String valueTwo = "value";

        Boolean expected = isNotBlank(valueOne, valueTwo);
        assertFalse(expected);
    }

    @Test
    public void givenListIfAllCotainsValueShouldReturnTrue() {
        String value = "it's works";
        String valueTwo = "other value";
        Boolean expected = isNotBlank(value, valueTwo);
        assertTrue(expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenBlankShouldReturnException() {
        String value = "";

        isBlankThen(value, (e) -> {
            throw new IllegalArgumentException("can not be blank");
        });
    }

    @Test
    public void givenNotBlankShouldNotReturnException() {
        String value = "value";

        isBlankThen(value, (e) -> {
            throw new IllegalArgumentException("can not be blank");
        });
    }

    @Test
    public void givenStringFormatNumberShouldReturnTrue() {
        String value = "2";
        Boolean expected = isNumber(value);
        assertTrue(expected);
    }

    @Test
    public void givenStringFormatNumberDoubleShouldReturnTrue() {
        String value = "2.5";
        Boolean expected = isNumber(value);
        assertTrue(expected);
    }

    @Test
    public void givenStringShouldReturnFalse() {
        String value = "string";
        Boolean expected = isNumber(value);
        assertFalse(expected);
    }

    @Test
    public void shouldCapitalizeString() {
        String value = "string";
        String capitalize = toCapitalize(value);
        assertEquals("String", capitalize);
    }

}