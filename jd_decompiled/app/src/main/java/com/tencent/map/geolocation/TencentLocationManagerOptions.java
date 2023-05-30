package com.tencent.map.geolocation;

import android.text.TextUtils;
import c.t.m.g.x6;

/* loaded from: classes9.dex */
public class TencentLocationManagerOptions {
    public static boolean a = true;
    public static String b = "";

    /* renamed from: c  reason: collision with root package name */
    public static String f16176c = "";

    public static String getExtraKey() {
        return f16176c;
    }

    public static String getKey() {
        return b;
    }

    public static boolean isLoadLibraryEnabled() {
        return a;
    }

    public static void setDebuggable(boolean z) {
        x6.a = z;
    }

    public static boolean setExtraKey(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        f16176c = str;
        return true;
    }

    public static boolean setKey(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        b = str;
        return true;
    }

    public static void setLoadLibraryEnabled(boolean z) {
        a = z;
    }
}
