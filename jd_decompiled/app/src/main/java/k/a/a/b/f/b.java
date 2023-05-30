package k.a.a.b.f;

/* loaded from: classes11.dex */
public class b {
    private static final int[] b = new int[256];
    private final int[] a = new int[3];

    static {
        for (int i2 = 0; i2 < 256; i2++) {
            int i3 = i2;
            for (int i4 = 0; i4 < 8; i4++) {
                i3 = (i3 & 1) == 1 ? (i3 >>> 1) ^ (-306674912) : i3 >>> 1;
            }
            b[i2] = i3;
        }
    }

    private int a(int i2, byte b2) {
        return b[(i2 ^ b2) & 255] ^ (i2 >>> 8);
    }

    public byte b() {
        int i2 = this.a[2] | 2;
        return (byte) ((i2 * (i2 ^ 1)) >>> 8);
    }

    public void c(char[] cArr) {
        int[] iArr = this.a;
        iArr[0] = 305419896;
        iArr[1] = 591751049;
        iArr[2] = 878082192;
        for (char c2 : cArr) {
            d((byte) (c2 & '\u00ff'));
        }
    }

    public void d(byte b2) {
        int[] iArr = this.a;
        iArr[0] = a(iArr[0], b2);
        int[] iArr2 = this.a;
        iArr2[1] = iArr2[1] + (iArr2[0] & 255);
        iArr2[1] = (iArr2[1] * 134775813) + 1;
        iArr2[2] = a(iArr2[2], (byte) (iArr2[1] >> 24));
    }
}
