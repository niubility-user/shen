package com.jingdong.common.jdreactFramework.modules.community.upload.utils;

import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class Utils {
    public static void log(String str, String str2) {
        if (OKLog.D) {
            OKLog.d(str, str2);
        }
    }

    public static long parse(String str, long j2) {
        try {
            return Long.parseLong(str);
        } catch (Exception unused) {
            return j2;
        }
    }

    public static int parse(String str, int i2) {
        try {
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return i2;
        }
    }
}
