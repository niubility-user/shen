package com.xiaomi.push;

import com.jd.dynamic.DYConstants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes11.dex */
public class t4 implements n8<t4, Object>, Serializable, Cloneable {

    /* renamed from: g */
    private static final e9 f19226g = new e9("StatsEvents");

    /* renamed from: h */
    private static final v8 f19227h = new v8("", (byte) 11, 1);

    /* renamed from: i */
    private static final v8 f19228i = new v8("", (byte) 11, 2);

    /* renamed from: j */
    private static final v8 f19229j = new v8("", (byte) 15, 3);
    public String a;

    /* renamed from: a */
    public List<s4> f244a;
    public String b;

    public t4() {
    }

    public t4(String str, List<s4> list) {
        this();
        this.a = str;
        this.f244a = list;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(t4 t4Var) {
        int g2;
        int e2;
        int e3;
        if (getClass().equals(t4Var.getClass())) {
            int compareTo = Boolean.valueOf(m163a()).compareTo(Boolean.valueOf(t4Var.m163a()));
            if (compareTo != 0) {
                return compareTo;
            }
            if (!m163a() || (e3 = o8.e(this.a, t4Var.a)) == 0) {
                int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(t4Var.b()));
                if (compareTo2 != 0) {
                    return compareTo2;
                }
                if (!b() || (e2 = o8.e(this.b, t4Var.b)) == 0) {
                    int compareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(t4Var.c()));
                    if (compareTo3 != 0) {
                        return compareTo3;
                    }
                    if (!c() || (g2 = o8.g(this.f244a, t4Var.f244a)) == 0) {
                        return 0;
                    }
                    return g2;
                }
                return e2;
            }
            return e3;
        }
        return getClass().getName().compareTo(t4Var.getClass().getName());
    }

    public t4 a(String str) {
        this.b = str;
        return this;
    }

    public void a() {
        if (this.a == null) {
            throw new z8("Required field 'uuid' was not present! Struct: " + toString());
        } else if (this.f244a != null) {
        } else {
            throw new z8("Required field 'events' was not present! Struct: " + toString());
        }
    }

    @Override // com.xiaomi.push.n8
    public void a(y8 y8Var) {
        y8Var.i();
        while (true) {
            v8 e2 = y8Var.e();
            byte b = e2.b;
            if (b == 0) {
                y8Var.D();
                a();
                return;
            }
            short s = e2.f19282c;
            if (s == 1) {
                if (b == 11) {
                    this.a = y8Var.j();
                    y8Var.E();
                }
                c9.a(y8Var, b);
                y8Var.E();
            } else if (s != 2) {
                if (s == 3 && b == 15) {
                    w8 f2 = y8Var.f();
                    this.f244a = new ArrayList(f2.b);
                    for (int i2 = 0; i2 < f2.b; i2++) {
                        s4 s4Var = new s4();
                        s4Var.a(y8Var);
                        this.f244a.add(s4Var);
                    }
                    y8Var.G();
                    y8Var.E();
                }
                c9.a(y8Var, b);
                y8Var.E();
            } else {
                if (b == 11) {
                    this.b = y8Var.j();
                    y8Var.E();
                }
                c9.a(y8Var, b);
                y8Var.E();
            }
        }
    }

    /* renamed from: a */
    public boolean m163a() {
        return this.a != null;
    }

    /* renamed from: a */
    public boolean m164a(t4 t4Var) {
        if (t4Var == null) {
            return false;
        }
        boolean m163a = m163a();
        boolean m163a2 = t4Var.m163a();
        if ((m163a || m163a2) && !(m163a && m163a2 && this.a.equals(t4Var.a))) {
            return false;
        }
        boolean b = b();
        boolean b2 = t4Var.b();
        if ((b || b2) && !(b && b2 && this.b.equals(t4Var.b))) {
            return false;
        }
        boolean c2 = c();
        boolean c3 = t4Var.c();
        if (c2 || c3) {
            return c2 && c3 && this.f244a.equals(t4Var.f244a);
        }
        return true;
    }

    @Override // com.xiaomi.push.n8
    public void b(y8 y8Var) {
        a();
        y8Var.t(f19226g);
        if (this.a != null) {
            y8Var.q(f19227h);
            y8Var.u(this.a);
            y8Var.z();
        }
        if (this.b != null && b()) {
            y8Var.q(f19228i);
            y8Var.u(this.b);
            y8Var.z();
        }
        if (this.f244a != null) {
            y8Var.q(f19229j);
            y8Var.r(new w8((byte) 12, this.f244a.size()));
            Iterator<s4> it = this.f244a.iterator();
            while (it.hasNext()) {
                it.next().b(y8Var);
            }
            y8Var.C();
            y8Var.z();
        }
        y8Var.A();
        y8Var.m();
    }

    public boolean b() {
        return this.b != null;
    }

    public boolean c() {
        return this.f244a != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof t4)) {
            return m164a((t4) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("StatsEvents(");
        sb.append("uuid:");
        String str = this.a;
        if (str == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            sb.append(str);
        }
        if (b()) {
            sb.append(", ");
            sb.append("operator:");
            String str2 = this.b;
            if (str2 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str2);
            }
        }
        sb.append(", ");
        sb.append("events:");
        List<s4> list = this.f244a;
        if (list == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            sb.append(list);
        }
        sb.append(")");
        return sb.toString();
    }
}
