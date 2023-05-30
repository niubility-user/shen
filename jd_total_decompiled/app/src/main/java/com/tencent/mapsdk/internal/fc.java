package com.tencent.mapsdk.internal;

import androidx.core.view.MotionEventCompat;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* loaded from: classes9.dex */
public class fc implements gc {

    /* renamed from: h  reason: collision with root package name */
    private static final String f16526h = "UTF-16LE";

    /* renamed from: g  reason: collision with root package name */
    private ByteArrayOutputStream f16527g = new ByteArrayOutputStream();

    public static String a(byte[] bArr, String str) {
        try {
            return new String(bArr, str).trim();
        } catch (Exception unused) {
            return null;
        }
    }

    private void a(long j2, int i2) {
        for (int i3 = i2 - 1; i3 >= 0; i3--) {
            a((byte) ((j2 >> (i3 * 8)) & 255));
        }
    }

    private static void a(long j2, int i2, byte[] bArr) {
        for (int i3 = i2 - 1; i3 >= 0; i3--) {
            bArr[(i2 - i3) - 1] = (byte) ((j2 >> (i3 * 8)) & 255);
        }
    }

    public static byte[] a(double d) {
        return b(Double.doubleToLongBits(d));
    }

    public static byte[] a(float f2) {
        return d(Float.floatToIntBits(f2));
    }

    public static boolean b(byte[] bArr) {
        return bArr[0] > 0;
    }

    public static byte[] b(long j2) {
        return new byte[]{(byte) j2, (byte) (j2 >> 8), (byte) (j2 >> 16), (byte) (j2 >> 24), (byte) (j2 >> 32), (byte) (j2 >> 40), (byte) (j2 >> 48), (byte) (j2 >> 56)};
    }

    public static byte[] b(String str) {
        if (str != null) {
            try {
                return str.getBytes("UTF-16LE");
            } catch (Exception unused) {
            }
        }
        return new byte[0];
    }

    public static double c(byte[] bArr) {
        return Double.longBitsToDouble(f(bArr));
    }

    public static byte[] c(int i2) {
        byte[] bArr = new byte[4];
        a(i2, 4, bArr);
        return bArr;
    }

    public static float d(byte[] bArr) {
        return Float.intBitsToFloat(e(bArr));
    }

    public static byte[] d(int i2) {
        return new byte[]{(byte) i2, (byte) (i2 >> 8), (byte) (i2 >> 16), (byte) (i2 >> 24)};
    }

    public static int e(byte[] bArr) {
        return ((bArr[3] << 24) & (-16777216)) | (bArr[0] & 255) | ((bArr[1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | ((bArr[2] << 16) & 16711680);
    }

    public static long f(byte[] bArr) {
        return (bArr[0] & 255) | ((bArr[1] & 255) << 8) | ((bArr[2] & 255) << 16) | ((bArr[3] & 255) << 24) | ((bArr[4] & 255) << 32) | ((bArr[5] & 255) << 40) | ((bArr[6] & 255) << 48) | ((bArr[7] & 255) << 56);
    }

    public static String g(byte[] bArr) {
        try {
            return new String(bArr, "UTF-16LE").trim();
        } catch (Exception unused) {
            return null;
        }
    }

    public void a(byte b) {
        this.f16527g.write(b);
    }

    public void a(char c2) {
        for (int i2 = 0; i2 < 2; i2++) {
            a((byte) ((c2 >> (i2 * 8)) & 255));
        }
    }

    public void a(int i2) {
        a(i2, 4);
    }

    public void a(long j2) {
        a(j2, 8);
    }

    public void a(String str) {
        if (str == null) {
            return;
        }
        byte[] bytes = str.getBytes();
        b(bytes.length);
        a(bytes);
    }

    public void a(boolean z) {
        a(z ? (byte) 1 : (byte) 0);
    }

    public void a(byte[] bArr) {
        this.f16527g.write(bArr, 0, bArr.length);
    }

    public byte[] a() {
        byte[] bArr;
        IOException e2;
        try {
            this.f16527g.close();
            bArr = this.f16527g.toByteArray();
            try {
                this.f16527g = null;
            } catch (IOException e3) {
                e2 = e3;
                e2.printStackTrace();
                return bArr;
            }
        } catch (IOException e4) {
            bArr = null;
            e2 = e4;
        }
        return bArr;
    }

    public void b(int i2) {
        a(i2, 2);
    }
}
