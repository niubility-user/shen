package com.jd.android.sdk.coreinfo.util;

/* loaded from: classes12.dex */
public class CommandUtil {
    public static String handlerCommand(String str) {
        return handlerCommand(str, false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x004b, code lost:
        if (r1 != null) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x004d, code lost:
        r1.destroy();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0051, code lost:
        if (0 == 0) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0054, code lost:
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String handlerCommand(java.lang.String r4, boolean r5) {
        /*
            java.lang.String r0 = ""
            r1 = 0
            android.os.Looper r2 = android.os.Looper.myLooper()     // Catch: java.lang.Throwable -> L49
            android.os.Looper r3 = android.os.Looper.getMainLooper()     // Catch: java.lang.Throwable -> L49
            if (r2 == r3) goto L4b
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L49
            java.lang.Process r1 = r2.exec(r4)     // Catch: java.lang.Throwable -> L49
            java.io.OutputStream r4 = r1.getOutputStream()     // Catch: java.lang.Throwable -> L49
            r4.close()     // Catch: java.lang.Throwable -> L49
            com.jd.android.sdk.coreinfo.util.c r4 = new com.jd.android.sdk.coreinfo.util.c     // Catch: java.lang.Throwable -> L49
            java.io.InputStream r2 = r1.getInputStream()     // Catch: java.lang.Throwable -> L49
            r4.<init>(r2, r5)     // Catch: java.lang.Throwable -> L49
            r4.start()     // Catch: java.lang.Throwable -> L49
            int r5 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L49
            r2 = 26
            if (r5 < r2) goto L3d
            r2 = 2
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.SECONDS     // Catch: java.lang.Throwable -> L49
            boolean r5 = r1.waitFor(r2, r5)     // Catch: java.lang.Throwable -> L49
            if (r5 == 0) goto L4b
            java.lang.String r4 = r4.a()     // Catch: java.lang.Throwable -> L49
            goto L47
        L3d:
            int r5 = r1.waitFor()     // Catch: java.lang.Throwable -> L49
            if (r5 != 0) goto L4b
            java.lang.String r4 = r4.a()     // Catch: java.lang.Throwable -> L49
        L47:
            r0 = r4
            goto L4b
        L49:
            goto L51
        L4b:
            if (r1 == 0) goto L54
        L4d:
            r1.destroy()
            goto L54
        L51:
            if (r1 == 0) goto L54
            goto L4d
        L54:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.android.sdk.coreinfo.util.CommandUtil.handlerCommand(java.lang.String, boolean):java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x004c, code lost:
        if (r1 != null) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x004e, code lost:
        r1.destroy();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0052, code lost:
        if (0 == 0) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0055, code lost:
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String handlerCommand(java.lang.String[] r4, boolean r5) {
        /*
            java.lang.String r0 = ""
            r1 = 0
            android.os.Looper r2 = android.os.Looper.myLooper()     // Catch: java.lang.Throwable -> L4a
            android.os.Looper r3 = android.os.Looper.getMainLooper()     // Catch: java.lang.Throwable -> L4a
            if (r2 == r3) goto L4c
            java.lang.ProcessBuilder r2 = new java.lang.ProcessBuilder     // Catch: java.lang.Throwable -> L4a
            r2.<init>(r4)     // Catch: java.lang.Throwable -> L4a
            java.lang.Process r1 = r2.start()     // Catch: java.lang.Throwable -> L4a
            java.io.OutputStream r4 = r1.getOutputStream()     // Catch: java.lang.Throwable -> L4a
            r4.close()     // Catch: java.lang.Throwable -> L4a
            com.jd.android.sdk.coreinfo.util.c r4 = new com.jd.android.sdk.coreinfo.util.c     // Catch: java.lang.Throwable -> L4a
            java.io.InputStream r2 = r1.getInputStream()     // Catch: java.lang.Throwable -> L4a
            r4.<init>(r2, r5)     // Catch: java.lang.Throwable -> L4a
            r4.start()     // Catch: java.lang.Throwable -> L4a
            int r5 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L4a
            r2 = 26
            if (r5 < r2) goto L3e
            r2 = 2
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.SECONDS     // Catch: java.lang.Throwable -> L4a
            boolean r5 = r1.waitFor(r2, r5)     // Catch: java.lang.Throwable -> L4a
            if (r5 == 0) goto L4c
            java.lang.String r4 = r4.a()     // Catch: java.lang.Throwable -> L4a
            goto L48
        L3e:
            int r5 = r1.waitFor()     // Catch: java.lang.Throwable -> L4a
            if (r5 != 0) goto L4c
            java.lang.String r4 = r4.a()     // Catch: java.lang.Throwable -> L4a
        L48:
            r0 = r4
            goto L4c
        L4a:
            goto L52
        L4c:
            if (r1 == 0) goto L55
        L4e:
            r1.destroy()
            goto L55
        L52:
            if (r1 == 0) goto L55
            goto L4e
        L55:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.android.sdk.coreinfo.util.CommandUtil.handlerCommand(java.lang.String[], boolean):java.lang.String");
    }
}
