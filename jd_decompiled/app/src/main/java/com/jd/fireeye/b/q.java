package com.jd.fireeye.b;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;
import android.text.TextUtils;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes13.dex */
public class q {
    private static ThreadLocal<DateFormat> a = new a();
    private static ThreadLocal<DecimalFormat> b = new b();

    /* loaded from: classes13.dex */
    class a extends ThreadLocal<DateFormat> {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    }

    /* loaded from: classes13.dex */
    class b extends ThreadLocal<DecimalFormat> {
        b() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public DecimalFormat initialValue() {
            return new DecimalFormat("0.0");
        }
    }

    public static String a() {
        return a.get().format(new Date());
    }

    public static boolean b() {
        return Build.VERSION.SDK_INT >= 23;
    }

    public static boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] split = str.split("-");
        return (split.length != 3 || TextUtils.isEmpty(split[0]) || split[0].startsWith("0000000000") || TextUtils.equals("", split[0]) || TextUtils.equals("", split[0]) || TextUtils.isEmpty(split[1]) || "020000000000".equals(split[1])) ? false : true;
    }

    public static boolean d(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] split = str.split("-");
        return (split.length <= 1 || TextUtils.isEmpty(split[0]) || TextUtils.equals("", split[0]) || TextUtils.equals("", split[0]) || TextUtils.isEmpty(split[1])) ? false : true;
    }

    public static String a(double d) {
        return b.get().format(d / 1000000.0d);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0038, code lost:
        if (r6 == 0) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0057, code lost:
        if (r6 != 0) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0059, code lost:
        r6.destroy();
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x005c, code lost:
        return r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:44:0x004f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r6v11, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r6v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String b(java.lang.String r6) {
        /*
            java.lang.String r0 = ""
            r1 = 0
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L44
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L44
            r3.<init>()     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L44
            java.lang.String r4 = "getprop "
            r3.append(r4)     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L44
            r3.append(r6)     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L44
            java.lang.String r6 = r3.toString()     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L44
            java.lang.Process r6 = r2.exec(r6)     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L44
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L3d java.io.IOException -> L3f
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L3d java.io.IOException -> L3f
            java.io.InputStream r4 = r6.getInputStream()     // Catch: java.lang.Throwable -> L3d java.io.IOException -> L3f
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L3d java.io.IOException -> L3f
            r4 = 1024(0x400, float:1.435E-42)
            r2.<init>(r3, r4)     // Catch: java.lang.Throwable -> L3d java.io.IOException -> L3f
            java.lang.String r0 = r2.readLine()     // Catch: java.io.IOException -> L3b java.lang.Throwable -> L5d
            r2.close()     // Catch: java.io.IOException -> L34
            goto L38
        L34:
            r1 = move-exception
            r1.printStackTrace()
        L38:
            if (r6 == 0) goto L5c
            goto L59
        L3b:
            r1 = move-exception
            goto L4a
        L3d:
            r0 = move-exception
            goto L5f
        L3f:
            r2 = move-exception
            goto L47
        L41:
            r0 = move-exception
            r6 = r1
            goto L5f
        L44:
            r6 = move-exception
            r2 = r6
            r6 = r1
        L47:
            r5 = r2
            r2 = r1
            r1 = r5
        L4a:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L5d
            if (r2 == 0) goto L57
            r2.close()     // Catch: java.io.IOException -> L53
            goto L57
        L53:
            r1 = move-exception
            r1.printStackTrace()
        L57:
            if (r6 == 0) goto L5c
        L59:
            r6.destroy()
        L5c:
            return r0
        L5d:
            r0 = move-exception
            r1 = r2
        L5f:
            if (r1 == 0) goto L69
            r1.close()     // Catch: java.io.IOException -> L65
            goto L69
        L65:
            r1 = move-exception
            r1.printStackTrace()
        L69:
            if (r6 == 0) goto L6e
            r6.destroy()
        L6e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.fireeye.b.q.b(java.lang.String):java.lang.String");
    }

    public static String a(String str) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] bytes = str.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            messageDigest.update(bytes);
            byte[] digest = messageDigest.digest();
            char[] cArr2 = new char[digest.length * 2];
            int i2 = 0;
            for (byte b2 : digest) {
                int i3 = i2 + 1;
                cArr2[i2] = cArr[(b2 >>> 4) & 15];
                i2 = i3 + 1;
                cArr2[i3] = cArr[b2 & 15];
            }
            return new String(cArr2);
        } catch (Exception unused) {
            return null;
        }
    }

    public static Boolean a(Context context) {
        Boolean bool = Boolean.FALSE;
        if (context == null) {
            return bool;
        }
        try {
            return ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1).isConnected() ? Boolean.TRUE : bool;
        } catch (Exception e2) {
            e2.printStackTrace();
            return bool;
        }
    }
}
