package com.jdjr.risk.device.c;

import android.os.Build;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/* loaded from: classes18.dex */
public class ae {
    public static int a() {
        String str = Build.TAGS;
        if (str != null && str.contains("test-keys")) {
            return 3;
        }
        try {
            if (new File("/system/app/Superuser.apk").exists()) {
                return 2;
            }
        } catch (Exception unused) {
        }
        if (a("/system/xbin/which su") || a("/system/bin/which su") || a("which su") || a("busybox which su")) {
            return 1;
        }
        if (b()) {
            return 4;
        }
        return "0".equals(Class.forName("android.os.SystemProperties").getMethod(IMantoServerRequester.GET, String.class).invoke(null, "ro.secure")) ? 5 : 0;
    }

    private static boolean a(String str) {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(str);
            boolean z = new BufferedReader(new InputStreamReader(process.getInputStream())).readLine() != null;
            if (process != null) {
                process.destroy();
            }
            return z;
        } catch (Exception unused) {
            if (process != null) {
                process.destroy();
            }
            return false;
        } catch (Throwable th) {
            if (process != null) {
                process.destroy();
            }
            throw th;
        }
    }

    private static boolean b() {
        String[] strArr = {"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/", "/su/bin/", "/data/local/xbin/", "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/"};
        for (int i2 = 0; i2 < 11; i2++) {
            try {
                if (new File(strArr[i2] + "su").exists()) {
                    return true;
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }
}
