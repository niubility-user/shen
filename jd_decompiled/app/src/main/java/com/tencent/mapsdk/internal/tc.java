package com.tencent.mapsdk.internal;

import android.content.Context;
import android.util.Pair;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.tencent.mapsdk.internal.uc;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.VectorOverlay;

/* loaded from: classes9.dex */
public abstract class tc<T extends uc> {
    public qi b;
    private int a = 0;

    /* renamed from: c  reason: collision with root package name */
    public SparseArray<sc<T>> f17272c = new SparseArray<>();
    public SparseArray<sc<T>> d = new SparseArray<>();

    /* renamed from: e  reason: collision with root package name */
    public SparseArray<sc<T>> f17273e = new SparseArray<>();

    /* renamed from: f  reason: collision with root package name */
    public SparseArray<sc<T>> f17274f = new SparseArray<>();

    /* renamed from: g  reason: collision with root package name */
    public SparseArray<sc<T>> f17275g = new SparseArray<>();

    /* renamed from: h  reason: collision with root package name */
    public SparseArray<sc<T>> f17276h = new SparseArray<>();

    /* renamed from: i  reason: collision with root package name */
    public SparseArray<sc<T>> f17277i = new SparseArray<>();

    /* loaded from: classes9.dex */
    public static class a implements TencentMap.IClickedObject {
        public LatLng a;
        public String b;

        /* renamed from: c  reason: collision with root package name */
        public String f17278c;

        public a(LatLng latLng, String str, String str2) {
            this.a = latLng;
            this.b = str;
            this.f17278c = str2;
        }

        @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.IClickedObject
        public String getIdentifier() {
            return this.b;
        }

        @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.IClickedObject
        @Nullable
        public String getName() {
            return this.f17278c;
        }

        @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.IClickedObject
        public LatLng getPosition() {
            return this.a;
        }
    }

    public tc(qi qiVar) {
        this.b = qiVar;
    }

    public Pair<VectorOverlay, TencentMap.IClickedObject> a(LatLng latLng, long j2, String str, String str2) {
        return new Pair<>(null, null);
    }

    public synchronized sc<T> a(int i2) {
        return this.f17272c.get(i2);
    }

    public synchronized sc<T> a(@NonNull T t) {
        sc<T> b;
        SparseArray<sc<T>> sparseArray;
        int i2;
        b = b((tc<T>) t);
        do {
            sparseArray = this.f17272c;
            i2 = this.a + 1;
            this.a = i2;
        } while (sparseArray.get(i2) != null);
        int i3 = this.a;
        b.f17242g = i3;
        this.f17272c.append(i3, b);
        this.f17273e.append(b.f17242g, b);
        this.b.m(true);
        return b;
    }

    public synchronized void a() {
        this.f17277i.clear();
        this.f17273e.clear();
        this.f17275g.clear();
        this.f17272c.clear();
    }

    public abstract void a(sc scVar);

    public abstract sc<T> b(T t);

    public synchronized void b(@NonNull sc<T> scVar) {
        a(scVar);
        if (this.f17272c.get(scVar.f17242g) == null) {
            return;
        }
        if (this.f17273e.get(scVar.f17242g) == null) {
            this.f17277i.append(scVar.f17242g, scVar);
        }
        this.f17272c.remove(scVar.f17242g);
        this.f17273e.remove(scVar.f17242g);
        this.f17275g.remove(scVar.f17242g);
        this.b.m(true);
    }

    public boolean b() {
        return false;
    }

    public final synchronized void c() {
        g();
        SparseArray<sc<T>> sparseArray = this.f17276h;
        this.f17276h = this.f17277i;
        this.f17277i = sparseArray;
        SparseArray<sc<T>> sparseArray2 = this.f17274f;
        this.f17274f = this.f17275g;
        this.f17275g = sparseArray2;
        SparseArray<sc<T>> sparseArray3 = this.d;
        this.d = this.f17273e;
        this.f17273e = sparseArray3;
        sparseArray3.clear();
        this.f17275g.clear();
        this.f17277i.clear();
        h();
        j();
        i();
        this.f17276h.clear();
        this.f17274f.clear();
        this.d.clear();
        f();
    }

    public synchronized void c(@NonNull sc<T> scVar) {
        if (this.f17272c.get(scVar.f17242g) == null) {
            return;
        }
        this.f17275g.append(scVar.f17242g, scVar);
        this.b.m(true);
    }

    public synchronized void d() {
        a();
    }

    public Context e() {
        qi qiVar = this.b;
        if (qiVar == null) {
            return null;
        }
        return qiVar.o();
    }

    public void f() {
    }

    public void g() {
    }

    public abstract void h();

    public abstract void i();

    public abstract void j();
}
