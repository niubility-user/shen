package com.jingdong.common.jdreactFramework.utils;

/* loaded from: classes5.dex */
public class Base64Utils {
    private static final int CACHE_SIZE = 1024;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:40:0x005d A[Catch: Exception -> 0x0059, TRY_LEAVE, TryCatch #5 {Exception -> 0x0059, blocks: (B:36:0x0055, B:40:0x005d), top: B:50:0x0055 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0055 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.io.ByteArrayInputStream, java.io.InputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void byteArrayToFile(byte[] r3, java.lang.String r4) throws java.lang.Exception {
        /*
            r0 = 0
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4d
            r1.<init>(r3)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4d
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L46
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L46
            java.io.File r4 = r3.getParentFile()     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L46
            boolean r4 = r4.exists()     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L46
            if (r4 != 0) goto L1c
            java.io.File r4 = r3.getParentFile()     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L46
            r4.mkdirs()     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L46
        L1c:
            r3.createNewFile()     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L46
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L46
            r4.<init>(r3)     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L46
            r3 = 1024(0x400, float:1.435E-42)
            byte[] r3 = new byte[r3]     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
        L28:
            int r0 = r1.read(r3)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
            r2 = -1
            if (r0 == r2) goto L37
            r2 = 0
            r4.write(r3, r2, r0)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
            r4.flush()     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
            goto L28
        L37:
            r4.close()     // Catch: java.lang.Exception -> L3e
            r1.close()     // Catch: java.lang.Exception -> L3e
            return
        L3e:
            r3 = move-exception
            throw r3
        L40:
            r3 = move-exception
            goto L52
        L42:
            r3 = move-exception
            goto L48
        L44:
            r3 = move-exception
            goto L53
        L46:
            r3 = move-exception
            r4 = r0
        L48:
            r0 = r1
            goto L4f
        L4a:
            r3 = move-exception
            r1 = r0
            goto L53
        L4d:
            r3 = move-exception
            r4 = r0
        L4f:
            throw r3     // Catch: java.lang.Throwable -> L50
        L50:
            r3 = move-exception
            r1 = r0
        L52:
            r0 = r4
        L53:
            if (r0 == 0) goto L5b
            r0.close()     // Catch: java.lang.Exception -> L59
            goto L5b
        L59:
            r3 = move-exception
            goto L61
        L5b:
            if (r1 == 0) goto L62
            r1.close()     // Catch: java.lang.Exception -> L59
            goto L62
        L61:
            throw r3
        L62:
            goto L64
        L63:
            throw r3
        L64:
            goto L63
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.jdreactFramework.utils.Base64Utils.byteArrayToFile(byte[], java.lang.String):void");
    }

    public static byte[] decode(String str) throws Exception {
        return Base64.decode(str.getBytes());
    }

    public static void decodeToFile(String str, String str2) throws Exception {
        byteArrayToFile(decode(str2), str);
    }

    public static String encode(byte[] bArr) throws Exception {
        return new String(Base64.encode(bArr));
    }

    public static String encodeFile(String str) throws Exception {
        return encode(fileToByte(str));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0063 A[Catch: Exception -> 0x005f, TRY_LEAVE, TryCatch #0 {Exception -> 0x005f, blocks: (B:39:0x005b, B:43:0x0063), top: B:49:0x005b }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x005b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v0, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] fileToByte(java.lang.String r5) throws java.lang.Exception {
        /*
            r0 = 0
            byte[] r1 = new byte[r0]
            java.io.File r2 = new java.io.File
            r2.<init>(r5)
            r5 = 0
            boolean r3 = r2.exists()     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L53
            if (r3 == 0) goto L3c
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L53
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L53
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L39
            r3 = 2048(0x800, float:2.87E-42)
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L39
            r5 = 1024(0x400, float:1.435E-42)
            byte[] r5 = new byte[r5]     // Catch: java.lang.Exception -> L34 java.lang.Throwable -> L58
        L1f:
            int r3 = r1.read(r5)     // Catch: java.lang.Exception -> L34 java.lang.Throwable -> L58
            r4 = -1
            if (r3 == r4) goto L2d
            r2.write(r5, r0, r3)     // Catch: java.lang.Exception -> L34 java.lang.Throwable -> L58
            r2.flush()     // Catch: java.lang.Exception -> L34 java.lang.Throwable -> L58
            goto L1f
        L2d:
            byte[] r5 = r2.toByteArray()     // Catch: java.lang.Exception -> L34 java.lang.Throwable -> L58
            r0 = r5
            r5 = r2
            goto L3e
        L34:
            r5 = move-exception
            goto L57
        L36:
            r0 = move-exception
            r2 = r5
            goto L51
        L39:
            r0 = move-exception
            r2 = r5
            goto L56
        L3c:
            r0 = r1
            r1 = r5
        L3e:
            if (r5 == 0) goto L46
            r5.close()     // Catch: java.lang.Exception -> L44
            goto L46
        L44:
            r5 = move-exception
            goto L4c
        L46:
            if (r1 == 0) goto L4d
            r1.close()     // Catch: java.lang.Exception -> L44
            goto L4d
        L4c:
            throw r5
        L4d:
            return r0
        L4e:
            r0 = move-exception
            r1 = r5
            r2 = r1
        L51:
            r5 = r0
            goto L59
        L53:
            r0 = move-exception
            r1 = r5
            r2 = r1
        L56:
            r5 = r0
        L57:
            throw r5     // Catch: java.lang.Throwable -> L58
        L58:
            r5 = move-exception
        L59:
            if (r2 == 0) goto L61
            r2.close()     // Catch: java.lang.Exception -> L5f
            goto L61
        L5f:
            r5 = move-exception
            goto L67
        L61:
            if (r1 == 0) goto L68
            r1.close()     // Catch: java.lang.Exception -> L5f
            goto L68
        L67:
            throw r5
        L68:
            goto L6a
        L69:
            throw r5
        L6a:
            goto L69
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.jdreactFramework.utils.Base64Utils.fileToByte(java.lang.String):byte[]");
    }
}
