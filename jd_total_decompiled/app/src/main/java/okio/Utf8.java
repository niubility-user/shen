package okio;

/* loaded from: classes11.dex */
public final class Utf8 {
    private Utf8() {
    }

    public static long size(String str) {
        return size(str, 0, str.length());
    }

    public static long size(String str, int i2, int i3) {
        long j2;
        if (str != null) {
            if (i2 < 0) {
                throw new IllegalArgumentException("beginIndex < 0: " + i2);
            } else if (i3 >= i2) {
                if (i3 > str.length()) {
                    throw new IllegalArgumentException("endIndex > string.length: " + i3 + " > " + str.length());
                }
                long j3 = 0;
                while (i2 < i3) {
                    char charAt = str.charAt(i2);
                    if (charAt < '\u0080') {
                        j3++;
                    } else {
                        if (charAt < '\u0800') {
                            j2 = 2;
                        } else if (charAt < '\ud800' || charAt > '\udfff') {
                            j2 = 3;
                        } else {
                            int i4 = i2 + 1;
                            char charAt2 = i4 < i3 ? str.charAt(i4) : (char) 0;
                            if (charAt > '\udbff' || charAt2 < '\udc00' || charAt2 > '\udfff') {
                                j3++;
                                i2 = i4;
                            } else {
                                j3 += 4;
                                i2 += 2;
                            }
                        }
                        j3 += j2;
                    }
                    i2++;
                }
                return j3;
            } else {
                throw new IllegalArgumentException("endIndex < beginIndex: " + i3 + " < " + i2);
            }
        }
        throw new IllegalArgumentException("string == null");
    }
}
