package com.xiaomi.push;

import java.io.IOException;

/* loaded from: classes11.dex */
public abstract class o2 {
    public abstract int a();

    public abstract o2 b(c0 c0Var);

    public o2 c(byte[] bArr) {
        d(bArr, 0, bArr.length);
        return this;
    }

    public o2 d(byte[] bArr, int i2, int i3) {
        try {
            c0 g2 = c0.g(bArr, i2, i3);
            b(g2);
            g2.j(0);
            return this;
        } catch (n1 e2) {
            throw e2;
        } catch (IOException unused) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).");
        }
    }

    public abstract void e(y0 y0Var);

    public void f(byte[] bArr, int i2, int i3) {
        try {
            y0 p = y0.p(bArr, i2, i3);
            e(p);
            p.K();
        } catch (IOException unused) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean g(c0 c0Var, int i2) {
        return c0Var.m(i2);
    }

    public byte[] h() {
        int i2 = i();
        byte[] bArr = new byte[i2];
        f(bArr, 0, i2);
        return bArr;
    }

    public abstract int i();
}
