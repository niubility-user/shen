package com.facebook.yoga2;

/* loaded from: classes12.dex */
public class YogaMeasureOutput {
    public static float getHeight(long j2) {
        return Float.intBitsToFloat((int) (j2 & (-1)));
    }

    public static float getWidth(long j2) {
        return Float.intBitsToFloat((int) ((j2 >> 32) & (-1)));
    }

    public static long make(float f2, float f3) {
        return Float.floatToRawIntBits(f3) | (Float.floatToRawIntBits(f2) << 32);
    }

    public static long make(int i2, int i3) {
        return make(i2, i3);
    }
}
