package com.meituan.android.walle;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Map;

/* loaded from: classes.dex */
public final class PayloadReader {
    private PayloadReader() {
    }

    public static byte[] get(File file, int i2) {
        ByteBuffer byteBuffer;
        Map<Integer, ByteBuffer> all = getAll(file);
        if (all == null || (byteBuffer = all.get(Integer.valueOf(i2))) == null) {
            return null;
        }
        return getBytes(byteBuffer);
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0044, code lost:
        if (r1 == null) goto L31;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.util.Map<java.lang.Integer, java.nio.ByteBuffer> getAll(java.io.File r3) {
        /*
            r0 = 0
            java.io.RandomAccessFile r1 = new java.io.RandomAccessFile     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L3b
            java.lang.String r2 = "r"
            r1.<init>(r3, r2)     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L3b
            java.nio.channels.FileChannel r3 = r1.getChannel()     // Catch: java.lang.Throwable -> L26 java.io.IOException -> L29
            com.meituan.android.walle.Pair r2 = com.meituan.android.walle.ApkUtil.findApkSigningBlock(r3)     // Catch: java.lang.Throwable -> L24 java.io.IOException -> L3d
            java.lang.Object r2 = r2.getFirst()     // Catch: java.lang.Throwable -> L24 java.io.IOException -> L3d
            java.nio.ByteBuffer r2 = (java.nio.ByteBuffer) r2     // Catch: java.lang.Throwable -> L24 java.io.IOException -> L3d
            java.util.Map r0 = com.meituan.android.walle.ApkUtil.findIdValues(r2)     // Catch: java.lang.Throwable -> L24 java.io.IOException -> L3d
            if (r3 == 0) goto L20
            r3.close()     // Catch: java.io.IOException -> L20 java.lang.Throwable -> L47
        L20:
            r1.close()     // Catch: java.lang.Throwable -> L47
            goto L47
        L24:
            r2 = move-exception
            goto L2e
        L26:
            r2 = move-exception
            r3 = r0
            goto L2e
        L29:
            r3 = r0
            goto L3d
        L2b:
            r2 = move-exception
            r3 = r0
            r1 = r3
        L2e:
            if (r3 == 0) goto L35
            r3.close()     // Catch: java.io.IOException -> L34
            goto L35
        L34:
        L35:
            if (r1 == 0) goto L3a
            r1.close()     // Catch: java.io.IOException -> L3a
        L3a:
            throw r2     // Catch: java.lang.Throwable -> L47
        L3b:
            r3 = r0
            r1 = r3
        L3d:
            if (r3 == 0) goto L44
            r3.close()     // Catch: java.io.IOException -> L43
            goto L44
        L43:
        L44:
            if (r1 == 0) goto L47
            goto L20
        L47:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meituan.android.walle.PayloadReader.getAll(java.io.File):java.util.Map");
    }

    private static byte[] getBytes(ByteBuffer byteBuffer) {
        byte[] array = byteBuffer.array();
        int arrayOffset = byteBuffer.arrayOffset();
        return Arrays.copyOfRange(array, byteBuffer.position() + arrayOffset, arrayOffset + byteBuffer.limit());
    }

    public static String getString(File file, int i2) {
        byte[] bArr = get(file, i2);
        if (bArr == null) {
            return null;
        }
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
