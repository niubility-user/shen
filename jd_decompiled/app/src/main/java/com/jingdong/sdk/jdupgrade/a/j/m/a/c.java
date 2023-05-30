package com.jingdong.sdk.jdupgrade.a.j.m.a;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public final class c {
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0043, code lost:
        if (r1 == null) goto L31;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.Map<java.lang.Integer, java.nio.ByteBuffer> a(java.io.File r3) {
        /*
            r0 = 0
            java.io.RandomAccessFile r1 = new java.io.RandomAccessFile     // Catch: java.lang.Throwable -> L29 java.io.IOException -> L3a
            java.lang.String r2 = "r"
            r1.<init>(r3, r2)     // Catch: java.lang.Throwable -> L29 java.io.IOException -> L3a
            java.nio.channels.FileChannel r3 = r1.getChannel()     // Catch: java.lang.Throwable -> L24 java.io.IOException -> L27
            com.jingdong.sdk.jdupgrade.a.j.m.a.b r2 = com.jingdong.sdk.jdupgrade.a.j.m.a.a.a(r3)     // Catch: java.lang.Throwable -> L20 java.io.IOException -> L22
            java.lang.Object r2 = r2.a()     // Catch: java.lang.Throwable -> L20 java.io.IOException -> L22
            java.nio.ByteBuffer r2 = (java.nio.ByteBuffer) r2     // Catch: java.lang.Throwable -> L20 java.io.IOException -> L22
            java.util.Map r0 = com.jingdong.sdk.jdupgrade.a.j.m.a.a.b(r2)     // Catch: java.lang.Throwable -> L20 java.io.IOException -> L22
            if (r3 == 0) goto L45
            r3.close()     // Catch: java.io.IOException -> L45 java.lang.Throwable -> L48
            goto L45
        L20:
            r2 = move-exception
            goto L2d
        L22:
            goto L3c
        L24:
            r2 = move-exception
            r3 = r0
            goto L2d
        L27:
            r3 = r0
            goto L3c
        L29:
            r3 = move-exception
            r2 = r3
            r3 = r0
            r1 = r3
        L2d:
            if (r3 == 0) goto L34
            r3.close()     // Catch: java.io.IOException -> L33 java.lang.Throwable -> L48
            goto L34
        L33:
        L34:
            if (r1 == 0) goto L39
            r1.close()     // Catch: java.io.IOException -> L39 java.lang.Throwable -> L48
        L39:
            throw r2     // Catch: java.lang.Throwable -> L48
        L3a:
            r3 = r0
            r1 = r3
        L3c:
            if (r3 == 0) goto L43
            r3.close()     // Catch: java.io.IOException -> L42 java.lang.Throwable -> L48
            goto L43
        L42:
        L43:
            if (r1 == 0) goto L48
        L45:
            r1.close()     // Catch: java.lang.Throwable -> L48
        L48:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.jdupgrade.a.j.m.a.c.a(java.io.File):java.util.Map");
    }

    public static byte[] a(ByteBuffer byteBuffer) {
        byte[] array = byteBuffer.array();
        int arrayOffset = byteBuffer.arrayOffset();
        return Arrays.copyOfRange(array, byteBuffer.position() + arrayOffset, arrayOffset + byteBuffer.limit());
    }

    public static Map<Integer, String> b(File file) {
        Map<Integer, ByteBuffer> a = a(file);
        if (a == null) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<Integer, ByteBuffer> entry : a.entrySet()) {
            try {
                linkedHashMap.put(entry.getKey(), new String(a(entry.getValue()), "UTF-8"));
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
        }
        return linkedHashMap;
    }
}
