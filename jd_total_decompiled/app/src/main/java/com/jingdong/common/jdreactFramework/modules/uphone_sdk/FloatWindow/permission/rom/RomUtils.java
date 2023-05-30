package com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.permission.rom;

import android.text.TextUtils;
import com.jdpay.system.SystemInfo;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* loaded from: classes5.dex */
public class RomUtils {
    private static final String TAG = "RomUtils";

    public static boolean checkIs360Rom() {
        return BaseInfo.getDeviceManufacture().contains("QiKU") || BaseInfo.getDeviceManufacture().contains(SystemInfo.ROM_360);
    }

    public static boolean checkIsHuaweiRom() {
        return BaseInfo.getDeviceManufacture().contains("HUAWEI");
    }

    public static boolean checkIsMeizuRom() {
        String systemProperty = getSystemProperty("ro.build.display.id");
        if (TextUtils.isEmpty(systemProperty)) {
            return false;
        }
        return systemProperty.contains("flyme") || systemProperty.toLowerCase().contains("flyme");
    }

    public static boolean checkIsMiuiRom() {
        return !TextUtils.isEmpty(getSystemProperty("ro.miui.ui.version.name"));
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
    */
    public static String getSystemProperty(String str) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + str).getInputStream()), 1024);
        } catch (IOException unused) {
            bufferedReader = null;
        } catch (Throwable th) {
            th = th;
            if (bufferedReader2 != null) {
            }
            throw th;
        }
        try {
            try {
                String readLine = bufferedReader.readLine();
                bufferedReader.close();
                try {
                    bufferedReader.close();
                } catch (IOException unused2) {
                }
                return readLine;
            } catch (Throwable th2) {
                th = th2;
                bufferedReader2 = bufferedReader;
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException unused3) {
                    }
                }
                throw th;
            }
        } catch (IOException unused4) {
            String str2 = "Unable to read sysprop " + str;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException unused5) {
                }
            }
            return null;
        }
    }
}
