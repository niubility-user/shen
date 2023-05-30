package com.jdpay.recover;

import android.os.Process;
import androidx.annotation.NonNull;
import com.jdpay.lib.cache.Cache;
import com.jdpay.lib.util.JDPayLog;
import java.lang.Thread;

/* loaded from: classes18.dex */
public abstract class CrashHandler implements Recoverable, Thread.UncaughtExceptionHandler {
    private static CrashHandler instance;
    protected final Cache cache;
    protected Thread.UncaughtExceptionHandler defaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
    protected boolean isKillSelfOnCrash;

    public CrashHandler(@NonNull Cache cache, boolean z) {
        this.cache = cache;
        this.isKillSelfOnCrash = z;
    }

    public static CrashHandler getInstance() {
        return instance;
    }

    public static CrashHandler init(CrashHandler crashHandler) {
        instance = crashHandler;
        return crashHandler;
    }

    public void destroy() {
        Thread.setDefaultUncaughtExceptionHandler(this.defaultExceptionHandler);
    }

    @Override // com.jdpay.recover.Recoverable
    public boolean isRecoverable() {
        return this.cache.length() > 0;
    }

    public void killSelf() {
        Process.killProcess(Process.myPid());
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        JDPayLog.e(th);
        onSave();
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.defaultExceptionHandler;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        } else if (this.isKillSelfOnCrash) {
            killSelf();
        }
    }
}
