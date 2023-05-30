package com.jingdong.app.mall.home.deploy.view.layout.corelive2x2;

import android.animation.Animator;
import android.content.Context;
import com.jingdong.app.mall.home.floor.animation.lottie.HomeSimpleLottieView;

/* loaded from: classes4.dex */
public class LiveLottie extends HomeSimpleLottieView {
    public LiveLottie(Context context) {
        super(context);
        e("local/homeLiveLottie.json", "HOME_LIVE_LOTTIE");
        if (this.f9133g.get()) {
            setRepeatCount(0);
            addAnimatorListener(new Animator.AnimatorListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.corelive2x2.LiveLottie.1
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    LiveLottie.this.i();
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                }
            });
        }
    }
}
