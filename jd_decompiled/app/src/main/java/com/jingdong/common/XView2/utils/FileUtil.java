package com.jingdong.common.XView2.utils;

/* loaded from: classes5.dex */
public class FileUtil {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0039 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getStringFromFile(android.content.Context r4, java.io.File r5) {
        /*
            r0 = 0
            if (r5 == 0) goto L32
            if (r4 == 0) goto L32
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L2b
            r4.<init>()     // Catch: java.lang.Throwable -> L2b
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L29
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L29
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L29
            r3.<init>(r5)     // Catch: java.lang.Throwable -> L29
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L29
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L29
        L19:
            java.lang.String r5 = r1.readLine()     // Catch: java.lang.Throwable -> L27
            if (r5 == 0) goto L23
            r4.append(r5)     // Catch: java.lang.Throwable -> L27
            goto L19
        L23:
            r1.close()     // Catch: java.lang.Throwable -> L31
            goto L31
        L27:
            r0 = r1
            goto L2c
        L29:
            goto L2c
        L2b:
            r4 = r0
        L2c:
            if (r0 == 0) goto L31
            r0.close()     // Catch: java.lang.Throwable -> L31
        L31:
            r0 = r4
        L32:
            if (r0 == 0) goto L39
            java.lang.String r4 = r0.toString()
            goto L3b
        L39:
            java.lang.String r4 = ""
        L3b:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.XView2.utils.FileUtil.getStringFromFile(android.content.Context, java.io.File):java.lang.String");
    }
}
