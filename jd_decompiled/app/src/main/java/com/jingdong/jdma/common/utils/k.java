package com.jingdong.jdma.common.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.jingdong.jdma.minterface.JDMABaseInfo;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.tencent.smtt.sdk.ProxyConfig;

/* loaded from: classes12.dex */
public class k {
    private static String a;
    private static String b;

    /* renamed from: c  reason: collision with root package name */
    private static String f12709c;
    private static String d;

    public static String a(Context context) {
        JDMABaseInfo jDMABaseInfo = c.v;
        if (jDMABaseInfo != null) {
            try {
                return jDMABaseInfo.getAndroidId();
            } catch (Throwable th) {
                th.printStackTrace();
                return "";
            }
        }
        boolean z = c.p;
        return "";
    }

    public static String b() {
        JDMABaseInfo jDMABaseInfo = c.v;
        String str = "";
        if (jDMABaseInfo != null) {
            try {
                str = jDMABaseInfo.getDeviceModel();
            } catch (Throwable th) {
                th.printStackTrace();
            }
            return !TextUtils.isEmpty(str) ? a(str, 12) : str;
        } else if (c.p) {
            if (TextUtils.isEmpty(f12709c)) {
                f12709c = a(BaseInfo.getDeviceModel(), 12);
            }
            return f12709c;
        } else {
            return "";
        }
    }

    public static String c() {
        JDMABaseInfo jDMABaseInfo = c.v;
        if (jDMABaseInfo != null) {
            try {
                return jDMABaseInfo.getDeviceModel();
            } catch (Throwable th) {
                th.printStackTrace();
                return "";
            }
        } else if (c.p) {
            if (TextUtils.isEmpty(b)) {
                b = BaseInfo.getDeviceModel();
            }
            return b;
        } else {
            return "";
        }
    }

    public static String a() {
        JDMABaseInfo jDMABaseInfo = c.v;
        if (jDMABaseInfo != null) {
            try {
                return jDMABaseInfo.getDeviceBrand();
            } catch (Throwable th) {
                th.printStackTrace();
                return "";
            }
        } else if (c.p) {
            if (TextUtils.isEmpty(a)) {
                a = BaseInfo.getDeviceBrand();
            }
            return a;
        } else {
            return "";
        }
    }

    public static String b(Context context) {
        JDMABaseInfo jDMABaseInfo = c.v;
        if (jDMABaseInfo != null) {
            try {
                return jDMABaseInfo.getScreenSize();
            } catch (Throwable th) {
                th.printStackTrace();
                return "";
            }
        } else if (c.p && context != null) {
            if (TextUtils.isEmpty(d)) {
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                d = displayMetrics.heightPixels + ProxyConfig.MATCH_ALL_SCHEMES + displayMetrics.widthPixels;
            }
            return d;
        } else {
            return "";
        }
    }

    private static String a(String str, int i2) {
        if (str != null) {
            try {
                return str.length() > i2 ? str.substring(0, i2) : str;
            } catch (Exception e2) {
                e2.printStackTrace();
                return str;
            }
        }
        return str;
    }
}
