package com.jingdong.common.utils;

import com.jingdong.sdk.oklog.OKLog;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/* loaded from: classes6.dex */
public class BitmapkitZip {
    private static final String TAG = "BitmapkitZip";

    public static byte[] objectToBytes(Object obj) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            objectOutputStream.close();
            byteArrayOutputStream.close();
            return byteArray;
        } catch (IOException e2) {
            OKLog.e(TAG, e2);
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x003d, code lost:
        if (r7.length() <= 0) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0047, code lost:
        if (r3.getName().endsWith(r7) != false) goto L78;
     */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0086: MOVE (r1 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:50:0x0086 */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0089 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] unZip(java.lang.String r5, java.lang.String r6, java.lang.String r7) {
        /*
            java.lang.String r0 = "BitmapkitZip"
            r1 = 0
            java.util.zip.ZipFile r2 = new java.util.zip.ZipFile     // Catch: java.lang.Throwable -> L73 java.lang.Exception -> L75
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L73 java.lang.Exception -> L75
            r3.<init>(r5)     // Catch: java.lang.Throwable -> L73 java.lang.Exception -> L75
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L73 java.lang.Exception -> L75
            java.util.Enumeration r5 = r2.entries()     // Catch: java.lang.Exception -> L71 java.lang.Throwable -> L85
        L11:
            boolean r3 = r5.hasMoreElements()     // Catch: java.lang.Exception -> L71 java.lang.Throwable -> L85
            if (r3 == 0) goto L6d
            java.lang.Object r3 = r5.nextElement()     // Catch: java.lang.Exception -> L71 java.lang.Throwable -> L85
            java.util.zip.ZipEntry r3 = (java.util.zip.ZipEntry) r3     // Catch: java.lang.Exception -> L71 java.lang.Throwable -> L85
            boolean r4 = r3.isDirectory()     // Catch: java.lang.Exception -> L71 java.lang.Throwable -> L85
            if (r4 == 0) goto L24
            goto L11
        L24:
            if (r6 == 0) goto L37
            int r4 = r6.length()     // Catch: java.lang.Exception -> L71 java.lang.Throwable -> L85
            if (r4 <= 0) goto L37
            java.lang.String r4 = r3.getName()     // Catch: java.lang.Exception -> L71 java.lang.Throwable -> L85
            boolean r4 = r4.startsWith(r6)     // Catch: java.lang.Exception -> L71 java.lang.Throwable -> L85
            if (r4 != 0) goto L37
            goto L11
        L37:
            if (r7 == 0) goto L4a
            int r4 = r7.length()     // Catch: java.lang.Exception -> L71 java.lang.Throwable -> L85
            if (r4 <= 0) goto L4a
            java.lang.String r4 = r3.getName()     // Catch: java.lang.Exception -> L71 java.lang.Throwable -> L85
            boolean r4 = r4.endsWith(r7)     // Catch: java.lang.Exception -> L71 java.lang.Throwable -> L85
            if (r4 != 0) goto L4a
            goto L11
        L4a:
            java.io.InputStream r5 = r2.getInputStream(r3)     // Catch: java.lang.Exception -> L71 java.lang.Throwable -> L85
            long r6 = r3.getSize()     // Catch: java.lang.Exception -> L71 java.lang.Throwable -> L85
            int r7 = (int) r6     // Catch: java.lang.Exception -> L71 java.lang.Throwable -> L85
            byte[] r6 = new byte[r7]     // Catch: java.lang.Exception -> L71 java.lang.Throwable -> L85
            int r5 = r5.read(r6)     // Catch: java.lang.Exception -> L71 java.lang.Throwable -> L85
            if (r5 == r7) goto L64
            r2.close()     // Catch: java.io.IOException -> L5f
            goto L63
        L5f:
            r5 = move-exception
            com.jingdong.sdk.oklog.OKLog.e(r0, r5)
        L63:
            return r1
        L64:
            r2.close()     // Catch: java.io.IOException -> L68
            goto L6c
        L68:
            r5 = move-exception
            com.jingdong.sdk.oklog.OKLog.e(r0, r5)
        L6c:
            return r6
        L6d:
            r2.close()     // Catch: java.io.IOException -> L80
            goto L84
        L71:
            r5 = move-exception
            goto L77
        L73:
            r5 = move-exception
            goto L87
        L75:
            r5 = move-exception
            r2 = r1
        L77:
            com.jingdong.sdk.oklog.OKLog.e(r0, r5)     // Catch: java.lang.Throwable -> L85
            if (r2 == 0) goto L84
            r2.close()     // Catch: java.io.IOException -> L80
            goto L84
        L80:
            r5 = move-exception
            com.jingdong.sdk.oklog.OKLog.e(r0, r5)
        L84:
            return r1
        L85:
            r5 = move-exception
            r1 = r2
        L87:
            if (r1 == 0) goto L91
            r1.close()     // Catch: java.io.IOException -> L8d
            goto L91
        L8d:
            r6 = move-exception
            com.jingdong.sdk.oklog.OKLog.e(r0, r6)
        L91:
            goto L93
        L92:
            throw r5
        L93:
            goto L92
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.utils.BitmapkitZip.unZip(java.lang.String, java.lang.String, java.lang.String):byte[]");
    }
}
