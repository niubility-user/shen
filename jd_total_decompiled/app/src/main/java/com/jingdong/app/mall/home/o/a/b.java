package com.jingdong.app.mall.home.o.a;

import android.util.Log;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes4.dex */
public abstract class b implements Runnable {
    private static final String TAG = b.class.getName();

    public void onError(Throwable th) {
        if (OKLog.E) {
            OKLog.e("JD_Home_".concat(TAG), Log.getStackTraceString(th));
        }
        l.j(th);
        if (k.w()) {
            f.o(th.getMessage());
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            safeRun();
        } catch (Throwable th) {
            onError(th);
        }
    }

    protected abstract void safeRun();
}
