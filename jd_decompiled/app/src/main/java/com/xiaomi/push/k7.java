package com.xiaomi.push;

import com.jd.dynamic.DYConstants;
import java.io.Serializable;
import java.util.BitSet;

/* loaded from: classes11.dex */
public class k7 implements n8<k7, Object>, Serializable, Cloneable {

    /* renamed from: g  reason: collision with root package name */
    private static final e9 f18798g = new e9("DataCollectionItem");

    /* renamed from: h  reason: collision with root package name */
    private static final v8 f18799h = new v8("", (byte) 10, 1);

    /* renamed from: i  reason: collision with root package name */
    private static final v8 f18800i = new v8("", (byte) 8, 2);

    /* renamed from: j  reason: collision with root package name */
    private static final v8 f18801j = new v8("", (byte) 11, 3);
    public long a;

    /* renamed from: a  reason: collision with other field name */
    public e7 f164a;

    /* renamed from: a  reason: collision with other field name */
    public String f165a;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f166a = new BitSet(1);

    @Override // java.lang.Comparable
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public int compareTo(k7 k7Var) {
        int e2;
        int d;
        int c2;
        if (getClass().equals(k7Var.getClass())) {
            int compareTo = Boolean.valueOf(m90a()).compareTo(Boolean.valueOf(k7Var.m90a()));
            if (compareTo != 0) {
                return compareTo;
            }
            if (!m90a() || (c2 = o8.c(this.a, k7Var.a)) == 0) {
                int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(k7Var.b()));
                if (compareTo2 != 0) {
                    return compareTo2;
                }
                if (!b() || (d = o8.d(this.f164a, k7Var.f164a)) == 0) {
                    int compareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(k7Var.c()));
                    if (compareTo3 != 0) {
                        return compareTo3;
                    }
                    if (!c() || (e2 = o8.e(this.f165a, k7Var.f165a)) == 0) {
                        return 0;
                    }
                    return e2;
                }
                return d;
            }
            return c2;
        }
        return getClass().getName().compareTo(k7Var.getClass().getName());
    }

    public k7 a(long j2) {
        this.a = j2;
        a(true);
        return this;
    }

    public k7 a(e7 e7Var) {
        this.f164a = e7Var;
        return this;
    }

    public k7 a(String str) {
        this.f165a = str;
        return this;
    }

    public String a() {
        return this.f165a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m89a() {
        if (this.f164a == null) {
            throw new z8("Required field 'collectionType' was not present! Struct: " + toString());
        } else if (this.f165a != null) {
        } else {
            throw new z8("Required field 'content' was not present! Struct: " + toString());
        }
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
                if (b == 10) {
                    this.a = y8Var.d();
                    a(true);
                    y8Var.E();
                }
                c9.a(y8Var, b);
                y8Var.E();
            } else if (s != 2) {
                if (s == 3 && b == 11) {
                    this.f165a = y8Var.j();
                    y8Var.E();
                }
                c9.a(y8Var, b);
                y8Var.E();
            } else {
                if (b == 8) {
                    this.f164a = e7.a(y8Var.c());
                    y8Var.E();
                }
                c9.a(y8Var, b);
                y8Var.E();
            }
        }
        y8Var.D();
        if (m90a()) {
            m89a();
            return;
        }
        throw new z8("Required field 'collectedAt' was not found in serialized data! Struct: " + toString());
    }

    public void a(boolean z) {
        this.f166a.set(0, z);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m90a() {
        return this.f166a.get(0);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m91a(k7 k7Var) {
        if (k7Var != null && this.a == k7Var.a) {
            boolean b = b();
            boolean b2 = k7Var.b();
            if ((b || b2) && !(b && b2 && this.f164a.equals(k7Var.f164a))) {
                return false;
            }
            boolean c2 = c();
            boolean c3 = k7Var.c();
            if (c2 || c3) {
                return c2 && c3 && this.f165a.equals(k7Var.f165a);
            }
            return true;
        }
        return false;
    }

    @Override // com.xiaomi.push.n8
    public void b(y8 y8Var) {
        m89a();
        y8Var.t(f18798g);
        y8Var.q(f18799h);
        y8Var.p(this.a);
        y8Var.z();
        if (this.f164a != null) {
            y8Var.q(f18800i);
            y8Var.o(this.f164a.a());
            y8Var.z();
        }
        if (this.f165a != null) {
            y8Var.q(f18801j);
            y8Var.u(this.f165a);
            y8Var.z();
        }
        y8Var.A();
        y8Var.m();
    }

    public boolean b() {
        return this.f164a != null;
    }

    public boolean c() {
        return this.f165a != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof k7)) {
            return m91a((k7) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("DataCollectionItem(");
        sb.append("collectedAt:");
        sb.append(this.a);
        sb.append(", ");
        sb.append("collectionType:");
        e7 e7Var = this.f164a;
        if (e7Var == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            sb.append(e7Var);
        }
        sb.append(", ");
        sb.append("content:");
        String str = this.f165a;
        if (str == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            sb.append(str);
        }
        sb.append(")");
        return sb.toString();
    }
}
