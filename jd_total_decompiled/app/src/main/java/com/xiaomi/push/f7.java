package com.xiaomi.push;

import com.jd.dynamic.DYConstants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes11.dex */
public class f7 implements n8<f7, Object>, Serializable, Cloneable {

    /* renamed from: g  reason: collision with root package name */
    private static final e9 f18623g = new e9("ClientUploadData");

    /* renamed from: h  reason: collision with root package name */
    private static final v8 f18624h = new v8("", (byte) 15, 1);
    public List<g7> a;

    public int a() {
        List<g7> list = this.a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // java.lang.Comparable
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public int compareTo(f7 f7Var) {
        int g2;
        if (getClass().equals(f7Var.getClass())) {
            int compareTo = Boolean.valueOf(m56a()).compareTo(Boolean.valueOf(f7Var.m56a()));
            if (compareTo != 0) {
                return compareTo;
            }
            if (!m56a() || (g2 = o8.g(this.a, f7Var.a)) == 0) {
                return 0;
            }
            return g2;
        }
        return getClass().getName().compareTo(f7Var.getClass().getName());
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m55a() {
        if (this.a != null) {
            return;
        }
        throw new z8("Required field 'uploadDataItems' was not present! Struct: " + toString());
    }

    public void a(g7 g7Var) {
        if (this.a == null) {
            this.a = new ArrayList();
        }
        this.a.add(g7Var);
    }

    @Override // com.xiaomi.push.n8
    public void a(y8 y8Var) {
        y8Var.i();
        while (true) {
            v8 e2 = y8Var.e();
            byte b = e2.b;
            if (b == 0) {
                y8Var.D();
                m55a();
                return;
            }
            if (e2.f19282c == 1 && b == 15) {
                w8 f2 = y8Var.f();
                this.a = new ArrayList(f2.b);
                for (int i2 = 0; i2 < f2.b; i2++) {
                    g7 g7Var = new g7();
                    g7Var.a(y8Var);
                    this.a.add(g7Var);
                }
                y8Var.G();
            } else {
                c9.a(y8Var, b);
            }
            y8Var.E();
        }
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m56a() {
        return this.a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m57a(f7 f7Var) {
        if (f7Var == null) {
            return false;
        }
        boolean m56a = m56a();
        boolean m56a2 = f7Var.m56a();
        if (m56a || m56a2) {
            return m56a && m56a2 && this.a.equals(f7Var.a);
        }
        return true;
    }

    @Override // com.xiaomi.push.n8
    public void b(y8 y8Var) {
        m55a();
        y8Var.t(f18623g);
        if (this.a != null) {
            y8Var.q(f18624h);
            y8Var.r(new w8((byte) 12, this.a.size()));
            Iterator<g7> it = this.a.iterator();
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
        if (obj != null && (obj instanceof f7)) {
            return m57a((f7) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ClientUploadData(");
        sb.append("uploadDataItems:");
        List<g7> list = this.a;
        if (list == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            sb.append(list);
        }
        sb.append(")");
        return sb.toString();
    }
}
