package com.jdjr.risk.device.c;

import android.content.Context;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes18.dex */
public class y {
    public static String a(Context context) {
        try {
            String wifiMacAddressForDeviceFinger = BaseInfo.getWifiMacAddressForDeviceFinger();
            return wifiMacAddressForDeviceFinger == null ? "" : wifiMacAddressForDeviceFinger;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }
}
