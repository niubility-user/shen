package com.jingdong.jdma.common.utils;

import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import com.jingdong.jdma.minterface.JDMABaseInfo;

/* loaded from: classes12.dex */
public class i {
    static String a = "";
    private static String b;

    /* renamed from: c  reason: collision with root package name */
    private static int f12708c;

    public static boolean a(Context context) {
        if (context == null) {
            return false;
        }
        if (TextUtils.isEmpty(a)) {
            a = a(Process.myPid());
        }
        return !TextUtils.isEmpty(a) && context.getPackageName().equals(a);
    }

    public static String b() {
        JDMABaseInfo jDMABaseInfo = c.v;
        if (jDMABaseInfo != null) {
            try {
                return jDMABaseInfo.getOsVersionName();
            } catch (Throwable th) {
                th.printStackTrace();
                return "";
            }
        } else if (c.p) {
            if (TextUtils.isEmpty(b)) {
                b = Build.VERSION.RELEASE;
            }
            return b;
        } else {
            return "";
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x007d, code lost:
        if (r2 == null) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0097, code lost:
        if (r2 == null) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0099, code lost:
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x009c, code lost:
        return "";
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:?, code lost:
        return "";
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String a(int r9) {
        /*
            java.lang.String r0 = "OsUtil"
            r1 = 0
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L66 java.io.FileNotFoundException -> L80
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L66 java.io.FileNotFoundException -> L80
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L66 java.io.FileNotFoundException -> L80
            r4.<init>()     // Catch: java.lang.Throwable -> L66 java.io.FileNotFoundException -> L80
            java.lang.String r5 = "/proc/"
            r4.append(r5)     // Catch: java.lang.Throwable -> L66 java.io.FileNotFoundException -> L80
            r4.append(r9)     // Catch: java.lang.Throwable -> L66 java.io.FileNotFoundException -> L80
            java.lang.String r9 = "/cmdline"
            r4.append(r9)     // Catch: java.lang.Throwable -> L66 java.io.FileNotFoundException -> L80
            java.lang.String r9 = r4.toString()     // Catch: java.lang.Throwable -> L66 java.io.FileNotFoundException -> L80
            r3.<init>(r9)     // Catch: java.lang.Throwable -> L66 java.io.FileNotFoundException -> L80
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L66 java.io.FileNotFoundException -> L80
            java.io.BufferedReader r9 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L5c java.io.FileNotFoundException -> L61
            r9.<init>(r2)     // Catch: java.lang.Throwable -> L5c java.io.FileNotFoundException -> L61
            r1 = 64
            char[] r3 = new char[r1]     // Catch: java.lang.Throwable -> L52 java.io.FileNotFoundException -> L57
            r9.read(r3)     // Catch: java.lang.Throwable -> L52 java.io.FileNotFoundException -> L57
            r4 = 0
            r5 = 0
            r6 = 0
        L32:
            if (r5 >= r1) goto L3d
            char r7 = r3[r5]     // Catch: java.lang.Throwable -> L52 java.io.FileNotFoundException -> L57
            if (r7 == 0) goto L3d
            int r6 = r6 + 1
            int r5 = r5 + 1
            goto L32
        L3d:
            java.lang.String r1 = new java.lang.String     // Catch: java.lang.Throwable -> L52 java.io.FileNotFoundException -> L57
            r1.<init>(r3, r4, r6)     // Catch: java.lang.Throwable -> L52 java.io.FileNotFoundException -> L57
            r2.close()     // Catch: java.lang.Throwable -> L46
            goto L4e
        L46:
            r2 = move-exception
            java.lang.String r2 = r2.toString()
            com.jingdong.jdma.common.utils.LogUtil.d(r0, r2)
        L4e:
            r9.close()     // Catch: java.lang.Throwable -> L51
        L51:
            return r1
        L52:
            r1 = move-exception
            r8 = r2
            r2 = r9
            r9 = r1
            goto L5f
        L57:
            r1 = move-exception
            r8 = r2
            r2 = r9
            r9 = r1
            goto L64
        L5c:
            r9 = move-exception
            r8 = r2
            r2 = r1
        L5f:
            r1 = r8
            goto L68
        L61:
            r9 = move-exception
            r8 = r2
            r2 = r1
        L64:
            r1 = r8
            goto L82
        L66:
            r9 = move-exception
            r2 = r1
        L68:
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> L9f
            com.jingdong.jdma.common.utils.LogUtil.d(r0, r9)     // Catch: java.lang.Throwable -> L9f
            if (r1 == 0) goto L7d
            r1.close()     // Catch: java.lang.Throwable -> L75
            goto L7d
        L75:
            r9 = move-exception
            java.lang.String r9 = r9.toString()
            com.jingdong.jdma.common.utils.LogUtil.d(r0, r9)
        L7d:
            if (r2 == 0) goto L9c
            goto L99
        L80:
            r9 = move-exception
            r2 = r1
        L82:
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> L9f
            com.jingdong.jdma.common.utils.LogUtil.d(r0, r9)     // Catch: java.lang.Throwable -> L9f
            if (r1 == 0) goto L97
            r1.close()     // Catch: java.lang.Throwable -> L8f
            goto L97
        L8f:
            r9 = move-exception
            java.lang.String r9 = r9.toString()
            com.jingdong.jdma.common.utils.LogUtil.d(r0, r9)
        L97:
            if (r2 == 0) goto L9c
        L99:
            r2.close()     // Catch: java.lang.Throwable -> L9c
        L9c:
            java.lang.String r9 = ""
            return r9
        L9f:
            r9 = move-exception
            if (r1 == 0) goto Lae
            r1.close()     // Catch: java.lang.Throwable -> La6
            goto Lae
        La6:
            r1 = move-exception
            java.lang.String r1 = r1.toString()
            com.jingdong.jdma.common.utils.LogUtil.d(r0, r1)
        Lae:
            if (r2 == 0) goto Lb3
            r2.close()     // Catch: java.lang.Throwable -> Lb3
        Lb3:
            goto Lb5
        Lb4:
            throw r9
        Lb5:
            goto Lb4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdma.common.utils.i.a(int):java.lang.String");
    }

    public static int a() {
        JDMABaseInfo jDMABaseInfo = c.v;
        if (jDMABaseInfo != null) {
            return jDMABaseInfo.getOsVersionInt();
        }
        if (c.p) {
            if (f12708c == 0) {
                f12708c = Build.VERSION.SDK_INT;
            }
            return f12708c;
        }
        return 0;
    }
}
