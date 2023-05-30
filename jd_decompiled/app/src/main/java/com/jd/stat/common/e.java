package com.jd.stat.common;

import android.app.ActivityManager;
import android.os.Debug;
import android.os.Process;
import java.io.File;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes18.dex */
public class e {
    private static String a = "TracerPid";

    public static String a() {
        try {
            return ActivityManager.isUserAMonkey() ? "1" : "0";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "c";
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0051, code lost:
        if (java.lang.Integer.decode(r0.substring(com.jd.stat.common.e.a.length() + 1).trim()).intValue() <= 0) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0055, code lost:
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0059, code lost:
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x005a, code lost:
        r1.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x005e, code lost:
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0062, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0063, code lost:
        r0.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0066, code lost:
        return "0";
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:?, code lost:
        return "0";
     */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0087 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String b() {
        /*
            r0 = 0
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L70
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L70
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L70
            java.lang.String r4 = "/proc/self/status"
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L70
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L70
            r3 = 1000(0x3e8, float:1.401E-42)
            r1.<init>(r2, r3)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L70
        L14:
            java.lang.String r0 = r1.readLine()     // Catch: java.lang.Exception -> L69 java.lang.Throwable -> L84
            if (r0 == 0) goto L5e
            int r2 = r0.length()     // Catch: java.lang.Exception -> L69 java.lang.Throwable -> L84
            java.lang.String r3 = com.jd.stat.common.e.a     // Catch: java.lang.Exception -> L69 java.lang.Throwable -> L84
            int r3 = r3.length()     // Catch: java.lang.Exception -> L69 java.lang.Throwable -> L84
            if (r2 <= r3) goto L14
            r2 = 0
            java.lang.String r3 = com.jd.stat.common.e.a     // Catch: java.lang.Exception -> L69 java.lang.Throwable -> L84
            int r3 = r3.length()     // Catch: java.lang.Exception -> L69 java.lang.Throwable -> L84
            java.lang.String r2 = r0.substring(r2, r3)     // Catch: java.lang.Exception -> L69 java.lang.Throwable -> L84
            java.lang.String r3 = com.jd.stat.common.e.a     // Catch: java.lang.Exception -> L69 java.lang.Throwable -> L84
            boolean r2 = r2.equalsIgnoreCase(r3)     // Catch: java.lang.Exception -> L69 java.lang.Throwable -> L84
            if (r2 == 0) goto L14
            java.lang.String r2 = com.jd.stat.common.e.a     // Catch: java.lang.Exception -> L69 java.lang.Throwable -> L84
            int r2 = r2.length()     // Catch: java.lang.Exception -> L69 java.lang.Throwable -> L84
            int r2 = r2 + 1
            java.lang.String r0 = r0.substring(r2)     // Catch: java.lang.Exception -> L69 java.lang.Throwable -> L84
            java.lang.String r0 = r0.trim()     // Catch: java.lang.Exception -> L69 java.lang.Throwable -> L84
            java.lang.Integer r0 = java.lang.Integer.decode(r0)     // Catch: java.lang.Exception -> L69 java.lang.Throwable -> L84
            int r0 = r0.intValue()     // Catch: java.lang.Exception -> L69 java.lang.Throwable -> L84
            if (r0 <= 0) goto L5e
            java.lang.String r0 = "1"
            r1.close()     // Catch: java.io.IOException -> L59
            goto L5d
        L59:
            r1 = move-exception
            r1.printStackTrace()
        L5d:
            return r0
        L5e:
            r1.close()     // Catch: java.io.IOException -> L62
            goto L66
        L62:
            r0 = move-exception
            r0.printStackTrace()
        L66:
            java.lang.String r0 = "0"
            return r0
        L69:
            r0 = move-exception
            goto L74
        L6b:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L85
        L70:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
        L74:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L84
            java.lang.String r0 = "c"
            if (r1 == 0) goto L83
            r1.close()     // Catch: java.io.IOException -> L7f
            goto L83
        L7f:
            r1 = move-exception
            r1.printStackTrace()
        L83:
            return r0
        L84:
            r0 = move-exception
        L85:
            if (r1 == 0) goto L8f
            r1.close()     // Catch: java.io.IOException -> L8b
            goto L8f
        L8b:
            r1 = move-exception
            r1.printStackTrace()
        L8f:
            goto L91
        L90:
            throw r0
        L91:
            goto L90
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.stat.common.e.b():java.lang.String");
    }

    public static String c() {
        try {
            return Debug.isDebuggerConnected() ? "1" : "0";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "c";
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x004d, code lost:
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x004e, code lost:
        r1.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:50:0x007f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String d() {
        /*
            r0 = 0
            int r1 = g()     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L68
            if (r1 <= 0) goto L56
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L68
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L68
            r3.<init>()     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L68
            java.lang.String r4 = "/proc/"
            r3.append(r4)     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L68
            r3.append(r1)     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L68
            java.lang.String r1 = "/cmdline"
            r3.append(r1)     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L68
            java.lang.String r1 = r3.toString()     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L68
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L68
            boolean r1 = r2.exists()     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L68
            if (r1 == 0) goto L56
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L68
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L68
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L68
            r4.<init>(r2)     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L68
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L68
            r2 = 1000(0x3e8, float:1.401E-42)
            r1.<init>(r3, r2)     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L68
        L39:
            java.lang.String r0 = r1.readLine()     // Catch: java.lang.Exception -> L54 java.lang.Throwable -> L7c
            if (r0 == 0) goto L52
            java.lang.String r2 = "gdb"
            boolean r0 = r0.contains(r2)     // Catch: java.lang.Exception -> L54 java.lang.Throwable -> L7c
            if (r0 == 0) goto L39
            java.lang.String r0 = "1"
            r1.close()     // Catch: java.io.IOException -> L4d
            goto L51
        L4d:
            r1 = move-exception
            r1.printStackTrace()
        L51:
            return r0
        L52:
            r0 = r1
            goto L56
        L54:
            r0 = move-exception
            goto L6c
        L56:
            if (r0 == 0) goto L60
            r0.close()     // Catch: java.io.IOException -> L5c
            goto L60
        L5c:
            r0 = move-exception
            r0.printStackTrace()
        L60:
            java.lang.String r0 = "0"
            return r0
        L63:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L7d
        L68:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
        L6c:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L7c
            java.lang.String r0 = "c"
            if (r1 == 0) goto L7b
            r1.close()     // Catch: java.io.IOException -> L77
            goto L7b
        L77:
            r1 = move-exception
            r1.printStackTrace()
        L7b:
            return r0
        L7c:
            r0 = move-exception
        L7d:
            if (r1 == 0) goto L87
            r1.close()     // Catch: java.io.IOException -> L83
            goto L87
        L83:
            r1 = move-exception
            r1.printStackTrace()
        L87:
            goto L89
        L88:
            throw r0
        L89:
            goto L88
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.stat.common.e.d():java.lang.String");
    }

    public static String e() {
        try {
            return EncryptUtil.androidServerDetected() ? "1" : "0";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "c";
        }
    }

    public static String f() {
        try {
            File[] listFiles = new File("/proc/self/fd").listFiles();
            if (listFiles == null || listFiles.length <= 0) {
                return "0";
            }
            for (File file : listFiles) {
                if ("5".equals(file.getName())) {
                    return "1";
                }
            }
            return "0";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "c";
        }
    }

    private static int g() {
        Process process;
        try {
            try {
                process = (Process) Process.class.newInstance();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
                process = null;
                return ((Integer) Process.class.getMethod("myPpid", new Class[0]).invoke(process, new Object[0])).intValue();
            } catch (InstantiationException e3) {
                e3.printStackTrace();
                process = null;
                return ((Integer) Process.class.getMethod("myPpid", new Class[0]).invoke(process, new Object[0])).intValue();
            } catch (Throwable th) {
                th.printStackTrace();
                process = null;
                return ((Integer) Process.class.getMethod("myPpid", new Class[0]).invoke(process, new Object[0])).intValue();
            }
            return ((Integer) Process.class.getMethod("myPpid", new Class[0]).invoke(process, new Object[0])).intValue();
        } catch (IllegalAccessException e4) {
            e4.printStackTrace();
            return 0;
        } catch (NoSuchMethodException e5) {
            e5.printStackTrace();
            return 0;
        } catch (InvocationTargetException e6) {
            e6.printStackTrace();
            return 0;
        } catch (Throwable th2) {
            th2.printStackTrace();
            return 0;
        }
    }
}
