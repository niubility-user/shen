package c.t.m.g;

/* loaded from: classes.dex */
public class s4 {
    public static final byte[] a = new byte[0];

    public static byte[] a(int i2) {
        byte[] bArr = new byte[2];
        for (int i3 = 0; i3 < 2; i3++) {
            bArr[i3] = Integer.valueOf(i2 & 255).byteValue();
            i2 >>= 8;
        }
        return bArr;
    }

    public static byte[] b(byte[] bArr, String str) {
        if (bArr == null || bArr.length == 0) {
            return a;
        }
        byte[] h2 = o5.h(f4.b(bArr), str);
        if (h2 == null || h2.length == 0) {
            return a;
        }
        byte[] bArr2 = new byte[h2.length + 2];
        System.arraycopy(a(h2.length), 0, bArr2, 0, 2);
        System.arraycopy(h2, 0, bArr2, 2, h2.length);
        return bArr2;
    }
}
