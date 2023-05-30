package com.jingdong.common.unification.video.player;

import android.content.Context;
import android.media.AudioManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class VideoPlayUtil {
    private static Map<Integer, Animation> cacheAnimation = new HashMap();

    public static void clearAnimationCache() {
        cacheAnimation.clear();
    }

    public static String generateTime(int i2) {
        int i3 = i2 / 1000;
        int i4 = i3 % 60;
        int i5 = (i3 / 60) % 60;
        int i6 = i3 / R2.color.c_f2f3f3;
        return i6 > 0 ? String.format("%02d:%02d:%02d", Integer.valueOf(i6), Integer.valueOf(i5), Integer.valueOf(i4)) : String.format("%02d:%02d", Integer.valueOf(i5), Integer.valueOf(i4));
    }

    public static Animation getAnimation(Context context, int i2, Animation.AnimationListener animationListener) {
        Animation loadAnimation = AnimationUtils.loadAnimation(context, i2);
        if (animationListener != null) {
            loadAnimation.setAnimationListener(animationListener);
        }
        return loadAnimation;
    }

    public static boolean hasAudioPlay(Context context) {
        return ((AudioManager) context.getSystemService("audio")).isMusicActive();
    }

    public static boolean isMobileNet() {
        return NetUtils.is3GOr2GNetwork();
    }

    public static boolean muteAudioFocus(Context context, boolean z) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        if (z) {
            if (audioManager.requestAudioFocus(null, 3, 2) != 1) {
                return false;
            }
        } else if (audioManager.abandonAudioFocus(null) != 1) {
            return false;
        }
        return true;
    }
}
