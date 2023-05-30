package com.jingdong.common.cashiernative;

import com.jingdong.jdsdk.config.Configuration;

/* loaded from: classes5.dex */
public class CashierCommonUtil {
    public static String getPayAppID() {
        return "api.m.jd.com".equalsIgnoreCase(Configuration.getCashierNativeHost()) ? "jd_android_app4" : "android_app_beta";
    }

    public static String getPayAppKey() {
        return "api.m.jd.com".equalsIgnoreCase(Configuration.getCashierNativeHost()) ? "e53jfgRgd7Hk" : "6fg7weDfF6gh";
    }
}
