package com.example.numbertowords;

import java.util.*;

/**
 * NumberConverter is a utility class that converts numeric strings
 * into their English words representation, including dollars and cents.
 */
public class NumberConverter {

    // Arrays for number to word conversion
    private static final String[] ones = {
            "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
            "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen",
            "sixteen", "seventeen", "eighteen", "nineteen"
    };

    private static final String[] tens = {
            "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
    };

    private static final String[] thousands = {
            "", "thousand", "million", "billion"
    };

    /**
     * Converts a numeric string to its words representation.
     * Handles both integer and decimal parts.
     *
     * @param numberStr The number as a string (e.g., "123.45")
     * @return The words representation of the number
     */
    public static String convertToWords(String numberStr) {
        try {
            // Split input into integer and decimal parts
            String[] parts = numberStr.split("\\.");
            long integerPart = Long.parseLong(parts[0]);
            int decimalPart = (parts.length > 1) 
                    ? Integer.parseInt(parts[1].substring(0, Math.min(2, parts[1].length()))) 
                    : 0;

            String integerWords = convertInteger(integerPart);
            String decimalWords = convertCents(decimalPart);

            String result = "";
            if (!integerWords.isEmpty()) {
                result += integerWords + (integerPart == 1 ? " dollar" : " dollars");
            }
            if (decimalPart > 0) {
                result += " and " + decimalWords + (decimalPart == 1 ? " cent" : " cents");
            }

            return result.trim();
        } catch (Exception e) {
            return "invalid input"; // Handles non-numeric input
        }
    }

    /**
     * Converts the integer part of a number to words.
     *
     * @param number The integer number
     * @return Words representation of the integer
     */
    private static String convertInteger(long number) {
        if (number == 0) return "zero";

        Stack<String> stack = new Stack<>();
        int group = 0;

        // Break number into groups of 3 digits and convert each
        while (number > 0) {
            int chunk = (int)(number % 1000);
            if (chunk != 0) {
                String chunkWords = convertChunk(chunk);
                if (!thousands[group].isEmpty()) chunkWords += " " + thousands[group];
                stack.push(chunkWords);
            }
            number /= 1000;
            group++;
        }

        // Build final string from stack
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
            if (!stack.isEmpty()) sb.append(" ");
        }

        return sb.toString();
    }

    /**
     * Converts a number less than 1000 to words.
     *
     * @param number The number to convert
     * @return Words representation of the number
     */
    private static String convertChunk(int number) {
        String result = "";

        // Handle hundreds
        if (number >= 100) {
            result += ones[number / 100] + " hundred";
            number %= 100;
            if (number > 0) result += " and ";
        }

        // Handle tens and ones
        if (number >= 20) {
            result += tens[number / 10];
            number %= 10;
            if (number > 0) result += "-" + ones[number];
        } else if (number > 0) {
            result += ones[number];
        }

        return result;
    }

    /**
     * Converts the decimal part (cents) to words.
     *
     * @param number Decimal part of the number
     * @return Words representation of the cents
     */
    private static String convertCents(int number) {
        if (number == 0) return "zero";
        return convertChunk(number);
    }
}
