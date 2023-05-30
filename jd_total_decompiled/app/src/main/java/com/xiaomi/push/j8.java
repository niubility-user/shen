package com.xiaomi.push;

import com.jd.dynamic.DYConstants;
import java.io.Serializable;
import java.util.BitSet;

/* loaded from: classes11.dex */
public class j8 implements n8<j8, Object>, Serializable, Cloneable {

    /* renamed from: j  reason: collision with root package name */
    private static final e9 f18777j = new e9("XmPushActionUnRegistration");

    /* renamed from: k  reason: collision with root package name */
    private static final v8 f18778k = new v8("", (byte) 11, 1);

    /* renamed from: l  reason: collision with root package name */
    private static final v8 f18779l = new v8("", (byte) 12, 2);

    /* renamed from: m  reason: collision with root package name */
    private static final v8 f18780m = new v8("", (byte) 11, 3);

    /* renamed from: n  reason: collision with root package name */
    private static final v8 f18781n = new v8("", (byte) 11, 4);
    private static final v8 o = new v8("", (byte) 11, 5);
    private static final v8 p = new v8("", (byte) 11, 6);
    private static final v8 q = new v8("", (byte) 11, 7);
    private static final v8 r = new v8("", (byte) 11, 8);
    private static final v8 s = new v8("", (byte) 11, 9);
    private static final v8 t = new v8("", (byte) 11, 10);
    private static final v8 u = new v8("", (byte) 2, 11);
    private static final v8 v = new v8("", (byte) 10, 12);
    public long a;

    /* renamed from: a  reason: collision with other field name */
    public r7 f160a;

    /* renamed from: a  reason: collision with other field name */
    public String f161a;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f162a = new BitSet(2);

    /* renamed from: a  reason: collision with other field name */
    public boolean f163a = true;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public String f18782c;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public String f18783e;

    /* renamed from: f  reason: collision with root package name */
    public String f18784f;

    /* renamed from: g  reason: collision with root package name */
    public String f18785g;

    /* renamed from: h  reason: collision with root package name */
    public String f18786h;

    /* renamed from: i  reason: collision with root package name */
    public String f18787i;

    @Override // java.lang.Comparable
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public int compareTo(j8 j8Var) {
        int c2;
        int k2;
        int e2;
        int e3;
        int e4;
        int e5;
        int e6;
        int e7;
        int e8;
        int e9;
        int d;
        int e10;
        if (getClass().equals(j8Var.getClass())) {
            int compareTo = Boolean.valueOf(m87a()).compareTo(Boolean.valueOf(j8Var.m87a()));
            if (compareTo != 0) {
                return compareTo;
            }
            if (!m87a() || (e10 = o8.e(this.f161a, j8Var.f161a)) == 0) {
                int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(j8Var.b()));
                if (compareTo2 != 0) {
                    return compareTo2;
                }
                if (!b() || (d = o8.d(this.f160a, j8Var.f160a)) == 0) {
                    int compareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(j8Var.c()));
                    if (compareTo3 != 0) {
                        return compareTo3;
                    }
                    if (!c() || (e9 = o8.e(this.b, j8Var.b)) == 0) {
                        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(j8Var.d()));
                        if (compareTo4 != 0) {
                            return compareTo4;
                        }
                        if (!d() || (e8 = o8.e(this.f18782c, j8Var.f18782c)) == 0) {
                            int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(j8Var.e()));
                            if (compareTo5 != 0) {
                                return compareTo5;
                            }
                            if (!e() || (e7 = o8.e(this.d, j8Var.d)) == 0) {
                                int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(j8Var.f()));
                                if (compareTo6 != 0) {
                                    return compareTo6;
                                }
                                if (!f() || (e6 = o8.e(this.f18783e, j8Var.f18783e)) == 0) {
                                    int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(j8Var.g()));
                                    if (compareTo7 != 0) {
                                        return compareTo7;
                                    }
                                    if (!g() || (e5 = o8.e(this.f18784f, j8Var.f18784f)) == 0) {
                                        int compareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(j8Var.h()));
                                        if (compareTo8 != 0) {
                                            return compareTo8;
                                        }
                                        if (!h() || (e4 = o8.e(this.f18785g, j8Var.f18785g)) == 0) {
                                            int compareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(j8Var.i()));
                                            if (compareTo9 != 0) {
                                                return compareTo9;
                                            }
                                            if (!i() || (e3 = o8.e(this.f18786h, j8Var.f18786h)) == 0) {
                                                int compareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(j8Var.j()));
                                                if (compareTo10 != 0) {
                                                    return compareTo10;
                                                }
                                                if (!j() || (e2 = o8.e(this.f18787i, j8Var.f18787i)) == 0) {
                                                    int compareTo11 = Boolean.valueOf(k()).compareTo(Boolean.valueOf(j8Var.k()));
                                                    if (compareTo11 != 0) {
                                                        return compareTo11;
                                                    }
                                                    if (!k() || (k2 = o8.k(this.f163a, j8Var.f163a)) == 0) {
                                                        int compareTo12 = Boolean.valueOf(l()).compareTo(Boolean.valueOf(j8Var.l()));
                                                        if (compareTo12 != 0) {
                                                            return compareTo12;
                                                        }
                                                        if (!l() || (c2 = o8.c(this.a, j8Var.a)) == 0) {
                                                            return 0;
                                                        }
                                                        return c2;
                                                    }
                                                    return k2;
                                                }
                                                return e2;
                                            }
                                            return e3;
                                        }
                                        return e4;
                                    }
                                    return e5;
                                }
                                return e6;
                            }
                            return e7;
                        }
                        return e8;
                    }
                    return e9;
                }
                return d;
            }
            return e10;
        }
        return getClass().getName().compareTo(j8Var.getClass().getName());
    }

    public j8 a(String str) {
        this.b = str;
        return this;
    }

    public void a() {
        if (this.b == null) {
            throw new z8("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f18782c != null) {
        } else {
            throw new z8("Required field 'appId' was not present! Struct: " + toString());
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
            switch (e2.f19282c) {
                case 1:
                    if (b == 11) {
                        this.f161a = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 2:
                    if (b == 12) {
                        r7 r7Var = new r7();
                        this.f160a = r7Var;
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
                        this.f18782c = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 5:
                    if (b == 11) {
                        this.d = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 6:
                    if (b == 11) {
                        this.f18783e = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 7:
                    if (b == 11) {
                        this.f18784f = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 8:
                    if (b == 11) {
                        this.f18785g = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 9:
                    if (b == 11) {
                        this.f18786h = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 10:
                    if (b == 11) {
                        this.f18787i = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 11:
                    if (b == 2) {
                        this.f163a = y8Var.y();
                        a(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 12:
                    if (b == 10) {
                        this.a = y8Var.d();
                        b(true);
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
        this.f162a.set(0, z);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m87a() {
        return this.f161a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m88a(j8 j8Var) {
        if (j8Var == null) {
            return false;
        }
        boolean m87a = m87a();
        boolean m87a2 = j8Var.m87a();
        if ((m87a || m87a2) && !(m87a && m87a2 && this.f161a.equals(j8Var.f161a))) {
            return false;
        }
        boolean b = b();
        boolean b2 = j8Var.b();
        if ((b || b2) && !(b && b2 && this.f160a.m132a(j8Var.f160a))) {
            return false;
        }
        boolean c2 = c();
        boolean c3 = j8Var.c();
        if ((c2 || c3) && !(c2 && c3 && this.b.equals(j8Var.b))) {
            return false;
        }
        boolean d = d();
        boolean d2 = j8Var.d();
        if ((d || d2) && !(d && d2 && this.f18782c.equals(j8Var.f18782c))) {
            return false;
        }
        boolean e2 = e();
        boolean e3 = j8Var.e();
        if ((e2 || e3) && !(e2 && e3 && this.d.equals(j8Var.d))) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = j8Var.f();
        if ((f2 || f3) && !(f2 && f3 && this.f18783e.equals(j8Var.f18783e))) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = j8Var.g();
        if ((g2 || g3) && !(g2 && g3 && this.f18784f.equals(j8Var.f18784f))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = j8Var.h();
        if ((h2 || h3) && !(h2 && h3 && this.f18785g.equals(j8Var.f18785g))) {
            return false;
        }
        boolean i2 = i();
        boolean i3 = j8Var.i();
        if ((i2 || i3) && !(i2 && i3 && this.f18786h.equals(j8Var.f18786h))) {
            return false;
        }
        boolean j2 = j();
        boolean j3 = j8Var.j();
        if ((j2 || j3) && !(j2 && j3 && this.f18787i.equals(j8Var.f18787i))) {
            return false;
        }
        boolean k2 = k();
        boolean k3 = j8Var.k();
        if ((k2 || k3) && !(k2 && k3 && this.f163a == j8Var.f163a)) {
            return false;
        }
        boolean l2 = l();
        boolean l3 = j8Var.l();
        if (l2 || l3) {
            return l2 && l3 && this.a == j8Var.a;
        }
        return true;
    }

    public j8 b(String str) {
        this.f18782c = str;
        return this;
    }

    @Override // com.xiaomi.push.n8
    public void b(y8 y8Var) {
        a();
        y8Var.t(f18777j);
        if (this.f161a != null && m87a()) {
            y8Var.q(f18778k);
            y8Var.u(this.f161a);
            y8Var.z();
        }
        if (this.f160a != null && b()) {
            y8Var.q(f18779l);
            this.f160a.b(y8Var);
            y8Var.z();
        }
        if (this.b != null) {
            y8Var.q(f18780m);
            y8Var.u(this.b);
            y8Var.z();
        }
        if (this.f18782c != null) {
            y8Var.q(f18781n);
            y8Var.u(this.f18782c);
            y8Var.z();
        }
        if (this.d != null && e()) {
            y8Var.q(o);
            y8Var.u(this.d);
            y8Var.z();
        }
        if (this.f18783e != null && f()) {
            y8Var.q(p);
            y8Var.u(this.f18783e);
            y8Var.z();
        }
        if (this.f18784f != null && g()) {
            y8Var.q(q);
            y8Var.u(this.f18784f);
            y8Var.z();
        }
        if (this.f18785g != null && h()) {
            y8Var.q(r);
            y8Var.u(this.f18785g);
            y8Var.z();
        }
        if (this.f18786h != null && i()) {
            y8Var.q(s);
            y8Var.u(this.f18786h);
            y8Var.z();
        }
        if (this.f18787i != null && j()) {
            y8Var.q(t);
            y8Var.u(this.f18787i);
            y8Var.z();
        }
        if (k()) {
            y8Var.q(u);
            y8Var.x(this.f163a);
            y8Var.z();
        }
        if (l()) {
            y8Var.q(v);
            y8Var.p(this.a);
            y8Var.z();
        }
        y8Var.A();
        y8Var.m();
    }

    public void b(boolean z) {
        this.f162a.set(1, z);
    }

    public boolean b() {
        return this.f160a != null;
    }

    public j8 c(String str) {
        this.d = str;
        return this;
    }

    public boolean c() {
        return this.b != null;
    }

    public j8 d(String str) {
        this.f18784f = str;
        return this;
    }

    public boolean d() {
        return this.f18782c != null;
    }

    public j8 e(String str) {
        this.f18785g = str;
        return this;
    }

    public boolean e() {
        return this.d != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof j8)) {
            return m88a((j8) obj);
        }
        return false;
    }

    public boolean f() {
        return this.f18783e != null;
    }

    public boolean g() {
        return this.f18784f != null;
    }

    public boolean h() {
        return this.f18785g != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f18786h != null;
    }

    public boolean j() {
        return this.f18787i != null;
    }

    public boolean k() {
        return this.f162a.get(0);
    }

    public boolean l() {
        return this.f162a.get(1);
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionUnRegistration(");
        boolean z2 = false;
        if (m87a()) {
            sb.append("debug:");
            String str = this.f161a;
            if (str == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str);
            }
            z = false;
        } else {
            z = true;
        }
        if (b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            r7 r7Var = this.f160a;
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
        sb.append(", ");
        sb.append("appId:");
        String str3 = this.f18782c;
        if (str3 == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            sb.append(str3);
        }
        if (e()) {
            sb.append(", ");
            sb.append("regId:");
            String str4 = this.d;
            if (str4 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str4);
            }
        }
        if (f()) {
            sb.append(", ");
            sb.append("appVersion:");
            String str5 = this.f18783e;
            if (str5 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str5);
            }
        }
        if (g()) {
            sb.append(", ");
            sb.append("packageName:");
            String str6 = this.f18784f;
            if (str6 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str6);
            }
        }
        if (h()) {
            sb.append(", ");
            sb.append("token:");
            String str7 = this.f18785g;
            if (str7 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str7);
            }
        }
        if (i()) {
            sb.append(", ");
            sb.append("deviceId:");
            String str8 = this.f18786h;
            if (str8 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str8);
            }
        }
        if (j()) {
            sb.append(", ");
            sb.append("aliasName:");
            String str9 = this.f18787i;
            if (str9 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str9);
            }
        }
        if (k()) {
            sb.append(", ");
            sb.append("needAck:");
            sb.append(this.f163a);
        }
        if (l()) {
            sb.append(", ");
            sb.append("createdTs:");
            sb.append(this.a);
        }
        sb.append(")");
        return sb.toString();
    }
}
