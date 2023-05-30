package com.xiaomi.push.service;

import com.huawei.hms.framework.common.ContainerUtils;
import com.jingdong.common.utils.LangUtils;

/* loaded from: classes11.dex */
public class r0 {
    private int d = -666;
    private byte[] a = new byte[256];

    /* renamed from: c  reason: collision with root package name */
    private int f19175c = 0;
    private int b = 0;

    public static int b(byte b) {
        return b >= 0 ? b : b + 256;
    }

    private void c() {
        this.f19175c = 0;
        this.b = 0;
    }

    private void d(int i2, byte[] bArr, boolean z) {
        int length = bArr.length;
        for (int i3 = 0; i3 < 256; i3++) {
            this.a[i3] = (byte) i3;
        }
        this.f19175c = 0;
        this.b = 0;
        while (true) {
            int i4 = this.b;
            if (i4 >= i2) {
                break;
            }
            int b = ((this.f19175c + b(this.a[i4])) + b(bArr[this.b % length])) % 256;
            this.f19175c = b;
            f(this.a, this.b, b);
            this.b++;
        }
        if (i2 != 256) {
            this.d = ((this.f19175c + b(this.a[i2])) + b(bArr[i2 % length])) % 256;
        }
        if (z) {
            StringBuilder sb = new StringBuilder();
            sb.append("S_");
            int i5 = i2 - 1;
            sb.append(i5);
            sb.append(":");
            for (int i6 = 0; i6 <= i2; i6++) {
                sb.append(LangUtils.SINGLE_SPACE);
                sb.append(b(this.a[i6]));
            }
            sb.append("   j_");
            sb.append(i5);
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(this.f19175c);
            sb.append("   j_");
            sb.append(i2);
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(this.d);
            sb.append("   S_");
            sb.append(i5);
            sb.append("[j_");
            sb.append(i5);
            sb.append("]=");
            sb.append(b(this.a[this.f19175c]));
            sb.append("   S_");
            sb.append(i5);
            sb.append("[j_");
            sb.append(i2);
            sb.append("]=");
            sb.append(b(this.a[this.d]));
            if (this.a[1] != 0) {
                sb.append("   S[1]!=0");
            }
            g.j.a.a.a.c.o(sb.toString());
        }
    }

    private void e(byte[] bArr) {
        d(256, bArr, false);
    }

    private static void f(byte[] bArr, int i2, int i3) {
        byte b = bArr[i2];
        bArr[i2] = bArr[i3];
        bArr[i3] = b;
    }

    public static byte[] g(String str, String str2) {
        byte[] b = com.xiaomi.push.m0.b(str);
        byte[] bytes = str2.getBytes();
        byte[] bArr = new byte[b.length + 1 + bytes.length];
        for (int i2 = 0; i2 < b.length; i2++) {
            bArr[i2] = b[i2];
        }
        bArr[b.length] = 95;
        for (int i3 = 0; i3 < bytes.length; i3++) {
            bArr[b.length + 1 + i3] = bytes[i3];
        }
        return bArr;
    }

    public static byte[] h(byte[] bArr, String str) {
        return i(bArr, com.xiaomi.push.m0.b(str));
    }

    public static byte[] i(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr2.length];
        r0 r0Var = new r0();
        r0Var.e(bArr);
        r0Var.c();
        for (int i2 = 0; i2 < bArr2.length; i2++) {
            bArr3[i2] = (byte) (bArr2[i2] ^ r0Var.a());
        }
        return bArr3;
    }

    public static byte[] j(byte[] bArr, byte[] bArr2, boolean z, int i2, int i3) {
        byte[] bArr3;
        int i4;
        if (i2 < 0 || i2 > bArr2.length || i2 + i3 > bArr2.length) {
            throw new IllegalArgumentException("start = " + i2 + " len = " + i3);
        }
        if (z) {
            bArr3 = bArr2;
            i4 = i2;
        } else {
            bArr3 = new byte[i3];
            i4 = 0;
        }
        r0 r0Var = new r0();
        r0Var.e(bArr);
        r0Var.c();
        for (int i5 = 0; i5 < i3; i5++) {
            bArr3[i4 + i5] = (byte) (bArr2[i2 + i5] ^ r0Var.a());
        }
        return bArr3;
    }

    byte a() {
        int i2 = (this.b + 1) % 256;
        this.b = i2;
        int b = (this.f19175c + b(this.a[i2])) % 256;
        this.f19175c = b;
        f(this.a, this.b, b);
        byte[] bArr = this.a;
        return bArr[(b(bArr[this.b]) + b(this.a[this.f19175c])) % 256];
    }
}
