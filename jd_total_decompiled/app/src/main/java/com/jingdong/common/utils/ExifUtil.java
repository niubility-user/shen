package com.jingdong.common.utils;

import android.annotation.TargetApi;
import android.media.ExifInterface;
import android.os.Build;
import com.jingdong.sdk.oklog.OKLog;
import java.io.IOException;

/* loaded from: classes6.dex */
public class ExifUtil {
    private static final String TAG = "ExifUtil";

    @TargetApi(5)
    public static int readPictureDegree(String str) {
        if (Build.VERSION.SDK_INT < 5) {
            return 0;
        }
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt("Orientation", 1);
            if (attributeInt != 3) {
                if (attributeInt != 6) {
                    return attributeInt != 8 ? 0 : 270;
                }
                return 90;
            }
            return 180;
        } catch (IOException e2) {
            OKLog.e(TAG, e2);
            return 0;
        }
    }
}
