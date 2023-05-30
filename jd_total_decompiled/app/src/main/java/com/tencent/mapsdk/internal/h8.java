package com.tencent.mapsdk.internal;

import com.tencent.tencentmap.mapsdk.maps.model.AlphaAnimation;
import com.tencent.tencentmap.mapsdk.maps.model.Animation;
import com.tencent.tencentmap.mapsdk.maps.model.AnimationSet;
import com.tencent.tencentmap.mapsdk.maps.model.EmergeAnimation;
import com.tencent.tencentmap.mapsdk.maps.model.IAnimationSet;
import com.tencent.tencentmap.mapsdk.maps.model.RotateAnimation;
import com.tencent.tencentmap.mapsdk.maps.model.ScaleAnimation;
import com.tencent.tencentmap.mapsdk.maps.model.TranslateAnimation;
import java.util.Iterator;

/* loaded from: classes9.dex */
public class h8 {
    public static <A extends Animation> i7 a(o1 o1Var, A a) {
        Animation animation = null;
        if (o1Var == null || a == null) {
            return null;
        }
        if (a instanceof i7) {
            return (i7) a;
        }
        Class<?> cls = a.getClass();
        if (cls == AlphaAnimation.class) {
            AlphaAnimation alphaAnimation = (AlphaAnimation) a;
            animation = o1Var.createAlphaAnimation(alphaAnimation.mFromAlpha, alphaAnimation.mToAlpha);
        } else if (cls == ScaleAnimation.class) {
            ScaleAnimation scaleAnimation = (ScaleAnimation) a;
            animation = o1Var.createScaleAnimation(scaleAnimation.mFromX, scaleAnimation.mToX, scaleAnimation.mFromY, scaleAnimation.mToY);
        } else if (cls == EmergeAnimation.class) {
            animation = o1Var.createEmergeAnimation(((EmergeAnimation) a).mStartPoint);
        } else if (cls == AnimationSet.class) {
            AnimationSet animationSet = (AnimationSet) a;
            IAnimationSet createAnimationSet = o1Var.createAnimationSet(animationSet.mShareInterpolator);
            Iterator<Animation> it = animationSet.mAnimations.iterator();
            while (it.hasNext()) {
                ((j7) createAnimationSet).addAnimation(a(o1Var, it.next()));
            }
            animation = createAnimationSet;
        } else if (cls == RotateAnimation.class) {
            RotateAnimation rotateAnimation = (RotateAnimation) a;
            animation = o1Var.createRotateAnimation(rotateAnimation.mFromDegree, rotateAnimation.mToDegree, rotateAnimation.mPivoteX, rotateAnimation.mPivoteY, rotateAnimation.mPivoteZ);
        } else if (cls == TranslateAnimation.class) {
            animation = o1Var.createTranslateAnimation(((TranslateAnimation) a).mTargetLatLng);
        }
        if (animation != null) {
            animation.setDuration(a.getDuration());
            animation.setInterpolator(a.getInterpolator());
            animation.setAnimationListener(a.getAnimationListener());
        }
        return (i7) animation;
    }

    public static double[] a(double d, double d2, int i2) {
        double d3 = d + d2;
        double abs = Math.abs(d2) / 2.0d;
        double sqrt = Math.sqrt((4.0d * abs) / 3.141592653589793d);
        int i3 = i2 >> 1;
        int i4 = i3 << 1;
        double[] dArr = new double[i4];
        int i5 = i3 - 1;
        dArr[i5] = d + (d2 / 2.0d);
        dArr[i4 - 1] = d3;
        double d4 = i3;
        Double.isNaN(d4);
        double d5 = sqrt / d4;
        int i6 = 0;
        while (i6 < i5) {
            int i7 = i6 + 1;
            double d6 = i7;
            Double.isNaN(d6);
            double d7 = sqrt - (d6 * d5);
            double acos = Math.acos(d7 / sqrt);
            double sin = (((acos * abs) * 2.0d) / 3.141592653589793d) - ((d7 * (Math.sin(acos) * sqrt)) / 2.0d);
            if (d2 < 0.0d) {
                sin = -sin;
            }
            dArr[i6] = d + sin;
            dArr[(i4 - 2) - i6] = d3 - sin;
            i6 = i7;
        }
        return dArr;
    }

    public static double[] b(double d, double d2, int i2) {
        double d3 = d + d2;
        double[] dArr = new double[i2];
        double d4 = i2;
        Double.isNaN(d4);
        Double.isNaN(d4);
        double d5 = ((d2 * 2.0d) / d4) / d4;
        double d6 = d5 / 2.0d;
        int i3 = i2 - 1;
        dArr[i3] = d3;
        dArr[0] = d + d6;
        for (int i4 = 1; i4 < i3; i4++) {
            double d7 = i4;
            Double.isNaN(d7);
            dArr[i4] = dArr[i4 - 1] + (d7 * d5) + d6;
        }
        return dArr;
    }

    public static double[] c(double d, double d2, int i2) {
        double d3 = d + d2;
        int i3 = i2 >> 1;
        int i4 = i3 << 1;
        double[] dArr = new double[i4];
        int i5 = i3 - 1;
        dArr[i5] = (d2 / 2.0d) + d;
        int i6 = i4 - 1;
        dArr[i6] = d3;
        double d4 = i3;
        Double.isNaN(d4);
        Double.isNaN(d4);
        double d5 = (d2 / d4) / d4;
        double d6 = d5 / 2.0d;
        dArr[0] = d + d6;
        int i7 = i4 - 2;
        dArr[i7] = d3 - d6;
        for (int i8 = 1; i8 < i5; i8++) {
            double d7 = i8;
            Double.isNaN(d7);
            double d8 = (d7 * d5) + d6;
            dArr[i8] = dArr[i8 - 1] + d8;
            dArr[i7 - i8] = dArr[i6 - i8] - d8;
        }
        return dArr;
    }
}
