package com.example.numbertowords;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NumberConverterTest {

    @Test
    void testZero() {
        assertEquals("zero dollars", NumberConverter.convertToWords("0"));
        assertEquals("zero dollars and one cent", NumberConverter.convertToWords("0.01"));
    }

    @Test
    void testOnesAndTeens() {
        assertEquals("one dollar", NumberConverter.convertToWords("1"));
        assertEquals("twelve dollars", NumberConverter.convertToWords("12"));
        assertEquals("nineteen dollars", NumberConverter.convertToWords("19"));
    }

    @Test
    void testTens() {
        assertEquals("twenty dollars", NumberConverter.convertToWords("20"));
        assertEquals("thirty-five dollars", NumberConverter.convertToWords("35"));
        assertEquals("ninety-nine dollars", NumberConverter.convertToWords("99"));
    }

    @Test
    void testHundreds() {
        assertEquals("one hundred dollars", NumberConverter.convertToWords("100"));
        assertEquals("one hundred and twenty-three dollars", NumberConverter.convertToWords("123"));
        assertEquals("nine hundred and ninety-nine dollars", NumberConverter.convertToWords("999"));
    }

    @Test
    void testThousands() {
        assertEquals("one thousand dollars", NumberConverter.convertToWords("1000"));
        assertEquals("one thousand two hundred and thirty-four dollars", NumberConverter.convertToWords("1234"));
        assertEquals("twelve thousand three hundred and forty-five dollars", NumberConverter.convertToWords("12345"));
    }

    @Test
    void testMillions() {
        assertEquals("one million dollars", NumberConverter.convertToWords("1000000"));
        assertEquals("one million two hundred and thirty-four thousand five hundred and sixty-seven dollars", NumberConverter.convertToWords("1234567"));
    }

    @Test
    void testDecimals() {
        assertEquals("one dollar and one cent", NumberConverter.convertToWords("1.01"));
        assertEquals("twelve dollars and thirty-four cents", NumberConverter.convertToWords("12.34"));
        assertEquals("one hundred and twenty-three dollars and forty-five cents", NumberConverter.convertToWords("123.45"));
    }

    @Test
    void testEdgeCases() {
        assertEquals("invalid input", NumberConverter.convertToWords("abc"));
        assertEquals("invalid input", NumberConverter.convertToWords(""));
        assertEquals("one hundred dollars and one cent", NumberConverter.convertToWords("100.01"));
    }

    @Test
    void testLargeNumbers() {
        assertEquals(
            "nine hundred and ninety-nine million nine hundred and ninety-nine thousand nine hundred and ninety-nine dollars",
            NumberConverter.convertToWords("999999999")
        );
    }
}
