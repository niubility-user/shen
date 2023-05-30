package com.jingdong.jdsdk.utils;

import com.jingdong.sdk.oklog.OKLog;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes14.dex */
public class FormatUtils {
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static DateFormat dateFormatCoupon = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static DateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static synchronized String formatDate(Date date) {
        String format;
        synchronized (FormatUtils.class) {
            format = dateFormat.format(date);
        }
        return format;
    }

    public static synchronized String formatDateWithYMH(Date date) {
        String format;
        synchronized (FormatUtils.class) {
            format = ymdFormat.format(date);
        }
        return format;
    }

    public static synchronized long getTime(String str) {
        long time;
        synchronized (FormatUtils.class) {
            try {
                time = dateFormatCoupon.parse(str).getTime();
            } catch (Exception unused) {
                if (OKLog.E) {
                    OKLog.e(FormatUtils.class.getName(), "getTime() dateStr -->> " + str);
                    return 0L;
                }
                return 0L;
            }
        }
        return time;
    }

    public static synchronized Date parseDate(String str) {
        Date parse;
        synchronized (FormatUtils.class) {
            try {
                parse = dateFormat.parse(str);
            } catch (ParseException e2) {
                if (OKLog.E) {
                    OKLog.e(FormatUtils.class.getName(), "parseDate() dateStr -->> " + str);
                }
                throw e2;
            }
        }
        return parse;
    }
}
