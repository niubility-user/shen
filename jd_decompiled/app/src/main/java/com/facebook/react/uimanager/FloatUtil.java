package com.facebook.react.uimanager;

/* loaded from: classes12.dex */
public class FloatUtil {
    private static final float EPSILON = 1.0E-5f;

    public static boolean floatsEqual(float f2, float f3) {
        return (Float.isNaN(f2) || Float.isNaN(f3)) ? Float.isNaN(f2) && Float.isNaN(f3) : Math.abs(f3 - f2) < EPSILON;
    }
}
