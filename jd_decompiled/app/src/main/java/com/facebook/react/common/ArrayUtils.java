package com.facebook.react.common;

import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class ArrayUtils {
    public static float[] copyArray(float[] fArr) {
        if (fArr == null) {
            return null;
        }
        return Arrays.copyOf(fArr, fArr.length);
    }

    public static int[] copyListToArray(List<Integer> list) {
        int[] iArr = new int[list.size()];
        for (int i2 = 0; i2 < list.size(); i2++) {
            iArr[i2] = list.get(i2).intValue();
        }
        return iArr;
    }
}
