package com.gnit.utils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by jdry on 2016/9/9.
 * jdry 工具类
 */
public class JdryTime {
    public static SimpleDateFormat ymd = new SimpleDateFormat("yyyy.MM.dd");
    public static SimpleDateFormat ymdhm = new SimpleDateFormat("yyyy.MM.dd HH:mm");
    public static SimpleDateFormat ymdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static GregorianCalendar gc = new GregorianCalendar();

    public static Date getDate(String date) {
        Date d = new Date();
        try {
            d = ymd.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

    public static Date getFullDate(String date) {
        Date d = new Date();
        try {
            d = ymdhms.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }
    
    /**
     * 时间戳转换成时间字符串yyyy-MM-dd HH:mm:ss
     * @param sec
     * @return
     */
    public static String getFullTimeBySec(long sec) {
        Date d = new Date(sec);
        return ymdhms.format(d);
    }
    
    /**
     * 时间戳转换成时间字符串yyyy.MM.dd HH:mm
     * @param sec
     * @return
     */
    public static String getDayHourMinBySec(long sec) {
        Date d = new Date(sec);
        return ymdhm.format(d);
    }

    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;
    private static final long ONE_WEEK = 604800000L;

    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";
    private static final String ONE_MONTH_AGO = "月前";
    private static final String ONE_YEAR_AGO = "年前";

    public static String format(Date date) {
        long delta = new Date().getTime() - date.getTime();
        if (delta < 1L * ONE_MINUTE) {
            long seconds = toSeconds(delta);
            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
        }
        if (delta < 45L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        }
        if (delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
        }
        if (delta < 48L * ONE_HOUR) {
            return "昨天";
        }
        if (delta < 30L * ONE_DAY) {
            long days = toDays(delta);
            return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
        }
        if (delta < 12L * 4L * ONE_WEEK) {
            long months = toMonths(delta);
            return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;
        } else {
            long years = toYears(delta);
            return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;
        }
    }

    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    private static long toMonths(long date) {
        return toDays(date) / 30L;
    }

    private static long toYears(long date) {
        return toMonths(date) / 365L;
    }
}
