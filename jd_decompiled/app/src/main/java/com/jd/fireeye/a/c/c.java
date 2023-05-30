package com.jd.fireeye.a.c;

import java.io.BufferedInputStream;

/* loaded from: classes13.dex */
public class c {
    /* JADX WARN: Removed duplicated region for block: B:41:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0066 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0082 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x005c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0078 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:81:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(java.lang.String r5) {
        /*
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L74
            java.lang.String r2 = "sh"
            java.lang.Process r1 = r1.exec(r2)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L74
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L75
            java.io.OutputStream r3 = r1.getOutputStream()     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L75
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L75
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L52
            java.io.InputStream r4 = r1.getInputStream()     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L52
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L52
            byte[] r5 = r5.getBytes()     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L53
            r2.write(r5)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L53
            r5 = 10
            r2.write(r5)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L53
            r2.flush()     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L53
            r2.close()     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L53
            r1.waitFor()     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L53
            java.lang.String r5 = a(r3)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L53
            r2.close()     // Catch: java.io.IOException -> L3a
            goto L3e
        L3a:
            r0 = move-exception
            r0.printStackTrace()
        L3e:
            r3.close()     // Catch: java.io.IOException -> L42
            goto L46
        L42:
            r0 = move-exception
            r0.printStackTrace()
        L46:
            if (r1 == 0) goto L4b
            r1.destroy()
        L4b:
            return r5
        L4c:
            r5 = move-exception
            goto L50
        L4e:
            r5 = move-exception
            r3 = r0
        L50:
            r0 = r2
            goto L5a
        L52:
            r3 = r0
        L53:
            r0 = r2
            goto L76
        L55:
            r5 = move-exception
            goto L59
        L57:
            r5 = move-exception
            r1 = r0
        L59:
            r3 = r0
        L5a:
            if (r0 == 0) goto L64
            r0.close()     // Catch: java.io.IOException -> L60
            goto L64
        L60:
            r0 = move-exception
            r0.printStackTrace()
        L64:
            if (r3 == 0) goto L6e
            r3.close()     // Catch: java.io.IOException -> L6a
            goto L6e
        L6a:
            r0 = move-exception
            r0.printStackTrace()
        L6e:
            if (r1 == 0) goto L73
            r1.destroy()
        L73:
            throw r5
        L74:
            r1 = r0
        L75:
            r3 = r0
        L76:
            if (r0 == 0) goto L80
            r0.close()     // Catch: java.io.IOException -> L7c
            goto L80
        L7c:
            r5 = move-exception
            r5.printStackTrace()
        L80:
            if (r3 == 0) goto L8a
            r3.close()     // Catch: java.io.IOException -> L86
            goto L8a
        L86:
            r5 = move-exception
            r5.printStackTrace()
        L8a:
            if (r1 == 0) goto L8f
            r1.destroy()
        L8f:
            java.lang.String r5 = ""
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.fireeye.a.c.c.a(java.lang.String):java.lang.String");
    }

    private static String a(BufferedInputStream bufferedInputStream) {
        if (bufferedInputStream == null) {
            return "";
        }
        byte[] bArr = new byte[1024];
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                int read = bufferedInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                sb.append(new String(bArr, 0, read));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return sb.toString();
    }
}
