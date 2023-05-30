package com.jd.aips.tracker;

import android.os.Handler;
import android.os.HandlerThread;

/* loaded from: classes12.dex */
public class UemsWorkerThread extends HandlerThread {
    private Handler a;

    public UemsWorkerThread(String str) {
        super(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Runnable runnable) {
        synchronized (this) {
            if (this.a == null) {
                this.a = new Handler(getLooper());
            }
        }
        this.a.post(runnable);
    }
}
