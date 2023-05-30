package c.t.m.g;

import com.jd.dynamic.DYConstants;

/* loaded from: classes.dex */
public class e2 {
    public static final byte[] a = new byte[0];

    public static String a(double[] dArr, int i2, boolean z) {
        if (dArr == null) {
            return DYConstants.DY_NULL_STR;
        }
        int length = dArr.length - 1;
        if (length == -1) {
            return z ? "[]" : "";
        }
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append('[');
        }
        int i3 = 0;
        while (true) {
            sb.append(f2.b(dArr[i3], i2));
            if (i3 == length) {
                break;
            }
            sb.append(DYConstants.DY_REGEX_COMMA);
            i3++;
        }
        if (z) {
            sb.append(']');
        }
        return sb.toString();
    }
}
