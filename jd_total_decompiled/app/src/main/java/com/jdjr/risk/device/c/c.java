package com.jdjr.risk.device.c;

import android.content.Context;
import com.jdjr.acr.IntegrityCheck;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes18.dex */
public class c {
    public static String a() {
        try {
            return BaseInfo.getAppName();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String a(Context context) {
        try {
            return new IntegrityCheck(context).check();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String b() {
        try {
            return BaseInfo.getAppVersionName();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String b(Context context) {
        try {
            return BaseInfo.isAppForeground() ? "1" : "0";
        } catch (Throwable unused) {
            return "0";
        }
    }

    public static String c() {
        try {
            return BaseInfo.getAppVersionCode() + "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String d() {
        try {
            return BaseInfo.getAppPackageName();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String e() {
        try {
            return BaseInfo.getAppSignatureHash() + "";
        } catch (Throwable unused) {
            return "";
        }
    }
}
