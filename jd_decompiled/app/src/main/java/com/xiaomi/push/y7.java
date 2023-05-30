package com.xiaomi.push;

import com.jd.dynamic.DYConstants;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;

/* loaded from: classes11.dex */
public class y7 implements n8<y7, Object>, Serializable, Cloneable {

    /* renamed from: g  reason: collision with root package name */
    private static final e9 f19325g = new e9("XmPushActionContainer");

    /* renamed from: h  reason: collision with root package name */
    private static final v8 f19326h = new v8("", (byte) 8, 1);

    /* renamed from: i  reason: collision with root package name */
    private static final v8 f19327i = new v8("", (byte) 2, 2);

    /* renamed from: j  reason: collision with root package name */
    private static final v8 f19328j = new v8("", (byte) 2, 3);

    /* renamed from: k  reason: collision with root package name */
    private static final v8 f19329k = new v8("", (byte) 11, 4);

    /* renamed from: l  reason: collision with root package name */
    private static final v8 f19330l = new v8("", (byte) 11, 5);

    /* renamed from: m  reason: collision with root package name */
    private static final v8 f19331m = new v8("", (byte) 11, 6);

    /* renamed from: n  reason: collision with root package name */
    private static final v8 f19332n = new v8("", (byte) 12, 7);
    private static final v8 o = new v8("", (byte) 12, 8);
    public c7 a;

    /* renamed from: a  reason: collision with other field name */
    public p7 f263a;

    /* renamed from: a  reason: collision with other field name */
    public r7 f264a;

    /* renamed from: a  reason: collision with other field name */
    public String f265a;

    /* renamed from: a  reason: collision with other field name */
    public ByteBuffer f266a;
    public String b;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f267a = new BitSet(2);

    /* renamed from: a  reason: collision with other field name */
    public boolean f268a = true;

    /* renamed from: b  reason: collision with other field name */
    public boolean f269b = true;

    @Override // java.lang.Comparable
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public int compareTo(y7 y7Var) {
        int d;
        int d2;
        int e2;
        int e3;
        int d3;
        int k2;
        int k3;
        int d4;
        if (getClass().equals(y7Var.getClass())) {
            int compareTo = Boolean.valueOf(m189a()).compareTo(Boolean.valueOf(y7Var.m189a()));
            if (compareTo != 0) {
                return compareTo;
            }
            if (!m189a() || (d4 = o8.d(this.a, y7Var.a)) == 0) {
                int compareTo2 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(y7Var.c()));
                if (compareTo2 != 0) {
                    return compareTo2;
                }
                if (!c() || (k3 = o8.k(this.f268a, y7Var.f268a)) == 0) {
                    int compareTo3 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(y7Var.d()));
                    if (compareTo3 != 0) {
                        return compareTo3;
                    }
                    if (!d() || (k2 = o8.k(this.f269b, y7Var.f269b)) == 0) {
                        int compareTo4 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(y7Var.e()));
                        if (compareTo4 != 0) {
                            return compareTo4;
                        }
                        if (!e() || (d3 = o8.d(this.f266a, y7Var.f266a)) == 0) {
                            int compareTo5 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(y7Var.f()));
                            if (compareTo5 != 0) {
                                return compareTo5;
                            }
                            if (!f() || (e3 = o8.e(this.f265a, y7Var.f265a)) == 0) {
                                int compareTo6 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(y7Var.g()));
                                if (compareTo6 != 0) {
                                    return compareTo6;
                                }
                                if (!g() || (e2 = o8.e(this.b, y7Var.b)) == 0) {
                                    int compareTo7 = Boolean.valueOf(h()).compareTo(Boolean.valueOf(y7Var.h()));
                                    if (compareTo7 != 0) {
                                        return compareTo7;
                                    }
                                    if (!h() || (d2 = o8.d(this.f264a, y7Var.f264a)) == 0) {
                                        int compareTo8 = Boolean.valueOf(i()).compareTo(Boolean.valueOf(y7Var.i()));
                                        if (compareTo8 != 0) {
                                            return compareTo8;
                                        }
                                        if (!i() || (d = o8.d(this.f263a, y7Var.f263a)) == 0) {
                                            return 0;
                                        }
                                        return d;
                                    }
                                    return d2;
                                }
                                return e2;
                            }
                            return e3;
                        }
                        return d3;
                    }
                    return k2;
                }
                return k3;
            }
            return d4;
        }
        return getClass().getName().compareTo(y7Var.getClass().getName());
    }

    public c7 a() {
        return this.a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public p7 m185a() {
        return this.f263a;
    }

    public y7 a(c7 c7Var) {
        this.a = c7Var;
        return this;
    }

    public y7 a(p7 p7Var) {
        this.f263a = p7Var;
        return this;
    }

    public y7 a(r7 r7Var) {
        this.f264a = r7Var;
        return this;
    }

    public y7 a(String str) {
        this.f265a = str;
        return this;
    }

    public y7 a(ByteBuffer byteBuffer) {
        this.f266a = byteBuffer;
        return this;
    }

    public y7 a(boolean z) {
        this.f268a = z;
        m188a(true);
        return this;
    }

    /* renamed from: a  reason: collision with other method in class */
    public String m186a() {
        return this.f265a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m187a() {
        if (this.a == null) {
            throw new z8("Required field 'action' was not present! Struct: " + toString());
        } else if (this.f266a == null) {
            throw new z8("Required field 'pushAction' was not present! Struct: " + toString());
        } else if (this.f264a != null) {
        } else {
            throw new z8("Required field 'target' was not present! Struct: " + toString());
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
                if (!c()) {
                    throw new z8("Required field 'encryptAction' was not found in serialized data! Struct: " + toString());
                } else if (d()) {
                    m187a();
                    return;
                } else {
                    throw new z8("Required field 'isRequest' was not found in serialized data! Struct: " + toString());
                }
            }
            switch (e2.f19282c) {
                case 1:
                    if (b == 8) {
                        this.a = c7.a(y8Var.c());
                        continue;
                        y8Var.E();
                    }
                    break;
                case 2:
                    if (b == 2) {
                        this.f268a = y8Var.y();
                        m188a(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 3:
                    if (b == 2) {
                        this.f269b = y8Var.y();
                        m192b(true);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 4:
                    if (b == 11) {
                        this.f266a = y8Var.k();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 5:
                    if (b == 11) {
                        this.f265a = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 6:
                    if (b == 11) {
                        this.b = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 7:
                    if (b == 12) {
                        r7 r7Var = new r7();
                        this.f264a = r7Var;
                        r7Var.a(y8Var);
                        continue;
                        y8Var.E();
                    }
                    break;
                case 8:
                    if (b == 12) {
                        p7 p7Var = new p7();
                        this.f263a = p7Var;
                        p7Var.a(y8Var);
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
    public void m188a(boolean z) {
        this.f267a.set(0, z);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m189a() {
        return this.a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m190a(y7 y7Var) {
        if (y7Var == null) {
            return false;
        }
        boolean m189a = m189a();
        boolean m189a2 = y7Var.m189a();
        if (((!m189a && !m189a2) || (m189a && m189a2 && this.a.equals(y7Var.a))) && this.f268a == y7Var.f268a && this.f269b == y7Var.f269b) {
            boolean e2 = e();
            boolean e3 = y7Var.e();
            if ((e2 || e3) && !(e2 && e3 && this.f266a.equals(y7Var.f266a))) {
                return false;
            }
            boolean f2 = f();
            boolean f3 = y7Var.f();
            if ((f2 || f3) && !(f2 && f3 && this.f265a.equals(y7Var.f265a))) {
                return false;
            }
            boolean g2 = g();
            boolean g3 = y7Var.g();
            if ((g2 || g3) && !(g2 && g3 && this.b.equals(y7Var.b))) {
                return false;
            }
            boolean h2 = h();
            boolean h3 = y7Var.h();
            if ((h2 || h3) && !(h2 && h3 && this.f264a.m132a(y7Var.f264a))) {
                return false;
            }
            boolean i2 = i();
            boolean i3 = y7Var.i();
            if (i2 || i3) {
                return i2 && i3 && this.f263a.m124a(y7Var.f263a);
            }
            return true;
        }
        return false;
    }

    /* renamed from: a  reason: collision with other method in class */
    public byte[] m191a() {
        a(o8.n(this.f266a));
        return this.f266a.array();
    }

    public y7 b(String str) {
        this.b = str;
        return this;
    }

    public y7 b(boolean z) {
        this.f269b = z;
        m192b(true);
        return this;
    }

    public String b() {
        return this.b;
    }

    @Override // com.xiaomi.push.n8
    public void b(y8 y8Var) {
        m187a();
        y8Var.t(f19325g);
        if (this.a != null) {
            y8Var.q(f19326h);
            y8Var.o(this.a.a());
            y8Var.z();
        }
        y8Var.q(f19327i);
        y8Var.x(this.f268a);
        y8Var.z();
        y8Var.q(f19328j);
        y8Var.x(this.f269b);
        y8Var.z();
        if (this.f266a != null) {
            y8Var.q(f19329k);
            y8Var.v(this.f266a);
            y8Var.z();
        }
        if (this.f265a != null && f()) {
            y8Var.q(f19330l);
            y8Var.u(this.f265a);
            y8Var.z();
        }
        if (this.b != null && g()) {
            y8Var.q(f19331m);
            y8Var.u(this.b);
            y8Var.z();
        }
        if (this.f264a != null) {
            y8Var.q(f19332n);
            this.f264a.b(y8Var);
            y8Var.z();
        }
        if (this.f263a != null && i()) {
            y8Var.q(o);
            this.f263a.b(y8Var);
            y8Var.z();
        }
        y8Var.A();
        y8Var.m();
    }

    /* renamed from: b  reason: collision with other method in class */
    public void m192b(boolean z) {
        this.f267a.set(1, z);
    }

    /* renamed from: b  reason: collision with other method in class */
    public boolean m193b() {
        return this.f268a;
    }

    public boolean c() {
        return this.f267a.get(0);
    }

    public boolean d() {
        return this.f267a.get(1);
    }

    public boolean e() {
        return this.f266a != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof y7)) {
            return m190a((y7) obj);
        }
        return false;
    }

    public boolean f() {
        return this.f265a != null;
    }

    public boolean g() {
        return this.b != null;
    }

    public boolean h() {
        return this.f264a != null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.f263a != null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("XmPushActionContainer(");
        sb.append("action:");
        c7 c7Var = this.a;
        if (c7Var == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            sb.append(c7Var);
        }
        sb.append(", ");
        sb.append("encryptAction:");
        sb.append(this.f268a);
        sb.append(", ");
        sb.append("isRequest:");
        sb.append(this.f269b);
        if (f()) {
            sb.append(", ");
            sb.append("appid:");
            String str = this.f265a;
            if (str == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str);
            }
        }
        if (g()) {
            sb.append(", ");
            sb.append("packageName:");
            String str2 = this.b;
            if (str2 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str2);
            }
        }
        sb.append(", ");
        sb.append("target:");
        r7 r7Var = this.f264a;
        if (r7Var == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            sb.append(r7Var);
        }
        if (i()) {
            sb.append(", ");
            sb.append("metaInfo:");
            p7 p7Var = this.f263a;
            if (p7Var == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(p7Var);
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
