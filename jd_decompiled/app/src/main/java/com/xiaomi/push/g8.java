package com.xiaomi.push;

import com.jd.dynamic.DYConstants;
import java.io.Serializable;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes11.dex */
public class g8 implements n8<g8, Object>, Serializable, Cloneable {

    /* renamed from: i */
    private static final e9 f18670i = new e9("XmPushActionSendMessage");

    /* renamed from: j */
    private static final v8 f18671j = new v8("", (byte) 11, 1);

    /* renamed from: k */
    private static final v8 f18672k = new v8("", (byte) 12, 2);

    /* renamed from: l */
    private static final v8 f18673l = new v8("", (byte) 11, 3);

    /* renamed from: m */
    private static final v8 f18674m = new v8("", (byte) 11, 4);

    /* renamed from: n */
    private static final v8 f18675n = new v8("", (byte) 11, 5);
    private static final v8 o = new v8("", (byte) 11, 6);
    private static final v8 p = new v8("", (byte) 11, 7);
    private static final v8 q = new v8("", (byte) 12, 8);
    private static final v8 r = new v8("", (byte) 2, 9);
    private static final v8 s = new v8("", (byte) 13, 10);
    private static final v8 t = new v8("", (byte) 11, 11);
    private static final v8 u = new v8("", (byte) 11, 12);
    public o7 a;

    /* renamed from: a */
    public r7 f147a;

    /* renamed from: a */
    public String f148a;

    /* renamed from: a */
    public Map<String, String> f150a;
    public String b;

    /* renamed from: c */
    public String f18676c;
    public String d;

    /* renamed from: e */
    public String f18677e;

    /* renamed from: f */
    public String f18678f;

    /* renamed from: g */
    public String f18679g;

    /* renamed from: h */
    public String f18680h;

    /* renamed from: a */
    private BitSet f149a = new BitSet(1);

    /* renamed from: a */
    public boolean f151a = true;

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(g8 g8Var) {
        int e2;
        int e3;
        int h2;
        int k2;
        int d;
        int e4;
        int e5;
        int e6;
        int e7;
        int e8;
        int d2;
        int e9;
        if (getClass().equals(g8Var.getClass())) {
            int compareTo = Boolean.valueOf(m72a()).compareTo(Boolean.valueOf(g8Var.m72a()));
            if (compareTo != 0) {
                return compareTo;
            }
            if (!m72a() || (e9 = o8.e(this.f148a, g8Var.f148a)) == 0) {
                int compareTo2 = Boolean.valueOf(m74b()).compareTo(Boolean.valueOf(g8Var.m74b()));
                if (compareTo2 != 0) {
                    return compareTo2;
                }
                if (!m74b() || (d2 = o8.d(this.f147a, g8Var.f147a)) == 0) {
                    int compareTo3 = Boolean.valueOf(m75c()).compareTo(Boolean.valueOf(g8Var.m75c()));
                    if (compareTo3 != 0) {
                        return compareTo3;
                    }
                    if (!m75c() || (e8 = o8.e(this.b, g8Var.b)) == 0) {
                        int compareTo4 = Boolean.valueOf(m76d()).compareTo(Boolean.valueOf(g8Var.m76d()));
                        if (compareTo4 != 0) {
                            return compareTo4;
                        }
                        if (!m76d() || (e7 = o8.e(this.f18676c, g8Var.f18676c)) == 0) {
                            int compareTo5 = Boolean.valueOf(m77e()).compareTo(Boolean.valueOf(g8Var.m77e()));
                            if (compareTo5 != 0) {
                                return compareTo5;
                            }
                            if (!m77e() || (e6 = o8.e(this.d, g8Var.d)) == 0) {
                                int compareTo6 = Boolean.valueOf(m78f()).compareTo(Boolean.valueOf(g8Var.m78f()));
                                if (compareTo6 != 0) {
                                    return compareTo6;
                                }
                                if (!m78f() || (e5 = o8.e(this.f18677e, g8Var.f18677e)) == 0) {
                                    int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(g8Var.g()));
                                    if (compareTo7 != 0) {
                                        return compareTo7;
                                    }
                                    if (!g() || (e4 = o8.e(this.f18678f, g8Var.f18678f)) == 0) {
                                        int compareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(g8Var.h()));
                                        if (compareTo8 != 0) {
                                            return compareTo8;
                                        }
                                        if (!h() || (d = o8.d(this.a, g8Var.a)) == 0) {
                                            int compareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(g8Var.i()));
                                            if (compareTo9 != 0) {
                                                return compareTo9;
                                            }
                                            if (!i() || (k2 = o8.k(this.f151a, g8Var.f151a)) == 0) {
                                                int compareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(g8Var.j()));
                                                if (compareTo10 != 0) {
                                                    return compareTo10;
                                                }
                                                if (!j() || (h2 = o8.h(this.f150a, g8Var.f150a)) == 0) {
                                                    int compareTo11 = Boolean.valueOf(k()).compareTo(Boolean.valueOf(g8Var.k()));
                                                    if (compareTo11 != 0) {
                                                        return compareTo11;
                                                    }
                                                    if (!k() || (e3 = o8.e(this.f18679g, g8Var.f18679g)) == 0) {
                                                        int compareTo12 = Boolean.valueOf(l()).compareTo(Boolean.valueOf(g8Var.l()));
                                                        if (compareTo12 != 0) {
                                                            return compareTo12;
                                                        }
                                                        if (!l() || (e2 = o8.e(this.f18680h, g8Var.f18680h)) == 0) {
                                                            return 0;
                                                        }
                                                        return e2;
                                                    }
                                                    return e3;
                                                }
                                                return h2;
                                            }
                                            return k2;
                                        }
                                        return d;
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
                return d2;
            }
            return e9;
        }
        return getClass().getName().compareTo(g8Var.getClass().getName());
    }

    public o7 a() {
        return this.a;
    }

    /* renamed from: a */
    public String m70a() {
        return this.b;
    }

    /* renamed from: a */
    public void m71a() {
        if (this.b == null) {
            throw new z8("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f18676c != null) {
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
                m71a();
                return;
            }
            switch (e2.f19282c) {
                case 1:
                    if (b == 11) {
                        this.f148a = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 2:
                    if (b == 12) {
                        r7 r7Var = new r7();
                        this.f147a = r7Var;
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
                        this.f18676c = y8Var.j();
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
                        this.f18677e = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 7:
                    if (b == 11) {
                        this.f18678f = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 8:
                    if (b == 12) {
                        o7 o7Var = new o7();
                        this.a = o7Var;
                        o7Var.a(y8Var);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 9:
                    if (b == 2) {
                        this.f151a = y8Var.y();
                        a(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 10:
                    if (b == 13) {
                        x8 g2 = y8Var.g();
                        this.f150a = new HashMap(g2.f19321c * 2);
                        for (int i2 = 0; i2 < g2.f19321c; i2++) {
                            this.f150a.put(y8Var.j(), y8Var.j());
                        }
                        y8Var.F();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 11:
                    if (b == 11) {
                        this.f18679g = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 12:
                    if (b == 11) {
                        this.f18680h = y8Var.j();
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
        this.f149a.set(0, z);
    }

    /* renamed from: a */
    public boolean m72a() {
        return this.f148a != null;
    }

    /* renamed from: a */
    public boolean m73a(g8 g8Var) {
        if (g8Var == null) {
            return false;
        }
        boolean m72a = m72a();
        boolean m72a2 = g8Var.m72a();
        if ((m72a || m72a2) && !(m72a && m72a2 && this.f148a.equals(g8Var.f148a))) {
            return false;
        }
        boolean m74b = m74b();
        boolean m74b2 = g8Var.m74b();
        if ((m74b || m74b2) && !(m74b && m74b2 && this.f147a.m132a(g8Var.f147a))) {
            return false;
        }
        boolean m75c = m75c();
        boolean m75c2 = g8Var.m75c();
        if ((m75c || m75c2) && !(m75c && m75c2 && this.b.equals(g8Var.b))) {
            return false;
        }
        boolean m76d = m76d();
        boolean m76d2 = g8Var.m76d();
        if ((m76d || m76d2) && !(m76d && m76d2 && this.f18676c.equals(g8Var.f18676c))) {
            return false;
        }
        boolean m77e = m77e();
        boolean m77e2 = g8Var.m77e();
        if ((m77e || m77e2) && !(m77e && m77e2 && this.d.equals(g8Var.d))) {
            return false;
        }
        boolean m78f = m78f();
        boolean m78f2 = g8Var.m78f();
        if ((m78f || m78f2) && !(m78f && m78f2 && this.f18677e.equals(g8Var.f18677e))) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = g8Var.g();
        if ((g2 || g3) && !(g2 && g3 && this.f18678f.equals(g8Var.f18678f))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = g8Var.h();
        if ((h2 || h3) && !(h2 && h3 && this.a.m115a(g8Var.a))) {
            return false;
        }
        boolean i2 = i();
        boolean i3 = g8Var.i();
        if ((i2 || i3) && !(i2 && i3 && this.f151a == g8Var.f151a)) {
            return false;
        }
        boolean j2 = j();
        boolean j3 = g8Var.j();
        if ((j2 || j3) && !(j2 && j3 && this.f150a.equals(g8Var.f150a))) {
            return false;
        }
        boolean k2 = k();
        boolean k3 = g8Var.k();
        if ((k2 || k3) && !(k2 && k3 && this.f18679g.equals(g8Var.f18679g))) {
            return false;
        }
        boolean l2 = l();
        boolean l3 = g8Var.l();
        if (l2 || l3) {
            return l2 && l3 && this.f18680h.equals(g8Var.f18680h);
        }
        return true;
    }

    public String b() {
        return this.f18676c;
    }

    @Override // com.xiaomi.push.n8
    public void b(y8 y8Var) {
        m71a();
        y8Var.t(f18670i);
        if (this.f148a != null && m72a()) {
            y8Var.q(f18671j);
            y8Var.u(this.f148a);
            y8Var.z();
        }
        if (this.f147a != null && m74b()) {
            y8Var.q(f18672k);
            this.f147a.b(y8Var);
            y8Var.z();
        }
        if (this.b != null) {
            y8Var.q(f18673l);
            y8Var.u(this.b);
            y8Var.z();
        }
        if (this.f18676c != null) {
            y8Var.q(f18674m);
            y8Var.u(this.f18676c);
            y8Var.z();
        }
        if (this.d != null && m77e()) {
            y8Var.q(f18675n);
            y8Var.u(this.d);
            y8Var.z();
        }
        if (this.f18677e != null && m78f()) {
            y8Var.q(o);
            y8Var.u(this.f18677e);
            y8Var.z();
        }
        if (this.f18678f != null && g()) {
            y8Var.q(p);
            y8Var.u(this.f18678f);
            y8Var.z();
        }
        if (this.a != null && h()) {
            y8Var.q(q);
            this.a.b(y8Var);
            y8Var.z();
        }
        if (i()) {
            y8Var.q(r);
            y8Var.x(this.f151a);
            y8Var.z();
        }
        if (this.f150a != null && j()) {
            y8Var.q(s);
            y8Var.s(new x8((byte) 11, (byte) 11, this.f150a.size()));
            for (Map.Entry<String, String> entry : this.f150a.entrySet()) {
                y8Var.u(entry.getKey());
                y8Var.u(entry.getValue());
            }
            y8Var.B();
            y8Var.z();
        }
        if (this.f18679g != null && k()) {
            y8Var.q(t);
            y8Var.u(this.f18679g);
            y8Var.z();
        }
        if (this.f18680h != null && l()) {
            y8Var.q(u);
            y8Var.u(this.f18680h);
            y8Var.z();
        }
        y8Var.A();
        y8Var.m();
    }

    /* renamed from: b */
    public boolean m74b() {
        return this.f147a != null;
    }

    public String c() {
        return this.f18677e;
    }

    /* renamed from: c */
    public boolean m75c() {
        return this.b != null;
    }

    public String d() {
        return this.f18678f;
    }

    /* renamed from: d */
    public boolean m76d() {
        return this.f18676c != null;
    }

    public String e() {
        return this.f18679g;
    }

    /* renamed from: e */
    public boolean m77e() {
        return this.d != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof g8)) {
            return m73a((g8) obj);
        }
        return false;
    }

    public String f() {
        return this.f18680h;
    }

    /* renamed from: f */
    public boolean m78f() {
        return this.f18677e != null;
    }

    public boolean g() {
        return this.f18678f != null;
    }

    public boolean h() {
        return this.a != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f149a.get(0);
    }

    public boolean j() {
        return this.f150a != null;
    }

    public boolean k() {
        return this.f18679g != null;
    }

    public boolean l() {
        return this.f18680h != null;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionSendMessage(");
        boolean z2 = false;
        if (m72a()) {
            sb.append("debug:");
            String str = this.f148a;
            if (str == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str);
            }
            z = false;
        } else {
            z = true;
        }
        if (m74b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            r7 r7Var = this.f147a;
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
        String str3 = this.f18676c;
        if (str3 == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            sb.append(str3);
        }
        if (m77e()) {
            sb.append(", ");
            sb.append("packageName:");
            String str4 = this.d;
            if (str4 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str4);
            }
        }
        if (m78f()) {
            sb.append(", ");
            sb.append("topic:");
            String str5 = this.f18677e;
            if (str5 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str5);
            }
        }
        if (g()) {
            sb.append(", ");
            sb.append("aliasName:");
            String str6 = this.f18678f;
            if (str6 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str6);
            }
        }
        if (h()) {
            sb.append(", ");
            sb.append("message:");
            o7 o7Var = this.a;
            if (o7Var == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(o7Var);
            }
        }
        if (i()) {
            sb.append(", ");
            sb.append("needAck:");
            sb.append(this.f151a);
        }
        if (j()) {
            sb.append(", ");
            sb.append("params:");
            Map<String, String> map = this.f150a;
            if (map == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(map);
            }
        }
        if (k()) {
            sb.append(", ");
            sb.append("category:");
            String str7 = this.f18679g;
            if (str7 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str7);
            }
        }
        if (l()) {
            sb.append(", ");
            sb.append("userAccount:");
            String str8 = this.f18680h;
            if (str8 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str8);
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
