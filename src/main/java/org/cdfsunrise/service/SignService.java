package org.cdfsunrise.service;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.SortedMap;
import java.security.MessageDigest;

public class SignService {
    public static String Sign(SortedMap<String, String> rawParams) throws Exception {
        if (rawParams == null) {
            return "";
        }
        StringBuilder sortedParams = new StringBuilder();
        rawParams.forEach((k, v) -> {
            sortedParams.append(String.format("%s=%s&", k, v));
        });
        String join = sortedParams.toString();
        if (join.lastIndexOf("&") == join.length()-1) {
            join = join.substring(0, join.length()-1);
        }

        return computeMD5Hash(join);
    }

    public static String GetCurrentTimestampSeconds() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    public static String computeMD5Hash(String input) {
        try {
            // 获取MD5 MessageDigest实例
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 将输入字符串转换为字节数组
            byte[] messageBytes = input.getBytes(StandardCharsets.UTF_8);

            // 计算消息摘要
            byte[] digestBytes = md.digest(messageBytes);

            // 将摘要结果转换为16进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : digestBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }
}
