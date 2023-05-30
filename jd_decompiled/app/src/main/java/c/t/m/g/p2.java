package c.t.m.g;

import android.util.Base64;

/* loaded from: classes.dex */
public class p2 {
    public static final byte[] a = new byte[0];

    public static byte[] a(int i2) {
        byte[] bArr = new byte[2];
        for (int i3 = 0; i3 < 2; i3++) {
            bArr[i3] = Integer.valueOf(i2 & 255).byteValue();
            i2 >>= 8;
        }
        return bArr;
    }

    public static byte[] b(String str) {
        try {
            return c(str.getBytes("UTF-8"), 4);
        } catch (Throwable unused) {
            return a;
        }
    }

    public static byte[] c(byte[] bArr, int i2) {
        byte[] b = f4.b(bArr);
        String a2 = o5.a("fc_base");
        t2.c(a2);
        if (i2 != 1 && i2 != 2) {
            if (i2 != 3) {
                if (i2 == 4) {
                    byte[] h2 = o5.h(b, a2);
                    if (!t2.e(h2)) {
                        byte[] encode = Base64.encode(h2, 2);
                        if (!t2.e(encode)) {
                            return encode;
                        }
                    }
                    return a;
                }
                return a;
            }
            b = o5.h(b, a2);
        }
        if (t2.e(b)) {
            return a;
        }
        byte[] bArr2 = new byte[b.length + 2];
        System.arraycopy(a(b.length), 0, bArr2, 0, 2);
        System.arraycopy(b, 0, bArr2, 2, b.length);
        return bArr2;
    }
}
