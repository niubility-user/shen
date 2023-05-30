package com.jd.aips.common.utils;

import android.content.Context;
import androidx.annotation.NonNull;

/* loaded from: classes12.dex */
public class AppInfoUtil {
    public static String getAppName(@NonNull Context context) {
        if (context == null) {
            return "";
        }
        try {
            return context.getResources().getString(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.labelRes);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }
}
