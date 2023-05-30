package com.jingdong.jdpush_new.j;

import android.content.Context;
import java.lang.Thread;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes12.dex */
public class e implements Thread.UncaughtExceptionHandler {
    private static e a;

    private e() {
        new HashMap();
        new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault());
    }

    public static e a() {
        if (a == null) {
            a = new e();
        }
        return a;
    }

    public void b(Context context) {
        Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
    }
}
