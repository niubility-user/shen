package com.jd.lib.un.utils;

import androidx.annotation.NonNull;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes16.dex */
public class UnTimeUtils {
    public static final int DAY = 86400000;
    public static final int HOUR = 3600000;
    public static final int MIN = 60000;
    public static final int MSEC = 1;
    public static final int SEC = 1000;
    private static final DateFormat DEFAULT_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String[] CHINESE_ZODIAC = {"\u7334", "\u9e21", "\u72d7", "\u732a", "\u9f20", "\u725b", "\u864e", "\u5154", "\u9f99", "\u86c7", "\u9a6c", "\u7f8a"};
    private static final int[] ZODIAC_FLAGS = {20, 19, 21, 21, 21, 22, 23, 23, 23, 24, 23, 22};
    private static final String[] ZODIAC = {"\u6c34\u74f6\u5ea7", "\u53cc\u9c7c\u5ea7", "\u767d\u7f8a\u5ea7", "\u91d1\u725b\u5ea7", "\u53cc\u5b50\u5ea7", "\u5de8\u87f9\u5ea7", "\u72ee\u5b50\u5ea7", "\u5904\u5973\u5ea7", "\u5929\u79e4\u5ea7", "\u5929\u874e\u5ea7", "\u5c04\u624b\u5ea7", "\u9b54\u7faf\u5ea7"};

    public static long date2Millis(Date date) {
        return date.getTime();
    }

    public static String date2String(Date date) {
        return date2String(date, DEFAULT_FORMAT);
    }

    public static String getChineseZodiac(String str) {
        return getChineseZodiac(string2Date(str, DEFAULT_FORMAT));
    }

    public static Date getNowDate() {
        return new Date();
    }

    public static long getNowMills() {
        return System.currentTimeMillis();
    }

    public static String getNowString() {
        return millis2String(System.currentTimeMillis(), DEFAULT_FORMAT);
    }

    public static long getTimeSpan(String str, String str2, int i2) {
        return getTimeSpan(str, str2, DEFAULT_FORMAT, i2);
    }

    public static long getTimeSpanByNow(String str, int i2) {
        return getTimeSpan(getNowString(), str, DEFAULT_FORMAT, i2);
    }

    public static int getValueByCalendarField(String str, int i2) {
        return getValueByCalendarField(string2Date(str, DEFAULT_FORMAT), i2);
    }

    private static long getWeeOfToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public static String getZodiac(String str) {
        return getZodiac(string2Date(str, DEFAULT_FORMAT));
    }

    public static boolean isLeapYear(String str) {
        return isLeapYear(string2Date(str, DEFAULT_FORMAT));
    }

    public static boolean isToday(String str) {
        return isToday(string2Millis(str, DEFAULT_FORMAT));
    }

    public static Date millis2Date(long j2) {
        return new Date(j2);
    }

    public static String millis2String(long j2) {
        return millis2String(j2, DEFAULT_FORMAT);
    }

    private static long millis2TimeSpan(long j2, int i2) {
        return j2 / i2;
    }

    public static Date string2Date(String str) {
        return string2Date(str, DEFAULT_FORMAT);
    }

    public static long string2Millis(String str) {
        return string2Millis(str, DEFAULT_FORMAT);
    }

    public static String date2String(Date date, @NonNull DateFormat dateFormat) {
        return dateFormat.format(date);
    }

    public static String getChineseZodiac(String str, @NonNull DateFormat dateFormat) {
        return getChineseZodiac(string2Date(str, dateFormat));
    }

    public static String getNowString(@NonNull DateFormat dateFormat) {
        return millis2String(System.currentTimeMillis(), dateFormat);
    }

    public static long getTimeSpan(String str, String str2, @NonNull DateFormat dateFormat, int i2) {
        return millis2TimeSpan(Math.abs(string2Millis(str, dateFormat) - string2Millis(str2, dateFormat)), i2);
    }

    public static long getTimeSpanByNow(String str, @NonNull DateFormat dateFormat, int i2) {
        return getTimeSpan(getNowString(dateFormat), str, dateFormat, i2);
    }

    public static int getValueByCalendarField(String str, @NonNull DateFormat dateFormat, int i2) {
        return getValueByCalendarField(string2Date(str, dateFormat), i2);
    }

    public static String getZodiac(String str, @NonNull DateFormat dateFormat) {
        return getZodiac(string2Date(str, dateFormat));
    }

    public static boolean isLeapYear(String str, @NonNull DateFormat dateFormat) {
        return isLeapYear(string2Date(str, dateFormat));
    }

    public static boolean isToday(Date date) {
        return isToday(date.getTime());
    }

    public static String millis2String(long j2, @NonNull DateFormat dateFormat) {
        return dateFormat.format(new Date(j2));
    }

    public static Date string2Date(String str, @NonNull DateFormat dateFormat) {
        try {
            return dateFormat.parse(str);
        } catch (ParseException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static long string2Millis(String str, @NonNull DateFormat dateFormat) {
        try {
            return dateFormat.parse(str).getTime();
        } catch (ParseException e2) {
            e2.printStackTrace();
            return -1L;
        }
    }

    public static String getChineseZodiac(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return CHINESE_ZODIAC[calendar.get(1) % 12];
    }

    public static long getTimeSpanByNow(Date date, int i2) {
        return getTimeSpan(new Date(), date, i2);
    }

    public static int getValueByCalendarField(Date date, int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(i2);
    }

    public static String getZodiac(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getZodiac(calendar.get(2) + 1, calendar.get(5));
    }

    public static boolean isLeapYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return isLeapYear(calendar.get(1));
    }

    public static boolean isToday(long j2) {
        long weeOfToday = getWeeOfToday();
        return j2 >= weeOfToday && j2 < weeOfToday + 86400000;
    }

    public static long getTimeSpan(Date date, Date date2, int i2) {
        return millis2TimeSpan(Math.abs(date2Millis(date) - date2Millis(date2)), i2);
    }

    public static long getTimeSpanByNow(long j2, int i2) {
        return getTimeSpan(System.currentTimeMillis(), j2, i2);
    }

    public static long getTimeSpan(long j2, long j3, int i2) {
        return millis2TimeSpan(Math.abs(j2 - j3), i2);
    }

    public static String getChineseZodiac(long j2) {
        return getChineseZodiac(millis2Date(j2));
    }

    public static int getValueByCalendarField(long j2, int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j2);
        return calendar.get(i2);
    }

    public static String getChineseZodiac(int i2) {
        return CHINESE_ZODIAC[i2 % 12];
    }

    public static boolean isLeapYear(int i2) {
        return (i2 % 4 == 0 && i2 % 100 != 0) || i2 % 400 == 0;
    }

    public static String getZodiac(long j2) {
        return getZodiac(millis2Date(j2));
    }

    public static String getZodiac(int i2, int i3) {
        String[] strArr = ZODIAC;
        int i4 = i2 - 1;
        if (i3 < ZODIAC_FLAGS[i4]) {
            i4 = (i2 + 10) % 12;
        }
        return strArr[i4];
    }
}
