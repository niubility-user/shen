package com.jingdong.lib.monitor;

import android.os.SystemClock;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.sdk.oklog.OKLog;
import java.io.File;

/* loaded from: classes14.dex */
public class c {
    static {
        try {
            new File(PermissionHelper.getExternalFilesDir("crash"), "crash");
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("MonitorController", e2);
            }
        }
    }

    public static String a(long j2) {
        long j3 = j2 / 1000;
        long j4 = j3 / 3600;
        long j5 = j3 % 3600;
        return j4 + ":" + (j5 / 60) + ":" + (j5 % 60);
    }

    public static String b() {
        return a(SystemClock.uptimeMillis() - BaseApplication.startTime) + " runRealTime :" + a(SystemClock.elapsedRealtime() - BaseApplication.startRealTime);
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x008f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:43:0x0082 -> B:60:0x0085). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean c(java.lang.String r7) {
        /*
            java.lang.String r0 = "MonitorController"
            r1 = 0
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6f
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6f
            r3.<init>()     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6f
            java.lang.String r4 = "ls -l "
            r3.append(r4)     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6f
            r3.append(r7)     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6f
            java.lang.String r7 = r3.toString()     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6f
            java.lang.Process r7 = r2.exec(r7)     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6f
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L65
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L65
            java.io.InputStream r4 = r7.getInputStream()     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L65
            java.lang.String r5 = "UTF-8"
            r3.<init>(r4, r5)     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L65
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L65
            java.lang.String r1 = r2.readLine()     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L87
            if (r1 == 0) goto L55
            int r3 = r1.length()     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L87
            r4 = 4
            if (r3 < r4) goto L55
            r3 = 3
            char r1 = r1.charAt(r3)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L87
            r3 = 115(0x73, float:1.61E-43)
            if (r1 == r3) goto L46
            r3 = 120(0x78, float:1.68E-43)
            if (r1 != r3) goto L55
        L46:
            r1 = 1
            if (r7 == 0) goto L4c
            r7.destroy()
        L4c:
            r2.close()     // Catch: java.io.IOException -> L50
            goto L54
        L50:
            r7 = move-exception
            com.jingdong.sdk.oklog.OKLog.e(r0, r7)
        L54:
            return r1
        L55:
            if (r7 == 0) goto L5a
            r7.destroy()
        L5a:
            r2.close()     // Catch: java.io.IOException -> L81
            goto L85
        L5e:
            r1 = move-exception
            goto L73
        L60:
            r2 = move-exception
            r6 = r2
            r2 = r1
            r1 = r6
            goto L88
        L65:
            r2 = move-exception
            r6 = r2
            r2 = r1
            r1 = r6
            goto L73
        L6a:
            r7 = move-exception
            r2 = r1
            r1 = r7
            r7 = r2
            goto L88
        L6f:
            r7 = move-exception
            r2 = r1
            r1 = r7
            r7 = r2
        L73:
            com.jingdong.sdk.oklog.OKLog.e(r0, r1)     // Catch: java.lang.Throwable -> L87
            if (r7 == 0) goto L7b
            r7.destroy()
        L7b:
            if (r2 == 0) goto L85
            r2.close()     // Catch: java.io.IOException -> L81
            goto L85
        L81:
            r7 = move-exception
            com.jingdong.sdk.oklog.OKLog.e(r0, r7)
        L85:
            r7 = 0
            return r7
        L87:
            r1 = move-exception
        L88:
            if (r7 == 0) goto L8d
            r7.destroy()
        L8d:
            if (r2 == 0) goto L97
            r2.close()     // Catch: java.io.IOException -> L93
            goto L97
        L93:
            r7 = move-exception
            com.jingdong.sdk.oklog.OKLog.e(r0, r7)
        L97:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.lib.monitor.c.c(java.lang.String):boolean");
    }

    public static boolean d() {
        if (new File("/system/bin/su").exists() && c("/system/bin/su")) {
            return true;
        }
        return new File("/system/xbin/su").exists() && c("/system/xbin/su");
    }
}
