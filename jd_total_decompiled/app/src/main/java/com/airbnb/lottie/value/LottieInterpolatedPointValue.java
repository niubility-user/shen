package com.airbnb.lottie.value;

import android.graphics.PointF;
import android.view.animation.Interpolator;
import com.airbnb.lottie.utils.MiscUtils;

/* loaded from: classes.dex */
public class LottieInterpolatedPointValue extends LottieInterpolatedValue<PointF> {

    /* renamed from: point  reason: collision with root package name */
    private final PointF f818point;

    public LottieInterpolatedPointValue(PointF pointF, PointF pointF2) {
        super(pointF, pointF2);
        this.f818point = new PointF();
    }

    @Override // com.airbnb.lottie.value.LottieInterpolatedValue, com.airbnb.lottie.value.LottieValueCallback
    public /* bridge */ /* synthetic */ Object getValue(LottieFrameInfo lottieFrameInfo) {
        return super.getValue(lottieFrameInfo);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.airbnb.lottie.value.LottieInterpolatedValue
    public PointF interpolateValue(PointF pointF, PointF pointF2, float f2) {
        this.f818point.set(MiscUtils.lerp(pointF.x, pointF2.x, f2), MiscUtils.lerp(pointF.y, pointF2.y, f2));
        return this.f818point;
    }

    public LottieInterpolatedPointValue(PointF pointF, PointF pointF2, Interpolator interpolator) {
        super(pointF, pointF2, interpolator);
        this.f818point = new PointF();
    }
}
