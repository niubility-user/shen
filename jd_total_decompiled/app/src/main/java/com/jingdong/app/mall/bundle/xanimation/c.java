package com.jingdong.app.mall.bundle.xanimation;

import com.airbnb.lottie.animation.keyframe.FloatKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.TransformKeyframeAnimation;
import com.airbnb.lottie.model.layer.Layer;

/* loaded from: classes3.dex */
public class c {
    private Layer a;
    private TransformKeyframeAnimation b;

    /* renamed from: c  reason: collision with root package name */
    private FloatKeyframeAnimation f8301c;

    public FloatKeyframeAnimation a() {
        return this.f8301c;
    }

    public Layer b() {
        return this.a;
    }

    public TransformKeyframeAnimation c() {
        return this.b;
    }

    public void d(FloatKeyframeAnimation floatKeyframeAnimation) {
        this.f8301c = floatKeyframeAnimation;
    }

    public void e(Layer layer) {
        this.a = layer;
    }

    public void f(TransformKeyframeAnimation transformKeyframeAnimation) {
        this.b = transformKeyframeAnimation;
    }
}
