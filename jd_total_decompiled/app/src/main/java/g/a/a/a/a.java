package g.a.a.a;

import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes12.dex */
public final class a {
    private static short a(byte[] bArr, byte[] bArr2, int i2) {
        int i3 = i2 % 8;
        short s = (short) (bArr2[i2] & 255);
        return (bArr[i2 / 8] & e.a[i3]) != 0 ? (short) (s | 256) : s;
    }

    private static int b(char c2) {
        int i2 = c2 - '\u4e00';
        if (i2 < 0 || i2 >= 7000) {
            if (7000 <= i2 && i2 < 14000) {
                return a(c.a, c.b, i2 - R2.dimen.pd_space_width_10);
            }
            return a(d.a, d.b, i2 - 14000);
        }
        return a(b.a, b.b, i2);
    }

    public static boolean c(char c2) {
        return ('\u4e00' <= c2 && c2 <= '\u9fa5' && b(c2) > 0) || '\u3007' == c2;
    }

    public static String d(char c2) {
        if (c(c2)) {
            return c2 == '\u3007' ? "LING" : e.b[b(c2)];
        }
        return String.valueOf(c2);
    }
}
