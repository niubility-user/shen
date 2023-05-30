package g.e.a.h.c;

/* loaded from: classes12.dex */
public class a {
    private static final char[] a = "0123456789ABCDEF".toCharArray();

    private static byte[] a(char[] cArr) {
        if ((cArr.length & 1) == 0) {
            byte[] bArr = new byte[cArr.length >> 1];
            int i2 = 0;
            int i3 = 0;
            while (i2 < cArr.length) {
                int digit = Character.digit(cArr[i2], 16);
                if (digit == -1) {
                    throw new IllegalArgumentException("Illegal hexadecimal character at index " + i2);
                }
                int i4 = i2 + 1;
                int digit2 = Character.digit(cArr[i4], 16);
                if (digit2 == -1) {
                    throw new IllegalArgumentException("Illegal hexadecimal character at index " + i4);
                }
                i2 = i4 + 1;
                bArr[i3] = (byte) (((digit << 4) | digit2) & 255);
                i3++;
            }
            return bArr;
        }
        throw new IllegalArgumentException("Odd number of characters.");
    }

    public static byte[] b(String str) {
        return a(str.toCharArray());
    }

    public static String c(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            char[] cArr = a;
            sb.append(cArr[(b >> 4) & 15]);
            sb.append(cArr[b & 15]);
        }
        return sb.toString();
    }
}
