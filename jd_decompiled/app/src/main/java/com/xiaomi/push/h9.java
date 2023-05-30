package com.xiaomi.push;

/* loaded from: classes11.dex */
public class h9 extends j9 {
    private q8 a;
    private int b;

    public h9(int i2) {
        this.a = new q8(i2);
    }

    @Override // com.xiaomi.push.j9
    public int b(byte[] bArr, int i2, int i3) {
        byte[] g2 = this.a.g();
        if (i3 > this.a.f() - this.b) {
            i3 = this.a.f() - this.b;
        }
        if (i3 > 0) {
            System.arraycopy(g2, this.b, bArr, i2, i3);
            this.b += i3;
        }
        return i3;
    }

    @Override // com.xiaomi.push.j9
    public void d(byte[] bArr, int i2, int i3) {
        this.a.write(bArr, i2, i3);
    }

    public int h() {
        return this.a.size();
    }
}
