package com.xiaomi.push;

import com.jd.dynamic.DYConstants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes11.dex */
public class h8 implements n8<h8, Object>, Serializable, Cloneable {

    /* renamed from: g  reason: collision with root package name */
    private static final e9 f18717g = new e9("XmPushActionSubscription");

    /* renamed from: h  reason: collision with root package name */
    private static final v8 f18718h = new v8("", (byte) 11, 1);

    /* renamed from: i  reason: collision with root package name */
    private static final v8 f18719i = new v8("", (byte) 12, 2);

    /* renamed from: j  reason: collision with root package name */
    private static final v8 f18720j = new v8("", (byte) 11, 3);

    /* renamed from: k  reason: collision with root package name */
    private static final v8 f18721k = new v8("", (byte) 11, 4);

    /* renamed from: l  reason: collision with root package name */
    private static final v8 f18722l = new v8("", (byte) 11, 5);

    /* renamed from: m  reason: collision with root package name */
    private static final v8 f18723m = new v8("", (byte) 11, 6);

    /* renamed from: n  reason: collision with root package name */
    private static final v8 f18724n = new v8("", (byte) 11, 7);
    private static final v8 o = new v8("", (byte) 15, 8);
    public r7 a;

    /* renamed from: a  reason: collision with other field name */
    public String f153a;

    /* renamed from: a  reason: collision with other field name */
    public List<String> f154a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public String f18725c;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public String f18726e;

    /* renamed from: f  reason: collision with root package name */
    public String f18727f;

    @Override // java.lang.Comparable
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public int compareTo(h8 h8Var) {
        int g2;
        int e2;
        int e3;
        int e4;
        int e5;
        int e6;
        int d;
        int e7;
        if (getClass().equals(h8Var.getClass())) {
            int compareTo = Boolean.valueOf(m79a()).compareTo(Boolean.valueOf(h8Var.m79a()));
            if (compareTo != 0) {
                return compareTo;
            }
            if (!m79a() || (e7 = o8.e(this.f153a, h8Var.f153a)) == 0) {
                int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(h8Var.b()));
                if (compareTo2 != 0) {
                    return compareTo2;
                }
                if (!b() || (d = o8.d(this.a, h8Var.a)) == 0) {
                    int compareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(h8Var.c()));
                    if (compareTo3 != 0) {
                        return compareTo3;
                    }
                    if (!c() || (e6 = o8.e(this.b, h8Var.b)) == 0) {
                        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(h8Var.d()));
                        if (compareTo4 != 0) {
                            return compareTo4;
                        }
                        if (!d() || (e5 = o8.e(this.f18725c, h8Var.f18725c)) == 0) {
                            int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(h8Var.e()));
                            if (compareTo5 != 0) {
                                return compareTo5;
                            }
                            if (!e() || (e4 = o8.e(this.d, h8Var.d)) == 0) {
                                int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(h8Var.f()));
                                if (compareTo6 != 0) {
                                    return compareTo6;
                                }
                                if (!f() || (e3 = o8.e(this.f18726e, h8Var.f18726e)) == 0) {
                                    int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(h8Var.g()));
                                    if (compareTo7 != 0) {
                                        return compareTo7;
                                    }
                                    if (!g() || (e2 = o8.e(this.f18727f, h8Var.f18727f)) == 0) {
                                        int compareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(h8Var.h()));
                                        if (compareTo8 != 0) {
                                            return compareTo8;
                                        }
                                        if (!h() || (g2 = o8.g(this.f154a, h8Var.f154a)) == 0) {
                                            return 0;
                                        }
                                        return g2;
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
                return d;
            }
            return e7;
        }
        return getClass().getName().compareTo(h8Var.getClass().getName());
    }

    public h8 a(String str) {
        this.b = str;
        return this;
    }

    public void a() {
        if (this.b == null) {
            throw new z8("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f18725c == null) {
            throw new z8("Required field 'appId' was not present! Struct: " + toString());
        } else if (this.d != null) {
        } else {
            throw new z8("Required field 'topic' was not present! Struct: " + toString());
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
                        this.f153a = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 2:
                    if (b == 12) {
                        r7 r7Var = new r7();
                        this.a = r7Var;
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
                        this.f18725c = y8Var.j();
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
                        this.f18726e = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 7:
                    if (b == 11) {
                        this.f18727f = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 8:
                    if (b == 15) {
                        w8 f2 = y8Var.f();
                        this.f154a = new ArrayList(f2.b);
                        for (int i2 = 0; i2 < f2.b; i2++) {
                            this.f154a.add(y8Var.j());
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

    /* renamed from: a  reason: collision with other method in class */
    public boolean m79a() {
        return this.f153a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m80a(h8 h8Var) {
        if (h8Var == null) {
            return false;
        }
        boolean m79a = m79a();
        boolean m79a2 = h8Var.m79a();
        if ((m79a || m79a2) && !(m79a && m79a2 && this.f153a.equals(h8Var.f153a))) {
            return false;
        }
        boolean b = b();
        boolean b2 = h8Var.b();
        if ((b || b2) && !(b && b2 && this.a.m132a(h8Var.a))) {
            return false;
        }
        boolean c2 = c();
        boolean c3 = h8Var.c();
        if ((c2 || c3) && !(c2 && c3 && this.b.equals(h8Var.b))) {
            return false;
        }
        boolean d = d();
        boolean d2 = h8Var.d();
        if ((d || d2) && !(d && d2 && this.f18725c.equals(h8Var.f18725c))) {
            return false;
        }
        boolean e2 = e();
        boolean e3 = h8Var.e();
        if ((e2 || e3) && !(e2 && e3 && this.d.equals(h8Var.d))) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = h8Var.f();
        if ((f2 || f3) && !(f2 && f3 && this.f18726e.equals(h8Var.f18726e))) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = h8Var.g();
        if ((g2 || g3) && !(g2 && g3 && this.f18727f.equals(h8Var.f18727f))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = h8Var.h();
        if (h2 || h3) {
            return h2 && h3 && this.f154a.equals(h8Var.f154a);
        }
        return true;
    }

    public h8 b(String str) {
        this.f18725c = str;
        return this;
    }

    @Override // com.xiaomi.push.n8
    public void b(y8 y8Var) {
        a();
        y8Var.t(f18717g);
        if (this.f153a != null && m79a()) {
            y8Var.q(f18718h);
            y8Var.u(this.f153a);
            y8Var.z();
        }
        if (this.a != null && b()) {
            y8Var.q(f18719i);
            this.a.b(y8Var);
            y8Var.z();
        }
        if (this.b != null) {
            y8Var.q(f18720j);
            y8Var.u(this.b);
            y8Var.z();
        }
        if (this.f18725c != null) {
            y8Var.q(f18721k);
            y8Var.u(this.f18725c);
            y8Var.z();
        }
        if (this.d != null) {
            y8Var.q(f18722l);
            y8Var.u(this.d);
            y8Var.z();
        }
        if (this.f18726e != null && f()) {
            y8Var.q(f18723m);
            y8Var.u(this.f18726e);
            y8Var.z();
        }
        if (this.f18727f != null && g()) {
            y8Var.q(f18724n);
            y8Var.u(this.f18727f);
            y8Var.z();
        }
        if (this.f154a != null && h()) {
            y8Var.q(o);
            y8Var.r(new w8((byte) 11, this.f154a.size()));
            Iterator<String> it = this.f154a.iterator();
            while (it.hasNext()) {
                y8Var.u(it.next());
            }
            y8Var.C();
            y8Var.z();
        }
        y8Var.A();
        y8Var.m();
    }

    public boolean b() {
        return this.a != null;
    }

    public h8 c(String str) {
        this.d = str;
        return this;
    }

    public boolean c() {
        return this.b != null;
    }

    public h8 d(String str) {
        this.f18726e = str;
        return this;
    }

    public boolean d() {
        return this.f18725c != null;
    }

    public h8 e(String str) {
        this.f18727f = str;
        return this;
    }

    public boolean e() {
        return this.d != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof h8)) {
            return m80a((h8) obj);
        }
        return false;
    }

    public boolean f() {
        return this.f18726e != null;
    }

    public boolean g() {
        return this.f18727f != null;
    }

    public boolean h() {
        return this.f154a != null;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionSubscription(");
        boolean z2 = false;
        if (m79a()) {
            sb.append("debug:");
            String str = this.f153a;
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
            r7 r7Var = this.a;
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
        String str3 = this.f18725c;
        if (str3 == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            sb.append(str3);
        }
        sb.append(", ");
        sb.append("topic:");
        String str4 = this.d;
        if (str4 == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            sb.append(str4);
        }
        if (f()) {
            sb.append(", ");
            sb.append("packageName:");
            String str5 = this.f18726e;
            if (str5 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str5);
            }
        }
        if (g()) {
            sb.append(", ");
            sb.append("category:");
            String str6 = this.f18727f;
            if (str6 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str6);
            }
        }
        if (h()) {
            sb.append(", ");
            sb.append("aliases:");
            List<String> list = this.f154a;
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
