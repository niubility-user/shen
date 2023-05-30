package com.jdjr.risk.device.c;

import com.jd.dynamic.DYConstants;
import com.jingdong.common.web.managers.PerformanceManager;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.io.File;
import java.io.FileFilter;

/* loaded from: classes18.dex */
public class h {
    private static final FileFilter a = new FileFilter() { // from class: com.jdjr.risk.device.c.h.1
        @Override // java.io.FileFilter
        public boolean accept(File file) {
            try {
                String name = file.getName();
                if (name != null && name.length() != 0 && name.startsWith(PerformanceManager.CUP)) {
                    for (int i2 = 3; i2 < name.length(); i2++) {
                        if (name.charAt(i2) < '0' || name.charAt(i2) > '9') {
                            return false;
                        }
                    }
                    return true;
                }
            } catch (Exception unused) {
            }
            return false;
        }
    };

    public static String a() {
        try {
            return BaseInfo.getCPUNum();
        } catch (Throwable unused) {
            return "-1";
        }
    }

    public static String b() {
        try {
            return BaseInfo.getCPUMaxFreq();
        } catch (Throwable unused) {
            return "-1";
        }
    }

    public static String c() {
        try {
            return BaseInfo.getCpuName();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String d() {
        try {
            return BaseInfo.getCpuCurFreq();
        } catch (Throwable unused) {
            return "-1";
        }
    }

    public static String e() {
        try {
            String[] deviceSuppportedABIs = BaseInfo.getDeviceSuppportedABIs();
            return deviceSuppportedABIs.length == 0 ? "" : deviceSuppportedABIs[0];
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String f() {
        try {
            float floatValue = Float.valueOf(b()).floatValue();
            float floatValue2 = Float.valueOf(d()).floatValue();
            if (floatValue <= 0.0f || floatValue2 <= 0.0f) {
                return "";
            }
            return ((floatValue2 / floatValue) * 100.0f) + "%";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String g() {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            String[] deviceSuppportedABIs = BaseInfo.getDeviceSuppportedABIs();
            if (deviceSuppportedABIs != null && deviceSuppportedABIs.length > 0) {
                for (int i2 = 0; i2 < deviceSuppportedABIs.length; i2++) {
                    stringBuffer.append(deviceSuppportedABIs[i2]);
                    if (i2 != deviceSuppportedABIs.length - 1) {
                        stringBuffer.append(DYConstants.DY_REGEX_COMMA);
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return stringBuffer.toString();
    }
}
