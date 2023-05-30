package com.tencent.mapsdk.internal;

import java.io.UnsupportedEncodingException;

/* loaded from: classes9.dex */
public class ec implements gc {

    /* renamed from: g  reason: collision with root package name */
    private int f16469g;

    /* renamed from: h  reason: collision with root package name */
    private byte[] f16470h;

    public ec(byte[] bArr) {
        a(bArr);
    }

    private long b(int i2) {
        long j2 = 0;
        for (int i3 = i2 - 1; i3 >= 0; i3--) {
            j2 |= (b() & 255) << (i3 * 8);
        }
        return j2;
    }

    public void a(int i2) {
        this.f16469g -= i2;
    }

    public void a(byte[] bArr) {
        this.f16470h = bArr;
        this.f16469g = 0;
    }

    public boolean a() {
        return b() != 0;
    }

    public byte b() {
        int i2;
        byte[] bArr = this.f16470h;
        if (bArr == null || (i2 = this.f16469g) >= bArr.length) {
            return (byte) 0;
        }
        this.f16469g = i2 + 1;
        return bArr[i2];
    }

    public char c() {
        long j2 = 0;
        for (int i2 = 0; i2 < 2; i2++) {
            j2 |= (b() & 255) << (i2 * 8);
        }
        return (char) j2;
    }

    public byte[] c(int i2) {
        byte[] bArr = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3] = b();
        }
        return bArr;
    }

    public int d() {
        return (int) b(4);
    }

    public String d(int i2) {
        int i3 = i2 >> 1;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i4 = 0; i4 < i3; i4++) {
            stringBuffer.append(c());
        }
        return stringBuffer.toString();
    }

    public void e(int i2) {
        this.f16469g += i2;
    }

    public byte[] e() {
        byte[] bArr = this.f16470h;
        int length = bArr.length;
        int i2 = this.f16469g;
        int i3 = length - i2;
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i2, bArr2, 0, i3);
        return bArr2;
    }

    public long f() {
        return b(8);
    }

    public int g() {
        return this.f16469g;
    }

    public int h() {
        return (int) b(2);
    }

    public String i() {
        int h2 = h();
        if (h2 == 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < h2; i2++) {
            stringBuffer.append(c());
        }
        return stringBuffer.toString();
    }

    public String j() {
        int h2 = h();
        if (h2 == 0) {
            return "";
        }
        try {
            return new String(c(h2), "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public boolean k() {
        return this.f16469g == this.f16470h.length - 1;
    }
}
