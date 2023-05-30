package com.facebook.react.bridge;

import com.facebook.proguard.annotations.DoNotStrip;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

@DoNotStrip
/* loaded from: classes.dex */
public class ReactMarker {
    private static final List<MarkerListener> sListeners = new ArrayList();

    /* loaded from: classes.dex */
    public interface MarkerListener {
        void logMarker(ReactMarkerConstants reactMarkerConstants, @Nullable String str, int i2);
    }

    @DoNotStrip
    public static void addListener(MarkerListener markerListener) {
        List<MarkerListener> list = sListeners;
        synchronized (list) {
            if (!list.contains(markerListener)) {
                list.add(markerListener);
            }
        }
    }

    @DoNotStrip
    public static void clearMarkerListeners() {
        List<MarkerListener> list = sListeners;
        synchronized (list) {
            list.clear();
        }
    }

    @DoNotStrip
    public static void logMarker(String str) {
        logMarker(str, (String) null);
    }

    @DoNotStrip
    public static void removeListener(MarkerListener markerListener) {
        List<MarkerListener> list = sListeners;
        synchronized (list) {
            list.remove(markerListener);
        }
    }

    @DoNotStrip
    public static void logMarker(String str, int i2) {
        logMarker(str, (String) null, i2);
    }

    @DoNotStrip
    public static void logMarker(String str, @Nullable String str2) {
        logMarker(str, str2, 0);
    }

    @DoNotStrip
    public static void logMarker(String str, @Nullable String str2, int i2) {
        logMarker(ReactMarkerConstants.valueOf(str), str2, i2);
    }

    @DoNotStrip
    public static void logMarker(ReactMarkerConstants reactMarkerConstants) {
        logMarker(reactMarkerConstants, (String) null, 0);
    }

    @DoNotStrip
    public static void logMarker(ReactMarkerConstants reactMarkerConstants, int i2) {
        logMarker(reactMarkerConstants, (String) null, i2);
    }

    @DoNotStrip
    public static void logMarker(ReactMarkerConstants reactMarkerConstants, @Nullable String str) {
        logMarker(reactMarkerConstants, str, 0);
    }

    @DoNotStrip
    public static void logMarker(ReactMarkerConstants reactMarkerConstants, @Nullable String str, int i2) {
        List<MarkerListener> list = sListeners;
        synchronized (list) {
            Iterator<MarkerListener> it = list.iterator();
            while (it.hasNext()) {
                it.next().logMarker(reactMarkerConstants, str, i2);
            }
        }
    }
}
