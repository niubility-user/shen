package com.xiaomi.push;

/* loaded from: classes11.dex */
public abstract class j9 {
    public int a() {
        return 0;
    }

    public abstract int b(byte[] bArr, int i2, int i3);

    public void c(int i2) {
    }

    public abstract void d(byte[] bArr, int i2, int i3);

    public byte[] e() {
        return null;
    }

    public int f() {
        return -1;
    }

    public int g(byte[] bArr, int i2, int i3) {
        int i4 = 0;
        while (i4 < i3) {
            int b = b(bArr, i2 + i4, i3 - i4);
            if (b <= 0) {
                throw new k9("Cannot read. Remote side has closed. Tried to read " + i3 + " bytes, but only got " + i4 + " bytes.");
            }
            i4 += b;
        }
        return i4;
    }
}
