package com.tencent.mapsdk.internal;

import androidx.annotation.NonNull;

/* loaded from: classes9.dex */
public class ld extends tc<nd> {
    public ld(qi qiVar) {
        super(qiVar);
    }

    public int a(long j2) {
        qi qiVar = this.b;
        if (qiVar == null || j2 == 0) {
            return 0;
        }
        return qiVar.e(j2);
    }

    @Override // com.tencent.mapsdk.internal.tc
    public synchronized md a(@NonNull nd ndVar) {
        return (md) super.a((ld) ndVar);
    }

    @Override // com.tencent.mapsdk.internal.tc
    public void a(sc scVar) {
    }

    @Override // com.tencent.mapsdk.internal.tc
    public md b(nd ndVar) {
        return new md(this, ndVar);
    }

    @Override // com.tencent.mapsdk.internal.tc
    public void h() {
        int size = this.d.size();
        for (int i2 = 0; i2 < size; i2++) {
            md mdVar = (md) this.d.get(this.d.keyAt(i2));
            mdVar.a(this.b.a(mdVar.f()));
        }
    }

    @Override // com.tencent.mapsdk.internal.tc
    public void i() {
        int size = this.f17276h.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.b.g(((sc) this.f17276h.get(this.f17276h.keyAt(i2))).x());
        }
    }

    @Override // com.tencent.mapsdk.internal.tc
    public void j() {
        int size = this.f17274f.size();
        for (int i2 = 0; i2 < size; i2++) {
            md mdVar = (md) this.f17274f.get(this.f17274f.keyAt(i2));
            this.b.a(mdVar.x(), mdVar.f());
        }
    }
}
