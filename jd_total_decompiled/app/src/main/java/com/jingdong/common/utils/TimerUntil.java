package com.jingdong.common.utils;

import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes6.dex */
public class TimerUntil {
    public static final String CHANGED_TIME = "changed_time";
    public static final String USER_TIME = "user_time";
    public static long endTime;
    public static long startTime;

    public static long getUserTime() {
        if (OKLog.D) {
            System.out.println("TimerUntil startTime=" + startTime + "\tendTime=" + endTime);
        }
        long j2 = endTime;
        long j3 = startTime;
        if ((j2 - j3) / 1000 > 0) {
            return (j2 - j3) / 1000;
        }
        return 0L;
    }
}
