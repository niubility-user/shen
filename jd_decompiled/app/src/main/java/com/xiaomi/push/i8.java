package com.xiaomi.push;

import com.jd.dynamic.DYConstants;
import java.io.Serializable;
import java.util.BitSet;

/* loaded from: classes11.dex */
public class i8 implements n8<i8, Object>, Serializable, Cloneable {

    /* renamed from: h  reason: collision with root package name */
    private static final e9 f18751h = new e9("XmPushActionSubscriptionResult");

    /* renamed from: i  reason: collision with root package name */
    private static final v8 f18752i = new v8("", (byte) 11, 1);

    /* renamed from: j  reason: collision with root package name */
    private static final v8 f18753j = new v8("", (byte) 12, 2);

    /* renamed from: k  reason: collision with root package name */
    private static final v8 f18754k = new v8("", (byte) 11, 3);

    /* renamed from: l  reason: collision with root package name */
    private static final v8 f18755l = new v8("", (byte) 11, 4);

    /* renamed from: m  reason: collision with root package name */
    private static final v8 f18756m = new v8("", (byte) 10, 6);

    /* renamed from: n  reason: collision with root package name */
    private static final v8 f18757n = new v8("", (byte) 11, 7);
    private static final v8 o = new v8("", (byte) 11, 8);
    private static final v8 p = new v8("", (byte) 11, 9);
    private static final v8 q = new v8("", (byte) 11, 10);
    public long a;

    /* renamed from: a  reason: collision with other field name */
    public r7 f156a;

    /* renamed from: a  reason: collision with other field name */
    public String f157a;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f158a = new BitSet(1);
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public String f18758c;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public String f18759e;

    /* renamed from: f  reason: collision with root package name */
    public String f18760f;

    /* renamed from: g  reason: collision with root package name */
    public String f18761g;

    @Override // java.lang.Comparable
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public int compareTo(i8 i8Var) {
        int e2;
        int e3;
        int e4;
        int e5;
        int c2;
        int e6;
        int e7;
        int d;
        int e8;
        if (getClass().equals(i8Var.getClass())) {
            int compareTo = Boolean.valueOf(m83a()).compareTo(Boolean.valueOf(i8Var.m83a()));
            if (compareTo != 0) {
                return compareTo;
            }
            if (!m83a() || (e8 = o8.e(this.f157a, i8Var.f157a)) == 0) {
                int compareTo2 = Boolean.valueOf(m85b()).compareTo(Boolean.valueOf(i8Var.m85b()));
                if (compareTo2 != 0) {
                    return compareTo2;
                }
                if (!m85b() || (d = o8.d(this.f156a, i8Var.f156a)) == 0) {
                    int compareTo3 = Boolean.valueOf(m86c()).compareTo(Boolean.valueOf(i8Var.m86c()));
                    if (compareTo3 != 0) {
                        return compareTo3;
                    }
                    if (!m86c() || (e7 = o8.e(this.b, i8Var.b)) == 0) {
                        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(i8Var.d()));
                        if (compareTo4 != 0) {
                            return compareTo4;
                        }
                        if (!d() || (e6 = o8.e(this.f18758c, i8Var.f18758c)) == 0) {
                            int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(i8Var.e()));
                            if (compareTo5 != 0) {
                                return compareTo5;
                            }
                            if (!e() || (c2 = o8.c(this.a, i8Var.a)) == 0) {
                                int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(i8Var.f()));
                                if (compareTo6 != 0) {
                                    return compareTo6;
                                }
                                if (!f() || (e5 = o8.e(this.d, i8Var.d)) == 0) {
                                    int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(i8Var.g()));
                                    if (compareTo7 != 0) {
                                        return compareTo7;
                                    }
                                    if (!g() || (e4 = o8.e(this.f18759e, i8Var.f18759e)) == 0) {
                                        int compareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(i8Var.h()));
                                        if (compareTo8 != 0) {
                                            return compareTo8;
                                        }
                                        if (!h() || (e3 = o8.e(this.f18760f, i8Var.f18760f)) == 0) {
                                            int compareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(i8Var.i()));
                                            if (compareTo9 != 0) {
                                                return compareTo9;
                                            }
                                            if (!i() || (e2 = o8.e(this.f18761g, i8Var.f18761g)) == 0) {
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
        return getClass().getName().compareTo(i8Var.getClass().getName());
    }

    public String a() {
        return this.b;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m82a() {
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
                m82a();
                return;
            }
            switch (e2.f19282c) {
                case 1:
                    if (b == 11) {
                        this.f157a = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 2:
                    if (b == 12) {
                        r7 r7Var = new r7();
                        this.f156a = r7Var;
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
                        this.f18758c = y8Var.j();
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
                        this.f18759e = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 9:
                    if (b == 11) {
                        this.f18760f = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 10:
                    if (b == 11) {
                        this.f18761g = y8Var.j();
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
        this.f158a.set(0, z);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m83a() {
        return this.f157a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m84a(i8 i8Var) {
        if (i8Var == null) {
            return false;
        }
        boolean m83a = m83a();
        boolean m83a2 = i8Var.m83a();
        if ((m83a || m83a2) && !(m83a && m83a2 && this.f157a.equals(i8Var.f157a))) {
            return false;
        }
        boolean m85b = m85b();
        boolean m85b2 = i8Var.m85b();
        if ((m85b || m85b2) && !(m85b && m85b2 && this.f156a.m132a(i8Var.f156a))) {
            return false;
        }
        boolean m86c = m86c();
        boolean m86c2 = i8Var.m86c();
        if ((m86c || m86c2) && !(m86c && m86c2 && this.b.equals(i8Var.b))) {
            return false;
        }
        boolean d = d();
        boolean d2 = i8Var.d();
        if ((d || d2) && !(d && d2 && this.f18758c.equals(i8Var.f18758c))) {
            return false;
        }
        boolean e2 = e();
        boolean e3 = i8Var.e();
        if ((e2 || e3) && !(e2 && e3 && this.a == i8Var.a)) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = i8Var.f();
        if ((f2 || f3) && !(f2 && f3 && this.d.equals(i8Var.d))) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = i8Var.g();
        if ((g2 || g3) && !(g2 && g3 && this.f18759e.equals(i8Var.f18759e))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = i8Var.h();
        if ((h2 || h3) && !(h2 && h3 && this.f18760f.equals(i8Var.f18760f))) {
            return false;
        }
        boolean i2 = i();
        boolean i3 = i8Var.i();
        if (i2 || i3) {
            return i2 && i3 && this.f18761g.equals(i8Var.f18761g);
        }
        return true;
    }

    public String b() {
        return this.f18759e;
    }

    @Override // com.xiaomi.push.n8
    public void b(y8 y8Var) {
        m82a();
        y8Var.t(f18751h);
        if (this.f157a != null && m83a()) {
            y8Var.q(f18752i);
            y8Var.u(this.f157a);
            y8Var.z();
        }
        if (this.f156a != null && m85b()) {
            y8Var.q(f18753j);
            this.f156a.b(y8Var);
            y8Var.z();
        }
        if (this.b != null) {
            y8Var.q(f18754k);
            y8Var.u(this.b);
            y8Var.z();
        }
        if (this.f18758c != null && d()) {
            y8Var.q(f18755l);
            y8Var.u(this.f18758c);
            y8Var.z();
        }
        if (e()) {
            y8Var.q(f18756m);
            y8Var.p(this.a);
            y8Var.z();
        }
        if (this.d != null && f()) {
            y8Var.q(f18757n);
            y8Var.u(this.d);
            y8Var.z();
        }
        if (this.f18759e != null && g()) {
            y8Var.q(o);
            y8Var.u(this.f18759e);
            y8Var.z();
        }
        if (this.f18760f != null && h()) {
            y8Var.q(p);
            y8Var.u(this.f18760f);
            y8Var.z();
        }
        if (this.f18761g != null && i()) {
            y8Var.q(q);
            y8Var.u(this.f18761g);
            y8Var.z();
        }
        y8Var.A();
        y8Var.m();
    }

    /* renamed from: b  reason: collision with other method in class */
    public boolean m85b() {
        return this.f156a != null;
    }

    public String c() {
        return this.f18761g;
    }

    /* renamed from: c  reason: collision with other method in class */
    public boolean m86c() {
        return this.b != null;
    }

    public boolean d() {
        return this.f18758c != null;
    }

    public boolean e() {
        return this.f158a.get(0);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof i8)) {
            return m84a((i8) obj);
        }
        return false;
    }

    public boolean f() {
        return this.d != null;
    }

    public boolean g() {
        return this.f18759e != null;
    }

    public boolean h() {
        return this.f18760f != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f18761g != null;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionSubscriptionResult(");
        boolean z2 = false;
        if (m83a()) {
            sb.append("debug:");
            String str = this.f157a;
            if (str == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str);
            }
            z = false;
        } else {
            z = true;
        }
        if (m85b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            r7 r7Var = this.f156a;
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
            String str3 = this.f18758c;
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
            String str5 = this.f18759e;
            if (str5 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str5);
            }
        }
        if (h()) {
            sb.append(", ");
            sb.append("packageName:");
            String str6 = this.f18760f;
            if (str6 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str6);
            }
        }
        if (i()) {
            sb.append(", ");
            sb.append("category:");
            String str7 = this.f18761g;
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
