package com.jingdong.manto.utils;

import android.content.Context;

/* loaded from: classes16.dex */
public class n {
    private static String a;
    public static String b;

    /* renamed from: c  reason: collision with root package name */
    public static String f14314c;

    static {
        Context a2 = com.jingdong.manto.c.a();
        if (a2 == null) {
            throw new RuntimeException("Application Context not initialized.");
        }
        a = a2.getFilesDir().getAbsolutePath();
        b = a2.getCacheDir().getAbsolutePath();
        f14314c = a + "/manto/" + com.jingdong.manto.b.m() + "/";
    }
}
