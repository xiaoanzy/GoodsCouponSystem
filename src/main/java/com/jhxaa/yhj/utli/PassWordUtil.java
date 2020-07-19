package com.jhxaa.yhj.utli;

public class PassWordUtil {

    private final static String str = "YrtyJMKIuiopfghjklFRTG456qweBNH790DCVzxcvQAZWasdSXEbnm1238ULOP";
    private static char[] charArray = null;


    static {
        if (charArray == null) {
            charArray = str.toCharArray();
        }
    }

    public static String createPassWordByLength(Integer length) {
        int size = charArray.length;
        Integer index = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer i = 0; i < length; i++) {
            index = TimeUtil.randomArrayIndex(size);
            stringBuilder.append(charArray[index]);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(createPassWordByLength(10));
    }
}
