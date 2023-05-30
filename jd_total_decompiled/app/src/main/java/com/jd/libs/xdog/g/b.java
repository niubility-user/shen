package com.jd.libs.xdog.g;

import android.view.animation.TranslateAnimation;

/* loaded from: classes16.dex */
public class b {
    public static TranslateAnimation a() {
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
        translateAnimation.setDuration(300L);
        return translateAnimation;
    }

    public static TranslateAnimation b() {
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
        translateAnimation.setDuration(300L);
        return translateAnimation;
    }
}
