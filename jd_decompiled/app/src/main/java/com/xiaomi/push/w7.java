package com.xiaomi.push;

import com.jd.dynamic.DYConstants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes11.dex */
public class w7 implements n8<w7, Object>, Serializable, Cloneable {

    /* renamed from: g  reason: collision with root package name */
    private static final e9 f19288g = new e9("XmPushActionCommand");

    /* renamed from: h  reason: collision with root package name */
    private static final v8 f19289h = new v8("", (byte) 12, 2);

    /* renamed from: i  reason: collision with root package name */
    private static final v8 f19290i = new v8("", (byte) 11, 3);

    /* renamed from: j  reason: collision with root package name */
    private static final v8 f19291j = new v8("", (byte) 11, 4);

    /* renamed from: k  reason: collision with root package name */
    private static final v8 f19292k = new v8("", (byte) 11, 5);

    /* renamed from: l  reason: collision with root package name */
    private static final v8 f19293l = new v8("", (byte) 15, 6);

    /* renamed from: m  reason: collision with root package name */
    private static final v8 f19294m = new v8("", (byte) 11, 7);

    /* renamed from: n  reason: collision with root package name */
    private static final v8 f19295n = new v8("", (byte) 11, 9);
    private static final v8 o = new v8("", (byte) 2, 10);
    private static final v8 p = new v8("", (byte) 2, 11);
    private static final v8 q = new v8("", (byte) 10, 12);
    public long a;

    /* renamed from: a  reason: collision with other field name */
    public r7 f251a;

    /* renamed from: a  reason: collision with other field name */
    public String f252a;

    /* renamed from: a  reason: collision with other field name */
    public List<String> f254a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public String f19296c;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public String f19297e;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f253a = new BitSet(3);

    /* renamed from: a  reason: collision with other field name */
    public boolean f255a = false;

    /* renamed from: b  reason: collision with other field name */
    public boolean f256b = true;

    @Override // java.lang.Comparable
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public int compareTo(w7 w7Var) {
        int c2;
        int k2;
        int k3;
        int e2;
        int e3;
        int g2;
        int e4;
        int e5;
        int e6;
        int d;
        if (getClass().equals(w7Var.getClass())) {
            int compareTo = Boolean.valueOf(m176a()).compareTo(Boolean.valueOf(w7Var.m176a()));
            if (compareTo != 0) {
                return compareTo;
            }
            if (!m176a() || (d = o8.d(this.f251a, w7Var.f251a)) == 0) {
                int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(w7Var.b()));
                if (compareTo2 != 0) {
                    return compareTo2;
                }
                if (!b() || (e6 = o8.e(this.f252a, w7Var.f252a)) == 0) {
                    int compareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(w7Var.c()));
                    if (compareTo3 != 0) {
                        return compareTo3;
                    }
                    if (!c() || (e5 = o8.e(this.b, w7Var.b)) == 0) {
                        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(w7Var.d()));
                        if (compareTo4 != 0) {
                            return compareTo4;
                        }
                        if (!d() || (e4 = o8.e(this.f19296c, w7Var.f19296c)) == 0) {
                            int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(w7Var.e()));
                            if (compareTo5 != 0) {
                                return compareTo5;
                            }
                            if (!e() || (g2 = o8.g(this.f254a, w7Var.f254a)) == 0) {
                                int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(w7Var.f()));
                                if (compareTo6 != 0) {
                                    return compareTo6;
                                }
                                if (!f() || (e3 = o8.e(this.d, w7Var.d)) == 0) {
                                    int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(w7Var.g()));
                                    if (compareTo7 != 0) {
                                        return compareTo7;
                                    }
                                    if (!g() || (e2 = o8.e(this.f19297e, w7Var.f19297e)) == 0) {
                                        int compareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(w7Var.h()));
                                        if (compareTo8 != 0) {
                                            return compareTo8;
                                        }
                                        if (!h() || (k3 = o8.k(this.f255a, w7Var.f255a)) == 0) {
                                            int compareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(w7Var.i()));
                                            if (compareTo9 != 0) {
                                                return compareTo9;
                                            }
                                            if (!i() || (k2 = o8.k(this.f256b, w7Var.f256b)) == 0) {
                                                int compareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(w7Var.j()));
                                                if (compareTo10 != 0) {
                                                    return compareTo10;
                                                }
                                                if (!j() || (c2 = o8.c(this.a, w7Var.a)) == 0) {
                                                    return 0;
                                                }
                                                return c2;
                                            }
                                            return k2;
                                        }
                                        return k3;
                                    }
                                    return e2;
                                }
                                return e3;
                            }
                            return g2;
                        }
                        return e4;
                    }
                    return e5;
                }
                return e6;
            }
            return d;
        }
        return getClass().getName().compareTo(w7Var.getClass().getName());
    }

    public w7 a(String str) {
        this.f252a = str;
        return this;
    }

    public String a() {
        return this.f19296c;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m174a() {
        if (this.f252a == null) {
            throw new z8("Required field 'id' was not present! Struct: " + toString());
        } else if (this.b == null) {
            throw new z8("Required field 'appId' was not present! Struct: " + toString());
        } else if (this.f19296c != null) {
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
                m174a();
                return;
            }
            switch (e2.f19282c) {
                case 2:
                    if (b == 12) {
                        r7 r7Var = new r7();
                        this.f251a = r7Var;
                        r7Var.a(y8Var);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 3:
                    if (b == 11) {
                        this.f252a = y8Var.j();
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
                        this.f19296c = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 6:
                    if (b == 15) {
                        w8 f2 = y8Var.f();
                        this.f254a = new ArrayList(f2.b);
                        for (int i2 = 0; i2 < f2.b; i2++) {
                            this.f254a.add(y8Var.j());
                        }
                        y8Var.G();
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
                case 9:
                    if (b == 11) {
                        this.f19297e = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 10:
                    if (b == 2) {
                        this.f255a = y8Var.y();
                        a(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 11:
                    if (b == 2) {
                        this.f256b = y8Var.y();
                        b(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 12:
                    if (b == 10) {
                        this.a = y8Var.d();
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

    /* renamed from: a  reason: collision with other method in class */
    public void m175a(String str) {
        if (this.f254a == null) {
            this.f254a = new ArrayList();
        }
        this.f254a.add(str);
    }

    public void a(boolean z) {
        this.f253a.set(0, z);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m176a() {
        return this.f251a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m177a(w7 w7Var) {
        if (w7Var == null) {
            return false;
        }
        boolean m176a = m176a();
        boolean m176a2 = w7Var.m176a();
        if ((m176a || m176a2) && !(m176a && m176a2 && this.f251a.m132a(w7Var.f251a))) {
            return false;
        }
        boolean b = b();
        boolean b2 = w7Var.b();
        if ((b || b2) && !(b && b2 && this.f252a.equals(w7Var.f252a))) {
            return false;
        }
        boolean c2 = c();
        boolean c3 = w7Var.c();
        if ((c2 || c3) && !(c2 && c3 && this.b.equals(w7Var.b))) {
            return false;
        }
        boolean d = d();
        boolean d2 = w7Var.d();
        if ((d || d2) && !(d && d2 && this.f19296c.equals(w7Var.f19296c))) {
            return false;
        }
        boolean e2 = e();
        boolean e3 = w7Var.e();
        if ((e2 || e3) && !(e2 && e3 && this.f254a.equals(w7Var.f254a))) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = w7Var.f();
        if ((f2 || f3) && !(f2 && f3 && this.d.equals(w7Var.d))) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = w7Var.g();
        if ((g2 || g3) && !(g2 && g3 && this.f19297e.equals(w7Var.f19297e))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = w7Var.h();
        if ((h2 || h3) && !(h2 && h3 && this.f255a == w7Var.f255a)) {
            return false;
        }
        boolean i2 = i();
        boolean i3 = w7Var.i();
        if ((i2 || i3) && !(i2 && i3 && this.f256b == w7Var.f256b)) {
            return false;
        }
        boolean j2 = j();
        boolean j3 = w7Var.j();
        if (j2 || j3) {
            return j2 && j3 && this.a == w7Var.a;
        }
        return true;
    }

    public w7 b(String str) {
        this.b = str;
        return this;
    }

    @Override // com.xiaomi.push.n8
    public void b(y8 y8Var) {
        m174a();
        y8Var.t(f19288g);
        if (this.f251a != null && m176a()) {
            y8Var.q(f19289h);
            this.f251a.b(y8Var);
            y8Var.z();
        }
        if (this.f252a != null) {
            y8Var.q(f19290i);
            y8Var.u(this.f252a);
            y8Var.z();
        }
        if (this.b != null) {
            y8Var.q(f19291j);
            y8Var.u(this.b);
            y8Var.z();
        }
        if (this.f19296c != null) {
            y8Var.q(f19292k);
            y8Var.u(this.f19296c);
            y8Var.z();
        }
        if (this.f254a != null && e()) {
            y8Var.q(f19293l);
            y8Var.r(new w8((byte) 11, this.f254a.size()));
            Iterator<String> it = this.f254a.iterator();
            while (it.hasNext()) {
                y8Var.u(it.next());
            }
            y8Var.C();
            y8Var.z();
        }
        if (this.d != null && f()) {
            y8Var.q(f19294m);
            y8Var.u(this.d);
            y8Var.z();
        }
        if (this.f19297e != null && g()) {
            y8Var.q(f19295n);
            y8Var.u(this.f19297e);
            y8Var.z();
        }
        if (h()) {
            y8Var.q(o);
            y8Var.x(this.f255a);
            y8Var.z();
        }
        if (i()) {
            y8Var.q(p);
            y8Var.x(this.f256b);
            y8Var.z();
        }
        if (j()) {
            y8Var.q(q);
            y8Var.p(this.a);
            y8Var.z();
        }
        y8Var.A();
        y8Var.m();
    }

    public void b(boolean z) {
        this.f253a.set(1, z);
    }

    public boolean b() {
        return this.f252a != null;
    }

    public w7 c(String str) {
        this.f19296c = str;
        return this;
    }

    public void c(boolean z) {
        this.f253a.set(2, z);
    }

    public boolean c() {
        return this.b != null;
    }

    public w7 d(String str) {
        this.d = str;
        return this;
    }

    public boolean d() {
        return this.f19296c != null;
    }

    public w7 e(String str) {
        this.f19297e = str;
        return this;
    }

    public boolean e() {
        return this.f254a != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof w7)) {
            return m177a((w7) obj);
        }
        return false;
    }

    public boolean f() {
        return this.d != null;
    }

    public boolean g() {
        return this.f19297e != null;
    }

    public boolean h() {
        return this.f253a.get(0);
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f253a.get(1);
    }

    public boolean j() {
        return this.f253a.get(2);
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionCommand(");
        if (m176a()) {
            sb.append("target:");
            r7 r7Var = this.f251a;
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
        String str = this.f252a;
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
        String str3 = this.f19296c;
        if (str3 == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            sb.append(str3);
        }
        if (e()) {
            sb.append(", ");
            sb.append("cmdArgs:");
            List<String> list = this.f254a;
            if (list == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(list);
            }
        }
        if (f()) {
            sb.append(", ");
            sb.append("packageName:");
            String str4 = this.d;
            if (str4 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str4);
            }
        }
        if (g()) {
            sb.append(", ");
            sb.append("category:");
            String str5 = this.f19297e;
            if (str5 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str5);
            }
        }
        if (h()) {
            sb.append(", ");
            sb.append("updateCache:");
            sb.append(this.f255a);
        }
        if (i()) {
            sb.append(", ");
            sb.append("response2Client:");
            sb.append(this.f256b);
        }
        if (j()) {
            sb.append(", ");
            sb.append("createdTs:");
            sb.append(this.a);
        }
        sb.append(")");
        return sb.toString();
    }
}
