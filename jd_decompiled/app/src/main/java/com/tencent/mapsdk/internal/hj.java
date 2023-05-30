package com.tencent.mapsdk.internal;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

@TargetApi(14)
/* loaded from: classes9.dex */
public class hj extends TextureView implements TextureView.SurfaceTextureListener, v1 {

    /* renamed from: g  reason: collision with root package name */
    private xi f16668g;

    /* renamed from: h  reason: collision with root package name */
    private wi f16669h;

    /* renamed from: i  reason: collision with root package name */
    private SurfaceTexture f16670i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f16671j;

    /* renamed from: k  reason: collision with root package name */
    private boolean f16672k;

    public hj(e1 e1Var) {
        super(e1Var.getContext());
        this.f16671j = false;
        this.f16672k = false;
        this.f16668g = (xi) e1Var.j();
        setSurfaceTextureListener(this);
        setOpaque(e1Var.isOpaque());
        wi wiVar = new wi(this.f16668g);
        this.f16669h = wiVar;
        wiVar.a(e1Var.k());
        this.f16669h.start();
    }

    @Override // com.tencent.mapsdk.internal.v1
    public void a(float f2) {
        wi wiVar = this.f16669h;
        if (wiVar != null) {
            wiVar.a(f2);
        }
    }

    @Override // android.view.View
    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        xi xiVar = this.f16668g;
        if (xiVar == null || !xiVar.a(motionEvent)) {
            return super.dispatchHoverEvent(motionEvent);
        }
        return true;
    }

    @Override // com.tencent.mapsdk.internal.v1
    public View getView() {
        return this;
    }

    @Override // com.tencent.mapsdk.internal.v1
    public void j() {
        wi wiVar = this.f16669h;
        if (wiVar != null) {
            synchronized (wiVar) {
                this.f16669h.notify();
            }
        }
    }

    @Override // com.tencent.mapsdk.internal.v1
    public void onDestroy() {
        wi wiVar = this.f16669h;
        if (wiVar != null) {
            wiVar.e();
        }
        SurfaceTexture surfaceTexture = this.f16670i;
        if (surfaceTexture != null) {
            surfaceTexture.release();
            this.f16670i = null;
        }
    }

    @Override // com.tencent.mapsdk.internal.v1
    public void onPause() {
        this.f16672k = true;
        wi wiVar = this.f16669h;
        if (wiVar != null) {
            wiVar.f();
        }
    }

    @Override // com.tencent.mapsdk.internal.v1
    public void onResume() {
        this.f16672k = false;
        if (this.f16670i != null && this.f16671j && getSurfaceTexture() != this.f16670i && isAvailable()) {
            setSurfaceTexture(this.f16670i);
            this.f16671j = false;
        }
        wi wiVar = this.f16669h;
        if (wiVar != null) {
            wiVar.g();
        }
    }

    @Override // android.view.TextureView, android.view.View, com.tencent.mapsdk.internal.v1
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        xi xiVar = this.f16668g;
        if (xiVar != null) {
            xiVar.d(i2, i3);
        }
    }

    @Override // com.tencent.mapsdk.internal.v1
    public void onSurfaceChanged(Object obj, int i2, int i3) {
        this.f16669h.a(obj);
        xi xiVar = this.f16668g;
        if (xiVar != null) {
            xiVar.a((GL10) null, (EGLConfig) null);
            this.f16668g.a((GL10) null, i2, i3);
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    @TargetApi(16)
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i2, int i3) {
        this.f16670i = surfaceTexture;
        onSurfaceChanged(surfaceTexture, i2, i3);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.f16671j = true;
        return !this.f16672k;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i2, int i3) {
        xi xiVar = this.f16668g;
        if (xiVar != null) {
            xiVar.a((GL10) null, i2, i3);
            wi wiVar = this.f16669h;
            if (wiVar != null) {
                wiVar.h();
            }
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    @Override // android.view.View, com.tencent.mapsdk.internal.v1
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v1
    public void setMapOpaque(boolean z) {
        if (this.f16668g != null) {
            setOpaque(z);
        }
    }

    @Override // com.tencent.mapsdk.internal.v1
    public void setZOrderMediaOverlay(boolean z) {
    }

    @Override // com.tencent.mapsdk.internal.v1
    public boolean z() {
        return isOpaque();
    }
}
