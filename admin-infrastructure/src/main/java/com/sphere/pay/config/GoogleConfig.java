package com.sphere.pay.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


@Slf4j
public class GoogleConfig {

    public static final int SECRET_SIZE = 10;

    public static final int window_size = 1;

    public static final String SEED = "g8GjEvTbW5oVSV7avLBdwIHqGlUYNzKFI7izOF8GwLDVKs2m0QN7vxRs2im5MDaNCWGmcD2rvcZx";

    public static final String FORMAT = "https://quickchart.io/chart?chs=400x400&chld=M%%7C0&cht=qr&chl=otpauth://totp/%s@%s" +
            "%%3Fsecret%%3D%s";

    public static final String RANDOM_NUMBER_ALGORITHM = "SHA1PRNG";

    public static void main(String[] args) {
        String secret = generateSecretKey();
        log.info("secret={}", secret);

        String qrUrl = getQrUrl("ASD", "Host", secret);
        log.info("qrUrl={}", qrUrl);

    }

    public static Boolean verifyCode(String code, String secret) {
        long time = System.currentTimeMillis();
        Base32 codec = new Base32();
        byte[] decodedKey = codec.decode(secret);
        long t = (time / 1000L) / 30L;
        for (int i = -window_size; i <= window_size; ++i) {
            long hash;
            try {
                hash = verifyCode(decodedKey, t + i);
            } catch (Exception e) {
                log.error("checkCode exception: ", e);
                return false;
            }
            if (hash == Long.parseLong(code)) {
                return true;
            }
        }
        return false;
    }

    public static String generateSecretKey() {
        SecureRandom sr;
        try {
            sr = SecureRandom.getInstance(RANDOM_NUMBER_ALGORITHM);
            sr.setSeed(Base64.decodeBase64(SEED));
            byte[] buffer = sr.generateSeed(SECRET_SIZE);
            Base32 codec = new Base32();
            byte[] bEncodedKey = codec.encode(buffer);
            return new String(bEncodedKey);
        } catch (NoSuchAlgorithmException e) {
            log.error("generateSecretKey exception:", e);
        }
        return null;
    }


    private static int verifyCode(byte[] key, long t) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] data = new byte[8];
        long value = t;
        for (int i = 8; i-- > 0; value >>>= 8) {
            data[i] = (byte) value;
        }
        SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signKey);
        byte[] hash = mac.doFinal(data);
        int offset = hash[20 - 1] & 0xF;
        long truncatedHash = 0;
        for (int i = 0; i < 4; ++i) {
            truncatedHash <<= 8;
            truncatedHash |= (hash[offset + i] & 0xFF);
        }
        truncatedHash &= 0x7FFFFFFF;
        truncatedHash %= 1000000;
        return (int) truncatedHash;
    }

    public static String getQrUrl(String user, String host, String secret) {
        return String.format(FORMAT, user, host, secret);
    }
}
 
 
 