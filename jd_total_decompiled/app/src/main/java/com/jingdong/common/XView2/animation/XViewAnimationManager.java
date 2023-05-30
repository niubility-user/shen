package com.jingdong.common.XView2.animation;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.jingdong.app.mall.bundle.xanimation.d;
import com.jingdong.app.mall.bundle.xanimation.e;

/* loaded from: classes5.dex */
public class XViewAnimationManager {
    private RotationAnimation mRotationAnimation;

    private void startRotationAnimation(Context context, View view) {
        RotationAnimation rotationAnimation = new RotationAnimation(view);
        this.mRotationAnimation = rotationAnimation;
        rotationAnimation.startRotationAnimation((Activity) context, new AnimateListener() { // from class: com.jingdong.common.XView2.animation.XViewAnimationManager.1
            @Override // com.jingdong.common.XView2.animation.AnimateListener
            public void onAnimateEnd() {
            }

            @Override // com.jingdong.common.XView2.animation.AnimateListener
            public void onAnimateError() {
            }

            @Override // com.jingdong.common.XView2.animation.AnimateListener
            public void onAnimateStart(long j2) {
            }
        });
    }

    public void destroyAnimation() {
        RotationAnimation rotationAnimation = this.mRotationAnimation;
        if (rotationAnimation != null) {
            rotationAnimation.destroyAnimation();
            this.mRotationAnimation = null;
        }
    }

    public void setXViewAnimation(Context context, View view, int i2) {
        if (i2 == 8) {
            startRotationAnimation(context, view);
            return;
        }
        d dVar = new d();
        e eVar = new e();
        if (i2 == 1) {
            eVar.b("fade_in_alpha.json");
        } else if (i2 == 2) {
            eVar.b("lottie/fadeout.zip");
        } else {
            if (i2 == 3) {
                eVar.b("lottie/breath.zip");
            } else if (i2 == 4) {
                eVar.b("lottie/shake.zip");
            } else if (i2 == 5) {
                eVar.b("lottie/pressbounce.zip");
            } else if (i2 == 6) {
                eVar.b("lottie/rotate360.zip");
            } else if (i2 == 7) {
                eVar.b("lottie/pendulum.zip");
            }
            eVar.c(-1);
        }
        eVar.a("mainLayer");
        eVar.d(view);
        dVar.l(context, eVar);
    }
}
