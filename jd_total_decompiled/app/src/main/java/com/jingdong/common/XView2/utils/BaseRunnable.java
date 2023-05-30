package com.jingdong.common.XView2.utils;

import android.util.Log;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public abstract class BaseRunnable implements Runnable {
    private static final String TAG = BaseRunnable.class.getName();

    @Override // java.lang.Runnable
    public final void run() {
        try {
            safeRun();
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, Log.getStackTraceString(th));
            }
        }
    }

    protected abstract void safeRun();
}
