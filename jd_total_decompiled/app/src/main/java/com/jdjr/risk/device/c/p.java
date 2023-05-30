package com.jdjr.risk.device.c;

import android.content.Context;
import com.jd.android.sdk.coreinfo.CoreInfo;
import com.jd.android.sdk.coreinfo.ScreenSize;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes18.dex */
public class p {
    public static int a(int i2) {
        try {
            ScreenSize realScreenSize = BaseInfo.getRealScreenSize();
            if (i2 == 0) {
                return realScreenSize.widthPixels;
            }
            if (i2 == 1) {
                return realScreenSize.heightPixels;
            }
            return 0;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static String a(Context context) {
        try {
            return BaseInfo.getDisplayMetrics();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String b(Context context) {
        try {
            return BaseInfo.getDensityDpi();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String c(Context context) {
        try {
            return String.format("%.3f", Float.valueOf(((float) CoreInfo.Device.getAvailableInternalMemorySize(context)) / ((float) BaseInfo.getRomSize())));
        } catch (Exception unused) {
            return "";
        }
    }

    public static String d(Context context) {
        try {
            return BaseInfo.getDisplayMetricsWithNavigationBar(context);
        } catch (Exception unused) {
            return "";
        }
    }
}
