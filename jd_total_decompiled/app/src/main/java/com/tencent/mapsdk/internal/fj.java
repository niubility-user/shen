package com.tencent.mapsdk.internal;

import android.content.Context;
import android.opengl.GLDebugHelper;
import android.opengl.GLUtils;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.Writer;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes9.dex */
public class fj extends SurfaceView implements SurfaceHolder.Callback {
    public static final int A = 0;
    public static final int B = 1;
    public static final int C = 1;
    public static final int D = 2;
    public static final int E = 60;
    private static final k F = new k();
    public static final String r = "tms-gl";
    private static final String s = "GLSurfaceView";
    private static final boolean t = false;
    private static final boolean u = false;
    private static final boolean v = false;
    private static final boolean w = false;
    private static final boolean x = false;
    private static final boolean y = true;
    private static final boolean z = false;

    /* renamed from: g  reason: collision with root package name */
    private final WeakReference<fj> f16528g;

    /* renamed from: h  reason: collision with root package name */
    private j f16529h;

    /* renamed from: i  reason: collision with root package name */
    private n f16530i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f16531j;

    /* renamed from: k  reason: collision with root package name */
    private f f16532k;

    /* renamed from: l  reason: collision with root package name */
    private g f16533l;

    /* renamed from: m  reason: collision with root package name */
    private h f16534m;

    /* renamed from: n  reason: collision with root package name */
    private l f16535n;
    private int o;
    private int p;
    private boolean q;

    /* loaded from: classes9.dex */
    public abstract class b implements f {
        public int[] a;

        public b(int[] iArr) {
            this.a = a(iArr);
        }

        private int[] a(int[] iArr) {
            if (fj.this.p != 2) {
                return iArr;
            }
            int length = iArr.length;
            int[] iArr2 = new int[length + 2];
            int i2 = length - 1;
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            iArr2[i2] = 12352;
            iArr2[length] = 4;
            iArr2[length + 1] = 12344;
            return iArr2;
        }

        @Override // com.tencent.mapsdk.internal.fj.f
        public EGLConfig a(EGL10 egl10, EGLDisplay eGLDisplay) {
            int[] iArr = new int[1];
            if (egl10.eglChooseConfig(eGLDisplay, this.a, null, 0, iArr)) {
                int i2 = iArr[0];
                if (i2 > 0) {
                    EGLConfig[] eGLConfigArr = new EGLConfig[i2];
                    if (egl10.eglChooseConfig(eGLDisplay, this.a, eGLConfigArr, i2, iArr)) {
                        EGLConfig a = a(egl10, eGLDisplay, eGLConfigArr);
                        if (a != null) {
                            return a;
                        }
                        throw new IllegalArgumentException("No config chosen");
                    }
                    throw new IllegalArgumentException("eglChooseConfig#2 failed");
                }
                throw new IllegalArgumentException("No configs match configSpec");
            }
            throw new IllegalArgumentException("eglChooseConfig failed");
        }

        public abstract EGLConfig a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr);
    }

    /* loaded from: classes9.dex */
    public class c extends b {

        /* renamed from: c  reason: collision with root package name */
        private int[] f16536c;
        public int d;

        /* renamed from: e  reason: collision with root package name */
        public int f16537e;

        /* renamed from: f  reason: collision with root package name */
        public int f16538f;

        /* renamed from: g  reason: collision with root package name */
        public int f16539g;

        /* renamed from: h  reason: collision with root package name */
        public int f16540h;

        /* renamed from: i  reason: collision with root package name */
        public int f16541i;

        public c(int i2, int i3, int i4, int i5, int i6, int i7) {
            super(new int[]{R2.id.app_brand_remote_debug_op_layout, i2, R2.id.app_brand_remote_debug_info_tv, i3, R2.id.app_brand_remote_debug_expand_tv, i4, R2.id.app_brand_remote_debug_error_tv, i5, R2.id.app_brand_remote_debug_quit_tv, i6, R2.id.app_brand_remote_debug_server_dot, i7, R2.id.app_name_tv});
            this.f16536c = new int[1];
            this.d = i2;
            this.f16537e = i3;
            this.f16538f = i4;
            this.f16539g = i5;
            this.f16540h = i6;
            this.f16541i = i7;
        }

        private int a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i2, int i3) {
            return egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i2, this.f16536c) ? this.f16536c[0] : i3;
        }

        @Override // com.tencent.mapsdk.internal.fj.b
        public EGLConfig a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
            for (EGLConfig eGLConfig : eGLConfigArr) {
                int a = a(egl10, eGLDisplay, eGLConfig, R2.id.app_brand_remote_debug_quit_tv, 0);
                int a2 = a(egl10, eGLDisplay, eGLConfig, R2.id.app_brand_remote_debug_server_dot, 0);
                if (a >= this.f16540h && a2 >= this.f16541i) {
                    int a3 = a(egl10, eGLDisplay, eGLConfig, R2.id.app_brand_remote_debug_op_layout, 0);
                    int a4 = a(egl10, eGLDisplay, eGLConfig, R2.id.app_brand_remote_debug_info_tv, 0);
                    int a5 = a(egl10, eGLDisplay, eGLConfig, R2.id.app_brand_remote_debug_expand_tv, 0);
                    int a6 = a(egl10, eGLDisplay, eGLConfig, R2.id.app_brand_remote_debug_error_tv, 0);
                    if (a3 == this.d && a4 == this.f16537e && a5 == this.f16538f && a6 == this.f16539g) {
                        return eGLConfig;
                    }
                }
            }
            return null;
        }
    }

    /* loaded from: classes9.dex */
    public class d implements g {
        private int a;

        private d() {
            this.a = R2.id.back_button;
        }

        @Override // com.tencent.mapsdk.internal.fj.g
        public EGLContext a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
            int[] iArr = {this.a, fj.this.p, R2.id.app_name_tv};
            EGLContext eGLContext = EGL10.EGL_NO_CONTEXT;
            if (fj.this.p == 0) {
                iArr = null;
            }
            return egl10.eglCreateContext(eGLDisplay, eGLConfig, eGLContext, iArr);
        }

        @Override // com.tencent.mapsdk.internal.fj.g
        public void a(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext) {
            if (egl10.eglDestroyContext(eGLDisplay, eGLContext)) {
                return;
            }
            String str = "display:" + eGLDisplay + " context: " + eGLContext;
            i.b("eglDestroyContex", egl10.eglGetError());
        }
    }

    /* loaded from: classes9.dex */
    public static class e implements h {
        private e() {
        }

        @Override // com.tencent.mapsdk.internal.fj.h
        public EGLSurface a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj) {
            try {
                return egl10.eglCreateWindowSurface(eGLDisplay, eGLConfig, obj, null);
            } catch (IllegalArgumentException | OutOfMemoryError unused) {
                return null;
            }
        }

        @Override // com.tencent.mapsdk.internal.fj.h
        public void a(EGL10 egl10, EGLDisplay eGLDisplay, EGLSurface eGLSurface) {
            egl10.eglDestroySurface(eGLDisplay, eGLSurface);
        }
    }

    /* loaded from: classes9.dex */
    public interface f {
        EGLConfig a(EGL10 egl10, EGLDisplay eGLDisplay);
    }

    /* loaded from: classes9.dex */
    public interface g {
        EGLContext a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig);

        void a(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext);
    }

    /* loaded from: classes9.dex */
    public interface h {
        EGLSurface a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj);

        void a(EGL10 egl10, EGLDisplay eGLDisplay, EGLSurface eGLSurface);
    }

    /* loaded from: classes9.dex */
    public static class i {
        private WeakReference<fj> a;
        public EGL10 b;

        /* renamed from: c  reason: collision with root package name */
        public EGLDisplay f16543c;
        public EGLSurface d;

        /* renamed from: e  reason: collision with root package name */
        public EGLConfig f16544e;

        /* renamed from: f  reason: collision with root package name */
        public EGLContext f16545f;

        public i(WeakReference<fj> weakReference) {
            this.a = weakReference;
        }

        public static String a(String str, int i2) {
            return str + " failed: ";
        }

        private void a(String str) {
            b(str, this.b.eglGetError());
        }

        public static void a(String str, String str2, int i2) {
            a(str2, i2);
        }

        public static void b(String str, int i2) {
            throw new RuntimeException(a(str, i2));
        }

        private void d() {
            EGLSurface eGLSurface;
            EGLSurface eGLSurface2 = this.d;
            if (eGLSurface2 == null || eGLSurface2 == (eGLSurface = EGL10.EGL_NO_SURFACE)) {
                return;
            }
            this.b.eglMakeCurrent(this.f16543c, eGLSurface, eGLSurface, EGL10.EGL_NO_CONTEXT);
            fj fjVar = this.a.get();
            if (fjVar != null) {
                fjVar.f16534m.a(this.b, this.f16543c, this.d);
            }
            this.d = null;
        }

        public GL a() {
            GL gl = this.f16545f.getGL();
            fj fjVar = this.a.get();
            if (fjVar != null) {
                if (fjVar.f16535n != null) {
                    gl = fjVar.f16535n.a(gl);
                }
                if ((fjVar.o & 3) != 0) {
                    return GLDebugHelper.wrap(gl, (fjVar.o & 1) != 0 ? 1 : 0, (fjVar.o & 2) != 0 ? new m() : null);
                }
                return gl;
            }
            return gl;
        }

        public boolean b() {
            if (this.b != null) {
                if (this.f16543c != null) {
                    if (this.f16544e != null) {
                        d();
                        fj fjVar = this.a.get();
                        this.d = fjVar != null ? fjVar.f16534m.a(this.b, this.f16543c, this.f16544e, fjVar.getHolder()) : null;
                        EGLSurface eGLSurface = this.d;
                        if (eGLSurface == null || eGLSurface == EGL10.EGL_NO_SURFACE) {
                            this.b.eglGetError();
                            return false;
                        } else if (this.b.eglMakeCurrent(this.f16543c, eGLSurface, eGLSurface, this.f16545f)) {
                            return true;
                        } else {
                            a("EGLHelper", "eglMakeCurrent", this.b.eglGetError());
                            return false;
                        }
                    }
                    throw new RuntimeException("mEglConfig not initialized");
                }
                throw new RuntimeException("eglDisplay not initialized");
            }
            throw new RuntimeException("egl not initialized");
        }

        public void c() {
            d();
        }

        public void e() {
            if (this.f16545f != null) {
                fj fjVar = this.a.get();
                if (fjVar != null) {
                    try {
                        EGL10 egl10 = this.b;
                        EGLDisplay eGLDisplay = this.f16543c;
                        EGLSurface eGLSurface = EGL10.EGL_NO_SURFACE;
                        if (!egl10.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, this.f16545f)) {
                            String str = "finish() eglMakeCurrent failed,errorDetail:" + GLUtils.getEGLErrorString(this.b.eglGetError());
                        }
                    } catch (Exception e2) {
                        String str2 = "finish eglMakeCurrent exceptionDetail:" + Log.getStackTraceString(e2);
                    }
                    fjVar.f16533l.a(this.b, this.f16543c, this.f16545f);
                }
                this.f16545f = null;
            }
            EGLDisplay eGLDisplay2 = this.f16543c;
            if (eGLDisplay2 != null) {
                this.b.eglTerminate(eGLDisplay2);
                this.f16543c = null;
            }
        }

        public void f() {
            EGL10 egl10 = (EGL10) EGLContext.getEGL();
            this.b = egl10;
            EGLDisplay eglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            this.f16543c = eglGetDisplay;
            if (eglGetDisplay == EGL10.EGL_NO_DISPLAY) {
                throw new RuntimeException("eglGetDisplay failed");
            }
            if (!this.b.eglInitialize(eglGetDisplay, new int[2])) {
                throw new RuntimeException("eglInitialize failed");
            }
            fj fjVar = this.a.get();
            if (fjVar == null) {
                this.f16544e = null;
                this.f16545f = null;
            } else {
                this.f16544e = fjVar.f16532k.a(this.b, this.f16543c);
                this.f16545f = fjVar.f16533l.a(this.b, this.f16543c, this.f16544e);
            }
            EGLContext eGLContext = this.f16545f;
            if (eGLContext == null || eGLContext == EGL10.EGL_NO_CONTEXT) {
                this.f16545f = null;
                a("createContext");
            }
            this.d = null;
        }

        public int g() {
            if (this.b.eglSwapBuffers(this.f16543c, this.d)) {
                return 12288;
            }
            return this.b.eglGetError();
        }
    }

    /* loaded from: classes9.dex */
    public static class j extends Thread {
        private long a;
        private boolean b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f16546c;
        private boolean d;

        /* renamed from: e  reason: collision with root package name */
        private boolean f16547e;

        /* renamed from: f  reason: collision with root package name */
        private boolean f16548f;

        /* renamed from: g  reason: collision with root package name */
        private boolean f16549g;

        /* renamed from: h  reason: collision with root package name */
        private boolean f16550h;

        /* renamed from: i  reason: collision with root package name */
        private boolean f16551i;

        /* renamed from: j  reason: collision with root package name */
        private boolean f16552j;

        /* renamed from: k  reason: collision with root package name */
        private boolean f16553k;

        /* renamed from: l  reason: collision with root package name */
        private boolean f16554l;
        private boolean q;
        private i u;
        private WeakReference<fj> v;
        private ArrayList<Runnable> r = new ArrayList<>();
        private boolean s = true;
        private float t = 60.0f;

        /* renamed from: m  reason: collision with root package name */
        private int f16555m = 0;

        /* renamed from: n  reason: collision with root package name */
        private int f16556n = 0;
        private boolean p = true;
        private int o = 1;

        public j(WeakReference<fj> weakReference) {
            this.v = weakReference;
            setName(fj.c("SV"));
        }

        /* JADX WARN: Removed duplicated region for block: B:155:0x0251  */
        /* JADX WARN: Removed duplicated region for block: B:158:0x025c A[Catch: all -> 0x027b, Exception -> 0x027d, TRY_LEAVE, TryCatch #4 {Exception -> 0x027d, blocks: (B:3:0x001c, B:4:0x0020, B:93:0x0165, B:95:0x016f, B:97:0x0177, B:98:0x017b, B:105:0x018b, B:106:0x018c, B:107:0x0190, B:115:0x01a4, B:117:0x01a7, B:119:0x01ba, B:121:0x01c4, B:124:0x01d2, B:126:0x01dc, B:129:0x01e7, B:131:0x01ef, B:132:0x01f2, B:134:0x0206, B:136:0x0210, B:138:0x0218, B:142:0x0228, B:143:0x0235, B:156:0x0252, B:158:0x025c, B:167:0x0267, B:150:0x0244, B:173:0x027a), top: B:204:0x001c, outer: #9 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private void c() {
            boolean z;
            int i2;
            boolean z2;
            int i3;
            int i4;
            boolean z3;
            this.u = new i(this.v);
            this.f16551i = false;
            this.f16552j = false;
            boolean z4 = false;
            boolean z5 = false;
            boolean z6 = false;
            GL10 gl10 = null;
            boolean z7 = false;
            boolean z8 = false;
            boolean z9 = false;
            boolean z10 = false;
            int i5 = 0;
            int i6 = 0;
            Runnable runnable = null;
            boolean z11 = false;
            loop0: while (true) {
                try {
                    try {
                        synchronized (fj.F) {
                            while (!this.b) {
                                if (this.r.isEmpty()) {
                                    boolean z12 = this.f16547e;
                                    boolean z13 = this.d;
                                    if (z12 != z13) {
                                        this.f16547e = z13;
                                        fj.F.notifyAll();
                                    } else {
                                        z13 = false;
                                    }
                                    if (this.f16554l) {
                                        k();
                                        j();
                                        this.f16554l = false;
                                        z6 = true;
                                    }
                                    if (z4) {
                                        k();
                                        j();
                                        z4 = false;
                                    }
                                    if (z13 && this.f16552j) {
                                        k();
                                    }
                                    if (z13 && this.f16551i) {
                                        fj fjVar = this.v.get();
                                        if (!(fjVar != null && fjVar.q) || fj.F.b()) {
                                            j();
                                        }
                                    }
                                    if (z13 && fj.F.c()) {
                                        this.u.e();
                                    }
                                    if (!this.f16548f && !this.f16550h) {
                                        if (this.f16552j) {
                                            k();
                                        }
                                        this.f16550h = true;
                                        this.f16549g = false;
                                        fj.F.notifyAll();
                                    }
                                    if (this.f16548f && this.f16550h) {
                                        this.f16550h = false;
                                        fj.F.notifyAll();
                                    }
                                    if (z5) {
                                        this.q = true;
                                        fj.F.notifyAll();
                                        z5 = false;
                                        z11 = false;
                                    }
                                    if (f()) {
                                        if (!this.f16551i) {
                                            if (z6) {
                                                z6 = false;
                                            } else if (fj.F.c(this)) {
                                                try {
                                                    this.u.f();
                                                } catch (RuntimeException unused) {
                                                    fj.F.a(this);
                                                }
                                                this.f16551i = true;
                                                fj.F.notifyAll();
                                                z7 = true;
                                            }
                                        }
                                        if (this.f16551i && !this.f16552j) {
                                            this.f16552j = true;
                                            z8 = true;
                                            z9 = true;
                                            z10 = true;
                                        }
                                        if (this.f16552j) {
                                            if (this.s) {
                                                int i7 = this.f16555m;
                                                int i8 = this.f16556n;
                                                z3 = false;
                                                this.s = false;
                                                i5 = i7;
                                                i6 = i8;
                                                z = true;
                                                z8 = true;
                                                z11 = true;
                                            } else {
                                                z3 = false;
                                                z = false;
                                            }
                                            this.p = z3;
                                            fj.F.notifyAll();
                                        }
                                    }
                                    fj.F.wait();
                                } else {
                                    runnable = this.r.remove(0);
                                    z = z10;
                                }
                            }
                            break loop0;
                        }
                        if (runnable != null) {
                            runnable.run();
                            z10 = z;
                            runnable = null;
                        } else {
                            if (z8) {
                                if (this.u.b()) {
                                    synchronized (fj.F) {
                                        this.f16553k = true;
                                        fj.F.notifyAll();
                                    }
                                    z8 = false;
                                } else {
                                    synchronized (fj.F) {
                                        this.f16553k = true;
                                        this.f16549g = true;
                                        fj.F.notifyAll();
                                    }
                                    z10 = z;
                                }
                            }
                            if (z9) {
                                gl10 = (GL10) this.u.a();
                                fj.F.a(gl10);
                                z9 = false;
                            }
                            if (z7) {
                                fj fjVar2 = this.v.get();
                                if (fjVar2 != null) {
                                    fjVar2.f16530i.a(gl10, this.u.f16544e);
                                }
                                z7 = false;
                            }
                            if (z) {
                                fj fjVar3 = this.v.get();
                                if (fjVar3 != null) {
                                    fjVar3.f16530i.a(gl10, i5, i6);
                                }
                                i2 = i5;
                                z = false;
                            } else {
                                i2 = i5;
                            }
                            if (this.a != 0) {
                                SystemClock.elapsedRealtime();
                            }
                            this.a = SystemClock.elapsedRealtime();
                            long elapsedRealtime = SystemClock.elapsedRealtime();
                            fj fjVar4 = this.v.get();
                            boolean a = fjVar4 != null ? fjVar4.f16530i.a(gl10) : false;
                            long elapsedRealtime2 = SystemClock.elapsedRealtime() - elapsedRealtime;
                            if (a) {
                                int g2 = this.u.g();
                                z2 = z;
                                if (g2 != 12288) {
                                    if (g2 != 12302) {
                                        i3 = i2;
                                        i.a(fj.r, "eglSwapBuffers", g2);
                                        synchronized (fj.F) {
                                            this.f16549g = true;
                                            fj.F.notifyAll();
                                        }
                                    } else {
                                        i3 = i2;
                                        z4 = true;
                                    }
                                    if (z11) {
                                        z5 = true;
                                    }
                                    i4 = (int) ((1000.0f / this.t) - ((float) elapsedRealtime2));
                                    if (i4 > 0) {
                                        synchronized (this) {
                                            try {
                                                wait(i4);
                                            } catch (InterruptedException unused2) {
                                            }
                                        }
                                    }
                                    z10 = z2;
                                    i5 = i3;
                                }
                            } else {
                                z2 = z;
                            }
                            i3 = i2;
                            if (z11) {
                            }
                            i4 = (int) ((1000.0f / this.t) - ((float) elapsedRealtime2));
                            if (i4 > 0) {
                            }
                            z10 = z2;
                            i5 = i3;
                        }
                    } catch (Throwable th) {
                        synchronized (fj.F) {
                            k();
                            j();
                            throw th;
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    synchronized (fj.F) {
                        k();
                        j();
                        return;
                    }
                }
            }
            fj fjVar5 = this.v.get();
            if (fjVar5 != null) {
                fjVar5.f16530i.x();
            }
            synchronized (fj.F) {
                k();
                j();
            }
        }

        private boolean f() {
            return !this.f16547e && this.f16548f && !this.f16549g && this.f16555m > 0 && this.f16556n > 0 && (this.p || this.o == 1);
        }

        private void j() {
            if (this.f16551i) {
                this.u.e();
                this.f16551i = false;
                fj.F.a(this);
            }
        }

        private void k() {
            if (this.f16552j) {
                this.f16552j = false;
                this.u.c();
            }
        }

        public void a(float f2) {
            if (f2 <= 1.0f) {
                ma.b(la.f16825l, "\u5e27\u7387\u8bbe\u7f6e\u4e0d\u5728\u6709\u6548\u503c\u8303\u56f4\u5185");
            } else {
                this.t = f2;
            }
        }

        public void a(int i2) {
            if (i2 < 0 || i2 > 1) {
                throw new IllegalArgumentException("renderMode");
            }
            synchronized (fj.F) {
                this.o = i2;
                fj.F.notifyAll();
            }
        }

        public void a(int i2, int i3) {
            synchronized (fj.F) {
                if (this.f16555m == i2 && this.f16556n == i3) {
                    this.s = false;
                    fj.F.notifyAll();
                    return;
                }
                this.f16555m = i2;
                this.f16556n = i3;
                this.s = true;
                this.p = true;
                this.q = false;
                fj.F.notifyAll();
                while (!this.f16546c && !this.f16547e && !this.q && a()) {
                    try {
                        fj.F.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void a(Runnable runnable) {
            if (runnable == null) {
                throw new IllegalArgumentException("r must not be null");
            }
            synchronized (fj.F) {
                this.r.add(runnable);
                fj.F.notifyAll();
            }
        }

        public boolean a() {
            return this.f16551i && this.f16552j && f();
        }

        public int b() {
            int i2;
            synchronized (fj.F) {
                i2 = this.o;
            }
            return i2;
        }

        public void d() {
            synchronized (fj.F) {
                this.d = true;
                fj.F.notifyAll();
                while (!this.f16546c && !this.f16547e) {
                    try {
                        fj.F.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void e() {
            synchronized (fj.F) {
                this.d = false;
                this.p = true;
                this.q = false;
                fj.F.notifyAll();
                while (!this.f16546c && this.f16547e && !this.q) {
                    try {
                        fj.F.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void g() {
            synchronized (fj.F) {
                this.b = true;
                fj.F.notifyAll();
                while (!this.f16546c) {
                    try {
                        fj.F.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void h() {
            synchronized (fj.F) {
                this.f16554l = true;
                fj.F.notifyAll();
            }
        }

        public void i() {
            synchronized (fj.F) {
                this.p = true;
                fj.F.notifyAll();
            }
        }

        public void l() {
            synchronized (fj.F) {
                this.f16548f = true;
                this.f16553k = false;
                fj.F.notifyAll();
                while (this.f16550h && !this.f16553k && !this.f16546c) {
                    try {
                        fj.F.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void m() {
            synchronized (fj.F) {
                this.f16548f = false;
                fj.F.notifyAll();
                while (!this.f16550h && !this.f16546c) {
                    try {
                        fj.F.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                try {
                    c();
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
            } finally {
                fj.F.b(this);
            }
        }
    }

    /* loaded from: classes9.dex */
    public static class k {

        /* renamed from: g  reason: collision with root package name */
        private static String f16557g = "GLThreadManager";

        /* renamed from: h  reason: collision with root package name */
        private static final int f16558h = 131072;

        /* renamed from: i  reason: collision with root package name */
        private static final String f16559i = "Q3Dimension MSM7500 ";
        private boolean a;
        private int b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f16560c;
        private boolean d;

        /* renamed from: e  reason: collision with root package name */
        private boolean f16561e;

        /* renamed from: f  reason: collision with root package name */
        private j f16562f;

        private k() {
        }

        private void a() {
            if (this.a) {
                return;
            }
            this.b = 131072;
            this.d = true;
            this.a = true;
        }

        public synchronized void a(j jVar) {
            if (this.f16562f == jVar) {
                this.f16562f = null;
            }
            notifyAll();
        }

        public synchronized void a(GL10 gl10) {
            if (!this.f16560c) {
                a();
                String glGetString = gl10.glGetString(R2.drawable.btn_keyboard_other_click);
                if (this.b < 131072) {
                    this.d = !glGetString.startsWith(f16559i);
                    notifyAll();
                }
                this.f16561e = !this.d;
                this.f16560c = true;
            }
        }

        public synchronized void b(j jVar) {
            jVar.f16546c = true;
            if (this.f16562f == jVar) {
                this.f16562f = null;
            }
            notifyAll();
        }

        public synchronized boolean b() {
            return this.f16561e;
        }

        public synchronized boolean c() {
            a();
            return !this.d;
        }

        public synchronized boolean c(j jVar) {
            j jVar2 = this.f16562f;
            if (jVar2 != jVar && jVar2 != null) {
                a();
                if (this.d) {
                    return true;
                }
                j jVar3 = this.f16562f;
                if (jVar3 != null) {
                    jVar3.h();
                }
                return false;
            }
            this.f16562f = jVar;
            notifyAll();
            return true;
        }
    }

    /* loaded from: classes9.dex */
    public interface l {
        GL a(GL gl);
    }

    /* loaded from: classes9.dex */
    public static class m extends Writer {
        private StringBuilder a = new StringBuilder();

        private void a() {
            if (this.a.length() > 0) {
                this.a.toString();
                StringBuilder sb = this.a;
                sb.delete(0, sb.length());
            }
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            a();
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
            a();
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i2, int i3) {
            for (int i4 = 0; i4 < i3; i4++) {
                char c2 = cArr[i2 + i4];
                if (c2 == '\n') {
                    a();
                } else {
                    this.a.append(c2);
                }
            }
        }
    }

    /* loaded from: classes9.dex */
    public interface n {
        void a(GL10 gl10, int i2, int i3);

        void a(GL10 gl10, EGLConfig eGLConfig);

        boolean a(GL10 gl10);

        void x();
    }

    /* loaded from: classes9.dex */
    public class o extends c {
        public o(boolean z) {
            super(8, 8, 8, 0, z ? 16 : 0, 0);
        }
    }

    public fj(Context context) {
        super(context);
        this.f16528g = new WeakReference<>(this);
        R();
    }

    public fj(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f16528g = new WeakReference<>(this);
        R();
    }

    private void Q() {
        if (this.f16529h != null) {
            throw new IllegalStateException("setRenderer has already been called for this instance.");
        }
    }

    private void R() {
        getHolder().addCallback(this);
    }

    public static String c(String str) {
        return "tms-gl." + str + OrderISVUtil.MONEY_DECIMAL + "8b22fda";
    }

    public void a() {
        this.f16529h.i();
    }

    public void a(float f2) {
        j jVar = this.f16529h;
        if (jVar != null) {
            jVar.a(f2);
            this.f16529h.i();
        }
    }

    public void a(int i2, int i3, int i4, int i5, int i6, int i7) {
        setEGLConfigChooser(new c(i2, i3, i4, i5, i6, i7));
    }

    public void a(n nVar, float f2) {
        Q();
        if (this.f16532k == null) {
            this.f16532k = new o(true);
        }
        if (this.f16533l == null) {
            this.f16533l = new d();
        }
        if (this.f16534m == null) {
            this.f16534m = new e();
        }
        this.f16530i = nVar;
        j jVar = new j(this.f16528g);
        this.f16529h = jVar;
        jVar.a(f2);
        this.f16529h.start();
    }

    public void a(Runnable runnable) {
        this.f16529h.a(runnable);
    }

    public void finalize() {
        try {
            j jVar = this.f16529h;
            if (jVar != null) {
                jVar.g();
            }
        } finally {
            super.finalize();
        }
    }

    public int getDebugFlags() {
        return this.o;
    }

    public boolean getPreserveEGLContextOnPause() {
        return this.q;
    }

    public int getRenderMode() {
        return this.f16529h.b();
    }

    @Override // android.view.SurfaceView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f16531j) {
            this.f16529h.e();
        }
        this.f16531j = false;
    }

    public void onDestroy() {
        j jVar = this.f16529h;
        if (jVar != null) {
            jVar.g();
        }
    }

    @Override // android.view.SurfaceView, android.view.View
    public void onDetachedFromWindow() {
        j jVar = this.f16529h;
        if (jVar != null) {
            jVar.d();
        }
        this.f16531j = true;
        super.onDetachedFromWindow();
    }

    public void onPause() {
        this.f16529h.d();
    }

    public void onResume() {
        this.f16529h.e();
    }

    public void setDebugFlags(int i2) {
        this.o = i2;
    }

    public void setEGLConfigChooser(f fVar) {
        Q();
        this.f16532k = fVar;
    }

    public void setEGLConfigChooser(boolean z2) {
        setEGLConfigChooser(new o(z2));
    }

    public void setEGLContextClientVersion(int i2) {
        Q();
        this.p = i2;
    }

    public void setEGLContextFactory(g gVar) {
        Q();
        this.f16533l = gVar;
    }

    public void setEGLWindowSurfaceFactory(h hVar) {
        Q();
        this.f16534m = hVar;
    }

    public void setGLWrapper(l lVar) {
        this.f16535n = lVar;
    }

    public void setPreserveEGLContextOnPause(boolean z2) {
        this.q = z2;
    }

    public void setRenderMode(int i2) {
        this.f16529h.a(i2);
    }

    public void setRenderer(n nVar) {
        a(nVar, 60.0f);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i3, int i4) {
        this.f16529h.a(i3, i4);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.f16529h.l();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.f16529h.m();
    }
}
