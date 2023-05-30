package com.xiaomi.push;

import com.jd.dynamic.DYConstants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes11.dex */
public class b8 implements n8<b8, Object>, Serializable, Cloneable {

    /* renamed from: g  reason: collision with root package name */
    private static final e9 f18483g = new e9("XmPushActionNormalConfig");

    /* renamed from: h  reason: collision with root package name */
    private static final v8 f18484h = new v8("", (byte) 15, 1);
    public List<l7> a;

    @Override // java.lang.Comparable
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public int compareTo(b8 b8Var) {
        int g2;
        if (getClass().equals(b8Var.getClass())) {
            int compareTo = Boolean.valueOf(m32a()).compareTo(Boolean.valueOf(b8Var.m32a()));
            if (compareTo != 0) {
                return compareTo;
            }
            if (!m32a() || (g2 = o8.g(this.a, b8Var.a)) == 0) {
                return 0;
            }
            return g2;
        }
        return getClass().getName().compareTo(b8Var.getClass().getName());
    }

    public List<l7> a() {
        return this.a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m31a() {
        if (this.a != null) {
            return;
        }
        throw new z8("Required field 'normalConfigs' was not present! Struct: " + toString());
    }

    @Override // com.xiaomi.push.n8
    public void a(y8 y8Var) {
        y8Var.i();
        while (true) {
            v8 e2 = y8Var.e();
            byte b = e2.b;
            if (b == 0) {
                y8Var.D();
                m31a();
                return;
            }
            if (e2.f19282c == 1 && b == 15) {
                w8 f2 = y8Var.f();
                this.a = new ArrayList(f2.b);
                for (int i2 = 0; i2 < f2.b; i2++) {
                    l7 l7Var = new l7();
                    l7Var.a(y8Var);
                    this.a.add(l7Var);
                }
                y8Var.G();
            } else {
                c9.a(y8Var, b);
            }
            y8Var.E();
        }
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m32a() {
        return this.a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m33a(b8 b8Var) {
        if (b8Var == null) {
            return false;
        }
        boolean m32a = m32a();
        boolean m32a2 = b8Var.m32a();
        if (m32a || m32a2) {
            return m32a && m32a2 && this.a.equals(b8Var.a);
        }
        return true;
    }

    @Override // com.xiaomi.push.n8
    public void b(y8 y8Var) {
        m31a();
        y8Var.t(f18483g);
        if (this.a != null) {
            y8Var.q(f18484h);
            y8Var.r(new w8((byte) 12, this.a.size()));
            Iterator<l7> it = this.a.iterator();
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
        if (obj != null && (obj instanceof b8)) {
            return m33a((b8) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("XmPushActionNormalConfig(");
        sb.append("normalConfigs:");
        List<l7> list = this.a;
        if (list == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            sb.append(list);
        }
        sb.append(")");
        return sb.toString();
    }
}
