package com.xiaomi.push;

import com.jd.dynamic.DYConstants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes11.dex */
public class v7 implements n8<v7, Object>, Serializable, Cloneable {

    /* renamed from: g  reason: collision with root package name */
    private static final e9 f19280g = new e9("XmPushActionCollectData");

    /* renamed from: h  reason: collision with root package name */
    private static final v8 f19281h = new v8("", (byte) 15, 1);
    public List<k7> a;

    @Override // java.lang.Comparable
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public int compareTo(v7 v7Var) {
        int g2;
        if (getClass().equals(v7Var.getClass())) {
            int compareTo = Boolean.valueOf(m172a()).compareTo(Boolean.valueOf(v7Var.m172a()));
            if (compareTo != 0) {
                return compareTo;
            }
            if (!m172a() || (g2 = o8.g(this.a, v7Var.a)) == 0) {
                return 0;
            }
            return g2;
        }
        return getClass().getName().compareTo(v7Var.getClass().getName());
    }

    public v7 a(List<k7> list) {
        this.a = list;
        return this;
    }

    public void a() {
        if (this.a != null) {
            return;
        }
        throw new z8("Required field 'dataCollectionItems' was not present! Struct: " + toString());
    }

    @Override // com.xiaomi.push.n8
    public void a(y8 y8Var) {
        y8Var.i();
        while (true) {
            v8 e2 = y8Var.e();
            byte b = e2.b;
            if (b == 0) {
                y8Var.D();
                a();
                return;
            }
            if (e2.f19282c == 1 && b == 15) {
                w8 f2 = y8Var.f();
                this.a = new ArrayList(f2.b);
                for (int i2 = 0; i2 < f2.b; i2++) {
                    k7 k7Var = new k7();
                    k7Var.a(y8Var);
                    this.a.add(k7Var);
                }
                y8Var.G();
            } else {
                c9.a(y8Var, b);
            }
            y8Var.E();
        }
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m172a() {
        return this.a != null;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m173a(v7 v7Var) {
        if (v7Var == null) {
            return false;
        }
        boolean m172a = m172a();
        boolean m172a2 = v7Var.m172a();
        if (m172a || m172a2) {
            return m172a && m172a2 && this.a.equals(v7Var.a);
        }
        return true;
    }

    @Override // com.xiaomi.push.n8
    public void b(y8 y8Var) {
        a();
        y8Var.t(f19280g);
        if (this.a != null) {
            y8Var.q(f19281h);
            y8Var.r(new w8((byte) 12, this.a.size()));
            Iterator<k7> it = this.a.iterator();
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
        if (obj != null && (obj instanceof v7)) {
            return m173a((v7) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("XmPushActionCollectData(");
        sb.append("dataCollectionItems:");
        List<k7> list = this.a;
        if (list == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            sb.append(list);
        }
        sb.append(")");
        return sb.toString();
    }
}
