package com.googlecode.mp4parser.util;

import java.util.Date;

/* loaded from: classes12.dex */
public class DateHelper {
    public static Date convert(long j2) {
        return new Date((j2 - 2082844800) * 1000);
    }

    public static long convert(Date date) {
        return (date.getTime() / 1000) + 2082844800;
    }
}
