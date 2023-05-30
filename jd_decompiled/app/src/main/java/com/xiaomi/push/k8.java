package com.xiaomi.push;

import com.jd.dynamic.DYConstants;
import java.io.Serializable;
import java.util.BitSet;

/* loaded from: classes11.dex */
public class k8 implements n8<k8, Object>, Serializable, Cloneable {

    /* renamed from: g  reason: collision with root package name */
    private static final e9 f18802g = new e9("XmPushActionUnRegistrationResult");

    /* renamed from: h  reason: collision with root package name */
    private static final v8 f18803h = new v8("", (byte) 11, 1);

    /* renamed from: i  reason: collision with root package name */
    private static final v8 f18804i = new v8("", (byte) 12, 2);

    /* renamed from: j  reason: collision with root package name */
    private static final v8 f18805j = new v8("", (byte) 11, 3);

    /* renamed from: k  reason: collision with root package name */
    private static final v8 f18806k = new v8("", (byte) 11, 4);

    /* renamed from: l  reason: collision with root package name */
    private static final v8 f18807l = new v8("", (byte) 10, 6);

    /* renamed from: m  reason: collision with root package name */
    private static final v8 f18808m = new v8("", (byte) 11, 7);

    /* renamed from: n  reason: collision with root package name */
    private static final v8 f18809n = new v8("", (byte) 11, 8);
    private static final v8 o = new v8("", (byte) 10, 9);
    private static final v8 p = new v8("", (byte) 10, 10);
    public long a;

    /* renamed from: a  reason: collision with other field name */
    public r7 f167a;

    /* renamed from: a  reason: collision with other field name */
    public String f168a;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f169a = new BitSet(3);
    public long b;

    /* renamed from: b  reason: collision with other field name */
    public String f170b;

    /* renamed from: c  reason: collision with root package name */
    public long f18810c;

    /* renamed from: c  reason: collision with other field name */
    public String f171c;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public String f18811e;

    @Override // java.lang.Comparable
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public int compareTo(k8 k8Var) {
        int c2;
        int c3;
        int e2;
        int e3;
        int c4;
        int e4;
        int e5;
        int d;
        int e6;
        if (getClass().equals(k8Var.getClass())) {
            int compareTo = Boolean.valueOf(m93a()).compareTo(Boolean.valueOf(k8Var.m93a()));
            if (compareTo != 0) {
                return compareTo;
            }
            if (!m93a() || (e6 = o8.e(this.f168a, k8Var.f168a)) == 0) {
                int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(k8Var.b()));
                if (compareTo2 != 0) {
                    return compareTo2;
                }
                if (!b() || (d = o8.d(this.f167a, k8Var.f167a)) == 0) {
                    int compareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(k8Var.c()));
                    if (compareTo3 != 0) {
                        return compareTo3;
                    }
                    if (!c() || (e5 = o8.e(this.f170b, k8Var.f170b)) == 0) {
                        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(k8Var.d()));
                        if (compareTo4 != 0) {
                            return compareTo4;
                        }
                        if (!d() || (e4 = o8.e(this.f171c, k8Var.f171c)) == 0) {
                            int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(k8Var.e()));
                            if (compareTo5 != 0) {
                                return compareTo5;
                            }
                            if (!e() || (c4 = o8.c(this.a, k8Var.a)) == 0) {
                                int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(k8Var.f()));
                                if (compareTo6 != 0) {
                                    return compareTo6;
                                }
                                if (!f() || (e3 = o8.e(this.d, k8Var.d)) == 0) {
                                    int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(k8Var.g()));
                                    if (compareTo7 != 0) {
                                        return compareTo7;
                                    }
                                    if (!g() || (e2 = o8.e(this.f18811e, k8Var.f18811e)) == 0) {
                                        int compareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(k8Var.h()));
                                        if (compareTo8 != 0) {
                                            return compareTo8;
                                        }
                                        if (!h() || (c3 = o8.c(this.b, k8Var.b)) == 0) {
                                            int compareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(k8Var.i()));
                                            if (compareTo9 != 0) {
                                                return compareTo9;
                                            }
                                            if (!i() || (c2 = o8.c(this.f18810c, k8Var.f18810c)) == 0) {
                                                return 0;
                                            }
                                            return c2;
                                        }
                                        return c3;
                                    }
                                    return e2;
                                }
                                return e3;
                            }
                            return c4;
                        }
                        return e4;
                    }
                    return e5;
                }
                return d;
            }
            return e6;
        }
        return getClass().getName().compareTo(k8Var.getClass().getName());
    }

    public String a() {
        return this.f18811e;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m92a() {
        if (this.f170b == null) {
            throw new z8("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f171c != null) {
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
                    m92a();
                    return;
                }
                throw new z8("Required field 'errorCode' was not found in serialized data! Struct: " + toString());
            }
            switch (e2.f19282c) {
                case 1:
                    if (b == 11) {
                        this.f168a = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 2:
                    if (b == 12) {
                        r7 r7Var = new r7();
                        this.f167a = r7Var;
                        r7Var.a(y8Var);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 3:
                    if (b == 11) {
                        this.f170b = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 4:
                    if (b == 11) {
                        this.f171c = y8Var.j();
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
                        this.f18811e = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 9:
                    if (b == 10) {
                        this.b = y8Var.d();
                        b(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 10:
                    if (b == 10) {
                        this.f18810c = y8Var.d();
                        c(true);
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
        this.f169a.set(0, z);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m93a() {
        return this.f168a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m94a(k8 k8Var) {
        if (k8Var == null) {
            return false;
        }
        boolean m93a = m93a();
        boolean m93a2 = k8Var.m93a();
        if ((m93a || m93a2) && !(m93a && m93a2 && this.f168a.equals(k8Var.f168a))) {
            return false;
        }
        boolean b = b();
        boolean b2 = k8Var.b();
        if ((b || b2) && !(b && b2 && this.f167a.m132a(k8Var.f167a))) {
            return false;
        }
        boolean c2 = c();
        boolean c3 = k8Var.c();
        if ((c2 || c3) && !(c2 && c3 && this.f170b.equals(k8Var.f170b))) {
            return false;
        }
        boolean d = d();
        boolean d2 = k8Var.d();
        if (((d || d2) && !(d && d2 && this.f171c.equals(k8Var.f171c))) || this.a != k8Var.a) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = k8Var.f();
        if ((f2 || f3) && !(f2 && f3 && this.d.equals(k8Var.d))) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = k8Var.g();
        if ((g2 || g3) && !(g2 && g3 && this.f18811e.equals(k8Var.f18811e))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = k8Var.h();
        if ((h2 || h3) && !(h2 && h3 && this.b == k8Var.b)) {
            return false;
        }
        boolean i2 = i();
        boolean i3 = k8Var.i();
        if (i2 || i3) {
            return i2 && i3 && this.f18810c == k8Var.f18810c;
        }
        return true;
    }

    @Override // com.xiaomi.push.n8
    public void b(y8 y8Var) {
        m92a();
        y8Var.t(f18802g);
        if (this.f168a != null && m93a()) {
            y8Var.q(f18803h);
            y8Var.u(this.f168a);
            y8Var.z();
        }
        if (this.f167a != null && b()) {
            y8Var.q(f18804i);
            this.f167a.b(y8Var);
            y8Var.z();
        }
        if (this.f170b != null) {
            y8Var.q(f18805j);
            y8Var.u(this.f170b);
            y8Var.z();
        }
        if (this.f171c != null) {
            y8Var.q(f18806k);
            y8Var.u(this.f171c);
            y8Var.z();
        }
        y8Var.q(f18807l);
        y8Var.p(this.a);
        y8Var.z();
        if (this.d != null && f()) {
            y8Var.q(f18808m);
            y8Var.u(this.d);
            y8Var.z();
        }
        if (this.f18811e != null && g()) {
            y8Var.q(f18809n);
            y8Var.u(this.f18811e);
            y8Var.z();
        }
        if (h()) {
            y8Var.q(o);
            y8Var.p(this.b);
            y8Var.z();
        }
        if (i()) {
            y8Var.q(p);
            y8Var.p(this.f18810c);
            y8Var.z();
        }
        y8Var.A();
        y8Var.m();
    }

    public void b(boolean z) {
        this.f169a.set(1, z);
    }

    public boolean b() {
        return this.f167a != null;
    }

    public void c(boolean z) {
        this.f169a.set(2, z);
    }

    public boolean c() {
        return this.f170b != null;
    }

    public boolean d() {
        return this.f171c != null;
    }

    public boolean e() {
        return this.f169a.get(0);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof k8)) {
            return m94a((k8) obj);
        }
        return false;
    }

    public boolean f() {
        return this.d != null;
    }

    public boolean g() {
        return this.f18811e != null;
    }

    public boolean h() {
        return this.f169a.get(1);
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f169a.get(2);
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionUnRegistrationResult(");
        boolean z2 = false;
        if (m93a()) {
            sb.append("debug:");
            String str = this.f168a;
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
            r7 r7Var = this.f167a;
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
        String str2 = this.f170b;
        if (str2 == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            sb.append(str2);
        }
        sb.append(", ");
        sb.append("appId:");
        String str3 = this.f171c;
        if (str3 == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            sb.append(str3);
        }
        sb.append(", ");
        sb.append("errorCode:");
        sb.append(this.a);
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
            sb.append("packageName:");
            String str5 = this.f18811e;
            if (str5 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str5);
            }
        }
        if (h()) {
            sb.append(", ");
            sb.append("unRegisteredAt:");
            sb.append(this.b);
        }
        if (i()) {
            sb.append(", ");
            sb.append("costTime:");
            sb.append(this.f18810c);
        }
        sb.append(")");
        return sb.toString();
    }
}
