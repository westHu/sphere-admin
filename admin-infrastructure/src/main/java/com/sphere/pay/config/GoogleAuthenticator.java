package com.sphere.pay.config;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;


@Slf4j
public class GoogleAuthenticator {

    public static final int WINDOW_SIZE = 3;
    public static final int SECRET_SIZE = 10;
    public static final String SEED = RandomStringUtils.randomAlphanumeric(64);
    public static final String RANDOM_NUMBER_ALGORITHM = "SHA1PRNG";
    public static final String FORMAT = "https://quickchart.io/chart?chs=400x400" +
            "&chld=M%%7C0&cht=qr&chl=otpauth://totp/%s@%s%%3Fsecret%%3D%s";

    public static String genURL(String user, String host) {
        return genURL(user, host, GoogleAuthenticator.generateSecretKey());
    }

    public static String genURL(String user, String host, String secret) {
        return GoogleAuthenticator.getQRBarcodeURL(user, host, secret);
    }

    public static Boolean verifyCode(String codes, String savedSecret) {
        long code = Long.parseLong(codes);
        long t = System.currentTimeMillis();
        GoogleAuthenticator ga = new GoogleAuthenticator();
        return ga.checkCode(savedSecret, code, t);
    }

    //--------------------------------------------------------------------------------------------------

    private static String getQRBarcodeURL(String user, String host, String secret) {
        return String.format(FORMAT, user, host, secret);
    }

    private boolean checkCode(String secret, long code, long timeMsec) {
        Base32 codec = new Base32();
        byte[] decodedKey = codec.decode(secret);
        long t = (timeMsec / 1000L) / 30L;
        for (int i = -WINDOW_SIZE; i <= WINDOW_SIZE; ++i) {
            long hash;
            try {
                hash = verifyCode(decodedKey, t + i);
            } catch (Exception e) {
                log.error("Google check code exception:", e);
                return false;
            }
            if (hash == code) {
                return true;
            }
        }
        return false;
    }

    @SneakyThrows
    private static String generateSecretKey() {
        SecureRandom sr = SecureRandom.getInstance(RANDOM_NUMBER_ALGORITHM);
        sr.setSeed(Base64.decodeBase64(SEED));
        byte[] buffer = sr.generateSeed(SECRET_SIZE);
        Base32 codec = new Base32();
        byte[] bEncodedKey = codec.encode(buffer);
        return new String(bEncodedKey);
    }

    @SneakyThrows
    private static int verifyCode(byte[] key, long t) {
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
}
 
 
 