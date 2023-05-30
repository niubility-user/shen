package com.tencent.mapsdk.internal;

import android.view.animation.Interpolator;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public abstract class j8 implements Cloneable {
    public ArrayList<a> a = null;

    /* loaded from: classes9.dex */
    public interface a {
        void a(j8 j8Var);

        void b(j8 j8Var);

        void c(j8 j8Var);

        void d(j8 j8Var);
    }

    public abstract j8 a(long j2);

    public void a() {
    }

    public abstract void a(Interpolator interpolator);

    public void a(a aVar) {
        if (this.a == null) {
            this.a = new ArrayList<>();
        }
        this.a.add(aVar);
    }

    @Override // 
    /* renamed from: b  reason: merged with bridge method [inline-methods] */
    public j8 clone() {
        try {
            j8 j8Var = (j8) super.clone();
            ArrayList<a> arrayList = this.a;
            if (arrayList != null) {
                j8Var.a = new ArrayList<>();
                int size = arrayList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    j8Var.a.add(arrayList.get(i2));
                }
            }
            return j8Var;
        } catch (CloneNotSupportedException unused) {
            throw new AssertionError();
        }
    }

    public abstract void b(long j2);

    public void b(a aVar) {
        ArrayList<a> arrayList = this.a;
        if (arrayList == null) {
            return;
        }
        arrayList.remove(aVar);
        if (this.a.size() == 0) {
            this.a = null;
        }
    }

    public void c() {
    }

    public abstract long d();

    public ArrayList<a> e() {
        return this.a;
    }

    public abstract long f();

    public abstract boolean g();

    public boolean h() {
        return g();
    }

    public void i() {
        ArrayList<a> arrayList = this.a;
        if (arrayList != null) {
            arrayList.clear();
            this.a = null;
        }
    }

    public void j() {
    }
}
