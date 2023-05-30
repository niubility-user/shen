package com.tencent.mapsdk.internal;

import androidx.annotation.NonNull;

/* loaded from: classes9.dex */
public class sd extends tc<td> {
    public sd(qi qiVar) {
        super(qiVar);
    }

    @Override // com.tencent.mapsdk.internal.tc
    public synchronized rd a(@NonNull td tdVar) {
        return (rd) super.a((sd) tdVar);
    }

    @Override // com.tencent.mapsdk.internal.tc
    public void a(sc scVar) {
    }

    @Override // com.tencent.mapsdk.internal.tc
    public sc<td> b(td tdVar) {
        return new rd(this, tdVar);
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
            O |= ((rd) this.f17272c.get(this.f17272c.keyAt(i2))).f().isAnimate();
        }
        if (O) {
            this.b.T();
        }
    }

    @Override // com.tencent.mapsdk.internal.tc
    public void h() {
        int size = this.d.size();
        for (int i2 = 0; i2 < size; i2++) {
            rd rdVar = (rd) this.d.get(this.d.keyAt(i2));
            rdVar.a(this.b.a(rdVar.f()));
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
            rd rdVar = (rd) this.f17274f.get(this.f17274f.keyAt(i2));
            this.b.a(rdVar.x(), rdVar.f());
        }
    }
}
