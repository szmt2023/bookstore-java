package com.atguigu.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: ljg
 * @Date: 2025/8/28 10:07 AM Thursday
 * @Description:
 */
public class MD5Util {
    public static String encode(String source) {
        if(source == null || "".equals(source) || source.isEmpty()) {
            throw new RuntimeException("加密的明文不能为空");
        }
        String algorithm = "MD5";
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        messageDigest.update(source.getBytes());
        byte[] digest = messageDigest.digest();

        int signum = 1;
        BigInteger bigInteger = new BigInteger(signum, digest);

        int radix = 16;
        String encoded = bigInteger.toString(radix).toUpperCase();

        return encoded;
    }
}
