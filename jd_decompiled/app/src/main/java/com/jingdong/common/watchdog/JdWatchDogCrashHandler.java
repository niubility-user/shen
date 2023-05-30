package com.jingdong.common.watchdog;

import android.content.Context;
import java.lang.Thread;

/* loaded from: classes6.dex */
public class JdWatchDogCrashHandler implements Thread.UncaughtExceptionHandler {
    private static JdWatchDogCrashHandler INSTANCE;
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    private JdWatchDogCrashHandler() {
    }

    public static JdWatchDogCrashHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new JdWatchDogCrashHandler();
        }
        return INSTANCE;
    }

    public void init(Context context) {
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
    }
}
