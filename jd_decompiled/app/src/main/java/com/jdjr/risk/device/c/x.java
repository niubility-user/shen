package com.jdjr.risk.device.c;

import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes18.dex */
public class x {
    public static String a() {
        try {
            String linuxVersion = BaseInfo.getLinuxVersion();
            return linuxVersion == null ? "" : linuxVersion;
        } catch (Throwable unused) {
            return "";
        }
    }
}
