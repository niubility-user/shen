package com.tencent.mapsdk.internal;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public final class m {
    private ByteBuffer a;
    public String b = "GBK";

    /* loaded from: classes9.dex */
    public static class a {
        public byte a;
        public int b;

        public void a() {
            this.a = (byte) 0;
            this.b = 0;
        }
    }

    public m() {
    }

    public m(ByteBuffer byteBuffer) {
        this.a = byteBuffer;
    }

    public m(byte[] bArr) {
        this.a = ByteBuffer.wrap(bArr);
    }

    public m(byte[] bArr, int i2) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        this.a = wrap;
        wrap.position(i2);
    }

    private int a(a aVar) {
        return a(aVar, this.a.duplicate());
    }

    public static int a(a aVar, ByteBuffer byteBuffer) {
        byte b = byteBuffer.get();
        aVar.a = (byte) (b & 15);
        int i2 = (b & 240) >> 4;
        aVar.b = i2;
        if (i2 == 15) {
            aVar.b = byteBuffer.get() & 255;
            return 2;
        }
        return 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <K, V> Map<K, V> a(Map<K, V> map, Map<K, V> map2, int i2, boolean z) {
        if (map2 == null || map2.isEmpty()) {
            return new HashMap();
        }
        Map.Entry<K, V> next = map2.entrySet().iterator().next();
        K key = next.getKey();
        V value = next.getValue();
        if (b(i2)) {
            a aVar = new a();
            b(aVar);
            if (aVar.a != 8) {
                throw new j("type mismatch.");
            }
            int a2 = a(0, 0, true);
            if (a2 < 0) {
                throw new j("size invalid: " + a2);
            }
            for (int i3 = 0; i3 < a2; i3++) {
                map.put(a((m) key, 0, true), a((m) value, 1, true));
            }
        } else if (z) {
            throw new j("require field not exist.");
        }
        return map;
    }

    private void a(byte b) {
        int i2;
        int i3 = 0;
        switch (b) {
            case 0:
                a(1);
                return;
            case 1:
                a(2);
                return;
            case 2:
            case 4:
                a(4);
                return;
            case 3:
            case 5:
                a(8);
                return;
            case 6:
                i2 = this.a.get();
                if (i2 < 0) {
                    i2 += 256;
                    break;
                }
                break;
            case 7:
                i2 = this.a.getInt();
                break;
            case 8:
                int a2 = a(0, 0, true);
                while (i3 < a2 * 2) {
                    b();
                    i3++;
                }
                return;
            case 9:
                int a3 = a(0, 0, true);
                while (i3 < a3) {
                    b();
                    i3++;
                }
                return;
            case 10:
                c();
                return;
            case 11:
            case 12:
                return;
            case 13:
                a aVar = new a();
                b(aVar);
                if (aVar.a != 0) {
                    throw new j("skipField with invalid type, type value: " + ((int) b) + ", " + ((int) aVar.a));
                }
                i2 = a(0, 0, true);
                break;
            default:
                throw new j("invalid type.");
        }
        a(i2);
    }

    private void a(int i2) {
        ByteBuffer byteBuffer = this.a;
        byteBuffer.position(byteBuffer.position() + i2);
    }

    private void b() {
        a aVar = new a();
        b(aVar);
        a(aVar.a);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T> T[] b(T t, int i2, boolean z) {
        if (!b(i2)) {
            if (z) {
                throw new j("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        b(aVar);
        if (aVar.a == 9) {
            int a2 = a(0, 0, true);
            if (a2 < 0) {
                throw new j("size invalid: " + a2);
            }
            T[] tArr = (T[]) ((Object[]) Array.newInstance(t.getClass(), a2));
            for (int i3 = 0; i3 < a2; i3++) {
                tArr[i3] = a((m) t, 0, true);
            }
            return tArr;
        }
        throw new j("type mismatch.");
    }

    public byte a(byte b, int i2, boolean z) {
        if (!b(i2)) {
            if (z) {
                throw new j("require field not exist.");
            }
            return b;
        }
        a aVar = new a();
        b(aVar);
        byte b2 = aVar.a;
        if (b2 != 0) {
            if (b2 == 12) {
                return (byte) 0;
            }
            throw new j("type mismatch.");
        }
        return this.a.get();
    }

    public double a(double d, int i2, boolean z) {
        if (!b(i2)) {
            if (z) {
                throw new j("require field not exist.");
            }
            return d;
        }
        a aVar = new a();
        b(aVar);
        byte b = aVar.a;
        if (b != 4) {
            if (b != 5) {
                if (b == 12) {
                    return 0.0d;
                }
                throw new j("type mismatch.");
            }
            return this.a.getDouble();
        }
        return this.a.getFloat();
    }

    public float a(float f2, int i2, boolean z) {
        if (!b(i2)) {
            if (z) {
                throw new j("require field not exist.");
            }
            return f2;
        }
        a aVar = new a();
        b(aVar);
        byte b = aVar.a;
        if (b != 4) {
            if (b == 12) {
                return 0.0f;
            }
            throw new j("type mismatch.");
        }
        return this.a.getFloat();
    }

    public int a(int i2, int i3, boolean z) {
        if (!b(i3)) {
            if (z) {
                throw new j("require field not exist.");
            }
            return i2;
        }
        a aVar = new a();
        b(aVar);
        byte b = aVar.a;
        if (b != 0) {
            if (b != 1) {
                if (b != 2) {
                    if (b == 12) {
                        return 0;
                    }
                    throw new j("type mismatch.");
                }
                return this.a.getInt();
            }
            return this.a.getShort();
        }
        return this.a.get();
    }

    public int a(String str) {
        this.b = str;
        return 0;
    }

    public long a(long j2, int i2, boolean z) {
        int i3;
        if (!b(i2)) {
            if (z) {
                throw new j("require field not exist.");
            }
            return j2;
        }
        a aVar = new a();
        b(aVar);
        byte b = aVar.a;
        if (b != 12) {
            if (b == 0) {
                i3 = this.a.get();
            } else if (b == 1) {
                i3 = this.a.getShort();
            } else if (b != 2) {
                if (b == 3) {
                    return this.a.getLong();
                }
                throw new j("type mismatch.");
            } else {
                i3 = this.a.getInt();
            }
            return i3;
        }
        return 0L;
    }

    public p a(p pVar, int i2, boolean z) {
        if (!b(i2)) {
            if (z) {
                throw new j("require field not exist.");
            }
            return null;
        }
        try {
            p newInit = pVar.newInit();
            a aVar = new a();
            b(aVar);
            if (aVar.a == 10) {
                newInit.readFrom(this);
                c();
                return newInit;
            }
            throw new j("type mismatch.");
        } catch (Exception e2) {
            throw new j(e2.getMessage());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> Object a(T t, int i2, boolean z) {
        if (t instanceof Byte) {
            return Byte.valueOf(a((byte) 0, i2, z));
        }
        if (t instanceof Boolean) {
            return Boolean.valueOf(a(false, i2, z));
        }
        if (t instanceof Short) {
            return Short.valueOf(a((short) 0, i2, z));
        }
        if (t instanceof Integer) {
            return Integer.valueOf(a(0, i2, z));
        }
        if (t instanceof Long) {
            return Long.valueOf(a(0L, i2, z));
        }
        if (t instanceof Float) {
            return Float.valueOf(a(0.0f, i2, z));
        }
        if (t instanceof Double) {
            return Double.valueOf(a(0.0d, i2, z));
        }
        if (t instanceof String) {
            return b(i2, z);
        }
        if (t instanceof Map) {
            return a((Map) t, i2, z);
        }
        if (t instanceof List) {
            return a((List) t, i2, z);
        }
        if (t instanceof p) {
            return b((p) t, i2, z);
        }
        if (t.getClass().isArray()) {
            return ((t instanceof byte[]) || (t instanceof Byte[])) ? a((byte[]) null, i2, z) : t instanceof boolean[] ? a((boolean[]) null, i2, z) : t instanceof short[] ? a((short[]) null, i2, z) : t instanceof int[] ? a((int[]) null, i2, z) : t instanceof long[] ? a((long[]) null, i2, z) : t instanceof float[] ? a((float[]) null, i2, z) : t instanceof double[] ? a((double[]) null, i2, z) : a((Object[]) t, i2, z);
        }
        throw new j("read object error: unsupport type.");
    }

    public String a(String str, int i2, boolean z) {
        String str2;
        if (!b(i2)) {
            if (z) {
                throw new j("require field not exist.");
            }
            return str;
        }
        a aVar = new a();
        b(aVar);
        byte b = aVar.a;
        if (b == 6) {
            int i3 = this.a.get();
            if (i3 < 0) {
                i3 += 256;
            }
            byte[] bArr = new byte[i3];
            this.a.get(bArr);
            try {
                str2 = new String(bArr, this.b);
            } catch (UnsupportedEncodingException unused) {
                str2 = new String(bArr);
            }
        } else if (b != 7) {
            throw new j("type mismatch.");
        } else {
            int i4 = this.a.getInt();
            if (i4 > 104857600 || i4 < 0 || i4 > this.a.capacity()) {
                throw new j("String too long: " + i4);
            }
            byte[] bArr2 = new byte[i4];
            this.a.get(bArr2);
            try {
                str2 = new String(bArr2, this.b);
            } catch (UnsupportedEncodingException unused2) {
                str2 = new String(bArr2);
            }
        }
        return str2;
    }

    public ByteBuffer a() {
        return this.a;
    }

    public <K, V> HashMap<K, V> a(Map<K, V> map, int i2, boolean z) {
        return (HashMap) a(new HashMap(), map, i2, z);
    }

    public List a(int i2, boolean z) {
        int i3;
        ArrayList arrayList = new ArrayList();
        if (b(i2)) {
            a aVar = new a();
            b(aVar);
            if (aVar.a != 9) {
                throw new j("type mismatch.");
            }
            int a2 = a(0, 0, true);
            if (a2 < 0) {
                throw new j("size invalid: " + a2);
            }
            for (int i4 = 0; i4 < a2; i4++) {
                a aVar2 = new a();
                b(aVar2);
                switch (aVar2.a) {
                    case 0:
                        a(1);
                        continue;
                    case 1:
                        i3 = 2;
                        break;
                    case 2:
                    case 4:
                        a(4);
                        continue;
                    case 3:
                    case 5:
                        a(8);
                        continue;
                    case 6:
                        i3 = this.a.get();
                        if (i3 < 0) {
                            i3 += 256;
                            break;
                        }
                        break;
                    case 7:
                        i3 = this.a.getInt();
                        break;
                    case 8:
                    case 9:
                    case 10:
                        try {
                            p pVar = (p) Class.forName(p.class.getName()).getConstructor(new Class[0]).newInstance(new Object[0]);
                            pVar.readFrom(this);
                            c();
                            arrayList.add(pVar);
                            continue;
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            throw new j("type mismatch." + e2);
                        }
                    case 11:
                    default:
                        throw new j("type mismatch.");
                    case 12:
                        arrayList.add(new Integer(0));
                        continue;
                }
                a(i3);
            }
        } else if (z) {
            throw new j("require field not exist.");
        }
        return arrayList;
    }

    public <T> List<T> a(List<T> list, int i2, boolean z) {
        if (list == null || list.isEmpty()) {
            return new ArrayList();
        }
        Object[] b = b((m) list.get(0), i2, z);
        if (b == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : b) {
            arrayList.add(obj);
        }
        return arrayList;
    }

    public short a(short s, int i2, boolean z) {
        if (!b(i2)) {
            if (z) {
                throw new j("require field not exist.");
            }
            return s;
        }
        a aVar = new a();
        b(aVar);
        byte b = aVar.a;
        if (b != 0) {
            if (b != 1) {
                if (b == 12) {
                    return (short) 0;
                }
                throw new j("type mismatch.");
            }
            return this.a.getShort();
        }
        return this.a.get();
    }

    public void a(byte[] bArr) {
        b(bArr);
    }

    public boolean a(boolean z, int i2, boolean z2) {
        return a((byte) 0, i2, z2) != 0;
    }

    public byte[] a(byte[] bArr, int i2, boolean z) {
        if (!b(i2)) {
            if (z) {
                throw new j("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        b(aVar);
        byte b = aVar.a;
        if (b == 9) {
            int a2 = a(0, 0, true);
            if (a2 < 0 || a2 > this.a.capacity()) {
                throw new j("size invalid: " + a2);
            }
            byte[] bArr2 = new byte[a2];
            for (int i3 = 0; i3 < a2; i3++) {
                bArr2[i3] = a(bArr2[0], 0, true);
            }
            return bArr2;
        } else if (b == 13) {
            a aVar2 = new a();
            b(aVar2);
            if (aVar2.a != 0) {
                throw new j("type mismatch, tag: " + i2 + ", type: " + ((int) aVar.a) + ", " + ((int) aVar2.a));
            }
            int a3 = a(0, 0, true);
            if (a3 >= 0 && a3 <= this.a.capacity()) {
                byte[] bArr3 = new byte[a3];
                this.a.get(bArr3);
                return bArr3;
            }
            throw new j("invalid size, tag: " + i2 + ", type: " + ((int) aVar.a) + ", " + ((int) aVar2.a) + ", size: " + a3);
        } else {
            throw new j("type mismatch.");
        }
    }

    public double[] a(double[] dArr, int i2, boolean z) {
        if (!b(i2)) {
            if (z) {
                throw new j("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        b(aVar);
        if (aVar.a == 9) {
            int a2 = a(0, 0, true);
            if (a2 < 0) {
                throw new j("size invalid: " + a2);
            }
            double[] dArr2 = new double[a2];
            for (int i3 = 0; i3 < a2; i3++) {
                dArr2[i3] = a(dArr2[0], 0, true);
            }
            return dArr2;
        }
        throw new j("type mismatch.");
    }

    public float[] a(float[] fArr, int i2, boolean z) {
        if (!b(i2)) {
            if (z) {
                throw new j("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        b(aVar);
        if (aVar.a == 9) {
            int a2 = a(0, 0, true);
            if (a2 < 0) {
                throw new j("size invalid: " + a2);
            }
            float[] fArr2 = new float[a2];
            for (int i3 = 0; i3 < a2; i3++) {
                fArr2[i3] = a(fArr2[0], 0, true);
            }
            return fArr2;
        }
        throw new j("type mismatch.");
    }

    public int[] a(int[] iArr, int i2, boolean z) {
        if (!b(i2)) {
            if (z) {
                throw new j("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        b(aVar);
        if (aVar.a == 9) {
            int a2 = a(0, 0, true);
            if (a2 < 0) {
                throw new j("size invalid: " + a2);
            }
            int[] iArr2 = new int[a2];
            for (int i3 = 0; i3 < a2; i3++) {
                iArr2[i3] = a(iArr2[0], 0, true);
            }
            return iArr2;
        }
        throw new j("type mismatch.");
    }

    public long[] a(long[] jArr, int i2, boolean z) {
        if (!b(i2)) {
            if (z) {
                throw new j("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        b(aVar);
        if (aVar.a == 9) {
            int a2 = a(0, 0, true);
            if (a2 < 0) {
                throw new j("size invalid: " + a2);
            }
            long[] jArr2 = new long[a2];
            for (int i3 = 0; i3 < a2; i3++) {
                jArr2[i3] = a(jArr2[0], 0, true);
            }
            return jArr2;
        }
        throw new j("type mismatch.");
    }

    public p[] a(p[] pVarArr, int i2, boolean z) {
        return (p[]) a((Object[]) pVarArr, i2, z);
    }

    public <T> T[] a(T[] tArr, int i2, boolean z) {
        if (tArr == null || tArr.length == 0) {
            throw new j("unable to get type of key and value.");
        }
        return (T[]) b((m) tArr[0], i2, z);
    }

    public String[] a(String[] strArr, int i2, boolean z) {
        return (String[]) a((Object[]) strArr, i2, z);
    }

    public short[] a(short[] sArr, int i2, boolean z) {
        if (!b(i2)) {
            if (z) {
                throw new j("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        b(aVar);
        if (aVar.a == 9) {
            int a2 = a(0, 0, true);
            if (a2 < 0) {
                throw new j("size invalid: " + a2);
            }
            short[] sArr2 = new short[a2];
            for (int i3 = 0; i3 < a2; i3++) {
                sArr2[i3] = a(sArr2[0], 0, true);
            }
            return sArr2;
        }
        throw new j("type mismatch.");
    }

    public boolean[] a(boolean[] zArr, int i2, boolean z) {
        if (!b(i2)) {
            if (z) {
                throw new j("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        b(aVar);
        if (aVar.a == 9) {
            int a2 = a(0, 0, true);
            if (a2 < 0) {
                throw new j("size invalid: " + a2);
            }
            boolean[] zArr2 = new boolean[a2];
            for (int i3 = 0; i3 < a2; i3++) {
                zArr2[i3] = a(zArr2[0], 0, true);
            }
            return zArr2;
        }
        throw new j("type mismatch.");
    }

    public p b(p pVar, int i2, boolean z) {
        if (!b(i2)) {
            if (z) {
                throw new j("require field not exist.");
            }
            return null;
        }
        try {
            p pVar2 = (p) pVar.getClass().newInstance();
            a aVar = new a();
            b(aVar);
            if (aVar.a == 10) {
                pVar2.readFrom(this);
                c();
                return pVar2;
            }
            throw new j("type mismatch.");
        } catch (Exception e2) {
            throw new j(e2.getMessage());
        }
    }

    public String b(int i2, boolean z) {
        if (!b(i2)) {
            if (z) {
                throw new j("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        b(aVar);
        byte b = aVar.a;
        if (b == 6) {
            int i3 = this.a.get();
            if (i3 < 0) {
                i3 += 256;
            }
            byte[] bArr = new byte[i3];
            this.a.get(bArr);
            try {
                return new String(bArr, this.b);
            } catch (UnsupportedEncodingException unused) {
                return new String(bArr);
            }
        } else if (b == 7) {
            int i4 = this.a.getInt();
            if (i4 > 104857600 || i4 < 0 || i4 > this.a.capacity()) {
                throw new j("String too long: " + i4);
            }
            byte[] bArr2 = new byte[i4];
            this.a.get(bArr2);
            try {
                return new String(bArr2, this.b);
            } catch (UnsupportedEncodingException unused2) {
                return new String(bArr2);
            }
        } else {
            throw new j("type mismatch.");
        }
    }

    public String b(String str, int i2, boolean z) {
        if (!b(i2)) {
            if (z) {
                throw new j("require field not exist.");
            }
            return str;
        }
        a aVar = new a();
        b(aVar);
        byte b = aVar.a;
        if (b == 6) {
            int i3 = this.a.get();
            if (i3 < 0) {
                i3 += 256;
            }
            byte[] bArr = new byte[i3];
            this.a.get(bArr);
            return i.a(bArr);
        } else if (b == 7) {
            int i4 = this.a.getInt();
            if (i4 <= 104857600 && i4 >= 0 && i4 <= this.a.capacity()) {
                byte[] bArr2 = new byte[i4];
                this.a.get(bArr2);
                return i.a(bArr2);
            }
            throw new j("String too long: " + i4);
        } else {
            throw new j("type mismatch.");
        }
    }

    public void b(a aVar) {
        a(aVar, this.a);
    }

    public void b(byte[] bArr) {
        this.a = ByteBuffer.wrap(bArr);
    }

    public boolean b(int i2) {
        int i3;
        try {
            a aVar = new a();
            while (true) {
                int a2 = a(aVar);
                i3 = aVar.b;
                if (i2 <= i3 || aVar.a == 11) {
                    break;
                }
                a(a2);
                a(aVar.a);
            }
            return aVar.a != 11 && i2 == i3;
        } catch (j | BufferUnderflowException unused) {
            return false;
        }
    }

    public Map<String, String> c(int i2, boolean z) {
        HashMap hashMap = new HashMap();
        if (b(i2)) {
            a aVar = new a();
            b(aVar);
            if (aVar.a != 8) {
                throw new j("type mismatch.");
            }
            int a2 = a(0, 0, true);
            if (a2 < 0) {
                throw new j("size invalid: " + a2);
            }
            for (int i3 = 0; i3 < a2; i3++) {
                hashMap.put(b(0, true), b(1, true));
            }
        } else if (z) {
            throw new j("require field not exist.");
        }
        return hashMap;
    }

    public void c() {
        a aVar = new a();
        do {
            b(aVar);
            a(aVar.a);
        } while (aVar.a != 11);
    }
}
