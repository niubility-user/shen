package com.tencent.mapsdk.internal;

import androidx.annotation.NonNull;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.VectorHeatAggregationUnit;

/* loaded from: classes9.dex */
public class yc extends tc<xc> {
    public yc(qi qiVar) {
        super(qiVar);
    }

    @Override // com.tencent.mapsdk.internal.tc
    public synchronized wc a(@NonNull xc xcVar) {
        return (wc) super.a((yc) xcVar);
    }

    public VectorHeatAggregationUnit a(long j2, LatLng latLng) {
        qi qiVar = this.b;
        if (qiVar == null) {
            return null;
        }
        return qiVar.a(j2, latLng);
    }

    @Override // com.tencent.mapsdk.internal.tc
    public void a(sc scVar) {
    }

    @Override // com.tencent.mapsdk.internal.tc
    public sc<xc> b(xc xcVar) {
        return new wc(this, xcVar);
    }

    @Override // com.tencent.mapsdk.internal.tc
    public void f() {
        if (this.b.m()) {
            this.b.T();
        }
    }

    @Override // com.tencent.mapsdk.internal.tc
    public void g() {
        int size = this.f17272c.size();
        boolean O = this.b.O();
        for (int i2 = 0; i2 < size; i2++) {
            O |= ((wc) this.f17272c.get(this.f17272c.keyAt(i2))).f().isAnimate();
        }
        if (O) {
            this.b.T();
        }
    }

    @Override // com.tencent.mapsdk.internal.tc
    public void h() {
        int size = this.d.size();
        for (int i2 = 0; i2 < size; i2++) {
            wc wcVar = (wc) this.d.get(this.d.keyAt(i2));
            wcVar.a(this.b.a(wcVar.f()));
        }
    }

    @Override // com.tencent.mapsdk.internal.tc
    public void i() {
        int size = this.f17276h.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.b.h(((sc) this.f17276h.get(this.f17276h.keyAt(i2))).x());
        }
    }

    @Override // com.tencent.mapsdk.internal.tc
    public void j() {
        int size = this.f17274f.size();
        for (int i2 = 0; i2 < size; i2++) {
            wc wcVar = (wc) this.f17274f.get(this.f17274f.keyAt(i2));
            this.b.a(wcVar.x(), wcVar.f());
        }
    }
}
