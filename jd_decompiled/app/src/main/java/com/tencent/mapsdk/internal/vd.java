package com.tencent.mapsdk.internal;

import androidx.annotation.NonNull;

/* loaded from: classes9.dex */
public class vd extends tc<wd> {
    public vd(qi qiVar) {
        super(qiVar);
    }

    @Override // com.tencent.mapsdk.internal.tc
    public synchronized ud a(@NonNull wd wdVar) {
        return (ud) super.a((vd) wdVar);
    }

    @Override // com.tencent.mapsdk.internal.tc
    public void a(sc scVar) {
    }

    @Override // com.tencent.mapsdk.internal.tc
    public sc<wd> b(wd wdVar) {
        return new ud(this, wdVar);
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
            O |= ((ud) this.f17272c.get(this.f17272c.keyAt(i2))).f().getIsAnimate();
        }
        if (O) {
            this.b.T();
        }
    }

    @Override // com.tencent.mapsdk.internal.tc
    public void h() {
        int size = this.d.size();
        for (int i2 = 0; i2 < size; i2++) {
            ud udVar = (ud) this.d.get(this.d.keyAt(i2));
            udVar.a(this.b.a(udVar.f()));
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
            ud udVar = (ud) this.f17274f.get(this.f17274f.keyAt(i2));
            this.b.a(udVar.x(), udVar.f());
        }
    }
}
