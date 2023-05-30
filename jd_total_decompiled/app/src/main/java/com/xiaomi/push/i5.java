package com.xiaomi.push;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.zip.GZIPInputStream;

/* loaded from: classes11.dex */
public class i5 {
    public static final byte[] d = {80, 85, 83, 72};
    private byte a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    private byte[] f18740c;

    /* loaded from: classes11.dex */
    public static class a {
        public static final c a = new c();

        public static byte[] a(byte[] bArr) {
            return b(bArr, a);
        }

        public static byte[] b(byte[] bArr, b bVar) {
            if (i5.f(bArr)) {
                i5 e2 = i5.e(bArr);
                return (e2.a == 0 || e2.a != bVar.a()) ? e2.f18740c : bVar.a(e2.f18740c, e2.b);
            }
            return bArr;
        }
    }

    /* loaded from: classes11.dex */
    public interface b {
        byte a();

        byte[] a(byte[] bArr, int i2);
    }

    /* loaded from: classes11.dex */
    public static final class c implements b {
        @Override // com.xiaomi.push.i5.b
        public byte a() {
            return (byte) 2;
        }

        @Override // com.xiaomi.push.i5.b
        public byte[] a(byte[] bArr, int i2) {
            GZIPInputStream gZIPInputStream;
            GZIPInputStream gZIPInputStream2 = null;
            try {
                gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(bArr), i2);
            } catch (IOException unused) {
            } catch (Throwable th) {
                th = th;
            }
            try {
                byte[] bArr2 = new byte[i2];
                gZIPInputStream.read(bArr2);
                try {
                    gZIPInputStream.close();
                } catch (IOException unused2) {
                }
                return bArr2;
            } catch (IOException unused3) {
                gZIPInputStream2 = gZIPInputStream;
                if (gZIPInputStream2 != null) {
                    try {
                        gZIPInputStream2.close();
                    } catch (IOException unused4) {
                    }
                }
                return bArr;
            } catch (Throwable th2) {
                th = th2;
                gZIPInputStream2 = gZIPInputStream;
                if (gZIPInputStream2 != null) {
                    try {
                        gZIPInputStream2.close();
                    } catch (IOException unused5) {
                    }
                }
                throw th;
            }
        }
    }

    protected i5(byte b2, int i2, byte[] bArr) {
        this((short) 1, b2, i2, bArr);
    }

    protected i5(short s, byte b2, int i2, byte[] bArr) {
        this.a = b2;
        this.b = i2;
        this.f18740c = bArr;
    }

    public static i5 c(byte b2, int i2, byte[] bArr) {
        return new i5(b2, i2, bArr);
    }

    public static i5 d(short s, byte b2, int i2, byte[] bArr) {
        return new i5(s, b2, i2, bArr);
    }

    public static i5 e(byte[] bArr) {
        if (f(bArr)) {
            ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.BIG_ENDIAN);
            order.getInt();
            short s = order.getShort();
            byte b2 = order.get();
            int i2 = order.getInt();
            byte[] bArr2 = new byte[order.getInt()];
            order.get(bArr2);
            return d(s, b2, i2, bArr2);
        }
        return c((byte) 0, bArr.length, bArr);
    }

    public static boolean f(byte[] bArr) {
        byte[] bArr2 = d;
        return g(bArr2, bArr, bArr2.length);
    }

    public static boolean g(byte[] bArr, byte[] bArr2, int i2) {
        if (bArr.length < i2 || bArr2.length < i2) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (bArr[i3] != bArr2[i3]) {
                return false;
            }
        }
        return true;
    }
}
