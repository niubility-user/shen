package com.jdjr.risk.util.b;

import java.io.ByteArrayOutputStream;
import jd.wjlogin_sdk.util.ReplyCode;

/* loaded from: classes18.dex */
public class a {

    /* renamed from: c  reason: collision with root package name */
    private static final a f7496c = new a(C0229a.a);
    private final byte[] a;
    private byte[] b;

    /* renamed from: com.jdjr.risk.util.b.a$a  reason: collision with other inner class name */
    /* loaded from: classes18.dex */
    private static class C0229a {
        private static final byte[] a = {48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, ReplyCode.reply0x4f, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, ReplyCode.reply0x64, 101, 102, ReplyCode.reply0x67, ReplyCode.reply0x68, 105, ReplyCode.reply0x6a, 107, 108, 109, 110, 111, 112, 113, 114, ReplyCode.reply0x73, 116, 117, 118, ReplyCode.reply0x77, ReplyCode.reply0x78, 121, ReplyCode.reply0x7a};
        private static final byte[] b = {48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 97, 98, 99, ReplyCode.reply0x64, 101, 102, ReplyCode.reply0x67, ReplyCode.reply0x68, 105, ReplyCode.reply0x6a, 107, 108, 109, 110, 111, 112, 113, 114, ReplyCode.reply0x73, 116, 117, 118, ReplyCode.reply0x77, ReplyCode.reply0x78, 121, ReplyCode.reply0x7a, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, ReplyCode.reply0x4f, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90};
    }

    private a(byte[] bArr) {
        this.a = bArr;
        a();
    }

    private int a(int i2, int i3, int i4) {
        double d = i2;
        Double.isNaN(d);
        return (int) Math.ceil((Math.log(i3) / Math.log(i4)) * d);
    }

    private void a() {
        this.b = new byte[256];
        int i2 = 0;
        while (true) {
            byte[] bArr = this.a;
            if (i2 >= bArr.length) {
                return;
            }
            this.b[bArr[i2]] = (byte) (i2 & 255);
            i2++;
        }
    }

    public static byte[] a(byte[] bArr) {
        return f7496c.b(bArr);
    }

    private byte[] a(byte[] bArr, int i2, int i3) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(a(bArr.length, i2, i3));
        byte[] bArr2 = bArr;
        while (true) {
            if (bArr2.length <= 0) {
                break;
            }
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream(bArr2.length);
            int i4 = 0;
            for (byte b : bArr2) {
                int i5 = (b & 255) + (i4 * i2);
                i4 = i5 % i3;
                int i6 = (i5 - i4) / i3;
                if (byteArrayOutputStream2.size() > 0 || i6 > 0) {
                    byteArrayOutputStream2.write(i6);
                }
            }
            byteArrayOutputStream.write(i4);
            bArr2 = byteArrayOutputStream2.toByteArray();
        }
        for (int i7 = 0; i7 < bArr.length - 1 && bArr[i7] == 0; i7++) {
            byteArrayOutputStream.write(0);
        }
        return d(byteArrayOutputStream.toByteArray());
    }

    private byte[] a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr3[i2] = bArr2[bArr[i2]];
        }
        return bArr3;
    }

    private byte[] d(byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            bArr2[(length - i2) - 1] = bArr[i2];
        }
        return bArr2;
    }

    public byte[] b(byte[] bArr) {
        if (c(bArr)) {
            return a(a(bArr, this.b), 62, 256);
        }
        throw new IllegalArgumentException("Input is not encoded correctly");
    }

    public boolean c(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        for (byte b : bArr) {
            if ((48 > b || 57 < b) && ((97 > b || 122 < b) && (65 > b || 90 < b))) {
                return false;
            }
        }
        return true;
    }
}
