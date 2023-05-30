package com.jd.libs.hybrid.xbehavior.lifecycle;

import android.os.Build;
import com.jd.hybrid.downloader.j;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.util.DatabaseExecutors;
import com.jd.libs.hybrid.offlineload.OfflineLoadController;

/* loaded from: classes16.dex */
public class LifeCycleDispatcher {
    public static void dispatchOnCreate(final String str, String str2) {
        DatabaseExecutors.getInstance().runOnIoThread(new Runnable() { // from class: com.jd.libs.hybrid.xbehavior.lifecycle.LifeCycleDispatcher.1
            @Override // java.lang.Runnable
            public void run() {
                j.l().q(str);
                if (Build.VERSION.SDK_INT < 23 || !HybridSettings.isInited()) {
                    return;
                }
                OfflineLoadController.getInstance(HybridSettings.getAppContext()).startDownload(str);
            }
        });
    }

    public static void dispatchOnDestroy(String str, String str2) {
    }
}
