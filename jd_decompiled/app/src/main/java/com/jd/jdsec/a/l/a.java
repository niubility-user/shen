package com.jd.jdsec.a.l;

import java.io.BufferedInputStream;

/* loaded from: classes13.dex */
public class a {
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

    /* JADX WARN: Removed duplicated region for block: B:41:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x006d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0089 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0063 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x007f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:81:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String b(java.lang.String r5) {
        /*
            java.lang.ProcessBuilder r0 = new java.lang.ProcessBuilder
            r1 = 1
            java.lang.String[] r1 = new java.lang.String[r1]
            r2 = 0
            java.lang.String r3 = "sh"
            r1[r2] = r3
            r0.<init>(r1)
            r1 = 0
            java.lang.Process r0 = r0.start()     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L7b
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L7c
            java.io.OutputStream r3 = r0.getOutputStream()     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L7c
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L7c
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L59
            java.io.InputStream r4 = r0.getInputStream()     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L59
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L55 java.lang.Exception -> L59
            byte[] r5 = r5.getBytes()     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L5a
            r2.write(r5)     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L5a
            r5 = 10
            r2.write(r5)     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L5a
            r2.flush()     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L5a
            r2.close()     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L5a
            r0.waitFor()     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L5a
            java.lang.String r5 = a(r3)     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L5a
            r2.close()     // Catch: java.io.IOException -> L41
            goto L45
        L41:
            r1 = move-exception
            r1.printStackTrace()
        L45:
            r3.close()     // Catch: java.io.IOException -> L49
            goto L4d
        L49:
            r1 = move-exception
            r1.printStackTrace()
        L4d:
            if (r0 == 0) goto L52
            r0.destroy()
        L52:
            return r5
        L53:
            r5 = move-exception
            goto L57
        L55:
            r5 = move-exception
            r3 = r1
        L57:
            r1 = r2
            goto L61
        L59:
            r3 = r1
        L5a:
            r1 = r2
            goto L7d
        L5c:
            r5 = move-exception
            goto L60
        L5e:
            r5 = move-exception
            r0 = r1
        L60:
            r3 = r1
        L61:
            if (r1 == 0) goto L6b
            r1.close()     // Catch: java.io.IOException -> L67
            goto L6b
        L67:
            r1 = move-exception
            r1.printStackTrace()
        L6b:
            if (r3 == 0) goto L75
            r3.close()     // Catch: java.io.IOException -> L71
            goto L75
        L71:
            r1 = move-exception
            r1.printStackTrace()
        L75:
            if (r0 == 0) goto L7a
            r0.destroy()
        L7a:
            throw r5
        L7b:
            r0 = r1
        L7c:
            r3 = r1
        L7d:
            if (r1 == 0) goto L87
            r1.close()     // Catch: java.io.IOException -> L83
            goto L87
        L83:
            r5 = move-exception
            r5.printStackTrace()
        L87:
            if (r3 == 0) goto L91
            r3.close()     // Catch: java.io.IOException -> L8d
            goto L91
        L8d:
            r5 = move-exception
            r5.printStackTrace()
        L91:
            if (r0 == 0) goto L96
            r0.destroy()
        L96:
            java.lang.String r5 = ""
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.jdsec.a.l.a.b(java.lang.String):java.lang.String");
    }
}
