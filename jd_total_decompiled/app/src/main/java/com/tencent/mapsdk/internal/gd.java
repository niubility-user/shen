package com.tencent.mapsdk.internal;

import androidx.annotation.NonNull;

/* loaded from: classes9.dex */
public class gd extends tc<hd> {
    public gd(qi qiVar) {
        super(qiVar);
    }

    @Override // com.tencent.mapsdk.internal.tc
    public synchronized fd a(@NonNull hd hdVar) {
        return (fd) super.a((gd) hdVar);
    }

    @Override // com.tencent.mapsdk.internal.tc
    public void a(sc scVar) {
    }

    @Override // com.tencent.mapsdk.internal.tc
    public sc<hd> b(hd hdVar) {
        return new fd(this, hdVar);
    }

    @Override // com.tencent.mapsdk.internal.tc
    public void h() {
        int size = this.d.size();
        for (int i2 = 0; i2 < size; i2++) {
            fd fdVar = (fd) this.d.get(this.d.keyAt(i2));
            fdVar.a(this.b.a(fdVar.f()));
            fdVar.f().setBitmap(null);
            fdVar.f().setLatLngBounds(null);
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
            fd fdVar = (fd) this.f17274f.get(this.f17274f.keyAt(i2));
            this.b.a(fdVar.x(), fdVar.f());
            fdVar.f().setBitmap(null);
            fdVar.f().setLatLngBounds(null);
        }
    }
}
