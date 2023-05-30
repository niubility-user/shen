package com.tencent.mapsdk.internal;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class n {
    private ByteBuffer a;
    private r b;

    /* renamed from: c */
    public String f16883c;

    public n() {
        this(128);
    }

    public n(int i2) {
        this.f16883c = "GBK";
        this.a = ByteBuffer.allocate(i2);
    }

    public n(ByteBuffer byteBuffer) {
        this.f16883c = "GBK";
        this.a = byteBuffer;
    }

    private void b(Object[] objArr, int i2) {
        a(8);
        b((byte) 9, i2);
        a(objArr.length, 0);
        for (Object obj : objArr) {
            a(obj, 0);
        }
    }

    public int a(String str) {
        this.f16883c = str;
        return 0;
    }

    public ByteBuffer a() {
        return this.a;
    }

    public void a(byte b, int i2) {
        a(3);
        if (b == 0) {
            b((byte) 12, i2);
            return;
        }
        b((byte) 0, i2);
        this.a.put(b);
    }

    public void a(double d, int i2) {
        a(10);
        b((byte) 5, i2);
        this.a.putDouble(d);
    }

    public void a(float f2, int i2) {
        a(6);
        b((byte) 4, i2);
        this.a.putFloat(f2);
    }

    public void a(int i2) {
        if (this.a.remaining() < i2) {
            int capacity = (this.a.capacity() + i2) * 2;
            try {
                ByteBuffer allocate = ByteBuffer.allocate(capacity);
                allocate.put(this.a.array(), 0, this.a.position());
                this.a = allocate;
            } catch (IllegalArgumentException e2) {
                r rVar = this.b;
                if (rVar != null) {
                    rVar.a(e2, this.a, i2, capacity);
                }
                throw e2;
            }
        }
    }

    public void a(int i2, int i3) {
        a(6);
        if (i2 >= -32768 && i2 <= 32767) {
            a((short) i2, i3);
            return;
        }
        b((byte) 2, i3);
        this.a.putInt(i2);
    }

    public void a(long j2, int i2) {
        a(10);
        if (j2 >= -2147483648L && j2 <= 2147483647L) {
            a((int) j2, i2);
            return;
        }
        b((byte) 3, i2);
        this.a.putLong(j2);
    }

    public void a(p pVar, int i2) {
        a(2);
        b((byte) 10, i2);
        pVar.writeTo(this);
        a(2);
        b((byte) 11, 0);
    }

    public void a(r rVar) {
        this.b = rVar;
    }

    public void a(Boolean bool, int i2) {
        a(bool.booleanValue(), i2);
    }

    public void a(Byte b, int i2) {
        a(b.byteValue(), i2);
    }

    public void a(Double d, int i2) {
        a(d.doubleValue(), i2);
    }

    public void a(Float f2, int i2) {
        a(f2.floatValue(), i2);
    }

    public void a(Integer num, int i2) {
        a(num.intValue(), i2);
    }

    public void a(Long l2, int i2) {
        a(l2.longValue(), i2);
    }

    public void a(Object obj, int i2) {
        Object obj2;
        if (obj instanceof Byte) {
            a(((Byte) obj).byteValue(), i2);
        } else if (obj instanceof Boolean) {
            a(((Boolean) obj).booleanValue(), i2);
        } else if (obj instanceof Short) {
            a(((Short) obj).shortValue(), i2);
        } else if (obj instanceof Integer) {
            a(((Integer) obj).intValue(), i2);
        } else if (obj instanceof Long) {
            a(((Long) obj).longValue(), i2);
        } else if (obj instanceof Float) {
            a(((Float) obj).floatValue(), i2);
        } else if (obj instanceof Double) {
            a(((Double) obj).doubleValue(), i2);
        } else if (obj instanceof String) {
            a((String) obj, i2);
        } else if (obj instanceof Map) {
            a((Map) obj, i2);
        } else {
            if (obj instanceof List) {
                obj2 = (List) obj;
            } else if (obj instanceof p) {
                a((p) obj, i2);
                return;
            } else if (obj instanceof byte[]) {
                a((byte[]) obj, i2);
                return;
            } else if (obj instanceof boolean[]) {
                a((boolean[]) obj, i2);
                return;
            } else if (obj instanceof short[]) {
                a((short[]) obj, i2);
                return;
            } else if (obj instanceof int[]) {
                a((int[]) obj, i2);
                return;
            } else if (obj instanceof long[]) {
                a((long[]) obj, i2);
                return;
            } else if (obj instanceof float[]) {
                a((float[]) obj, i2);
                return;
            } else if (obj instanceof double[]) {
                a((double[]) obj, i2);
                return;
            } else if (obj.getClass().isArray()) {
                b((Object[]) obj, i2);
                return;
            } else if (!(obj instanceof Collection)) {
                throw new l("write object error: unsupport type. " + obj.getClass());
            } else {
                obj2 = (Collection) obj;
            }
            a((Collection) obj2, i2);
        }
    }

    public void a(Short sh, int i2) {
        a(sh.shortValue(), i2);
    }

    public void a(String str, int i2) {
        byte[] bytes;
        try {
            bytes = str.getBytes(this.f16883c);
        } catch (UnsupportedEncodingException unused) {
            bytes = str.getBytes();
        }
        a(bytes.length + 10);
        if (bytes.length > 255) {
            b((byte) 7, i2);
            this.a.putInt(bytes.length);
        } else {
            b((byte) 6, i2);
            this.a.put((byte) bytes.length);
        }
        this.a.put(bytes);
    }

    public <T> void a(Collection<T> collection, int i2) {
        a(8);
        b((byte) 9, i2);
        a(collection == null ? 0 : collection.size(), 0);
        if (collection != null) {
            Iterator<T> it = collection.iterator();
            while (it.hasNext()) {
                a(it.next(), 0);
            }
        }
    }

    public <K, V> void a(Map<K, V> map, int i2) {
        a(8);
        b((byte) 8, i2);
        a(map == null ? 0 : map.size(), 0);
        if (map != null) {
            for (Map.Entry<K, V> entry : map.entrySet()) {
                a(entry.getKey(), 0);
                a(entry.getValue(), 1);
            }
        }
    }

    public void a(short s, int i2) {
        a(4);
        if (s >= -128 && s <= 127) {
            a((byte) s, i2);
            return;
        }
        b((byte) 1, i2);
        this.a.putShort(s);
    }

    public void a(boolean z, int i2) {
        a(z ? (byte) 1 : (byte) 0, i2);
    }

    public void a(byte[] bArr, int i2) {
        a(bArr.length + 8);
        b((byte) 13, i2);
        b((byte) 0, 0);
        a(bArr.length, 0);
        this.a.put(bArr);
    }

    public void a(double[] dArr, int i2) {
        a(8);
        b((byte) 9, i2);
        a(dArr.length, 0);
        for (double d : dArr) {
            a(d, 0);
        }
    }

    public void a(float[] fArr, int i2) {
        a(8);
        b((byte) 9, i2);
        a(fArr.length, 0);
        for (float f2 : fArr) {
            a(f2, 0);
        }
    }

    public void a(int[] iArr, int i2) {
        a(8);
        b((byte) 9, i2);
        a(iArr.length, 0);
        for (int i3 : iArr) {
            a(i3, 0);
        }
    }

    public void a(long[] jArr, int i2) {
        a(8);
        b((byte) 9, i2);
        a(jArr.length, 0);
        for (long j2 : jArr) {
            a(j2, 0);
        }
    }

    public <T> void a(T[] tArr, int i2) {
        b(tArr, i2);
    }

    public void a(short[] sArr, int i2) {
        a(8);
        b((byte) 9, i2);
        a(sArr.length, 0);
        for (short s : sArr) {
            a(s, 0);
        }
    }

    public void a(boolean[] zArr, int i2) {
        a(8);
        b((byte) 9, i2);
        a(zArr.length, 0);
        for (boolean z : zArr) {
            a(z, 0);
        }
    }

    public r b() {
        return this.b;
    }

    public void b(byte b, int i2) {
        if (i2 < 15) {
            this.a.put((byte) (b | (i2 << 4)));
        } else if (i2 < 256) {
            this.a.put((byte) (b | 240));
            this.a.put((byte) i2);
        } else {
            throw new l("tag is too large: " + i2);
        }
    }

    public void b(String str, int i2) {
        a(str.length() + 10);
        byte[] b = i.b(str);
        if (b.length > 255) {
            b((byte) 7, i2);
            this.a.putInt(b.length);
        } else {
            b((byte) 6, i2);
            this.a.put((byte) b.length);
        }
        this.a.put(b);
    }

    public void c(String str, int i2) {
        byte[] b = i.b(str);
        a(b.length + 10);
        if (b.length > 255) {
            b((byte) 7, i2);
            this.a.putInt(b.length);
        } else {
            b((byte) 6, i2);
            this.a.put((byte) b.length);
        }
        this.a.put(b);
    }

    public byte[] c() {
        byte[] bArr = new byte[this.a.position()];
        System.arraycopy(this.a.array(), 0, bArr, 0, this.a.position());
        return bArr;
    }
}
