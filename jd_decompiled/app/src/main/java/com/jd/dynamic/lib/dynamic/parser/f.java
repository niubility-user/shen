package com.jd.dynamic.lib.dynamic.parser;

import androidx.annotation.NonNull;

/* loaded from: classes13.dex */
public class f {
    private byte[] a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    private int f2229c;

    public void a() {
        if (this.a != null) {
            this.a = null;
        }
    }

    public void b(byte[] bArr) {
        this.a = bArr;
        if (bArr != null) {
            this.f2229c = bArr.length;
        } else {
            this.f2229c = 0;
        }
        this.b = 0;
    }

    public boolean c(int i2) {
        int i3 = this.f2229c;
        if (i2 > i3) {
            this.b = i3;
            return false;
        } else if (i2 < 0) {
            this.b = 0;
            return false;
        } else {
            this.b = i2;
            return true;
        }
    }

    public String d(int i2) {
        String str;
        if (this.b + i2 <= this.f2229c) {
            str = new String(this.a, this.b, i2);
            this.b += i2;
        } else {
            str = null;
        }
        if (str != null) {
            return str;
        }
        throw com.jd.dynamic.lib.dynamic.parser.e.a.c();
    }

    public short e() {
        int i2;
        byte[] bArr = this.a;
        if (bArr == null || (i2 = this.b) >= this.f2229c - 1) {
            throw com.jd.dynamic.lib.dynamic.parser.e.a.b();
        }
        int i3 = i2 + 1;
        this.b = i3;
        this.b = i3 + 1;
        return (short) ((bArr[i3] & 255) | ((bArr[i2] & 255) << 8));
    }

    public int f() {
        int i2;
        byte[] bArr = this.a;
        if (bArr == null || (i2 = this.b) >= this.f2229c - 3) {
            throw com.jd.dynamic.lib.dynamic.parser.e.a.a();
        }
        int i3 = i2 + 1;
        this.b = i3;
        int i4 = i3 + 1;
        this.b = i4;
        int i5 = ((bArr[i2] & 255) << 24) | ((bArr[i3] & 255) << 16);
        int i6 = i4 + 1;
        this.b = i6;
        int i7 = i5 | ((bArr[i4] & 255) << 8);
        this.b = i6 + 1;
        return (bArr[i6] & 255) | i7;
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("count : ");
        sb.append(this.f2229c);
        sb.append(" curPosition:");
        sb.append(this.b);
        sb.append(" source: ");
        byte[] bArr = this.a;
        sb.append(bArr == null ? -1 : bArr.length);
        return sb.toString();
    }
}
