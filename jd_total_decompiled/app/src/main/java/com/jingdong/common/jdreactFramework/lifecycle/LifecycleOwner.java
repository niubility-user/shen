package com.jingdong.common.jdreactFramework.lifecycle;

import android.text.TextUtils;
import com.jingdong.common.jdreactFramework.lifecycle.LifecycleReporter;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class LifecycleOwner {
    private static volatile LifecycleOwner sInstance;
    private ConcurrentHashMap<String, LifecycleReporter.Event> mLifecycleMap = new ConcurrentHashMap<>();

    private LifecycleOwner() {
    }

    public static LifecycleOwner getInstance() {
        if (sInstance == null) {
            synchronized (LifecycleOwner.class) {
                if (sInstance == null) {
                    sInstance = new LifecycleOwner();
                }
            }
        }
        return sInstance;
    }

    public void dispatch(String str, LifecycleReporter.Event event) {
        if (event == LifecycleReporter.Event.ON_DESTROY) {
            this.mLifecycleMap.remove(str);
        } else {
            this.mLifecycleMap.put(str, event);
        }
    }

    public boolean isRunning(String str) {
        return (TextUtils.isEmpty(str) || this.mLifecycleMap.get(str) == null || this.mLifecycleMap.get(str) == LifecycleReporter.Event.ON_DESTROY) ? false : true;
    }
}
