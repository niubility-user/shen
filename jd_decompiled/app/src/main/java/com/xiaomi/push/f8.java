package com.xiaomi.push;

import com.jd.dynamic.DYConstants;
import java.io.Serializable;
import java.util.BitSet;

/* loaded from: classes11.dex */
public class f8 implements n8<f8, Object>, Serializable, Cloneable {

    /* renamed from: g  reason: collision with root package name */
    private static final e9 f18625g = new e9("XmPushActionSendFeedbackResult");

    /* renamed from: h  reason: collision with root package name */
    private static final v8 f18626h = new v8("", (byte) 11, 1);

    /* renamed from: i  reason: collision with root package name */
    private static final v8 f18627i = new v8("", (byte) 12, 2);

    /* renamed from: j  reason: collision with root package name */
    private static final v8 f18628j = new v8("", (byte) 11, 3);

    /* renamed from: k  reason: collision with root package name */
    private static final v8 f18629k = new v8("", (byte) 11, 4);

    /* renamed from: l  reason: collision with root package name */
    private static final v8 f18630l = new v8("", (byte) 10, 6);

    /* renamed from: m  reason: collision with root package name */
    private static final v8 f18631m = new v8("", (byte) 11, 7);

    /* renamed from: n  reason: collision with root package name */
    private static final v8 f18632n = new v8("", (byte) 11, 8);
    public long a;

    /* renamed from: a  reason: collision with other field name */
    public r7 f139a;

    /* renamed from: a  reason: collision with other field name */
    public String f140a;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f141a = new BitSet(1);
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public String f18633c;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public String f18634e;

    @Override // java.lang.Comparable
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public int compareTo(f8 f8Var) {
        int e2;
        int e3;
        int c2;
        int e4;
        int e5;
        int d;
        int e6;
        if (getClass().equals(f8Var.getClass())) {
            int compareTo = Boolean.valueOf(m58a()).compareTo(Boolean.valueOf(f8Var.m58a()));
            if (compareTo != 0) {
                return compareTo;
            }
            if (!m58a() || (e6 = o8.e(this.f140a, f8Var.f140a)) == 0) {
                int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(f8Var.b()));
                if (compareTo2 != 0) {
                    return compareTo2;
                }
                if (!b() || (d = o8.d(this.f139a, f8Var.f139a)) == 0) {
                    int compareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(f8Var.c()));
                    if (compareTo3 != 0) {
                        return compareTo3;
                    }
                    if (!c() || (e5 = o8.e(this.b, f8Var.b)) == 0) {
                        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(f8Var.d()));
                        if (compareTo4 != 0) {
                            return compareTo4;
                        }
                        if (!d() || (e4 = o8.e(this.f18633c, f8Var.f18633c)) == 0) {
                            int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(f8Var.e()));
                            if (compareTo5 != 0) {
                                return compareTo5;
                            }
                            if (!e() || (c2 = o8.c(this.a, f8Var.a)) == 0) {
                                int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(f8Var.f()));
                                if (compareTo6 != 0) {
                                    return compareTo6;
                                }
                                if (!f() || (e3 = o8.e(this.d, f8Var.d)) == 0) {
                                    int compareTo7 = Boolean.valueOf(g()).compareTo(Boolean.valueOf(f8Var.g()));
                                    if (compareTo7 != 0) {
                                        return compareTo7;
                                    }
                                    if (!g() || (e2 = o8.e(this.f18634e, f8Var.f18634e)) == 0) {
                                        return 0;
                                    }
                                    return e2;
                                }
                                return e3;
                            }
                            return c2;
                        }
                        return e4;
                    }
                    return e5;
                }
                return d;
            }
            return e6;
        }
        return getClass().getName().compareTo(f8Var.getClass().getName());
    }

    public void a() {
        if (this.b == null) {
            throw new z8("Required field 'id' was not present! Struct: " + toString());
        } else if (this.f18633c != null) {
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
                    a();
                    return;
                }
                throw new z8("Required field 'errorCode' was not found in serialized data! Struct: " + toString());
            }
            switch (e2.f19282c) {
                case 1:
                    if (b == 11) {
                        this.f140a = y8Var.j();
                        continue;
                        y8Var.E();
                    }
                    break;
                case 2:
                    if (b == 12) {
                        r7 r7Var = new r7();
                        this.f139a = r7Var;
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
                        this.f18633c = y8Var.j();
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
                        this.f18634e = y8Var.j();
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
        this.f141a.set(0, z);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m58a() {
        return this.f140a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m59a(f8 f8Var) {
        if (f8Var == null) {
            return false;
        }
        boolean m58a = m58a();
        boolean m58a2 = f8Var.m58a();
        if ((m58a || m58a2) && !(m58a && m58a2 && this.f140a.equals(f8Var.f140a))) {
            return false;
        }
        boolean b = b();
        boolean b2 = f8Var.b();
        if ((b || b2) && !(b && b2 && this.f139a.m132a(f8Var.f139a))) {
            return false;
        }
        boolean c2 = c();
        boolean c3 = f8Var.c();
        if ((c2 || c3) && !(c2 && c3 && this.b.equals(f8Var.b))) {
            return false;
        }
        boolean d = d();
        boolean d2 = f8Var.d();
        if (((d || d2) && !(d && d2 && this.f18633c.equals(f8Var.f18633c))) || this.a != f8Var.a) {
            return false;
        }
        boolean f2 = f();
        boolean f3 = f8Var.f();
        if ((f2 || f3) && !(f2 && f3 && this.d.equals(f8Var.d))) {
            return false;
        }
        boolean g2 = g();
        boolean g3 = f8Var.g();
        if (g2 || g3) {
            return g2 && g3 && this.f18634e.equals(f8Var.f18634e);
        }
        return true;
    }

    @Override // com.xiaomi.push.n8
    public void b(y8 y8Var) {
        a();
        y8Var.t(f18625g);
        if (this.f140a != null && m58a()) {
            y8Var.q(f18626h);
            y8Var.u(this.f140a);
            y8Var.z();
        }
        if (this.f139a != null && b()) {
            y8Var.q(f18627i);
            this.f139a.b(y8Var);
            y8Var.z();
        }
        if (this.b != null) {
            y8Var.q(f18628j);
            y8Var.u(this.b);
            y8Var.z();
        }
        if (this.f18633c != null) {
            y8Var.q(f18629k);
            y8Var.u(this.f18633c);
            y8Var.z();
        }
        y8Var.q(f18630l);
        y8Var.p(this.a);
        y8Var.z();
        if (this.d != null && f()) {
            y8Var.q(f18631m);
            y8Var.u(this.d);
            y8Var.z();
        }
        if (this.f18634e != null && g()) {
            y8Var.q(f18632n);
            y8Var.u(this.f18634e);
            y8Var.z();
        }
        y8Var.A();
        y8Var.m();
    }

    public boolean b() {
        return this.f139a != null;
    }

    public boolean c() {
        return this.b != null;
    }

    public boolean d() {
        return this.f18633c != null;
    }

    public boolean e() {
        return this.f141a.get(0);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof f8)) {
            return m59a((f8) obj);
        }
        return false;
    }

    public boolean f() {
        return this.d != null;
    }

    public boolean g() {
        return this.f18634e != null;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionSendFeedbackResult(");
        boolean z2 = false;
        if (m58a()) {
            sb.append("debug:");
            String str = this.f140a;
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
            r7 r7Var = this.f139a;
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
        sb.append(", ");
        sb.append("appId:");
        String str3 = this.f18633c;
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
            sb.append("category:");
            String str5 = this.f18634e;
            if (str5 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str5);
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
