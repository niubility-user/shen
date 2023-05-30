package com.jingdong.common.jdreactFramework.track;

import com.jingdong.common.jdreactFramework.JDReactHelper;

/* loaded from: classes5.dex */
public class TrackUtil {
    public static void trackLoadFail(int i2, String str) {
        TrackListener trackListener = JDReactHelper.newInstance().getTrackListener();
        if (trackListener != null) {
            trackListener.onLoadFail(i2, str);
        }
    }

    public static void trackLoadFinish(String str) {
        TrackListener trackListener = JDReactHelper.newInstance().getTrackListener();
        if (trackListener != null) {
            trackListener.onLoadFinish(str);
        }
    }

    public static void trackLoadStart(String str) {
        TrackListener trackListener = JDReactHelper.newInstance().getTrackListener();
        if (trackListener != null) {
            trackListener.onLoadStart(str);
        }
    }

    public static void trackUpdateNode(int i2, int i3, String str) {
        TrackListener trackListener = JDReactHelper.newInstance().getTrackListener();
        if (trackListener != null) {
            trackListener.onUpdateNode(i2, i3, str);
        }
    }
}
