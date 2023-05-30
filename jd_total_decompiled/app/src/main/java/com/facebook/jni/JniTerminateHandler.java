package com.facebook.jni;

import java.lang.Thread;

/* loaded from: classes.dex */
public class JniTerminateHandler {
    public static void handleTerminate(Throwable th) throws Throwable {
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler == null) {
            return;
        }
        defaultUncaughtExceptionHandler.uncaughtException(Thread.currentThread(), th);
    }
}
