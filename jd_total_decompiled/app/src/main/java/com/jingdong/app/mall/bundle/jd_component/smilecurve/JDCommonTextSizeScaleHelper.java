package com.jingdong.app.mall.bundle.jd_component.smilecurve;

import android.animation.ObjectAnimator;
import android.widget.TextView;
import com.jd.dynamic.DYConstants;

/* loaded from: classes2.dex */
public class JDCommonTextSizeScaleHelper {
    public static void textSizeZoomIn(TextView textView, float f2, float f3) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(textView, DYConstants.DY_TEXT_SIZE, f2, f3);
        ofFloat.setDuration(300L);
        ofFloat.start();
    }

    public static void textSizeZoomOut(TextView textView, float f2, float f3) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(textView, DYConstants.DY_TEXT_SIZE, f2, f3);
        ofFloat.setDuration(300L);
        ofFloat.start();
    }
}
