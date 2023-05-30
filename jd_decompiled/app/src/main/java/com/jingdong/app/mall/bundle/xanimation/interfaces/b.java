package com.jingdong.app.mall.bundle.xanimation.interfaces;

import android.animation.Animator;
import com.airbnb.lottie.LottieComposition;

/* loaded from: classes3.dex */
public interface b {
    void onAnimationCancel(Animator animator);

    void onAnimationEnd(Animator animator);

    void onAnimationError(Throwable th);

    void onAnimationRepeat(Animator animator);

    void onAnimationStart(Animator animator);

    void onAnimationUpdate(float f2);

    void onLayerStatusListener(String str, int i2, int i3);

    void onLottieLoadResult(LottieComposition lottieComposition);
}
