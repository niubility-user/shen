package com.airbnb.lottie.animation.keyframe;

import android.graphics.Matrix;
import android.graphics.PointF;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import com.airbnb.lottie.value.ScaleXY;
import java.util.Collections;

/* loaded from: classes.dex */
public class TransformKeyframeAnimation {
    @NonNull
    private BaseKeyframeAnimation<PointF, PointF> anchorPoint;
    @Nullable
    private BaseKeyframeAnimation<?, Float> endOpacity;
    private final Matrix matrix = new Matrix();
    @NonNull
    private BaseKeyframeAnimation<Integer, Integer> opacity;
    @NonNull
    private BaseKeyframeAnimation<?, PointF> position;
    @NonNull
    private BaseKeyframeAnimation<Float, Float> rotation;
    @NonNull
    private BaseKeyframeAnimation<ScaleXY, ScaleXY> scale;
    @Nullable
    private FloatKeyframeAnimation skew;
    @Nullable
    private FloatKeyframeAnimation skewAngle;
    private final Matrix skewMatrix1;
    private final Matrix skewMatrix2;
    private final Matrix skewMatrix3;
    private final float[] skewValues;
    @Nullable
    private BaseKeyframeAnimation<?, Float> startOpacity;

    public TransformKeyframeAnimation(AnimatableTransform animatableTransform) {
        this.anchorPoint = animatableTransform.getAnchorPoint() == null ? null : animatableTransform.getAnchorPoint().createAnimation();
        this.position = animatableTransform.getPosition() == null ? null : animatableTransform.getPosition().createAnimation();
        this.scale = animatableTransform.getScale() == null ? null : animatableTransform.getScale().createAnimation();
        this.rotation = animatableTransform.getRotation() == null ? null : animatableTransform.getRotation().createAnimation();
        FloatKeyframeAnimation floatKeyframeAnimation = animatableTransform.getSkew() == null ? null : (FloatKeyframeAnimation) animatableTransform.getSkew().createAnimation();
        this.skew = floatKeyframeAnimation;
        if (floatKeyframeAnimation != null) {
            this.skewMatrix1 = new Matrix();
            this.skewMatrix2 = new Matrix();
            this.skewMatrix3 = new Matrix();
            this.skewValues = new float[9];
        } else {
            this.skewMatrix1 = null;
            this.skewMatrix2 = null;
            this.skewMatrix3 = null;
            this.skewValues = null;
        }
        this.skewAngle = animatableTransform.getSkewAngle() == null ? null : (FloatKeyframeAnimation) animatableTransform.getSkewAngle().createAnimation();
        if (animatableTransform.getOpacity() != null) {
            this.opacity = animatableTransform.getOpacity().createAnimation();
        }
        if (animatableTransform.getStartOpacity() != null) {
            this.startOpacity = animatableTransform.getStartOpacity().createAnimation();
        } else {
            this.startOpacity = null;
        }
        if (animatableTransform.getEndOpacity() != null) {
            this.endOpacity = animatableTransform.getEndOpacity().createAnimation();
        } else {
            this.endOpacity = null;
        }
    }

    private void clearSkewValues() {
        for (int i2 = 0; i2 < 9; i2++) {
            this.skewValues[i2] = 0.0f;
        }
    }

    public void addAnimationsToLayer(BaseLayer baseLayer) {
        baseLayer.addAnimation(this.opacity);
        baseLayer.addAnimation(this.startOpacity);
        baseLayer.addAnimation(this.endOpacity);
        baseLayer.addAnimation(this.anchorPoint);
        baseLayer.addAnimation(this.position);
        baseLayer.addAnimation(this.scale);
        baseLayer.addAnimation(this.rotation);
        baseLayer.addAnimation(this.skew);
        baseLayer.addAnimation(this.skewAngle);
    }

    public void addListener(BaseKeyframeAnimation.AnimationListener animationListener) {
        BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation = this.opacity;
        if (baseKeyframeAnimation != null) {
            baseKeyframeAnimation.addUpdateListener(animationListener);
        }
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation2 = this.startOpacity;
        if (baseKeyframeAnimation2 != null) {
            baseKeyframeAnimation2.addUpdateListener(animationListener);
        }
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation3 = this.endOpacity;
        if (baseKeyframeAnimation3 != null) {
            baseKeyframeAnimation3.addUpdateListener(animationListener);
        }
        BaseKeyframeAnimation<PointF, PointF> baseKeyframeAnimation4 = this.anchorPoint;
        if (baseKeyframeAnimation4 != null) {
            baseKeyframeAnimation4.addUpdateListener(animationListener);
        }
        BaseKeyframeAnimation<?, PointF> baseKeyframeAnimation5 = this.position;
        if (baseKeyframeAnimation5 != null) {
            baseKeyframeAnimation5.addUpdateListener(animationListener);
        }
        BaseKeyframeAnimation<ScaleXY, ScaleXY> baseKeyframeAnimation6 = this.scale;
        if (baseKeyframeAnimation6 != null) {
            baseKeyframeAnimation6.addUpdateListener(animationListener);
        }
        BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation7 = this.rotation;
        if (baseKeyframeAnimation7 != null) {
            baseKeyframeAnimation7.addUpdateListener(animationListener);
        }
        FloatKeyframeAnimation floatKeyframeAnimation = this.skew;
        if (floatKeyframeAnimation != null) {
            floatKeyframeAnimation.addUpdateListener(animationListener);
        }
        FloatKeyframeAnimation floatKeyframeAnimation2 = this.skewAngle;
        if (floatKeyframeAnimation2 != null) {
            floatKeyframeAnimation2.addUpdateListener(animationListener);
        }
    }

    public <T> boolean applyValueCallback(T t, @Nullable LottieValueCallback<T> lottieValueCallback) {
        FloatKeyframeAnimation floatKeyframeAnimation;
        FloatKeyframeAnimation floatKeyframeAnimation2;
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation;
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation2;
        if (t == LottieProperty.TRANSFORM_ANCHOR_POINT) {
            BaseKeyframeAnimation<PointF, PointF> baseKeyframeAnimation3 = this.anchorPoint;
            if (baseKeyframeAnimation3 == null) {
                this.anchorPoint = new ValueCallbackKeyframeAnimation(lottieValueCallback, new PointF());
                return true;
            }
            baseKeyframeAnimation3.setValueCallback(lottieValueCallback);
            return true;
        } else if (t == LottieProperty.TRANSFORM_POSITION) {
            BaseKeyframeAnimation<?, PointF> baseKeyframeAnimation4 = this.position;
            if (baseKeyframeAnimation4 == null) {
                this.position = new ValueCallbackKeyframeAnimation(lottieValueCallback, new PointF());
                return true;
            }
            baseKeyframeAnimation4.setValueCallback(lottieValueCallback);
            return true;
        } else {
            if (t == LottieProperty.TRANSFORM_POSITION_X) {
                BaseKeyframeAnimation<?, PointF> baseKeyframeAnimation5 = this.position;
                if (baseKeyframeAnimation5 instanceof SplitDimensionPathKeyframeAnimation) {
                    ((SplitDimensionPathKeyframeAnimation) baseKeyframeAnimation5).setXValueCallback(lottieValueCallback);
                    return true;
                }
            }
            if (t == LottieProperty.TRANSFORM_POSITION_Y) {
                BaseKeyframeAnimation<?, PointF> baseKeyframeAnimation6 = this.position;
                if (baseKeyframeAnimation6 instanceof SplitDimensionPathKeyframeAnimation) {
                    ((SplitDimensionPathKeyframeAnimation) baseKeyframeAnimation6).setYValueCallback(lottieValueCallback);
                    return true;
                }
            }
            if (t == LottieProperty.TRANSFORM_SCALE) {
                BaseKeyframeAnimation<ScaleXY, ScaleXY> baseKeyframeAnimation7 = this.scale;
                if (baseKeyframeAnimation7 == null) {
                    this.scale = new ValueCallbackKeyframeAnimation(lottieValueCallback, new ScaleXY());
                    return true;
                }
                baseKeyframeAnimation7.setValueCallback(lottieValueCallback);
                return true;
            } else if (t == LottieProperty.TRANSFORM_ROTATION) {
                BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation8 = this.rotation;
                if (baseKeyframeAnimation8 == null) {
                    this.rotation = new ValueCallbackKeyframeAnimation(lottieValueCallback, Float.valueOf(0.0f));
                    return true;
                }
                baseKeyframeAnimation8.setValueCallback(lottieValueCallback);
                return true;
            } else if (t == LottieProperty.TRANSFORM_OPACITY) {
                BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation9 = this.opacity;
                if (baseKeyframeAnimation9 == null) {
                    this.opacity = new ValueCallbackKeyframeAnimation(lottieValueCallback, 100);
                    return true;
                }
                baseKeyframeAnimation9.setValueCallback(lottieValueCallback);
                return true;
            } else if (t == LottieProperty.TRANSFORM_START_OPACITY && (baseKeyframeAnimation2 = this.startOpacity) != null) {
                if (baseKeyframeAnimation2 == null) {
                    this.startOpacity = new ValueCallbackKeyframeAnimation(lottieValueCallback, 100);
                    return true;
                }
                baseKeyframeAnimation2.setValueCallback(lottieValueCallback);
                return true;
            } else if (t == LottieProperty.TRANSFORM_END_OPACITY && (baseKeyframeAnimation = this.endOpacity) != null) {
                if (baseKeyframeAnimation == null) {
                    this.endOpacity = new ValueCallbackKeyframeAnimation(lottieValueCallback, 100);
                    return true;
                }
                baseKeyframeAnimation.setValueCallback(lottieValueCallback);
                return true;
            } else if (t == LottieProperty.TRANSFORM_SKEW && (floatKeyframeAnimation2 = this.skew) != null) {
                if (floatKeyframeAnimation2 == null) {
                    this.skew = new FloatKeyframeAnimation(Collections.singletonList(new Keyframe(Float.valueOf(0.0f))));
                }
                this.skew.setValueCallback(lottieValueCallback);
                return true;
            } else if (t != LottieProperty.TRANSFORM_SKEW_ANGLE || (floatKeyframeAnimation = this.skewAngle) == null) {
                return false;
            } else {
                if (floatKeyframeAnimation == null) {
                    this.skewAngle = new FloatKeyframeAnimation(Collections.singletonList(new Keyframe(Float.valueOf(0.0f))));
                }
                this.skewAngle.setValueCallback(lottieValueCallback);
                return true;
            }
        }
    }

    @Nullable
    public BaseKeyframeAnimation<PointF, PointF> getAnchorPoint() {
        return this.anchorPoint;
    }

    @Nullable
    public BaseKeyframeAnimation<?, Float> getEndOpacity() {
        return this.endOpacity;
    }

    public Matrix getMatrix() {
        float floatValue;
        this.matrix.reset();
        BaseKeyframeAnimation<?, PointF> baseKeyframeAnimation = this.position;
        if (baseKeyframeAnimation != null) {
            PointF value = baseKeyframeAnimation.getValue();
            float f2 = value.x;
            if (f2 != 0.0f || value.y != 0.0f) {
                this.matrix.preTranslate(f2, value.y);
            }
        }
        BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation2 = this.rotation;
        if (baseKeyframeAnimation2 != null) {
            if (baseKeyframeAnimation2 instanceof ValueCallbackKeyframeAnimation) {
                floatValue = baseKeyframeAnimation2.getValue().floatValue();
            } else {
                floatValue = ((FloatKeyframeAnimation) baseKeyframeAnimation2).getFloatValue();
            }
            if (floatValue != 0.0f) {
                this.matrix.preRotate(floatValue);
            }
        }
        if (this.skew != null) {
            float cos = this.skewAngle == null ? 0.0f : (float) Math.cos(Math.toRadians((-r0.getFloatValue()) + 90.0f));
            float sin = this.skewAngle == null ? 1.0f : (float) Math.sin(Math.toRadians((-r4.getFloatValue()) + 90.0f));
            clearSkewValues();
            float[] fArr = this.skewValues;
            fArr[0] = cos;
            fArr[1] = sin;
            float f3 = -sin;
            fArr[3] = f3;
            fArr[4] = cos;
            fArr[8] = 1.0f;
            this.skewMatrix1.setValues(fArr);
            clearSkewValues();
            float[] fArr2 = this.skewValues;
            fArr2[0] = 1.0f;
            fArr2[3] = (float) Math.tan(Math.toRadians(this.skew.getFloatValue()));
            fArr2[4] = 1.0f;
            fArr2[8] = 1.0f;
            this.skewMatrix2.setValues(fArr2);
            clearSkewValues();
            float[] fArr3 = this.skewValues;
            fArr3[0] = cos;
            fArr3[1] = f3;
            fArr3[3] = sin;
            fArr3[4] = cos;
            fArr3[8] = 1.0f;
            this.skewMatrix3.setValues(fArr3);
            this.skewMatrix2.preConcat(this.skewMatrix1);
            this.skewMatrix3.preConcat(this.skewMatrix2);
            this.matrix.preConcat(this.skewMatrix3);
        }
        BaseKeyframeAnimation<ScaleXY, ScaleXY> baseKeyframeAnimation3 = this.scale;
        if (baseKeyframeAnimation3 != null) {
            ScaleXY value2 = baseKeyframeAnimation3.getValue();
            if (value2.getScaleX() != 1.0f || value2.getScaleY() != 1.0f) {
                this.matrix.preScale(value2.getScaleX(), value2.getScaleY());
            }
        }
        BaseKeyframeAnimation<PointF, PointF> baseKeyframeAnimation4 = this.anchorPoint;
        if (baseKeyframeAnimation4 != null) {
            PointF value3 = baseKeyframeAnimation4.getValue();
            float f4 = value3.x;
            if (f4 != 0.0f || value3.y != 0.0f) {
                this.matrix.preTranslate(-f4, -value3.y);
            }
        }
        return this.matrix;
    }

    public Matrix getMatrixForRepeater(float f2) {
        BaseKeyframeAnimation<?, PointF> baseKeyframeAnimation = this.position;
        PointF value = baseKeyframeAnimation == null ? null : baseKeyframeAnimation.getValue();
        BaseKeyframeAnimation<ScaleXY, ScaleXY> baseKeyframeAnimation2 = this.scale;
        ScaleXY value2 = baseKeyframeAnimation2 == null ? null : baseKeyframeAnimation2.getValue();
        this.matrix.reset();
        if (value != null) {
            this.matrix.preTranslate(value.x * f2, value.y * f2);
        }
        if (value2 != null) {
            double d = f2;
            this.matrix.preScale((float) Math.pow(value2.getScaleX(), d), (float) Math.pow(value2.getScaleY(), d));
        }
        BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation3 = this.rotation;
        if (baseKeyframeAnimation3 != null) {
            float floatValue = baseKeyframeAnimation3.getValue().floatValue();
            BaseKeyframeAnimation<PointF, PointF> baseKeyframeAnimation4 = this.anchorPoint;
            PointF value3 = baseKeyframeAnimation4 != null ? baseKeyframeAnimation4.getValue() : null;
            this.matrix.preRotate(floatValue * f2, value3 == null ? 0.0f : value3.x, value3 != null ? value3.y : 0.0f);
        }
        return this.matrix;
    }

    @Nullable
    public BaseKeyframeAnimation<?, Integer> getOpacity() {
        return this.opacity;
    }

    @Nullable
    public BaseKeyframeAnimation<?, PointF> getPosition() {
        return this.position;
    }

    @Nullable
    public BaseKeyframeAnimation<Float, Float> getRotation() {
        return this.rotation;
    }

    @Nullable
    public BaseKeyframeAnimation<ScaleXY, ScaleXY> getScale() {
        return this.scale;
    }

    @Nullable
    public BaseKeyframeAnimation<?, Float> getStartOpacity() {
        return this.startOpacity;
    }

    public void setProgress(float f2) {
        BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation = this.opacity;
        if (baseKeyframeAnimation != null) {
            baseKeyframeAnimation.setProgress(f2);
        }
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation2 = this.startOpacity;
        if (baseKeyframeAnimation2 != null) {
            baseKeyframeAnimation2.setProgress(f2);
        }
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation3 = this.endOpacity;
        if (baseKeyframeAnimation3 != null) {
            baseKeyframeAnimation3.setProgress(f2);
        }
        BaseKeyframeAnimation<PointF, PointF> baseKeyframeAnimation4 = this.anchorPoint;
        if (baseKeyframeAnimation4 != null) {
            baseKeyframeAnimation4.setProgress(f2);
        }
        BaseKeyframeAnimation<?, PointF> baseKeyframeAnimation5 = this.position;
        if (baseKeyframeAnimation5 != null) {
            baseKeyframeAnimation5.setProgress(f2);
        }
        BaseKeyframeAnimation<ScaleXY, ScaleXY> baseKeyframeAnimation6 = this.scale;
        if (baseKeyframeAnimation6 != null) {
            baseKeyframeAnimation6.setProgress(f2);
        }
        BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation7 = this.rotation;
        if (baseKeyframeAnimation7 != null) {
            baseKeyframeAnimation7.setProgress(f2);
        }
        FloatKeyframeAnimation floatKeyframeAnimation = this.skew;
        if (floatKeyframeAnimation != null) {
            floatKeyframeAnimation.setProgress(f2);
        }
        FloatKeyframeAnimation floatKeyframeAnimation2 = this.skewAngle;
        if (floatKeyframeAnimation2 != null) {
            floatKeyframeAnimation2.setProgress(f2);
        }
    }
}
