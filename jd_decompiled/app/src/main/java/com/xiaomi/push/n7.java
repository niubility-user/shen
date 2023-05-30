package com.xiaomi.push;

import com.jd.dynamic.DYConstants;
import java.io.Serializable;
import java.util.BitSet;

/* loaded from: classes11.dex */
public class n7 implements n8<n7, Object>, Serializable, Cloneable {

    /* renamed from: g  reason: collision with root package name */
    private static final e9 f18881g = new e9("OnlineConfigItem");

    /* renamed from: h  reason: collision with root package name */
    private static final v8 f18882h = new v8("", (byte) 8, 1);

    /* renamed from: i  reason: collision with root package name */
    private static final v8 f18883i = new v8("", (byte) 8, 2);

    /* renamed from: j  reason: collision with root package name */
    private static final v8 f18884j = new v8("", (byte) 2, 3);

    /* renamed from: k  reason: collision with root package name */
    private static final v8 f18885k = new v8("", (byte) 8, 4);

    /* renamed from: l  reason: collision with root package name */
    private static final v8 f18886l = new v8("", (byte) 10, 5);

    /* renamed from: m  reason: collision with root package name */
    private static final v8 f18887m = new v8("", (byte) 11, 6);

    /* renamed from: n  reason: collision with root package name */
    private static final v8 f18888n = new v8("", (byte) 2, 7);
    public int a;

    /* renamed from: a  reason: collision with other field name */
    public long f180a;

    /* renamed from: a  reason: collision with other field name */
    public String f181a;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f182a = new BitSet(6);

    /* renamed from: a  reason: collision with other field name */
    public boolean f183a;
    public int b;

    /* renamed from: b  reason: collision with other field name */
    public boolean f184b;

    /* renamed from: c  reason: collision with root package name */
    public int f18889c;

    public int a() {
        return this.a;
    }

    @Override // java.lang.Comparable
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public int compareTo(n7 n7Var) {
        int k2;
        int e2;
        int c2;
        int b;
        int k3;
        int b2;
        int b3;
        if (getClass().equals(n7Var.getClass())) {
            int compareTo = Boolean.valueOf(m108a()).compareTo(Boolean.valueOf(n7Var.m108a()));
            if (compareTo != 0) {
                return compareTo;
            }
            if (!m108a() || (b3 = o8.b(this.a, n7Var.a)) == 0) {
                int compareTo2 = Boolean.valueOf(m110b()).compareTo(Boolean.valueOf(n7Var.m110b()));
                if (compareTo2 != 0) {
                    return compareTo2;
                }
                if (!m110b() || (b2 = o8.b(this.b, n7Var.b)) == 0) {
                    int compareTo3 = Boolean.valueOf(m111c()).compareTo(Boolean.valueOf(n7Var.m111c()));
                    if (compareTo3 != 0) {
                        return compareTo3;
                    }
                    if (!m111c() || (k3 = o8.k(this.f183a, n7Var.f183a)) == 0) {
                        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(n7Var.d()));
                        if (compareTo4 != 0) {
                            return compareTo4;
                        }
                        if (!d() || (b = o8.b(this.f18889c, n7Var.f18889c)) == 0) {
                            int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(n7Var.e()));
                            if (compareTo5 != 0) {
                                return compareTo5;
                            }
                            if (!e() || (c2 = o8.c(this.f180a, n7Var.f180a)) == 0) {
                                int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(n7Var.f()));
                                if (compareTo6 != 0) {
                                    return compareTo6;
                                }
                                if (!f() || (e2 = o8.e(this.f181a, n7Var.f181a)) == 0) {
                                    int compareTo7 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(n7Var.h()));
                                    if (compareTo7 != 0) {
                                        return compareTo7;
                                    }
                                    if (!h() || (k2 = o8.k(this.f184b, n7Var.f184b)) == 0) {
                                        return 0;
                                    }
                                    return k2;
                                }
                                return e2;
                            }
                            return c2;
                        }
                        return b;
                    }
                    return k3;
                }
                return b2;
            }
            return b3;
        }
        return getClass().getName().compareTo(n7Var.getClass().getName());
    }

    /* renamed from: a  reason: collision with other method in class */
    public long m105a() {
        return this.f180a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public String m106a() {
        return this.f181a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m107a() {
    }

    @Override // com.xiaomi.push.n8
    public void a(y8 y8Var) {
        y8Var.i();
        while (true) {
            v8 e2 = y8Var.e();
            byte b = e2.b;
            if (b == 0) {
                y8Var.D();
                m107a();
                return;
            }
            switch (e2.f19282c) {
                case 1:
                    if (b == 8) {
                        this.a = y8Var.c();
                        a(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 2:
                    if (b == 8) {
                        this.b = y8Var.c();
                        b(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 3:
                    if (b == 2) {
                        this.f183a = y8Var.y();
                        c(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 4:
                    if (b == 8) {
                        this.f18889c = y8Var.c();
                        d(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 5:
                    if (b == 10) {
                        this.f180a = y8Var.d();
                        e(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 6:
                    if (b == 11) {
                        this.f181a = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 7:
                    if (b == 2) {
                        this.f184b = y8Var.y();
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
        this.f182a.set(0, z);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m108a() {
        return this.f182a.get(0);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m109a(n7 n7Var) {
        if (n7Var == null) {
            return false;
        }
        boolean m108a = m108a();
        boolean m108a2 = n7Var.m108a();
        if ((m108a || m108a2) && !(m108a && m108a2 && this.a == n7Var.a)) {
            return false;
        }
        boolean m110b = m110b();
        boolean m110b2 = n7Var.m110b();
        if ((m110b || m110b2) && !(m110b && m110b2 && this.b == n7Var.b)) {
            return false;
        }
        boolean m111c = m111c();
        boolean m111c2 = n7Var.m111c();
        if ((m111c || m111c2) && !(m111c && m111c2 && this.f183a == n7Var.f183a)) {
            return false;
        }
        boolean d = d();
        boolean d2 = n7Var.d();
        if ((d || d2) && !(d && d2 && this.f18889c == n7Var.f18889c)) {
            return false;
        }
        boolean e2 = e();
        boolean e3 = n7Var.e();
        if ((e2 || e3) && !(e2 && e3 && this.f180a == n7Var.f180a)) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = n7Var.f();
        if ((f2 || f3) && !(f2 && f3 && this.f181a.equals(n7Var.f181a))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = n7Var.h();
        if (h2 || h3) {
            return h2 && h3 && this.f184b == n7Var.f184b;
        }
        return true;
    }

    public int b() {
        return this.b;
    }

    @Override // com.xiaomi.push.n8
    public void b(y8 y8Var) {
        m107a();
        y8Var.t(f18881g);
        if (m108a()) {
            y8Var.q(f18882h);
            y8Var.o(this.a);
            y8Var.z();
        }
        if (m110b()) {
            y8Var.q(f18883i);
            y8Var.o(this.b);
            y8Var.z();
        }
        if (m111c()) {
            y8Var.q(f18884j);
            y8Var.x(this.f183a);
            y8Var.z();
        }
        if (d()) {
            y8Var.q(f18885k);
            y8Var.o(this.f18889c);
            y8Var.z();
        }
        if (e()) {
            y8Var.q(f18886l);
            y8Var.p(this.f180a);
            y8Var.z();
        }
        if (this.f181a != null && f()) {
            y8Var.q(f18887m);
            y8Var.u(this.f181a);
            y8Var.z();
        }
        if (h()) {
            y8Var.q(f18888n);
            y8Var.x(this.f184b);
            y8Var.z();
        }
        y8Var.A();
        y8Var.m();
    }

    public void b(boolean z) {
        this.f182a.set(1, z);
    }

    /* renamed from: b  reason: collision with other method in class */
    public boolean m110b() {
        return this.f182a.get(1);
    }

    public int c() {
        return this.f18889c;
    }

    public void c(boolean z) {
        this.f182a.set(2, z);
    }

    /* renamed from: c  reason: collision with other method in class */
    public boolean m111c() {
        return this.f182a.get(2);
    }

    public void d(boolean z) {
        this.f182a.set(3, z);
    }

    public boolean d() {
        return this.f182a.get(3);
    }

    public void e(boolean z) {
        this.f182a.set(4, z);
    }

    public boolean e() {
        return this.f182a.get(4);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof n7)) {
            return m109a((n7) obj);
        }
        return false;
    }

    public void f(boolean z) {
        this.f182a.set(5, z);
    }

    public boolean f() {
        return this.f181a != null;
    }

    public boolean g() {
        return this.f184b;
    }

    public boolean h() {
        return this.f182a.get(5);
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("OnlineConfigItem(");
        boolean z2 = false;
        if (m108a()) {
            sb.append("key:");
            sb.append(this.a);
            z = false;
        } else {
            z = true;
        }
        if (m110b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("type:");
            sb.append(this.b);
            z = false;
        }
        if (m111c()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("clear:");
            sb.append(this.f183a);
            z = false;
        }
        if (d()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("intValue:");
            sb.append(this.f18889c);
            z = false;
        }
        if (e()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("longValue:");
            sb.append(this.f180a);
            z = false;
        }
        if (f()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("stringValue:");
            String str = this.f181a;
            if (str == null) {
                str = DYConstants.DY_NULL_STR;
            }
            sb.append(str);
        } else {
            z2 = z;
        }
        if (h()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("boolValue:");
            sb.append(this.f184b);
        }
        sb.append(")");
        return sb.toString();
    }
}
