package com.tencent.mapsdk.internal;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes9.dex */
public class gj extends View implements v1 {

    /* renamed from: g  reason: collision with root package name */
    private Context f16618g;

    /* renamed from: h  reason: collision with root package name */
    private xi f16619h;

    /* renamed from: i  reason: collision with root package name */
    private Object f16620i;

    /* renamed from: j  reason: collision with root package name */
    private int f16621j;

    /* renamed from: k  reason: collision with root package name */
    private int f16622k;

    /* renamed from: l  reason: collision with root package name */
    private wi f16623l;

    /* renamed from: m  reason: collision with root package name */
    private boolean f16624m;

    public gj(e1 e1Var) {
        super(e1Var.getContext());
        this.f16624m = true;
        Object i2 = e1Var.i();
        if (i2 == null) {
            return;
        }
        this.f16618g = e1Var.getContext();
        this.f16619h = (xi) e1Var.j();
        this.f16620i = i2;
        this.f16621j = e1Var.l();
        int c2 = e1Var.c();
        this.f16622k = c2;
        if (c2 <= 0 || this.f16621j <= 0) {
            this.f16621j = 0;
            this.f16622k = 0;
        }
        wi wiVar = new wi(this.f16619h);
        this.f16623l = wiVar;
        wiVar.a(this.f16620i);
        this.f16623l.a(e1Var.k());
        this.f16623l.start();
    }

    private void a() {
        xi xiVar = this.f16619h;
        if (xiVar == null || !this.f16624m) {
            return;
        }
        xiVar.a((GL10) null, (EGLConfig) null);
        this.f16619h.a((GL10) null, this.f16621j, this.f16622k);
        this.f16619h.d(this.f16621j, this.f16622k);
        this.f16624m = false;
    }

    @Override // com.tencent.mapsdk.internal.v1
    public void a(float f2) {
        wi wiVar = this.f16623l;
        if (wiVar != null) {
            wiVar.a(f2);
        }
    }

    @Override // com.tencent.mapsdk.internal.v1
    public View getView() {
        return this;
    }

    @Override // com.tencent.mapsdk.internal.v1
    public void j() {
        wi wiVar = this.f16623l;
        if (wiVar != null) {
            synchronized (wiVar) {
                this.f16623l.notify();
            }
        }
    }

    @Override // com.tencent.mapsdk.internal.v1
    public void onDestroy() {
        wi wiVar = this.f16623l;
        if (wiVar != null) {
            wiVar.e();
        }
    }

    @Override // com.tencent.mapsdk.internal.v1
    public void onPause() {
        wi wiVar = this.f16623l;
        if (wiVar != null) {
            wiVar.f();
        }
    }

    @Override // com.tencent.mapsdk.internal.v1
    public void onResume() {
        wi wiVar = this.f16623l;
        if (wiVar != null) {
            wiVar.g();
        }
        a();
    }

    @Override // android.view.View, com.tencent.mapsdk.internal.v1
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        xi xiVar = this.f16619h;
        if (xiVar != null) {
            this.f16621j = i2;
            this.f16622k = i3;
            xiVar.a((GL10) null, i2, i3);
            this.f16619h.d(i2, i3);
            this.f16619h.a();
            this.f16624m = true;
        }
    }

    @Override // com.tencent.mapsdk.internal.v1
    public void onSurfaceChanged(Object obj, int i2, int i3) {
        wi wiVar;
        if (this.f16619h == null || (wiVar = this.f16623l) == null || !wiVar.isAlive()) {
            return;
        }
        wi wiVar2 = this.f16623l;
        if (wiVar2 != null) {
            this.f16620i = obj;
            wiVar2.a(obj);
        }
        xi xiVar = this.f16619h;
        if (xiVar != null) {
            xiVar.a((GL10) null, (EGLConfig) null);
            this.f16619h.a((GL10) null, i2, i3);
        }
    }

    @Override // android.view.View, com.tencent.mapsdk.internal.v1
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v1
    public void setMapOpaque(boolean z) {
    }

    @Override // com.tencent.mapsdk.internal.v1
    public void setZOrderMediaOverlay(boolean z) {
    }

    @Override // com.tencent.mapsdk.internal.v1
    public boolean z() {
        return false;
    }
}
