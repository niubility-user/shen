package com.jingdong.pdj.libcore.utils;

import android.util.Log;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes7.dex */
public abstract class HourlyGoSafeRunnable implements Runnable {
    @Override // java.lang.Runnable
    public final void run() {
        try {
            safeRun();
        } catch (Throwable th) {
            th.printStackTrace();
            OKLog.e(HourlyGoLibConstant.HOURLY_GO_EXCEPTION, Log.getStackTraceString(th));
            ExceptionReporter.reportExceptionToBugly(th);
        }
    }

    protected abstract void safeRun();
}
