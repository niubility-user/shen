package com.jdjr.risk.device.c;

import android.content.Context;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes18.dex */
public class b {
    public static String a(Context context) {
        try {
            String androidIdForDeviceFinger = BaseInfo.getAndroidIdForDeviceFinger();
            return androidIdForDeviceFinger == null ? "" : androidIdForDeviceFinger;
        } catch (Throwable unused) {
            return "";
        }
    }
}
