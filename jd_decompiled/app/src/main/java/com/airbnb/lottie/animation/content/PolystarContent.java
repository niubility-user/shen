package com.airbnb.lottie.animation.content;

import android.graphics.Path;
import android.graphics.PointF;
import androidx.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.PolystarShape;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

/* loaded from: classes.dex */
public class PolystarContent implements PathContent, BaseKeyframeAnimation.AnimationListener, KeyPathElementContent {
    private static final float POLYGON_MAGIC_NUMBER = 0.25f;
    private static final float POLYSTAR_MAGIC_NUMBER = 0.47829f;
    private final boolean hidden;
    @Nullable
    private final BaseKeyframeAnimation<?, Float> innerRadiusAnimation;
    @Nullable
    private final BaseKeyframeAnimation<?, Float> innerRoundednessAnimation;
    private boolean isPathValid;
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final BaseKeyframeAnimation<?, Float> outerRadiusAnimation;
    private final BaseKeyframeAnimation<?, Float> outerRoundednessAnimation;
    private final BaseKeyframeAnimation<?, Float> pointsAnimation;
    private final BaseKeyframeAnimation<?, PointF> positionAnimation;
    private final BaseKeyframeAnimation<?, Float> rotationAnimation;
    private final PolystarShape.Type type;
    private final Path path = new Path();
    private CompoundTrimPathContent trimPaths = new CompoundTrimPathContent();

    /* renamed from: com.airbnb.lottie.animation.content.PolystarContent$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$airbnb$lottie$model$content$PolystarShape$Type;

        static {
            int[] iArr = new int[PolystarShape.Type.values().length];
            $SwitchMap$com$airbnb$lottie$model$content$PolystarShape$Type = iArr;
            try {
                iArr[PolystarShape.Type.STAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$content$PolystarShape$Type[PolystarShape.Type.POLYGON.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public PolystarContent(LottieDrawable lottieDrawable, BaseLayer baseLayer, PolystarShape polystarShape) {
        this.lottieDrawable = lottieDrawable;
        this.name = polystarShape.getName();
        PolystarShape.Type type = polystarShape.getType();
        this.type = type;
        this.hidden = polystarShape.isHidden();
        BaseKeyframeAnimation<Float, Float> createAnimation = polystarShape.getPoints().createAnimation();
        this.pointsAnimation = createAnimation;
        BaseKeyframeAnimation<PointF, PointF> createAnimation2 = polystarShape.getPosition().createAnimation();
        this.positionAnimation = createAnimation2;
        BaseKeyframeAnimation<Float, Float> createAnimation3 = polystarShape.getRotation().createAnimation();
        this.rotationAnimation = createAnimation3;
        BaseKeyframeAnimation<Float, Float> createAnimation4 = polystarShape.getOuterRadius().createAnimation();
        this.outerRadiusAnimation = createAnimation4;
        BaseKeyframeAnimation<Float, Float> createAnimation5 = polystarShape.getOuterRoundedness().createAnimation();
        this.outerRoundednessAnimation = createAnimation5;
        PolystarShape.Type type2 = PolystarShape.Type.STAR;
        if (type == type2) {
            this.innerRadiusAnimation = polystarShape.getInnerRadius().createAnimation();
            this.innerRoundednessAnimation = polystarShape.getInnerRoundedness().createAnimation();
        } else {
            this.innerRadiusAnimation = null;
            this.innerRoundednessAnimation = null;
        }
        baseLayer.addAnimation(createAnimation);
        baseLayer.addAnimation(createAnimation2);
        baseLayer.addAnimation(createAnimation3);
        baseLayer.addAnimation(createAnimation4);
        baseLayer.addAnimation(createAnimation5);
        if (type == type2) {
            baseLayer.addAnimation(this.innerRadiusAnimation);
            baseLayer.addAnimation(this.innerRoundednessAnimation);
        }
        createAnimation.addUpdateListener(this);
        createAnimation2.addUpdateListener(this);
        createAnimation3.addUpdateListener(this);
        createAnimation4.addUpdateListener(this);
        createAnimation5.addUpdateListener(this);
        if (type == type2) {
            this.innerRadiusAnimation.addUpdateListener(this);
            this.innerRoundednessAnimation.addUpdateListener(this);
        }
    }

    private void createPolygonPath() {
        double d;
        double d2;
        double d3;
        int i2;
        int floor = (int) Math.floor(this.pointsAnimation.getValue().floatValue());
        double radians = Math.toRadians((this.rotationAnimation == null ? 0.0d : r2.getValue().floatValue()) - 90.0d);
        double d4 = floor;
        Double.isNaN(d4);
        float floatValue = this.outerRoundednessAnimation.getValue().floatValue() / 100.0f;
        float floatValue2 = this.outerRadiusAnimation.getValue().floatValue();
        double d5 = floatValue2;
        double cos = Math.cos(radians);
        Double.isNaN(d5);
        float f2 = (float) (cos * d5);
        double sin = Math.sin(radians);
        Double.isNaN(d5);
        float f3 = (float) (sin * d5);
        this.path.moveTo(f2, f3);
        double d6 = (float) (6.283185307179586d / d4);
        Double.isNaN(d6);
        double d7 = radians + d6;
        double ceil = Math.ceil(d4);
        int i3 = 0;
        while (i3 < ceil) {
            double cos2 = Math.cos(d7);
            Double.isNaN(d5);
            float f4 = (float) (cos2 * d5);
            double sin2 = Math.sin(d7);
            Double.isNaN(d5);
            double d8 = ceil;
            float f5 = (float) (d5 * sin2);
            if (floatValue != 0.0f) {
                d2 = d5;
                i2 = i3;
                d = d7;
                double atan2 = (float) (Math.atan2(f3, f2) - 1.5707963267948966d);
                float cos3 = (float) Math.cos(atan2);
                d3 = d6;
                double atan22 = (float) (Math.atan2(f5, f4) - 1.5707963267948966d);
                float f6 = floatValue2 * floatValue * POLYGON_MAGIC_NUMBER;
                this.path.cubicTo(f2 - (cos3 * f6), f3 - (((float) Math.sin(atan2)) * f6), f4 + (((float) Math.cos(atan22)) * f6), f5 + (f6 * ((float) Math.sin(atan22))), f4, f5);
            } else {
                d = d7;
                d2 = d5;
                d3 = d6;
                i2 = i3;
                this.path.lineTo(f4, f5);
            }
            Double.isNaN(d3);
            d7 = d + d3;
            i3 = i2 + 1;
            f3 = f5;
            f2 = f4;
            ceil = d8;
            d5 = d2;
            d6 = d3;
        }
        PointF value = this.positionAnimation.getValue();
        this.path.offset(value.x, value.y);
        this.path.close();
    }

    private void createStarPath() {
        double d;
        float f2;
        float f3;
        float f4;
        float f5;
        double d2;
        float f6;
        float f7;
        float f8;
        float f9;
        float f10;
        float f11;
        double d3;
        float f12;
        float f13;
        float floatValue = this.pointsAnimation.getValue().floatValue();
        double radians = Math.toRadians((this.rotationAnimation == null ? 0.0d : r2.getValue().floatValue()) - 90.0d);
        double d4 = floatValue;
        Double.isNaN(d4);
        float f14 = (float) (6.283185307179586d / d4);
        float f15 = f14 / 2.0f;
        float f16 = floatValue - ((int) floatValue);
        if (f16 != 0.0f) {
            double d5 = (1.0f - f16) * f15;
            Double.isNaN(d5);
            radians += d5;
        }
        float floatValue2 = this.outerRadiusAnimation.getValue().floatValue();
        float floatValue3 = this.innerRadiusAnimation.getValue().floatValue();
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation = this.innerRoundednessAnimation;
        float floatValue4 = baseKeyframeAnimation != null ? baseKeyframeAnimation.getValue().floatValue() / 100.0f : 0.0f;
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation2 = this.outerRoundednessAnimation;
        float floatValue5 = baseKeyframeAnimation2 != null ? baseKeyframeAnimation2.getValue().floatValue() / 100.0f : 0.0f;
        if (f16 != 0.0f) {
            float f17 = ((floatValue2 - floatValue3) * f16) + floatValue3;
            double d6 = f17;
            double cos = Math.cos(radians);
            Double.isNaN(d6);
            d = d4;
            float f18 = (float) (d6 * cos);
            double sin = Math.sin(radians);
            Double.isNaN(d6);
            float f19 = (float) (d6 * sin);
            this.path.moveTo(f18, f19);
            double d7 = (f14 * f16) / 2.0f;
            Double.isNaN(d7);
            d2 = radians + d7;
            f4 = f18;
            f6 = f17;
            f2 = floatValue2;
            f5 = f19;
            f3 = f15;
        } else {
            d = d4;
            f2 = floatValue2;
            double d8 = f2;
            double cos2 = Math.cos(radians);
            Double.isNaN(d8);
            f3 = f15;
            f4 = (float) (d8 * cos2);
            double sin2 = Math.sin(radians);
            Double.isNaN(d8);
            f5 = (float) (d8 * sin2);
            this.path.moveTo(f4, f5);
            double d9 = f3;
            Double.isNaN(d9);
            d2 = radians + d9;
            f6 = 0.0f;
        }
        double ceil = Math.ceil(d) * 2.0d;
        int i2 = 0;
        float f20 = floatValue5;
        boolean z = false;
        while (true) {
            double d10 = i2;
            if (d10 < ceil) {
                float f21 = z ? f2 : floatValue3;
                float f22 = (f6 == 0.0f || d10 != ceil - 2.0d) ? f3 : (f14 * f16) / 2.0f;
                if (f6 == 0.0f || d10 != ceil - 1.0d) {
                    f7 = f14;
                    f8 = f21;
                    f9 = f2;
                } else {
                    f7 = f14;
                    f9 = f2;
                    f8 = f6;
                }
                double d11 = f8;
                double cos3 = Math.cos(d2);
                Double.isNaN(d11);
                float f23 = (float) (d11 * cos3);
                double sin3 = Math.sin(d2);
                Double.isNaN(d11);
                float f24 = (float) (d11 * sin3);
                if (floatValue4 == 0.0f && f20 == 0.0f) {
                    this.path.lineTo(f23, f24);
                    d3 = d2;
                    f10 = floatValue3;
                    f11 = floatValue4;
                    f12 = f3;
                    f13 = f22;
                } else {
                    f10 = floatValue3;
                    f11 = floatValue4;
                    double atan2 = (float) (Math.atan2(f5, f4) - 1.5707963267948966d);
                    float cos4 = (float) Math.cos(atan2);
                    float sin4 = (float) Math.sin(atan2);
                    d3 = d2;
                    f12 = f3;
                    f13 = f22;
                    double atan22 = (float) (Math.atan2(f24, f23) - 1.5707963267948966d);
                    float cos5 = (float) Math.cos(atan22);
                    float sin5 = (float) Math.sin(atan22);
                    float f25 = z ? f11 : f20;
                    float f26 = z ? f20 : f11;
                    float f27 = z ? f10 : f9;
                    float f28 = z ? f9 : f10;
                    float f29 = f27 * f25 * POLYSTAR_MAGIC_NUMBER;
                    float f30 = cos4 * f29;
                    float f31 = f29 * sin4;
                    float f32 = f28 * f26 * POLYSTAR_MAGIC_NUMBER;
                    float f33 = cos5 * f32;
                    float f34 = f32 * sin5;
                    if (f16 != 0.0f) {
                        if (i2 == 0) {
                            f30 *= f16;
                            f31 *= f16;
                        } else if (d10 == ceil - 1.0d) {
                            f33 *= f16;
                            f34 *= f16;
                        }
                    }
                    this.path.cubicTo(f4 - f30, f5 - f31, f23 + f33, f24 + f34, f23, f24);
                }
                double d12 = f13;
                Double.isNaN(d12);
                z = !z;
                i2++;
                f5 = f24;
                d2 = d3 + d12;
                f3 = f12;
                f4 = f23;
                f2 = f9;
                f14 = f7;
                floatValue3 = f10;
                floatValue4 = f11;
            } else {
                PointF value = this.positionAnimation.getValue();
                this.path.offset(value.x, value.y);
                this.path.close();
                return;
            }
        }
    }

    private void invalidate() {
        this.isPathValid = false;
        this.lottieDrawable.invalidateSelf();
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public <T> void addValueCallback(T t, @Nullable LottieValueCallback<T> lottieValueCallback) {
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation;
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation2;
        if (t == LottieProperty.POLYSTAR_POINTS) {
            this.pointsAnimation.setValueCallback(lottieValueCallback);
        } else if (t == LottieProperty.POLYSTAR_ROTATION) {
            this.rotationAnimation.setValueCallback(lottieValueCallback);
        } else if (t == LottieProperty.POSITION) {
            this.positionAnimation.setValueCallback(lottieValueCallback);
        } else if (t == LottieProperty.POLYSTAR_INNER_RADIUS && (baseKeyframeAnimation2 = this.innerRadiusAnimation) != null) {
            baseKeyframeAnimation2.setValueCallback(lottieValueCallback);
        } else if (t == LottieProperty.POLYSTAR_OUTER_RADIUS) {
            this.outerRadiusAnimation.setValueCallback(lottieValueCallback);
        } else if (t == LottieProperty.POLYSTAR_INNER_ROUNDEDNESS && (baseKeyframeAnimation = this.innerRoundednessAnimation) != null) {
            baseKeyframeAnimation.setValueCallback(lottieValueCallback);
        } else if (t == LottieProperty.POLYSTAR_OUTER_ROUNDEDNESS) {
            this.outerRoundednessAnimation.setValueCallback(lottieValueCallback);
        }
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.name;
    }

    @Override // com.airbnb.lottie.animation.content.PathContent
    public Path getPath() {
        if (this.isPathValid) {
            return this.path;
        }
        this.path.reset();
        if (this.hidden) {
            this.isPathValid = true;
            return this.path;
        }
        int i2 = AnonymousClass1.$SwitchMap$com$airbnb$lottie$model$content$PolystarShape$Type[this.type.ordinal()];
        if (i2 == 1) {
            createStarPath();
        } else if (i2 == 2) {
            createPolygonPath();
        }
        this.path.close();
        this.trimPaths.apply(this.path);
        this.isPathValid = true;
        return this.path;
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void onValueChanged() {
        invalidate();
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public void resolveKeyPath(KeyPath keyPath, int i2, List<KeyPath> list, KeyPath keyPath2) {
        MiscUtils.resolveKeyPath(keyPath, i2, list, keyPath2, this);
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public void setContents(List<Content> list, List<Content> list2) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            Content content = list.get(i2);
            if (content instanceof TrimPathContent) {
                TrimPathContent trimPathContent = (TrimPathContent) content;
                if (trimPathContent.getType() == ShapeTrimPath.Type.SIMULTANEOUSLY) {
                    this.trimPaths.addTrimPath(trimPathContent);
                    trimPathContent.addListener(this);
                }
            }
        }
    }
}
