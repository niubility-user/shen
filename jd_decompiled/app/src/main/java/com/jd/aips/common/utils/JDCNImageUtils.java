package com.jd.aips.common.utils;

import android.graphics.Bitmap;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes12.dex */
public class JDCNImageUtils {
    public static byte[] bitmap2Bytes(Bitmap bitmap, Bitmap.CompressFormat compressFormat, int i2) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, i2, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static void bitmap2File(Bitmap bitmap, String str, int i2) {
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str));
            bitmap.compress(Bitmap.CompressFormat.JPEG, i2, bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(13:1|2|3|(2:5|(8:7|8|(1:10)(1:33)|11|12|13|(1:15)|18))|(1:35)|(2:(1:39)(1:41)|40)(1:42)|(0)(0)|11|12|13|(0)|18|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x001a, code lost:
        if (r3 > r4) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x005d, code lost:
        if (r9.isRecycled() == false) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0060, code lost:
        r10 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0061, code lost:
        r0 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0064, code lost:
        if (r0 != null) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x006c, code lost:
        r0.recycle();
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x006f, code lost:
        throw r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0071, code lost:
        if (r9 != null) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0077, code lost:
        if (r9.isRecycled() == false) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0079, code lost:
        r9.recycle();
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] jpegRotaing(byte[] r9, int r10, int r11, int r12, int r13, int r14, int r15) {
        /*
            r0 = 0
            android.graphics.BitmapFactory$Options r1 = new android.graphics.BitmapFactory$Options     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L70
            r1.<init>()     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L70
            android.graphics.Bitmap$Config r2 = android.graphics.Bitmap.Config.RGB_565     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L70
            r1.inPreferredConfig = r2     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L70
            r2 = 1
            if (r10 <= r11) goto L14
            float r3 = (float) r10     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L70
            float r4 = (float) r13     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L70
            int r5 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r5 <= 0) goto L14
            goto L1c
        L14:
            if (r10 >= r11) goto L1f
            float r3 = (float) r11     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L70
            float r4 = (float) r14     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L70
            int r5 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r5 <= 0) goto L1f
        L1c:
            float r3 = r3 / r4
            int r10 = (int) r3     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L70
            goto L2c
        L1f:
            if (r10 != r11) goto L2b
            if (r13 <= r14) goto L26
            float r10 = (float) r10     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L70
            float r11 = (float) r13     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L70
            goto L28
        L26:
            float r10 = (float) r11     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L70
            float r11 = (float) r14     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L70
        L28:
            float r10 = r10 / r11
            int r10 = (int) r10     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L70
            goto L2c
        L2b:
            r10 = 1
        L2c:
            if (r10 > 0) goto L2f
            goto L30
        L2f:
            r2 = r10
        L30:
            r1.inSampleSize = r2     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L70
            r10 = 0
            int r11 = r9.length     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L70
            android.graphics.Bitmap r9 = android.graphics.BitmapFactory.decodeByteArray(r9, r10, r11, r1)     // Catch: java.lang.Throwable -> L63 java.lang.Exception -> L70
            android.graphics.Matrix r7 = new android.graphics.Matrix     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L71
            r7.<init>()     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L71
            float r10 = (float) r15     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L71
            r7.postRotate(r10)     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L71
            r3 = 0
            r4 = 0
            int r5 = r9.getWidth()     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L71
            int r6 = r9.getHeight()     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L71
            r8 = 1
            r2 = r9
            android.graphics.Bitmap r9 = android.graphics.Bitmap.createBitmap(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L71
            android.graphics.Bitmap$CompressFormat r10 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L71
            byte[] r0 = bitmap2Bytes(r9, r10, r12)     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L71
            if (r9 == 0) goto L7c
            boolean r10 = r9.isRecycled()
            if (r10 != 0) goto L7c
            goto L79
        L60:
            r10 = move-exception
            r0 = r9
            goto L64
        L63:
            r10 = move-exception
        L64:
            if (r0 == 0) goto L6f
            boolean r9 = r0.isRecycled()
            if (r9 != 0) goto L6f
            r0.recycle()
        L6f:
            throw r10
        L70:
            r9 = r0
        L71:
            if (r9 == 0) goto L7c
            boolean r10 = r9.isRecycled()
            if (r10 != 0) goto L7c
        L79:
            r9.recycle()
        L7c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.aips.common.utils.JDCNImageUtils.jpegRotaing(byte[], int, int, int, int, int, int):byte[]");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:(3:4|5|(2:6|7))|(2:9|(10:11|12|(1:14)(1:50)|15|17|18|(1:22)|23|24|25))|(1:52)|(2:(1:56)(1:58)|57)(1:59)|(0)(0)|15|17|18|(2:20|22)|23|24|25) */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0045, code lost:
        if (r6 > r11) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0099, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x009a, code lost:
        r10 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x009c, code lost:
        r10 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00a1, code lost:
        r1 = r0;
        r0 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00ac, code lost:
        if (r10 != null) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00b4, code lost:
        r10.recycle();
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00b7, code lost:
        if (r0 != null) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00b9, code lost:
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00bd, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00be, code lost:
        r0.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00c2, code lost:
        throw r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00c5, code lost:
        if (r10 != null) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00cd, code lost:
        r10.recycle();
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00d0, code lost:
        if (r1 != null) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00d2, code lost:
        r1.close();
     */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:60:0x00d6 -> B:64:0x00d9). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] yuv2JpegRotaing(byte[] r19, int r20, int r21, int r22, int r23, int r24, int r25) {
        /*
            Method dump skipped, instructions count: 218
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.aips.common.utils.JDCNImageUtils.yuv2JpegRotaing(byte[], int, int, int, int, int, int):byte[]");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(14:1|(3:2|3|(4:4|5|7|8))|(2:10|(10:12|13|(1:15)(1:51)|16|18|19|(1:23)|24|25|26))|(1:53)|(2:(1:57)(1:59)|58)(1:60)|(0)(0)|16|18|19|(2:21|23)|24|25|26|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0045, code lost:
        if (r6 > r11) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00a0, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00a1, code lost:
        r10 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00a3, code lost:
        r10 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00a8, code lost:
        r1 = r0;
        r0 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00b3, code lost:
        if (r10 != null) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00bb, code lost:
        r10.recycle();
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00be, code lost:
        if (r0 != null) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00c0, code lost:
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00c4, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00c5, code lost:
        r0.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00c9, code lost:
        throw r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00cc, code lost:
        if (r10 != null) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00d4, code lost:
        r10.recycle();
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00d7, code lost:
        if (r1 != null) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00d9, code lost:
        r1.close();
     */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:60:0x00dd -> B:68:0x00e0). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] yuv2JpegRotaingWithoutMirror(byte[] r19, int r20, int r21, int r22, int r23, int r24, int r25) {
        /*
            Method dump skipped, instructions count: 225
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.aips.common.utils.JDCNImageUtils.yuv2JpegRotaingWithoutMirror(byte[], int, int, int, int, int, int):byte[]");
    }
}
