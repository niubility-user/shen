package com.facebook.react.uimanager.layoutanimation;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.BaseInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.IllegalViewOperationException;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public abstract class AbstractLayoutAnimation {
    private static final Map<InterpolatorType, BaseInterpolator> INTERPOLATOR = MapBuilder.of(InterpolatorType.LINEAR, new LinearInterpolator(), InterpolatorType.EASE_IN, new AccelerateInterpolator(), InterpolatorType.EASE_OUT, new DecelerateInterpolator(), InterpolatorType.EASE_IN_EASE_OUT, new AccelerateDecelerateInterpolator());
    private static final boolean SLOWDOWN_ANIMATION_MODE = false;
    @Nullable
    protected AnimatedPropertyType mAnimatedProperty;
    private int mDelayMs;
    protected int mDurationMs;
    @Nullable
    private Interpolator mInterpolator;

    private static Interpolator getInterpolator(InterpolatorType interpolatorType, ReadableMap readableMap) {
        BaseInterpolator baseInterpolator;
        if (interpolatorType.equals(InterpolatorType.SPRING)) {
            baseInterpolator = new SimpleSpringInterpolator(SimpleSpringInterpolator.getSpringDamping(readableMap));
        } else {
            baseInterpolator = INTERPOLATOR.get(interpolatorType);
        }
        if (baseInterpolator != null) {
            return baseInterpolator;
        }
        throw new IllegalArgumentException("Missing interpolator for type : " + interpolatorType);
    }

    @Nullable
    public final Animation createAnimation(View view, int i2, int i3, int i4, int i5) {
        if (isValid()) {
            Animation createAnimationImpl = createAnimationImpl(view, i2, i3, i4, i5);
            if (createAnimationImpl != null) {
                createAnimationImpl.setDuration(this.mDurationMs * 1);
                createAnimationImpl.setStartOffset(this.mDelayMs * 1);
                createAnimationImpl.setInterpolator(this.mInterpolator);
            }
            return createAnimationImpl;
        }
        return null;
    }

    @Nullable
    abstract Animation createAnimationImpl(View view, int i2, int i3, int i4, int i5);

    public void initializeFromConfig(ReadableMap readableMap, int i2) {
        this.mAnimatedProperty = readableMap.hasKey("property") ? AnimatedPropertyType.fromString(readableMap.getString("property")) : null;
        if (readableMap.hasKey("duration")) {
            i2 = readableMap.getInt("duration");
        }
        this.mDurationMs = i2;
        this.mDelayMs = readableMap.hasKey("delay") ? readableMap.getInt("delay") : 0;
        if (readableMap.hasKey("type")) {
            this.mInterpolator = getInterpolator(InterpolatorType.fromString(readableMap.getString("type")), readableMap);
            if (isValid()) {
                return;
            }
            throw new IllegalViewOperationException("Invalid layout animation : " + readableMap);
        }
        throw new IllegalArgumentException("Missing interpolation type.");
    }

    abstract boolean isValid();

    public void reset() {
        this.mAnimatedProperty = null;
        this.mDurationMs = 0;
        this.mDelayMs = 0;
        this.mInterpolator = null;
    }
}
