package com.jd.android.sdk.coreinfo.util;

/* loaded from: classes.dex */
public class FileReaderUtil {
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0040, code lost:
        if (r1 == null) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String read(java.lang.String r4, boolean r5) {
        /*
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.io.File r1 = new java.io.File
            r1.<init>(r4)
            boolean r4 = r1.exists()
            java.lang.String r2 = "\n"
            if (r4 == 0) goto L43
            java.io.FileReader r4 = new java.io.FileReader     // Catch: java.lang.Throwable -> L3f
            r4.<init>(r1)     // Catch: java.lang.Throwable -> L3f
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L3f
            r1.<init>(r4)     // Catch: java.lang.Throwable -> L3f
        L1c:
            java.lang.String r4 = r1.readLine()     // Catch: java.lang.Throwable -> L3d
            if (r4 == 0) goto L37
            if (r5 == 0) goto L33
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3d
            r3.<init>()     // Catch: java.lang.Throwable -> L3d
            r3.append(r4)     // Catch: java.lang.Throwable -> L3d
            r3.append(r2)     // Catch: java.lang.Throwable -> L3d
            java.lang.String r4 = r3.toString()     // Catch: java.lang.Throwable -> L3d
        L33:
            r0.append(r4)     // Catch: java.lang.Throwable -> L3d
            goto L1c
        L37:
            r1.close()     // Catch: java.io.IOException -> L3b
            goto L43
        L3b:
            goto L43
        L3d:
            goto L40
        L3f:
            r1 = 0
        L40:
            if (r1 == 0) goto L43
            goto L37
        L43:
            java.lang.String r4 = r0.toString()
            java.lang.String r4 = r4.trim()
            if (r5 == 0) goto L5e
            boolean r5 = r4.endsWith(r2)
            if (r5 == 0) goto L5e
            r5 = 0
            int r0 = r4.length()
            int r0 = r0 + (-2)
            java.lang.String r4 = r4.substring(r5, r0)
        L5e:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.android.sdk.coreinfo.util.FileReaderUtil.read(java.lang.String, boolean):java.lang.String");
    }
}
