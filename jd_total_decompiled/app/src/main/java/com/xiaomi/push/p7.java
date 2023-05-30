package com.xiaomi.push;

import com.jd.dynamic.DYConstants;
import java.io.Serializable;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes11.dex */
public class p7 implements n8<p7, Object>, Serializable, Cloneable {

    /* renamed from: g */
    private static final e9 f18938g = new e9("PushMetaInfo");

    /* renamed from: h */
    private static final v8 f18939h = new v8("", (byte) 11, 1);

    /* renamed from: i */
    private static final v8 f18940i = new v8("", (byte) 10, 2);

    /* renamed from: j */
    private static final v8 f18941j = new v8("", (byte) 11, 3);

    /* renamed from: k */
    private static final v8 f18942k = new v8("", (byte) 11, 4);

    /* renamed from: l */
    private static final v8 f18943l = new v8("", (byte) 11, 5);

    /* renamed from: m */
    private static final v8 f18944m = new v8("", (byte) 8, 6);

    /* renamed from: n */
    private static final v8 f18945n = new v8("", (byte) 11, 7);
    private static final v8 o = new v8("", (byte) 8, 8);
    private static final v8 p = new v8("", (byte) 8, 9);
    private static final v8 q = new v8("", (byte) 13, 10);
    private static final v8 r = new v8("", (byte) 13, 11);
    private static final v8 s = new v8("", (byte) 2, 12);
    private static final v8 t = new v8("", (byte) 13, 13);
    public int a;

    /* renamed from: a */
    public long f192a;

    /* renamed from: a */
    public String f193a;

    /* renamed from: a */
    private BitSet f194a;

    /* renamed from: a */
    public Map<String, String> f195a;

    /* renamed from: a */
    public boolean f196a;
    public int b;

    /* renamed from: b */
    public String f197b;

    /* renamed from: b */
    public Map<String, String> f198b;

    /* renamed from: c */
    public int f18946c;

    /* renamed from: c */
    public String f199c;

    /* renamed from: c */
    public Map<String, String> f200c;
    public String d;

    /* renamed from: e */
    public String f18947e;

    public p7() {
        this.f194a = new BitSet(5);
        this.f196a = false;
    }

    public p7(p7 p7Var) {
        BitSet bitSet = new BitSet(5);
        this.f194a = bitSet;
        bitSet.clear();
        this.f194a.or(p7Var.f194a);
        if (p7Var.m123a()) {
            this.f193a = p7Var.f193a;
        }
        this.f192a = p7Var.f192a;
        if (p7Var.m129c()) {
            this.f197b = p7Var.f197b;
        }
        if (p7Var.m130d()) {
            this.f199c = p7Var.f199c;
        }
        if (p7Var.e()) {
            this.d = p7Var.d;
        }
        this.a = p7Var.a;
        if (p7Var.g()) {
            this.f18947e = p7Var.f18947e;
        }
        this.b = p7Var.b;
        this.f18946c = p7Var.f18946c;
        if (p7Var.j()) {
            HashMap hashMap = new HashMap();
            for (Map.Entry<String, String> entry : p7Var.f195a.entrySet()) {
                hashMap.put(entry.getKey(), entry.getValue());
            }
            this.f195a = hashMap;
        }
        if (p7Var.k()) {
            HashMap hashMap2 = new HashMap();
            for (Map.Entry<String, String> entry2 : p7Var.f198b.entrySet()) {
                hashMap2.put(entry2.getKey(), entry2.getValue());
            }
            this.f198b = hashMap2;
        }
        this.f196a = p7Var.f196a;
        if (p7Var.n()) {
            HashMap hashMap3 = new HashMap();
            for (Map.Entry<String, String> entry3 : p7Var.f200c.entrySet()) {
                hashMap3.put(entry3.getKey(), entry3.getValue());
            }
            this.f200c = hashMap3;
        }
    }

    public int a() {
        return this.a;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(p7 p7Var) {
        int h2;
        int k2;
        int h3;
        int h4;
        int b;
        int b2;
        int e2;
        int b3;
        int e3;
        int e4;
        int e5;
        int c2;
        int e6;
        if (getClass().equals(p7Var.getClass())) {
            int compareTo = Boolean.valueOf(m123a()).compareTo(Boolean.valueOf(p7Var.m123a()));
            if (compareTo != 0) {
                return compareTo;
            }
            if (!m123a() || (e6 = o8.e(this.f193a, p7Var.f193a)) == 0) {
                int compareTo2 = Boolean.valueOf(m127b()).compareTo(Boolean.valueOf(p7Var.m127b()));
                if (compareTo2 != 0) {
                    return compareTo2;
                }
                if (!m127b() || (c2 = o8.c(this.f192a, p7Var.f192a)) == 0) {
                    int compareTo3 = Boolean.valueOf(m129c()).compareTo(Boolean.valueOf(p7Var.m129c()));
                    if (compareTo3 != 0) {
                        return compareTo3;
                    }
                    if (!m129c() || (e5 = o8.e(this.f197b, p7Var.f197b)) == 0) {
                        int compareTo4 = Boolean.valueOf(m130d()).compareTo(Boolean.valueOf(p7Var.m130d()));
                        if (compareTo4 != 0) {
                            return compareTo4;
                        }
                        if (!m130d() || (e4 = o8.e(this.f199c, p7Var.f199c)) == 0) {
                            int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(p7Var.e()));
                            if (compareTo5 != 0) {
                                return compareTo5;
                            }
                            if (!e() || (e3 = o8.e(this.d, p7Var.d)) == 0) {
                                int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(p7Var.f()));
                                if (compareTo6 != 0) {
                                    return compareTo6;
                                }
                                if (!f() || (b3 = o8.b(this.a, p7Var.a)) == 0) {
                                    int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(p7Var.g()));
                                    if (compareTo7 != 0) {
                                        return compareTo7;
                                    }
                                    if (!g() || (e2 = o8.e(this.f18947e, p7Var.f18947e)) == 0) {
                                        int compareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(p7Var.h()));
                                        if (compareTo8 != 0) {
                                            return compareTo8;
                                        }
                                        if (!h() || (b2 = o8.b(this.b, p7Var.b)) == 0) {
                                            int compareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(p7Var.i()));
                                            if (compareTo9 != 0) {
                                                return compareTo9;
                                            }
                                            if (!i() || (b = o8.b(this.f18946c, p7Var.f18946c)) == 0) {
                                                int compareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(p7Var.j()));
                                                if (compareTo10 != 0) {
                                                    return compareTo10;
                                                }
                                                if (!j() || (h4 = o8.h(this.f195a, p7Var.f195a)) == 0) {
                                                    int compareTo11 = Boolean.valueOf(k()).compareTo(Boolean.valueOf(p7Var.k()));
                                                    if (compareTo11 != 0) {
                                                        return compareTo11;
                                                    }
                                                    if (!k() || (h3 = o8.h(this.f198b, p7Var.f198b)) == 0) {
                                                        int compareTo12 = Boolean.valueOf(m()).compareTo(Boolean.valueOf(p7Var.m()));
                                                        if (compareTo12 != 0) {
                                                            return compareTo12;
                                                        }
                                                        if (!m() || (k2 = o8.k(this.f196a, p7Var.f196a)) == 0) {
                                                            int compareTo13 = Boolean.valueOf(n()).compareTo(Boolean.valueOf(p7Var.n()));
                                                            if (compareTo13 != 0) {
                                                                return compareTo13;
                                                            }
                                                            if (!n() || (h2 = o8.h(this.f200c, p7Var.f200c)) == 0) {
                                                                return 0;
                                                            }
                                                            return h2;
                                                        }
                                                        return k2;
                                                    }
                                                    return h3;
                                                }
                                                return h4;
                                            }
                                            return b;
                                        }
                                        return b2;
                                    }
                                    return e2;
                                }
                                return b3;
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
        return getClass().getName().compareTo(p7Var.getClass().getName());
    }

    /* renamed from: a */
    public long m118a() {
        return this.f192a;
    }

    /* renamed from: a */
    public p7 m119a() {
        return new p7(this);
    }

    public p7 a(int i2) {
        this.a = i2;
        b(true);
        return this;
    }

    public p7 a(String str) {
        this.f193a = str;
        return this;
    }

    public p7 a(Map<String, String> map) {
        this.f195a = map;
        return this;
    }

    /* renamed from: a */
    public String m120a() {
        return this.f193a;
    }

    /* renamed from: a */
    public Map<String, String> m121a() {
        return this.f195a;
    }

    /* renamed from: a */
    public void m122a() {
        if (this.f193a != null) {
            return;
        }
        throw new z8("Required field 'id' was not present! Struct: " + toString());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.xiaomi.push.n8
    public void a(y8 y8Var) {
        y8Var.i();
        while (true) {
            v8 e2 = y8Var.e();
            byte b = e2.b;
            if (b == 0) {
                y8Var.D();
                if (m127b()) {
                    m122a();
                    return;
                }
                throw new z8("Required field 'messageTs' was not found in serialized data! Struct: " + toString());
            }
            int i2 = 0;
            switch (e2.f19282c) {
                case 1:
                    if (b == 11) {
                        this.f193a = y8Var.j();
                        break;
                    }
                    c9.a(y8Var, b);
                    break;
                case 2:
                    if (b == 10) {
                        this.f192a = y8Var.d();
                        a(true);
                        break;
                    }
                    c9.a(y8Var, b);
                    break;
                case 3:
                    if (b == 11) {
                        this.f197b = y8Var.j();
                        break;
                    }
                    c9.a(y8Var, b);
                    break;
                case 4:
                    if (b == 11) {
                        this.f199c = y8Var.j();
                        break;
                    }
                    c9.a(y8Var, b);
                    break;
                case 5:
                    if (b == 11) {
                        this.d = y8Var.j();
                        break;
                    }
                    c9.a(y8Var, b);
                    break;
                case 6:
                    if (b == 8) {
                        this.a = y8Var.c();
                        b(true);
                        break;
                    }
                    c9.a(y8Var, b);
                    break;
                case 7:
                    if (b == 11) {
                        this.f18947e = y8Var.j();
                        break;
                    }
                    c9.a(y8Var, b);
                    break;
                case 8:
                    if (b == 8) {
                        this.b = y8Var.c();
                        c(true);
                        break;
                    }
                    c9.a(y8Var, b);
                    break;
                case 9:
                    if (b == 8) {
                        this.f18946c = y8Var.c();
                        d(true);
                        break;
                    }
                    c9.a(y8Var, b);
                    break;
                case 10:
                    if (b == 13) {
                        x8 g2 = y8Var.g();
                        this.f195a = new HashMap(g2.f19321c * 2);
                        while (i2 < g2.f19321c) {
                            this.f195a.put(y8Var.j(), y8Var.j());
                            i2++;
                        }
                        y8Var.F();
                        break;
                    }
                    c9.a(y8Var, b);
                    break;
                case 11:
                    if (b == 13) {
                        x8 g3 = y8Var.g();
                        this.f198b = new HashMap(g3.f19321c * 2);
                        while (i2 < g3.f19321c) {
                            this.f198b.put(y8Var.j(), y8Var.j());
                            i2++;
                        }
                        y8Var.F();
                        break;
                    }
                    c9.a(y8Var, b);
                    break;
                case 12:
                    if (b == 2) {
                        this.f196a = y8Var.y();
                        e(true);
                        break;
                    }
                    c9.a(y8Var, b);
                    break;
                case 13:
                    if (b == 13) {
                        x8 g4 = y8Var.g();
                        this.f200c = new HashMap(g4.f19321c * 2);
                        while (i2 < g4.f19321c) {
                            this.f200c.put(y8Var.j(), y8Var.j());
                            i2++;
                        }
                        y8Var.F();
                        break;
                    }
                    c9.a(y8Var, b);
                    break;
                default:
                    c9.a(y8Var, b);
                    break;
            }
            y8Var.E();
        }
    }

    public void a(String str, String str2) {
        if (this.f195a == null) {
            this.f195a = new HashMap();
        }
        this.f195a.put(str, str2);
    }

    public void a(boolean z) {
        this.f194a.set(0, z);
    }

    /* renamed from: a */
    public boolean m123a() {
        return this.f193a != null;
    }

    /* renamed from: a */
    public boolean m124a(p7 p7Var) {
        if (p7Var == null) {
            return false;
        }
        boolean m123a = m123a();
        boolean m123a2 = p7Var.m123a();
        if (((m123a || m123a2) && !(m123a && m123a2 && this.f193a.equals(p7Var.f193a))) || this.f192a != p7Var.f192a) {
            return false;
        }
        boolean m129c = m129c();
        boolean m129c2 = p7Var.m129c();
        if ((m129c || m129c2) && !(m129c && m129c2 && this.f197b.equals(p7Var.f197b))) {
            return false;
        }
        boolean m130d = m130d();
        boolean m130d2 = p7Var.m130d();
        if ((m130d || m130d2) && !(m130d && m130d2 && this.f199c.equals(p7Var.f199c))) {
            return false;
        }
        boolean e2 = e();
        boolean e3 = p7Var.e();
        if ((e2 || e3) && !(e2 && e3 && this.d.equals(p7Var.d))) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = p7Var.f();
        if ((f2 || f3) && !(f2 && f3 && this.a == p7Var.a)) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = p7Var.g();
        if ((g2 || g3) && !(g2 && g3 && this.f18947e.equals(p7Var.f18947e))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = p7Var.h();
        if ((h2 || h3) && !(h2 && h3 && this.b == p7Var.b)) {
            return false;
        }
        boolean i2 = i();
        boolean i3 = p7Var.i();
        if ((i2 || i3) && !(i2 && i3 && this.f18946c == p7Var.f18946c)) {
            return false;
        }
        boolean j2 = j();
        boolean j3 = p7Var.j();
        if ((j2 || j3) && !(j2 && j3 && this.f195a.equals(p7Var.f195a))) {
            return false;
        }
        boolean k2 = k();
        boolean k3 = p7Var.k();
        if ((k2 || k3) && !(k2 && k3 && this.f198b.equals(p7Var.f198b))) {
            return false;
        }
        boolean m2 = m();
        boolean m3 = p7Var.m();
        if ((m2 || m3) && !(m2 && m3 && this.f196a == p7Var.f196a)) {
            return false;
        }
        boolean n2 = n();
        boolean n3 = p7Var.n();
        if (n2 || n3) {
            return n2 && n3 && this.f200c.equals(p7Var.f200c);
        }
        return true;
    }

    public int b() {
        return this.b;
    }

    public p7 b(int i2) {
        this.b = i2;
        c(true);
        return this;
    }

    public p7 b(String str) {
        this.f197b = str;
        return this;
    }

    /* renamed from: b */
    public String m125b() {
        return this.f197b;
    }

    /* renamed from: b */
    public Map<String, String> m126b() {
        return this.f198b;
    }

    @Override // com.xiaomi.push.n8
    public void b(y8 y8Var) {
        m122a();
        y8Var.t(f18938g);
        if (this.f193a != null) {
            y8Var.q(f18939h);
            y8Var.u(this.f193a);
            y8Var.z();
        }
        y8Var.q(f18940i);
        y8Var.p(this.f192a);
        y8Var.z();
        if (this.f197b != null && m129c()) {
            y8Var.q(f18941j);
            y8Var.u(this.f197b);
            y8Var.z();
        }
        if (this.f199c != null && m130d()) {
            y8Var.q(f18942k);
            y8Var.u(this.f199c);
            y8Var.z();
        }
        if (this.d != null && e()) {
            y8Var.q(f18943l);
            y8Var.u(this.d);
            y8Var.z();
        }
        if (f()) {
            y8Var.q(f18944m);
            y8Var.o(this.a);
            y8Var.z();
        }
        if (this.f18947e != null && g()) {
            y8Var.q(f18945n);
            y8Var.u(this.f18947e);
            y8Var.z();
        }
        if (h()) {
            y8Var.q(o);
            y8Var.o(this.b);
            y8Var.z();
        }
        if (i()) {
            y8Var.q(p);
            y8Var.o(this.f18946c);
            y8Var.z();
        }
        if (this.f195a != null && j()) {
            y8Var.q(q);
            y8Var.s(new x8((byte) 11, (byte) 11, this.f195a.size()));
            for (Map.Entry<String, String> entry : this.f195a.entrySet()) {
                y8Var.u(entry.getKey());
                y8Var.u(entry.getValue());
            }
            y8Var.B();
            y8Var.z();
        }
        if (this.f198b != null && k()) {
            y8Var.q(r);
            y8Var.s(new x8((byte) 11, (byte) 11, this.f198b.size()));
            for (Map.Entry<String, String> entry2 : this.f198b.entrySet()) {
                y8Var.u(entry2.getKey());
                y8Var.u(entry2.getValue());
            }
            y8Var.B();
            y8Var.z();
        }
        if (m()) {
            y8Var.q(s);
            y8Var.x(this.f196a);
            y8Var.z();
        }
        if (this.f200c != null && n()) {
            y8Var.q(t);
            y8Var.s(new x8((byte) 11, (byte) 11, this.f200c.size()));
            for (Map.Entry<String, String> entry3 : this.f200c.entrySet()) {
                y8Var.u(entry3.getKey());
                y8Var.u(entry3.getValue());
            }
            y8Var.B();
            y8Var.z();
        }
        y8Var.A();
        y8Var.m();
    }

    public void b(String str, String str2) {
        if (this.f198b == null) {
            this.f198b = new HashMap();
        }
        this.f198b.put(str, str2);
    }

    public void b(boolean z) {
        this.f194a.set(1, z);
    }

    /* renamed from: b */
    public boolean m127b() {
        return this.f194a.get(0);
    }

    public int c() {
        return this.f18946c;
    }

    public p7 c(int i2) {
        this.f18946c = i2;
        d(true);
        return this;
    }

    public p7 c(String str) {
        this.f199c = str;
        return this;
    }

    /* renamed from: c */
    public String m128c() {
        return this.f199c;
    }

    public void c(boolean z) {
        this.f194a.set(2, z);
    }

    /* renamed from: c */
    public boolean m129c() {
        return this.f197b != null;
    }

    public p7 d(String str) {
        this.d = str;
        return this;
    }

    public String d() {
        return this.d;
    }

    public void d(boolean z) {
        this.f194a.set(3, z);
    }

    /* renamed from: d */
    public boolean m130d() {
        return this.f199c != null;
    }

    public void e(boolean z) {
        this.f194a.set(4, z);
    }

    public boolean e() {
        return this.d != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof p7)) {
            return m124a((p7) obj);
        }
        return false;
    }

    public boolean f() {
        return this.f194a.get(1);
    }

    public boolean g() {
        return this.f18947e != null;
    }

    public boolean h() {
        return this.f194a.get(2);
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f194a.get(3);
    }

    public boolean j() {
        return this.f195a != null;
    }

    public boolean k() {
        return this.f198b != null;
    }

    public boolean l() {
        return this.f196a;
    }

    public boolean m() {
        return this.f194a.get(4);
    }

    public boolean n() {
        return this.f200c != null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("PushMetaInfo(");
        sb.append("id:");
        String str = this.f193a;
        if (str == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            sb.append(com.xiaomi.push.service.f0.b(str));
        }
        sb.append(", ");
        sb.append("messageTs:");
        sb.append(this.f192a);
        if (m129c()) {
            sb.append(", ");
            sb.append("topic:");
            String str2 = this.f197b;
            if (str2 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str2);
            }
        }
        if (m130d()) {
            sb.append(", ");
            sb.append("title:");
            String str3 = this.f199c;
            if (str3 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str3);
            }
        }
        if (e()) {
            sb.append(", ");
            sb.append("description:");
            String str4 = this.d;
            if (str4 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str4);
            }
        }
        if (f()) {
            sb.append(", ");
            sb.append("notifyType:");
            sb.append(this.a);
        }
        if (g()) {
            sb.append(", ");
            sb.append("url:");
            String str5 = this.f18947e;
            if (str5 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str5);
            }
        }
        if (h()) {
            sb.append(", ");
            sb.append("passThrough:");
            sb.append(this.b);
        }
        if (i()) {
            sb.append(", ");
            sb.append("notifyId:");
            sb.append(this.f18946c);
        }
        if (j()) {
            sb.append(", ");
            sb.append("extra:");
            Map<String, String> map = this.f195a;
            if (map == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(map);
            }
        }
        if (k()) {
            sb.append(", ");
            sb.append("internal:");
            Map<String, String> map2 = this.f198b;
            if (map2 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(map2);
            }
        }
        if (m()) {
            sb.append(", ");
            sb.append("ignoreRegInfo:");
            sb.append(this.f196a);
        }
        if (n()) {
            sb.append(", ");
            sb.append("apsProperFields:");
            Map<String, String> map3 = this.f200c;
            if (map3 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(map3);
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
