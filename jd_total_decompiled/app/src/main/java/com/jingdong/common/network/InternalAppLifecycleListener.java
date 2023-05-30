package com.jingdong.common.network;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import com.jd.framework.network.filedown.PrivacyController;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes.dex */
public class InternalAppLifecycleListener implements LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onBackground() {
        if (OKLog.D) {
            OKLog.d("LifecycleObserver", "\u5e94\u7528\u9000\u5230\u540e\u53f0");
        }
        PrivacyController.onAppBackground();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onForeground() {
        if (OKLog.D) {
            OKLog.d("LifecycleObserver", "\u5e94\u7528\u56de\u5230\u524d\u53f0");
        }
        PrivacyController.onAppForeground();
    }
}
