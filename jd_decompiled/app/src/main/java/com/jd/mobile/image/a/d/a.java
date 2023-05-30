package com.jd.mobile.image.a.d;

import com.facebook.fresco.animation.drawable.AnimatedDrawable2;
import com.facebook.fresco.animation.drawable.BaseAnimationListener;
import com.jd.mobile.image.listener.BaseImageRequestListener;

/* loaded from: classes17.dex */
public class a extends BaseAnimationListener {
    private int a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    private BaseImageRequestListener f6842c;

    public a(int i2) {
        this(i2, null);
    }

    public a(int i2, BaseImageRequestListener baseImageRequestListener) {
        this.a = 0;
        this.b = 0;
        this.a = i2;
        this.b = 0;
        this.f6842c = baseImageRequestListener;
    }

    @Override // com.facebook.fresco.animation.drawable.BaseAnimationListener, com.facebook.fresco.animation.drawable.AnimationListener
    public void onAnimationFrame(AnimatedDrawable2 animatedDrawable2, int i2) {
        if (this.a > 0 && i2 == animatedDrawable2.getFrameCount() - 1) {
            int i3 = this.b + 1;
            this.b = i3;
            if (i3 == this.a) {
                animatedDrawable2.stop();
                this.b = 0;
            }
        }
    }

    @Override // com.facebook.fresco.animation.drawable.BaseAnimationListener, com.facebook.fresco.animation.drawable.AnimationListener
    public void onAnimationStart(AnimatedDrawable2 animatedDrawable2) {
    }

    @Override // com.facebook.fresco.animation.drawable.BaseAnimationListener, com.facebook.fresco.animation.drawable.AnimationListener
    public void onAnimationStop(AnimatedDrawable2 animatedDrawable2) {
        BaseImageRequestListener baseImageRequestListener = this.f6842c;
        if (baseImageRequestListener != null) {
            baseImageRequestListener.onAnimationStop();
        }
    }
}
