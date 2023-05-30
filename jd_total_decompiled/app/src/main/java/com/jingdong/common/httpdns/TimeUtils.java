package com.jingdong.common.httpdns;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes5.dex */
public class TimeUtils {
    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static long getDistanceMins(String str, String str2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date parse = simpleDateFormat.parse(str);
            Date parse2 = simpleDateFormat.parse(str2);
            long time = parse.getTime();
            long time2 = parse2.getTime();
            return (time < time2 ? time2 - time : time - time2) / 1000;
        } catch (ParseException e2) {
            e2.printStackTrace();
            return -1L;
        }
    }
}
