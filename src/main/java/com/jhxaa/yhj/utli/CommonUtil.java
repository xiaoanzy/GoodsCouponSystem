package com.jhxaa.yhj.utli;

import com.jhxaa.yhj.common.Common;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

public class CommonUtil {

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static void clearList(Collection<?> collection) {
        collection.clear();
    }

    //1.淘宝2.天猫
    public static Integer getPlatformType(String name) {
        if (Common.SYSTEM_PLATFORM_STRING_TYPE_1.equals(name.trim())) {
            return Common.SYSTEM_PLATFORM_TYPE_1;
        } else if (Common.SYSTEM_PLATFORM_STRING_TYPE_2.equals(name.trim())) {
            return Common.SYSTEM_PLATFORM_TYPE_1;
        }
        return Common.SYSTEM_PLATFORM_TYPE_1;
    }


    public static BigDecimal handleStrGetBigDecimal(String str) {
        String result = null;
        String substring = null;
        Double of = null;
        if (str.contains("元无条件券")) {
            int indexOf = str.indexOf("元");
            substring = str.substring(0, indexOf);
        } else {
            //满22元减5元
            int indexOf = str.indexOf("元减") + 2;
            int lastIndexOf = str.lastIndexOf("元");
            substring = str.substring(indexOf, lastIndexOf);
        }
        of = Double.valueOf(substring);
        DecimalFormat df1 = new DecimalFormat("0.00");
        result = df1.format(of);
        if (result == null || result.isEmpty() || "".equals(result)) {
            result = "0.0";
        }
        return new BigDecimal(result);
    }

    public static BigDecimal getBigDecimal(double f) {
        DecimalFormat df1 = new DecimalFormat("0.00");
        String str = df1.format(f);
        return new BigDecimal(str);
    }

    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getURLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * list集合去重复
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<T> delRepeatList(List<T> list) {
        Set<T> set = new HashSet<>(list);
        List<T> resultList = new ArrayList<>(set);
        return resultList;
    }
//    public static String getSearchName(String name){
//        class
//    }

    public static void main(String[] args) {

        System.out.println(CommonUtil.SearchStrUtil.getSearchName("femaleClothing"));

    }

    /**
     * categoryType
     * 全部   女装  男装  美妆   鞋品   箱包   配饰   内衣   日用   母婴   其他
     * <p>
     * all                全部
     * femaleClothing     女装
     * maleClothing       男装
     * makeup             美妆
     * footwear           鞋品
     * bags               箱包
     * accessories        配饰
     * underwear          内衣
     * daily              日用
     * bady               母婴
     * other              其他
     */
    public static class SearchStrUtil {

        static Map<String, String> hashMap = null;

        static {
            if (hashMap == null) {
                hashMap = new HashMap<>();
                hashMap.put("all", "");
                hashMap.put("femaleClothing", "女装");
                hashMap.put("maleClothing", "男装");
                hashMap.put("makeup", "妆");
                hashMap.put("footwear", "鞋");
                hashMap.put("bags", "箱包");
                hashMap.put("accessories", "饰");
                hashMap.put("underwear", "内衣");
                hashMap.put("daily", "日用");
                hashMap.put("bady", "婴");
                hashMap.put("other", "用");
            }
        }


        public static String getSearchName(String name) {
            String s = hashMap.get(name);
            if (s == null || "".equals(s)) {
                s = "";
            }
            return s;
        }
    }

}
