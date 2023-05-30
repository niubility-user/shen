package com.tencent.mapsdk.internal;

import androidx.annotation.NonNull;

/* loaded from: classes9.dex */
public class pd extends tc<qd> {
    public pd(qi qiVar) {
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
    public synchronized od a(@NonNull qd qdVar) {
        return (od) super.a((pd) qdVar);
    }

    @Override // com.tencent.mapsdk.internal.tc
    public void a(sc scVar) {
    }

    @Override // com.tencent.mapsdk.internal.tc
    /* renamed from: b  reason: merged with bridge method [inline-methods] */
    public synchronized od a(int i2) {
        return (od) super.a(i2);
    }

    @Override // com.tencent.mapsdk.internal.tc
    public od b(qd qdVar) {
        return new od(this, qdVar);
    }

    @Override // com.tencent.mapsdk.internal.tc
    public void h() {
        int size = this.d.size();
        for (int i2 = 0; i2 < size; i2++) {
            od odVar = (od) this.d.get(this.d.keyAt(i2));
            odVar.a(this.b.a(odVar.f()));
        }
    }

    @Override // com.tencent.mapsdk.internal.tc
    public void i() {
        int size = this.f17276h.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.b.g(((od) this.f17276h.get(this.f17276h.keyAt(i2))).x());
        }
    }

    @Override // com.tencent.mapsdk.internal.tc
    public void j() {
        int size = this.f17274f.size();
        for (int i2 = 0; i2 < size; i2++) {
            od odVar = (od) this.f17274f.get(this.f17274f.keyAt(i2));
            this.b.a(odVar.x(), odVar.f());
        }
    }
}
