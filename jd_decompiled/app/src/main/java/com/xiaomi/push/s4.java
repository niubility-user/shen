package com.xiaomi.push;

import com.jd.dynamic.DYConstants;
import java.io.Serializable;
import java.util.BitSet;

/* loaded from: classes11.dex */
public class s4 implements n8<s4, Object>, Serializable, Cloneable {

    /* renamed from: g */
    private static final e9 f19005g = new e9("StatsEvent");

    /* renamed from: h */
    private static final v8 f19006h = new v8("", (byte) 3, 1);

    /* renamed from: i */
    private static final v8 f19007i = new v8("", (byte) 8, 2);

    /* renamed from: j */
    private static final v8 f19008j = new v8("", (byte) 8, 3);

    /* renamed from: k */
    private static final v8 f19009k = new v8("", (byte) 11, 4);

    /* renamed from: l */
    private static final v8 f19010l = new v8("", (byte) 11, 5);

    /* renamed from: m */
    private static final v8 f19011m = new v8("", (byte) 8, 6);

    /* renamed from: n */
    private static final v8 f19012n = new v8("", (byte) 11, 7);
    private static final v8 o = new v8("", (byte) 11, 8);
    private static final v8 p = new v8("", (byte) 8, 9);
    private static final v8 q = new v8("", (byte) 8, 10);
    public byte a;

    /* renamed from: a */
    public int f206a;

    /* renamed from: a */
    public String f207a;

    /* renamed from: a */
    private BitSet f208a = new BitSet(6);
    public int b;

    /* renamed from: b */
    public String f209b;

    /* renamed from: c */
    public int f19013c;

    /* renamed from: c */
    public String f210c;
    public int d;

    /* renamed from: d */
    public String f211d;

    /* renamed from: e */
    public int f19014e;

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(s4 s4Var) {
        int b;
        int b2;
        int e2;
        int e3;
        int b3;
        int e4;
        int e5;
        int b4;
        int b5;
        int a;
        if (getClass().equals(s4Var.getClass())) {
            int compareTo = Boolean.valueOf(m133a()).compareTo(Boolean.valueOf(s4Var.m133a()));
            if (compareTo != 0) {
                return compareTo;
            }
            if (!m133a() || (a = o8.a(this.a, s4Var.a)) == 0) {
                int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(s4Var.b()));
                if (compareTo2 != 0) {
                    return compareTo2;
                }
                if (!b() || (b5 = o8.b(this.f206a, s4Var.f206a)) == 0) {
                    int compareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(s4Var.c()));
                    if (compareTo3 != 0) {
                        return compareTo3;
                    }
                    if (!c() || (b4 = o8.b(this.b, s4Var.b)) == 0) {
                        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(s4Var.d()));
                        if (compareTo4 != 0) {
                            return compareTo4;
                        }
                        if (!d() || (e5 = o8.e(this.f207a, s4Var.f207a)) == 0) {
                            int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(s4Var.e()));
                            if (compareTo5 != 0) {
                                return compareTo5;
                            }
                            if (!e() || (e4 = o8.e(this.f209b, s4Var.f209b)) == 0) {
                                int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(s4Var.f()));
                                if (compareTo6 != 0) {
                                    return compareTo6;
                                }
                                if (!f() || (b3 = o8.b(this.f19013c, s4Var.f19013c)) == 0) {
                                    int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(s4Var.g()));
                                    if (compareTo7 != 0) {
                                        return compareTo7;
                                    }
                                    if (!g() || (e3 = o8.e(this.f210c, s4Var.f210c)) == 0) {
                                        int compareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(s4Var.h()));
                                        if (compareTo8 != 0) {
                                            return compareTo8;
                                        }
                                        if (!h() || (e2 = o8.e(this.f211d, s4Var.f211d)) == 0) {
                                            int compareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(s4Var.i()));
                                            if (compareTo9 != 0) {
                                                return compareTo9;
                                            }
                                            if (!i() || (b2 = o8.b(this.d, s4Var.d)) == 0) {
                                                int compareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(s4Var.j()));
                                                if (compareTo10 != 0) {
                                                    return compareTo10;
                                                }
                                                if (!j() || (b = o8.b(this.f19014e, s4Var.f19014e)) == 0) {
                                                    return 0;
                                                }
                                                return b;
                                            }
                                            return b2;
                                        }
                                        return e2;
                                    }
                                    return e3;
                                }
                                return b3;
                            }
                            return e4;
                        }
                        return e5;
                    }
                    return b4;
                }
                return b5;
            }
            return a;
        }
        return getClass().getName().compareTo(s4Var.getClass().getName());
    }

    public s4 a(byte b) {
        this.a = b;
        a(true);
        return this;
    }

    public s4 a(int i2) {
        this.f206a = i2;
        b(true);
        return this;
    }

    public s4 a(String str) {
        this.f207a = str;
        return this;
    }

    public void a() {
        if (this.f207a != null) {
            return;
        }
        throw new z8("Required field 'connpt' was not present! Struct: " + toString());
    }

    @Override // com.xiaomi.push.n8
    public void a(y8 y8Var) {
        y8Var.i();
        while (true) {
            v8 e2 = y8Var.e();
            byte b = e2.b;
            if (b == 0) {
                y8Var.D();
                if (!m133a()) {
                    throw new z8("Required field 'chid' was not found in serialized data! Struct: " + toString());
                } else if (!b()) {
                    throw new z8("Required field 'type' was not found in serialized data! Struct: " + toString());
                } else if (c()) {
                    a();
                    return;
                } else {
                    throw new z8("Required field 'value' was not found in serialized data! Struct: " + toString());
                }
            }
            switch (e2.f19282c) {
                case 1:
                    if (b == 3) {
                        this.a = y8Var.a();
                        a(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 2:
                    if (b == 8) {
                        this.f206a = y8Var.c();
                        b(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 3:
                    if (b == 8) {
                        this.b = y8Var.c();
                        c(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 4:
                    if (b == 11) {
                        this.f207a = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 5:
                    if (b == 11) {
                        this.f209b = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 6:
                    if (b == 8) {
                        this.f19013c = y8Var.c();
                        d(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 7:
                    if (b == 11) {
                        this.f210c = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 8:
                    if (b == 11) {
                        this.f211d = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 9:
                    if (b == 8) {
                        this.d = y8Var.c();
                        e(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 10:
                    if (b == 8) {
                        this.f19014e = y8Var.c();
                        f(true);
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
        this.f208a.set(0, z);
    }

    /* renamed from: a */
    public boolean m133a() {
        return this.f208a.get(0);
    }

    /* renamed from: a */
    public boolean m134a(s4 s4Var) {
        if (s4Var != null && this.a == s4Var.a && this.f206a == s4Var.f206a && this.b == s4Var.b) {
            boolean d = d();
            boolean d2 = s4Var.d();
            if ((d || d2) && !(d && d2 && this.f207a.equals(s4Var.f207a))) {
                return false;
            }
            boolean e2 = e();
            boolean e3 = s4Var.e();
            if ((e2 || e3) && !(e2 && e3 && this.f209b.equals(s4Var.f209b))) {
                return false;
            }
            boolean f2 = f();
            boolean f3 = s4Var.f();
            if ((f2 || f3) && !(f2 && f3 && this.f19013c == s4Var.f19013c)) {
                return false;
            }
            boolean g2 = g();
            boolean g3 = s4Var.g();
            if ((g2 || g3) && !(g2 && g3 && this.f210c.equals(s4Var.f210c))) {
                return false;
            }
            boolean h2 = h();
            boolean h3 = s4Var.h();
            if ((h2 || h3) && !(h2 && h3 && this.f211d.equals(s4Var.f211d))) {
                return false;
            }
            boolean i2 = i();
            boolean i3 = s4Var.i();
            if ((i2 || i3) && !(i2 && i3 && this.d == s4Var.d)) {
                return false;
            }
            boolean j2 = j();
            boolean j3 = s4Var.j();
            if (j2 || j3) {
                return j2 && j3 && this.f19014e == s4Var.f19014e;
            }
            return true;
        }
        return false;
    }

    public s4 b(int i2) {
        this.b = i2;
        c(true);
        return this;
    }

    public s4 b(String str) {
        this.f209b = str;
        return this;
    }

    @Override // com.xiaomi.push.n8
    public void b(y8 y8Var) {
        a();
        y8Var.t(f19005g);
        y8Var.q(f19006h);
        y8Var.n(this.a);
        y8Var.z();
        y8Var.q(f19007i);
        y8Var.o(this.f206a);
        y8Var.z();
        y8Var.q(f19008j);
        y8Var.o(this.b);
        y8Var.z();
        if (this.f207a != null) {
            y8Var.q(f19009k);
            y8Var.u(this.f207a);
            y8Var.z();
        }
        if (this.f209b != null && e()) {
            y8Var.q(f19010l);
            y8Var.u(this.f209b);
            y8Var.z();
        }
        if (f()) {
            y8Var.q(f19011m);
            y8Var.o(this.f19013c);
            y8Var.z();
        }
        if (this.f210c != null && g()) {
            y8Var.q(f19012n);
            y8Var.u(this.f210c);
            y8Var.z();
        }
        if (this.f211d != null && h()) {
            y8Var.q(o);
            y8Var.u(this.f211d);
            y8Var.z();
        }
        if (i()) {
            y8Var.q(p);
            y8Var.o(this.d);
            y8Var.z();
        }
        if (j()) {
            y8Var.q(q);
            y8Var.o(this.f19014e);
            y8Var.z();
        }
        y8Var.A();
        y8Var.m();
    }

    public void b(boolean z) {
        this.f208a.set(1, z);
    }

    public boolean b() {
        return this.f208a.get(1);
    }

    public s4 c(int i2) {
        this.f19013c = i2;
        d(true);
        return this;
    }

    public s4 c(String str) {
        this.f210c = str;
        return this;
    }

    public void c(boolean z) {
        this.f208a.set(2, z);
    }

    public boolean c() {
        return this.f208a.get(2);
    }

    public s4 d(int i2) {
        this.d = i2;
        e(true);
        return this;
    }

    public s4 d(String str) {
        this.f211d = str;
        return this;
    }

    public void d(boolean z) {
        this.f208a.set(3, z);
    }

    public boolean d() {
        return this.f207a != null;
    }

    public void e(boolean z) {
        this.f208a.set(4, z);
    }

    public boolean e() {
        return this.f209b != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof s4)) {
            return m134a((s4) obj);
        }
        return false;
    }

    public void f(boolean z) {
        this.f208a.set(5, z);
    }

    public boolean f() {
        return this.f208a.get(3);
    }

    public boolean g() {
        return this.f210c != null;
    }

    public boolean h() {
        return this.f211d != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f208a.get(4);
    }

    public boolean j() {
        return this.f208a.get(5);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("StatsEvent(");
        sb.append("chid:");
        sb.append((int) this.a);
        sb.append(", ");
        sb.append("type:");
        sb.append(this.f206a);
        sb.append(", ");
        sb.append("value:");
        sb.append(this.b);
        sb.append(", ");
        sb.append("connpt:");
        String str = this.f207a;
        if (str == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            sb.append(str);
        }
        if (e()) {
            sb.append(", ");
            sb.append("host:");
            String str2 = this.f209b;
            if (str2 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str2);
            }
        }
        if (f()) {
            sb.append(", ");
            sb.append("subvalue:");
            sb.append(this.f19013c);
        }
        if (g()) {
            sb.append(", ");
            sb.append("annotation:");
            String str3 = this.f210c;
            if (str3 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str3);
            }
        }
        if (h()) {
            sb.append(", ");
            sb.append("user:");
            String str4 = this.f211d;
            if (str4 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str4);
            }
        }
        if (i()) {
            sb.append(", ");
            sb.append("time:");
            sb.append(this.d);
        }
        if (j()) {
            sb.append(", ");
            sb.append("clientIp:");
            sb.append(this.f19014e);
        }
        sb.append(")");
        return sb.toString();
    }
}
