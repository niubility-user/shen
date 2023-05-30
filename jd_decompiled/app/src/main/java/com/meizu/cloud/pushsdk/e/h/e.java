package com.meizu.cloud.pushsdk.e.h;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes14.dex */
public class e implements Serializable, Comparable<e> {
    private static final long serialVersionUID = 1;

    /* renamed from: c  reason: collision with root package name */
    final byte[] f15843c;

    /* renamed from: g  reason: collision with root package name */
    transient int f15844g;

    /* renamed from: h  reason: collision with root package name */
    transient String f15845h;

    /* renamed from: i  reason: collision with root package name */
    static final char[] f15842i = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static final e b = a(new byte[0]);

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(byte[] bArr) {
        this.f15843c = bArr;
    }

    public static e a(InputStream inputStream, int i2) throws IOException {
        if (inputStream != null) {
            if (i2 < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + i2);
            }
            byte[] bArr = new byte[i2];
            int i3 = 0;
            while (i3 < i2) {
                int read = inputStream.read(bArr, i3, i2 - i3);
                if (read == -1) {
                    throw new EOFException();
                }
                i3 += read;
            }
            return new e(bArr);
        }
        throw new IllegalArgumentException("in == null");
    }

    public static e a(byte... bArr) {
        if (bArr != null) {
            return new e((byte[]) bArr.clone());
        }
        throw new IllegalArgumentException("data == null");
    }

    public static e b(String str) {
        if (str != null) {
            e eVar = new e(str.getBytes(o.a));
            eVar.f15845h = str;
            return eVar;
        }
        throw new IllegalArgumentException("s == null");
    }

    private e c(String str) {
        try {
            return a(MessageDigest.getInstance(str).digest(this.f15843c));
        } catch (NoSuchAlgorithmException e2) {
            throw new AssertionError(e2);
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        e a = a(objectInputStream, objectInputStream.readInt());
        try {
            Field declaredField = e.class.getDeclaredField("c");
            declaredField.setAccessible(true);
            declaredField.set(this, a.f15843c);
        } catch (IllegalAccessException unused) {
            throw new AssertionError();
        } catch (NoSuchFieldException unused2) {
            throw new AssertionError();
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.f15843c.length);
        objectOutputStream.write(this.f15843c);
    }

    public byte a(int i2) {
        return this.f15843c[i2];
    }

    @Override // java.lang.Comparable
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public int compareTo(e eVar) {
        int c2 = c();
        int c3 = eVar.c();
        int min = Math.min(c2, c3);
        for (int i2 = 0; i2 < min; i2++) {
            int a = a(i2) & 255;
            int a2 = eVar.a(i2) & 255;
            if (a != a2) {
                return a < a2 ? -1 : 1;
            }
        }
        if (c2 == c3) {
            return 0;
        }
        return c2 < c3 ? -1 : 1;
    }

    public String a() {
        byte[] bArr = this.f15843c;
        char[] cArr = new char[bArr.length * 2];
        int i2 = 0;
        for (byte b2 : bArr) {
            int i3 = i2 + 1;
            char[] cArr2 = f15842i;
            cArr[i2] = cArr2[(b2 >> 4) & 15];
            i2 = i3 + 1;
            cArr[i3] = cArr2[b2 & 15];
        }
        return new String(cArr);
    }

    public boolean a(int i2, byte[] bArr, int i3, int i4) {
        byte[] bArr2 = this.f15843c;
        return i2 <= bArr2.length - i4 && i3 <= bArr.length - i4 && o.c(bArr2, i2, bArr, i3, i4);
    }

    public e b() {
        return c(MessageDigestAlgorithms.MD5);
    }

    public int c() {
        return this.f15843c.length;
    }

    public String d() {
        String str = this.f15845h;
        if (str != null) {
            return str;
        }
        String str2 = new String(this.f15843c, o.a);
        this.f15845h = str2;
        return str2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(b bVar) {
        byte[] bArr = this.f15843c;
        bVar.q(bArr, 0, bArr.length);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof e) {
            e eVar = (e) obj;
            int c2 = eVar.c();
            byte[] bArr = this.f15843c;
            if (c2 == bArr.length && eVar.a(0, bArr, 0, bArr.length)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i2 = this.f15844g;
        if (i2 != 0) {
            return i2;
        }
        int hashCode = Arrays.hashCode(this.f15843c);
        this.f15844g = hashCode;
        return hashCode;
    }

    public String toString() {
        byte[] bArr = this.f15843c;
        return bArr.length == 0 ? "ByteString[size=0]" : bArr.length <= 16 ? String.format("ByteString[size=%s data=%s]", Integer.valueOf(bArr.length), a()) : String.format("ByteString[size=%s md5=%s]", Integer.valueOf(bArr.length), b().a());
    }
}
