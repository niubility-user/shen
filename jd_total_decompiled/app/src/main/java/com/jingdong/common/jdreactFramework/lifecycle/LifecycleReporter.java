package com.jingdong.common.jdreactFramework.lifecycle;

import android.text.TextUtils;
import com.jingdong.common.jdreactFramework.helper.LifecycleListener;

/* loaded from: classes5.dex */
public class LifecycleReporter {
    private static LifecycleListener sLifecycleListener;

    /* loaded from: classes5.dex */
    public enum Event {
        ON_CREATE,
        ON_RESUME,
        ON_PAUSE,
        ON_DESTROY
    }

    private static void dispatch(String str, Event event) {
        LifecycleListener lifecycleListener;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (event != null && (lifecycleListener = sLifecycleListener) != null) {
            lifecycleListener.onEvent(str, event);
        }
        LifecycleOwner.getInstance().dispatch(str, event);
    }

    public static void dispatchCreate(String str) {
        dispatch(str, Event.ON_CREATE);
    }

    public static void dispatchDestroy(String str) {
        dispatch(str, Event.ON_DESTROY);
    }

    public static void dispatchPause(String str) {
        dispatch(str, Event.ON_PAUSE);
    }

    public static void dispatchResume(String str) {
        dispatch(str, Event.ON_RESUME);
    }

    public static void setLifecycleListener(LifecycleListener lifecycleListener) {
        sLifecycleListener = lifecycleListener;
    }
}
