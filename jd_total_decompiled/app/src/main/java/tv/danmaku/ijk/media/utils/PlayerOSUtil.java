package tv.danmaku.ijk.media.utils;

import android.os.Build;

/* loaded from: classes11.dex */
public class PlayerOSUtil {
    public static boolean belowKitKat() {
        return Build.VERSION.SDK_INT <= 19;
    }
}
