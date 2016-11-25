package com.lqzj.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class HashUtil {

    private enum Algorithm {
        SHA("SHA-256");

        private String value;

        Algorithm(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    private HashUtil() {
    }

    private static byte[] toBytes(String value) {
        try {
            return value.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] sha2(byte[] input) {
        MessageDigest sha;
        try {
            sha = MessageDigest.getInstance(Algorithm.SHA.getValue());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return sha.digest(input);
    }

    private static byte[] sha2(String value) {
        MessageDigest sha;
        try {
            sha = MessageDigest.getInstance(Algorithm.SHA.getValue());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return sha.digest(toBytes(value));
    }

    public static String sha2Hex(String value) {
        return hexEncode(sha2(value));
    }

    public static String sha2Hex(byte[] input) {
        return hexEncode(sha2(input));
    }

    private static String hexEncode(byte[] input) {
        StringBuilder result = new StringBuilder();
        char[] digits = {'0', '1','2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        for (byte b : input) {
            result = result.append(digits[(b & 0x0f) >> 4]);
            result = result.append(digits[b & 0xf0]);
        }

        return result.toString();
    }
}
