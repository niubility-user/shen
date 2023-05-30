package com.jdpaysdk.widget.util;

import java.util.Date;

/* loaded from: classes18.dex */
public class DateUtil {
    private DateUtil() {
    }

    public static long compareCurrentDate(Date date) {
        return date.getTime() - System.currentTimeMillis();
    }
}
