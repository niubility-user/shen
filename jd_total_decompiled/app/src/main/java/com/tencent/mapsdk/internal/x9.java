package com.tencent.mapsdk.internal;

import android.animation.ObjectAnimator;
import android.os.Build;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

/* loaded from: classes9.dex */
public class x9 {
    public static void a(View view, float f2) {
        if (Build.VERSION.SDK_INT >= 11) {
            view.setAlpha(f2);
            return;
        }
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, f2);
        alphaAnimation.setDuration(0L);
        alphaAnimation.setFillAfter(true);
        view.startAnimation(alphaAnimation);
    }

    public static void a(View view, float f2, float f3) {
        if (Build.VERSION.SDK_INT >= 11) {
            view.setScaleX(f2);
            view.setScaleY(f3);
            return;
        }
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, f2, 1.0f, f3);
        scaleAnimation.setDuration(0L);
        scaleAnimation.setFillAfter(true);
        view.startAnimation(scaleAnimation);
    }

    public static void a(Object obj, String str, int i2, float... fArr) {
        Animation scaleAnimation;
        if (Build.VERSION.SDK_INT >= 11) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(obj, str, fArr[0], fArr[1]);
            ofFloat.setDuration(i2);
            ofFloat.start();
            return;
        }
        if ("Alpha".equals(str) && (obj instanceof View)) {
            scaleAnimation = new AlphaAnimation(fArr[0], fArr[1]);
        } else if (!"Scale".equals(str) || !(obj instanceof View)) {
            return;
        } else {
            scaleAnimation = new ScaleAnimation(fArr[0], fArr[1], fArr[0], fArr[1]);
        }
        scaleAnimation.setDuration(i2);
        scaleAnimation.setFillAfter(true);
        ((View) obj).startAnimation(scaleAnimation);
    }

    public static boolean a(String str, String str2, int i2) {
        return d(str, str2, i2) == 0;
    }

    public static boolean b(String str, String str2, int i2) {
        return d(str, str2, i2) > 0;
    }

    public static boolean c(String str, String str2, int i2) {
        return d(str, str2, i2) < 0;
    }

    public static int d(String str, String str2, int i2) {
        int i3 = 0;
        if (e7.b(str) || e7.b(str2) || str.equals(str2)) {
            return 0;
        }
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        int i4 = 0;
        while (i3 < i2) {
            try {
                i4 = Integer.valueOf(Integer.parseInt(i3 < split.length ? split[i3] : "0")).compareTo(Integer.valueOf(Integer.parseInt(i3 < split2.length ? split2[i3] : "0")));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (i4 != 0) {
                break;
            }
            i3++;
        }
        return i4;
    }
}
