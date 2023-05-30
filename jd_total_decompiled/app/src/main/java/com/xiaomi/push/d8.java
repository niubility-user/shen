package com.xiaomi.push;

import com.jd.dynamic.DYConstants;
import java.io.Serializable;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes11.dex */
public class d8 implements n8<d8, Object>, Serializable, Cloneable {
    public int a;

    /* renamed from: a  reason: collision with other field name */
    public long f117a;

    /* renamed from: a  reason: collision with other field name */
    public q7 f118a;

    /* renamed from: a  reason: collision with other field name */
    public r7 f119a;

    /* renamed from: a  reason: collision with other field name */
    public String f120a;

    /* renamed from: a  reason: collision with other field name */
    public Map<String, String> f122a;
    public int b;

    /* renamed from: b  reason: collision with other field name */
    public long f124b;

    /* renamed from: b  reason: collision with other field name */
    public String f125b;

    /* renamed from: c  reason: collision with root package name */
    public int f18543c;

    /* renamed from: c  reason: collision with other field name */
    public String f127c;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public String f18544e;

    /* renamed from: f  reason: collision with root package name */
    public String f18545f;

    /* renamed from: g  reason: collision with root package name */
    public String f18546g;

    /* renamed from: h  reason: collision with root package name */
    public String f18547h;

    /* renamed from: i  reason: collision with root package name */
    public String f18548i;

    /* renamed from: j  reason: collision with root package name */
    public String f18549j;

    /* renamed from: k  reason: collision with root package name */
    public String f18550k;

    /* renamed from: l  reason: collision with root package name */
    public String f18551l;

    /* renamed from: m  reason: collision with root package name */
    public String f18552m;

    /* renamed from: n  reason: collision with root package name */
    public String f18553n;
    public String o;
    public String p;
    public String q;
    public String r;
    private static final e9 s = new e9("XmPushActionRegistration");
    private static final v8 t = new v8("", (byte) 11, 1);
    private static final v8 u = new v8("", (byte) 12, 2);
    private static final v8 v = new v8("", (byte) 11, 3);
    private static final v8 w = new v8("", (byte) 11, 4);
    private static final v8 x = new v8("", (byte) 11, 5);
    private static final v8 y = new v8("", (byte) 11, 6);
    private static final v8 z = new v8("", (byte) 11, 7);
    private static final v8 A = new v8("", (byte) 11, 8);
    private static final v8 B = new v8("", (byte) 11, 9);
    private static final v8 C = new v8("", (byte) 11, 10);
    private static final v8 D = new v8("", (byte) 11, 11);
    private static final v8 E = new v8("", (byte) 11, 12);
    private static final v8 F = new v8("", (byte) 8, 13);
    private static final v8 G = new v8("", (byte) 8, 14);
    private static final v8 H = new v8("", (byte) 11, 15);
    private static final v8 I = new v8("", (byte) 11, 16);
    private static final v8 J = new v8("", (byte) 11, 17);
    private static final v8 K = new v8("", (byte) 11, 18);
    private static final v8 L = new v8("", (byte) 8, 19);
    private static final v8 M = new v8("", (byte) 8, 20);
    private static final v8 N = new v8("", (byte) 2, 21);
    private static final v8 O = new v8("", (byte) 10, 22);
    private static final v8 P = new v8("", (byte) 10, 23);
    private static final v8 Q = new v8("", (byte) 11, 24);
    private static final v8 R = new v8("", (byte) 11, 25);
    private static final v8 S = new v8("", (byte) 2, 26);
    private static final v8 T = new v8("", (byte) 13, 100);
    private static final v8 U = new v8("", (byte) 2, jd.wjlogin_sdk.util.z.Q);
    private static final v8 V = new v8("", (byte) 11, 102);

    /* renamed from: a  reason: collision with other field name */
    private BitSet f121a = new BitSet(8);

    /* renamed from: a  reason: collision with other field name */
    public boolean f123a = true;

    /* renamed from: c  reason: collision with other field name */
    public boolean f128c = false;

    /* renamed from: b  reason: collision with other field name */
    public boolean f126b = false;

    public boolean A() {
        return this.f122a != null;
    }

    public boolean B() {
        return this.f121a.get(7);
    }

    public boolean C() {
        return this.r != null;
    }

    @Override // java.lang.Comparable
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public int compareTo(d8 d8Var) {
        int e2;
        int k2;
        int h2;
        int k3;
        int e3;
        int e4;
        int c2;
        int c3;
        int k4;
        int d;
        int b;
        int e5;
        int e6;
        int e7;
        int e8;
        int b2;
        int b3;
        int e9;
        int e10;
        int e11;
        int e12;
        int e13;
        int e14;
        int e15;
        int e16;
        int e17;
        int e18;
        int d2;
        int e19;
        if (getClass().equals(d8Var.getClass())) {
            int compareTo = Boolean.valueOf(m44a()).compareTo(Boolean.valueOf(d8Var.m44a()));
            if (compareTo != 0) {
                return compareTo;
            }
            if (!m44a() || (e19 = o8.e(this.f120a, d8Var.f120a)) == 0) {
                int compareTo2 = Boolean.valueOf(m46b()).compareTo(Boolean.valueOf(d8Var.m46b()));
                if (compareTo2 != 0) {
                    return compareTo2;
                }
                if (!m46b() || (d2 = o8.d(this.f119a, d8Var.f119a)) == 0) {
                    int compareTo3 = Boolean.valueOf(m47c()).compareTo(Boolean.valueOf(d8Var.m47c()));
                    if (compareTo3 != 0) {
                        return compareTo3;
                    }
                    if (!m47c() || (e18 = o8.e(this.f125b, d8Var.f125b)) == 0) {
                        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(d8Var.d()));
                        if (compareTo4 != 0) {
                            return compareTo4;
                        }
                        if (!d() || (e17 = o8.e(this.f127c, d8Var.f127c)) == 0) {
                            int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(d8Var.e()));
                            if (compareTo5 != 0) {
                                return compareTo5;
                            }
                            if (!e() || (e16 = o8.e(this.d, d8Var.d)) == 0) {
                                int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(d8Var.f()));
                                if (compareTo6 != 0) {
                                    return compareTo6;
                                }
                                if (!f() || (e15 = o8.e(this.f18544e, d8Var.f18544e)) == 0) {
                                    int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(d8Var.g()));
                                    if (compareTo7 != 0) {
                                        return compareTo7;
                                    }
                                    if (!g() || (e14 = o8.e(this.f18545f, d8Var.f18545f)) == 0) {
                                        int compareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(d8Var.h()));
                                        if (compareTo8 != 0) {
                                            return compareTo8;
                                        }
                                        if (!h() || (e13 = o8.e(this.f18546g, d8Var.f18546g)) == 0) {
                                            int compareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(d8Var.i()));
                                            if (compareTo9 != 0) {
                                                return compareTo9;
                                            }
                                            if (!i() || (e12 = o8.e(this.f18547h, d8Var.f18547h)) == 0) {
                                                int compareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(d8Var.j()));
                                                if (compareTo10 != 0) {
                                                    return compareTo10;
                                                }
                                                if (!j() || (e11 = o8.e(this.f18548i, d8Var.f18548i)) == 0) {
                                                    int compareTo11 = Boolean.valueOf(k()).compareTo(Boolean.valueOf(d8Var.k()));
                                                    if (compareTo11 != 0) {
                                                        return compareTo11;
                                                    }
                                                    if (!k() || (e10 = o8.e(this.f18549j, d8Var.f18549j)) == 0) {
                                                        int compareTo12 = Boolean.valueOf(l()).compareTo(Boolean.valueOf(d8Var.l()));
                                                        if (compareTo12 != 0) {
                                                            return compareTo12;
                                                        }
                                                        if (!l() || (e9 = o8.e(this.f18550k, d8Var.f18550k)) == 0) {
                                                            int compareTo13 = Boolean.valueOf(m()).compareTo(Boolean.valueOf(d8Var.m()));
                                                            if (compareTo13 != 0) {
                                                                return compareTo13;
                                                            }
                                                            if (!m() || (b3 = o8.b(this.a, d8Var.a)) == 0) {
                                                                int compareTo14 = Boolean.valueOf(n()).compareTo(Boolean.valueOf(d8Var.n()));
                                                                if (compareTo14 != 0) {
                                                                    return compareTo14;
                                                                }
                                                                if (!n() || (b2 = o8.b(this.b, d8Var.b)) == 0) {
                                                                    int compareTo15 = Boolean.valueOf(o()).compareTo(Boolean.valueOf(d8Var.o()));
                                                                    if (compareTo15 != 0) {
                                                                        return compareTo15;
                                                                    }
                                                                    if (!o() || (e8 = o8.e(this.f18551l, d8Var.f18551l)) == 0) {
                                                                        int compareTo16 = Boolean.valueOf(p()).compareTo(Boolean.valueOf(d8Var.p()));
                                                                        if (compareTo16 != 0) {
                                                                            return compareTo16;
                                                                        }
                                                                        if (!p() || (e7 = o8.e(this.f18552m, d8Var.f18552m)) == 0) {
                                                                            int compareTo17 = Boolean.valueOf(q()).compareTo(Boolean.valueOf(d8Var.q()));
                                                                            if (compareTo17 != 0) {
                                                                                return compareTo17;
                                                                            }
                                                                            if (!q() || (e6 = o8.e(this.f18553n, d8Var.f18553n)) == 0) {
                                                                                int compareTo18 = Boolean.valueOf(r()).compareTo(Boolean.valueOf(d8Var.r()));
                                                                                if (compareTo18 != 0) {
                                                                                    return compareTo18;
                                                                                }
                                                                                if (!r() || (e5 = o8.e(this.o, d8Var.o)) == 0) {
                                                                                    int compareTo19 = Boolean.valueOf(s()).compareTo(Boolean.valueOf(d8Var.s()));
                                                                                    if (compareTo19 != 0) {
                                                                                        return compareTo19;
                                                                                    }
                                                                                    if (!s() || (b = o8.b(this.f18543c, d8Var.f18543c)) == 0) {
                                                                                        int compareTo20 = Boolean.valueOf(t()).compareTo(Boolean.valueOf(d8Var.t()));
                                                                                        if (compareTo20 != 0) {
                                                                                            return compareTo20;
                                                                                        }
                                                                                        if (!t() || (d = o8.d(this.f118a, d8Var.f118a)) == 0) {
                                                                                            int compareTo21 = Boolean.valueOf(u()).compareTo(Boolean.valueOf(d8Var.u()));
                                                                                            if (compareTo21 != 0) {
                                                                                                return compareTo21;
                                                                                            }
                                                                                            if (!u() || (k4 = o8.k(this.f123a, d8Var.f123a)) == 0) {
                                                                                                int compareTo22 = Boolean.valueOf(v()).compareTo(Boolean.valueOf(d8Var.v()));
                                                                                                if (compareTo22 != 0) {
                                                                                                    return compareTo22;
                                                                                                }
                                                                                                if (!v() || (c3 = o8.c(this.f117a, d8Var.f117a)) == 0) {
                                                                                                    int compareTo23 = Boolean.valueOf(w()).compareTo(Boolean.valueOf(d8Var.w()));
                                                                                                    if (compareTo23 != 0) {
                                                                                                        return compareTo23;
                                                                                                    }
                                                                                                    if (!w() || (c2 = o8.c(this.f124b, d8Var.f124b)) == 0) {
                                                                                                        int compareTo24 = Boolean.valueOf(x()).compareTo(Boolean.valueOf(d8Var.x()));
                                                                                                        if (compareTo24 != 0) {
                                                                                                            return compareTo24;
                                                                                                        }
                                                                                                        if (!x() || (e4 = o8.e(this.p, d8Var.p)) == 0) {
                                                                                                            int compareTo25 = Boolean.valueOf(y()).compareTo(Boolean.valueOf(d8Var.y()));
                                                                                                            if (compareTo25 != 0) {
                                                                                                                return compareTo25;
                                                                                                            }
                                                                                                            if (!y() || (e3 = o8.e(this.q, d8Var.q)) == 0) {
                                                                                                                int compareTo26 = Boolean.valueOf(z()).compareTo(Boolean.valueOf(d8Var.z()));
                                                                                                                if (compareTo26 != 0) {
                                                                                                                    return compareTo26;
                                                                                                                }
                                                                                                                if (!z() || (k3 = o8.k(this.f126b, d8Var.f126b)) == 0) {
                                                                                                                    int compareTo27 = Boolean.valueOf(A()).compareTo(Boolean.valueOf(d8Var.A()));
                                                                                                                    if (compareTo27 != 0) {
                                                                                                                        return compareTo27;
                                                                                                                    }
                                                                                                                    if (!A() || (h2 = o8.h(this.f122a, d8Var.f122a)) == 0) {
                                                                                                                        int compareTo28 = Boolean.valueOf(B()).compareTo(Boolean.valueOf(d8Var.B()));
                                                                                                                        if (compareTo28 != 0) {
                                                                                                                            return compareTo28;
                                                                                                                        }
                                                                                                                        if (!B() || (k2 = o8.k(this.f128c, d8Var.f128c)) == 0) {
                                                                                                                            int compareTo29 = Boolean.valueOf(C()).compareTo(Boolean.valueOf(d8Var.C()));
                                                                                                                            if (compareTo29 != 0) {
                                                                                                                                return compareTo29;
                                                                                                                            }
                                                                                                                            if (!C() || (e2 = o8.e(this.r, d8Var.r)) == 0) {
                                                                                                                                return 0;
                                                                                                                            }
                                                                                                                            return e2;
                                                                                                                        }
                                                                                                                        return k2;
                                                                                                                    }
                                                                                                                    return h2;
                                                                                                                }
                                                                                                                return k3;
                                                                                                            }
                                                                                                            return e3;
                                                                                                        }
                                                                                                        return e4;
                                                                                                    }
                                                                                                    return c2;
                                                                                                }
                                                                                                return c3;
                                                                                            }
                                                                                            return k4;
                                                                                        }
                                                                                        return d;
                                                                                    }
                                                                                    return b;
                                                                                }
                                                                                return e5;
                                                                            }
                                                                            return e6;
                                                                        }
                                                                        return e7;
                                                                    }
                                                                    return e8;
                                                                }
                                                                return b2;
                                                            }
                                                            return b3;
                                                        }
                                                        return e9;
                                                    }
                                                    return e10;
                                                }
                                                return e11;
                                            }
                                            return e12;
                                        }
                                        return e13;
                                    }
                                    return e14;
                                }
                                return e15;
                            }
                            return e16;
                        }
                        return e17;
                    }
                    return e18;
                }
                return d2;
            }
            return e19;
        }
        return getClass().getName().compareTo(d8Var.getClass().getName());
    }

    public d8 a(int i2) {
        this.a = i2;
        a(true);
        return this;
    }

    public d8 a(q7 q7Var) {
        this.f118a = q7Var;
        return this;
    }

    public d8 a(String str) {
        this.f125b = str;
        return this;
    }

    public String a() {
        return this.f125b;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m43a() {
        if (this.f125b == null) {
            throw new z8("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f127c == null) {
            throw new z8("Required field 'appId' was not present! Struct: " + toString());
        } else if (this.f18545f != null) {
        } else {
            throw new z8("Required field 'token' was not present! Struct: " + toString());
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
                m43a();
                return;
            }
            short s2 = e2.f19282c;
            switch (s2) {
                case 1:
                    if (b == 11) {
                        this.f120a = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 2:
                    if (b == 12) {
                        r7 r7Var = new r7();
                        this.f119a = r7Var;
                        r7Var.a(y8Var);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 3:
                    if (b == 11) {
                        this.f125b = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 4:
                    if (b == 11) {
                        this.f127c = y8Var.j();
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
                        this.f18544e = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 7:
                    if (b == 11) {
                        this.f18545f = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 8:
                    if (b == 11) {
                        this.f18546g = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 9:
                    if (b == 11) {
                        this.f18547h = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 10:
                    if (b == 11) {
                        this.f18548i = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 11:
                    if (b == 11) {
                        this.f18549j = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 12:
                    if (b == 11) {
                        this.f18550k = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 13:
                    if (b == 8) {
                        this.a = y8Var.c();
                        a(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 14:
                    if (b == 8) {
                        this.b = y8Var.c();
                        b(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 15:
                    if (b == 11) {
                        this.f18551l = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 16:
                    if (b == 11) {
                        this.f18552m = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 17:
                    if (b == 11) {
                        this.f18553n = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 18:
                    if (b == 11) {
                        this.o = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 19:
                    if (b == 8) {
                        this.f18543c = y8Var.c();
                        c(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 20:
                    if (b == 8) {
                        this.f118a = q7.a(y8Var.c());
                        continue;
                        y8Var.E();
                    }
                    break;
                case 21:
                    if (b == 2) {
                        this.f123a = y8Var.y();
                        d(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 22:
                    if (b == 10) {
                        this.f117a = y8Var.d();
                        e(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 23:
                    if (b == 10) {
                        this.f124b = y8Var.d();
                        f(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 24:
                    if (b == 11) {
                        this.p = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 25:
                    if (b == 11) {
                        this.q = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 26:
                    if (b == 2) {
                        this.f126b = y8Var.y();
                        g(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                default:
                    switch (s2) {
                        case 100:
                            if (b == 13) {
                                x8 g2 = y8Var.g();
                                this.f122a = new HashMap(g2.f19321c * 2);
                                for (int i2 = 0; i2 < g2.f19321c; i2++) {
                                    this.f122a.put(y8Var.j(), y8Var.j());
                                }
                                y8Var.F();
                                break;
                            }
                            break;
                        case 101:
                            if (b == 2) {
                                this.f128c = y8Var.y();
                                h(true);
                                break;
                            }
                            break;
                        case 102:
                            if (b == 11) {
                                this.r = y8Var.j();
                                continue;
                            }
                            break;
                    }
                    y8Var.E();
                    break;
            }
            c9.a(y8Var, b);
            y8Var.E();
        }
    }

    public void a(boolean z2) {
        this.f121a.set(0, z2);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m44a() {
        return this.f120a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m45a(d8 d8Var) {
        if (d8Var == null) {
            return false;
        }
        boolean m44a = m44a();
        boolean m44a2 = d8Var.m44a();
        if ((m44a || m44a2) && !(m44a && m44a2 && this.f120a.equals(d8Var.f120a))) {
            return false;
        }
        boolean m46b = m46b();
        boolean m46b2 = d8Var.m46b();
        if ((m46b || m46b2) && !(m46b && m46b2 && this.f119a.m132a(d8Var.f119a))) {
            return false;
        }
        boolean m47c = m47c();
        boolean m47c2 = d8Var.m47c();
        if ((m47c || m47c2) && !(m47c && m47c2 && this.f125b.equals(d8Var.f125b))) {
            return false;
        }
        boolean d = d();
        boolean d2 = d8Var.d();
        if ((d || d2) && !(d && d2 && this.f127c.equals(d8Var.f127c))) {
            return false;
        }
        boolean e2 = e();
        boolean e3 = d8Var.e();
        if ((e2 || e3) && !(e2 && e3 && this.d.equals(d8Var.d))) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = d8Var.f();
        if ((f2 || f3) && !(f2 && f3 && this.f18544e.equals(d8Var.f18544e))) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = d8Var.g();
        if ((g2 || g3) && !(g2 && g3 && this.f18545f.equals(d8Var.f18545f))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = d8Var.h();
        if ((h2 || h3) && !(h2 && h3 && this.f18546g.equals(d8Var.f18546g))) {
            return false;
        }
        boolean i2 = i();
        boolean i3 = d8Var.i();
        if ((i2 || i3) && !(i2 && i3 && this.f18547h.equals(d8Var.f18547h))) {
            return false;
        }
        boolean j2 = j();
        boolean j3 = d8Var.j();
        if ((j2 || j3) && !(j2 && j3 && this.f18548i.equals(d8Var.f18548i))) {
            return false;
        }
        boolean k2 = k();
        boolean k3 = d8Var.k();
        if ((k2 || k3) && !(k2 && k3 && this.f18549j.equals(d8Var.f18549j))) {
            return false;
        }
        boolean l2 = l();
        boolean l3 = d8Var.l();
        if ((l2 || l3) && !(l2 && l3 && this.f18550k.equals(d8Var.f18550k))) {
            return false;
        }
        boolean m2 = m();
        boolean m3 = d8Var.m();
        if ((m2 || m3) && !(m2 && m3 && this.a == d8Var.a)) {
            return false;
        }
        boolean n2 = n();
        boolean n3 = d8Var.n();
        if ((n2 || n3) && !(n2 && n3 && this.b == d8Var.b)) {
            return false;
        }
        boolean o = o();
        boolean o2 = d8Var.o();
        if ((o || o2) && !(o && o2 && this.f18551l.equals(d8Var.f18551l))) {
            return false;
        }
        boolean p = p();
        boolean p2 = d8Var.p();
        if ((p || p2) && !(p && p2 && this.f18552m.equals(d8Var.f18552m))) {
            return false;
        }
        boolean q = q();
        boolean q2 = d8Var.q();
        if ((q || q2) && !(q && q2 && this.f18553n.equals(d8Var.f18553n))) {
            return false;
        }
        boolean r = r();
        boolean r2 = d8Var.r();
        if ((r || r2) && !(r && r2 && this.o.equals(d8Var.o))) {
            return false;
        }
        boolean s2 = s();
        boolean s3 = d8Var.s();
        if ((s2 || s3) && !(s2 && s3 && this.f18543c == d8Var.f18543c)) {
            return false;
        }
        boolean t2 = t();
        boolean t3 = d8Var.t();
        if ((t2 || t3) && !(t2 && t3 && this.f118a.equals(d8Var.f118a))) {
            return false;
        }
        boolean u2 = u();
        boolean u3 = d8Var.u();
        if ((u2 || u3) && !(u2 && u3 && this.f123a == d8Var.f123a)) {
            return false;
        }
        boolean v2 = v();
        boolean v3 = d8Var.v();
        if ((v2 || v3) && !(v2 && v3 && this.f117a == d8Var.f117a)) {
            return false;
        }
        boolean w2 = w();
        boolean w3 = d8Var.w();
        if ((w2 || w3) && !(w2 && w3 && this.f124b == d8Var.f124b)) {
            return false;
        }
        boolean x2 = x();
        boolean x3 = d8Var.x();
        if ((x2 || x3) && !(x2 && x3 && this.p.equals(d8Var.p))) {
            return false;
        }
        boolean y2 = y();
        boolean y3 = d8Var.y();
        if ((y2 || y3) && !(y2 && y3 && this.q.equals(d8Var.q))) {
            return false;
        }
        boolean z2 = z();
        boolean z3 = d8Var.z();
        if ((z2 || z3) && !(z2 && z3 && this.f126b == d8Var.f126b)) {
            return false;
        }
        boolean A2 = A();
        boolean A3 = d8Var.A();
        if ((A2 || A3) && !(A2 && A3 && this.f122a.equals(d8Var.f122a))) {
            return false;
        }
        boolean B2 = B();
        boolean B3 = d8Var.B();
        if ((B2 || B3) && !(B2 && B3 && this.f128c == d8Var.f128c)) {
            return false;
        }
        boolean C2 = C();
        boolean C3 = d8Var.C();
        if (C2 || C3) {
            return C2 && C3 && this.r.equals(d8Var.r);
        }
        return true;
    }

    public d8 b(int i2) {
        this.b = i2;
        b(true);
        return this;
    }

    public d8 b(String str) {
        this.f127c = str;
        return this;
    }

    public String b() {
        return this.f127c;
    }

    @Override // com.xiaomi.push.n8
    public void b(y8 y8Var) {
        m43a();
        y8Var.t(s);
        if (this.f120a != null && m44a()) {
            y8Var.q(t);
            y8Var.u(this.f120a);
            y8Var.z();
        }
        if (this.f119a != null && m46b()) {
            y8Var.q(u);
            this.f119a.b(y8Var);
            y8Var.z();
        }
        if (this.f125b != null) {
            y8Var.q(v);
            y8Var.u(this.f125b);
            y8Var.z();
        }
        if (this.f127c != null) {
            y8Var.q(w);
            y8Var.u(this.f127c);
            y8Var.z();
        }
        if (this.d != null && e()) {
            y8Var.q(x);
            y8Var.u(this.d);
            y8Var.z();
        }
        if (this.f18544e != null && f()) {
            y8Var.q(y);
            y8Var.u(this.f18544e);
            y8Var.z();
        }
        if (this.f18545f != null) {
            y8Var.q(z);
            y8Var.u(this.f18545f);
            y8Var.z();
        }
        if (this.f18546g != null && h()) {
            y8Var.q(A);
            y8Var.u(this.f18546g);
            y8Var.z();
        }
        if (this.f18547h != null && i()) {
            y8Var.q(B);
            y8Var.u(this.f18547h);
            y8Var.z();
        }
        if (this.f18548i != null && j()) {
            y8Var.q(C);
            y8Var.u(this.f18548i);
            y8Var.z();
        }
        if (this.f18549j != null && k()) {
            y8Var.q(D);
            y8Var.u(this.f18549j);
            y8Var.z();
        }
        if (this.f18550k != null && l()) {
            y8Var.q(E);
            y8Var.u(this.f18550k);
            y8Var.z();
        }
        if (m()) {
            y8Var.q(F);
            y8Var.o(this.a);
            y8Var.z();
        }
        if (n()) {
            y8Var.q(G);
            y8Var.o(this.b);
            y8Var.z();
        }
        if (this.f18551l != null && o()) {
            y8Var.q(H);
            y8Var.u(this.f18551l);
            y8Var.z();
        }
        if (this.f18552m != null && p()) {
            y8Var.q(I);
            y8Var.u(this.f18552m);
            y8Var.z();
        }
        if (this.f18553n != null && q()) {
            y8Var.q(J);
            y8Var.u(this.f18553n);
            y8Var.z();
        }
        if (this.o != null && r()) {
            y8Var.q(K);
            y8Var.u(this.o);
            y8Var.z();
        }
        if (s()) {
            y8Var.q(L);
            y8Var.o(this.f18543c);
            y8Var.z();
        }
        if (this.f118a != null && t()) {
            y8Var.q(M);
            y8Var.o(this.f118a.a());
            y8Var.z();
        }
        if (u()) {
            y8Var.q(N);
            y8Var.x(this.f123a);
            y8Var.z();
        }
        if (v()) {
            y8Var.q(O);
            y8Var.p(this.f117a);
            y8Var.z();
        }
        if (w()) {
            y8Var.q(P);
            y8Var.p(this.f124b);
            y8Var.z();
        }
        if (this.p != null && x()) {
            y8Var.q(Q);
            y8Var.u(this.p);
            y8Var.z();
        }
        if (this.q != null && y()) {
            y8Var.q(R);
            y8Var.u(this.q);
            y8Var.z();
        }
        if (z()) {
            y8Var.q(S);
            y8Var.x(this.f126b);
            y8Var.z();
        }
        if (this.f122a != null && A()) {
            y8Var.q(T);
            y8Var.s(new x8((byte) 11, (byte) 11, this.f122a.size()));
            for (Map.Entry<String, String> entry : this.f122a.entrySet()) {
                y8Var.u(entry.getKey());
                y8Var.u(entry.getValue());
            }
            y8Var.B();
            y8Var.z();
        }
        if (B()) {
            y8Var.q(U);
            y8Var.x(this.f128c);
            y8Var.z();
        }
        if (this.r != null && C()) {
            y8Var.q(V);
            y8Var.u(this.r);
            y8Var.z();
        }
        y8Var.A();
        y8Var.m();
    }

    public void b(boolean z2) {
        this.f121a.set(1, z2);
    }

    /* renamed from: b  reason: collision with other method in class */
    public boolean m46b() {
        return this.f119a != null;
    }

    public d8 c(int i2) {
        this.f18543c = i2;
        c(true);
        return this;
    }

    public d8 c(String str) {
        this.d = str;
        return this;
    }

    public String c() {
        return this.f18545f;
    }

    public void c(boolean z2) {
        this.f121a.set(2, z2);
    }

    /* renamed from: c  reason: collision with other method in class */
    public boolean m47c() {
        return this.f125b != null;
    }

    public d8 d(String str) {
        this.f18544e = str;
        return this;
    }

    public void d(boolean z2) {
        this.f121a.set(3, z2);
    }

    public boolean d() {
        return this.f127c != null;
    }

    public d8 e(String str) {
        this.f18545f = str;
        return this;
    }

    public void e(boolean z2) {
        this.f121a.set(4, z2);
    }

    public boolean e() {
        return this.d != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof d8)) {
            return m45a((d8) obj);
        }
        return false;
    }

    public d8 f(String str) {
        this.f18546g = str;
        return this;
    }

    public void f(boolean z2) {
        this.f121a.set(5, z2);
    }

    public boolean f() {
        return this.f18544e != null;
    }

    public d8 g(String str) {
        this.f18547h = str;
        return this;
    }

    public void g(boolean z2) {
        this.f121a.set(6, z2);
    }

    public boolean g() {
        return this.f18545f != null;
    }

    public d8 h(String str) {
        this.f18550k = str;
        return this;
    }

    public void h(boolean z2) {
        this.f121a.set(7, z2);
    }

    public boolean h() {
        return this.f18546g != null;
    }

    public int hashCode() {
        return 0;
    }

    public d8 i(String str) {
        this.o = str;
        return this;
    }

    public boolean i() {
        return this.f18547h != null;
    }

    public boolean j() {
        return this.f18548i != null;
    }

    public boolean k() {
        return this.f18549j != null;
    }

    public boolean l() {
        return this.f18550k != null;
    }

    public boolean m() {
        return this.f121a.get(0);
    }

    public boolean n() {
        return this.f121a.get(1);
    }

    public boolean o() {
        return this.f18551l != null;
    }

    public boolean p() {
        return this.f18552m != null;
    }

    public boolean q() {
        return this.f18553n != null;
    }

    public boolean r() {
        return this.o != null;
    }

    public boolean s() {
        return this.f121a.get(2);
    }

    public boolean t() {
        return this.f118a != null;
    }

    public String toString() {
        boolean z2;
        StringBuilder sb = new StringBuilder("XmPushActionRegistration(");
        boolean z3 = false;
        if (m44a()) {
            sb.append("debug:");
            String str = this.f120a;
            if (str == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str);
            }
            z2 = false;
        } else {
            z2 = true;
        }
        if (m46b()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("target:");
            r7 r7Var = this.f119a;
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
        String str2 = this.f125b;
        if (str2 == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            sb.append(com.xiaomi.push.service.f0.b(str2));
        }
        sb.append(", ");
        sb.append("appId:");
        String str3 = this.f127c;
        if (str3 == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            sb.append(str3);
        }
        if (e()) {
            sb.append(", ");
            sb.append("appVersion:");
            String str4 = this.d;
            if (str4 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str4);
            }
        }
        if (f()) {
            sb.append(", ");
            sb.append("packageName:");
            String str5 = this.f18544e;
            if (str5 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str5);
            }
        }
        sb.append(", ");
        sb.append("token:");
        String str6 = this.f18545f;
        if (str6 == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            sb.append(str6);
        }
        if (h()) {
            sb.append(", ");
            sb.append("deviceId:");
            String str7 = this.f18546g;
            if (str7 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str7);
            }
        }
        if (i()) {
            sb.append(", ");
            sb.append("aliasName:");
            String str8 = this.f18547h;
            if (str8 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str8);
            }
        }
        if (j()) {
            sb.append(", ");
            sb.append("sdkVersion:");
            String str9 = this.f18548i;
            if (str9 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str9);
            }
        }
        if (k()) {
            sb.append(", ");
            sb.append("regId:");
            String str10 = this.f18549j;
            if (str10 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str10);
            }
        }
        if (l()) {
            sb.append(", ");
            sb.append("pushSdkVersionName:");
            String str11 = this.f18550k;
            if (str11 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str11);
            }
        }
        if (m()) {
            sb.append(", ");
            sb.append("pushSdkVersionCode:");
            sb.append(this.a);
        }
        if (n()) {
            sb.append(", ");
            sb.append("appVersionCode:");
            sb.append(this.b);
        }
        if (o()) {
            sb.append(", ");
            sb.append("androidId:");
            String str12 = this.f18551l;
            if (str12 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str12);
            }
        }
        if (p()) {
            sb.append(", ");
            sb.append("imei:");
            String str13 = this.f18552m;
            if (str13 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str13);
            }
        }
        if (q()) {
            sb.append(", ");
            sb.append("serial:");
            String str14 = this.f18553n;
            if (str14 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str14);
            }
        }
        if (r()) {
            sb.append(", ");
            sb.append("imeiMd5:");
            String str15 = this.o;
            if (str15 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str15);
            }
        }
        if (s()) {
            sb.append(", ");
            sb.append("spaceId:");
            sb.append(this.f18543c);
        }
        if (t()) {
            sb.append(", ");
            sb.append("reason:");
            q7 q7Var = this.f118a;
            if (q7Var == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(q7Var);
            }
        }
        if (u()) {
            sb.append(", ");
            sb.append("validateToken:");
            sb.append(this.f123a);
        }
        if (v()) {
            sb.append(", ");
            sb.append("miid:");
            sb.append(this.f117a);
        }
        if (w()) {
            sb.append(", ");
            sb.append("createdTs:");
            sb.append(this.f124b);
        }
        if (x()) {
            sb.append(", ");
            sb.append("subImei:");
            String str16 = this.p;
            if (str16 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str16);
            }
        }
        if (y()) {
            sb.append(", ");
            sb.append("subImeiMd5:");
            String str17 = this.q;
            if (str17 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str17);
            }
        }
        if (z()) {
            sb.append(", ");
            sb.append("isHybridFrame:");
            sb.append(this.f126b);
        }
        if (A()) {
            sb.append(", ");
            sb.append("connectionAttrs:");
            Map<String, String> map = this.f122a;
            if (map == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(map);
            }
        }
        if (B()) {
            sb.append(", ");
            sb.append("cleanOldRegInfo:");
            sb.append(this.f128c);
        }
        if (C()) {
            sb.append(", ");
            sb.append("oldRegId:");
            String str18 = this.r;
            if (str18 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str18);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public boolean u() {
        return this.f121a.get(3);
    }

    public boolean v() {
        return this.f121a.get(4);
    }

    public boolean w() {
        return this.f121a.get(5);
    }

    public boolean x() {
        return this.p != null;
    }

    public boolean y() {
        return this.q != null;
    }

    public boolean z() {
        return this.f121a.get(6);
    }
}
