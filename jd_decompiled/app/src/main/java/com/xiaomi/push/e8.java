package com.xiaomi.push;

import com.jd.dynamic.DYConstants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes11.dex */
public class e8 implements n8<e8, Object>, Serializable, Cloneable {
    public int a;

    /* renamed from: a  reason: collision with other field name */
    public long f130a;

    /* renamed from: a  reason: collision with other field name */
    public r7 f131a;

    /* renamed from: a  reason: collision with other field name */
    public String f132a;

    /* renamed from: a  reason: collision with other field name */
    public List<String> f134a;
    public int b;

    /* renamed from: b  reason: collision with other field name */
    public long f136b;

    /* renamed from: b  reason: collision with other field name */
    public String f137b;

    /* renamed from: c  reason: collision with root package name */
    public long f18587c;

    /* renamed from: c  reason: collision with other field name */
    public String f138c;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public String f18588e;

    /* renamed from: f  reason: collision with root package name */
    public String f18589f;

    /* renamed from: g  reason: collision with root package name */
    public String f18590g;

    /* renamed from: h  reason: collision with root package name */
    public String f18591h;

    /* renamed from: i  reason: collision with root package name */
    public String f18592i;

    /* renamed from: j  reason: collision with root package name */
    public String f18593j;

    /* renamed from: k  reason: collision with root package name */
    public String f18594k;

    /* renamed from: l  reason: collision with root package name */
    public String f18595l;

    /* renamed from: m  reason: collision with root package name */
    private static final e9 f18585m = new e9("XmPushActionRegistrationResult");

    /* renamed from: n  reason: collision with root package name */
    private static final v8 f18586n = new v8("", (byte) 11, 1);
    private static final v8 o = new v8("", (byte) 12, 2);
    private static final v8 p = new v8("", (byte) 11, 3);
    private static final v8 q = new v8("", (byte) 11, 4);
    private static final v8 r = new v8("", (byte) 10, 6);
    private static final v8 s = new v8("", (byte) 11, 7);
    private static final v8 t = new v8("", (byte) 11, 8);
    private static final v8 u = new v8("", (byte) 11, 9);
    private static final v8 v = new v8("", (byte) 11, 10);
    private static final v8 w = new v8("", (byte) 10, 11);
    private static final v8 x = new v8("", (byte) 11, 12);
    private static final v8 y = new v8("", (byte) 11, 13);
    private static final v8 z = new v8("", (byte) 10, 14);
    private static final v8 A = new v8("", (byte) 11, 15);
    private static final v8 B = new v8("", (byte) 8, 16);
    private static final v8 C = new v8("", (byte) 11, 17);
    private static final v8 D = new v8("", (byte) 8, 18);
    private static final v8 E = new v8("", (byte) 11, 19);
    private static final v8 F = new v8("", (byte) 2, 20);
    private static final v8 G = new v8("", (byte) 15, 21);

    /* renamed from: a  reason: collision with other field name */
    private BitSet f133a = new BitSet(6);

    /* renamed from: a  reason: collision with other field name */
    public boolean f135a = false;

    @Override // java.lang.Comparable
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public int compareTo(e8 e8Var) {
        int g2;
        int k2;
        int e2;
        int b;
        int e3;
        int b2;
        int e4;
        int c2;
        int e5;
        int e6;
        int c3;
        int e7;
        int e8;
        int e9;
        int e10;
        int c4;
        int e11;
        int e12;
        int d;
        int e13;
        if (getClass().equals(e8Var.getClass())) {
            int compareTo = Boolean.valueOf(m51a()).compareTo(Boolean.valueOf(e8Var.m51a()));
            if (compareTo != 0) {
                return compareTo;
            }
            if (!m51a() || (e13 = o8.e(this.f132a, e8Var.f132a)) == 0) {
                int compareTo2 = Boolean.valueOf(m53b()).compareTo(Boolean.valueOf(e8Var.m53b()));
                if (compareTo2 != 0) {
                    return compareTo2;
                }
                if (!m53b() || (d = o8.d(this.f131a, e8Var.f131a)) == 0) {
                    int compareTo3 = Boolean.valueOf(m54c()).compareTo(Boolean.valueOf(e8Var.m54c()));
                    if (compareTo3 != 0) {
                        return compareTo3;
                    }
                    if (!m54c() || (e12 = o8.e(this.f137b, e8Var.f137b)) == 0) {
                        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(e8Var.d()));
                        if (compareTo4 != 0) {
                            return compareTo4;
                        }
                        if (!d() || (e11 = o8.e(this.f138c, e8Var.f138c)) == 0) {
                            int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(e8Var.e()));
                            if (compareTo5 != 0) {
                                return compareTo5;
                            }
                            if (!e() || (c4 = o8.c(this.f130a, e8Var.f130a)) == 0) {
                                int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(e8Var.f()));
                                if (compareTo6 != 0) {
                                    return compareTo6;
                                }
                                if (!f() || (e10 = o8.e(this.d, e8Var.d)) == 0) {
                                    int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(e8Var.g()));
                                    if (compareTo7 != 0) {
                                        return compareTo7;
                                    }
                                    if (!g() || (e9 = o8.e(this.f18588e, e8Var.f18588e)) == 0) {
                                        int compareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(e8Var.h()));
                                        if (compareTo8 != 0) {
                                            return compareTo8;
                                        }
                                        if (!h() || (e8 = o8.e(this.f18589f, e8Var.f18589f)) == 0) {
                                            int compareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(e8Var.i()));
                                            if (compareTo9 != 0) {
                                                return compareTo9;
                                            }
                                            if (!i() || (e7 = o8.e(this.f18590g, e8Var.f18590g)) == 0) {
                                                int compareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(e8Var.j()));
                                                if (compareTo10 != 0) {
                                                    return compareTo10;
                                                }
                                                if (!j() || (c3 = o8.c(this.f136b, e8Var.f136b)) == 0) {
                                                    int compareTo11 = Boolean.valueOf(k()).compareTo(Boolean.valueOf(e8Var.k()));
                                                    if (compareTo11 != 0) {
                                                        return compareTo11;
                                                    }
                                                    if (!k() || (e6 = o8.e(this.f18591h, e8Var.f18591h)) == 0) {
                                                        int compareTo12 = Boolean.valueOf(l()).compareTo(Boolean.valueOf(e8Var.l()));
                                                        if (compareTo12 != 0) {
                                                            return compareTo12;
                                                        }
                                                        if (!l() || (e5 = o8.e(this.f18592i, e8Var.f18592i)) == 0) {
                                                            int compareTo13 = Boolean.valueOf(m()).compareTo(Boolean.valueOf(e8Var.m()));
                                                            if (compareTo13 != 0) {
                                                                return compareTo13;
                                                            }
                                                            if (!m() || (c2 = o8.c(this.f18587c, e8Var.f18587c)) == 0) {
                                                                int compareTo14 = Boolean.valueOf(n()).compareTo(Boolean.valueOf(e8Var.n()));
                                                                if (compareTo14 != 0) {
                                                                    return compareTo14;
                                                                }
                                                                if (!n() || (e4 = o8.e(this.f18593j, e8Var.f18593j)) == 0) {
                                                                    int compareTo15 = Boolean.valueOf(o()).compareTo(Boolean.valueOf(e8Var.o()));
                                                                    if (compareTo15 != 0) {
                                                                        return compareTo15;
                                                                    }
                                                                    if (!o() || (b2 = o8.b(this.a, e8Var.a)) == 0) {
                                                                        int compareTo16 = Boolean.valueOf(p()).compareTo(Boolean.valueOf(e8Var.p()));
                                                                        if (compareTo16 != 0) {
                                                                            return compareTo16;
                                                                        }
                                                                        if (!p() || (e3 = o8.e(this.f18594k, e8Var.f18594k)) == 0) {
                                                                            int compareTo17 = Boolean.valueOf(q()).compareTo(Boolean.valueOf(e8Var.q()));
                                                                            if (compareTo17 != 0) {
                                                                                return compareTo17;
                                                                            }
                                                                            if (!q() || (b = o8.b(this.b, e8Var.b)) == 0) {
                                                                                int compareTo18 = Boolean.valueOf(r()).compareTo(Boolean.valueOf(e8Var.r()));
                                                                                if (compareTo18 != 0) {
                                                                                    return compareTo18;
                                                                                }
                                                                                if (!r() || (e2 = o8.e(this.f18595l, e8Var.f18595l)) == 0) {
                                                                                    int compareTo19 = Boolean.valueOf(s()).compareTo(Boolean.valueOf(e8Var.s()));
                                                                                    if (compareTo19 != 0) {
                                                                                        return compareTo19;
                                                                                    }
                                                                                    if (!s() || (k2 = o8.k(this.f135a, e8Var.f135a)) == 0) {
                                                                                        int compareTo20 = Boolean.valueOf(t()).compareTo(Boolean.valueOf(e8Var.t()));
                                                                                        if (compareTo20 != 0) {
                                                                                            return compareTo20;
                                                                                        }
                                                                                        if (!t() || (g2 = o8.g(this.f134a, e8Var.f134a)) == 0) {
                                                                                            return 0;
                                                                                        }
                                                                                        return g2;
                                                                                    }
                                                                                    return k2;
                                                                                }
                                                                                return e2;
                                                                            }
                                                                            return b;
                                                                        }
                                                                        return e3;
                                                                    }
                                                                    return b2;
                                                                }
                                                                return e4;
                                                            }
                                                            return c2;
                                                        }
                                                        return e5;
                                                    }
                                                    return e6;
                                                }
                                                return c3;
                                            }
                                            return e7;
                                        }
                                        return e8;
                                    }
                                    return e9;
                                }
                                return e10;
                            }
                            return c4;
                        }
                        return e11;
                    }
                    return e12;
                }
                return d;
            }
            return e13;
        }
        return getClass().getName().compareTo(e8Var.getClass().getName());
    }

    public long a() {
        return this.f130a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public String m48a() {
        return this.f137b;
    }

    /* renamed from: a  reason: collision with other method in class */
    public List<String> m49a() {
        return this.f134a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m50a() {
        if (this.f137b == null) {
            throw new z8("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f138c != null) {
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
                if (e()) {
                    m50a();
                    return;
                }
                throw new z8("Required field 'errorCode' was not found in serialized data! Struct: " + toString());
            }
            switch (e2.f19282c) {
                case 1:
                    if (b == 11) {
                        this.f132a = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 2:
                    if (b == 12) {
                        r7 r7Var = new r7();
                        this.f131a = r7Var;
                        r7Var.a(y8Var);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 3:
                    if (b == 11) {
                        this.f137b = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 4:
                    if (b == 11) {
                        this.f138c = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 6:
                    if (b == 10) {
                        this.f130a = y8Var.d();
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
                        this.f18588e = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 9:
                    if (b == 11) {
                        this.f18589f = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 10:
                    if (b == 11) {
                        this.f18590g = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 11:
                    if (b == 10) {
                        this.f136b = y8Var.d();
                        b(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 12:
                    if (b == 11) {
                        this.f18591h = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 13:
                    if (b == 11) {
                        this.f18592i = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 14:
                    if (b == 10) {
                        this.f18587c = y8Var.d();
                        c(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 15:
                    if (b == 11) {
                        this.f18593j = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 16:
                    if (b == 8) {
                        this.a = y8Var.c();
                        d(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 17:
                    if (b == 11) {
                        this.f18594k = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 18:
                    if (b == 8) {
                        this.b = y8Var.c();
                        e(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 19:
                    if (b == 11) {
                        this.f18595l = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 20:
                    if (b == 2) {
                        this.f135a = y8Var.y();
                        f(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 21:
                    if (b == 15) {
                        w8 f2 = y8Var.f();
                        this.f134a = new ArrayList(f2.b);
                        for (int i2 = 0; i2 < f2.b; i2++) {
                            this.f134a.add(y8Var.j());
                        }
                        y8Var.G();
                        continue;
                        y8Var.E();
                    }
                    break;
            }
            c9.a(y8Var, b);
            y8Var.E();
        }
    }

    public void a(boolean z2) {
        this.f133a.set(0, z2);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m51a() {
        return this.f132a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m52a(e8 e8Var) {
        if (e8Var == null) {
            return false;
        }
        boolean m51a = m51a();
        boolean m51a2 = e8Var.m51a();
        if ((m51a || m51a2) && !(m51a && m51a2 && this.f132a.equals(e8Var.f132a))) {
            return false;
        }
        boolean m53b = m53b();
        boolean m53b2 = e8Var.m53b();
        if ((m53b || m53b2) && !(m53b && m53b2 && this.f131a.m132a(e8Var.f131a))) {
            return false;
        }
        boolean m54c = m54c();
        boolean m54c2 = e8Var.m54c();
        if ((m54c || m54c2) && !(m54c && m54c2 && this.f137b.equals(e8Var.f137b))) {
            return false;
        }
        boolean d = d();
        boolean d2 = e8Var.d();
        if (((d || d2) && !(d && d2 && this.f138c.equals(e8Var.f138c))) || this.f130a != e8Var.f130a) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = e8Var.f();
        if ((f2 || f3) && !(f2 && f3 && this.d.equals(e8Var.d))) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = e8Var.g();
        if ((g2 || g3) && !(g2 && g3 && this.f18588e.equals(e8Var.f18588e))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = e8Var.h();
        if ((h2 || h3) && !(h2 && h3 && this.f18589f.equals(e8Var.f18589f))) {
            return false;
        }
        boolean i2 = i();
        boolean i3 = e8Var.i();
        if ((i2 || i3) && !(i2 && i3 && this.f18590g.equals(e8Var.f18590g))) {
            return false;
        }
        boolean j2 = j();
        boolean j3 = e8Var.j();
        if ((j2 || j3) && !(j2 && j3 && this.f136b == e8Var.f136b)) {
            return false;
        }
        boolean k2 = k();
        boolean k3 = e8Var.k();
        if ((k2 || k3) && !(k2 && k3 && this.f18591h.equals(e8Var.f18591h))) {
            return false;
        }
        boolean l2 = l();
        boolean l3 = e8Var.l();
        if ((l2 || l3) && !(l2 && l3 && this.f18592i.equals(e8Var.f18592i))) {
            return false;
        }
        boolean m2 = m();
        boolean m3 = e8Var.m();
        if ((m2 || m3) && !(m2 && m3 && this.f18587c == e8Var.f18587c)) {
            return false;
        }
        boolean n2 = n();
        boolean n3 = e8Var.n();
        if ((n2 || n3) && !(n2 && n3 && this.f18593j.equals(e8Var.f18593j))) {
            return false;
        }
        boolean o2 = o();
        boolean o3 = e8Var.o();
        if ((o2 || o3) && !(o2 && o3 && this.a == e8Var.a)) {
            return false;
        }
        boolean p2 = p();
        boolean p3 = e8Var.p();
        if ((p2 || p3) && !(p2 && p3 && this.f18594k.equals(e8Var.f18594k))) {
            return false;
        }
        boolean q2 = q();
        boolean q3 = e8Var.q();
        if ((q2 || q3) && !(q2 && q3 && this.b == e8Var.b)) {
            return false;
        }
        boolean r2 = r();
        boolean r3 = e8Var.r();
        if ((r2 || r3) && !(r2 && r3 && this.f18595l.equals(e8Var.f18595l))) {
            return false;
        }
        boolean s2 = s();
        boolean s3 = e8Var.s();
        if ((s2 || s3) && !(s2 && s3 && this.f135a == e8Var.f135a)) {
            return false;
        }
        boolean t2 = t();
        boolean t3 = e8Var.t();
        if (t2 || t3) {
            return t2 && t3 && this.f134a.equals(e8Var.f134a);
        }
        return true;
    }

    public String b() {
        return this.f18589f;
    }

    @Override // com.xiaomi.push.n8
    public void b(y8 y8Var) {
        m50a();
        y8Var.t(f18585m);
        if (this.f132a != null && m51a()) {
            y8Var.q(f18586n);
            y8Var.u(this.f132a);
            y8Var.z();
        }
        if (this.f131a != null && m53b()) {
            y8Var.q(o);
            this.f131a.b(y8Var);
            y8Var.z();
        }
        if (this.f137b != null) {
            y8Var.q(p);
            y8Var.u(this.f137b);
            y8Var.z();
        }
        if (this.f138c != null) {
            y8Var.q(q);
            y8Var.u(this.f138c);
            y8Var.z();
        }
        y8Var.q(r);
        y8Var.p(this.f130a);
        y8Var.z();
        if (this.d != null && f()) {
            y8Var.q(s);
            y8Var.u(this.d);
            y8Var.z();
        }
        if (this.f18588e != null && g()) {
            y8Var.q(t);
            y8Var.u(this.f18588e);
            y8Var.z();
        }
        if (this.f18589f != null && h()) {
            y8Var.q(u);
            y8Var.u(this.f18589f);
            y8Var.z();
        }
        if (this.f18590g != null && i()) {
            y8Var.q(v);
            y8Var.u(this.f18590g);
            y8Var.z();
        }
        if (j()) {
            y8Var.q(w);
            y8Var.p(this.f136b);
            y8Var.z();
        }
        if (this.f18591h != null && k()) {
            y8Var.q(x);
            y8Var.u(this.f18591h);
            y8Var.z();
        }
        if (this.f18592i != null && l()) {
            y8Var.q(y);
            y8Var.u(this.f18592i);
            y8Var.z();
        }
        if (m()) {
            y8Var.q(z);
            y8Var.p(this.f18587c);
            y8Var.z();
        }
        if (this.f18593j != null && n()) {
            y8Var.q(A);
            y8Var.u(this.f18593j);
            y8Var.z();
        }
        if (o()) {
            y8Var.q(B);
            y8Var.o(this.a);
            y8Var.z();
        }
        if (this.f18594k != null && p()) {
            y8Var.q(C);
            y8Var.u(this.f18594k);
            y8Var.z();
        }
        if (q()) {
            y8Var.q(D);
            y8Var.o(this.b);
            y8Var.z();
        }
        if (this.f18595l != null && r()) {
            y8Var.q(E);
            y8Var.u(this.f18595l);
            y8Var.z();
        }
        if (s()) {
            y8Var.q(F);
            y8Var.x(this.f135a);
            y8Var.z();
        }
        if (this.f134a != null && t()) {
            y8Var.q(G);
            y8Var.r(new w8((byte) 11, this.f134a.size()));
            Iterator<String> it = this.f134a.iterator();
            while (it.hasNext()) {
                y8Var.u(it.next());
            }
            y8Var.C();
            y8Var.z();
        }
        y8Var.A();
        y8Var.m();
    }

    public void b(boolean z2) {
        this.f133a.set(1, z2);
    }

    /* renamed from: b  reason: collision with other method in class */
    public boolean m53b() {
        return this.f131a != null;
    }

    public String c() {
        return this.f18590g;
    }

    public void c(boolean z2) {
        this.f133a.set(2, z2);
    }

    /* renamed from: c  reason: collision with other method in class */
    public boolean m54c() {
        return this.f137b != null;
    }

    public void d(boolean z2) {
        this.f133a.set(3, z2);
    }

    public boolean d() {
        return this.f138c != null;
    }

    public void e(boolean z2) {
        this.f133a.set(4, z2);
    }

    public boolean e() {
        return this.f133a.get(0);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof e8)) {
            return m52a((e8) obj);
        }
        return false;
    }

    public void f(boolean z2) {
        this.f133a.set(5, z2);
    }

    public boolean f() {
        return this.d != null;
    }

    public boolean g() {
        return this.f18588e != null;
    }

    public boolean h() {
        return this.f18589f != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f18590g != null;
    }

    public boolean j() {
        return this.f133a.get(1);
    }

    public boolean k() {
        return this.f18591h != null;
    }

    public boolean l() {
        return this.f18592i != null;
    }

    public boolean m() {
        return this.f133a.get(2);
    }

    public boolean n() {
        return this.f18593j != null;
    }

    public boolean o() {
        return this.f133a.get(3);
    }

    public boolean p() {
        return this.f18594k != null;
    }

    public boolean q() {
        return this.f133a.get(4);
    }

    public boolean r() {
        return this.f18595l != null;
    }

    public boolean s() {
        return this.f133a.get(5);
    }

    public boolean t() {
        return this.f134a != null;
    }

    public String toString() {
        boolean z2;
        StringBuilder sb = new StringBuilder("XmPushActionRegistrationResult(");
        boolean z3 = false;
        if (m51a()) {
            sb.append("debug:");
            String str = this.f132a;
            if (str == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str);
            }
            z2 = false;
        } else {
            z2 = true;
        }
        if (m53b()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("target:");
            r7 r7Var = this.f131a;
            if (r7Var == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(r7Var);
            }
        } else {
            z3 = z2;
        }
        if (!z3) {
            sb.append(", ");
        }
        sb.append("id:");
        String str2 = this.f137b;
        if (str2 == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            sb.append(com.xiaomi.push.service.f0.b(str2));
        }
        sb.append(", ");
        sb.append("appId:");
        String str3 = this.f138c;
        if (str3 == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            sb.append(str3);
        }
        sb.append(", ");
        sb.append("errorCode:");
        sb.append(this.f130a);
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
            sb.append("regId:");
            String str5 = this.f18588e;
            if (str5 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str5);
            }
        }
        if (i()) {
            sb.append(", ");
            sb.append("packageName:");
            String str6 = this.f18590g;
            if (str6 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str6);
            }
        }
        if (j()) {
            sb.append(", ");
            sb.append("registeredAt:");
            sb.append(this.f136b);
        }
        if (k()) {
            sb.append(", ");
            sb.append("aliasName:");
            String str7 = this.f18591h;
            if (str7 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str7);
            }
        }
        if (l()) {
            sb.append(", ");
            sb.append("clientId:");
            String str8 = this.f18592i;
            if (str8 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str8);
            }
        }
        if (m()) {
            sb.append(", ");
            sb.append("costTime:");
            sb.append(this.f18587c);
        }
        if (n()) {
            sb.append(", ");
            sb.append("appVersion:");
            String str9 = this.f18593j;
            if (str9 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str9);
            }
        }
        if (o()) {
            sb.append(", ");
            sb.append("pushSdkVersionCode:");
            sb.append(this.a);
        }
        if (p()) {
            sb.append(", ");
            sb.append("hybridPushEndpoint:");
            String str10 = this.f18594k;
            if (str10 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str10);
            }
        }
        if (q()) {
            sb.append(", ");
            sb.append("appVersionCode:");
            sb.append(this.b);
        }
        if (r()) {
            sb.append(", ");
            sb.append("region:");
            String str11 = this.f18595l;
            if (str11 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str11);
            }
        }
        if (s()) {
            sb.append(", ");
            sb.append("isHybridFrame:");
            sb.append(this.f135a);
        }
        if (t()) {
            sb.append(", ");
            sb.append("autoMarkPkgs:");
            List<String> list = this.f134a;
            if (list == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(list);
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
