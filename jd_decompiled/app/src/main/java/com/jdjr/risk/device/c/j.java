package com.jdjr.risk.device.c;

import com.jingdong.manto.sdk.api.IMantoServerRequester;

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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(java.lang.String r4, boolean r5) {
        /*
            java.lang.String r0 = ""
            r1 = 0
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L48
            java.lang.Process r1 = r2.exec(r4)     // Catch: java.lang.Throwable -> L48
            java.io.OutputStream r4 = r1.getOutputStream()     // Catch: java.lang.Throwable -> L48
            r4.close()     // Catch: java.lang.Throwable -> L48
            com.jdjr.risk.device.c.af r4 = new com.jdjr.risk.device.c.af     // Catch: java.lang.Throwable -> L48
            java.io.InputStream r2 = r1.getInputStream()     // Catch: java.lang.Throwable -> L48
            r4.<init>(r2, r5)     // Catch: java.lang.Throwable -> L48
            com.jdjr.risk.biometric.core.BiometricManager r5 = com.jdjr.risk.biometric.core.BiometricManager.getInstance()     // Catch: java.lang.Throwable -> L48
            java.util.concurrent.ExecutorService r5 = r5.b()     // Catch: java.lang.Throwable -> L48
            r5.submit(r4)     // Catch: java.lang.Throwable -> L48
            int r5 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L48
            r2 = 26
            if (r5 < r2) goto L37
            r2 = 2
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.SECONDS     // Catch: java.lang.Throwable -> L48
            boolean r5 = r1.waitFor(r2, r5)     // Catch: java.lang.Throwable -> L48
            if (r5 == 0) goto L42
            goto L3d
        L37:
            int r5 = r1.waitFor()     // Catch: java.lang.Throwable -> L48
            if (r5 != 0) goto L42
        L3d:
            java.lang.String r4 = r4.a()     // Catch: java.lang.Throwable -> L48
            r0 = r4
        L42:
            if (r1 == 0) goto L4c
        L44:
            r1.destroy()
            goto L4c
        L48:
            if (r1 == 0) goto L4c
            goto L44
        L4c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jdjr.risk.device.c.j.a(java.lang.String, boolean):java.lang.String");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(java.lang.String[] r5) {
        /*
            java.lang.String r0 = ""
            r1 = 0
            android.os.Looper r2 = android.os.Looper.myLooper()     // Catch: java.lang.Throwable -> L53
            android.os.Looper r3 = android.os.Looper.getMainLooper()     // Catch: java.lang.Throwable -> L53
            if (r2 == r3) goto L4d
            java.lang.ProcessBuilder r2 = new java.lang.ProcessBuilder     // Catch: java.lang.Throwable -> L53
            r2.<init>(r5)     // Catch: java.lang.Throwable -> L53
            java.lang.Process r1 = r2.start()     // Catch: java.lang.Throwable -> L53
            java.io.OutputStream r5 = r1.getOutputStream()     // Catch: java.lang.Throwable -> L53
            r5.close()     // Catch: java.lang.Throwable -> L53
            com.jdjr.risk.device.c.af r5 = new com.jdjr.risk.device.c.af     // Catch: java.lang.Throwable -> L53
            java.io.InputStream r2 = r1.getInputStream()     // Catch: java.lang.Throwable -> L53
            r5.<init>(r2)     // Catch: java.lang.Throwable -> L53
            com.jdjr.risk.biometric.core.BiometricManager r2 = com.jdjr.risk.biometric.core.BiometricManager.getInstance()     // Catch: java.lang.Throwable -> L53
            java.util.concurrent.ExecutorService r2 = r2.b()     // Catch: java.lang.Throwable -> L53
            r2.submit(r5)     // Catch: java.lang.Throwable -> L53
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L53
            r3 = 26
            if (r2 < r3) goto L42
            r2 = 2
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.SECONDS     // Catch: java.lang.Throwable -> L53
            boolean r2 = r1.waitFor(r2, r4)     // Catch: java.lang.Throwable -> L53
            if (r2 == 0) goto L4d
            goto L48
        L42:
            int r2 = r1.waitFor()     // Catch: java.lang.Throwable -> L53
            if (r2 != 0) goto L4d
        L48:
            java.lang.String r5 = r5.a()     // Catch: java.lang.Throwable -> L53
            r0 = r5
        L4d:
            if (r1 == 0) goto L57
        L4f:
            r1.destroy()
            goto L57
        L53:
            if (r1 == 0) goto L57
            goto L4f
        L57:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jdjr.risk.device.c.j.a(java.lang.String[]):java.lang.String");
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
