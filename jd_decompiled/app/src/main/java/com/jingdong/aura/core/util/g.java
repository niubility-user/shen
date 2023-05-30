package com.jingdong.aura.core.util;

import android.app.Application;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import java.io.FileInputStream;

/* loaded from: classes4.dex */
public class g {
    private static String a;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x009d: MOVE (r7 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:61:0x009d */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00ad A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00a3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(int r8) {
        /*
            Method dump skipped, instructions count: 184
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.aura.core.util.g.a(int):java.lang.String");
    }

    public static String b(Application application) {
        String str = a;
        if (str != null) {
            return str;
        }
        String c2 = c(application);
        a = c2;
        return c2;
    }

    private static String c(Application application) {
        FileInputStream fileInputStream;
        int read;
        String processName = Build.VERSION.SDK_INT > 28 ? Application.getProcessName() : "";
        if (TextUtils.isEmpty(processName)) {
            int myPid = Process.myPid();
            if (application == null || myPid <= 0) {
                return "";
            }
            byte[] bArr = new byte[128];
            FileInputStream fileInputStream2 = null;
            try {
                try {
                    fileInputStream = new FileInputStream("/proc/" + myPid + "/cmdline");
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    read = fileInputStream.read(bArr);
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream2 = fileInputStream;
                    try {
                        th.printStackTrace();
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        return "";
                    } catch (Throwable th3) {
                        if (fileInputStream2 != null) {
                            try {
                                fileInputStream2.close();
                            } catch (Throwable th4) {
                                th4.printStackTrace();
                            }
                        }
                        throw th3;
                    }
                }
            } catch (Throwable th5) {
                th5.printStackTrace();
            }
            if (read > 0) {
                for (int i2 = 0; i2 < read; i2++) {
                    if (bArr[i2] <= 128 && bArr[i2] > 0) {
                    }
                    read = i2;
                    break;
                }
                String str = new String(bArr, 0, read);
                try {
                    fileInputStream.close();
                } catch (Throwable th6) {
                    th6.printStackTrace();
                }
                return str;
            }
            fileInputStream.close();
            return "";
        }
        return processName;
    }

    public static boolean d(Application application) {
        return a(application, a(application));
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0014, code lost:
        if (r0.length() == 0) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean a(android.app.Application r1, java.lang.String r2) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            if (r0 != 0) goto L31
            if (r1 != 0) goto L9
            goto L31
        L9:
            r0 = 0
            java.lang.String r0 = b(r1)     // Catch: java.lang.Throwable -> L19
            if (r0 == 0) goto L16
            int r1 = r0.length()     // Catch: java.lang.Throwable -> L19
            if (r1 != 0) goto L1a
        L16:
            java.lang.String r0 = ""
            goto L1a
        L19:
        L1a:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L2c
            int r1 = android.os.Process.myPid()
            java.lang.String r1 = a(r1)
            java.lang.String r0 = r1.trim()
        L2c:
            boolean r1 = r2.equals(r0)
            return r1
        L31:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.aura.core.util.g.a(android.app.Application, java.lang.String):boolean");
    }

    private static String a(Application application) {
        return application.getPackageName();
    }
}
