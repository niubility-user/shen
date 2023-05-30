package tv.danmaku.ijk.media.alpha.util;

import tv.danmaku.ijk.media.alpha.AlphaConfig;

/* loaded from: classes11.dex */
public class TexCoordsUtil {
    public static float[] create(int i2, int i3, AlphaConfig.PointRect pointRect, float[] fArr) {
        int i4 = pointRect.x;
        float f2 = i2;
        fArr[0] = i4 / f2;
        int i5 = pointRect.y;
        float f3 = i3;
        fArr[1] = i5 / f3;
        fArr[2] = i4 / f2;
        int i6 = pointRect.f20500h;
        fArr[3] = (i5 + i6) / f3;
        int i7 = pointRect.w;
        fArr[4] = (i4 + i7) / f2;
        fArr[5] = i5 / f3;
        fArr[6] = (i4 + i7) / f2;
        fArr[7] = (i5 + i6) / f3;
        return fArr;
    }

    public static float[] rotate90(float[] fArr) {
        float f2 = fArr[0];
        float f3 = fArr[1];
        fArr[0] = fArr[2];
        fArr[1] = fArr[3];
        fArr[2] = fArr[6];
        fArr[3] = fArr[7];
        fArr[6] = fArr[4];
        fArr[7] = fArr[5];
        fArr[4] = f2;
        fArr[5] = f3;
        return fArr;
    }
}
