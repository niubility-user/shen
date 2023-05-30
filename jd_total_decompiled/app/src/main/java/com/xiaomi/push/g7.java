package com.xiaomi.push;

import com.jd.dynamic.DYConstants;
import java.io.Serializable;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes11.dex */
public class g7 implements n8<g7, Object>, Serializable, Cloneable {

    /* renamed from: h  reason: collision with root package name */
    private static final e9 f18659h = new e9("ClientUploadDataItem");

    /* renamed from: i  reason: collision with root package name */
    private static final v8 f18660i = new v8("", (byte) 11, 1);

    /* renamed from: j  reason: collision with root package name */
    private static final v8 f18661j = new v8("", (byte) 11, 2);

    /* renamed from: k  reason: collision with root package name */
    private static final v8 f18662k = new v8("", (byte) 11, 3);

    /* renamed from: l  reason: collision with root package name */
    private static final v8 f18663l = new v8("", (byte) 10, 4);

    /* renamed from: m  reason: collision with root package name */
    private static final v8 f18664m = new v8("", (byte) 10, 5);

    /* renamed from: n  reason: collision with root package name */
    private static final v8 f18665n = new v8("", (byte) 2, 6);
    private static final v8 o = new v8("", (byte) 11, 7);
    private static final v8 p = new v8("", (byte) 11, 8);
    private static final v8 q = new v8("", (byte) 11, 9);
    private static final v8 r = new v8("", (byte) 13, 10);
    private static final v8 s = new v8("", (byte) 11, 11);
    public long a;

    /* renamed from: a  reason: collision with other field name */
    public String f142a;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f143a = new BitSet(3);

    /* renamed from: a  reason: collision with other field name */
    public Map<String, String> f144a;

    /* renamed from: a  reason: collision with other field name */
    public boolean f145a;
    public long b;

    /* renamed from: b  reason: collision with other field name */
    public String f146b;

    /* renamed from: c  reason: collision with root package name */
    public String f18666c;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public String f18667e;

    /* renamed from: f  reason: collision with root package name */
    public String f18668f;

    /* renamed from: g  reason: collision with root package name */
    public String f18669g;

    @Override // java.lang.Comparable
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public int compareTo(g7 g7Var) {
        int e2;
        int h2;
        int e3;
        int e4;
        int e5;
        int k2;
        int c2;
        int c3;
        int e6;
        int e7;
        int e8;
        if (getClass().equals(g7Var.getClass())) {
            int compareTo = Boolean.valueOf(m64a()).compareTo(Boolean.valueOf(g7Var.m64a()));
            if (compareTo != 0) {
                return compareTo;
            }
            if (!m64a() || (e8 = o8.e(this.f142a, g7Var.f142a)) == 0) {
                int compareTo2 = Boolean.valueOf(m66b()).compareTo(Boolean.valueOf(g7Var.m66b()));
                if (compareTo2 != 0) {
                    return compareTo2;
                }
                if (!m66b() || (e7 = o8.e(this.f146b, g7Var.f146b)) == 0) {
                    int compareTo3 = Boolean.valueOf(m67c()).compareTo(Boolean.valueOf(g7Var.m67c()));
                    if (compareTo3 != 0) {
                        return compareTo3;
                    }
                    if (!m67c() || (e6 = o8.e(this.f18666c, g7Var.f18666c)) == 0) {
                        int compareTo4 = Boolean.valueOf(m68d()).compareTo(Boolean.valueOf(g7Var.m68d()));
                        if (compareTo4 != 0) {
                            return compareTo4;
                        }
                        if (!m68d() || (c3 = o8.c(this.a, g7Var.a)) == 0) {
                            int compareTo5 = Boolean.valueOf(m69e()).compareTo(Boolean.valueOf(g7Var.m69e()));
                            if (compareTo5 != 0) {
                                return compareTo5;
                            }
                            if (!m69e() || (c2 = o8.c(this.b, g7Var.b)) == 0) {
                                int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(g7Var.f()));
                                if (compareTo6 != 0) {
                                    return compareTo6;
                                }
                                if (!f() || (k2 = o8.k(this.f145a, g7Var.f145a)) == 0) {
                                    int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(g7Var.g()));
                                    if (compareTo7 != 0) {
                                        return compareTo7;
                                    }
                                    if (!g() || (e5 = o8.e(this.d, g7Var.d)) == 0) {
                                        int compareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(g7Var.h()));
                                        if (compareTo8 != 0) {
                                            return compareTo8;
                                        }
                                        if (!h() || (e4 = o8.e(this.f18667e, g7Var.f18667e)) == 0) {
                                            int compareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(g7Var.i()));
                                            if (compareTo9 != 0) {
                                                return compareTo9;
                                            }
                                            if (!i() || (e3 = o8.e(this.f18668f, g7Var.f18668f)) == 0) {
                                                int compareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(g7Var.j()));
                                                if (compareTo10 != 0) {
                                                    return compareTo10;
                                                }
                                                if (!j() || (h2 = o8.h(this.f144a, g7Var.f144a)) == 0) {
                                                    int compareTo11 = Boolean.valueOf(k()).compareTo(Boolean.valueOf(g7Var.k()));
                                                    if (compareTo11 != 0) {
                                                        return compareTo11;
                                                    }
                                                    if (!k() || (e2 = o8.e(this.f18669g, g7Var.f18669g)) == 0) {
                                                        return 0;
                                                    }
                                                    return e2;
                                                }
                                                return h2;
                                            }
                                            return e3;
                                        }
                                        return e4;
                                    }
                                    return e5;
                                }
                                return k2;
                            }
                            return c2;
                        }
                        return c3;
                    }
                    return e6;
                }
                return e7;
            }
            return e8;
        }
        return getClass().getName().compareTo(g7Var.getClass().getName());
    }

    public long a() {
        return this.b;
    }

    public g7 a(long j2) {
        this.a = j2;
        m63a(true);
        return this;
    }

    public g7 a(String str) {
        this.f142a = str;
        return this;
    }

    public g7 a(Map<String, String> map) {
        this.f144a = map;
        return this;
    }

    public g7 a(boolean z) {
        this.f145a = z;
        c(true);
        return this;
    }

    /* renamed from: a  reason: collision with other method in class */
    public String m60a() {
        return this.f142a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public Map<String, String> m61a() {
        return this.f144a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m62a() {
    }

    @Override // com.xiaomi.push.n8
    public void a(y8 y8Var) {
        y8Var.i();
        while (true) {
            v8 e2 = y8Var.e();
            byte b = e2.b;
            if (b == 0) {
                y8Var.D();
                m62a();
                return;
            }
            switch (e2.f19282c) {
                case 1:
                    if (b == 11) {
                        this.f142a = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 2:
                    if (b == 11) {
                        this.f146b = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 3:
                    if (b == 11) {
                        this.f18666c = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 4:
                    if (b == 10) {
                        this.a = y8Var.d();
                        m63a(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 5:
                    if (b == 10) {
                        this.b = y8Var.d();
                        b(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 6:
                    if (b == 2) {
                        this.f145a = y8Var.y();
                        c(true);
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
                        this.f18667e = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 9:
                    if (b == 11) {
                        this.f18668f = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 10:
                    if (b == 13) {
                        x8 g2 = y8Var.g();
                        this.f144a = new HashMap(g2.f19321c * 2);
                        for (int i2 = 0; i2 < g2.f19321c; i2++) {
                            this.f144a.put(y8Var.j(), y8Var.j());
                        }
                        y8Var.F();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 11:
                    if (b == 11) {
                        this.f18669g = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
            }
            c9.a(y8Var, b);
            y8Var.E();
        }
    }

    public void a(String str, String str2) {
        if (this.f144a == null) {
            this.f144a = new HashMap();
        }
        this.f144a.put(str, str2);
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m63a(boolean z) {
        this.f143a.set(0, z);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m64a() {
        return this.f142a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m65a(g7 g7Var) {
        if (g7Var == null) {
            return false;
        }
        boolean m64a = m64a();
        boolean m64a2 = g7Var.m64a();
        if ((m64a || m64a2) && !(m64a && m64a2 && this.f142a.equals(g7Var.f142a))) {
            return false;
        }
        boolean m66b = m66b();
        boolean m66b2 = g7Var.m66b();
        if ((m66b || m66b2) && !(m66b && m66b2 && this.f146b.equals(g7Var.f146b))) {
            return false;
        }
        boolean m67c = m67c();
        boolean m67c2 = g7Var.m67c();
        if ((m67c || m67c2) && !(m67c && m67c2 && this.f18666c.equals(g7Var.f18666c))) {
            return false;
        }
        boolean m68d = m68d();
        boolean m68d2 = g7Var.m68d();
        if ((m68d || m68d2) && !(m68d && m68d2 && this.a == g7Var.a)) {
            return false;
        }
        boolean m69e = m69e();
        boolean m69e2 = g7Var.m69e();
        if ((m69e || m69e2) && !(m69e && m69e2 && this.b == g7Var.b)) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = g7Var.f();
        if ((f2 || f3) && !(f2 && f3 && this.f145a == g7Var.f145a)) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = g7Var.g();
        if ((g2 || g3) && !(g2 && g3 && this.d.equals(g7Var.d))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = g7Var.h();
        if ((h2 || h3) && !(h2 && h3 && this.f18667e.equals(g7Var.f18667e))) {
            return false;
        }
        boolean i2 = i();
        boolean i3 = g7Var.i();
        if ((i2 || i3) && !(i2 && i3 && this.f18668f.equals(g7Var.f18668f))) {
            return false;
        }
        boolean j2 = j();
        boolean j3 = g7Var.j();
        if ((j2 || j3) && !(j2 && j3 && this.f144a.equals(g7Var.f144a))) {
            return false;
        }
        boolean k2 = k();
        boolean k3 = g7Var.k();
        if (k2 || k3) {
            return k2 && k3 && this.f18669g.equals(g7Var.f18669g);
        }
        return true;
    }

    public g7 b(long j2) {
        this.b = j2;
        b(true);
        return this;
    }

    public g7 b(String str) {
        this.f146b = str;
        return this;
    }

    public String b() {
        return this.f18666c;
    }

    @Override // com.xiaomi.push.n8
    public void b(y8 y8Var) {
        m62a();
        y8Var.t(f18659h);
        if (this.f142a != null && m64a()) {
            y8Var.q(f18660i);
            y8Var.u(this.f142a);
            y8Var.z();
        }
        if (this.f146b != null && m66b()) {
            y8Var.q(f18661j);
            y8Var.u(this.f146b);
            y8Var.z();
        }
        if (this.f18666c != null && m67c()) {
            y8Var.q(f18662k);
            y8Var.u(this.f18666c);
            y8Var.z();
        }
        if (m68d()) {
            y8Var.q(f18663l);
            y8Var.p(this.a);
            y8Var.z();
        }
        if (m69e()) {
            y8Var.q(f18664m);
            y8Var.p(this.b);
            y8Var.z();
        }
        if (f()) {
            y8Var.q(f18665n);
            y8Var.x(this.f145a);
            y8Var.z();
        }
        if (this.d != null && g()) {
            y8Var.q(o);
            y8Var.u(this.d);
            y8Var.z();
        }
        if (this.f18667e != null && h()) {
            y8Var.q(p);
            y8Var.u(this.f18667e);
            y8Var.z();
        }
        if (this.f18668f != null && i()) {
            y8Var.q(q);
            y8Var.u(this.f18668f);
            y8Var.z();
        }
        if (this.f144a != null && j()) {
            y8Var.q(r);
            y8Var.s(new x8((byte) 11, (byte) 11, this.f144a.size()));
            for (Map.Entry<String, String> entry : this.f144a.entrySet()) {
                y8Var.u(entry.getKey());
                y8Var.u(entry.getValue());
            }
            y8Var.B();
            y8Var.z();
        }
        if (this.f18669g != null && k()) {
            y8Var.q(s);
            y8Var.u(this.f18669g);
            y8Var.z();
        }
        y8Var.A();
        y8Var.m();
    }

    public void b(boolean z) {
        this.f143a.set(1, z);
    }

    /* renamed from: b  reason: collision with other method in class */
    public boolean m66b() {
        return this.f146b != null;
    }

    public g7 c(String str) {
        this.f18666c = str;
        return this;
    }

    public String c() {
        return this.f18667e;
    }

    public void c(boolean z) {
        this.f143a.set(2, z);
    }

    /* renamed from: c  reason: collision with other method in class */
    public boolean m67c() {
        return this.f18666c != null;
    }

    public g7 d(String str) {
        this.d = str;
        return this;
    }

    public String d() {
        return this.f18668f;
    }

    /* renamed from: d  reason: collision with other method in class */
    public boolean m68d() {
        return this.f143a.get(0);
    }

    public g7 e(String str) {
        this.f18667e = str;
        return this;
    }

    public String e() {
        return this.f18669g;
    }

    /* renamed from: e  reason: collision with other method in class */
    public boolean m69e() {
        return this.f143a.get(1);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof g7)) {
            return m65a((g7) obj);
        }
        return false;
    }

    public g7 f(String str) {
        this.f18668f = str;
        return this;
    }

    public boolean f() {
        return this.f143a.get(2);
    }

    public g7 g(String str) {
        this.f18669g = str;
        return this;
    }

    public boolean g() {
        return this.d != null;
    }

    public boolean h() {
        return this.f18667e != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f18668f != null;
    }

    public boolean j() {
        return this.f144a != null;
    }

    public boolean k() {
        return this.f18669g != null;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("ClientUploadDataItem(");
        boolean z2 = false;
        if (m64a()) {
            sb.append("channel:");
            String str = this.f142a;
            if (str == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str);
            }
            z = false;
        } else {
            z = true;
        }
        if (m66b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("data:");
            String str2 = this.f146b;
            if (str2 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str2);
            }
            z = false;
        }
        if (m67c()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("name:");
            String str3 = this.f18666c;
            if (str3 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str3);
            }
            z = false;
        }
        if (m68d()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("counter:");
            sb.append(this.a);
            z = false;
        }
        if (m69e()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("timestamp:");
            sb.append(this.b);
            z = false;
        }
        if (f()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("fromSdk:");
            sb.append(this.f145a);
            z = false;
        }
        if (g()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("category:");
            String str4 = this.d;
            if (str4 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str4);
            }
            z = false;
        }
        if (h()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("sourcePackage:");
            String str5 = this.f18667e;
            if (str5 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str5);
            }
            z = false;
        }
        if (i()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("id:");
            String str6 = this.f18668f;
            if (str6 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str6);
            }
            z = false;
        }
        if (j()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("extra:");
            Map<String, String> map = this.f144a;
            if (map == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(map);
            }
        } else {
            z2 = z;
        }
        if (k()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("pkgName:");
            String str7 = this.f18669g;
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
