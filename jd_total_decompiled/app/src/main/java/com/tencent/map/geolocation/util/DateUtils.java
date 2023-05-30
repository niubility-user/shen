package com.tencent.map.geolocation.util;

/* loaded from: classes9.dex */
public class DateUtils {
    public static final long ONE_DAY = 86400000;
    public static final long ONE_HOUR = 3600000;
    public static final long ONE_MINUTE = 60000;
    public static final long ONE_SECOND = 1000;
    public static final long TEN_SECOND = 10000;
    public static long mTencentTime = System.currentTimeMillis();
    public static long mLocalTime = System.currentTimeMillis();

    public static boolean bbb08788c45327527041933a3f54c56b(long j2, long j3) {
        return Math.abs(j2 - j3) > 1000;
    }

    public static long f2593e4de50dde6467f44b48c4b8180d() {
        return System.currentTimeMillis();
    }

    public static long getTencentTime() {
        return System.currentTimeMillis() + (mTencentTime - mLocalTime);
    }

    public static void setTencentTime(long j2) {
        mTencentTime = j2;
        mLocalTime = System.currentTimeMillis();
    }
}
