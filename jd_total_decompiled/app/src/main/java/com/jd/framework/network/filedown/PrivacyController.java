package com.jd.framework.network.filedown;

import com.jingdong.jdsdk.network.config.RuntimeConfigHelper;
import com.jingdong.sdk.oklog.OKLog;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes13.dex */
public class PrivacyController {
    public static final long DEFAULT_DOWNLOAD_WINDOW = 210000;
    private static long bgTimeStart;
    private static AtomicBoolean isBackground = new AtomicBoolean();
    private static Object mLock = new Object();

    public static void awake() {
        synchronized (mLock) {
            mLock.notifyAll();
        }
    }

    public static void onAppBackground() {
        bgTimeStart = System.currentTimeMillis();
        isBackground.set(true);
    }

    public static void onAppForeground() {
        bgTimeStart = 0L;
        isBackground.set(false);
        awake();
    }

    public static boolean shouldSuspendTask() {
        boolean enableDownloadSuspend = RuntimeConfigHelper.enableDownloadSuspend();
        long downloadSuspendWindow = RuntimeConfigHelper.getDownloadSuspendWindow();
        if (OKLog.D) {
            OKLog.d("PrivacyController", "\u662f\u5426\u5f00\u542f\u4e0b\u8f7d\u5239\u8f66\u529f\u80fd: " + enableDownloadSuspend + ", \u540e\u53f0\u65f6\u95f4\u7a97\u53e3: " + downloadSuspendWindow);
        }
        return enableDownloadSuspend && isBackground.get() && System.currentTimeMillis() - bgTimeStart > downloadSuspendWindow;
    }

    public static void waiting() {
        synchronized (mLock) {
            try {
                mLock.wait();
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
    }
}
