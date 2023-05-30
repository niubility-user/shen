package com.jingdong.common.unification.uniutil;

import java.util.zip.CRC32;

/* loaded from: classes6.dex */
public class CRCUtils {
    private static final String TAG = "CRCUtils";

    public static long checksumBytes(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return -1L;
        }
        CRC32 crc32 = new CRC32();
        crc32.update(bArr);
        return crc32.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0024: MOVE (r1 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:16:0x0024 */
    /* JADX WARN: Removed duplicated region for block: B:27:0x003b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0045 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static long checksumInputStream(java.lang.String r5) {
        /*
            java.lang.String r0 = "CRCUtils"
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L2d
            r2.<init>(r5)     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L2d
            java.util.zip.CRC32 r5 = new java.util.zip.CRC32     // Catch: java.lang.Throwable -> L23 java.io.IOException -> L26
            r5.<init>()     // Catch: java.lang.Throwable -> L23 java.io.IOException -> L26
        Ld:
            int r1 = r2.read()     // Catch: java.io.IOException -> L21 java.lang.Throwable -> L23
            r3 = -1
            if (r1 == r3) goto L18
            r5.update(r1)     // Catch: java.io.IOException -> L21 java.lang.Throwable -> L23
            goto Ld
        L18:
            r2.close()     // Catch: java.io.IOException -> L1c
            goto L39
        L1c:
            r1 = move-exception
            com.jingdong.sdk.oklog.OKLog.e(r0, r1)
            goto L39
        L21:
            r1 = move-exception
            goto L31
        L23:
            r5 = move-exception
            r1 = r2
            goto L43
        L26:
            r5 = move-exception
            r4 = r1
            r1 = r5
            r5 = r4
            goto L31
        L2b:
            r5 = move-exception
            goto L43
        L2d:
            r5 = move-exception
            r2 = r1
            r1 = r5
            r5 = r2
        L31:
            com.jingdong.sdk.oklog.OKLog.e(r0, r1)     // Catch: java.lang.Throwable -> L23
            if (r2 == 0) goto L39
            r2.close()     // Catch: java.io.IOException -> L1c
        L39:
            if (r5 != 0) goto L3e
            r0 = -1
            return r0
        L3e:
            long r0 = r5.getValue()
            return r0
        L43:
            if (r1 == 0) goto L4d
            r1.close()     // Catch: java.io.IOException -> L49
            goto L4d
        L49:
            r1 = move-exception
            com.jingdong.sdk.oklog.OKLog.e(r0, r1)
        L4d:
            goto L4f
        L4e:
            throw r5
        L4f:
            goto L4e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.unification.uniutil.CRCUtils.checksumInputStream(java.lang.String):long");
    }
}
