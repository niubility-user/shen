package com.jdjr.risk.device.c;

import android.os.Build;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes18.dex */
public class o {
    public static String a() {
        try {
            return BaseInfo.getOSFingerprint();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String b() {
        try {
            return BaseInfo.getDeviceBrand() + '/' + BaseInfo.getDeviceProductName() + '/' + BaseInfo.getDeviceName() + ':' + BaseInfo.getAndroidVersion() + '/' + Build.ID + '/' + Build.VERSION.INCREMENTAL + ':' + BaseInfo.getOSVersionType() + '/' + BaseInfo.getOSVersionTags();
        } catch (Exception unused) {
            return "";
        }
    }
}
