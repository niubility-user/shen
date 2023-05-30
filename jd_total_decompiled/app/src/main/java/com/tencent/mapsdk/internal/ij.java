package com.tencent.mapsdk.internal;

import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import com.jingdong.sdk.platform.business.personal.R2;
import com.tencent.map.lib.models.AccessibleTouchItem;
import com.tencent.mapsdk.internal.fj;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes9.dex */
public class ij extends fj implements fj.n, le, v1, zd {
    private final xi G;
    private boolean H;
    private Object I;
    private e1 J;

    /* loaded from: classes9.dex */
    public class a implements fj.g {
        public a() {
        }

        @Override // com.tencent.mapsdk.internal.fj.g
        public EGLContext a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
            EGLContext eglCreateContext = egl10.eglCreateContext(eGLDisplay, eGLConfig, EGL10.EGL_NO_CONTEXT, new int[]{R2.id.back_button, 2, R2.id.app_name_tv});
            if (ij.this.G != null) {
                ij.this.G.g();
            }
            return eglCreateContext;
        }

        @Override // com.tencent.mapsdk.internal.fj.g
        public void a(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext) {
            if (ij.this.G != null) {
                ij.this.G.p();
            }
            EGLSurface eGLSurface = EGL10.EGL_NO_SURFACE;
            egl10.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, EGL10.EGL_NO_CONTEXT);
            egl10.eglDestroyContext(eGLDisplay, eGLContext);
        }
    }

    /* loaded from: classes9.dex */
    public class b implements fj.h {
        public b() {
        }

        @Override // com.tencent.mapsdk.internal.fj.h
        public EGLSurface a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj) {
            EGLSurface eGLSurface = null;
            try {
                eGLSurface = egl10.eglCreateWindowSurface(eGLDisplay, eGLConfig, ij.this.I, null);
            } catch (IllegalArgumentException | OutOfMemoryError unused) {
            }
            if (ij.this.G != null) {
                ij.this.G.g();
            }
            return eGLSurface;
        }

        @Override // com.tencent.mapsdk.internal.fj.h
        public void a(EGL10 egl10, EGLDisplay eGLDisplay, EGLSurface eGLSurface) {
            if (ij.this.G != null) {
                ij.this.G.p();
            }
            egl10.eglDestroySurface(eGLDisplay, eGLSurface);
        }
    }

    public ij(e1 e1Var) {
        super(e1Var.getContext());
        this.J = e1Var;
        this.I = e1Var.i();
        this.G = (xi) e1Var.j();
        L();
        setContentDescription(AccessibleTouchItem.MAP_DEFAULT_CONTENT_DESCRIPTION);
    }

    private void S() {
        setEGLContextClientVersion(2);
        setPreserveEGLContextOnPause(true);
        a(8, 8, 8, 8, 16, 8);
        setEGLContextFactory(new a());
        if (this.I != null) {
            setEGLWindowSurfaceFactory(new b());
        }
    }

    public static boolean a(fj fjVar, boolean z) {
        if (Build.VERSION.SDK_INT < 11) {
            return false;
        }
        fjVar.setPreserveEGLContextOnPause(z);
        return true;
    }

    @Override // com.tencent.mapsdk.internal.zd
    public void L() {
        S();
        a(this, this.J.k());
        setRenderMode(0);
        this.H = a((fj) this, true);
    }

    @Override // com.tencent.mapsdk.internal.fj.n
    public void a(GL10 gl10, int i2, int i3) {
        xi xiVar = this.G;
        if (xiVar != null) {
            xiVar.a(gl10, i2, i3);
        }
    }

    @Override // com.tencent.mapsdk.internal.fj.n
    public void a(GL10 gl10, EGLConfig eGLConfig) {
        xi xiVar = this.G;
        if (xiVar != null) {
            xiVar.a(gl10, eGLConfig);
        }
    }

    @Override // com.tencent.mapsdk.internal.fj.n
    public boolean a(GL10 gl10) {
        xi xiVar = this.G;
        if (xiVar == null) {
            return false;
        }
        return xiVar.a(gl10);
    }

    @Override // android.view.View
    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        xi xiVar = this.G;
        if (xiVar == null || !xiVar.a(motionEvent)) {
            return super.dispatchHoverEvent(motionEvent);
        }
        return true;
    }

    @Override // com.tencent.mapsdk.internal.le
    public int getEGLContextHash() {
        xi xiVar = this.G;
        if (xiVar != null) {
            return xiVar.getEGLContextHash();
        }
        return 0;
    }

    public a1 getVectorMapDelegate() {
        return this.G;
    }

    @Override // com.tencent.mapsdk.internal.v1
    public View getView() {
        return this;
    }

    @Override // com.tencent.mapsdk.internal.v1
    public void j() {
        if (getVisibility() == 0) {
            a();
        }
    }

    @Override // com.tencent.mapsdk.internal.fj, com.tencent.mapsdk.internal.v1
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.tencent.mapsdk.internal.fj, com.tencent.mapsdk.internal.v1
    public void onPause() {
        if (this.H) {
            super.onPause();
        }
    }

    @Override // com.tencent.mapsdk.internal.fj, com.tencent.mapsdk.internal.v1
    public void onResume() {
        if (this.H) {
            super.onResume();
        }
    }

    @Override // android.view.View, com.tencent.mapsdk.internal.v1
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        xi xiVar = this.G;
        if (xiVar != null) {
            xiVar.d(i2, i3);
        }
    }

    @Override // com.tencent.mapsdk.internal.v1
    public void onSurfaceChanged(Object obj, int i2, int i3) {
    }

    @Override // android.view.View, com.tencent.mapsdk.internal.v1
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v1
    public void setMapOpaque(boolean z) {
    }

    @Override // com.tencent.mapsdk.internal.fj.n
    public void x() {
        xi xiVar = this.G;
        if (xiVar != null) {
            xiVar.x();
        }
    }

    @Override // com.tencent.mapsdk.internal.v1
    public boolean z() {
        return false;
    }
}
