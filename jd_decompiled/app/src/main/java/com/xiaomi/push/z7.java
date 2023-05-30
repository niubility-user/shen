package com.xiaomi.push;

import com.jd.dynamic.DYConstants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes11.dex */
public class z7 implements n8<z7, Object>, Serializable, Cloneable {

    /* renamed from: g  reason: collision with root package name */
    private static final e9 f19362g = new e9("XmPushActionCustomConfig");

    /* renamed from: h  reason: collision with root package name */
    private static final v8 f19363h = new v8("", (byte) 15, 1);
    public List<n7> a;

    @Override // java.lang.Comparable
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public int compareTo(z7 z7Var) {
        int g2;
        if (getClass().equals(z7Var.getClass())) {
            int compareTo = Boolean.valueOf(m195a()).compareTo(Boolean.valueOf(z7Var.m195a()));
            if (compareTo != 0) {
                return compareTo;
            }
            if (!m195a() || (g2 = o8.g(this.a, z7Var.a)) == 0) {
                return 0;
            }
            return g2;
        }
        return getClass().getName().compareTo(z7Var.getClass().getName());
    }

    public List<n7> a() {
        return this.a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m194a() {
        if (this.a != null) {
            return;
        }
        throw new z8("Required field 'customConfigs' was not present! Struct: " + toString());
    }

    @Override // com.xiaomi.push.n8
    public void a(y8 y8Var) {
        y8Var.i();
        while (true) {
            v8 e2 = y8Var.e();
            byte b = e2.b;
            if (b == 0) {
                y8Var.D();
                m194a();
                return;
            }
            if (e2.f19282c == 1 && b == 15) {
                w8 f2 = y8Var.f();
                this.a = new ArrayList(f2.b);
                for (int i2 = 0; i2 < f2.b; i2++) {
                    n7 n7Var = new n7();
                    n7Var.a(y8Var);
                    this.a.add(n7Var);
                }
                y8Var.G();
            } else {
                c9.a(y8Var, b);
            }
            y8Var.E();
        }
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m195a() {
        return this.a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m196a(z7 z7Var) {
        if (z7Var == null) {
            return false;
        }
        boolean m195a = m195a();
        boolean m195a2 = z7Var.m195a();
        if (m195a || m195a2) {
            return m195a && m195a2 && this.a.equals(z7Var.a);
        }
        return true;
    }

    @Override // com.xiaomi.push.n8
    public void b(y8 y8Var) {
        m194a();
        y8Var.t(f19362g);
        if (this.a != null) {
            y8Var.q(f19363h);
            y8Var.r(new w8((byte) 12, this.a.size()));
            Iterator<n7> it = this.a.iterator();
            while (it.hasNext()) {
                it.next().b(y8Var);
            }
            y8Var.C();
            y8Var.z();
        }
        y8Var.A();
        y8Var.m();
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof z7)) {
            return m196a((z7) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("XmPushActionCustomConfig(");
        sb.append("customConfigs:");
        List<n7> list = this.a;
        if (list == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            sb.append(list);
        }
        sb.append(")");
        return sb.toString();
    }
}
