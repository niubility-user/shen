package com.facebook.yoga2;

/* loaded from: classes12.dex */
public class YogaConstants {
    public static final float UNDEFINED = 1.0E21f;

    public static boolean isUndefined(float f2) {
        return Float.compare(f2, 1.0E9f) >= 0 || Float.compare(f2, -1.0E9f) <= 0;
    }

    public static boolean isUndefined(YogaValue yogaValue) {
        return yogaValue.unit == YogaUnit.UNDEFINED;
    }
}
