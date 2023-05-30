package com.xiaomi.push;

import com.jd.dynamic.DYConstants;
import java.io.Serializable;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes11.dex */
public class t7 implements n8<t7, Object>, Serializable, Cloneable {

    /* renamed from: h  reason: collision with root package name */
    private static final e9 f19230h = new e9("XmPushActionAckNotification");

    /* renamed from: i  reason: collision with root package name */
    private static final v8 f19231i = new v8("", (byte) 11, 1);

    /* renamed from: j  reason: collision with root package name */
    private static final v8 f19232j = new v8("", (byte) 12, 2);

    /* renamed from: k  reason: collision with root package name */
    private static final v8 f19233k = new v8("", (byte) 11, 3);

    /* renamed from: l  reason: collision with root package name */
    private static final v8 f19234l = new v8("", (byte) 11, 4);

    /* renamed from: m  reason: collision with root package name */
    private static final v8 f19235m = new v8("", (byte) 11, 5);

    /* renamed from: n  reason: collision with root package name */
    private static final v8 f19236n = new v8("", (byte) 10, 7);
    private static final v8 o = new v8("", (byte) 11, 8);
    private static final v8 p = new v8("", (byte) 13, 9);
    private static final v8 q = new v8("", (byte) 11, 10);
    private static final v8 r = new v8("", (byte) 11, 11);

    /* renamed from: a  reason: collision with other field name */
    public r7 f245a;

    /* renamed from: a  reason: collision with other field name */
    public String f246a;

    /* renamed from: a  reason: collision with other field name */
    public Map<String, String> f248a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public String f19237c;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public String f19238e;

    /* renamed from: f  reason: collision with root package name */
    public String f19239f;

    /* renamed from: g  reason: collision with root package name */
    public String f19240g;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f247a = new BitSet(1);
    public long a = 0;

    @Override // java.lang.Comparable
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public int compareTo(t7 t7Var) {
        int e2;
        int e3;
        int h2;
        int e4;
        int c2;
        int e5;
        int e6;
        int e7;
        int d;
        int e8;
        if (getClass().equals(t7Var.getClass())) {
            int compareTo = Boolean.valueOf(m167a()).compareTo(Boolean.valueOf(t7Var.m167a()));
            if (compareTo != 0) {
                return compareTo;
            }
            if (!m167a() || (e8 = o8.e(this.f246a, t7Var.f246a)) == 0) {
                int compareTo2 = Boolean.valueOf(m169b()).compareTo(Boolean.valueOf(t7Var.m169b()));
                if (compareTo2 != 0) {
                    return compareTo2;
                }
                if (!m169b() || (d = o8.d(this.f245a, t7Var.f245a)) == 0) {
                    int compareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(t7Var.c()));
                    if (compareTo3 != 0) {
                        return compareTo3;
                    }
                    if (!c() || (e7 = o8.e(this.b, t7Var.b)) == 0) {
                        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(t7Var.d()));
                        if (compareTo4 != 0) {
                            return compareTo4;
                        }
                        if (!d() || (e6 = o8.e(this.f19237c, t7Var.f19237c)) == 0) {
                            int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(t7Var.e()));
                            if (compareTo5 != 0) {
                                return compareTo5;
                            }
                            if (!e() || (e5 = o8.e(this.d, t7Var.d)) == 0) {
                                int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(t7Var.f()));
                                if (compareTo6 != 0) {
                                    return compareTo6;
                                }
                                if (!f() || (c2 = o8.c(this.a, t7Var.a)) == 0) {
                                    int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(t7Var.g()));
                                    if (compareTo7 != 0) {
                                        return compareTo7;
                                    }
                                    if (!g() || (e4 = o8.e(this.f19238e, t7Var.f19238e)) == 0) {
                                        int compareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(t7Var.h()));
                                        if (compareTo8 != 0) {
                                            return compareTo8;
                                        }
                                        if (!h() || (h2 = o8.h(this.f248a, t7Var.f248a)) == 0) {
                                            int compareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(t7Var.i()));
                                            if (compareTo9 != 0) {
                                                return compareTo9;
                                            }
                                            if (!i() || (e3 = o8.e(this.f19239f, t7Var.f19239f)) == 0) {
                                                int compareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(t7Var.j()));
                                                if (compareTo10 != 0) {
                                                    return compareTo10;
                                                }
                                                if (!j() || (e2 = o8.e(this.f19240g, t7Var.f19240g)) == 0) {
                                                    return 0;
                                                }
                                                return e2;
                                            }
                                            return e3;
                                        }
                                        return h2;
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
            return e8;
        }
        return getClass().getName().compareTo(t7Var.getClass().getName());
    }

    public t7 a(long j2) {
        this.a = j2;
        a(true);
        return this;
    }

    public t7 a(r7 r7Var) {
        this.f245a = r7Var;
        return this;
    }

    public t7 a(String str) {
        this.b = str;
        return this;
    }

    public String a() {
        return this.b;
    }

    /* renamed from: a  reason: collision with other method in class */
    public Map<String, String> m165a() {
        return this.f248a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m166a() {
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
                m166a();
                return;
            }
            switch (e2.f19282c) {
                case 1:
                    if (b == 11) {
                        this.f246a = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 2:
                    if (b == 12) {
                        r7 r7Var = new r7();
                        this.f245a = r7Var;
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
                        this.f19237c = y8Var.j();
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
                        this.f19238e = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 9:
                    if (b == 13) {
                        x8 g2 = y8Var.g();
                        this.f248a = new HashMap(g2.f19321c * 2);
                        for (int i2 = 0; i2 < g2.f19321c; i2++) {
                            this.f248a.put(y8Var.j(), y8Var.j());
                        }
                        y8Var.F();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 10:
                    if (b == 11) {
                        this.f19239f = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 11:
                    if (b == 11) {
                        this.f19240g = y8Var.j();
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
        this.f247a.set(0, z);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m167a() {
        return this.f246a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m168a(t7 t7Var) {
        if (t7Var == null) {
            return false;
        }
        boolean m167a = m167a();
        boolean m167a2 = t7Var.m167a();
        if ((m167a || m167a2) && !(m167a && m167a2 && this.f246a.equals(t7Var.f246a))) {
            return false;
        }
        boolean m169b = m169b();
        boolean m169b2 = t7Var.m169b();
        if ((m169b || m169b2) && !(m169b && m169b2 && this.f245a.m132a(t7Var.f245a))) {
            return false;
        }
        boolean c2 = c();
        boolean c3 = t7Var.c();
        if ((c2 || c3) && !(c2 && c3 && this.b.equals(t7Var.b))) {
            return false;
        }
        boolean d = d();
        boolean d2 = t7Var.d();
        if ((d || d2) && !(d && d2 && this.f19237c.equals(t7Var.f19237c))) {
            return false;
        }
        boolean e2 = e();
        boolean e3 = t7Var.e();
        if ((e2 || e3) && !(e2 && e3 && this.d.equals(t7Var.d))) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = t7Var.f();
        if ((f2 || f3) && !(f2 && f3 && this.a == t7Var.a)) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = t7Var.g();
        if ((g2 || g3) && !(g2 && g3 && this.f19238e.equals(t7Var.f19238e))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = t7Var.h();
        if ((h2 || h3) && !(h2 && h3 && this.f248a.equals(t7Var.f248a))) {
            return false;
        }
        boolean i2 = i();
        boolean i3 = t7Var.i();
        if ((i2 || i3) && !(i2 && i3 && this.f19239f.equals(t7Var.f19239f))) {
            return false;
        }
        boolean j2 = j();
        boolean j3 = t7Var.j();
        if (j2 || j3) {
            return j2 && j3 && this.f19240g.equals(t7Var.f19240g);
        }
        return true;
    }

    public t7 b(String str) {
        this.f19237c = str;
        return this;
    }

    public String b() {
        return this.d;
    }

    @Override // com.xiaomi.push.n8
    public void b(y8 y8Var) {
        m166a();
        y8Var.t(f19230h);
        if (this.f246a != null && m167a()) {
            y8Var.q(f19231i);
            y8Var.u(this.f246a);
            y8Var.z();
        }
        if (this.f245a != null && m169b()) {
            y8Var.q(f19232j);
            this.f245a.b(y8Var);
            y8Var.z();
        }
        if (this.b != null) {
            y8Var.q(f19233k);
            y8Var.u(this.b);
            y8Var.z();
        }
        if (this.f19237c != null && d()) {
            y8Var.q(f19234l);
            y8Var.u(this.f19237c);
            y8Var.z();
        }
        if (this.d != null && e()) {
            y8Var.q(f19235m);
            y8Var.u(this.d);
            y8Var.z();
        }
        if (f()) {
            y8Var.q(f19236n);
            y8Var.p(this.a);
            y8Var.z();
        }
        if (this.f19238e != null && g()) {
            y8Var.q(o);
            y8Var.u(this.f19238e);
            y8Var.z();
        }
        if (this.f248a != null && h()) {
            y8Var.q(p);
            y8Var.s(new x8((byte) 11, (byte) 11, this.f248a.size()));
            for (Map.Entry<String, String> entry : this.f248a.entrySet()) {
                y8Var.u(entry.getKey());
                y8Var.u(entry.getValue());
            }
            y8Var.B();
            y8Var.z();
        }
        if (this.f19239f != null && i()) {
            y8Var.q(q);
            y8Var.u(this.f19239f);
            y8Var.z();
        }
        if (this.f19240g != null && j()) {
            y8Var.q(r);
            y8Var.u(this.f19240g);
            y8Var.z();
        }
        y8Var.A();
        y8Var.m();
    }

    /* renamed from: b  reason: collision with other method in class */
    public boolean m169b() {
        return this.f245a != null;
    }

    public t7 c(String str) {
        this.d = str;
        return this;
    }

    public boolean c() {
        return this.b != null;
    }

    public t7 d(String str) {
        this.f19238e = str;
        return this;
    }

    public boolean d() {
        return this.f19237c != null;
    }

    public t7 e(String str) {
        this.f19239f = str;
        return this;
    }

    public boolean e() {
        return this.d != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof t7)) {
            return m168a((t7) obj);
        }
        return false;
    }

    public boolean f() {
        return this.f247a.get(0);
    }

    public boolean g() {
        return this.f19238e != null;
    }

    public boolean h() {
        return this.f248a != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f19239f != null;
    }

    public boolean j() {
        return this.f19240g != null;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionAckNotification(");
        boolean z2 = false;
        if (m167a()) {
            sb.append("debug:");
            String str = this.f246a;
            if (str == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str);
            }
            z = false;
        } else {
            z = true;
        }
        if (m169b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            r7 r7Var = this.f245a;
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
            String str3 = this.f19237c;
            if (str3 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str3);
            }
        }
        if (e()) {
            sb.append(", ");
            sb.append("type:");
            String str4 = this.d;
            if (str4 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str4);
            }
        }
        if (f()) {
            sb.append(", ");
            sb.append("errorCode:");
            sb.append(this.a);
        }
        if (g()) {
            sb.append(", ");
            sb.append("reason:");
            String str5 = this.f19238e;
            if (str5 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str5);
            }
        }
        if (h()) {
            sb.append(", ");
            sb.append("extra:");
            Map<String, String> map = this.f248a;
            if (map == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(map);
            }
        }
        if (i()) {
            sb.append(", ");
            sb.append("packageName:");
            String str6 = this.f19239f;
            if (str6 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str6);
            }
        }
        if (j()) {
            sb.append(", ");
            sb.append("category:");
            String str7 = this.f19240g;
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
