package com.tencent.map.sdk.utilities.visualization;

import android.graphics.Color;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class Gradient {
    private static final int DEFAULT_COLOR_MAP_SIZE = 1000;
    public final int mColorMapSize;
    public int[] mColors;
    public float[] mStartPoints;

    /* loaded from: classes9.dex */
    public class b {
        private final int a;
        private final int b;

        /* renamed from: c  reason: collision with root package name */
        private final float f16189c;

        private b(int i2, int i3, float f2) {
            this.a = i2;
            this.b = i3;
            this.f16189c = f2;
        }
    }

    public Gradient(int[] iArr, float[] fArr) {
        this(iArr, fArr, 1000);
    }

    public Gradient(int[] iArr, float[] fArr, int i2) {
        if (iArr.length != fArr.length) {
            throw new IllegalArgumentException("colors and startPoints should be same length");
        }
        if (iArr.length == 0) {
            throw new IllegalArgumentException("No colors have been defined");
        }
        for (int i3 = 1; i3 < fArr.length; i3++) {
            if (fArr[i3] <= fArr[i3 - 1]) {
                throw new IllegalArgumentException("startPoints should be in increasing order");
            }
        }
        this.mColorMapSize = i2;
        int[] iArr2 = new int[iArr.length];
        this.mColors = iArr2;
        this.mStartPoints = new float[fArr.length];
        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        System.arraycopy(fArr, 0, this.mStartPoints, 0, fArr.length);
    }

    private HashMap<Integer, b> generateColorIntervals() {
        HashMap<Integer, b> hashMap = new HashMap<>();
        if (this.mStartPoints[0] != 0.0f) {
            hashMap.put(0, new b(Color.argb(0, Color.red(this.mColors[0]), Color.green(this.mColors[0]), Color.blue(this.mColors[0])), this.mColors[0], this.mColorMapSize * this.mStartPoints[0]));
        }
        for (int i2 = 1; i2 < this.mColors.length; i2++) {
            int i3 = i2 - 1;
            Integer valueOf = Integer.valueOf((int) (this.mColorMapSize * this.mStartPoints[i3]));
            int[] iArr = this.mColors;
            int i4 = iArr[i3];
            int i5 = iArr[i2];
            float[] fArr = this.mStartPoints;
            hashMap.put(valueOf, new b(i4, i5, (fArr[i2] - fArr[i3]) * this.mColorMapSize));
        }
        float[] fArr2 = this.mStartPoints;
        if (fArr2[fArr2.length - 1] != 1.0f) {
            int length = fArr2.length - 1;
            Integer valueOf2 = Integer.valueOf((int) (this.mColorMapSize * fArr2[length]));
            int[] iArr2 = this.mColors;
            hashMap.put(valueOf2, new b(iArr2[length], iArr2[length], this.mColorMapSize * (1.0f - this.mStartPoints[length])));
        }
        return hashMap;
    }

    public static int interpolateColor(int i2, int i3, float f2) {
        int alpha = (int) (((Color.alpha(i3) - Color.alpha(i2)) * f2) + Color.alpha(i2));
        float[] fArr = new float[3];
        Color.RGBToHSV(Color.red(i2), Color.green(i2), Color.blue(i2), fArr);
        float[] fArr2 = new float[3];
        Color.RGBToHSV(Color.red(i3), Color.green(i3), Color.blue(i3), fArr2);
        if (fArr[0] - fArr2[0] > 180.0f) {
            fArr2[0] = fArr2[0] + 360.0f;
        } else if (fArr2[0] - fArr[0] > 180.0f) {
            fArr[0] = fArr[0] + 360.0f;
        }
        float[] fArr3 = new float[3];
        for (int i4 = 0; i4 < 3; i4++) {
            fArr3[i4] = ((fArr2[i4] - fArr[i4]) * f2) + fArr[i4];
        }
        return Color.HSVToColor(alpha, fArr3);
    }

    public final int[] generateColorMap(double d) {
        HashMap<Integer, b> generateColorIntervals = generateColorIntervals();
        int[] iArr = new int[this.mColorMapSize];
        b bVar = generateColorIntervals.get(0);
        int i2 = 0;
        for (int i3 = 0; i3 < this.mColorMapSize; i3++) {
            if (generateColorIntervals.containsKey(Integer.valueOf(i3))) {
                bVar = generateColorIntervals.get(Integer.valueOf(i3));
                i2 = i3;
            }
            iArr[i3] = interpolateColor(bVar.a, bVar.b, (i3 - i2) / bVar.f16189c);
        }
        if (d != 1.0d) {
            for (int i4 = 0; i4 < this.mColorMapSize; i4++) {
                int i5 = iArr[i4];
                double alpha = Color.alpha(i5);
                Double.isNaN(alpha);
                iArr[i4] = Color.argb((int) (alpha * d), Color.red(i5), Color.green(i5), Color.blue(i5));
            }
        }
        return iArr;
    }
}
