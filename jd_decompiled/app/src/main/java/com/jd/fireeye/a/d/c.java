package com.jd.fireeye.a.d;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.jingdong.common.search.ProductListConstant;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/* loaded from: classes13.dex */
public class c {
    private static long a;
    private static String[] b = {"/data/app/com.bluestacks.appmart-1.apk", "/data/app/com.bluestacks.BstCommandProcessor-1.apk", "/data/app/com.bluestacks.help-1.apk", "/data/app/com.bluestacks.home-1.apk", "/data/app/com.bluestacks.s2p-1.apk", "/data/app/com.bluestacks.searchapp-1.apk", "/data/bluestacks.prop", "/data/data/com.androVM.vmconfig", "/data/data/com.bluestacks.accelerometerui", "/data/data/com.bluestacks.appfinder", "/data/data/com.bluestacks.appmart", "/data/data/com.bluestacks.appsettings", "/data/data/com.bluestacks.BstCommandProcessor", "/data/data/com.bluestacks.bstfolder", "/data/data/com.bluestacks.help", "/data/data/com.bluestacks.home", "/data/data/com.bluestacks.s2p", "/data/data/com.bluestacks.searchapp", "/data/data/com.bluestacks.settings", "/data/data/com.bluestacks.setup", "/data/data/com.bluestacks.spotlight", "/mnt/prebundledapps/bluestacks.prop.orig"};

    private static String a(String str) {
        String a2 = com.jd.fireeye.a.c.f.a(str);
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        return a2;
    }

    private static boolean b(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.camera");
    }

    private static boolean c(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.camera.flash");
    }

    public static String d() {
        try {
            Process start = new ProcessBuilder("/system/bin/cat", "/proc/cpuinfo").start();
            StringBuffer stringBuffer = new StringBuffer();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(start.getInputStream(), "utf-8"));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    stringBuffer.append(readLine);
                } else {
                    bufferedReader.close();
                    return stringBuffer.toString().toLowerCase();
                }
            }
        } catch (IOException unused) {
            return "";
        }
    }

    private static Boolean b() {
        if (com.jd.fireeye.a.c.c.a("cat /proc/self/cgroup") == null) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public static boolean c() {
        String deviceProductName = BaseInfo.getDeviceProductName();
        if (deviceProductName.contains("sdk") || deviceProductName.contains("sdk_x86") || deviceProductName.contains("sdk_google") || deviceProductName.contains("Andy") || deviceProductName.contains("Droid4X") || deviceProductName.contains("nox") || deviceProductName.contains("vbox86p") || deviceProductName.contains("aries")) {
            return true;
        }
        String deviceManufacture = BaseInfo.getDeviceManufacture();
        if (deviceManufacture.equals("Genymotion") || deviceManufacture.contains("Andy") || deviceManufacture.contains("nox") || deviceManufacture.contains("TiantianVM") || BaseInfo.getDeviceBrand().contains("Andy")) {
            return true;
        }
        String deviceModel = BaseInfo.getDeviceModel();
        if (deviceModel.contains("Emulator") || deviceModel.equals("google_sdk") || deviceModel.contains("Droid4X") || deviceModel.contains("TiantianVM") || deviceModel.contains("Andy") || deviceModel.equals("Android SDK built for x86_64") || deviceModel.equals("Android SDK built for x86")) {
            return true;
        }
        String str = Build.HARDWARE;
        if (str.equals("vbox86") || str.contains("nox") || str.contains("ttVM_x86")) {
            return true;
        }
        String oSFingerprint = BaseInfo.getOSFingerprint();
        return oSFingerprint.contains("generic/sdk/generic") || oSFingerprint.contains("generic_x86/sdk_x86/generic_x86") || oSFingerprint.contains("Andy") || oSFingerprint.contains("ttVM_Hdragon") || oSFingerprint.contains("generic/google_sdk/generic") || oSFingerprint.contains("vbox86p") || oSFingerprint.contains("generic/vbox86p/vbox86p");
    }

    public static Long a(Context context) {
        try {
            boolean z = !new e(context).d();
            if (a("ro.product.cpu.abi").contains("x86")) {
                a |= 1;
            }
            if (a("ro.product.cpu.abilist").contains("x86")) {
                a |= 2;
            }
            if (com.jd.fireeye.a.c.c.a("uname -m").contains("i686")) {
                a |= 4;
            }
            if (!b(context)) {
                a |= 8;
            }
            if (!c(context) && z) {
                a |= 16;
            }
            if (b().booleanValue()) {
                a |= 32;
            }
            if (a()) {
                a |= 64;
            }
            if (c()) {
                a |= 128;
            }
            if (TextUtils.isEmpty(a("gsm.version.baseband")) && z) {
                a |= 256;
            }
            String d = d();
            if (d.contains(ProductListConstant.INTEL) || d.contains("amd")) {
                a |= 512;
            }
        } catch (Exception unused) {
        }
        return Long.valueOf(a);
    }

    public static boolean a() {
        int i2 = 0;
        while (true) {
            String[] strArr = b;
            if (i2 >= strArr.length) {
                return false;
            }
            if (new File(strArr[i2]).exists()) {
                return true;
            }
            i2++;
        }
    }
}
