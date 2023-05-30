package com.xiaomi.push;

import com.jd.dynamic.DYConstants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes11.dex */
public class l7 implements n8<l7, Object>, Serializable, Cloneable {

    /* renamed from: g  reason: collision with root package name */
    private static final e9 f18836g = new e9("NormalConfig");

    /* renamed from: h  reason: collision with root package name */
    private static final v8 f18837h = new v8("", (byte) 8, 1);

    /* renamed from: i  reason: collision with root package name */
    private static final v8 f18838i = new v8("", (byte) 15, 2);

    /* renamed from: j  reason: collision with root package name */
    private static final v8 f18839j = new v8("", (byte) 8, 3);
    public int a;

    /* renamed from: a  reason: collision with other field name */
    public i7 f173a;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f174a = new BitSet(1);

    /* renamed from: a  reason: collision with other field name */
    public List<n7> f175a;

    public int a() {
        return this.a;
    }

    @Override // java.lang.Comparable
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public int compareTo(l7 l7Var) {
        int d;
        int g2;
        int b;
        if (getClass().equals(l7Var.getClass())) {
            int compareTo = Boolean.valueOf(m97a()).compareTo(Boolean.valueOf(l7Var.m97a()));
            if (compareTo != 0) {
                return compareTo;
            }
            if (!m97a() || (b = o8.b(this.a, l7Var.a)) == 0) {
                int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(l7Var.b()));
                if (compareTo2 != 0) {
                    return compareTo2;
                }
                if (!b() || (g2 = o8.g(this.f175a, l7Var.f175a)) == 0) {
                    int compareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(l7Var.c()));
                    if (compareTo3 != 0) {
                        return compareTo3;
                    }
                    if (!c() || (d = o8.d(this.f173a, l7Var.f173a)) == 0) {
                        return 0;
                    }
                    return d;
                }
                return g2;
            }
            return b;
        }
        return getClass().getName().compareTo(l7Var.getClass().getName());
    }

    /* renamed from: a  reason: collision with other method in class */
    public i7 m95a() {
        return this.f173a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m96a() {
        if (this.f175a != null) {
            return;
        }
        throw new z8("Required field 'configItems' was not present! Struct: " + toString());
    }

    @Override // com.xiaomi.push.n8
    public void a(y8 y8Var) {
        y8Var.i();
        while (true) {
            v8 e2 = y8Var.e();
            byte b = e2.b;
            if (b == 0) {
                break;
            }
            short s = e2.f19282c;
            if (s == 1) {
                if (b == 8) {
                    this.a = y8Var.c();
                    a(true);
                    y8Var.E();
                }
                c9.a(y8Var, b);
                y8Var.E();
            } else if (s != 2) {
                if (s == 3 && b == 8) {
                    this.f173a = i7.a(y8Var.c());
                    y8Var.E();
                }
                c9.a(y8Var, b);
                y8Var.E();
            } else {
                if (b == 15) {
                    w8 f2 = y8Var.f();
                    this.f175a = new ArrayList(f2.b);
                    for (int i2 = 0; i2 < f2.b; i2++) {
                        n7 n7Var = new n7();
                        n7Var.a(y8Var);
                        this.f175a.add(n7Var);
                    }
                    y8Var.G();
                    y8Var.E();
                }
                c9.a(y8Var, b);
                y8Var.E();
            }
        }
        y8Var.D();
        if (m97a()) {
            m96a();
            return;
        }
        throw new z8("Required field 'version' was not found in serialized data! Struct: " + toString());
    }

    public void a(boolean z) {
        this.f174a.set(0, z);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m97a() {
        return this.f174a.get(0);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m98a(l7 l7Var) {
        if (l7Var != null && this.a == l7Var.a) {
            boolean b = b();
            boolean b2 = l7Var.b();
            if ((b || b2) && !(b && b2 && this.f175a.equals(l7Var.f175a))) {
                return false;
            }
            boolean c2 = c();
            boolean c3 = l7Var.c();
            if (c2 || c3) {
                return c2 && c3 && this.f173a.equals(l7Var.f173a);
            }
            return true;
        }
        return false;
    }

    @Override // com.xiaomi.push.n8
    public void b(y8 y8Var) {
        m96a();
        y8Var.t(f18836g);
        y8Var.q(f18837h);
        y8Var.o(this.a);
        y8Var.z();
        if (this.f175a != null) {
            y8Var.q(f18838i);
            y8Var.r(new w8((byte) 12, this.f175a.size()));
            Iterator<n7> it = this.f175a.iterator();
            while (it.hasNext()) {
                it.next().b(y8Var);
            }
            y8Var.C();
            y8Var.z();
        }
        if (this.f173a != null && c()) {
            y8Var.q(f18839j);
            y8Var.o(this.f173a.a());
            y8Var.z();
        }
        y8Var.A();
        y8Var.m();
    }

    public boolean b() {
        return this.f175a != null;
    }

    public boolean c() {
        return this.f173a != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof l7)) {
            return m98a((l7) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("NormalConfig(");
        sb.append("version:");
        sb.append(this.a);
        sb.append(", ");
        sb.append("configItems:");
        List<n7> list = this.f175a;
        if (list == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            sb.append(list);
        }
        if (c()) {
            sb.append(", ");
            sb.append("type:");
            i7 i7Var = this.f173a;
            if (i7Var == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(i7Var);
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
