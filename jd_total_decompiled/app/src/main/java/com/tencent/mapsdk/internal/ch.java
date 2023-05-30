package com.tencent.mapsdk.internal;

import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.model.CameraPosition;

/* loaded from: classes9.dex */
public class ch implements k5, TencentMap.OnCameraChangeListener {

    /* renamed from: g  reason: collision with root package name */
    private qc f16374g;

    /* renamed from: h  reason: collision with root package name */
    private bh f16375h = null;

    /* renamed from: i  reason: collision with root package name */
    private hb f16376i;

    public ch(qc qcVar, hb hbVar) {
        this.f16374g = null;
        this.f16376i = null;
        this.f16374g = qcVar;
        this.f16376i = hbVar;
    }

    public void a() {
        qc qcVar = this.f16374g;
        if (qcVar == null) {
            return;
        }
        qcVar.h().b(this);
        bh bhVar = this.f16375h;
        if (bhVar != null) {
            bhVar.g();
            this.f16375h = null;
        }
    }

    public void a(zg zgVar) {
        bh bhVar = this.f16375h;
        if (bhVar == null || zgVar == null) {
            return;
        }
        bhVar.a(zgVar);
    }

    @Override // com.tencent.mapsdk.internal.k5
    public void b() {
        bh bhVar = this.f16375h;
        if (bhVar != null) {
            synchronized (bhVar) {
                this.f16375h.notify();
            }
        }
    }

    public void b(zg zgVar) {
        bh bhVar = this.f16375h;
        if (bhVar == null || zgVar == null) {
            return;
        }
        bhVar.b(zgVar);
    }

    public void c() {
        a();
    }

    public void d() {
        qc qcVar = this.f16374g;
        if (qcVar == null) {
            return;
        }
        qcVar.h().a(this);
        if (this.f16375h == null) {
            this.f16375h = new bh(this.f16374g, this.f16376i);
        }
        try {
            this.f16375h.start();
        } catch (Exception unused) {
        }
    }

    public void e() {
        bh bhVar = this.f16375h;
        if (bhVar != null) {
            bhVar.e();
        }
    }

    public void f() {
        bh bhVar = this.f16375h;
        if (bhVar != null) {
            bhVar.c();
            b();
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnCameraChangeListener
    public void onCameraChange(CameraPosition cameraPosition) {
        bh bhVar = this.f16375h;
        if (bhVar != null) {
            synchronized (bhVar) {
                this.f16375h.notify();
            }
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnCameraChangeListener
    public void onCameraChangeFinished(CameraPosition cameraPosition) {
    }
}
