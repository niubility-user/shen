package g.c.a;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.util.Arrays;
import java.util.regex.Pattern;

/* loaded from: classes12.dex */
public final class c {
    private static final Pattern a = Pattern.compile("\\.");

    public static int a(String str, char c2) {
        int i2 = 0;
        for (int i3 = 0; i3 < str.length(); i3++) {
            if (str.charAt(i3) == c2) {
                i2++;
            }
        }
        return i2;
    }

    public static String b(String str) {
        if (str.contains("::")) {
            if (str.equals("::")) {
                return c(8);
            }
            int a2 = a(str, ':');
            if (str.startsWith("::")) {
                return str.replace("::", c(9 - a2));
            }
            if (str.endsWith("::")) {
                return str.replace("::", ":" + c(9 - a2));
            }
            return str.replace("::", ":" + c(8 - a2));
        }
        return str;
    }

    public static String c(int i2) {
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < i2; i3++) {
            sb.append("0:");
        }
        return sb.toString();
    }

    public static boolean d(int i2) {
        return i2 >= 0 && i2 < 4;
    }

    public static boolean e(long j2, long j3) {
        return (j2 < j3) ^ (((j2 > 0L ? 1 : (j2 == 0L ? 0 : -1)) < 0) != ((j3 > 0L ? 1 : (j3 == 0L ? 0 : -1)) < 0));
    }

    public static b f(long[] jArr) {
        long j2 = 0;
        long j3 = 0;
        for (int i2 = 0; i2 < jArr.length; i2++) {
            if (d(i2)) {
                j2 |= jArr[i2] << (((4 - i2) - 1) * 16);
            } else {
                j3 |= jArr[i2] << (((4 - i2) - 1) * 16);
            }
        }
        return new b(j2, j3);
    }

    public static long[] g(String[] strArr) {
        long[] jArr = new long[strArr.length];
        for (int i2 = 0; i2 < strArr.length; i2++) {
            jArr[i2] = Long.parseLong(strArr[i2], 16);
        }
        return jArr;
    }

    public static byte[] h(byte[] bArr, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, 0, bArr2, i2 - bArr.length, bArr.length);
        return bArr2;
    }

    public static String i(String str) {
        if (str.contains(OrderISVUtil.MONEY_DECIMAL)) {
            int lastIndexOf = str.lastIndexOf(":") + 1;
            String substring = str.substring(0, lastIndexOf);
            String substring2 = str.substring(lastIndexOf);
            if (substring2.contains(OrderISVUtil.MONEY_DECIMAL)) {
                String[] split = a.split(substring2);
                if (split.length == 4) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(substring);
                    int parseInt = Integer.parseInt(split[0]);
                    int parseInt2 = Integer.parseInt(split[1]);
                    int parseInt3 = Integer.parseInt(split[2]);
                    int parseInt4 = Integer.parseInt(split[3]);
                    sb.append(String.format("%02x", Integer.valueOf(parseInt)));
                    sb.append(String.format("%02x", Integer.valueOf(parseInt2)));
                    sb.append(":");
                    sb.append(String.format("%02x", Integer.valueOf(parseInt3)));
                    sb.append(String.format("%02x", Integer.valueOf(parseInt4)));
                    return sb.toString();
                }
                throw new IllegalArgumentException(String.format("can not parse [%s]", str));
            }
            throw new IllegalArgumentException(String.format("can not parse [%s]", str));
        }
        return str;
    }

    public static void j(long[] jArr) {
        if (jArr.length == 8) {
            for (long j2 : jArr) {
                if (j2 < 0) {
                    throw new IllegalArgumentException("each element should be positive [" + Arrays.toString(jArr) + "]");
                } else if (j2 > 65535) {
                    throw new IllegalArgumentException("each element should be less than 0xFFFF [" + Arrays.toString(jArr) + "]");
                }
            }
            return;
        }
        throw new IllegalArgumentException("an IPv6 address should contain 8 shorts [" + Arrays.toString(jArr) + "]");
    }
}
