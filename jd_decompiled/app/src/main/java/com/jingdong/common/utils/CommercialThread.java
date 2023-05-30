package com.jingdong.common.utils;

import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes6.dex */
public class CommercialThread extends Thread {
    private static final String TAG = "CommercialThread";
    private CommercialThreadListener listner;
    public long sleepTime = Final.FIVE_SECOND;
    public boolean stop = false;
    public boolean isRunner = true;

    /* loaded from: classes6.dex */
    public interface CommercialThreadListener {
        void doRun();
    }

    public CommercialThread(CommercialThreadListener commercialThreadListener) {
        this.listner = commercialThreadListener;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        synchronized (this) {
            while (!this.stop) {
                try {
                    if (!this.isRunner) {
                        wait();
                    }
                    this.listner.doRun();
                    Thread.sleep(this.sleepTime);
                } catch (Exception e2) {
                    OKLog.e(TAG, e2);
                }
            }
        }
    }
}
