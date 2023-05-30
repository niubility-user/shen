package com.xiaomi.push;

import com.jd.dynamic.DYConstants;
import java.io.Serializable;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes11.dex */
public class s7 implements n8<s7, Object>, Serializable, Cloneable {
    public int a;

    /* renamed from: a  reason: collision with other field name */
    public long f212a;

    /* renamed from: a  reason: collision with other field name */
    public g8 f213a;

    /* renamed from: a  reason: collision with other field name */
    public r7 f214a;

    /* renamed from: a  reason: collision with other field name */
    public String f215a;

    /* renamed from: a  reason: collision with other field name */
    public Map<String, String> f217a;

    /* renamed from: a  reason: collision with other field name */
    public short f218a;
    public String b;

    /* renamed from: b  reason: collision with other field name */
    public short f220b;

    /* renamed from: c  reason: collision with root package name */
    public String f19017c;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public String f19018e;

    /* renamed from: f  reason: collision with root package name */
    public String f19019f;

    /* renamed from: g  reason: collision with root package name */
    public String f19020g;

    /* renamed from: h  reason: collision with root package name */
    public String f19021h;

    /* renamed from: i  reason: collision with root package name */
    public String f19022i;

    /* renamed from: j  reason: collision with root package name */
    public String f19023j;

    /* renamed from: k  reason: collision with root package name */
    public String f19024k;

    /* renamed from: l  reason: collision with root package name */
    public String f19025l;

    /* renamed from: m  reason: collision with root package name */
    private static final e9 f19015m = new e9("XmPushActionAckMessage");

    /* renamed from: n  reason: collision with root package name */
    private static final v8 f19016n = new v8("", (byte) 11, 1);
    private static final v8 o = new v8("", (byte) 12, 2);
    private static final v8 p = new v8("", (byte) 11, 3);
    private static final v8 q = new v8("", (byte) 11, 4);
    private static final v8 r = new v8("", (byte) 10, 5);
    private static final v8 s = new v8("", (byte) 11, 6);
    private static final v8 t = new v8("", (byte) 11, 7);
    private static final v8 u = new v8("", (byte) 12, 8);
    private static final v8 v = new v8("", (byte) 11, 9);
    private static final v8 w = new v8("", (byte) 11, 10);
    private static final v8 x = new v8("", (byte) 2, 11);
    private static final v8 y = new v8("", (byte) 11, 12);
    private static final v8 z = new v8("", (byte) 11, 13);
    private static final v8 A = new v8("", (byte) 11, 14);
    private static final v8 B = new v8("", (byte) 6, 15);
    private static final v8 C = new v8("", (byte) 6, 16);
    private static final v8 D = new v8("", (byte) 11, 20);
    private static final v8 E = new v8("", (byte) 11, 21);
    private static final v8 F = new v8("", (byte) 8, 22);
    private static final v8 G = new v8("", (byte) 13, 23);

    /* renamed from: a  reason: collision with other field name */
    private BitSet f216a = new BitSet(5);

    /* renamed from: a  reason: collision with other field name */
    public boolean f219a = false;

    @Override // java.lang.Comparable
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public int compareTo(s7 s7Var) {
        int h2;
        int b;
        int e2;
        int e3;
        int j2;
        int j3;
        int e4;
        int e5;
        int e6;
        int k2;
        int e7;
        int e8;
        int d;
        int e9;
        int e10;
        int c2;
        int e11;
        int e12;
        int d2;
        int e13;
        if (getClass().equals(s7Var.getClass())) {
            int compareTo = Boolean.valueOf(m135a()).compareTo(Boolean.valueOf(s7Var.m135a()));
            if (compareTo != 0) {
                return compareTo;
            }
            if (!m135a() || (e13 = o8.e(this.f215a, s7Var.f215a)) == 0) {
                int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(s7Var.b()));
                if (compareTo2 != 0) {
                    return compareTo2;
                }
                if (!b() || (d2 = o8.d(this.f214a, s7Var.f214a)) == 0) {
                    int compareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(s7Var.c()));
                    if (compareTo3 != 0) {
                        return compareTo3;
                    }
                    if (!c() || (e12 = o8.e(this.b, s7Var.b)) == 0) {
                        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(s7Var.d()));
                        if (compareTo4 != 0) {
                            return compareTo4;
                        }
                        if (!d() || (e11 = o8.e(this.f19017c, s7Var.f19017c)) == 0) {
                            int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(s7Var.e()));
                            if (compareTo5 != 0) {
                                return compareTo5;
                            }
                            if (!e() || (c2 = o8.c(this.f212a, s7Var.f212a)) == 0) {
                                int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(s7Var.f()));
                                if (compareTo6 != 0) {
                                    return compareTo6;
                                }
                                if (!f() || (e10 = o8.e(this.d, s7Var.d)) == 0) {
                                    int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(s7Var.g()));
                                    if (compareTo7 != 0) {
                                        return compareTo7;
                                    }
                                    if (!g() || (e9 = o8.e(this.f19018e, s7Var.f19018e)) == 0) {
                                        int compareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(s7Var.h()));
                                        if (compareTo8 != 0) {
                                            return compareTo8;
                                        }
                                        if (!h() || (d = o8.d(this.f213a, s7Var.f213a)) == 0) {
                                            int compareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(s7Var.i()));
                                            if (compareTo9 != 0) {
                                                return compareTo9;
                                            }
                                            if (!i() || (e8 = o8.e(this.f19019f, s7Var.f19019f)) == 0) {
                                                int compareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(s7Var.j()));
                                                if (compareTo10 != 0) {
                                                    return compareTo10;
                                                }
                                                if (!j() || (e7 = o8.e(this.f19020g, s7Var.f19020g)) == 0) {
                                                    int compareTo11 = Boolean.valueOf(k()).compareTo(Boolean.valueOf(s7Var.k()));
                                                    if (compareTo11 != 0) {
                                                        return compareTo11;
                                                    }
                                                    if (!k() || (k2 = o8.k(this.f219a, s7Var.f219a)) == 0) {
                                                        int compareTo12 = Boolean.valueOf(l()).compareTo(Boolean.valueOf(s7Var.l()));
                                                        if (compareTo12 != 0) {
                                                            return compareTo12;
                                                        }
                                                        if (!l() || (e6 = o8.e(this.f19021h, s7Var.f19021h)) == 0) {
                                                            int compareTo13 = Boolean.valueOf(m()).compareTo(Boolean.valueOf(s7Var.m()));
                                                            if (compareTo13 != 0) {
                                                                return compareTo13;
                                                            }
                                                            if (!m() || (e5 = o8.e(this.f19022i, s7Var.f19022i)) == 0) {
                                                                int compareTo14 = Boolean.valueOf(n()).compareTo(Boolean.valueOf(s7Var.n()));
                                                                if (compareTo14 != 0) {
                                                                    return compareTo14;
                                                                }
                                                                if (!n() || (e4 = o8.e(this.f19023j, s7Var.f19023j)) == 0) {
                                                                    int compareTo15 = Boolean.valueOf(o()).compareTo(Boolean.valueOf(s7Var.o()));
                                                                    if (compareTo15 != 0) {
                                                                        return compareTo15;
                                                                    }
                                                                    if (!o() || (j3 = o8.j(this.f218a, s7Var.f218a)) == 0) {
                                                                        int compareTo16 = Boolean.valueOf(p()).compareTo(Boolean.valueOf(s7Var.p()));
                                                                        if (compareTo16 != 0) {
                                                                            return compareTo16;
                                                                        }
                                                                        if (!p() || (j2 = o8.j(this.f220b, s7Var.f220b)) == 0) {
                                                                            int compareTo17 = Boolean.valueOf(q()).compareTo(Boolean.valueOf(s7Var.q()));
                                                                            if (compareTo17 != 0) {
                                                                                return compareTo17;
                                                                            }
                                                                            if (!q() || (e3 = o8.e(this.f19024k, s7Var.f19024k)) == 0) {
                                                                                int compareTo18 = Boolean.valueOf(r()).compareTo(Boolean.valueOf(s7Var.r()));
                                                                                if (compareTo18 != 0) {
                                                                                    return compareTo18;
                                                                                }
                                                                                if (!r() || (e2 = o8.e(this.f19025l, s7Var.f19025l)) == 0) {
                                                                                    int compareTo19 = Boolean.valueOf(s()).compareTo(Boolean.valueOf(s7Var.s()));
                                                                                    if (compareTo19 != 0) {
                                                                                        return compareTo19;
                                                                                    }
                                                                                    if (!s() || (b = o8.b(this.a, s7Var.a)) == 0) {
                                                                                        int compareTo20 = Boolean.valueOf(t()).compareTo(Boolean.valueOf(s7Var.t()));
                                                                                        if (compareTo20 != 0) {
                                                                                            return compareTo20;
                                                                                        }
                                                                                        if (!t() || (h2 = o8.h(this.f217a, s7Var.f217a)) == 0) {
                                                                                            return 0;
                                                                                        }
                                                                                        return h2;
                                                                                    }
                                                                                    return b;
                                                                                }
                                                                                return e2;
                                                                            }
                                                                            return e3;
                                                                        }
                                                                        return j2;
                                                                    }
                                                                    return j3;
                                                                }
                                                                return e4;
                                                            }
                                                            return e5;
                                                        }
                                                        return e6;
                                                    }
                                                    return k2;
                                                }
                                                return e7;
                                            }
                                            return e8;
                                        }
                                        return d;
                                    }
                                    return e9;
                                }
                                return e10;
                            }
                            return c2;
                        }
                        return e11;
                    }
                    return e12;
                }
                return d2;
            }
            return e13;
        }
        return getClass().getName().compareTo(s7Var.getClass().getName());
    }

    public s7 a(long j2) {
        this.f212a = j2;
        a(true);
        return this;
    }

    public s7 a(String str) {
        this.b = str;
        return this;
    }

    public s7 a(short s2) {
        this.f218a = s2;
        c(true);
        return this;
    }

    public void a() {
        if (this.b == null) {
            throw new z8("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f19017c != null) {
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
                    a();
                    return;
                }
                throw new z8("Required field 'messageTs' was not found in serialized data! Struct: " + toString());
            }
            switch (e2.f19282c) {
                case 1:
                    if (b == 11) {
                        this.f215a = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 2:
                    if (b == 12) {
                        r7 r7Var = new r7();
                        this.f214a = r7Var;
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
                        this.f19017c = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 5:
                    if (b == 10) {
                        this.f212a = y8Var.d();
                        a(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 6:
                    if (b == 11) {
                        this.d = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 7:
                    if (b == 11) {
                        this.f19018e = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 8:
                    if (b == 12) {
                        g8 g8Var = new g8();
                        this.f213a = g8Var;
                        g8Var.a(y8Var);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 9:
                    if (b == 11) {
                        this.f19019f = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 10:
                    if (b == 11) {
                        this.f19020g = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 11:
                    if (b == 2) {
                        this.f219a = y8Var.y();
                        b(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 12:
                    if (b == 11) {
                        this.f19021h = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 13:
                    if (b == 11) {
                        this.f19022i = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 14:
                    if (b == 11) {
                        this.f19023j = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 15:
                    if (b == 6) {
                        this.f218a = y8Var.l();
                        c(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 16:
                    if (b == 6) {
                        this.f220b = y8Var.l();
                        d(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 20:
                    if (b == 11) {
                        this.f19024k = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 21:
                    if (b == 11) {
                        this.f19025l = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 22:
                    if (b == 8) {
                        this.a = y8Var.c();
                        e(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 23:
                    if (b == 13) {
                        x8 g2 = y8Var.g();
                        this.f217a = new HashMap(g2.f19321c * 2);
                        for (int i2 = 0; i2 < g2.f19321c; i2++) {
                            this.f217a.put(y8Var.j(), y8Var.j());
                        }
                        y8Var.F();
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
        this.f216a.set(0, z2);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m135a() {
        return this.f215a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m136a(s7 s7Var) {
        if (s7Var == null) {
            return false;
        }
        boolean m135a = m135a();
        boolean m135a2 = s7Var.m135a();
        if ((m135a || m135a2) && !(m135a && m135a2 && this.f215a.equals(s7Var.f215a))) {
            return false;
        }
        boolean b = b();
        boolean b2 = s7Var.b();
        if ((b || b2) && !(b && b2 && this.f214a.m132a(s7Var.f214a))) {
            return false;
        }
        boolean c2 = c();
        boolean c3 = s7Var.c();
        if ((c2 || c3) && !(c2 && c3 && this.b.equals(s7Var.b))) {
            return false;
        }
        boolean d = d();
        boolean d2 = s7Var.d();
        if (((d || d2) && !(d && d2 && this.f19017c.equals(s7Var.f19017c))) || this.f212a != s7Var.f212a) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = s7Var.f();
        if ((f2 || f3) && !(f2 && f3 && this.d.equals(s7Var.d))) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = s7Var.g();
        if ((g2 || g3) && !(g2 && g3 && this.f19018e.equals(s7Var.f19018e))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = s7Var.h();
        if ((h2 || h3) && !(h2 && h3 && this.f213a.m73a(s7Var.f213a))) {
            return false;
        }
        boolean i2 = i();
        boolean i3 = s7Var.i();
        if ((i2 || i3) && !(i2 && i3 && this.f19019f.equals(s7Var.f19019f))) {
            return false;
        }
        boolean j2 = j();
        boolean j3 = s7Var.j();
        if ((j2 || j3) && !(j2 && j3 && this.f19020g.equals(s7Var.f19020g))) {
            return false;
        }
        boolean k2 = k();
        boolean k3 = s7Var.k();
        if ((k2 || k3) && !(k2 && k3 && this.f219a == s7Var.f219a)) {
            return false;
        }
        boolean l2 = l();
        boolean l3 = s7Var.l();
        if ((l2 || l3) && !(l2 && l3 && this.f19021h.equals(s7Var.f19021h))) {
            return false;
        }
        boolean m2 = m();
        boolean m3 = s7Var.m();
        if ((m2 || m3) && !(m2 && m3 && this.f19022i.equals(s7Var.f19022i))) {
            return false;
        }
        boolean n2 = n();
        boolean n3 = s7Var.n();
        if ((n2 || n3) && !(n2 && n3 && this.f19023j.equals(s7Var.f19023j))) {
            return false;
        }
        boolean o2 = o();
        boolean o3 = s7Var.o();
        if ((o2 || o3) && !(o2 && o3 && this.f218a == s7Var.f218a)) {
            return false;
        }
        boolean p2 = p();
        boolean p3 = s7Var.p();
        if ((p2 || p3) && !(p2 && p3 && this.f220b == s7Var.f220b)) {
            return false;
        }
        boolean q2 = q();
        boolean q3 = s7Var.q();
        if ((q2 || q3) && !(q2 && q3 && this.f19024k.equals(s7Var.f19024k))) {
            return false;
        }
        boolean r2 = r();
        boolean r3 = s7Var.r();
        if ((r2 || r3) && !(r2 && r3 && this.f19025l.equals(s7Var.f19025l))) {
            return false;
        }
        boolean s2 = s();
        boolean s3 = s7Var.s();
        if ((s2 || s3) && !(s2 && s3 && this.a == s7Var.a)) {
            return false;
        }
        boolean t2 = t();
        boolean t3 = s7Var.t();
        if (t2 || t3) {
            return t2 && t3 && this.f217a.equals(s7Var.f217a);
        }
        return true;
    }

    public s7 b(String str) {
        this.f19017c = str;
        return this;
    }

    @Override // com.xiaomi.push.n8
    public void b(y8 y8Var) {
        a();
        y8Var.t(f19015m);
        if (this.f215a != null && m135a()) {
            y8Var.q(f19016n);
            y8Var.u(this.f215a);
            y8Var.z();
        }
        if (this.f214a != null && b()) {
            y8Var.q(o);
            this.f214a.b(y8Var);
            y8Var.z();
        }
        if (this.b != null) {
            y8Var.q(p);
            y8Var.u(this.b);
            y8Var.z();
        }
        if (this.f19017c != null) {
            y8Var.q(q);
            y8Var.u(this.f19017c);
            y8Var.z();
        }
        y8Var.q(r);
        y8Var.p(this.f212a);
        y8Var.z();
        if (this.d != null && f()) {
            y8Var.q(s);
            y8Var.u(this.d);
            y8Var.z();
        }
        if (this.f19018e != null && g()) {
            y8Var.q(t);
            y8Var.u(this.f19018e);
            y8Var.z();
        }
        if (this.f213a != null && h()) {
            y8Var.q(u);
            this.f213a.b(y8Var);
            y8Var.z();
        }
        if (this.f19019f != null && i()) {
            y8Var.q(v);
            y8Var.u(this.f19019f);
            y8Var.z();
        }
        if (this.f19020g != null && j()) {
            y8Var.q(w);
            y8Var.u(this.f19020g);
            y8Var.z();
        }
        if (k()) {
            y8Var.q(x);
            y8Var.x(this.f219a);
            y8Var.z();
        }
        if (this.f19021h != null && l()) {
            y8Var.q(y);
            y8Var.u(this.f19021h);
            y8Var.z();
        }
        if (this.f19022i != null && m()) {
            y8Var.q(z);
            y8Var.u(this.f19022i);
            y8Var.z();
        }
        if (this.f19023j != null && n()) {
            y8Var.q(A);
            y8Var.u(this.f19023j);
            y8Var.z();
        }
        if (o()) {
            y8Var.q(B);
            y8Var.w(this.f218a);
            y8Var.z();
        }
        if (p()) {
            y8Var.q(C);
            y8Var.w(this.f220b);
            y8Var.z();
        }
        if (this.f19024k != null && q()) {
            y8Var.q(D);
            y8Var.u(this.f19024k);
            y8Var.z();
        }
        if (this.f19025l != null && r()) {
            y8Var.q(E);
            y8Var.u(this.f19025l);
            y8Var.z();
        }
        if (s()) {
            y8Var.q(F);
            y8Var.o(this.a);
            y8Var.z();
        }
        if (this.f217a != null && t()) {
            y8Var.q(G);
            y8Var.s(new x8((byte) 11, (byte) 11, this.f217a.size()));
            for (Map.Entry<String, String> entry : this.f217a.entrySet()) {
                y8Var.u(entry.getKey());
                y8Var.u(entry.getValue());
            }
            y8Var.B();
            y8Var.z();
        }
        y8Var.A();
        y8Var.m();
    }

    public void b(boolean z2) {
        this.f216a.set(1, z2);
    }

    public boolean b() {
        return this.f214a != null;
    }

    public s7 c(String str) {
        this.d = str;
        return this;
    }

    public void c(boolean z2) {
        this.f216a.set(2, z2);
    }

    public boolean c() {
        return this.b != null;
    }

    public s7 d(String str) {
        this.f19018e = str;
        return this;
    }

    public void d(boolean z2) {
        this.f216a.set(3, z2);
    }

    public boolean d() {
        return this.f19017c != null;
    }

    public void e(boolean z2) {
        this.f216a.set(4, z2);
    }

    public boolean e() {
        return this.f216a.get(0);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof s7)) {
            return m136a((s7) obj);
        }
        return false;
    }

    public boolean f() {
        return this.d != null;
    }

    public boolean g() {
        return this.f19018e != null;
    }

    public boolean h() {
        return this.f213a != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f19019f != null;
    }

    public boolean j() {
        return this.f19020g != null;
    }

    public boolean k() {
        return this.f216a.get(1);
    }

    public boolean l() {
        return this.f19021h != null;
    }

    public boolean m() {
        return this.f19022i != null;
    }

    public boolean n() {
        return this.f19023j != null;
    }

    public boolean o() {
        return this.f216a.get(2);
    }

    public boolean p() {
        return this.f216a.get(3);
    }

    public boolean q() {
        return this.f19024k != null;
    }

    public boolean r() {
        return this.f19025l != null;
    }

    public boolean s() {
        return this.f216a.get(4);
    }

    public boolean t() {
        return this.f217a != null;
    }

    public String toString() {
        boolean z2;
        StringBuilder sb = new StringBuilder("XmPushActionAckMessage(");
        boolean z3 = false;
        if (m135a()) {
            sb.append("debug:");
            String str = this.f215a;
            if (str == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str);
            }
            z2 = false;
        } else {
            z2 = true;
        }
        if (b()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("target:");
            r7 r7Var = this.f214a;
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
        String str2 = this.b;
        if (str2 == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            sb.append(str2);
        }
        sb.append(", ");
        sb.append("appId:");
        String str3 = this.f19017c;
        if (str3 == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            sb.append(str3);
        }
        sb.append(", ");
        sb.append("messageTs:");
        sb.append(this.f212a);
        if (f()) {
            sb.append(", ");
            sb.append("topic:");
            String str4 = this.d;
            if (str4 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str4);
            }
        }
        if (g()) {
            sb.append(", ");
            sb.append("aliasName:");
            String str5 = this.f19018e;
            if (str5 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str5);
            }
        }
        if (h()) {
            sb.append(", ");
            sb.append("request:");
            g8 g8Var = this.f213a;
            if (g8Var == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(g8Var);
            }
        }
        if (i()) {
            sb.append(", ");
            sb.append("packageName:");
            String str6 = this.f19019f;
            if (str6 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str6);
            }
        }
        if (j()) {
            sb.append(", ");
            sb.append("category:");
            String str7 = this.f19020g;
            if (str7 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str7);
            }
        }
        if (k()) {
            sb.append(", ");
            sb.append("isOnline:");
            sb.append(this.f219a);
        }
        if (l()) {
            sb.append(", ");
            sb.append("regId:");
            String str8 = this.f19021h;
            if (str8 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str8);
            }
        }
        if (m()) {
            sb.append(", ");
            sb.append("callbackUrl:");
            String str9 = this.f19022i;
            if (str9 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str9);
            }
        }
        if (n()) {
            sb.append(", ");
            sb.append("userAccount:");
            String str10 = this.f19023j;
            if (str10 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str10);
            }
        }
        if (o()) {
            sb.append(", ");
            sb.append("deviceStatus:");
            sb.append((int) this.f218a);
        }
        if (p()) {
            sb.append(", ");
            sb.append("geoMsgStatus:");
            sb.append((int) this.f220b);
        }
        if (q()) {
            sb.append(", ");
            sb.append("imeiMd5:");
            String str11 = this.f19024k;
            if (str11 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str11);
            }
        }
        if (r()) {
            sb.append(", ");
            sb.append("deviceId:");
            String str12 = this.f19025l;
            if (str12 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str12);
            }
        }
        if (s()) {
            sb.append(", ");
            sb.append("passThrough:");
            sb.append(this.a);
        }
        if (t()) {
            sb.append(", ");
            sb.append("extra:");
            Map<String, String> map = this.f217a;
            if (map == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(map);
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
