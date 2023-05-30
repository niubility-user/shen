package com.jd.security.jdguard.core;

import android.content.Context;

/* loaded from: classes17.dex */
public class Bridge {
    private static Context mContext;

    public static Context getAppContext() {
        return com.jd.security.jdguard.b.d().f();
    }

    public static String getAppKey() {
        return com.jd.security.jdguard.b.d().d();
    }

    public static String getJDGVN() {
        return "3.1.1";
    }

    public static String getPicName() {
        return com.jd.security.jdguard.b.d().g();
    }

    public static String getSecName() {
        return com.jd.security.jdguard.b.d().h();
    }

    public static native Object[] main(int i2, Object[] objArr);

    public static void setContext(Context context) {
        mContext = context;
    }
}
