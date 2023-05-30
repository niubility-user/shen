package com.facebook.imagepipeline.animated.util;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Build;
import java.util.Arrays;

/* loaded from: classes.dex */
public class AnimatedDrawableUtil {
    private static final int FRAME_DURATION_MS_FOR_MIN = 100;
    private static final int MIN_FRAME_DURATION_MS = 11;

    public static boolean isOutsideRange(int i2, int i3, int i4) {
        if (i2 == -1 || i3 == -1) {
            return true;
        }
        if (i2 <= i3) {
            if (i4 < i2 || i4 > i3) {
                return true;
            }
        } else if (i4 < i2 && i4 > i3) {
            return true;
        }
        return false;
    }

    public void fixFrameDurations(int[] iArr) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (iArr[i2] < 11) {
                iArr[i2] = 100;
            }
        }
    }

    public int getFrameForTimestampMs(int[] iArr, int i2) {
        int binarySearch = Arrays.binarySearch(iArr, i2);
        return binarySearch < 0 ? ((-binarySearch) - 1) - 1 : binarySearch;
    }

    public int[] getFrameTimeStampsFromDurations(int[] iArr) {
        int[] iArr2 = new int[iArr.length];
        int i2 = 0;
        for (int i3 = 0; i3 < iArr.length; i3++) {
            iArr2[i3] = i2;
            i2 += iArr[i3];
        }
        return iArr2;
    }

    @SuppressLint({"NewApi"})
    public int getSizeOfBitmap(Bitmap bitmap) {
        int i2 = Build.VERSION.SDK_INT;
        return i2 >= 19 ? bitmap.getAllocationByteCount() : i2 >= 12 ? bitmap.getByteCount() : bitmap.getWidth() * bitmap.getHeight() * 4;
    }

    public int getTotalDurationFromFrameDurations(int[] iArr) {
        int i2 = 0;
        for (int i3 : iArr) {
            i2 += i3;
        }
        return i2;
    }
}
