package com.jdjr.risk.device.c;

import android.os.Build;
import android.os.Looper;
import com.jdjr.risk.biometric.core.BiometricManager;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.util.concurrent.TimeUnit;

/* loaded from: classes18.dex */
public class j {

    /* loaded from: classes18.dex */
    private static class a {
        private static final j a = new j();
    }

    private j() {
    }

    public static final j a() {
        return a.a;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0042, code lost:
        if (r1 != null) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0044, code lost:
        r1.destroy();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0049, code lost:
        if (r1 != null) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x004c, code lost:
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x003b, code lost:
        if (r1.waitFor() == 0) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String a(String str, boolean z) {
        String str2 = "";
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(str);
            process.getOutputStream().close();
            af afVar = new af(process.getInputStream(), z);
            BiometricManager.getInstance().b().submit(afVar);
            if (Build.VERSION.SDK_INT >= 26) {
                if (process.waitFor(2L, TimeUnit.SECONDS)) {
                    str2 = afVar.a();
                }
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0046, code lost:
        if (r1.waitFor() == 0) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x004d, code lost:
        if (r1 != null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x004f, code lost:
        r1.destroy();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0054, code lost:
        if (r1 != null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0057, code lost:
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String a(String[] strArr) {
        String str = "";
        Process process = null;
        if (Looper.myLooper() != Looper.getMainLooper()) {
            process = new ProcessBuilder(strArr).start();
            process.getOutputStream().close();
            af afVar = new af(process.getInputStream());
            BiometricManager.getInstance().b().submit(afVar);
            if (Build.VERSION.SDK_INT >= 26) {
                if (process.waitFor(2L, TimeUnit.SECONDS)) {
                    str = afVar.a();
                }
            }
        }
    }

    public static String b(String str) {
        return a(str, false);
    }

    public String a(String str) {
        try {
            Object invoke = Class.forName("android.os.SystemProperties").getMethod(IMantoServerRequester.GET, String.class).invoke(null, str);
            if (invoke != null) {
                return (String) invoke;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }
}
