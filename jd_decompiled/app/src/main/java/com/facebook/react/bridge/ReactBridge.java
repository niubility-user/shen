package com.facebook.react.bridge;

import android.os.SystemClock;
import com.facebook.soloader.SoLoader;

/* loaded from: classes.dex */
public class ReactBridge {
    private static boolean sDidInit;
    private static volatile long sLoadEndTime;
    private static volatile long sLoadStartTime;

    public static long getLoadEndTime() {
        return sLoadEndTime;
    }

    public static long getLoadStartTime() {
        return sLoadStartTime;
    }

    public static synchronized void staticInit() {
        synchronized (ReactBridge.class) {
            if (sDidInit) {
                return;
            }
            sDidInit = true;
            sLoadStartTime = SystemClock.uptimeMillis();
            com.facebook.systrace.Systrace.beginSection(0L, "ReactBridge.staticInit::load:reactnativejni");
            ReactMarker.logMarker(ReactMarkerConstants.LOAD_REACT_NATIVE_SO_FILE_START);
            SoLoader.loadLibrary("reactnativejni");
            ReactMarker.logMarker(ReactMarkerConstants.LOAD_REACT_NATIVE_SO_FILE_END);
            com.facebook.systrace.Systrace.endSection(0L);
            sLoadEndTime = SystemClock.uptimeMillis();
        }
    }
}
