package com.facebook.imagepipeline.nativecode;

import android.graphics.Bitmap;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.Preconditions;

@DoNotStrip
/* loaded from: classes.dex */
public class NativeRoundingFilter {
    static {
        NativeFiltersLoader.load();
    }

    public static void addRoundedCorners(Bitmap bitmap, int i2, int i3, int i4, int i5) {
        nativeAddRoundedCornersFilter(bitmap, i2, i3, i4, i5);
    }

    @DoNotStrip
    private static native void nativeAddRoundedCornersFilter(Bitmap bitmap, int i2, int i3, int i4, int i5);

    @DoNotStrip
    private static native void nativeToCircleFastFilter(Bitmap bitmap, boolean z);

    @DoNotStrip
    private static native void nativeToCircleFilter(Bitmap bitmap, boolean z);

    @DoNotStrip
    private static native void nativeToCircleWithBorderFilter(Bitmap bitmap, int i2, int i3, boolean z);

    public static void toCircle(Bitmap bitmap) {
        toCircle(bitmap, false);
    }

    @DoNotStrip
    public static void toCircle(Bitmap bitmap, boolean z) {
        Preconditions.checkNotNull(bitmap);
        nativeToCircleFilter(bitmap, z);
    }

    public static void toCircleFast(Bitmap bitmap) {
        toCircleFast(bitmap, false);
    }

    @DoNotStrip
    public static void toCircleFast(Bitmap bitmap, boolean z) {
        Preconditions.checkNotNull(bitmap);
        nativeToCircleFastFilter(bitmap, z);
    }

    public static void toCircleWithBorder(Bitmap bitmap, int i2, int i3, boolean z) {
        Preconditions.checkNotNull(bitmap);
        nativeToCircleWithBorderFilter(bitmap, i2, i3, z);
    }
}
