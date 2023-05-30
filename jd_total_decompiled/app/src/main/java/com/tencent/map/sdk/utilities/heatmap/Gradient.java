package com.tencent.map.sdk.utilities.heatmap;

/* loaded from: classes9.dex */
public class Gradient extends com.tencent.map.sdk.utilities.visualization.Gradient {
    public Gradient(com.tencent.map.sdk.utilities.visualization.Gradient gradient) {
        super(gradient.mColors, gradient.mStartPoints, gradient.mColorMapSize);
    }

    public Gradient(int[] iArr, float[] fArr) {
        super(iArr, fArr);
    }

    public Gradient(int[] iArr, float[] fArr, int i2) {
        super(iArr, fArr, i2);
    }
}
