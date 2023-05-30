package com.xiaomi.push;

import com.jd.dynamic.DYConstants;
import java.io.Serializable;
import java.util.BitSet;

/* loaded from: classes11.dex */
public class r7 implements n8<r7, Object>, Serializable, Cloneable {

    /* renamed from: g */
    private static final e9 f18988g = new e9("Target");

    /* renamed from: h */
    private static final v8 f18989h = new v8("", (byte) 10, 1);

    /* renamed from: i */
    private static final v8 f18990i = new v8("", (byte) 11, 2);

    /* renamed from: j */
    private static final v8 f18991j = new v8("", (byte) 11, 3);

    /* renamed from: k */
    private static final v8 f18992k = new v8("", (byte) 11, 4);

    /* renamed from: l */
    private static final v8 f18993l = new v8("", (byte) 2, 5);

    /* renamed from: m */
    private static final v8 f18994m = new v8("", (byte) 11, 7);

    /* renamed from: a */
    public String f203a;
    public String d;

    /* renamed from: a */
    private BitSet f204a = new BitSet(2);
    public long a = 5;
    public String b = "xiaomi.com";

    /* renamed from: c */
    public String f18995c = "";

    /* renamed from: a */
    public boolean f205a = false;

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(r7 r7Var) {
        int e2;
        int k2;
        int e3;
        int e4;
        int e5;
        int c2;
        if (getClass().equals(r7Var.getClass())) {
            int compareTo = Boolean.valueOf(m131a()).compareTo(Boolean.valueOf(r7Var.m131a()));
            if (compareTo != 0) {
                return compareTo;
            }
            if (!m131a() || (c2 = o8.c(this.a, r7Var.a)) == 0) {
                int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(r7Var.b()));
                if (compareTo2 != 0) {
                    return compareTo2;
                }
                if (!b() || (e5 = o8.e(this.f203a, r7Var.f203a)) == 0) {
                    int compareTo3 = Boolean.valueOf(c()).compareTo(Boolean.valueOf(r7Var.c()));
                    if (compareTo3 != 0) {
                        return compareTo3;
                    }
                    if (!c() || (e4 = o8.e(this.b, r7Var.b)) == 0) {
                        int compareTo4 = Boolean.valueOf(d()).compareTo(Boolean.valueOf(r7Var.d()));
                        if (compareTo4 != 0) {
                            return compareTo4;
                        }
                        if (!d() || (e3 = o8.e(this.f18995c, r7Var.f18995c)) == 0) {
                            int compareTo5 = Boolean.valueOf(e()).compareTo(Boolean.valueOf(r7Var.e()));
                            if (compareTo5 != 0) {
                                return compareTo5;
                            }
                            if (!e() || (k2 = o8.k(this.f205a, r7Var.f205a)) == 0) {
                                int compareTo6 = Boolean.valueOf(f()).compareTo(Boolean.valueOf(r7Var.f()));
                                if (compareTo6 != 0) {
                                    return compareTo6;
                                }
                                if (!f() || (e2 = o8.e(this.d, r7Var.d)) == 0) {
                                    return 0;
                                }
                                return e2;
                            }
                            return k2;
                        }
                        return e3;
                    }
                    return e4;
                }
                return e5;
            }
            return c2;
        }
        return getClass().getName().compareTo(r7Var.getClass().getName());
    }

    public void a() {
        if (this.f203a != null) {
            return;
        }
        throw new z8("Required field 'userId' was not present! Struct: " + toString());
    }

    @Override // com.xiaomi.push.n8
    public void a(y8 y8Var) {
        y8Var.i();
        while (true) {
            v8 e2 = y8Var.e();
            byte b = e2.b;
            if (b == 0) {
                break;
            }
            short s = e2.f19282c;
            if (s == 1) {
                if (b == 10) {
                    this.a = y8Var.d();
                    a(true);
                    y8Var.E();
                }
                c9.a(y8Var, b);
                y8Var.E();
            } else if (s == 2) {
                if (b == 11) {
                    this.f203a = y8Var.j();
                    y8Var.E();
                }
                c9.a(y8Var, b);
                y8Var.E();
            } else if (s == 3) {
                if (b == 11) {
                    this.b = y8Var.j();
                    y8Var.E();
                }
                c9.a(y8Var, b);
                y8Var.E();
            } else if (s == 4) {
                if (b == 11) {
                    this.f18995c = y8Var.j();
                    y8Var.E();
                }
                c9.a(y8Var, b);
                y8Var.E();
            } else if (s != 5) {
                if (s == 7 && b == 11) {
                    this.d = y8Var.j();
                    y8Var.E();
                }
                c9.a(y8Var, b);
                y8Var.E();
            } else {
                if (b == 2) {
                    this.f205a = y8Var.y();
                    b(true);
                    y8Var.E();
                }
                c9.a(y8Var, b);
                y8Var.E();
            }
        }
        y8Var.D();
        if (m131a()) {
            a();
            return;
        }
        throw new z8("Required field 'channelId' was not found in serialized data! Struct: " + toString());
    }

    public void a(boolean z) {
        this.f204a.set(0, z);
    }

    /* renamed from: a */
    public boolean m131a() {
        return this.f204a.get(0);
    }

    /* renamed from: a */
    public boolean m132a(r7 r7Var) {
        if (r7Var != null && this.a == r7Var.a) {
            boolean b = b();
            boolean b2 = r7Var.b();
            if ((b || b2) && !(b && b2 && this.f203a.equals(r7Var.f203a))) {
                return false;
            }
            boolean c2 = c();
            boolean c3 = r7Var.c();
            if ((c2 || c3) && !(c2 && c3 && this.b.equals(r7Var.b))) {
                return false;
            }
            boolean d = d();
            boolean d2 = r7Var.d();
            if ((d || d2) && !(d && d2 && this.f18995c.equals(r7Var.f18995c))) {
                return false;
            }
            boolean e2 = e();
            boolean e3 = r7Var.e();
            if ((e2 || e3) && !(e2 && e3 && this.f205a == r7Var.f205a)) {
                return false;
            }
            boolean f2 = f();
            boolean f3 = r7Var.f();
            if (f2 || f3) {
                return f2 && f3 && this.d.equals(r7Var.d);
            }
            return true;
        }
        return false;
    }

    @Override // com.xiaomi.push.n8
    public void b(y8 y8Var) {
        a();
        y8Var.t(f18988g);
        y8Var.q(f18989h);
        y8Var.p(this.a);
        y8Var.z();
        if (this.f203a != null) {
            y8Var.q(f18990i);
            y8Var.u(this.f203a);
            y8Var.z();
        }
        if (this.b != null && c()) {
            y8Var.q(f18991j);
            y8Var.u(this.b);
            y8Var.z();
        }
        if (this.f18995c != null && d()) {
            y8Var.q(f18992k);
            y8Var.u(this.f18995c);
            y8Var.z();
        }
        if (e()) {
            y8Var.q(f18993l);
            y8Var.x(this.f205a);
            y8Var.z();
        }
        if (this.d != null && f()) {
            y8Var.q(f18994m);
            y8Var.u(this.d);
            y8Var.z();
        }
        y8Var.A();
        y8Var.m();
    }

    public void b(boolean z) {
        this.f204a.set(1, z);
    }

    public boolean b() {
        return this.f203a != null;
    }

    public boolean c() {
        return this.b != null;
    }

    public boolean d() {
        return this.f18995c != null;
    }

    public boolean e() {
        return this.f204a.get(1);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof r7)) {
            return m132a((r7) obj);
        }
        return false;
    }

    public boolean f() {
        return this.d != null;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Target(");
        sb.append("channelId:");
        sb.append(this.a);
        sb.append(", ");
        sb.append("userId:");
        String str = this.f203a;
        if (str == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            sb.append(str);
        }
        if (c()) {
            sb.append(", ");
            sb.append("server:");
            String str2 = this.b;
            if (str2 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str2);
            }
        }
        if (d()) {
            sb.append(", ");
            sb.append("resource:");
            String str3 = this.f18995c;
            if (str3 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str3);
            }
        }
        if (e()) {
            sb.append(", ");
            sb.append("isPreview:");
            sb.append(this.f205a);
        }
        if (f()) {
            sb.append(", ");
            sb.append("token:");
            String str4 = this.d;
            if (str4 == null) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(str4);
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
