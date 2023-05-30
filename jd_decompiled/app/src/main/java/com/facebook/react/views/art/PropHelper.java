package com.facebook.react.views.art;

import com.facebook.react.bridge.ReadableArray;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
class PropHelper {
    PropHelper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static float[] toFloatArray(@Nullable ReadableArray readableArray) {
        if (readableArray != null) {
            float[] fArr = new float[readableArray.size()];
            toFloatArray(readableArray, fArr);
            return fArr;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int toFloatArray(ReadableArray readableArray, float[] fArr) {
        int length = readableArray.size() > fArr.length ? fArr.length : readableArray.size();
        for (int i2 = 0; i2 < length; i2++) {
            fArr[i2] = (float) readableArray.getDouble(i2);
        }
        return readableArray.size();
    }
}
