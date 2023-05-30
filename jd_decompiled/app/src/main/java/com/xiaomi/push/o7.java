package com.xiaomi.push;

import com.jd.dynamic.DYConstants;
import java.io.Serializable;
import java.util.BitSet;

/* loaded from: classes11.dex */
public class o7 implements n8<o7, Object>, Serializable, Cloneable {
    public long a;

    /* renamed from: a */
    public p7 f185a;

    /* renamed from: a */
    public r7 f186a;

    /* renamed from: a */
    public String f187a;

    /* renamed from: a */
    private BitSet f188a = new BitSet(4);

    /* renamed from: a */
    public boolean f189a = false;
    public long b;

    /* renamed from: b */
    public String f190b;

    /* renamed from: c */
    public long f18908c;

    /* renamed from: c */
    public String f191c;
    public String d;

    /* renamed from: e */
    public String f18909e;

    /* renamed from: f */
    public String f18910f;

    /* renamed from: g */
    public String f18911g;

    /* renamed from: h */
    public String f18912h;

    /* renamed from: i */
    public String f18913i;

    /* renamed from: j */
    public String f18914j;

    /* renamed from: k */
    public String f18915k;

    /* renamed from: l */
    public String f18916l;

    /* renamed from: m */
    private static final e9 f18906m = new e9("PushMessage");

    /* renamed from: n */
    private static final v8 f18907n = new v8("", (byte) 12, 1);
    private static final v8 o = new v8("", (byte) 11, 2);
    private static final v8 p = new v8("", (byte) 11, 3);
    private static final v8 q = new v8("", (byte) 11, 4);
    private static final v8 r = new v8("", (byte) 10, 5);
    private static final v8 s = new v8("", (byte) 10, 6);
    private static final v8 t = new v8("", (byte) 11, 7);
    private static final v8 u = new v8("", (byte) 11, 8);
    private static final v8 v = new v8("", (byte) 11, 9);
    private static final v8 w = new v8("", (byte) 11, 10);
    private static final v8 x = new v8("", (byte) 11, 11);
    private static final v8 y = new v8("", (byte) 12, 12);
    private static final v8 z = new v8("", (byte) 11, 13);
    private static final v8 A = new v8("", (byte) 2, 14);
    private static final v8 B = new v8("", (byte) 11, 15);
    private static final v8 C = new v8("", (byte) 10, 16);
    private static final v8 D = new v8("", (byte) 11, 20);
    private static final v8 E = new v8("", (byte) 11, 21);

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(o7 o7Var) {
        int e2;
        int e3;
        int c2;
        int e4;
        int k2;
        int e5;
        int d;
        int e6;
        int e7;
        int e8;
        int e9;
        int e10;
        int c3;
        int c4;
        int e11;
        int e12;
        int e13;
        int d2;
        if (getClass().equals(o7Var.getClass())) {
            int compareTo = Boolean.valueOf(m114a()).compareTo(Boolean.valueOf(o7Var.m114a()));
            if (compareTo != 0) {
                return compareTo;
            }
            if (!m114a() || (d2 = o8.d(this.f186a, o7Var.f186a)) == 0) {
                int compareTo2 = Boolean.valueOf(m116b()).compareTo(Boolean.valueOf(o7Var.m116b()));
                if (compareTo2 != 0) {
                    return compareTo2;
                }
                if (!m116b() || (e13 = o8.e(this.f187a, o7Var.f187a)) == 0) {
                    int compareTo3 = Boolean.valueOf(m117c()).compareTo(Boolean.valueOf(o7Var.m117c()));
                    if (compareTo3 != 0) {
                        return compareTo3;
                    }
                    if (!m117c() || (e12 = o8.e(this.f190b, o7Var.f190b)) == 0) {
                        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(o7Var.d()));
                        if (compareTo4 != 0) {
                            return compareTo4;
                        }
                        if (!d() || (e11 = o8.e(this.f191c, o7Var.f191c)) == 0) {
                            int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(o7Var.e()));
                            if (compareTo5 != 0) {
                                return compareTo5;
                            }
                            if (!e() || (c4 = o8.c(this.a, o7Var.a)) == 0) {
                                int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(o7Var.f()));
                                if (compareTo6 != 0) {
                                    return compareTo6;
                                }
                                if (!f() || (c3 = o8.c(this.b, o7Var.b)) == 0) {
                                    int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(o7Var.g()));
                                    if (compareTo7 != 0) {
                                        return compareTo7;
                                    }
                                    if (!g() || (e10 = o8.e(this.d, o7Var.d)) == 0) {
                                        int compareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(o7Var.h()));
                                        if (compareTo8 != 0) {
                                            return compareTo8;
                                        }
                                        if (!h() || (e9 = o8.e(this.f18909e, o7Var.f18909e)) == 0) {
                                            int compareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(o7Var.i()));
                                            if (compareTo9 != 0) {
                                                return compareTo9;
                                            }
                                            if (!i() || (e8 = o8.e(this.f18910f, o7Var.f18910f)) == 0) {
                                                int compareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(o7Var.j()));
                                                if (compareTo10 != 0) {
                                                    return compareTo10;
                                                }
                                                if (!j() || (e7 = o8.e(this.f18911g, o7Var.f18911g)) == 0) {
                                                    int compareTo11 = Boolean.valueOf(k()).compareTo(Boolean.valueOf(o7Var.k()));
                                                    if (compareTo11 != 0) {
                                                        return compareTo11;
                                                    }
                                                    if (!k() || (e6 = o8.e(this.f18912h, o7Var.f18912h)) == 0) {
                                                        int compareTo12 = Boolean.valueOf(l()).compareTo(Boolean.valueOf(o7Var.l()));
                                                        if (compareTo12 != 0) {
                                                            return compareTo12;
                                                        }
                                                        if (!l() || (d = o8.d(this.f185a, o7Var.f185a)) == 0) {
                                                            int compareTo13 = Boolean.valueOf(m()).compareTo(Boolean.valueOf(o7Var.m()));
                                                            if (compareTo13 != 0) {
                                                                return compareTo13;
                                                            }
                                                            if (!m() || (e5 = o8.e(this.f18913i, o7Var.f18913i)) == 0) {
                                                                int compareTo14 = Boolean.valueOf(n()).compareTo(Boolean.valueOf(o7Var.n()));
                                                                if (compareTo14 != 0) {
                                                                    return compareTo14;
                                                                }
                                                                if (!n() || (k2 = o8.k(this.f189a, o7Var.f189a)) == 0) {
                                                                    int compareTo15 = Boolean.valueOf(o()).compareTo(Boolean.valueOf(o7Var.o()));
                                                                    if (compareTo15 != 0) {
                                                                        return compareTo15;
                                                                    }
                                                                    if (!o() || (e4 = o8.e(this.f18914j, o7Var.f18914j)) == 0) {
                                                                        int compareTo16 = Boolean.valueOf(p()).compareTo(Boolean.valueOf(o7Var.p()));
                                                                        if (compareTo16 != 0) {
                                                                            return compareTo16;
                                                                        }
                                                                        if (!p() || (c2 = o8.c(this.f18908c, o7Var.f18908c)) == 0) {
                                                                            int compareTo17 = Boolean.valueOf(q()).compareTo(Boolean.valueOf(o7Var.q()));
                                                                            if (compareTo17 != 0) {
                                                                                return compareTo17;
                                                                            }
                                                                            if (!q() || (e3 = o8.e(this.f18915k, o7Var.f18915k)) == 0) {
                                                                                int compareTo18 = Boolean.valueOf(r()).compareTo(Boolean.valueOf(o7Var.r()));
                                                                                if (compareTo18 != 0) {
                                                                                    return compareTo18;
                                                                                }
                                                                                if (!r() || (e2 = o8.e(this.f18916l, o7Var.f18916l)) == 0) {
                                                                                    return 0;
                                                                                }
                                                                                return e2;
                                                                            }
                                                                            return e3;
                                                                        }
                                                                        return c2;
                                                                    }
                                                                    return e4;
                                                                }
                                                                return k2;
                                                            }
                                                            return e5;
                                                        }
                                                        return d;
                                                    }
                                                    return e6;
                                                }
                                                return e7;
                                            }
                                            return e8;
                                        }
                                        return e9;
                                    }
                                    return e10;
                                }
                                return c3;
                            }
                            return c4;
                        }
                        return e11;
                    }
                    return e12;
                }
                return e13;
            }
            return d2;
        }
        return getClass().getName().compareTo(o7Var.getClass().getName());
    }

    public long a() {
        return this.a;
    }

    /* renamed from: a */
    public String m112a() {
        return this.f187a;
    }

    /* renamed from: a */
    public void m113a() {
        if (this.f187a == null) {
            throw new z8("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f190b == null) {
            throw new z8("Required field 'appId' was not present! Struct: " + toString());
        } else if (this.f191c != null) {
        } else {
            throw new z8("Required field 'payload' was not present! Struct: " + toString());
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.xiaomi.push.n8
    public void a(y8 y8Var) {
        y8Var.i();
        while (true) {
            v8 e2 = y8Var.e();
            byte b = e2.b;
            if (b == 0) {
                y8Var.D();
                m113a();
                return;
            }
            short s2 = e2.f19282c;
            if (s2 == 20) {
                if (b == 11) {
                    this.f18915k = y8Var.j();
                    y8Var.E();
                }
                c9.a(y8Var, b);
                y8Var.E();
            } else if (s2 != 21) {
                switch (s2) {
                    case 1:
                        if (b == 12) {
                            r7 r7Var = new r7();
                            this.f186a = r7Var;
                            r7Var.a(y8Var);
                            break;
                        }
                        c9.a(y8Var, b);
                        break;
                    case 2:
                        if (b == 11) {
                            this.f187a = y8Var.j();
                            break;
                        }
                        c9.a(y8Var, b);
                        break;
                    case 3:
                        if (b == 11) {
                            this.f190b = y8Var.j();
                            break;
                        }
                        c9.a(y8Var, b);
                        break;
                    case 4:
                        if (b == 11) {
                            this.f191c = y8Var.j();
                            break;
                        }
                        c9.a(y8Var, b);
                        break;
                    case 5:
                        if (b == 10) {
                            this.a = y8Var.d();
                            a(true);
                            break;
                        }
                        c9.a(y8Var, b);
                        break;
                    case 6:
                        if (b == 10) {
                            this.b = y8Var.d();
                            b(true);
                            break;
                        }
                        c9.a(y8Var, b);
                        break;
                    case 7:
                        if (b == 11) {
                            this.d = y8Var.j();
                            break;
                        }
                        c9.a(y8Var, b);
                        break;
                    case 8:
                        if (b == 11) {
                            this.f18909e = y8Var.j();
                            break;
                        }
                        c9.a(y8Var, b);
                        break;
                    case 9:
                        if (b == 11) {
                            this.f18910f = y8Var.j();
                            break;
                        }
                        c9.a(y8Var, b);
                        break;
                    case 10:
                        if (b == 11) {
                            this.f18911g = y8Var.j();
                            break;
                        }
                        c9.a(y8Var, b);
                        break;
                    case 11:
                        if (b == 11) {
                            this.f18912h = y8Var.j();
                            break;
                        }
                        c9.a(y8Var, b);
                        break;
                    case 12:
                        if (b == 12) {
                            p7 p7Var = new p7();
                            this.f185a = p7Var;
                            p7Var.a(y8Var);
                            break;
                        }
                        c9.a(y8Var, b);
                        break;
                    case 13:
                        if (b == 11) {
                            this.f18913i = y8Var.j();
                            break;
                        }
                        c9.a(y8Var, b);
                        break;
                    case 14:
                        if (b == 2) {
                            this.f189a = y8Var.y();
                            c(true);
                            break;
                        }
                        c9.a(y8Var, b);
                        break;
                    case 15:
                        if (b == 11) {
                            this.f18914j = y8Var.j();
                            break;
                        }
                        c9.a(y8Var, b);
                        break;
                    case 16:
                        if (b == 10) {
                            this.f18908c = y8Var.d();
                            d(true);
                            break;
                        }
                        c9.a(y8Var, b);
                        break;
                    default:
                        c9.a(y8Var, b);
                        break;
                }
                y8Var.E();
            } else {
                if (b == 11) {
                    this.f18916l = y8Var.j();
                    y8Var.E();
                }
                c9.a(y8Var, b);
                y8Var.E();
            }
        }
    }

    public void a(boolean z2) {
        this.f188a.set(0, z2);
    }

    /* renamed from: a */
    public boolean m114a() {
        return this.f186a != null;
    }

    /* renamed from: a */
    public boolean m115a(o7 o7Var) {
        if (o7Var == null) {
            return false;
        }
        boolean m114a = m114a();
        boolean m114a2 = o7Var.m114a();
        if ((m114a || m114a2) && !(m114a && m114a2 && this.f186a.m132a(o7Var.f186a))) {
            return false;
        }
        boolean m116b = m116b();
        boolean m116b2 = o7Var.m116b();
        if ((m116b || m116b2) && !(m116b && m116b2 && this.f187a.equals(o7Var.f187a))) {
            return false;
        }
        boolean m117c = m117c();
        boolean m117c2 = o7Var.m117c();
        if ((m117c || m117c2) && !(m117c && m117c2 && this.f190b.equals(o7Var.f190b))) {
            return false;
        }
        boolean d = d();
        boolean d2 = o7Var.d();
        if ((d || d2) && !(d && d2 && this.f191c.equals(o7Var.f191c))) {
            return false;
        }
        boolean e2 = e();
        boolean e3 = o7Var.e();
        if ((e2 || e3) && !(e2 && e3 && this.a == o7Var.a)) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = o7Var.f();
        if ((f2 || f3) && !(f2 && f3 && this.b == o7Var.b)) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = o7Var.g();
        if ((g2 || g3) && !(g2 && g3 && this.d.equals(o7Var.d))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = o7Var.h();
        if ((h2 || h3) && !(h2 && h3 && this.f18909e.equals(o7Var.f18909e))) {
            return false;
        }
        boolean i2 = i();
        boolean i3 = o7Var.i();
        if ((i2 || i3) && !(i2 && i3 && this.f18910f.equals(o7Var.f18910f))) {
            return false;
        }
        boolean j2 = j();
        boolean j3 = o7Var.j();
        if ((j2 || j3) && !(j2 && j3 && this.f18911g.equals(o7Var.f18911g))) {
            return false;
        }
        boolean k2 = k();
        boolean k3 = o7Var.k();
        if ((k2 || k3) && !(k2 && k3 && this.f18912h.equals(o7Var.f18912h))) {
            return false;
        }
        boolean l2 = l();
        boolean l3 = o7Var.l();
        if ((l2 || l3) && !(l2 && l3 && this.f185a.m124a(o7Var.f185a))) {
            return false;
        }
        boolean m2 = m();
        boolean m3 = o7Var.m();
        if ((m2 || m3) && !(m2 && m3 && this.f18913i.equals(o7Var.f18913i))) {
            return false;
        }
        boolean n2 = n();
        boolean n3 = o7Var.n();
        if ((n2 || n3) && !(n2 && n3 && this.f189a == o7Var.f189a)) {
            return false;
        }
        boolean o2 = o();
        boolean o3 = o7Var.o();
        if ((o2 || o3) && !(o2 && o3 && this.f18914j.equals(o7Var.f18914j))) {
            return false;
        }
        boolean p2 = p();
        boolean p3 = o7Var.p();
        if ((p2 || p3) && !(p2 && p3 && this.f18908c == o7Var.f18908c)) {
            return false;
        }
        boolean q2 = q();
        boolean q3 = o7Var.q();
        if ((q2 || q3) && !(q2 && q3 && this.f18915k.equals(o7Var.f18915k))) {
            return false;
        }
        boolean r2 = r();
        boolean r3 = o7Var.r();
        if (r2 || r3) {
            return r2 && r3 && this.f18916l.equals(o7Var.f18916l);
        }
        return true;
    }

    public String b() {
        return this.f190b;
    }

    @Override // com.xiaomi.push.n8
    public void b(y8 y8Var) {
        m113a();
        y8Var.t(f18906m);
        if (this.f186a != null && m114a()) {
            y8Var.q(f18907n);
            this.f186a.b(y8Var);
            y8Var.z();
        }
        if (this.f187a != null) {
            y8Var.q(o);
            y8Var.u(this.f187a);
            y8Var.z();
        }
        if (this.f190b != null) {
            y8Var.q(p);
            y8Var.u(this.f190b);
            y8Var.z();
        }
        if (this.f191c != null) {
            y8Var.q(q);
            y8Var.u(this.f191c);
            y8Var.z();
        }
        if (e()) {
            y8Var.q(r);
            y8Var.p(this.a);
            y8Var.z();
        }
        if (f()) {
            y8Var.q(s);
            y8Var.p(this.b);
            y8Var.z();
        }
        if (this.d != null && g()) {
            y8Var.q(t);
            y8Var.u(this.d);
            y8Var.z();
        }
        if (this.f18909e != null && h()) {
            y8Var.q(u);
            y8Var.u(this.f18909e);
            y8Var.z();
        }
        if (this.f18910f != null && i()) {
            y8Var.q(v);
            y8Var.u(this.f18910f);
            y8Var.z();
        }
        if (this.f18911g != null && j()) {
            y8Var.q(w);
            y8Var.u(this.f18911g);
            y8Var.z();
        }
        if (this.f18912h != null && k()) {
            y8Var.q(x);
            y8Var.u(this.f18912h);
            y8Var.z();
        }
        if (this.f185a != null && l()) {
            y8Var.q(y);
            this.f185a.b(y8Var);
            y8Var.z();
        }
        if (this.f18913i != null && m()) {
            y8Var.q(z);
            y8Var.u(this.f18913i);
            y8Var.z();
        }
        if (n()) {
            y8Var.q(A);
            y8Var.x(this.f189a);
            y8Var.z();
        }
        if (this.f18914j != null && o()) {
            y8Var.q(B);
            y8Var.u(this.f18914j);
            y8Var.z();
        }
        if (p()) {
            y8Var.q(C);
            y8Var.p(this.f18908c);
            y8Var.z();
        }
        if (this.f18915k != null && q()) {
            y8Var.q(D);
            y8Var.u(this.f18915k);
            y8Var.z();
        }
        if (this.f18916l != null && r()) {
            y8Var.q(E);
            y8Var.u(this.f18916l);
            y8Var.z();
        }
        y8Var.A();
        y8Var.m();
    }

    public void b(boolean z2) {
        this.f188a.set(1, z2);
    }

    /* renamed from: b */
    public boolean m116b() {
        return this.f187a != null;
    }

    public String c() {
        return this.f191c;
    }

    public void c(boolean z2) {
        this.f188a.set(2, z2);
    }

    /* renamed from: c */
    public boolean m117c() {
        return this.f190b != null;
    }

    public void d(boolean z2) {
        this.f188a.set(3, z2);
    }

    public boolean d() {
        return this.f191c != null;
    }

    public boolean e() {
        return this.f188a.get(0);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof o7)) {
            return m115a((o7) obj);
        }
        return false;
    }

    public boolean f() {
        return this.f188a.get(1);
    }

    public boolean g() {
        return this.d != null;
    }

    public boolean h() {
        return this.f18909e != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f18910f != null;
    }

    public boolean j() {
        return this.f18911g != null;
    }

    public boolean k() {
        return this.f18912h != null;
    }

    public boolean l() {
        return this.f185a != null;
    }

    public boolean m() {
        return this.f18913i != null;
    }

    public boolean n() {
        return this.f188a.get(2);
    }

    public boolean o() {
        return this.f18914j != null;
    }

    public boolean p() {
        return this.f188a.get(3);
    }

    public boolean q() {
        return this.f18915k != null;
    }

    public boolean r() {
        return this.f18916l != null;
    }

    public String toString() {
        boolean z2;
        StringBuilder sb = new StringBuilder("PushMessage(");
        if (m114a()) {
            sb.append("to:");
            r7 r7Var = this.f186a;
            if (r7Var == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(r7Var);
            }
            z2 = false;
        } else {
            z2 = true;
        }
        if (!z2) {
            sb.append(", ");
        }
        sb.append("id:");
        String str = this.f187a;
        if (str == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            sb.append(str);
        }
        sb.append(", ");
        sb.append("appId:");
        String str2 = this.f190b;
        if (str2 == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            sb.append(str2);
        }
        sb.append(", ");
        sb.append("payload:");
        String str3 = this.f191c;
        if (str3 == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            sb.append(str3);
        }
        if (e()) {
            sb.append(", ");
            sb.append("createAt:");
            sb.append(this.a);
        }
        if (f()) {
            sb.append(", ");
            sb.append("ttl:");
            sb.append(this.b);
        }
        if (g()) {
            sb.append(", ");
            sb.append("collapseKey:");
            String str4 = this.d;
            if (str4 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str4);
            }
        }
        if (h()) {
            sb.append(", ");
            sb.append("packageName:");
            String str5 = this.f18909e;
            if (str5 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str5);
            }
        }
        if (i()) {
            sb.append(", ");
            sb.append("regId:");
            String str6 = this.f18910f;
            if (str6 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str6);
            }
        }
        if (j()) {
            sb.append(", ");
            sb.append("category:");
            String str7 = this.f18911g;
            if (str7 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str7);
            }
        }
        if (k()) {
            sb.append(", ");
            sb.append("topic:");
            String str8 = this.f18912h;
            if (str8 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str8);
            }
        }
        if (l()) {
            sb.append(", ");
            sb.append("metaInfo:");
            p7 p7Var = this.f185a;
            if (p7Var == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(p7Var);
            }
        }
        if (m()) {
            sb.append(", ");
            sb.append("aliasName:");
            String str9 = this.f18913i;
            if (str9 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str9);
            }
        }
        if (n()) {
            sb.append(", ");
            sb.append("isOnline:");
            sb.append(this.f189a);
        }
        if (o()) {
            sb.append(", ");
            sb.append("userAccount:");
            String str10 = this.f18914j;
            if (str10 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str10);
            }
        }
        if (p()) {
            sb.append(", ");
            sb.append("miid:");
            sb.append(this.f18908c);
        }
        if (q()) {
            sb.append(", ");
            sb.append("imeiMd5:");
            String str11 = this.f18915k;
            if (str11 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str11);
            }
        }
        if (r()) {
            sb.append(", ");
            sb.append("deviceId:");
            String str12 = this.f18916l;
            if (str12 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str12);
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
