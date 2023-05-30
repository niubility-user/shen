package com.jingdong.common.widget.video;

import android.content.Context;
import android.media.AudioManager;

/* loaded from: classes12.dex */
public class VideoWidgetUtil {
    public static boolean abandonFocus(Context context) {
        return ((AudioManager) context.getApplicationContext().getSystemService("audio")).abandonAudioFocus(null) == 1;
    }

    public static boolean requestFocus(Context context, int i2) {
        return ((AudioManager) context.getApplicationContext().getSystemService("audio")).requestAudioFocus(null, 3, i2) == 1;
    }

    public static boolean requestTransientFocus(Context context) {
        return requestFocus(context, 2);
    }

    public static boolean requestTransientMayDuckFocus(Context context) {
        return requestFocus(context, 3);
    }
}
