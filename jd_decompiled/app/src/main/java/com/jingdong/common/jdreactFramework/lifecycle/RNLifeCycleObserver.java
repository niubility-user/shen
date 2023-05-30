package com.jingdong.common.jdreactFramework.lifecycle;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.download.ReactNativeDownloadDispatcher;
import com.jingdong.common.jdreactFramework.track.RenderDataReport;
import com.jingdong.common.jdreactFramework.utils.JLog;
import java.util.Iterator;

/* loaded from: classes.dex */
public class RNLifeCycleObserver implements LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onBackground() {
        Iterator it = RenderDataReport.getInstance().getMap().keySet().iterator();
        while (it.hasNext()) {
            RenderDataReport.getInstance().setBackgroundStatus((String) it.next(), 1);
        }
        JDReactHelper.setBackground(1);
        ReactNativeDownloadDispatcher.getInstance().notifyDequeCountDown();
        JLog.d("RNLifeCycleObserver", "======onBackground=======");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onForeground() {
        ReactNativeDownloadDispatcher.getInstance().notifyDequeStart();
        JLog.d("RNLifeCycleObserver", "======onForeground=======");
    }
}
