package com.airbnb.lottie.animation.keyframe;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

/* loaded from: classes.dex */
public class PathKeyframeAnimation extends KeyframeAnimation<PointF> {
    private final PathMeasure pathMeasure;
    private PathKeyframe pathMeasureKeyframe;

    /* renamed from: point  reason: collision with root package name */
    private final PointF f814point;
    private final float[] pos;

    public PathKeyframeAnimation(List<? extends Keyframe<PointF>> list) {
        super(list);
        this.f814point = new PointF();
        this.pos = new float[2];
        this.pathMeasure = new PathMeasure();
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public /* bridge */ /* synthetic */ Object getValue(Keyframe keyframe, float f2) {
        return getValue((Keyframe<PointF>) keyframe, f2);
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public PointF getValue(Keyframe<PointF> keyframe, float f2) {
        PointF pointF;
        PathKeyframe pathKeyframe = (PathKeyframe) keyframe;
        Path path = pathKeyframe.getPath();
        if (path == null) {
            return keyframe.startValue;
        }
        LottieValueCallback<A> lottieValueCallback = this.valueCallback;
        if (lottieValueCallback == 0 || (pointF = (PointF) lottieValueCallback.getValueInternal(pathKeyframe.startFrame, pathKeyframe.endFrame.floatValue(), (PointF) pathKeyframe.startValue, (PointF) pathKeyframe.endValue, getLinearCurrentKeyframeProgress(), f2, getProgress())) == null) {
            if (this.pathMeasureKeyframe != pathKeyframe) {
                this.pathMeasure.setPath(path, false);
                this.pathMeasureKeyframe = pathKeyframe;
            }
            PathMeasure pathMeasure = this.pathMeasure;
            pathMeasure.getPosTan(f2 * pathMeasure.getLength(), this.pos, null);
            PointF pointF2 = this.f814point;
            float[] fArr = this.pos;
            pointF2.set(fArr[0], fArr[1]);
            return this.f814point;
        }
        return pointF;
    }
}
