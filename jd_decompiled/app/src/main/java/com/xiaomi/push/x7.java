package com.xiaomi.push;

import com.jd.dynamic.DYConstants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes11.dex */
public class x7 implements n8<x7, Object>, Serializable, Cloneable {

    /* renamed from: g  reason: collision with root package name */
    private static final e9 f19310g = new e9("XmPushActionCommandResult");

    /* renamed from: h  reason: collision with root package name */
    private static final v8 f19311h = new v8("", (byte) 12, 2);

    /* renamed from: i  reason: collision with root package name */
    private static final v8 f19312i = new v8("", (byte) 11, 3);

    /* renamed from: j  reason: collision with root package name */
    private static final v8 f19313j = new v8("", (byte) 11, 4);

    /* renamed from: k  reason: collision with root package name */
    private static final v8 f19314k = new v8("", (byte) 11, 5);

    /* renamed from: l  reason: collision with root package name */
    private static final v8 f19315l = new v8("", (byte) 10, 7);

    /* renamed from: m  reason: collision with root package name */
    private static final v8 f19316m = new v8("", (byte) 11, 8);

    /* renamed from: n  reason: collision with root package name */
    private static final v8 f19317n = new v8("", (byte) 11, 9);
    private static final v8 o = new v8("", (byte) 15, 10);
    private static final v8 p = new v8("", (byte) 11, 12);
    private static final v8 q = new v8("", (byte) 2, 13);
    public long a;

    /* renamed from: a  reason: collision with other field name */
    public r7 f257a;

    /* renamed from: a  reason: collision with other field name */
    public String f258a;

    /* renamed from: a  reason: collision with other field name */
    public List<String> f260a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public String f19318c;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public String f19319e;

    /* renamed from: f  reason: collision with root package name */
    public String f19320f;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f259a = new BitSet(2);

    /* renamed from: a  reason: collision with other field name */
    public boolean f261a = true;

    @Override // java.lang.Comparable
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public int compareTo(x7 x7Var) {
        int k2;
        int e2;
        int g2;
        int e3;
        int e4;
        int c2;
        int e5;
        int e6;
        int e7;
        int d;
        if (getClass().equals(x7Var.getClass())) {
            int compareTo = Boolean.valueOf(m180a()).compareTo(Boolean.valueOf(x7Var.m180a()));
            if (compareTo != 0) {
                return compareTo;
            }
            if (!m180a() || (d = o8.d(this.f257a, x7Var.f257a)) == 0) {
                int compareTo2 = Boolean.valueOf(m182b()).compareTo(Boolean.valueOf(x7Var.m182b()));
                if (compareTo2 != 0) {
                    return compareTo2;
                }
                if (!m182b() || (e7 = o8.e(this.f258a, x7Var.f258a)) == 0) {
                    int compareTo3 = Boolean.valueOf(m183c()).compareTo(Boolean.valueOf(x7Var.m183c()));
                    if (compareTo3 != 0) {
                        return compareTo3;
                    }
                    if (!m183c() || (e6 = o8.e(this.b, x7Var.b)) == 0) {
                        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(x7Var.d()));
                        if (compareTo4 != 0) {
                            return compareTo4;
                        }
                        if (!d() || (e5 = o8.e(this.f19318c, x7Var.f19318c)) == 0) {
                            int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(x7Var.e()));
                            if (compareTo5 != 0) {
                                return compareTo5;
                            }
                            if (!e() || (c2 = o8.c(this.a, x7Var.a)) == 0) {
                                int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(x7Var.f()));
                                if (compareTo6 != 0) {
                                    return compareTo6;
                                }
                                if (!f() || (e4 = o8.e(this.d, x7Var.d)) == 0) {
                                    int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(x7Var.g()));
                                    if (compareTo7 != 0) {
                                        return compareTo7;
                                    }
                                    if (!g() || (e3 = o8.e(this.f19319e, x7Var.f19319e)) == 0) {
                                        int compareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(x7Var.h()));
                                        if (compareTo8 != 0) {
                                            return compareTo8;
                                        }
                                        if (!h() || (g2 = o8.g(this.f260a, x7Var.f260a)) == 0) {
                                            int compareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(x7Var.i()));
                                            if (compareTo9 != 0) {
                                                return compareTo9;
                                            }
                                            if (!i() || (e2 = o8.e(this.f19320f, x7Var.f19320f)) == 0) {
                                                int compareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(x7Var.j()));
                                                if (compareTo10 != 0) {
                                                    return compareTo10;
                                                }
                                                if (!j() || (k2 = o8.k(this.f261a, x7Var.f261a)) == 0) {
                                                    return 0;
                                                }
                                                return k2;
                                            }
                                            return e2;
                                        }
                                        return g2;
                                    }
                                    return e3;
                                }
                                return e4;
                            }
                            return c2;
                        }
                        return e5;
                    }
                    return e6;
                }
                return e7;
            }
            return d;
        }
        return getClass().getName().compareTo(x7Var.getClass().getName());
    }

    public String a() {
        return this.f258a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public List<String> m178a() {
        return this.f260a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m179a() {
        if (this.f258a == null) {
            throw new z8("Required field 'id' was not present! Struct: " + toString());
        } else if (this.b == null) {
            throw new z8("Required field 'appId' was not present! Struct: " + toString());
        } else if (this.f19318c != null) {
        } else {
            throw new z8("Required field 'cmdName' was not present! Struct: " + toString());
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
                    m179a();
                    return;
                }
                throw new z8("Required field 'errorCode' was not found in serialized data! Struct: " + toString());
            }
            switch (e2.f19282c) {
                case 2:
                    if (b == 12) {
                        r7 r7Var = new r7();
                        this.f257a = r7Var;
                        r7Var.a(y8Var);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 3:
                    if (b == 11) {
                        this.f258a = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 4:
                    if (b == 11) {
                        this.b = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 5:
                    if (b == 11) {
                        this.f19318c = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 7:
                    if (b == 10) {
                        this.a = y8Var.d();
                        a(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 8:
                    if (b == 11) {
                        this.d = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 9:
                    if (b == 11) {
                        this.f19319e = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 10:
                    if (b == 15) {
                        w8 f2 = y8Var.f();
                        this.f260a = new ArrayList(f2.b);
                        for (int i2 = 0; i2 < f2.b; i2++) {
                            this.f260a.add(y8Var.j());
                        }
                        y8Var.G();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 12:
                    if (b == 11) {
                        this.f19320f = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 13:
                    if (b == 2) {
                        this.f261a = y8Var.y();
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
        this.f259a.set(0, z);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m180a() {
        return this.f257a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m181a(x7 x7Var) {
        if (x7Var == null) {
            return false;
        }
        boolean m180a = m180a();
        boolean m180a2 = x7Var.m180a();
        if ((m180a || m180a2) && !(m180a && m180a2 && this.f257a.m132a(x7Var.f257a))) {
            return false;
        }
        boolean m182b = m182b();
        boolean m182b2 = x7Var.m182b();
        if ((m182b || m182b2) && !(m182b && m182b2 && this.f258a.equals(x7Var.f258a))) {
            return false;
        }
        boolean m183c = m183c();
        boolean m183c2 = x7Var.m183c();
        if ((m183c || m183c2) && !(m183c && m183c2 && this.b.equals(x7Var.b))) {
            return false;
        }
        boolean d = d();
        boolean d2 = x7Var.d();
        if (((d || d2) && !(d && d2 && this.f19318c.equals(x7Var.f19318c))) || this.a != x7Var.a) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = x7Var.f();
        if ((f2 || f3) && !(f2 && f3 && this.d.equals(x7Var.d))) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = x7Var.g();
        if ((g2 || g3) && !(g2 && g3 && this.f19319e.equals(x7Var.f19319e))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = x7Var.h();
        if ((h2 || h3) && !(h2 && h3 && this.f260a.equals(x7Var.f260a))) {
            return false;
        }
        boolean i2 = i();
        boolean i3 = x7Var.i();
        if ((i2 || i3) && !(i2 && i3 && this.f19320f.equals(x7Var.f19320f))) {
            return false;
        }
        boolean j2 = j();
        boolean j3 = x7Var.j();
        if (j2 || j3) {
            return j2 && j3 && this.f261a == x7Var.f261a;
        }
        return true;
    }

    public String b() {
        return this.f19318c;
    }

    @Override // com.xiaomi.push.n8
    public void b(y8 y8Var) {
        m179a();
        y8Var.t(f19310g);
        if (this.f257a != null && m180a()) {
            y8Var.q(f19311h);
            this.f257a.b(y8Var);
            y8Var.z();
        }
        if (this.f258a != null) {
            y8Var.q(f19312i);
            y8Var.u(this.f258a);
            y8Var.z();
        }
        if (this.b != null) {
            y8Var.q(f19313j);
            y8Var.u(this.b);
            y8Var.z();
        }
        if (this.f19318c != null) {
            y8Var.q(f19314k);
            y8Var.u(this.f19318c);
            y8Var.z();
        }
        y8Var.q(f19315l);
        y8Var.p(this.a);
        y8Var.z();
        if (this.d != null && f()) {
            y8Var.q(f19316m);
            y8Var.u(this.d);
            y8Var.z();
        }
        if (this.f19319e != null && g()) {
            y8Var.q(f19317n);
            y8Var.u(this.f19319e);
            y8Var.z();
        }
        if (this.f260a != null && h()) {
            y8Var.q(o);
            y8Var.r(new w8((byte) 11, this.f260a.size()));
            Iterator<String> it = this.f260a.iterator();
            while (it.hasNext()) {
                y8Var.u(it.next());
            }
            y8Var.C();
            y8Var.z();
        }
        if (this.f19320f != null && i()) {
            y8Var.q(p);
            y8Var.u(this.f19320f);
            y8Var.z();
        }
        if (j()) {
            y8Var.q(q);
            y8Var.x(this.f261a);
            y8Var.z();
        }
        y8Var.A();
        y8Var.m();
    }

    public void b(boolean z) {
        this.f259a.set(1, z);
    }

    /* renamed from: b  reason: collision with other method in class */
    public boolean m182b() {
        return this.f258a != null;
    }

    public String c() {
        return this.f19320f;
    }

    /* renamed from: c  reason: collision with other method in class */
    public boolean m183c() {
        return this.b != null;
    }

    public boolean d() {
        return this.f19318c != null;
    }

    public boolean e() {
        return this.f259a.get(0);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof x7)) {
            return m181a((x7) obj);
        }
        return false;
    }

    public boolean f() {
        return this.d != null;
    }

    public boolean g() {
        return this.f19319e != null;
    }

    public boolean h() {
        return this.f260a != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f19320f != null;
    }

    public boolean j() {
        return this.f259a.get(1);
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionCommandResult(");
        if (m180a()) {
            sb.append("target:");
            r7 r7Var = this.f257a;
            if (r7Var == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(r7Var);
            }
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("id:");
        String str = this.f258a;
        if (str == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            sb.append(str);
        }
        sb.append(", ");
        sb.append("appId:");
        String str2 = this.b;
        if (str2 == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            sb.append(str2);
        }
        sb.append(", ");
        sb.append("cmdName:");
        String str3 = this.f19318c;
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
            String str5 = this.f19319e;
            if (str5 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str5);
            }
        }
        if (h()) {
            sb.append(", ");
            sb.append("cmdArgs:");
            List<String> list = this.f260a;
            if (list == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(list);
            }
        }
        if (i()) {
            sb.append(", ");
            sb.append("category:");
            String str6 = this.f19320f;
            if (str6 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str6);
            }
        }
        if (j()) {
            sb.append(", ");
            sb.append("response2Client:");
            sb.append(this.f261a);
        }
        sb.append(")");
        return sb.toString();
    }
}
