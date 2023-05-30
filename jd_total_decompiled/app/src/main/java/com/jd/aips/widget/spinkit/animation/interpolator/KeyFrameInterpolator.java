package com.jd.aips.widget.spinkit.animation.interpolator;

import android.animation.TimeInterpolator;
import android.view.animation.Interpolator;

/* loaded from: classes12.dex */
public class KeyFrameInterpolator implements Interpolator {
    private TimeInterpolator a;
    private float[] b;

    public KeyFrameInterpolator(TimeInterpolator timeInterpolator, float... fArr) {
        this.a = timeInterpolator;
        this.b = fArr;
    }

    public static KeyFrameInterpolator easeInOut(float... fArr) {
        KeyFrameInterpolator keyFrameInterpolator = new KeyFrameInterpolator(Ease.inOut(), new float[0]);
        keyFrameInterpolator.setFractions(fArr);
        return keyFrameInterpolator;
    }

    public static KeyFrameInterpolator pathInterpolator(float f2, float f3, float f4, float f5, float... fArr) {
        KeyFrameInterpolator keyFrameInterpolator = new KeyFrameInterpolator(PathInterpolatorCompat.create(f2, f3, f4, f5), new float[0]);
        keyFrameInterpolator.setFractions(fArr);
        return keyFrameInterpolator;
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f2) {
        if (this.b.length > 1) {
            int i2 = 0;
            while (true) {
                float[] fArr = this.b;
                if (i2 >= fArr.length - 1) {
                    break;
                }
                float f3 = fArr[i2];
                i2++;
                float f4 = fArr[i2];
                float f5 = f4 - f3;
                if (f2 >= f3 && f2 <= f4) {
                    return f3 + (this.a.getInterpolation((f2 - f3) / f5) * f5);
                }
            }
        }
        return this.a.getInterpolation(f2);
    }

    public void setFractions(float... fArr) {
        this.b = fArr;
    }
}
