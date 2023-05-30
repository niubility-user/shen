package com.jd.framework.network.filedown;

import android.content.Context;
import com.android.volley.toolbox.HttpStackFactory;
import com.jd.framework.network.filedown.internal.BaseDownloader;
import com.jd.framework.network.request.JDFileRequestQueue;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes13.dex */
public class JDFileRequestConsumer extends Thread {
    public static final String TAG = "JDFileRequestConsumer";
    private final Context mContext;
    private final JDFileRequestQueue mQueue;
    private volatile boolean mQuit = false;
    private HttpStackFactory mStackFactory;

    public JDFileRequestConsumer(JDFileRequestQueue jDFileRequestQueue, HttpStackFactory httpStackFactory, Context context) {
        this.mQueue = jDFileRequestQueue;
        this.mContext = context;
        this.mStackFactory = httpStackFactory;
    }

    public void quit() {
        this.mQuit = true;
        interrupt();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        while (true) {
            try {
                if (PrivacyController.shouldSuspendTask()) {
                    if (OKLog.D) {
                        OKLog.d(TAG, "\u4e0b\u8f7d\u4f11\u7720>>>>>>>, \u7ebf\u7a0bID: " + getId() + ", \u961f\u5217\u957f\u5ea6: " + this.mQueue.getQueueSize());
                    }
                    PrivacyController.waiting();
                }
                if (OKLog.D) {
                    OKLog.d(TAG, "\u4e0b\u8f7d\u6267\u884c>>>>>>>, \u7ebf\u7a0bID: " + getId() + ", \u961f\u5217\u957f\u5ea6: " + this.mQueue.getQueueSize());
                }
                BaseDownloader.executeAction(this.mContext, this.mQueue.take(), this.mStackFactory);
            } catch (InterruptedException unused) {
                if (this.mQuit) {
                    return;
                }
            }
        }
    }
}
