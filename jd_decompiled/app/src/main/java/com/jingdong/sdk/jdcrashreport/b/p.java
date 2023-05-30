package com.jingdong.sdk.jdcrashreport.b;

import android.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/* loaded from: classes7.dex */
public final class p {
    public static String a(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            try {
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream2);
                gZIPOutputStream.write(str.getBytes());
                gZIPOutputStream.close();
                String encodeToString = Base64.encodeToString(byteArrayOutputStream2.toByteArray(), 2);
                try {
                    byteArrayOutputStream2.close();
                    return encodeToString;
                } catch (IOException unused) {
                    return encodeToString;
                }
            } catch (IOException unused2) {
                byteArrayOutputStream = byteArrayOutputStream2;
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException unused3) {
                    }
                }
                return "";
            } catch (Throwable th) {
                th = th;
                byteArrayOutputStream = byteArrayOutputStream2;
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException unused4) {
                    }
                }
                throw th;
            }
        } catch (IOException unused5) {
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0069 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0062 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x005b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String b(java.lang.String r6) {
        /*
            if (r6 == 0) goto L8f
            int r0 = r6.length()
            if (r0 != 0) goto La
            goto L8f
        La:
            r0 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L54 java.io.IOException -> L72
            r1.<init>()     // Catch: java.lang.Throwable -> L54 java.io.IOException -> L72
            r2 = 2
            byte[] r6 = android.util.Base64.decode(r6, r2)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L52
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L52
            r2.<init>(r6)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L52
            java.util.zip.GZIPInputStream r6 = new java.util.zip.GZIPInputStream     // Catch: java.lang.Throwable -> L48 java.io.IOException -> L4d
            r6.<init>(r2)     // Catch: java.lang.Throwable -> L48 java.io.IOException -> L4d
            r0 = 4096(0x1000, float:5.74E-42)
            byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L46
        L23:
            int r3 = r6.read(r0)     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L46
            r4 = -1
            if (r3 == r4) goto L2f
            r4 = 0
            r1.write(r0, r4, r3)     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L46
            goto L23
        L2f:
            java.lang.String r0 = r1.toString()     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L46
            r6.close()     // Catch: java.io.IOException -> L36
        L36:
            r2.close()     // Catch: java.io.IOException -> L39
        L39:
            r1.close()     // Catch: java.io.IOException -> L3e
            goto L8e
        L3e:
            r6 = move-exception
            r6.printStackTrace()
            goto L8e
        L44:
            r0 = move-exception
            goto L59
        L46:
            r0 = r6
            goto L74
        L48:
            r6 = move-exception
            r5 = r0
            r0 = r6
            r6 = r5
            goto L59
        L4d:
            goto L74
        L4f:
            r6 = move-exception
            r2 = r0
            goto L57
        L52:
            r2 = r0
            goto L74
        L54:
            r6 = move-exception
            r1 = r0
            r2 = r1
        L57:
            r0 = r6
            r6 = r2
        L59:
            if (r6 == 0) goto L60
            r6.close()     // Catch: java.io.IOException -> L5f
            goto L60
        L5f:
        L60:
            if (r2 == 0) goto L67
            r2.close()     // Catch: java.io.IOException -> L66
            goto L67
        L66:
        L67:
            if (r1 == 0) goto L71
            r1.close()     // Catch: java.io.IOException -> L6d
            goto L71
        L6d:
            r6 = move-exception
            r6.printStackTrace()
        L71:
            throw r0
        L72:
            r1 = r0
            r2 = r1
        L74:
            if (r0 == 0) goto L7b
            r0.close()     // Catch: java.io.IOException -> L7a
            goto L7b
        L7a:
        L7b:
            if (r2 == 0) goto L82
            r2.close()     // Catch: java.io.IOException -> L81
            goto L82
        L81:
        L82:
            if (r1 == 0) goto L8c
            r1.close()     // Catch: java.io.IOException -> L88
            goto L8c
        L88:
            r6 = move-exception
            r6.printStackTrace()
        L8c:
            java.lang.String r0 = ""
        L8e:
            return r0
        L8f:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.jdcrashreport.b.p.b(java.lang.String):java.lang.String");
    }
}
