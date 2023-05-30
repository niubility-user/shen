package com.tencent.mapsdk.internal;

import com.jd.dynamic.DYConstants;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public final class k {
    private StringBuilder a;
    private int b;

    public k(StringBuilder sb) {
        this.b = 0;
        this.a = sb;
    }

    public k(StringBuilder sb, int i2) {
        this.b = 0;
        this.a = sb;
        this.b = i2;
    }

    private void a(String str) {
        for (int i2 = 0; i2 < this.b; i2++) {
            this.a.append('\t');
        }
        if (str != null) {
            StringBuilder sb = this.a;
            sb.append(str);
            sb.append(": ");
        }
    }

    public k a(byte b, String str) {
        a(str);
        StringBuilder sb = this.a;
        sb.append((int) b);
        sb.append('\n');
        return this;
    }

    public k a(byte b, boolean z) {
        this.a.append((int) b);
        if (z) {
            this.a.append("|");
        }
        return this;
    }

    public k a(char c2, String str) {
        a(str);
        StringBuilder sb = this.a;
        sb.append(c2);
        sb.append('\n');
        return this;
    }

    public k a(char c2, boolean z) {
        this.a.append(c2);
        if (z) {
            this.a.append("|");
        }
        return this;
    }

    public k a(double d, String str) {
        a(str);
        StringBuilder sb = this.a;
        sb.append(d);
        sb.append('\n');
        return this;
    }

    public k a(double d, boolean z) {
        this.a.append(d);
        if (z) {
            this.a.append("|");
        }
        return this;
    }

    public k a(float f2, String str) {
        a(str);
        StringBuilder sb = this.a;
        sb.append(f2);
        sb.append('\n');
        return this;
    }

    public k a(float f2, boolean z) {
        this.a.append(f2);
        if (z) {
            this.a.append("|");
        }
        return this;
    }

    public k a(int i2, String str) {
        a(str);
        StringBuilder sb = this.a;
        sb.append(i2);
        sb.append('\n');
        return this;
    }

    public k a(int i2, boolean z) {
        this.a.append(i2);
        if (z) {
            this.a.append("|");
        }
        return this;
    }

    public k a(long j2, String str) {
        a(str);
        StringBuilder sb = this.a;
        sb.append(j2);
        sb.append('\n');
        return this;
    }

    public k a(long j2, boolean z) {
        this.a.append(j2);
        if (z) {
            this.a.append("|");
        }
        return this;
    }

    public k a(p pVar, String str) {
        a('{', str);
        if (pVar == null) {
            StringBuilder sb = this.a;
            sb.append('\t');
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            pVar.display(this.a, this.b + 1);
        }
        a('}', (String) null);
        return this;
    }

    public k a(p pVar, boolean z) {
        this.a.append("{");
        if (pVar == null) {
            StringBuilder sb = this.a;
            sb.append('\t');
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            pVar.displaySimple(this.a, this.b + 1);
        }
        this.a.append("}");
        if (z) {
            this.a.append("|");
        }
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> k a(T t, String str) {
        if (t == null) {
            StringBuilder sb = this.a;
            sb.append(DYConstants.DY_NULL_STR);
            sb.append('\n');
        } else if (t instanceof Byte) {
            a(((Byte) t).byteValue(), str);
        } else if (t instanceof Boolean) {
            a(((Boolean) t).booleanValue(), str);
        } else if (t instanceof Short) {
            a(((Short) t).shortValue(), str);
        } else if (t instanceof Integer) {
            a(((Integer) t).intValue(), str);
        } else if (t instanceof Long) {
            a(((Long) t).longValue(), str);
        } else if (t instanceof Float) {
            a(((Float) t).floatValue(), str);
        } else if (t instanceof Double) {
            a(((Double) t).doubleValue(), str);
        } else if (t instanceof String) {
            a((String) t, str);
        } else if (t instanceof Map) {
            a((Map) t, str);
        } else if (t instanceof List) {
            a((Collection) ((List) t), str);
        } else if (t instanceof p) {
            a((p) t, str);
        } else if (t instanceof byte[]) {
            a((byte[]) t, str);
        } else if (t instanceof boolean[]) {
            a((k) ((boolean[]) t), str);
        } else if (t instanceof short[]) {
            a((short[]) t, str);
        } else if (t instanceof int[]) {
            a((int[]) t, str);
        } else if (t instanceof long[]) {
            a((long[]) t, str);
        } else if (t instanceof float[]) {
            a((float[]) t, str);
        } else if (t instanceof double[]) {
            a((double[]) t, str);
        } else if (!t.getClass().isArray()) {
            throw new l("write object error: unsupport type.");
        } else {
            a((Object[]) t, str);
        }
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> k a(T t, boolean z) {
        if (t == null) {
            StringBuilder sb = this.a;
            sb.append(DYConstants.DY_NULL_STR);
            sb.append('\n');
        } else if (t instanceof Byte) {
            a(((Byte) t).byteValue(), z);
        } else if (t instanceof Boolean) {
            a(((Boolean) t).booleanValue(), z);
        } else if (t instanceof Short) {
            a(((Short) t).shortValue(), z);
        } else if (t instanceof Integer) {
            a(((Integer) t).intValue(), z);
        } else if (t instanceof Long) {
            a(((Long) t).longValue(), z);
        } else if (t instanceof Float) {
            a(((Float) t).floatValue(), z);
        } else if (t instanceof Double) {
            a(((Double) t).doubleValue(), z);
        } else if (t instanceof String) {
            a((String) t, z);
        } else if (t instanceof Map) {
            a((Map) t, z);
        } else if (t instanceof List) {
            a((Collection) ((List) t), z);
        } else if (t instanceof p) {
            a((p) t, z);
        } else if (t instanceof byte[]) {
            a((byte[]) t, z);
        } else if (t instanceof boolean[]) {
            a((k) ((boolean[]) t), z);
        } else if (t instanceof short[]) {
            a((short[]) t, z);
        } else if (t instanceof int[]) {
            a((int[]) t, z);
        } else if (t instanceof long[]) {
            a((long[]) t, z);
        } else if (t instanceof float[]) {
            a((float[]) t, z);
        } else if (t instanceof double[]) {
            a((double[]) t, z);
        } else if (!t.getClass().isArray()) {
            throw new l("write object error: unsupport type.");
        } else {
            a((Object[]) t, z);
        }
        return this;
    }

    public k a(String str, String str2) {
        a(str2);
        if (str == null) {
            StringBuilder sb = this.a;
            sb.append(DYConstants.DY_NULL_STR);
            sb.append('\n');
        } else {
            StringBuilder sb2 = this.a;
            sb2.append(str);
            sb2.append('\n');
        }
        return this;
    }

    public k a(String str, boolean z) {
        if (str == null) {
            this.a.append(DYConstants.DY_NULL_STR);
        } else {
            this.a.append(str);
        }
        if (z) {
            this.a.append("|");
        }
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> k a(Collection<T> collection, String str) {
        if (collection == null) {
            a(str);
            StringBuilder sb = this.a;
            sb.append(DYConstants.DY_NULL_STR);
            sb.append('\t');
            return this;
        }
        return a(collection.toArray(), str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> k a(Collection<T> collection, boolean z) {
        if (collection == null) {
            this.a.append("[]");
            if (z) {
                this.a.append("|");
            }
            return this;
        }
        return a(collection.toArray(), z);
    }

    public <K, V> k a(Map<K, V> map, String str) {
        a(str);
        if (map == null) {
            StringBuilder sb = this.a;
            sb.append(DYConstants.DY_NULL_STR);
            sb.append('\n');
            return this;
        } else if (map.isEmpty()) {
            StringBuilder sb2 = this.a;
            sb2.append(map.size());
            sb2.append(", {}");
            sb2.append('\n');
            return this;
        } else {
            StringBuilder sb3 = this.a;
            sb3.append(map.size());
            sb3.append(", {");
            sb3.append('\n');
            k kVar = new k(this.a, this.b + 1);
            k kVar2 = new k(this.a, this.b + 2);
            for (Map.Entry<K, V> entry : map.entrySet()) {
                kVar.a('(', (String) null);
                kVar2.a((k) entry.getKey(), (String) null);
                kVar2.a((k) entry.getValue(), (String) null);
                kVar.a(')', (String) null);
            }
            a('}', (String) null);
            return this;
        }
    }

    public <K, V> k a(Map<K, V> map, boolean z) {
        if (map == null || map.isEmpty()) {
            this.a.append("{}");
            if (z) {
                this.a.append("|");
            }
            return this;
        }
        this.a.append("{");
        k kVar = new k(this.a, this.b + 2);
        boolean z2 = true;
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (!z2) {
                this.a.append(DYConstants.DY_REGEX_COMMA);
            }
            kVar.a((k) entry.getKey(), true);
            kVar.a((k) entry.getValue(), false);
            z2 = false;
        }
        this.a.append("}");
        if (z) {
            this.a.append("|");
        }
        return this;
    }

    public k a(short s, String str) {
        a(str);
        StringBuilder sb = this.a;
        sb.append((int) s);
        sb.append('\n');
        return this;
    }

    public k a(short s, boolean z) {
        this.a.append((int) s);
        if (z) {
            this.a.append("|");
        }
        return this;
    }

    public k a(boolean z, String str) {
        a(str);
        StringBuilder sb = this.a;
        sb.append(z ? 'T' : 'F');
        sb.append('\n');
        return this;
    }

    public k a(boolean z, boolean z2) {
        this.a.append(z ? 'T' : 'F');
        if (z2) {
            this.a.append("|");
        }
        return this;
    }

    public k a(byte[] bArr, String str) {
        a(str);
        if (bArr == null) {
            StringBuilder sb = this.a;
            sb.append(DYConstants.DY_NULL_STR);
            sb.append('\n');
            return this;
        } else if (bArr.length == 0) {
            StringBuilder sb2 = this.a;
            sb2.append(bArr.length);
            sb2.append(", []");
            sb2.append('\n');
            return this;
        } else {
            StringBuilder sb3 = this.a;
            sb3.append(bArr.length);
            sb3.append(", [");
            sb3.append('\n');
            k kVar = new k(this.a, this.b + 1);
            for (byte b : bArr) {
                kVar.a(b, (String) null);
            }
            a(']', (String) null);
            return this;
        }
    }

    public k a(byte[] bArr, boolean z) {
        if (bArr == null || bArr.length == 0) {
            if (z) {
                this.a.append("|");
            }
            return this;
        }
        this.a.append(i.a(bArr));
        if (z) {
            this.a.append("|");
        }
        return this;
    }

    public k a(char[] cArr, String str) {
        a(str);
        if (cArr == null) {
            StringBuilder sb = this.a;
            sb.append(DYConstants.DY_NULL_STR);
            sb.append('\n');
            return this;
        } else if (cArr.length == 0) {
            StringBuilder sb2 = this.a;
            sb2.append(cArr.length);
            sb2.append(", []");
            sb2.append('\n');
            return this;
        } else {
            StringBuilder sb3 = this.a;
            sb3.append(cArr.length);
            sb3.append(", [");
            sb3.append('\n');
            k kVar = new k(this.a, this.b + 1);
            for (char c2 : cArr) {
                kVar.a(c2, (String) null);
            }
            a(']', (String) null);
            return this;
        }
    }

    public k a(char[] cArr, boolean z) {
        if (cArr == null || cArr.length == 0) {
            if (z) {
                this.a.append("|");
            }
            return this;
        }
        this.a.append(new String(cArr));
        if (z) {
            this.a.append("|");
        }
        return this;
    }

    public k a(double[] dArr, String str) {
        a(str);
        if (dArr == null) {
            StringBuilder sb = this.a;
            sb.append(DYConstants.DY_NULL_STR);
            sb.append('\n');
            return this;
        } else if (dArr.length == 0) {
            StringBuilder sb2 = this.a;
            sb2.append(dArr.length);
            sb2.append(", []");
            sb2.append('\n');
            return this;
        } else {
            StringBuilder sb3 = this.a;
            sb3.append(dArr.length);
            sb3.append(", [");
            sb3.append('\n');
            k kVar = new k(this.a, this.b + 1);
            for (double d : dArr) {
                kVar.a(d, (String) null);
            }
            a(']', (String) null);
            return this;
        }
    }

    public k a(double[] dArr, boolean z) {
        if (dArr == null || dArr.length == 0) {
            this.a.append("[]");
            if (z) {
                this.a.append("|");
            }
            return this;
        }
        this.a.append("[");
        k kVar = new k(this.a, this.b + 1);
        for (int i2 = 0; i2 < dArr.length; i2++) {
            double d = dArr[i2];
            if (i2 != 0) {
                this.a.append("|");
            }
            kVar.a(d, false);
        }
        this.a.append("[");
        if (z) {
            this.a.append("|");
        }
        return this;
    }

    public k a(float[] fArr, String str) {
        a(str);
        if (fArr == null) {
            StringBuilder sb = this.a;
            sb.append(DYConstants.DY_NULL_STR);
            sb.append('\n');
            return this;
        } else if (fArr.length == 0) {
            StringBuilder sb2 = this.a;
            sb2.append(fArr.length);
            sb2.append(", []");
            sb2.append('\n');
            return this;
        } else {
            StringBuilder sb3 = this.a;
            sb3.append(fArr.length);
            sb3.append(", [");
            sb3.append('\n');
            k kVar = new k(this.a, this.b + 1);
            for (float f2 : fArr) {
                kVar.a(f2, (String) null);
            }
            a(']', (String) null);
            return this;
        }
    }

    public k a(float[] fArr, boolean z) {
        if (fArr == null || fArr.length == 0) {
            this.a.append("[]");
            if (z) {
                this.a.append("|");
            }
            return this;
        }
        this.a.append("[");
        k kVar = new k(this.a, this.b + 1);
        for (int i2 = 0; i2 < fArr.length; i2++) {
            float f2 = fArr[i2];
            if (i2 != 0) {
                this.a.append("|");
            }
            kVar.a(f2, false);
        }
        this.a.append("]");
        if (z) {
            this.a.append("|");
        }
        return this;
    }

    public k a(int[] iArr, String str) {
        a(str);
        if (iArr == null) {
            StringBuilder sb = this.a;
            sb.append(DYConstants.DY_NULL_STR);
            sb.append('\n');
            return this;
        } else if (iArr.length == 0) {
            StringBuilder sb2 = this.a;
            sb2.append(iArr.length);
            sb2.append(", []");
            sb2.append('\n');
            return this;
        } else {
            StringBuilder sb3 = this.a;
            sb3.append(iArr.length);
            sb3.append(", [");
            sb3.append('\n');
            k kVar = new k(this.a, this.b + 1);
            for (int i2 : iArr) {
                kVar.a(i2, (String) null);
            }
            a(']', (String) null);
            return this;
        }
    }

    public k a(int[] iArr, boolean z) {
        if (iArr == null || iArr.length == 0) {
            this.a.append("[]");
            if (z) {
                this.a.append("|");
            }
            return this;
        }
        this.a.append("[");
        k kVar = new k(this.a, this.b + 1);
        for (int i2 = 0; i2 < iArr.length; i2++) {
            int i3 = iArr[i2];
            if (i2 != 0) {
                this.a.append("|");
            }
            kVar.a(i3, false);
        }
        this.a.append("]");
        if (z) {
            this.a.append("|");
        }
        return this;
    }

    public k a(long[] jArr, String str) {
        a(str);
        if (jArr == null) {
            StringBuilder sb = this.a;
            sb.append(DYConstants.DY_NULL_STR);
            sb.append('\n');
            return this;
        } else if (jArr.length == 0) {
            StringBuilder sb2 = this.a;
            sb2.append(jArr.length);
            sb2.append(", []");
            sb2.append('\n');
            return this;
        } else {
            StringBuilder sb3 = this.a;
            sb3.append(jArr.length);
            sb3.append(", [");
            sb3.append('\n');
            k kVar = new k(this.a, this.b + 1);
            for (long j2 : jArr) {
                kVar.a(j2, (String) null);
            }
            a(']', (String) null);
            return this;
        }
    }

    public k a(long[] jArr, boolean z) {
        if (jArr == null || jArr.length == 0) {
            this.a.append("[]");
            if (z) {
                this.a.append("|");
            }
            return this;
        }
        this.a.append("[");
        k kVar = new k(this.a, this.b + 1);
        for (int i2 = 0; i2 < jArr.length; i2++) {
            long j2 = jArr[i2];
            if (i2 != 0) {
                this.a.append("|");
            }
            kVar.a(j2, false);
        }
        this.a.append("]");
        if (z) {
            this.a.append("|");
        }
        return this;
    }

    public <T> k a(T[] tArr, String str) {
        a(str);
        if (tArr == null) {
            StringBuilder sb = this.a;
            sb.append(DYConstants.DY_NULL_STR);
            sb.append('\n');
            return this;
        } else if (tArr.length == 0) {
            StringBuilder sb2 = this.a;
            sb2.append(tArr.length);
            sb2.append(", []");
            sb2.append('\n');
            return this;
        } else {
            StringBuilder sb3 = this.a;
            sb3.append(tArr.length);
            sb3.append(", [");
            sb3.append('\n');
            k kVar = new k(this.a, this.b + 1);
            for (T t : tArr) {
                kVar.a((k) t, (String) null);
            }
            a(']', (String) null);
            return this;
        }
    }

    public <T> k a(T[] tArr, boolean z) {
        if (tArr == null || tArr.length == 0) {
            this.a.append("[]");
            if (z) {
                this.a.append("|");
            }
            return this;
        }
        this.a.append("[");
        k kVar = new k(this.a, this.b + 1);
        for (int i2 = 0; i2 < tArr.length; i2++) {
            T t = tArr[i2];
            if (i2 != 0) {
                this.a.append("|");
            }
            kVar.a((k) t, false);
        }
        this.a.append("]");
        if (z) {
            this.a.append("|");
        }
        return this;
    }

    public k a(short[] sArr, String str) {
        a(str);
        if (sArr == null) {
            StringBuilder sb = this.a;
            sb.append(DYConstants.DY_NULL_STR);
            sb.append('\n');
            return this;
        } else if (sArr.length == 0) {
            StringBuilder sb2 = this.a;
            sb2.append(sArr.length);
            sb2.append(", []");
            sb2.append('\n');
            return this;
        } else {
            StringBuilder sb3 = this.a;
            sb3.append(sArr.length);
            sb3.append(", [");
            sb3.append('\n');
            k kVar = new k(this.a, this.b + 1);
            for (short s : sArr) {
                kVar.a(s, (String) null);
            }
            a(']', (String) null);
            return this;
        }
    }

    public k a(short[] sArr, boolean z) {
        if (sArr == null || sArr.length == 0) {
            this.a.append("[]");
            if (z) {
                this.a.append("|");
            }
            return this;
        }
        this.a.append("[");
        k kVar = new k(this.a, this.b + 1);
        for (int i2 = 0; i2 < sArr.length; i2++) {
            short s = sArr[i2];
            if (i2 != 0) {
                this.a.append("|");
            }
            kVar.a(s, false);
        }
        this.a.append("]");
        if (z) {
            this.a.append("|");
        }
        return this;
    }
}
