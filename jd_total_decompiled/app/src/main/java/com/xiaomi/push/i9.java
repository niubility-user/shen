package com.xiaomi.push;

/* loaded from: classes11.dex */
public final class i9 extends j9 {
    private byte[] a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    private int f18762c;

    @Override // com.xiaomi.push.j9
    public int a() {
        return this.b;
    }

    @Override // com.xiaomi.push.j9
    public int b(byte[] bArr, int i2, int i3) {
        int f2 = f();
        if (i3 > f2) {
            i3 = f2;
        }
        if (i3 > 0) {
            System.arraycopy(this.a, this.b, bArr, i2, i3);
            c(i3);
        }
        return i3;
    }

    @Override // com.xiaomi.push.j9
    public void c(int i2) {
        this.b += i2;
    }

    @Override // com.xiaomi.push.j9
    public void d(byte[] bArr, int i2, int i3) {
        throw new UnsupportedOperationException("No writing allowed!");
    }

    @Override // com.xiaomi.push.j9
    public byte[] e() {
        return this.a;
    }

    @Override // com.xiaomi.push.j9
    public int f() {
        return this.f18762c - this.b;
    }

    public void h(byte[] bArr) {
        i(bArr, 0, bArr.length);
    }

    public void i(byte[] bArr, int i2, int i3) {
        this.a = bArr;
        this.b = i2;
        this.f18762c = i2 + i3;
    }
}
