package com.meizu.cloud.pushsdk.e.h;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import jd.wjlogin_sdk.util.ReplyCode;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes14.dex */
public final class b implements d, c, Cloneable {

    /* renamed from: i */
    private static final byte[] f15838i = {48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 97, 98, 99, ReplyCode.reply0x64, 101, 102};

    /* renamed from: g */
    j f15839g;

    /* renamed from: h */
    long f15840h;

    /* loaded from: classes14.dex */
    class a extends InputStream {
        a() {
            b.this = r1;
        }

        @Override // java.io.InputStream
        public int available() {
            return (int) Math.min(b.this.f15840h, 2147483647L);
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.InputStream
        public int read() {
            b bVar = b.this;
            if (bVar.f15840h > 0) {
                return bVar.y() & 255;
            }
            return -1;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i2, int i3) {
            return b.this.i(bArr, i2, i3);
        }

        public String toString() {
            return b.this + ".inputStream()";
        }
    }

    public long A() {
        return this.f15840h;
    }

    @Override // com.meizu.cloud.pushsdk.e.h.c
    public /* bridge */ /* synthetic */ c a(long j2) throws IOException {
        s(j2);
        return this;
    }

    @Override // com.meizu.cloud.pushsdk.e.h.c
    public /* bridge */ /* synthetic */ c a(String str) throws IOException {
        l(str);
        return this;
    }

    @Override // com.meizu.cloud.pushsdk.e.h.c
    public /* bridge */ /* synthetic */ c a(byte[] bArr) throws IOException {
        p(bArr);
        return this;
    }

    @Override // com.meizu.cloud.pushsdk.e.h.c
    public /* bridge */ /* synthetic */ c a(byte[] bArr, int i2, int i3) throws IOException {
        q(bArr, i2, i3);
        return this;
    }

    @Override // com.meizu.cloud.pushsdk.e.h.d
    public String a() {
        try {
            return h(this.f15840h, o.a);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    @Override // com.meizu.cloud.pushsdk.e.h.c
    public long b(m mVar) throws IOException {
        if (mVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j2 = 0;
        while (true) {
            long d = mVar.d(this, 2048L);
            if (d == -1) {
                return j2;
            }
            j2 += d;
        }
    }

    @Override // com.meizu.cloud.pushsdk.e.h.d
    public byte[] b() {
        try {
            return n(this.f15840h);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    @Override // com.meizu.cloud.pushsdk.e.h.c
    public b c() {
        return this;
    }

    @Override // com.meizu.cloud.pushsdk.e.h.l
    public void c(b bVar, long j2) {
        if (bVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (bVar == this) {
            throw new IllegalArgumentException("source == this");
        }
        o.a(bVar.f15840h, 0L, j2);
        while (j2 > 0) {
            j jVar = bVar.f15839g;
            if (j2 < jVar.f15858c - jVar.b) {
                j jVar2 = this.f15839g;
                j jVar3 = jVar2 != null ? jVar2.f15861g : null;
                if (jVar3 != null && jVar3.f15859e) {
                    if ((jVar3.f15858c + j2) - (jVar3.d ? 0 : jVar3.b) <= 2048) {
                        jVar.d(jVar3, (int) j2);
                        bVar.f15840h -= j2;
                        this.f15840h += j2;
                        return;
                    }
                }
                bVar.f15839g = jVar.a((int) j2);
            }
            j jVar4 = bVar.f15839g;
            long j3 = jVar4.f15858c - jVar4.b;
            bVar.f15839g = jVar4.e();
            j jVar5 = this.f15839g;
            if (jVar5 == null) {
                this.f15839g = jVar4;
                jVar4.f15861g = jVar4;
                jVar4.f15860f = jVar4;
            } else {
                jVar5.f15861g.b(jVar4);
                jVar4.c();
            }
            bVar.f15840h -= j3;
            this.f15840h += j3;
            j2 -= j3;
        }
    }

    @Override // com.meizu.cloud.pushsdk.e.h.m, java.io.Closeable, java.lang.AutoCloseable, com.meizu.cloud.pushsdk.e.h.l
    public void close() {
    }

    @Override // com.meizu.cloud.pushsdk.e.h.m
    public long d(b bVar, long j2) {
        if (bVar != null) {
            if (j2 < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j2);
            }
            long j3 = this.f15840h;
            if (j3 == 0) {
                return -1L;
            }
            if (j2 > j3) {
                j2 = j3;
            }
            bVar.c(this, j2);
            return j2;
        }
        throw new IllegalArgumentException("sink == null");
    }

    @Override // com.meizu.cloud.pushsdk.e.h.d
    public InputStream d() {
        return new a();
    }

    @Override // com.meizu.cloud.pushsdk.e.h.c
    public /* bridge */ /* synthetic */ c e(e eVar) throws IOException {
        k(eVar);
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof b) {
            b bVar = (b) obj;
            long j2 = this.f15840h;
            if (j2 != bVar.f15840h) {
                return false;
            }
            long j3 = 0;
            if (j2 == 0) {
                return true;
            }
            j jVar = this.f15839g;
            j jVar2 = bVar.f15839g;
            int i2 = jVar.b;
            int i3 = jVar2.b;
            while (j3 < this.f15840h) {
                long min = Math.min(jVar.f15858c - i2, jVar2.f15858c - i3);
                int i4 = 0;
                while (i4 < min) {
                    int i5 = i2 + 1;
                    int i6 = i3 + 1;
                    if (jVar.a[i2] != jVar2.a[i3]) {
                        return false;
                    }
                    i4++;
                    i2 = i5;
                    i3 = i6;
                }
                if (i2 == jVar.f15858c) {
                    jVar = jVar.f15860f;
                    i2 = jVar.b;
                }
                if (i3 == jVar2.f15858c) {
                    jVar2 = jVar2.f15860f;
                    i3 = jVar2.b;
                }
                j3 += min;
            }
            return true;
        }
        return false;
    }

    public b f(String str, int i2, int i3) {
        char charAt;
        int i4;
        if (str != null) {
            if (i2 < 0) {
                throw new IllegalAccessError("beginIndex < 0: " + i2);
            } else if (i3 < i2) {
                throw new IllegalArgumentException("endIndex < beginIndex: " + i3 + " < " + i2);
            } else if (i3 > str.length()) {
                throw new IllegalArgumentException("endIndex > string.length: " + i3 + " > " + str.length());
            } else {
                while (i2 < i3) {
                    char charAt2 = str.charAt(i2);
                    if (charAt2 < '\u0080') {
                        j g2 = g(1);
                        byte[] bArr = g2.a;
                        int i5 = g2.f15858c - i2;
                        int min = Math.min(i3, 2048 - i5);
                        int i6 = i2 + 1;
                        bArr[i2 + i5] = (byte) charAt2;
                        while (true) {
                            i2 = i6;
                            if (i2 >= min || (charAt = str.charAt(i2)) >= '\u0080') {
                                break;
                            }
                            i6 = i2 + 1;
                            bArr[i2 + i5] = (byte) charAt;
                        }
                        int i7 = g2.f15858c;
                        int i8 = (i5 + i2) - i7;
                        g2.f15858c = i7 + i8;
                        this.f15840h += i8;
                    } else {
                        if (charAt2 < '\u0800') {
                            i4 = (charAt2 >> 6) | 192;
                        } else if (charAt2 < '\ud800' || charAt2 > '\udfff') {
                            j((charAt2 >> '\f') | 224);
                            i4 = ((charAt2 >> 6) & 63) | 128;
                        } else {
                            int i9 = i2 + 1;
                            char charAt3 = i9 < i3 ? str.charAt(i9) : (char) 0;
                            if (charAt2 > '\udbff' || charAt3 < '\udc00' || charAt3 > '\udfff') {
                                j(63);
                                i2 = i9;
                            } else {
                                int i10 = (((charAt2 & '\u27ff') << 10) | ('\u23ff' & charAt3)) + 65536;
                                j((i10 >> 18) | 240);
                                j(((i10 >> 12) & 63) | 128);
                                j(((i10 >> 6) & 63) | 128);
                                j((i10 & 63) | 128);
                                i2 += 2;
                            }
                        }
                        j(i4);
                        j((charAt2 & '?') | 128);
                        i2++;
                    }
                }
                return this;
            }
        }
        throw new IllegalArgumentException("string == null");
    }

    @Override // com.meizu.cloud.pushsdk.e.h.l, java.io.Flushable
    public void flush() {
    }

    public j g(int i2) {
        if (i2 < 1 || i2 > 2048) {
            throw new IllegalArgumentException();
        }
        j jVar = this.f15839g;
        if (jVar == null) {
            j a2 = k.a();
            this.f15839g = a2;
            a2.f15861g = a2;
            a2.f15860f = a2;
            return a2;
        }
        j jVar2 = jVar.f15861g;
        if (jVar2.f15858c + i2 > 2048 || !jVar2.f15859e) {
            j a3 = k.a();
            jVar2.b(a3);
            return a3;
        }
        return jVar2;
    }

    public String h(long j2, Charset charset) throws EOFException {
        o.a(this.f15840h, 0L, j2);
        if (charset != null) {
            if (j2 > 2147483647L) {
                throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j2);
            } else if (j2 == 0) {
                return "";
            } else {
                j jVar = this.f15839g;
                if (jVar.b + j2 > jVar.f15858c) {
                    return new String(n(j2), charset);
                }
                String str = new String(jVar.a, jVar.b, (int) j2, charset);
                int i2 = (int) (jVar.b + j2);
                jVar.b = i2;
                this.f15840h -= j2;
                if (i2 == jVar.f15858c) {
                    this.f15839g = jVar.e();
                    k.b(jVar);
                }
                return str;
            }
        }
        throw new IllegalArgumentException("charset == null");
    }

    public int hashCode() {
        j jVar = this.f15839g;
        if (jVar == null) {
            return 0;
        }
        int i2 = 1;
        do {
            int i3 = jVar.f15858c;
            for (int i4 = jVar.b; i4 < i3; i4++) {
                i2 = (i2 * 31) + jVar.a[i4];
            }
            jVar = jVar.f15860f;
        } while (jVar != this.f15839g);
        return i2;
    }

    public int i(byte[] bArr, int i2, int i3) {
        o.a(bArr.length, i2, i3);
        j jVar = this.f15839g;
        if (jVar == null) {
            return -1;
        }
        int min = Math.min(i3, jVar.f15858c - jVar.b);
        System.arraycopy(jVar.a, jVar.b, bArr, i2, min);
        int i4 = jVar.b + min;
        jVar.b = i4;
        this.f15840h -= min;
        if (i4 == jVar.f15858c) {
            this.f15839g = jVar.e();
            k.b(jVar);
        }
        return min;
    }

    public b j(int i2) {
        j g2 = g(1);
        byte[] bArr = g2.a;
        int i3 = g2.f15858c;
        g2.f15858c = i3 + 1;
        bArr[i3] = (byte) i2;
        this.f15840h++;
        return this;
    }

    public b k(e eVar) {
        if (eVar != null) {
            eVar.d(this);
            return this;
        }
        throw new IllegalArgumentException("byteString == null");
    }

    public b l(String str) {
        f(str, 0, str.length());
        return this;
    }

    public void m(byte[] bArr) throws EOFException {
        int i2 = 0;
        while (i2 < bArr.length) {
            int i3 = i(bArr, i2, bArr.length - i2);
            if (i3 == -1) {
                throw new EOFException();
            }
            i2 += i3;
        }
    }

    public byte[] n(long j2) throws EOFException {
        o.a(this.f15840h, 0L, j2);
        if (j2 <= 2147483647L) {
            byte[] bArr = new byte[(int) j2];
            m(bArr);
            return bArr;
        }
        throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j2);
    }

    public b o(int i2) {
        int i3;
        int i4;
        if (i2 >= 128) {
            if (i2 < 2048) {
                i4 = (i2 >> 6) | 192;
            } else {
                if (i2 < 65536) {
                    if (i2 >= 55296 && i2 <= 57343) {
                        throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(i2));
                    }
                    i3 = (i2 >> 12) | 224;
                } else if (i2 > 1114111) {
                    throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(i2));
                } else {
                    j((i2 >> 18) | 240);
                    i3 = ((i2 >> 12) & 63) | 128;
                }
                j(i3);
                i4 = ((i2 >> 6) & 63) | 128;
            }
            j(i4);
            i2 = (i2 & 63) | 128;
        }
        j(i2);
        return this;
    }

    public b p(byte[] bArr) {
        if (bArr != null) {
            q(bArr, 0, bArr.length);
            return this;
        }
        throw new IllegalArgumentException("source == null");
    }

    public b q(byte[] bArr, int i2, int i3) {
        if (bArr != null) {
            long j2 = i3;
            o.a(bArr.length, i2, j2);
            int i4 = i3 + i2;
            while (i2 < i4) {
                j g2 = g(1);
                int min = Math.min(i4 - i2, 2048 - g2.f15858c);
                System.arraycopy(bArr, i2, g2.a, g2.f15858c, min);
                i2 += min;
                g2.f15858c += min;
            }
            this.f15840h += j2;
            return this;
        }
        throw new IllegalArgumentException("source == null");
    }

    public void r(long j2) throws EOFException {
        while (j2 > 0) {
            if (this.f15839g == null) {
                throw new EOFException();
            }
            int min = (int) Math.min(j2, r0.f15858c - r0.b);
            long j3 = min;
            this.f15840h -= j3;
            j2 -= j3;
            j jVar = this.f15839g;
            int i2 = jVar.b + min;
            jVar.b = i2;
            if (i2 == jVar.f15858c) {
                this.f15839g = jVar.e();
                k.b(jVar);
            }
        }
    }

    public b s(long j2) {
        int i2 = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
        if (i2 == 0) {
            j(48);
            return this;
        }
        boolean z = false;
        int i3 = 1;
        if (i2 < 0) {
            j2 = -j2;
            if (j2 < 0) {
                l("-9223372036854775808");
                return this;
            }
            z = true;
        }
        if (j2 >= 100000000) {
            i3 = j2 < 1000000000000L ? j2 < 10000000000L ? j2 < 1000000000 ? 9 : 10 : j2 < 100000000000L ? 11 : 12 : j2 < 1000000000000000L ? j2 < 10000000000000L ? 13 : j2 < 100000000000000L ? 14 : 15 : j2 < 100000000000000000L ? j2 < 10000000000000000L ? 16 : 17 : j2 < 1000000000000000000L ? 18 : 19;
        } else if (j2 >= 10000) {
            i3 = j2 < 1000000 ? j2 < 100000 ? 5 : 6 : j2 < 10000000 ? 7 : 8;
        } else if (j2 >= 100) {
            i3 = j2 < 1000 ? 3 : 4;
        } else if (j2 >= 10) {
            i3 = 2;
        }
        if (z) {
            i3++;
        }
        j g2 = g(i3);
        byte[] bArr = g2.a;
        int i4 = g2.f15858c + i3;
        while (j2 != 0) {
            i4--;
            bArr[i4] = f15838i[(int) (j2 % 10)];
            j2 /= 10;
        }
        if (z) {
            bArr[i4 - 1] = 45;
        }
        g2.f15858c += i3;
        this.f15840h += i3;
        return this;
    }

    public b t(long j2) {
        if (j2 == 0) {
            j(48);
            return this;
        }
        int numberOfTrailingZeros = (Long.numberOfTrailingZeros(Long.highestOneBit(j2)) / 4) + 1;
        j g2 = g(numberOfTrailingZeros);
        byte[] bArr = g2.a;
        int i2 = g2.f15858c;
        for (int i3 = (i2 + numberOfTrailingZeros) - 1; i3 >= i2; i3--) {
            bArr[i3] = f15838i[(int) (15 & j2)];
            j2 >>>= 4;
        }
        g2.f15858c += numberOfTrailingZeros;
        this.f15840h += numberOfTrailingZeros;
        return this;
    }

    public String toString() {
        long j2 = this.f15840h;
        if (j2 == 0) {
            return "Buffer[size=0]";
        }
        if (j2 <= 16) {
            return String.format("Buffer[size=%s data=%s]", Long.valueOf(this.f15840h), clone().z().a());
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            j jVar = this.f15839g;
            byte[] bArr = jVar.a;
            int i2 = jVar.b;
            messageDigest.update(bArr, i2, jVar.f15858c - i2);
            j jVar2 = this.f15839g;
            while (true) {
                jVar2 = jVar2.f15860f;
                if (jVar2 == this.f15839g) {
                    return String.format("Buffer[size=%s md5=%s]", Long.valueOf(this.f15840h), e.a(messageDigest.digest()).a());
                }
                byte[] bArr2 = jVar2.a;
                int i3 = jVar2.b;
                messageDigest.update(bArr2, i3, jVar2.f15858c - i3);
            }
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        }
    }

    public void u() {
        try {
            r(this.f15840h);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    /* renamed from: v */
    public b clone() {
        b bVar = new b();
        if (this.f15840h == 0) {
            return bVar;
        }
        j jVar = new j(this.f15839g);
        bVar.f15839g = jVar;
        jVar.f15861g = jVar;
        jVar.f15860f = jVar;
        j jVar2 = this.f15839g;
        while (true) {
            jVar2 = jVar2.f15860f;
            if (jVar2 == this.f15839g) {
                bVar.f15840h = this.f15840h;
                return bVar;
            }
            bVar.f15839g.f15861g.b(new j(jVar2));
        }
    }

    public long w() {
        long j2 = this.f15840h;
        if (j2 == 0) {
            return 0L;
        }
        j jVar = this.f15839g.f15861g;
        return (jVar.f15858c >= 2048 || !jVar.f15859e) ? j2 : j2 - (r3 - jVar.b);
    }

    public boolean x() {
        return this.f15840h == 0;
    }

    public byte y() {
        long j2 = this.f15840h;
        if (j2 != 0) {
            j jVar = this.f15839g;
            int i2 = jVar.b;
            int i3 = jVar.f15858c;
            int i4 = i2 + 1;
            byte b = jVar.a[i2];
            this.f15840h = j2 - 1;
            if (i4 == i3) {
                this.f15839g = jVar.e();
                k.b(jVar);
            } else {
                jVar.b = i4;
            }
            return b;
        }
        throw new IllegalStateException("size == 0");
    }

    public e z() {
        return new e(b());
    }
}
