package com.jhxaa.yhj.utli;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeUtil {

//    TimeUnit.DAYS          //天
//    TimeUnit.HOURS         //小时
//    TimeUnit.MINUTES       //分钟
//    TimeUnit.SECONDS       //秒
//    TimeUnit.MILLISECONDS  //毫秒

    //java获取当前月的天数
    public static int getDay() {
        Calendar calendar = Calendar.getInstance();
//        System.out.println(calendar.get(Calendar.YEAR));
//        System.out.println(calendar.get(Calendar.MONTH)+1);
//        System.out.println(calendar.get(Calendar.DATE));
        return calendar.get(Calendar.DATE);
    }


    public static void main(String[] args) {
        while (true) {
            // randomArrayIndex1(10);
            System.out.println(randomArrayIndex(10));
        }
    }

    public static void randomMiaoSleep(Integer num) {
        Integer resultNumber = (int) (Math.random() * num);
        System.out.println(resultNumber);
        try {
            TimeUnit.MILLISECONDS.sleep(resultNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Integer randomNumber(Integer number) {
        Integer resultNumber = (int) (Math.random() * number);
        return resultNumber;
    }

    public static Integer randomArrayIndex(Integer number) {
        Integer resultNumber = 0;
        for (; ; ) {
            resultNumber = (int) (Math.random() * number + (Math.random() >= 0.5 ? 1 : 0));
            if (resultNumber != number) {
                break;
            }
        }
        return resultNumber;
    }

//    public static Integer randomArrayIndex1(Integer number) {
//        Integer resultNumber = (int) (Math.random() * number + (Math.random() >= 0.5 ? 1 : 0));
//        if (resultNumber == number) {
//            return randomArrayIndex1(number);
//        }
//        //System.out.println(resultNumber + "::::"+number);
//        return resultNumber;
//    }

    public static Date getDate() {
        return new Date();
    }

    /**
     * 设置休眠天数
     *
     * @param daysNumber
     */
    public static void sleepDays(Long daysNumber) {
        try {
            TimeUnit.DAYS.sleep(daysNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置休眠小时
     *
     * @param hoursNumber
     */
    public static void sleepHours(Long hoursNumber) {
        try {
            TimeUnit.HOURS.sleep(hoursNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置休眠分钟
     *
     * @param minutesNumber
     */
    public static void sleepMinutes(Long minutesNumber) {
        try {
            TimeUnit.MINUTES.sleep(minutesNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置休眠秒数
     *
     * @param secondsNumber
     */
    public static void sleepSeconds(Long secondsNumber) {
        try {
            TimeUnit.SECONDS.sleep(secondsNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置休眠毫秒数
     *
     * @param millisecondsNumber
     */
    public static void sleepMilliseconds(Long millisecondsNumber) {
        try {
            TimeUnit.MILLISECONDS.sleep(millisecondsNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Date toDate(String dateStr, String format) {
        Date d2 = null;
        SimpleDateFormat sdf2 = null;
        try {
            sdf2 = new SimpleDateFormat(format);
            d2 = sdf2.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d2;
    }

    /**
     * date转string
     *
     * @param date
     * @param format
     * @return
     */
    public static String toDateString(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    public enum dateFormat {
        YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),
        YYYY_MM_DD("yyyy-MM-dd"),

        ;

        String format;


        dateFormat(String format) {
            this.format = format;
        }

        public String value() {
            return this.format;
        }

    }

}
