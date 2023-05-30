package com.jingdong.common.permission.rom;

import android.text.TextUtils;
import com.jdpay.system.SystemInfo;
import com.jingdong.common.messagecenter.RomUtil;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes5.dex */
public class RomUtils {
    private static final String TAG = "RomUtils";

    public static boolean checkIs360Rom() {
        return BaseInfo.getDeviceManufacture().contains("QiKU") || BaseInfo.getDeviceManufacture().contains(SystemInfo.ROM_360);
    }

    public static boolean checkIsHonorRom() {
        return "HONOR".equalsIgnoreCase(BaseInfo.getDeviceManufacture()) && (RomUtil.isMagicUI() || RomUtil.isEMUI());
    }

    public static boolean checkIsHuaweiRom() {
        return "HUAWEI".equalsIgnoreCase(BaseInfo.getDeviceManufacture()) && RomUtil.isEMUI();
    }

    public static boolean checkIsMeizuRom() {
        String systemProperty = getSystemProperty("ro.build.display.id");
        if (TextUtils.isEmpty(systemProperty)) {
            return false;
        }
        return systemProperty.contains("flyme") || systemProperty.toLowerCase().contains("flyme");
    }

    public static boolean checkIsMiuiRom() {
        return RomUtil.isMIUI();
    }

    public static boolean checkIsSmartisanRom() {
        return !TextUtils.isEmpty(getSystemProperty("ro.smartisan.version"));
    }

    public static double getEmuiVersion() {
        try {
            String systemProperty = getSystemProperty("ro.build.version.emui");
            return Double.parseDouble(systemProperty.substring(systemProperty.indexOf(CartConstant.KEY_YB_INFO_LINK) + 1));
        } catch (Exception e2) {
            e2.printStackTrace();
            return 4.0d;
        }
    }

    public static int getMiuiVersion() {
        String systemProperty = getSystemProperty("ro.miui.ui.version.name");
        if (systemProperty != null) {
            try {
                return Integer.parseInt(systemProperty.substring(1));
            } catch (Exception unused) {
                String str = "get miui version code error, version : " + systemProperty;
                return -1;
            }
        }
        return -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0052 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getSystemProperty(java.lang.String r4) {
        /*
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L37
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L37
            r2.<init>()     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L37
            java.lang.String r3 = "getprop "
            r2.append(r3)     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L37
            r2.append(r4)     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L37
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L37
            java.lang.Process r1 = r1.exec(r2)     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L37
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L37
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L37
            java.io.InputStream r1 = r1.getInputStream()     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L37
            r3.<init>(r1)     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L37
            r1 = 1024(0x400, float:1.435E-42)
            r2.<init>(r3, r1)     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L37
            java.lang.String r1 = r2.readLine()     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4e
            r2.close()     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4e
            r2.close()     // Catch: java.io.IOException -> L34
        L34:
            return r1
        L35:
            r4 = move-exception
            goto L50
        L37:
            r2 = r0
        L38:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4e
            r1.<init>()     // Catch: java.lang.Throwable -> L4e
            java.lang.String r3 = "Unable to read sysprop "
            r1.append(r3)     // Catch: java.lang.Throwable -> L4e
            r1.append(r4)     // Catch: java.lang.Throwable -> L4e
            r1.toString()     // Catch: java.lang.Throwable -> L4e
            if (r2 == 0) goto L4d
            r2.close()     // Catch: java.io.IOException -> L4d
        L4d:
            return r0
        L4e:
            r4 = move-exception
            r0 = r2
        L50:
            if (r0 == 0) goto L55
            r0.close()     // Catch: java.io.IOException -> L55
        L55:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.permission.rom.RomUtils.getSystemProperty(java.lang.String):java.lang.String");
    }
}
