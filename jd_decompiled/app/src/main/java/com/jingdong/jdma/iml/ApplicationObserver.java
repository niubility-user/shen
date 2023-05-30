package com.jingdong.jdma.iml;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import com.jingdong.jdma.common.utils.LogUtil;

/* loaded from: classes.dex */
class ApplicationObserver implements LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onBackground() {
        LogUtil.w("JDMA_ApplicationObserver", "onBackground!");
        com.jingdong.jdma.common.utils.c.f12678h = true;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onForeground() {
        LogUtil.w("JDMA_ApplicationObserver", "onForeground!");
        com.jingdong.jdma.common.utils.c.f12678h = false;
    }
}
