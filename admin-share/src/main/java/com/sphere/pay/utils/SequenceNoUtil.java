package com.sphere.pay.utils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * 唯一识别码
 */
public final class SequenceNoUtil {


    /**
     * ===> randomNanoId部分
     */
    public static final Integer SIZE = 10;

    public static final SecureRandom RANDOM = new SecureRandom();

    public static final char[] ALPHABET =
            "@#$%&*0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public static String randomNanoId(Integer size) {
        return randomNanoId(RANDOM, ALPHABET, size);
    }

    public static String randomNanoId() {
        return randomNanoId(RANDOM, ALPHABET, SIZE);
    }

    public static String randomNanoId(Random random, char[] alphabet, int size) {
        int mask = (2 << (int) Math.floor(Math.log((alphabet.length - 1)) / Math.log(2.0D))) - 1;
        int step = (int) Math.ceil(1.6D * mask * size / alphabet.length);
        StringBuilder idBuilder = new StringBuilder();

        while (true) {
            byte[] bytes = new byte[step];
            random.nextBytes(bytes);

            for (int i = 0; i < step; ++i) {
                int alphabetIndex = bytes[i] & mask;
                if (alphabetIndex < alphabet.length) {
                    idBuilder.append(alphabet[alphabetIndex]);
                    if (idBuilder.length() == size) {
                        return idBuilder.toString();
                    }
                }
            }
        }
    }


    /**
     * ====> snow 部分
     */
}