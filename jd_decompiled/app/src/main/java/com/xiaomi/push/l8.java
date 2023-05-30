package com.xiaomi.push;

import com.jd.dynamic.DYConstants;
import java.io.Serializable;
import java.util.BitSet;

/* loaded from: classes11.dex */
public class l8 implements n8<l8, Object>, Serializable, Cloneable {

    /* renamed from: h  reason: collision with root package name */
    private static final e9 f18840h = new e9("XmPushActionUnSubscriptionResult");

    /* renamed from: i  reason: collision with root package name */
    private static final v8 f18841i = new v8("", (byte) 11, 1);

    /* renamed from: j  reason: collision with root package name */
    private static final v8 f18842j = new v8("", (byte) 12, 2);

    /* renamed from: k  reason: collision with root package name */
    private static final v8 f18843k = new v8("", (byte) 11, 3);

    /* renamed from: l  reason: collision with root package name */
    private static final v8 f18844l = new v8("", (byte) 11, 4);

    /* renamed from: m  reason: collision with root package name */
    private static final v8 f18845m = new v8("", (byte) 10, 6);

    /* renamed from: n  reason: collision with root package name */
    private static final v8 f18846n = new v8("", (byte) 11, 7);
    private static final v8 o = new v8("", (byte) 11, 8);
    private static final v8 p = new v8("", (byte) 11, 9);
    private static final v8 q = new v8("", (byte) 11, 10);
    public long a;

    /* renamed from: a  reason: collision with other field name */
    public r7 f176a;

    /* renamed from: a  reason: collision with other field name */
    public String f177a;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f178a = new BitSet(1);
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public String f18847c;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public String f18848e;

    /* renamed from: f  reason: collision with root package name */
    public String f18849f;

    /* renamed from: g  reason: collision with root package name */
    public String f18850g;

    @Override // java.lang.Comparable
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public int compareTo(l8 l8Var) {
        int e2;
        int e3;
        int e4;
        int e5;
        int c2;
        int e6;
        int e7;
        int d;
        int e8;
        if (getClass().equals(l8Var.getClass())) {
            int compareTo = Boolean.valueOf(m100a()).compareTo(Boolean.valueOf(l8Var.m100a()));
            if (compareTo != 0) {
                return compareTo;
            }
            if (!m100a() || (e8 = o8.e(this.f177a, l8Var.f177a)) == 0) {
                int compareTo2 = Boolean.valueOf(m102b()).compareTo(Boolean.valueOf(l8Var.m102b()));
                if (compareTo2 != 0) {
                    return compareTo2;
                }
                if (!m102b() || (d = o8.d(this.f176a, l8Var.f176a)) == 0) {
                    int compareTo3 = Boolean.valueOf(m103c()).compareTo(Boolean.valueOf(l8Var.m103c()));
                    if (compareTo3 != 0) {
                        return compareTo3;
                    }
                    if (!m103c() || (e7 = o8.e(this.b, l8Var.b)) == 0) {
                        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(l8Var.d()));
                        if (compareTo4 != 0) {
                            return compareTo4;
                        }
                        if (!d() || (e6 = o8.e(this.f18847c, l8Var.f18847c)) == 0) {
                            int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(l8Var.e()));
                            if (compareTo5 != 0) {
                                return compareTo5;
                            }
                            if (!e() || (c2 = o8.c(this.a, l8Var.a)) == 0) {
                                int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(l8Var.f()));
                                if (compareTo6 != 0) {
                                    return compareTo6;
                                }
                                if (!f() || (e5 = o8.e(this.d, l8Var.d)) == 0) {
                                    int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(l8Var.g()));
                                    if (compareTo7 != 0) {
                                        return compareTo7;
                                    }
                                    if (!g() || (e4 = o8.e(this.f18848e, l8Var.f18848e)) == 0) {
                                        int compareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(l8Var.h()));
                                        if (compareTo8 != 0) {
                                            return compareTo8;
                                        }
                                        if (!h() || (e3 = o8.e(this.f18849f, l8Var.f18849f)) == 0) {
                                            int compareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(l8Var.i()));
                                            if (compareTo9 != 0) {
                                                return compareTo9;
                                            }
                                            if (!i() || (e2 = o8.e(this.f18850g, l8Var.f18850g)) == 0) {
                                                return 0;
                                            }
                                            return e2;
                                        }
                                        return e3;
                                    }
                                    return e4;
                                }
                                return e5;
                            }
                            return c2;
                        }
                        return e6;
                    }
                    return e7;
                }
                return d;
            }
            return e8;
        }
        return getClass().getName().compareTo(l8Var.getClass().getName());
    }

    public String a() {
        return this.b;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m99a() {
        if (this.b != null) {
            return;
        }
        throw new z8("Required field 'id' was not present! Struct: " + toString());
    }

    @Override // com.xiaomi.push.n8
    public void a(y8 y8Var) {
        y8Var.i();
        while (true) {
            v8 e2 = y8Var.e();
            byte b = e2.b;
            if (b == 0) {
                y8Var.D();
                m99a();
                return;
            }
            switch (e2.f19282c) {
                case 1:
                    if (b == 11) {
                        this.f177a = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 2:
                    if (b == 12) {
                        r7 r7Var = new r7();
                        this.f176a = r7Var;
                        r7Var.a(y8Var);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 3:
                    if (b == 11) {
                        this.b = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 4:
                    if (b == 11) {
                        this.f18847c = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 6:
                    if (b == 10) {
                        this.a = y8Var.d();
                        a(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 7:
                    if (b == 11) {
                        this.d = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 8:
                    if (b == 11) {
                        this.f18848e = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 9:
                    if (b == 11) {
                        this.f18849f = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 10:
                    if (b == 11) {
                        this.f18850g = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
            }
            c9.a(y8Var, b);
            y8Var.E();
        }
    }

    public void a(boolean z) {
        this.f178a.set(0, z);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m100a() {
        return this.f177a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m101a(l8 l8Var) {
        if (l8Var == null) {
            return false;
        }
        boolean m100a = m100a();
        boolean m100a2 = l8Var.m100a();
        if ((m100a || m100a2) && !(m100a && m100a2 && this.f177a.equals(l8Var.f177a))) {
            return false;
        }
        boolean m102b = m102b();
        boolean m102b2 = l8Var.m102b();
        if ((m102b || m102b2) && !(m102b && m102b2 && this.f176a.m132a(l8Var.f176a))) {
            return false;
        }
        boolean m103c = m103c();
        boolean m103c2 = l8Var.m103c();
        if ((m103c || m103c2) && !(m103c && m103c2 && this.b.equals(l8Var.b))) {
            return false;
        }
        boolean d = d();
        boolean d2 = l8Var.d();
        if ((d || d2) && !(d && d2 && this.f18847c.equals(l8Var.f18847c))) {
            return false;
        }
        boolean e2 = e();
        boolean e3 = l8Var.e();
        if ((e2 || e3) && !(e2 && e3 && this.a == l8Var.a)) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = l8Var.f();
        if ((f2 || f3) && !(f2 && f3 && this.d.equals(l8Var.d))) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = l8Var.g();
        if ((g2 || g3) && !(g2 && g3 && this.f18848e.equals(l8Var.f18848e))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = l8Var.h();
        if ((h2 || h3) && !(h2 && h3 && this.f18849f.equals(l8Var.f18849f))) {
            return false;
        }
        boolean i2 = i();
        boolean i3 = l8Var.i();
        if (i2 || i3) {
            return i2 && i3 && this.f18850g.equals(l8Var.f18850g);
        }
        return true;
    }

    public String b() {
        return this.f18848e;
    }

    @Override // com.xiaomi.push.n8
    public void b(y8 y8Var) {
        m99a();
        y8Var.t(f18840h);
        if (this.f177a != null && m100a()) {
            y8Var.q(f18841i);
            y8Var.u(this.f177a);
            y8Var.z();
        }
        if (this.f176a != null && m102b()) {
            y8Var.q(f18842j);
            this.f176a.b(y8Var);
            y8Var.z();
        }
        if (this.b != null) {
            y8Var.q(f18843k);
            y8Var.u(this.b);
            y8Var.z();
        }
        if (this.f18847c != null && d()) {
            y8Var.q(f18844l);
            y8Var.u(this.f18847c);
            y8Var.z();
        }
        if (e()) {
            y8Var.q(f18845m);
            y8Var.p(this.a);
            y8Var.z();
        }
        if (this.d != null && f()) {
            y8Var.q(f18846n);
            y8Var.u(this.d);
            y8Var.z();
        }
        if (this.f18848e != null && g()) {
            y8Var.q(o);
            y8Var.u(this.f18848e);
            y8Var.z();
        }
        if (this.f18849f != null && h()) {
            y8Var.q(p);
            y8Var.u(this.f18849f);
            y8Var.z();
        }
        if (this.f18850g != null && i()) {
            y8Var.q(q);
            y8Var.u(this.f18850g);
            y8Var.z();
        }
        y8Var.A();
        y8Var.m();
    }

    /* renamed from: b  reason: collision with other method in class */
    public boolean m102b() {
        return this.f176a != null;
    }

    public String c() {
        return this.f18850g;
    }

    /* renamed from: c  reason: collision with other method in class */
    public boolean m103c() {
        return this.b != null;
    }

    public boolean d() {
        return this.f18847c != null;
    }

    public boolean e() {
        return this.f178a.get(0);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof l8)) {
            return m101a((l8) obj);
        }
        return false;
    }

    public boolean f() {
        return this.d != null;
    }

    public boolean g() {
        return this.f18848e != null;
    }

    public boolean h() {
        return this.f18849f != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f18850g != null;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionUnSubscriptionResult(");
        boolean z2 = false;
        if (m100a()) {
            sb.append("debug:");
            String str = this.f177a;
            if (str == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str);
            }
            z = false;
        } else {
            z = true;
        }
        if (m102b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            r7 r7Var = this.f176a;
            if (r7Var == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(r7Var);
            }
        } else {
            z2 = z;
        }
        if (!z2) {
            sb.append(", ");
        }
        sb.append("id:");
        String str2 = this.b;
        if (str2 == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            sb.append(str2);
        }
        if (d()) {
            sb.append(", ");
            sb.append("appId:");
            String str3 = this.f18847c;
            if (str3 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str3);
            }
        }
        if (e()) {
            sb.append(", ");
            sb.append("errorCode:");
            sb.append(this.a);
        }
        if (f()) {
            sb.append(", ");
            sb.append("reason:");
            String str4 = this.d;
            if (str4 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str4);
            }
        }
        if (g()) {
            sb.append(", ");
            sb.append("topic:");
            String str5 = this.f18848e;
            if (str5 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str5);
            }
        }
        if (h()) {
            sb.append(", ");
            sb.append("packageName:");
            String str6 = this.f18849f;
            if (str6 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str6);
            }
        }
        if (i()) {
            sb.append(", ");
            sb.append("category:");
            String str7 = this.f18850g;
            if (str7 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str7);
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
