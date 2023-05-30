package com.jingdong.app.mall.nfc;

import android.content.IntentFilter;
import android.nfc.tech.IsoDep;
import java.util.Arrays;
import jd.wjlogin_sdk.util.ReplyCode;

/* loaded from: classes4.dex */
public class c {
    private static final String[] a = {"02DDF1", "02ADF1"};
    private static final byte[] b = {-112, 0};

    /* renamed from: c  reason: collision with root package name */
    private static final byte[] f11417c = {ReplyCode.reply0x6a, -127};
    private static final byte[] d = {ReplyCode.reply0x6a, ReplyCode.reply0x82};

    /* renamed from: e  reason: collision with root package name */
    private static final String[] f11418e = {"00A4000002DDF1", "C4FE000000"};

    /* renamed from: f  reason: collision with root package name */
    private static IsoDep f11419f;

    static {
        try {
            new IntentFilter("android.nfc.action.TECH_DISCOVERED", "*/*");
        } catch (Exception unused) {
        }
    }

    public static byte[] a(String str) {
        return c("00A40000" + str);
    }

    public static String b(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] cArr2 = new char[bArr.length * 2];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            int i3 = bArr[i2] & 255;
            int i4 = i2 * 2;
            cArr2[i4] = cArr[i3 >>> 4];
            cArr2[i4 + 1] = cArr[i3 & 15];
        }
        return new String(cArr2);
    }

    public static byte[] c(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        for (int i2 = 0; i2 < length; i2 += 2) {
            bArr[i2 / 2] = (byte) ((Character.digit(str.charAt(i2), 16) << 4) + Character.digit(str.charAt(i2 + 1), 16));
        }
        return bArr;
    }

    private static byte[][] d(byte[] bArr) {
        int length = bArr.length;
        int i2 = length - 2;
        return new byte[][]{new byte[]{bArr[i2], bArr[length - 1]}, Arrays.copyOf(bArr, i2)};
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x004b, code lost:
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004c, code lost:
        r1 = com.jingdong.app.mall.nfc.c.f11418e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004f, code lost:
        if (r4 >= r1.length) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0051, code lost:
        r1 = b(com.jingdong.app.mall.nfc.c.f11419f.transceive(c(r1[r4])));
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0062, code lost:
        if (r4 != 1) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x006a, code lost:
        if (r1.endsWith("9000") == false) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x006c, code lost:
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x006d, code lost:
        r4 = r4 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean e(android.nfc.Tag r4) {
        /*
            r0 = 0
            if (r4 != 0) goto L4
            return r0
        L4:
            android.nfc.tech.IsoDep r4 = android.nfc.tech.IsoDep.get(r4)
            com.jingdong.app.mall.nfc.c.f11419f = r4
            if (r4 == 0) goto L74
            boolean r4 = r4.isConnected()     // Catch: java.lang.Exception -> L70
            if (r4 != 0) goto L17
            android.nfc.tech.IsoDep r4 = com.jingdong.app.mall.nfc.c.f11419f     // Catch: java.lang.Exception -> L70
            r4.connect()     // Catch: java.lang.Exception -> L70
        L17:
            r4 = 0
        L18:
            java.lang.String[] r1 = com.jingdong.app.mall.nfc.c.a     // Catch: java.lang.Exception -> L70
            int r2 = r1.length     // Catch: java.lang.Exception -> L70
            if (r4 >= r2) goto L4b
            r1 = r1[r4]     // Catch: java.lang.Exception -> L70
            byte[] r1 = a(r1)     // Catch: java.lang.Exception -> L70
            android.nfc.tech.IsoDep r2 = com.jingdong.app.mall.nfc.c.f11419f     // Catch: java.lang.Exception -> L70
            byte[] r1 = r2.transceive(r1)     // Catch: java.lang.Exception -> L70
            byte[][] r1 = d(r1)     // Catch: java.lang.Exception -> L70
            r1 = r1[r0]     // Catch: java.lang.Exception -> L70
            byte[] r2 = com.jingdong.app.mall.nfc.c.b     // Catch: java.lang.Exception -> L70
            boolean r2 = java.util.Arrays.equals(r2, r1)     // Catch: java.lang.Exception -> L70
            if (r2 != 0) goto L48
            byte[] r2 = com.jingdong.app.mall.nfc.c.f11417c     // Catch: java.lang.Exception -> L70
            boolean r2 = java.util.Arrays.equals(r2, r1)     // Catch: java.lang.Exception -> L70
            if (r2 != 0) goto L48
            byte[] r2 = com.jingdong.app.mall.nfc.c.d     // Catch: java.lang.Exception -> L70
            boolean r1 = java.util.Arrays.equals(r2, r1)     // Catch: java.lang.Exception -> L70
            if (r1 != 0) goto L48
            return r0
        L48:
            int r4 = r4 + 1
            goto L18
        L4b:
            r4 = 0
        L4c:
            java.lang.String[] r1 = com.jingdong.app.mall.nfc.c.f11418e     // Catch: java.lang.Exception -> L70
            int r2 = r1.length     // Catch: java.lang.Exception -> L70
            if (r4 >= r2) goto L74
            r1 = r1[r4]     // Catch: java.lang.Exception -> L70
            byte[] r1 = c(r1)     // Catch: java.lang.Exception -> L70
            android.nfc.tech.IsoDep r2 = com.jingdong.app.mall.nfc.c.f11419f     // Catch: java.lang.Exception -> L70
            byte[] r1 = r2.transceive(r1)     // Catch: java.lang.Exception -> L70
            java.lang.String r1 = b(r1)     // Catch: java.lang.Exception -> L70
            r2 = 1
            if (r4 != r2) goto L6d
            java.lang.String r3 = "9000"
            boolean r1 = r1.endsWith(r3)     // Catch: java.lang.Exception -> L70
            if (r1 == 0) goto L6d
            return r2
        L6d:
            int r4 = r4 + 1
            goto L4c
        L70:
            r4 = move-exception
            r4.printStackTrace()
        L74:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.nfc.c.e(android.nfc.Tag):boolean");
    }
}
