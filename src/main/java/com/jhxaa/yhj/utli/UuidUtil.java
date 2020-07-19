package com.jhxaa.yhj.utli;

import java.util.UUID;

public class UuidUtil {


    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        return s;
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
