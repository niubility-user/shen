package tv.danmaku.ijk.media.alpha.util;

import tv.danmaku.ijk.media.alpha.AlphaConfig;

/* loaded from: classes11.dex */
public class VertexUtil {
    public static float[] create(int i2, int i3, AlphaConfig.PointRect pointRect, float[] fArr) {
        float f2 = i2;
        fArr[0] = switchX(pointRect.x / f2);
        float f3 = i3;
        fArr[1] = switchY(pointRect.y / f3);
        fArr[2] = switchX(pointRect.x / f2);
        fArr[3] = switchY((pointRect.y + pointRect.f20500h) / f3);
        fArr[4] = switchX((pointRect.x + pointRect.w) / f2);
        fArr[5] = switchY(pointRect.y / f3);
        fArr[6] = switchX((pointRect.x + pointRect.w) / f2);
        fArr[7] = switchY((pointRect.y + pointRect.f20500h) / f3);
        return fArr;
    }

    private static float switchX(float f2) {
        return (f2 * 2.0f) - 1.0f;
    }

    private static float switchY(float f2) {
        return (((f2 * 2.0f) - 2.0f) * (-1.0f)) - 1.0f;
    }
}
