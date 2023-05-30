package com.xiaomi.push;

import com.jd.dynamic.DYConstants;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes11.dex */
public class c8 implements n8<c8, Object>, Serializable, Cloneable {

    /* renamed from: j */
    private static final e9 f18513j = new e9("XmPushActionNotification");

    /* renamed from: k */
    private static final v8 f18514k = new v8("", (byte) 11, 1);

    /* renamed from: l */
    private static final v8 f18515l = new v8("", (byte) 12, 2);

    /* renamed from: m */
    private static final v8 f18516m = new v8("", (byte) 11, 3);

    /* renamed from: n */
    private static final v8 f18517n = new v8("", (byte) 11, 4);
    private static final v8 o = new v8("", (byte) 11, 5);
    private static final v8 p = new v8("", (byte) 2, 6);
    private static final v8 q = new v8("", (byte) 11, 7);
    private static final v8 r = new v8("", (byte) 13, 8);
    private static final v8 s = new v8("", (byte) 11, 9);
    private static final v8 t = new v8("", (byte) 11, 10);
    private static final v8 u = new v8("", (byte) 11, 12);
    private static final v8 v = new v8("", (byte) 11, 13);
    private static final v8 w = new v8("", (byte) 11, 14);
    private static final v8 x = new v8("", (byte) 10, 15);
    private static final v8 y = new v8("", (byte) 2, 20);
    public long a;

    /* renamed from: a */
    public r7 f109a;

    /* renamed from: a */
    public String f110a;

    /* renamed from: a */
    public ByteBuffer f111a;

    /* renamed from: a */
    private BitSet f112a;

    /* renamed from: a */
    public Map<String, String> f113a;

    /* renamed from: a */
    public boolean f114a;
    public String b;

    /* renamed from: b */
    public boolean f115b;

    /* renamed from: c */
    public String f18518c;
    public String d;

    /* renamed from: e */
    public String f18519e;

    /* renamed from: f */
    public String f18520f;

    /* renamed from: g */
    public String f18521g;

    /* renamed from: h */
    public String f18522h;

    /* renamed from: i */
    public String f18523i;

    public c8() {
        this.f112a = new BitSet(3);
        this.f114a = true;
        this.f115b = false;
    }

    public c8(String str, boolean z) {
        this();
        this.b = str;
        this.f114a = z;
        m37a(true);
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(c8 c8Var) {
        int k2;
        int c2;
        int d;
        int e2;
        int e3;
        int e4;
        int e5;
        int h2;
        int e6;
        int k3;
        int e7;
        int e8;
        int e9;
        int d2;
        int e10;
        if (getClass().equals(c8Var.getClass())) {
            int compareTo = Boolean.valueOf(m38a()).compareTo(Boolean.valueOf(c8Var.m38a()));
            if (compareTo != 0) {
                return compareTo;
            }
            if (!m38a() || (e10 = o8.e(this.f110a, c8Var.f110a)) == 0) {
                int compareTo2 = Boolean.valueOf(m41b()).compareTo(Boolean.valueOf(c8Var.m41b()));
                if (compareTo2 != 0) {
                    return compareTo2;
                }
                if (!m41b() || (d2 = o8.d(this.f109a, c8Var.f109a)) == 0) {
                    int compareTo3 = Boolean.valueOf(m42c()).compareTo(Boolean.valueOf(c8Var.m42c()));
                    if (compareTo3 != 0) {
                        return compareTo3;
                    }
                    if (!m42c() || (e9 = o8.e(this.b, c8Var.b)) == 0) {
                        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(c8Var.d()));
                        if (compareTo4 != 0) {
                            return compareTo4;
                        }
                        if (!d() || (e8 = o8.e(this.f18518c, c8Var.f18518c)) == 0) {
                            int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(c8Var.e()));
                            if (compareTo5 != 0) {
                                return compareTo5;
                            }
                            if (!e() || (e7 = o8.e(this.d, c8Var.d)) == 0) {
                                int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(c8Var.f()));
                                if (compareTo6 != 0) {
                                    return compareTo6;
                                }
                                if (!f() || (k3 = o8.k(this.f114a, c8Var.f114a)) == 0) {
                                    int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(c8Var.g()));
                                    if (compareTo7 != 0) {
                                        return compareTo7;
                                    }
                                    if (!g() || (e6 = o8.e(this.f18519e, c8Var.f18519e)) == 0) {
                                        int compareTo8 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(c8Var.h()));
                                        if (compareTo8 != 0) {
                                            return compareTo8;
                                        }
                                        if (!h() || (h2 = o8.h(this.f113a, c8Var.f113a)) == 0) {
                                            int compareTo9 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(c8Var.i()));
                                            if (compareTo9 != 0) {
                                                return compareTo9;
                                            }
                                            if (!i() || (e5 = o8.e(this.f18520f, c8Var.f18520f)) == 0) {
                                                int compareTo10 = Boolean.valueOf(j()).compareTo(Boolean.valueOf(c8Var.j()));
                                                if (compareTo10 != 0) {
                                                    return compareTo10;
                                                }
                                                if (!j() || (e4 = o8.e(this.f18521g, c8Var.f18521g)) == 0) {
                                                    int compareTo11 = Boolean.valueOf(k()).compareTo(Boolean.valueOf(c8Var.k()));
                                                    if (compareTo11 != 0) {
                                                        return compareTo11;
                                                    }
                                                    if (!k() || (e3 = o8.e(this.f18522h, c8Var.f18522h)) == 0) {
                                                        int compareTo12 = Boolean.valueOf(l()).compareTo(Boolean.valueOf(c8Var.l()));
                                                        if (compareTo12 != 0) {
                                                            return compareTo12;
                                                        }
                                                        if (!l() || (e2 = o8.e(this.f18523i, c8Var.f18523i)) == 0) {
                                                            int compareTo13 = Boolean.valueOf(m()).compareTo(Boolean.valueOf(c8Var.m()));
                                                            if (compareTo13 != 0) {
                                                                return compareTo13;
                                                            }
                                                            if (!m() || (d = o8.d(this.f111a, c8Var.f111a)) == 0) {
                                                                int compareTo14 = Boolean.valueOf(n()).compareTo(Boolean.valueOf(c8Var.n()));
                                                                if (compareTo14 != 0) {
                                                                    return compareTo14;
                                                                }
                                                                if (!n() || (c2 = o8.c(this.a, c8Var.a)) == 0) {
                                                                    int compareTo15 = Boolean.valueOf(o()).compareTo(Boolean.valueOf(c8Var.o()));
                                                                    if (compareTo15 != 0) {
                                                                        return compareTo15;
                                                                    }
                                                                    if (!o() || (k2 = o8.k(this.f115b, c8Var.f115b)) == 0) {
                                                                        return 0;
                                                                    }
                                                                    return k2;
                                                                }
                                                                return c2;
                                                            }
                                                            return d;
                                                        }
                                                        return e2;
                                                    }
                                                    return e3;
                                                }
                                                return e4;
                                            }
                                            return e5;
                                        }
                                        return h2;
                                    }
                                    return e6;
                                }
                                return k3;
                            }
                            return e7;
                        }
                        return e8;
                    }
                    return e9;
                }
                return d2;
            }
            return e10;
        }
        return getClass().getName().compareTo(c8Var.getClass().getName());
    }

    public c8 a(String str) {
        this.b = str;
        return this;
    }

    public c8 a(ByteBuffer byteBuffer) {
        this.f111a = byteBuffer;
        return this;
    }

    public c8 a(Map<String, String> map) {
        this.f113a = map;
        return this;
    }

    public c8 a(boolean z) {
        this.f114a = z;
        m37a(true);
        return this;
    }

    public c8 a(byte[] bArr) {
        a(ByteBuffer.wrap(bArr));
        return this;
    }

    public r7 a() {
        return this.f109a;
    }

    /* renamed from: a */
    public String m34a() {
        return this.b;
    }

    /* renamed from: a */
    public Map<String, String> m35a() {
        return this.f113a;
    }

    /* renamed from: a */
    public void m36a() {
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
                if (f()) {
                    m36a();
                    return;
                }
                throw new z8("Required field 'requireAck' was not found in serialized data! Struct: " + toString());
            }
            switch (e2.f19282c) {
                case 1:
                    if (b == 11) {
                        this.f110a = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 2:
                    if (b == 12) {
                        r7 r7Var = new r7();
                        this.f109a = r7Var;
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
                        this.f18518c = y8Var.j();
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
                    if (b == 2) {
                        this.f114a = y8Var.y();
                        m37a(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 7:
                    if (b == 11) {
                        this.f18519e = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 8:
                    if (b == 13) {
                        x8 g2 = y8Var.g();
                        this.f113a = new HashMap(g2.f19321c * 2);
                        for (int i2 = 0; i2 < g2.f19321c; i2++) {
                            this.f113a.put(y8Var.j(), y8Var.j());
                        }
                        y8Var.F();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 9:
                    if (b == 11) {
                        this.f18520f = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 10:
                    if (b == 11) {
                        this.f18521g = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 12:
                    if (b == 11) {
                        this.f18522h = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 13:
                    if (b == 11) {
                        this.f18523i = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 14:
                    if (b == 11) {
                        this.f111a = y8Var.k();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 15:
                    if (b == 10) {
                        this.a = y8Var.d();
                        b(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 20:
                    if (b == 2) {
                        this.f115b = y8Var.y();
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

    public void a(String str, String str2) {
        if (this.f113a == null) {
            this.f113a = new HashMap();
        }
        this.f113a.put(str, str2);
    }

    /* renamed from: a */
    public void m37a(boolean z) {
        this.f112a.set(0, z);
    }

    /* renamed from: a */
    public boolean m38a() {
        return this.f110a != null;
    }

    /* renamed from: a */
    public boolean m39a(c8 c8Var) {
        if (c8Var == null) {
            return false;
        }
        boolean m38a = m38a();
        boolean m38a2 = c8Var.m38a();
        if ((m38a || m38a2) && !(m38a && m38a2 && this.f110a.equals(c8Var.f110a))) {
            return false;
        }
        boolean m41b = m41b();
        boolean m41b2 = c8Var.m41b();
        if ((m41b || m41b2) && !(m41b && m41b2 && this.f109a.m132a(c8Var.f109a))) {
            return false;
        }
        boolean m42c = m42c();
        boolean m42c2 = c8Var.m42c();
        if ((m42c || m42c2) && !(m42c && m42c2 && this.b.equals(c8Var.b))) {
            return false;
        }
        boolean d = d();
        boolean d2 = c8Var.d();
        if ((d || d2) && !(d && d2 && this.f18518c.equals(c8Var.f18518c))) {
            return false;
        }
        boolean e2 = e();
        boolean e3 = c8Var.e();
        if (((e2 || e3) && !(e2 && e3 && this.d.equals(c8Var.d))) || this.f114a != c8Var.f114a) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = c8Var.g();
        if ((g2 || g3) && !(g2 && g3 && this.f18519e.equals(c8Var.f18519e))) {
            return false;
        }
        boolean h2 = h();
        boolean h3 = c8Var.h();
        if ((h2 || h3) && !(h2 && h3 && this.f113a.equals(c8Var.f113a))) {
            return false;
        }
        boolean i2 = i();
        boolean i3 = c8Var.i();
        if ((i2 || i3) && !(i2 && i3 && this.f18520f.equals(c8Var.f18520f))) {
            return false;
        }
        boolean j2 = j();
        boolean j3 = c8Var.j();
        if ((j2 || j3) && !(j2 && j3 && this.f18521g.equals(c8Var.f18521g))) {
            return false;
        }
        boolean k2 = k();
        boolean k3 = c8Var.k();
        if ((k2 || k3) && !(k2 && k3 && this.f18522h.equals(c8Var.f18522h))) {
            return false;
        }
        boolean l2 = l();
        boolean l3 = c8Var.l();
        if ((l2 || l3) && !(l2 && l3 && this.f18523i.equals(c8Var.f18523i))) {
            return false;
        }
        boolean m2 = m();
        boolean m3 = c8Var.m();
        if ((m2 || m3) && !(m2 && m3 && this.f111a.equals(c8Var.f111a))) {
            return false;
        }
        boolean n2 = n();
        boolean n3 = c8Var.n();
        if ((n2 || n3) && !(n2 && n3 && this.a == c8Var.a)) {
            return false;
        }
        boolean o2 = o();
        boolean o3 = c8Var.o();
        if (o2 || o3) {
            return o2 && o3 && this.f115b == c8Var.f115b;
        }
        return true;
    }

    /* renamed from: a */
    public byte[] m40a() {
        a(o8.n(this.f111a));
        return this.f111a.array();
    }

    public c8 b(String str) {
        this.f18518c = str;
        return this;
    }

    public String b() {
        return this.f18518c;
    }

    @Override // com.xiaomi.push.n8
    public void b(y8 y8Var) {
        m36a();
        y8Var.t(f18513j);
        if (this.f110a != null && m38a()) {
            y8Var.q(f18514k);
            y8Var.u(this.f110a);
            y8Var.z();
        }
        if (this.f109a != null && m41b()) {
            y8Var.q(f18515l);
            this.f109a.b(y8Var);
            y8Var.z();
        }
        if (this.b != null) {
            y8Var.q(f18516m);
            y8Var.u(this.b);
            y8Var.z();
        }
        if (this.f18518c != null && d()) {
            y8Var.q(f18517n);
            y8Var.u(this.f18518c);
            y8Var.z();
        }
        if (this.d != null && e()) {
            y8Var.q(o);
            y8Var.u(this.d);
            y8Var.z();
        }
        y8Var.q(p);
        y8Var.x(this.f114a);
        y8Var.z();
        if (this.f18519e != null && g()) {
            y8Var.q(q);
            y8Var.u(this.f18519e);
            y8Var.z();
        }
        if (this.f113a != null && h()) {
            y8Var.q(r);
            y8Var.s(new x8((byte) 11, (byte) 11, this.f113a.size()));
            for (Map.Entry<String, String> entry : this.f113a.entrySet()) {
                y8Var.u(entry.getKey());
                y8Var.u(entry.getValue());
            }
            y8Var.B();
            y8Var.z();
        }
        if (this.f18520f != null && i()) {
            y8Var.q(s);
            y8Var.u(this.f18520f);
            y8Var.z();
        }
        if (this.f18521g != null && j()) {
            y8Var.q(t);
            y8Var.u(this.f18521g);
            y8Var.z();
        }
        if (this.f18522h != null && k()) {
            y8Var.q(u);
            y8Var.u(this.f18522h);
            y8Var.z();
        }
        if (this.f18523i != null && l()) {
            y8Var.q(v);
            y8Var.u(this.f18523i);
            y8Var.z();
        }
        if (this.f111a != null && m()) {
            y8Var.q(w);
            y8Var.v(this.f111a);
            y8Var.z();
        }
        if (n()) {
            y8Var.q(x);
            y8Var.p(this.a);
            y8Var.z();
        }
        if (o()) {
            y8Var.q(y);
            y8Var.x(this.f115b);
            y8Var.z();
        }
        y8Var.A();
        y8Var.m();
    }

    public void b(boolean z) {
        this.f112a.set(1, z);
    }

    /* renamed from: b */
    public boolean m41b() {
        return this.f109a != null;
    }

    public c8 c(String str) {
        this.d = str;
        return this;
    }

    public String c() {
        return this.f18520f;
    }

    public void c(boolean z) {
        this.f112a.set(2, z);
    }

    /* renamed from: c */
    public boolean m42c() {
        return this.b != null;
    }

    public c8 d(String str) {
        this.f18520f = str;
        return this;
    }

    public boolean d() {
        return this.f18518c != null;
    }

    public boolean e() {
        return this.d != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof c8)) {
            return m39a((c8) obj);
        }
        return false;
    }

    public boolean f() {
        return this.f112a.get(0);
    }

    public boolean g() {
        return this.f18519e != null;
    }

    public boolean h() {
        return this.f113a != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f18520f != null;
    }

    public boolean j() {
        return this.f18521g != null;
    }

    public boolean k() {
        return this.f18522h != null;
    }

    public boolean l() {
        return this.f18523i != null;
    }

    public boolean m() {
        return this.f111a != null;
    }

    public boolean n() {
        return this.f112a.get(1);
    }

    public boolean o() {
        return this.f112a.get(2);
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionNotification(");
        boolean z2 = false;
        if (m38a()) {
            sb.append("debug:");
            String str = this.f110a;
            if (str == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str);
            }
            z = false;
        } else {
            z = true;
        }
        if (m41b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            r7 r7Var = this.f109a;
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
            String str3 = this.f18518c;
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
        sb.append(", ");
        sb.append("requireAck:");
        sb.append(this.f114a);
        if (g()) {
            sb.append(", ");
            sb.append("payload:");
            String str5 = this.f18519e;
            if (str5 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str5);
            }
        }
        if (h()) {
            sb.append(", ");
            sb.append("extra:");
            Map<String, String> map = this.f113a;
            if (map == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(map);
            }
        }
        if (i()) {
            sb.append(", ");
            sb.append("packageName:");
            String str6 = this.f18520f;
            if (str6 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str6);
            }
        }
        if (j()) {
            sb.append(", ");
            sb.append("category:");
            String str7 = this.f18521g;
            if (str7 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str7);
            }
        }
        if (k()) {
            sb.append(", ");
            sb.append("regId:");
            String str8 = this.f18522h;
            if (str8 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str8);
            }
        }
        if (l()) {
            sb.append(", ");
            sb.append("aliasName:");
            String str9 = this.f18523i;
            if (str9 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str9);
            }
        }
        if (m()) {
            sb.append(", ");
            sb.append("binaryExtra:");
            ByteBuffer byteBuffer = this.f111a;
            if (byteBuffer == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                o8.o(byteBuffer, sb);
            }
        }
        if (n()) {
            sb.append(", ");
            sb.append("createdTs:");
            sb.append(this.a);
        }
        if (o()) {
            sb.append(", ");
            sb.append("alreadyLogClickInXmq:");
            sb.append(this.f115b);
        }
        sb.append(")");
        return sb.toString();
    }
}
