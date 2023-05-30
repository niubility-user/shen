package com.jingdong.app.mall.nfc;

import android.content.IntentFilter;
import android.nfc.Tag;
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
    */
    public static boolean e(Tag tag) {
        if (tag == null) {
            return false;
        }
        IsoDep isoDep = IsoDep.get(tag);
        f11419f = isoDep;
        if (isoDep != null) {
            try {
                if (!isoDep.isConnected()) {
                    f11419f.connect();
                }
                int i2 = 0;
                while (true) {
                    String[] strArr = a;
                    if (i2 >= strArr.length) {
                        break;
                    }
                    byte[] bArr = d(f11419f.transceive(a(strArr[i2])))[0];
                    if (!Arrays.equals(b, bArr) && !Arrays.equals(f11417c, bArr) && !Arrays.equals(d, bArr)) {
                        return false;
                    }
                    i2++;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }
}
