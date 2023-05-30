package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* loaded from: classes11.dex */
public class u7 implements n8<u7, Object>, Serializable, Cloneable {

    /* renamed from: g  reason: collision with root package name */
    private static final e9 f19265g = new e9("XmPushActionCheckClientInfo");

    /* renamed from: h  reason: collision with root package name */
    private static final v8 f19266h = new v8("", (byte) 8, 1);

    /* renamed from: i  reason: collision with root package name */
    private static final v8 f19267i = new v8("", (byte) 8, 2);
    public int a;

    /* renamed from: a  reason: collision with other field name */
    private BitSet f249a = new BitSet(2);
    public int b;

    @Override // java.lang.Comparable
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public int compareTo(u7 u7Var) {
        int b;
        int b2;
        if (getClass().equals(u7Var.getClass())) {
            int compareTo = Boolean.valueOf(m170a()).compareTo(Boolean.valueOf(u7Var.m170a()));
            if (compareTo != 0) {
                return compareTo;
            }
            if (!m170a() || (b2 = o8.b(this.a, u7Var.a)) == 0) {
                int compareTo2 = Boolean.valueOf(b()).compareTo(Boolean.valueOf(u7Var.b()));
                if (compareTo2 != 0) {
                    return compareTo2;
                }
                if (!b() || (b = o8.b(this.b, u7Var.b)) == 0) {
                    return 0;
                }
                return b;
            }
            return b2;
        }
        return getClass().getName().compareTo(u7Var.getClass().getName());
    }

    public u7 a(int i2) {
        this.a = i2;
        a(true);
        return this;
    }

    public void a() {
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
            if (s != 1) {
                if (s == 2 && b == 8) {
                    this.b = y8Var.c();
                    b(true);
                    y8Var.E();
                }
                c9.a(y8Var, b);
                y8Var.E();
            } else {
                if (b == 8) {
                    this.a = y8Var.c();
                    a(true);
                    y8Var.E();
                }
                c9.a(y8Var, b);
                y8Var.E();
            }
        }
        y8Var.D();
        if (!m170a()) {
            throw new z8("Required field 'miscConfigVersion' was not found in serialized data! Struct: " + toString());
        } else if (b()) {
            a();
        } else {
            throw new z8("Required field 'pluginConfigVersion' was not found in serialized data! Struct: " + toString());
        }
    }

    public void a(boolean z) {
        this.f249a.set(0, z);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m170a() {
        return this.f249a.get(0);
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m171a(u7 u7Var) {
        return u7Var != null && this.a == u7Var.a && this.b == u7Var.b;
    }

    public u7 b(int i2) {
        this.b = i2;
        b(true);
        return this;
    }

    @Override // com.xiaomi.push.n8
    public void b(y8 y8Var) {
        a();
        y8Var.t(f19265g);
        y8Var.q(f19266h);
        y8Var.o(this.a);
        y8Var.z();
        y8Var.q(f19267i);
        y8Var.o(this.b);
        y8Var.z();
        y8Var.A();
        y8Var.m();
    }

    public void b(boolean z) {
        this.f249a.set(1, z);
    }

    public boolean b() {
        return this.f249a.get(1);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof u7)) {
            return m171a((u7) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        return "XmPushActionCheckClientInfo(miscConfigVersion:" + this.a + ", pluginConfigVersion:" + this.b + ")";
    }
}
