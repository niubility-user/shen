package com.tencent.mapsdk.internal;

import android.annotation.TargetApi;
import android.opengl.GLUtils;
import android.os.SystemClock;
import android.util.Log;
import com.jingdong.sdk.platform.business.personal.R2;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes9.dex */
public class wi extends Thread {
    private static float o = 60.0f;
    private static final int p = 12440;
    private static final int q = 4;
    private static int r = 2000;
    private static long s = 1500;
    private static final String t = "TextureGLRenderThread";
    private WeakReference<xi> a;
    private WeakReference<Object> d;

    /* renamed from: g  reason: collision with root package name */
    private EGL10 f17442g;

    /* renamed from: k  reason: collision with root package name */
    private GL f17446k;

    /* renamed from: l  reason: collision with root package name */
    private long f17447l;
    private AtomicBoolean b = new AtomicBoolean(true);

    /* renamed from: c  reason: collision with root package name */
    private AtomicBoolean f17439c = new AtomicBoolean(false);

    /* renamed from: e  reason: collision with root package name */
    private volatile boolean f17440e = false;

    /* renamed from: f  reason: collision with root package name */
    private EGLConfig f17441f = null;

    /* renamed from: h  reason: collision with root package name */
    private EGLDisplay f17443h = EGL10.EGL_NO_DISPLAY;

    /* renamed from: i  reason: collision with root package name */
    private EGLContext f17444i = EGL10.EGL_NO_CONTEXT;

    /* renamed from: j  reason: collision with root package name */
    private EGLSurface f17445j = EGL10.EGL_NO_SURFACE;

    /* renamed from: m  reason: collision with root package name */
    private volatile long f17448m = 0;

    /* renamed from: n  reason: collision with root package name */
    private boolean f17449n = false;

    public wi(xi xiVar) {
        this.a = new WeakReference<>(xiVar);
        setName(fj.c("TR"));
    }

    @TargetApi(14)
    private boolean a() {
        WeakReference<Object> weakReference;
        try {
            weakReference = this.d;
        } catch (Throwable th) {
            ma.g(t, "initializeGLContext failed,errorDetail:" + Log.getStackTraceString(th));
        }
        if (weakReference != null && weakReference.get() != null) {
            Object obj = this.d.get();
            EGL10 egl10 = (EGL10) EGLContext.getEGL();
            this.f17442g = egl10;
            EGLDisplay eglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            this.f17443h = eglGetDisplay;
            if (eglGetDisplay == EGL10.EGL_NO_DISPLAY) {
                ma.g(t, "eglGetdisplay failed,mEglDisplay == EGL10.EGL_NO_DISPLAY,errorDetail:" + GLUtils.getEGLErrorString(this.f17442g.eglGetError()));
                return false;
            } else if (!this.f17442g.eglInitialize(eglGetDisplay, new int[2])) {
                ma.g(t, "eglInitialize failed,errorDetail:" + GLUtils.getEGLErrorString(this.f17442g.eglGetError()));
                return false;
            } else {
                EGLConfig[] eGLConfigArr = new EGLConfig[1];
                if (!this.f17442g.eglChooseConfig(this.f17443h, new int[]{R2.id.app_brand_remote_debug_op_layout, 8, R2.id.app_brand_remote_debug_info_tv, 8, R2.id.app_brand_remote_debug_expand_tv, 8, R2.id.app_brand_remote_debug_error_tv, 8, R2.id.app_brand_remote_debug_quit_tv, 16, R2.id.app_brand_remote_debug_server_dot, 8, R2.id.app_video_box, 4, R2.id.app_name_tv}, eGLConfigArr, 1, new int[1])) {
                    ma.g(t, "eglChooseConfig failed,errorDetail:" + GLUtils.getEGLErrorString(this.f17442g.eglGetError()));
                    return false;
                }
                EGLConfig eGLConfig = eGLConfigArr[0];
                this.f17441f = eGLConfig;
                EGLSurface eglCreateWindowSurface = this.f17442g.eglCreateWindowSurface(this.f17443h, eGLConfig, obj, null);
                this.f17445j = eglCreateWindowSurface;
                if (eglCreateWindowSurface == EGL10.EGL_NO_SURFACE) {
                    ma.g(t, "eglCreateWindowSurface failed,mEglSurface == EGL10.EGL_NO_SURFACE,errorDetail:" + GLUtils.getEGLErrorString(this.f17442g.eglGetError()));
                    return false;
                }
                EGLContext eglCreateContext = this.f17442g.eglCreateContext(this.f17443h, eGLConfigArr[0], EGL10.EGL_NO_CONTEXT, new int[]{12440, 2, R2.id.app_name_tv});
                this.f17444i = eglCreateContext;
                if (eglCreateContext == EGL10.EGL_NO_CONTEXT) {
                    ma.g(t, "eglCreateContext failed,mEglContext == EGL10.EGL_NO_CONTEXT,errorDetail:" + GLUtils.getEGLErrorString(this.f17442g.eglGetError()));
                    return false;
                }
                EGL10 egl102 = this.f17442g;
                EGLDisplay eGLDisplay = this.f17443h;
                EGLSurface eGLSurface = this.f17445j;
                if (egl102.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, eglCreateContext)) {
                    this.f17446k = this.f17444i.getGL();
                    return true;
                }
                ma.g(t, "eglMakeCurrent failed,errorDetail:" + GLUtils.getEGLErrorString(this.f17442g.eglGetError()));
                return false;
            }
        }
        return false;
    }

    private void b() {
        d();
        c();
        i();
    }

    private void c() {
        EGLContext eGLContext = this.f17444i;
        if (eGLContext == null || eGLContext == EGL10.EGL_NO_CONTEXT) {
            ma.g(la.f16825l, "the EglContext is null or status is EGL_NO_CONTEXT");
            return;
        }
        this.f17442g.eglDestroyContext(this.f17443h, eGLContext);
        this.f17444i = EGL10.EGL_NO_CONTEXT;
    }

    private void d() {
        EGLSurface eGLSurface;
        EGLSurface eGLSurface2 = this.f17445j;
        if (eGLSurface2 == null || eGLSurface2 == (eGLSurface = EGL10.EGL_NO_SURFACE)) {
            ma.g(la.f16825l, "the EglSurface is null or status is EGL_NO_SURFACE");
            return;
        }
        this.f17442g.eglMakeCurrent(this.f17443h, eGLSurface, eGLSurface, EGL10.EGL_NO_CONTEXT);
        this.f17442g.eglDestroySurface(this.f17443h, this.f17445j);
        this.f17445j = EGL10.EGL_NO_SURFACE;
    }

    private void i() {
        EGLDisplay eGLDisplay = this.f17443h;
        if (eGLDisplay == null || eGLDisplay == EGL10.EGL_NO_DISPLAY) {
            ma.g(la.f16825l, "the EglDisplay is null or status is EGL_NO_DISPLAY");
            return;
        }
        this.f17442g.eglTerminate(eGLDisplay);
        this.f17443h = EGL10.EGL_NO_DISPLAY;
    }

    @TargetApi(14)
    private void j() {
        WeakReference<Object> weakReference;
        String str;
        while (this.b.get() && !this.f17439c.get() && SystemClock.elapsedRealtime() - this.f17447l <= r) {
            d();
            try {
                weakReference = this.d;
            } catch (Throwable th) {
                ma.g(t, "updateSurface failed,errorDetail:" + Log.getStackTraceString(th));
            }
            if (weakReference != null && weakReference.get() != null) {
                EGLSurface eglCreateWindowSurface = this.f17442g.eglCreateWindowSurface(this.f17443h, this.f17441f, this.d.get(), null);
                this.f17445j = eglCreateWindowSurface;
                if (eglCreateWindowSurface == EGL10.EGL_NO_SURFACE) {
                    str = "eglCreateWindowSurface failed,errorDetail:" + GLUtils.getEGLErrorString(this.f17442g.eglGetError());
                } else if (this.f17442g.eglMakeCurrent(this.f17443h, eglCreateWindowSurface, eglCreateWindowSurface, this.f17444i)) {
                    return;
                } else {
                    str = "eglMakeCurrent failed,errorDetail:" + GLUtils.getEGLErrorString(this.f17442g.eglGetError());
                }
                ma.g(t, str);
            }
            return;
        }
    }

    public void a(float f2) {
        if (f2 <= 0.0f) {
            ma.b(la.f16825l, "\u5e27\u7387\u8bbe\u7f6e\u4e0d\u5728\u6709\u6548\u503c\u8303\u56f4\u5185");
        } else {
            o = f2;
        }
    }

    public void a(Object obj) {
        WeakReference<Object> weakReference = this.d;
        if (weakReference != null && weakReference.get() != null) {
            this.f17440e = true;
        }
        this.d = new WeakReference<>(obj);
        synchronized (this) {
            notifyAll();
        }
    }

    public void e() {
        this.b.set(false);
        this.f17439c.set(false);
        synchronized (this) {
            notifyAll();
        }
        interrupt();
    }

    public void f() {
        this.f17439c.set(true);
        synchronized (this) {
            notifyAll();
        }
    }

    public void g() {
        this.f17439c.set(false);
        synchronized (this) {
            notifyAll();
        }
    }

    public void h() {
        this.f17449n = true;
        this.f17448m = SystemClock.elapsedRealtime();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        WeakReference<Object> weakReference;
        super.run();
        WeakReference<xi> weakReference2 = this.a;
        if (weakReference2 != null && weakReference2.get() != null) {
            this.a.get().g();
        }
        boolean z = false;
        while (this.b.get()) {
            while (this.b.get() && ((weakReference = this.d) == null || weakReference.get() == null)) {
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException unused) {
                    }
                }
            }
            if (!z) {
                z = a();
            }
            if (z) {
                try {
                    synchronized (this) {
                        while (this.b.get() && this.f17439c.get()) {
                            wait();
                        }
                    }
                    if (this.f17440e) {
                        this.f17447l = SystemClock.elapsedRealtime();
                        j();
                        this.f17449n = true;
                        this.f17440e = false;
                        h();
                    }
                    WeakReference<xi> weakReference3 = this.a;
                    if (weakReference3 != null && weakReference3.get() != null) {
                        xi xiVar = this.a.get();
                        if (!this.f17449n || SystemClock.elapsedRealtime() - this.f17448m >= s) {
                            this.f17449n = false;
                            this.f17448m = 0L;
                        } else if (xiVar != null) {
                            xiVar.h();
                        }
                        long elapsedRealtime = SystemClock.elapsedRealtime();
                        if (xiVar != null && xiVar.a((GL10) this.f17446k)) {
                            this.f17442g.eglSwapBuffers(this.f17443h, this.f17445j);
                        }
                        int elapsedRealtime2 = (int) ((1000.0f / o) - ((float) (SystemClock.elapsedRealtime() - elapsedRealtime)));
                        if (elapsedRealtime2 > 0) {
                            synchronized (this) {
                                try {
                                    wait(elapsedRealtime2);
                                } catch (InterruptedException unused2) {
                                }
                            }
                        }
                    }
                } catch (Throwable th) {
                    if (this.b.get() || !(th instanceof InterruptedException)) {
                        ma.g(t, "TextureGLRenderThread Render Exception:" + Log.getStackTraceString(th));
                    }
                }
            }
        }
        WeakReference<xi> weakReference4 = this.a;
        if (weakReference4 != null && weakReference4.get() != null) {
            this.a.get().p();
            this.a = null;
        }
        b();
    }
}
