package com.jhxaa.yhj.utli;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

public class MD5Util {
    public static String stringToMD5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

    public static String convertSignStr(Map<String, String[]> parameterMap) {
        TreeMap<String, String[]> treeMap = new TreeMap<>(parameterMap);
        StringBuilder stringBuilder = new StringBuilder("sign:");
        for (Map.Entry<String, String[]> entry : treeMap.entrySet()) {
            if (!"sign".equals(entry.getKey())) {
                stringBuilder.append(chekStr(entry.getKey()));
                stringBuilder.append("=");
                stringBuilder.append(chekStr(entry.getValue()[0]));
            }
        }
        return stringBuilder.toString();
    }

    private static String chekStr(String name) {
        if (name.isEmpty() || "".equals(name) || name == null) {
            return "";
        }
        return name;
    }
}
