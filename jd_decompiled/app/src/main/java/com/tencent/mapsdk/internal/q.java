package com.tencent.mapsdk.internal;

import com.jingdong.sdk.platform.business.personal.R2;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;
import jd.wjlogin_sdk.util.ReplyCode;

/* loaded from: classes9.dex */
public final class q {
    private static final int a = 37;
    private static final int b = 17;

    /* renamed from: c  reason: collision with root package name */
    private static final byte[] f17003c;
    private static final byte[] d;

    static {
        byte[] bArr = {48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 65, 66, 67, 68, 69, 70};
        byte[] bArr2 = new byte[256];
        byte[] bArr3 = new byte[256];
        for (int i2 = 0; i2 < 256; i2++) {
            bArr2[i2] = bArr[i2 >>> 4];
            bArr3[i2] = bArr[i2 & 15];
        }
        f17003c = bArr2;
        d = bArr3;
    }

    public static int a(byte b2) {
        return b2 + 629;
    }

    public static int a(byte b2, byte b3) {
        if (b2 < b3) {
            return -1;
        }
        return b2 > b3 ? 1 : 0;
    }

    public static int a(char c2) {
        return c2 + '\u0275';
    }

    public static int a(char c2, char c3) {
        if (c2 < c3) {
            return -1;
        }
        return c2 > c3 ? 1 : 0;
    }

    public static int a(double d2) {
        return a(Double.doubleToLongBits(d2));
    }

    public static int a(double d2, double d3) {
        if (d2 < d3) {
            return -1;
        }
        return d2 > d3 ? 1 : 0;
    }

    public static int a(float f2) {
        return Float.floatToIntBits(f2) + R2.attr.closeIconEndPadding;
    }

    public static int a(float f2, float f3) {
        if (f2 < f3) {
            return -1;
        }
        return f2 > f3 ? 1 : 0;
    }

    public static int a(int i2) {
        return i2 + R2.attr.closeIconEndPadding;
    }

    public static int a(int i2, int i3) {
        if (i2 < i3) {
            return -1;
        }
        return i2 > i3 ? 1 : 0;
    }

    public static int a(long j2) {
        return ((int) (j2 ^ (j2 >> 32))) + R2.attr.closeIconEndPadding;
    }

    public static int a(long j2, long j3) {
        int i2 = (j2 > j3 ? 1 : (j2 == j3 ? 0 : -1));
        if (i2 < 0) {
            return -1;
        }
        return i2 > 0 ? 1 : 0;
    }

    public static <T extends Comparable<T>> int a(T t, T t2) {
        return t.compareTo(t2);
    }

    public static int a(Object obj) {
        if (obj == null) {
            return R2.attr.closeIconEndPadding;
        }
        if (obj.getClass().isArray()) {
            return obj instanceof long[] ? a((long[]) obj) : obj instanceof int[] ? a((int[]) obj) : obj instanceof short[] ? a((short[]) obj) : obj instanceof char[] ? a((char[]) obj) : obj instanceof byte[] ? b((byte[]) obj) : obj instanceof double[] ? a((double[]) obj) : obj instanceof float[] ? a((float[]) obj) : obj instanceof boolean[] ? a((boolean[]) obj) : obj instanceof p[] ? a((p[]) obj) : a((Object[]) obj);
        }
        boolean z = obj instanceof p;
        int hashCode = obj.hashCode();
        return z ? hashCode : hashCode + R2.attr.closeIconEndPadding;
    }

    public static <T extends Comparable<T>> int a(List<T> list, List<T> list2) {
        Iterator<T> it = list.iterator();
        Iterator<T> it2 = list2.iterator();
        while (it.hasNext() && it2.hasNext()) {
            int compareTo = it.next().compareTo(it2.next());
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return a(it.hasNext(), it2.hasNext());
    }

    public static int a(short s) {
        return s + 629;
    }

    public static int a(short s, short s2) {
        if (s < s2) {
            return -1;
        }
        return s > s2 ? 1 : 0;
    }

    public static int a(boolean z) {
        return (!z ? 1 : 0) + R2.attr.closeIconEndPadding;
    }

    public static int a(boolean z, boolean z2) {
        return (z ? 1 : 0) - (z2 ? 1 : 0);
    }

    public static int a(byte[] bArr, byte[] bArr2) {
        int i2 = 0;
        for (int i3 = 0; i2 < bArr.length && i3 < bArr2.length; i3++) {
            int a2 = a(bArr[i2], bArr2[i3]);
            if (a2 != 0) {
                return a2;
            }
            i2++;
        }
        return a(bArr.length, bArr2.length);
    }

    public static int a(char[] cArr) {
        if (cArr == null) {
            return R2.attr.closeIconEndPadding;
        }
        int i2 = 17;
        for (char c2 : cArr) {
            i2 = (i2 * 37) + c2;
        }
        return i2;
    }

    public static int a(char[] cArr, char[] cArr2) {
        int i2 = 0;
        for (int i3 = 0; i2 < cArr.length && i3 < cArr2.length; i3++) {
            int a2 = a(cArr[i2], cArr2[i3]);
            if (a2 != 0) {
                return a2;
            }
            i2++;
        }
        return a(cArr.length, cArr2.length);
    }

    public static int a(double[] dArr) {
        if (dArr == null) {
            return R2.attr.closeIconEndPadding;
        }
        int i2 = 17;
        for (int i3 = 0; i3 < dArr.length; i3++) {
            i2 = (i2 * 37) + ((int) (Double.doubleToLongBits(dArr[i3]) ^ (Double.doubleToLongBits(dArr[i3]) >> 32)));
        }
        return i2;
    }

    public static int a(double[] dArr, double[] dArr2) {
        int i2 = 0;
        for (int i3 = 0; i2 < dArr.length && i3 < dArr2.length; i3++) {
            int a2 = a(dArr[i2], dArr2[i3]);
            if (a2 != 0) {
                return a2;
            }
            i2++;
        }
        return a(dArr.length, dArr2.length);
    }

    public static int a(float[] fArr) {
        if (fArr == null) {
            return R2.attr.closeIconEndPadding;
        }
        int i2 = 17;
        for (float f2 : fArr) {
            i2 = (i2 * 37) + Float.floatToIntBits(f2);
        }
        return i2;
    }

    public static int a(float[] fArr, float[] fArr2) {
        int i2 = 0;
        for (int i3 = 0; i2 < fArr.length && i3 < fArr2.length; i3++) {
            int a2 = a(fArr[i2], fArr2[i3]);
            if (a2 != 0) {
                return a2;
            }
            i2++;
        }
        return a(fArr.length, fArr2.length);
    }

    public static int a(int[] iArr) {
        if (iArr == null) {
            return R2.attr.closeIconEndPadding;
        }
        int i2 = 17;
        for (int i3 : iArr) {
            i2 = (i2 * 37) + i3;
        }
        return i2;
    }

    public static int a(int[] iArr, int[] iArr2) {
        int i2 = 0;
        for (int i3 = 0; i2 < iArr.length && i3 < iArr2.length; i3++) {
            int a2 = a(iArr[i2], iArr2[i3]);
            if (a2 != 0) {
                return a2;
            }
            i2++;
        }
        return a(iArr.length, iArr2.length);
    }

    public static int a(long[] jArr) {
        if (jArr == null) {
            return R2.attr.closeIconEndPadding;
        }
        int i2 = 17;
        for (int i3 = 0; i3 < jArr.length; i3++) {
            i2 = (i2 * 37) + ((int) (jArr[i3] ^ (jArr[i3] >> 32)));
        }
        return i2;
    }

    public static int a(long[] jArr, long[] jArr2) {
        int i2 = 0;
        for (int i3 = 0; i2 < jArr.length && i3 < jArr2.length; i3++) {
            int a2 = a(jArr[i2], jArr2[i3]);
            if (a2 != 0) {
                return a2;
            }
            i2++;
        }
        return a(jArr.length, jArr2.length);
    }

    public static int a(p[] pVarArr) {
        if (pVarArr == null) {
            return R2.attr.closeIconEndPadding;
        }
        int i2 = 17;
        for (p pVar : pVarArr) {
            i2 = (i2 * 37) + pVar.hashCode();
        }
        return i2;
    }

    public static <T extends Comparable<T>> int a(T[] tArr, T[] tArr2) {
        int i2 = 0;
        for (int i3 = 0; i2 < tArr.length && i3 < tArr2.length; i3++) {
            int compareTo = tArr[i2].compareTo(tArr2[i3]);
            if (compareTo != 0) {
                return compareTo;
            }
            i2++;
        }
        return a(tArr.length, tArr2.length);
    }

    public static int a(short[] sArr) {
        if (sArr == null) {
            return R2.attr.closeIconEndPadding;
        }
        int i2 = 17;
        for (short s : sArr) {
            i2 = (i2 * 37) + s;
        }
        return i2;
    }

    public static int a(short[] sArr, short[] sArr2) {
        int i2 = 0;
        for (int i3 = 0; i2 < sArr.length && i3 < sArr2.length; i3++) {
            int a2 = a(sArr[i2], sArr2[i3]);
            if (a2 != 0) {
                return a2;
            }
            i2++;
        }
        return a(sArr.length, sArr2.length);
    }

    public static int a(boolean[] zArr) {
        if (zArr == null) {
            return R2.attr.closeIconEndPadding;
        }
        int i2 = 17;
        for (boolean z : zArr) {
            i2 = (i2 * 37) + (!z ? 1 : 0);
        }
        return i2;
    }

    public static int a(boolean[] zArr, boolean[] zArr2) {
        int i2 = 0;
        for (int i3 = 0; i2 < zArr.length && i3 < zArr2.length; i3++) {
            int a2 = a(zArr[i2], zArr2[i3]);
            if (a2 != 0) {
                return a2;
            }
            i2++;
        }
        return a(zArr.length, zArr2.length);
    }

    public static String a(ByteBuffer byteBuffer) {
        int remaining = byteBuffer.remaining();
        if (remaining == 0) {
            return "empty";
        }
        StringBuffer stringBuffer = new StringBuffer((byteBuffer.remaining() * 3) - 1);
        int position = byteBuffer.position();
        int i2 = byteBuffer.get() & 255;
        stringBuffer.append((char) f17003c[i2]);
        byte b2 = d[i2];
        while (true) {
            stringBuffer.append((char) b2);
            remaining--;
            if (remaining <= 0) {
                byteBuffer.position(position);
                return stringBuffer.toString();
            }
            stringBuffer.append(' ');
            int i3 = byteBuffer.get() & 255;
            stringBuffer.append((char) f17003c[i3]);
            b2 = d[i3];
        }
    }

    public static String a(byte[] bArr) {
        return a(ByteBuffer.wrap(bArr));
    }

    public static boolean a(Object obj, Object obj2) {
        return obj == null ? obj == obj2 : obj.equals(obj2);
    }

    public static int b(byte[] bArr) {
        if (bArr == null) {
            return R2.attr.closeIconEndPadding;
        }
        int i2 = 17;
        for (byte b2 : bArr) {
            i2 = (i2 * 37) + b2;
        }
        return i2;
    }

    public static boolean b(byte b2, byte b3) {
        return b2 == b3;
    }

    public static boolean b(char c2, char c3) {
        return c2 == c3;
    }

    public static boolean b(double d2, double d3) {
        return d2 == d3;
    }

    public static boolean b(float f2, float f3) {
        return f2 == f3;
    }

    public static boolean b(int i2, int i3) {
        return i2 == i3;
    }

    public static boolean b(long j2, long j3) {
        return j2 == j3;
    }

    public static boolean b(short s, short s2) {
        return s == s2;
    }

    public static boolean b(boolean z, boolean z2) {
        return z == z2;
    }

    public static byte[] b(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        byte[] bArr = new byte[position];
        System.arraycopy(byteBuffer.array(), 0, bArr, 0, position);
        return bArr;
    }
}
