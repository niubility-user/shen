package com.jd.aips.verify.utils;

import android.graphics.Color;
import androidx.annotation.NonNull;
import com.jd.aips.verify.tracker.BaseVerifyTracker;
import java.net.URL;

/* loaded from: classes12.dex */
public class FsJsonStringChangeUtil {
    private static final String FALSE = "false";
    private static final String TRUE = "true";

    public static boolean parseBoolean(@NonNull BaseVerifyTracker baseVerifyTracker, String str, String str2) {
        if (!"true".equalsIgnoreCase(str) && !"false".equalsIgnoreCase(str)) {
            baseVerifyTracker.trackFormatException(str2, str, "value is not boolean type");
            return false;
        }
        try {
            return Boolean.parseBoolean(str);
        } catch (Exception e2) {
            baseVerifyTracker.trackFormatException(str2, str, e2.getMessage());
            return false;
        }
    }

    public static int parseColor(@NonNull BaseVerifyTracker baseVerifyTracker, String str, String str2) {
        try {
            return Color.parseColor(str);
        } catch (Exception e2) {
            baseVerifyTracker.trackFormatException(str2, str, e2.getMessage());
            return 0;
        }
    }

    public static float parseFloat(@NonNull BaseVerifyTracker baseVerifyTracker, String str, String str2) {
        try {
            return Float.parseFloat(str);
        } catch (Exception e2) {
            baseVerifyTracker.trackFormatException(str2, str, e2.getMessage());
            return 0.0f;
        }
    }

    public static int parseInt(@NonNull BaseVerifyTracker baseVerifyTracker, String str, String str2) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e2) {
            baseVerifyTracker.trackFormatException(str2, str, e2.getMessage());
            return 0;
        }
    }

    public static String parseUrl(@NonNull BaseVerifyTracker baseVerifyTracker, @NonNull String str, @NonNull String str2) {
        try {
            return new URL(str).toString();
        } catch (Exception e2) {
            baseVerifyTracker.trackFormatException(str2, str, e2.getMessage());
            return null;
        }
    }
}
