package com.jd.lib.un.basewidget.widget.simple;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.NestedScrollingParent;
import androidx.core.view.NestedScrollingParentHelper;
import com.jd.lib.un.basewidget.R;
import com.jd.lib.un.basewidget.widget.simple.footer.BallPulseFooter;
import com.jd.lib.un.basewidget.widget.simple.header.BezierRadarHeader;
import com.jd.lib.un.utils.config.UnDeviceInfo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes16.dex */
public class SimpleRefreshLayout extends ViewGroup implements com.jd.lib.un.basewidget.widget.simple.c.f, NestedScrollingParent {
    private int A;
    private com.jd.lib.un.basewidget.widget.simple.c.e A0;
    private int B;
    private List<com.jd.lib.un.basewidget.widget.simple.b.a> B0;
    private int[] C;
    private com.jd.lib.un.basewidget.widget.simple.a.c C0;
    private boolean D;
    private com.jd.lib.un.basewidget.widget.simple.a.c D0;
    private boolean E;
    private long E0;
    private boolean F;
    private int F0;
    private boolean G;
    private int G0;
    private boolean H;
    private boolean H0;
    private boolean I;
    private boolean I0;
    private boolean J;
    private boolean J0;
    private boolean K;
    private char K0;
    private boolean L;
    protected boolean L0;
    private boolean M;
    protected MotionEvent M0;
    private boolean N;
    protected Runnable N0;
    private boolean O;
    protected ValueAnimator O0;
    private boolean P;
    private boolean Q;
    private boolean R;
    private boolean S;
    private boolean T;
    private boolean U;
    private boolean V;
    private int W;
    private int a0;
    private int b0;
    private int c0;
    private int d0;
    private int e0;
    private int f0;

    /* renamed from: g */
    private int f5737g;
    private float g0;

    /* renamed from: h */
    private com.jd.lib.un.basewidget.widget.simple.a.a f5738h;
    private float h0;

    /* renamed from: i */
    private int f5739i;
    private float i0;

    /* renamed from: j */
    private com.jd.lib.un.basewidget.widget.simple.a.a f5740j;
    private float j0;

    /* renamed from: k */
    private int f5741k;
    private boolean k0;

    /* renamed from: l */
    private int f5742l;
    private boolean l0;

    /* renamed from: m */
    private int f5743m;
    private Scroller m0;

    /* renamed from: n */
    private int f5744n;
    private VelocityTracker n0;
    private Interpolator o;
    private com.jd.lib.un.basewidget.widget.simple.d.d o0;
    private int p;
    private com.jd.lib.un.basewidget.widget.simple.d.b p0;
    private NestedScrollingChildHelper q;
    private com.jd.lib.un.basewidget.widget.simple.d.c q0;
    private NestedScrollingParentHelper r;
    private com.jd.lib.un.basewidget.widget.simple.c.g r0;
    private float s;
    private int s0;
    private float t;
    private boolean t0;
    private float u;
    private int[] u0;
    private float v;
    private com.jd.lib.un.basewidget.widget.simple.c.d v0;
    private float w;
    private com.jd.lib.un.basewidget.widget.simple.c.d w0;
    private boolean x;
    private com.jd.lib.un.basewidget.widget.simple.c.a x0;
    private boolean y;
    private Paint y0;
    private int z;
    private Handler z0;

    /* loaded from: classes16.dex */
    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[com.jd.lib.un.basewidget.widget.simple.a.c.values().length];
            a = iArr;
            try {
                iArr[com.jd.lib.un.basewidget.widget.simple.a.c.None.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[com.jd.lib.un.basewidget.widget.simple.a.c.PullDownToRefresh.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[com.jd.lib.un.basewidget.widget.simple.a.c.PullUpToLoad.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[com.jd.lib.un.basewidget.widget.simple.a.c.PullDownCanceled.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[com.jd.lib.un.basewidget.widget.simple.a.c.PullUpCanceled.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[com.jd.lib.un.basewidget.widget.simple.a.c.ReleaseToRefresh.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[com.jd.lib.un.basewidget.widget.simple.a.c.ReleaseToLoad.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[com.jd.lib.un.basewidget.widget.simple.a.c.RefreshReleased.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[com.jd.lib.un.basewidget.widget.simple.a.c.LoadReleased.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                a[com.jd.lib.un.basewidget.widget.simple.a.c.Refreshing.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                a[com.jd.lib.un.basewidget.widget.simple.a.c.Loading.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                a[com.jd.lib.un.basewidget.widget.simple.a.c.RefreshFinish.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                a[com.jd.lib.un.basewidget.widget.simple.a.c.LoadFinish.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    /* loaded from: classes16.dex */
    class b implements Runnable {
        b() {
            SimpleRefreshLayout.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (ViewParent parent = SimpleRefreshLayout.this.getParent(); parent != null; parent = ((View) parent).getParent()) {
                if (parent instanceof NestedScrollingParent) {
                    SimpleRefreshLayout simpleRefreshLayout = SimpleRefreshLayout.this;
                    if (((NestedScrollingParent) parent).onStartNestedScroll(simpleRefreshLayout, simpleRefreshLayout, 2)) {
                        SimpleRefreshLayout.this.setNestedScrollingEnabled(true);
                        SimpleRefreshLayout.this.T = false;
                        return;
                    }
                }
                if (!(parent instanceof View)) {
                    return;
                }
            }
        }
    }

    /* loaded from: classes16.dex */
    public class c extends AnimatorListenerAdapter {
        c() {
            SimpleRefreshLayout.this = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            SimpleRefreshLayout.this.y0();
        }
    }

    /* loaded from: classes16.dex */
    public class d extends AnimatorListenerAdapter {
        d() {
            SimpleRefreshLayout.this = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            SimpleRefreshLayout.this.E0 = System.currentTimeMillis();
            SimpleRefreshLayout.this.q0(com.jd.lib.un.basewidget.widget.simple.a.c.Refreshing);
            if (SimpleRefreshLayout.this.o0 != null) {
                SimpleRefreshLayout.this.o0.a(SimpleRefreshLayout.this);
            } else if (SimpleRefreshLayout.this.q0 == null) {
                SimpleRefreshLayout.this.f0(3000);
            }
            if (SimpleRefreshLayout.this.v0 != null) {
                com.jd.lib.un.basewidget.widget.simple.c.d dVar = SimpleRefreshLayout.this.v0;
                SimpleRefreshLayout simpleRefreshLayout = SimpleRefreshLayout.this;
                dVar.h(simpleRefreshLayout, simpleRefreshLayout.f5737g, (int) (SimpleRefreshLayout.this.t * SimpleRefreshLayout.this.f5737g));
            }
            if (SimpleRefreshLayout.this.q0 == null || !(SimpleRefreshLayout.this.v0 instanceof com.jd.lib.un.basewidget.widget.simple.c.c)) {
                return;
            }
            SimpleRefreshLayout.this.q0.a(SimpleRefreshLayout.this);
            SimpleRefreshLayout.this.q0.l((com.jd.lib.un.basewidget.widget.simple.c.c) SimpleRefreshLayout.this.v0, SimpleRefreshLayout.this.f5737g, (int) (SimpleRefreshLayout.this.t * SimpleRefreshLayout.this.f5737g));
        }
    }

    /* loaded from: classes16.dex */
    public class e extends AnimatorListenerAdapter {
        e() {
            SimpleRefreshLayout.this = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            super.onAnimationEnd(animator);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            SimpleRefreshLayout simpleRefreshLayout = SimpleRefreshLayout.this;
            simpleRefreshLayout.O0 = null;
            if (simpleRefreshLayout.d0 == 0) {
                com.jd.lib.un.basewidget.widget.simple.a.c cVar = SimpleRefreshLayout.this.C0;
                com.jd.lib.un.basewidget.widget.simple.a.c cVar2 = com.jd.lib.un.basewidget.widget.simple.a.c.None;
                if (cVar == cVar2 || SimpleRefreshLayout.this.C0.isOpening) {
                    return;
                }
                SimpleRefreshLayout.this.q0(cVar2);
            } else if (SimpleRefreshLayout.this.C0 != SimpleRefreshLayout.this.D0) {
                SimpleRefreshLayout simpleRefreshLayout2 = SimpleRefreshLayout.this;
                simpleRefreshLayout2.B0(simpleRefreshLayout2.C0);
            }
        }
    }

    /* loaded from: classes16.dex */
    public class f implements ValueAnimator.AnimatorUpdateListener {
        f() {
            SimpleRefreshLayout.this = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            SimpleRefreshLayout.this.A0.b(((Integer) valueAnimator.getAnimatedValue()).intValue(), false);
        }
    }

    /* loaded from: classes16.dex */
    public class g implements Runnable {

        /* renamed from: g */
        final /* synthetic */ boolean f5750g;

        g(boolean z) {
            SimpleRefreshLayout.this = r1;
            this.f5750g = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (SimpleRefreshLayout.this.C0 != com.jd.lib.un.basewidget.widget.simple.a.c.Refreshing || SimpleRefreshLayout.this.v0 == null || SimpleRefreshLayout.this.x0 == null) {
                return;
            }
            SimpleRefreshLayout.this.q0(com.jd.lib.un.basewidget.widget.simple.a.c.RefreshFinish);
            int q = SimpleRefreshLayout.this.v0.q(SimpleRefreshLayout.this, this.f5750g);
            if (SimpleRefreshLayout.this.q0 != null && (SimpleRefreshLayout.this.v0 instanceof com.jd.lib.un.basewidget.widget.simple.c.c)) {
                SimpleRefreshLayout.this.q0.n((com.jd.lib.un.basewidget.widget.simple.c.c) SimpleRefreshLayout.this.v0, this.f5750g);
            }
            if (q < Integer.MAX_VALUE) {
                if (SimpleRefreshLayout.this.k0 || SimpleRefreshLayout.this.t0) {
                    if (SimpleRefreshLayout.this.k0) {
                        SimpleRefreshLayout simpleRefreshLayout = SimpleRefreshLayout.this;
                        simpleRefreshLayout.h0 = simpleRefreshLayout.j0;
                        SimpleRefreshLayout.this.f0 = 0;
                        SimpleRefreshLayout.this.k0 = false;
                    }
                    long currentTimeMillis = System.currentTimeMillis();
                    SimpleRefreshLayout simpleRefreshLayout2 = SimpleRefreshLayout.this;
                    SimpleRefreshLayout.super.dispatchTouchEvent(MotionEvent.obtain(currentTimeMillis, currentTimeMillis, 0, simpleRefreshLayout2.i0, (SimpleRefreshLayout.this.j0 + SimpleRefreshLayout.this.d0) - (SimpleRefreshLayout.this.f5741k * 2), 0));
                    SimpleRefreshLayout simpleRefreshLayout3 = SimpleRefreshLayout.this;
                    SimpleRefreshLayout.super.dispatchTouchEvent(MotionEvent.obtain(currentTimeMillis, currentTimeMillis, 2, simpleRefreshLayout3.i0, SimpleRefreshLayout.this.j0 + SimpleRefreshLayout.this.d0, 0));
                    if (SimpleRefreshLayout.this.t0) {
                        SimpleRefreshLayout.this.s0 = 0;
                    }
                }
                if (SimpleRefreshLayout.this.d0 <= 0) {
                    if (SimpleRefreshLayout.this.d0 >= 0) {
                        SimpleRefreshLayout.this.A0.b(0, false);
                        SimpleRefreshLayout.this.s0();
                        return;
                    }
                    SimpleRefreshLayout simpleRefreshLayout4 = SimpleRefreshLayout.this;
                    simpleRefreshLayout4.b0(0, q, simpleRefreshLayout4.o, SimpleRefreshLayout.this.z);
                    return;
                }
                SimpleRefreshLayout simpleRefreshLayout5 = SimpleRefreshLayout.this;
                ValueAnimator b0 = simpleRefreshLayout5.b0(0, q, simpleRefreshLayout5.o, SimpleRefreshLayout.this.z);
                ValueAnimator.AnimatorUpdateListener c2 = SimpleRefreshLayout.this.N ? SimpleRefreshLayout.this.x0.c(SimpleRefreshLayout.this.d0) : null;
                if (b0 == null || c2 == null) {
                    return;
                }
                b0.addUpdateListener(c2);
            }
        }
    }

    /* loaded from: classes16.dex */
    public class h implements Runnable {

        /* renamed from: g */
        final /* synthetic */ boolean f5752g;

        /* renamed from: h */
        final /* synthetic */ boolean f5753h;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes16.dex */
        public class a implements Runnable {

            /* renamed from: g */
            final /* synthetic */ int f5755g;

            /* renamed from: com.jd.lib.un.basewidget.widget.simple.SimpleRefreshLayout$h$a$a */
            /* loaded from: classes16.dex */
            class C0166a extends AnimatorListenerAdapter {
                C0166a() {
                    a.this = r1;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                    super.onAnimationEnd(animator);
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    SimpleRefreshLayout.this.J0 = false;
                    h hVar = h.this;
                    if (hVar.f5753h) {
                        SimpleRefreshLayout.this.t0(true);
                    }
                    if (SimpleRefreshLayout.this.C0 == com.jd.lib.un.basewidget.widget.simple.a.c.LoadFinish) {
                        SimpleRefreshLayout.this.q0(com.jd.lib.un.basewidget.widget.simple.a.c.None);
                    }
                }
            }

            a(int i2) {
                h.this = r1;
                this.f5755g = i2;
            }

            @Override // java.lang.Runnable
            public void run() {
                ValueAnimator valueAnimator;
                ValueAnimator.AnimatorUpdateListener c2 = (!SimpleRefreshLayout.this.M || this.f5755g >= 0) ? null : SimpleRefreshLayout.this.x0.c(SimpleRefreshLayout.this.d0);
                if (c2 != null) {
                    c2.onAnimationUpdate(ValueAnimator.ofInt(0, 0));
                }
                C0166a c0166a = new C0166a();
                if (SimpleRefreshLayout.this.d0 > 0) {
                    valueAnimator = SimpleRefreshLayout.this.A0.a(0);
                } else {
                    if (c2 == null && SimpleRefreshLayout.this.d0 != 0) {
                        h hVar = h.this;
                        if (!hVar.f5753h || !SimpleRefreshLayout.this.H) {
                            valueAnimator = SimpleRefreshLayout.this.A0.a(0);
                        } else if (SimpleRefreshLayout.this.d0 < (-SimpleRefreshLayout.this.f5739i)) {
                            valueAnimator = SimpleRefreshLayout.this.A0.a(-SimpleRefreshLayout.this.f5739i);
                        } else {
                            SimpleRefreshLayout.this.q0(com.jd.lib.un.basewidget.widget.simple.a.c.None);
                        }
                    } else {
                        ValueAnimator valueAnimator2 = SimpleRefreshLayout.this.O0;
                        if (valueAnimator2 != null) {
                            valueAnimator2.cancel();
                            SimpleRefreshLayout.this.O0 = null;
                        }
                        SimpleRefreshLayout.this.A0.b(0, false);
                        SimpleRefreshLayout.this.s0();
                    }
                    valueAnimator = null;
                }
                if (valueAnimator != null) {
                    valueAnimator.addListener(c0166a);
                } else {
                    c0166a.onAnimationEnd(null);
                }
            }
        }

        h(boolean z, boolean z2) {
            SimpleRefreshLayout.this = r1;
            this.f5752g = z;
            this.f5753h = z2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (SimpleRefreshLayout.this.C0 == com.jd.lib.un.basewidget.widget.simple.a.c.Loading && SimpleRefreshLayout.this.w0 != null && SimpleRefreshLayout.this.x0 != null) {
                SimpleRefreshLayout.this.q0(com.jd.lib.un.basewidget.widget.simple.a.c.LoadFinish);
                int q = SimpleRefreshLayout.this.w0.q(SimpleRefreshLayout.this, this.f5752g);
                if (SimpleRefreshLayout.this.q0 != null && (SimpleRefreshLayout.this.w0 instanceof com.jd.lib.un.basewidget.widget.simple.c.b)) {
                    SimpleRefreshLayout.this.q0.p((com.jd.lib.un.basewidget.widget.simple.c.b) SimpleRefreshLayout.this.w0, this.f5752g);
                }
                if (q < Integer.MAX_VALUE) {
                    int max = SimpleRefreshLayout.this.d0 - (this.f5753h && SimpleRefreshLayout.this.H && SimpleRefreshLayout.this.d0 < 0 && SimpleRefreshLayout.this.x0.i() ? Math.max(SimpleRefreshLayout.this.d0, -SimpleRefreshLayout.this.f5739i) : 0);
                    if (SimpleRefreshLayout.this.k0 || SimpleRefreshLayout.this.t0) {
                        if (SimpleRefreshLayout.this.k0) {
                            SimpleRefreshLayout simpleRefreshLayout = SimpleRefreshLayout.this;
                            simpleRefreshLayout.h0 = simpleRefreshLayout.j0;
                            SimpleRefreshLayout.this.k0 = false;
                            SimpleRefreshLayout simpleRefreshLayout2 = SimpleRefreshLayout.this;
                            simpleRefreshLayout2.f0 = simpleRefreshLayout2.d0 - max;
                        }
                        long currentTimeMillis = System.currentTimeMillis();
                        SimpleRefreshLayout simpleRefreshLayout3 = SimpleRefreshLayout.this;
                        float f2 = max;
                        SimpleRefreshLayout.super.dispatchTouchEvent(MotionEvent.obtain(currentTimeMillis, currentTimeMillis, 0, simpleRefreshLayout3.i0, SimpleRefreshLayout.this.j0 + f2 + (SimpleRefreshLayout.this.f5741k * 2), 0));
                        SimpleRefreshLayout simpleRefreshLayout4 = SimpleRefreshLayout.this;
                        SimpleRefreshLayout.super.dispatchTouchEvent(MotionEvent.obtain(currentTimeMillis, currentTimeMillis, 2, simpleRefreshLayout4.i0, SimpleRefreshLayout.this.j0 + f2, 0));
                        if (SimpleRefreshLayout.this.t0) {
                            SimpleRefreshLayout.this.s0 = 0;
                        }
                    }
                    SimpleRefreshLayout simpleRefreshLayout5 = SimpleRefreshLayout.this;
                    simpleRefreshLayout5.postDelayed(new a(max), simpleRefreshLayout5.d0 < 0 ? q : 0L);
                }
            } else if (this.f5753h) {
                SimpleRefreshLayout.this.t0(true);
            }
        }
    }

    /* loaded from: classes16.dex */
    public class i implements Runnable {

        /* renamed from: i */
        int f5760i;

        /* renamed from: l */
        float f5763l;

        /* renamed from: g */
        int f5758g = 0;

        /* renamed from: h */
        int f5759h = 10;

        /* renamed from: k */
        float f5762k = 0.0f;

        /* renamed from: j */
        long f5761j = AnimationUtils.currentAnimationTimeMillis();

        i(float f2, int i2) {
            SimpleRefreshLayout.this = r2;
            this.f5763l = f2;
            this.f5760i = i2;
            r2.postDelayed(this, this.f5759h);
        }

        @Override // java.lang.Runnable
        public void run() {
            SimpleRefreshLayout simpleRefreshLayout = SimpleRefreshLayout.this;
            if (simpleRefreshLayout.N0 != this || simpleRefreshLayout.C0.isFinishing) {
                return;
            }
            if (Math.abs(SimpleRefreshLayout.this.d0) >= Math.abs(this.f5760i)) {
                if (this.f5760i != 0) {
                    double d = this.f5763l;
                    this.f5758g = this.f5758g + 1;
                    double pow = Math.pow(0.44999998807907104d, r4 * 2);
                    Double.isNaN(d);
                    this.f5763l = (float) (d * pow);
                } else {
                    double d2 = this.f5763l;
                    this.f5758g = this.f5758g + 1;
                    double pow2 = Math.pow(0.8500000238418579d, r4 * 2);
                    Double.isNaN(d2);
                    this.f5763l = (float) (d2 * pow2);
                }
            } else {
                double d3 = this.f5763l;
                this.f5758g = this.f5758g + 1;
                double pow3 = Math.pow(0.949999988079071d, r4 * 2);
                Double.isNaN(d3);
                this.f5763l = (float) (d3 * pow3);
            }
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            float f2 = this.f5763l * ((((float) (currentAnimationTimeMillis - this.f5761j)) * 1.0f) / 1000.0f);
            if (Math.abs(f2) >= 1.0f) {
                this.f5761j = currentAnimationTimeMillis;
                float f3 = this.f5762k + f2;
                this.f5762k = f3;
                SimpleRefreshLayout.this.p0(f3);
                SimpleRefreshLayout.this.postDelayed(this, this.f5759h);
                return;
            }
            SimpleRefreshLayout simpleRefreshLayout2 = SimpleRefreshLayout.this;
            simpleRefreshLayout2.N0 = null;
            if (Math.abs(simpleRefreshLayout2.d0) >= Math.abs(this.f5760i)) {
                SimpleRefreshLayout simpleRefreshLayout3 = SimpleRefreshLayout.this;
                simpleRefreshLayout3.b0(this.f5760i, 0, simpleRefreshLayout3.o, Math.min(Math.max(com.jd.lib.un.basewidget.widget.simple.e.a.b(Math.abs(SimpleRefreshLayout.this.d0 - this.f5760i)), 30), 100) * 10);
            }
        }
    }

    /* loaded from: classes16.dex */
    public class j implements Runnable {

        /* renamed from: g */
        int f5765g;

        /* renamed from: i */
        float f5767i;

        /* renamed from: h */
        int f5766h = 10;

        /* renamed from: j */
        float f5768j = 0.98f;

        /* renamed from: k */
        long f5769k = 0;

        /* renamed from: l */
        long f5770l = AnimationUtils.currentAnimationTimeMillis();

        j(float f2) {
            SimpleRefreshLayout.this = r3;
            this.f5767i = f2;
            this.f5765g = r3.d0;
        }

        /* JADX WARN: Code restructure failed: missing block: B:64:0x0038, code lost:
            if (r0.n0(r0.y) != false) goto L65;
         */
        /* JADX WARN: Code restructure failed: missing block: B:72:0x005e, code lost:
            if (r0.n0(r0.y) != false) goto L73;
         */
        /* JADX WARN: Code restructure failed: missing block: B:74:0x006d, code lost:
            if (com.jd.lib.un.basewidget.widget.simple.SimpleRefreshLayout.this.d0 >= (-com.jd.lib.un.basewidget.widget.simple.SimpleRefreshLayout.this.f5739i)) goto L75;
         */
        /* JADX WARN: Code restructure failed: missing block: B:78:0x0085, code lost:
            if (com.jd.lib.un.basewidget.widget.simple.SimpleRefreshLayout.this.d0 > com.jd.lib.un.basewidget.widget.simple.SimpleRefreshLayout.this.f5737g) goto L79;
         */
        /* JADX WARN: Code restructure failed: missing block: B:89:0x00df, code lost:
            if (r2 > com.jd.lib.un.basewidget.widget.simple.SimpleRefreshLayout.this.f5737g) goto L94;
         */
        /* JADX WARN: Code restructure failed: missing block: B:93:0x00f0, code lost:
            if (r2 < (-com.jd.lib.un.basewidget.widget.simple.SimpleRefreshLayout.this.f5739i)) goto L94;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Runnable b() {
            /*
                Method dump skipped, instructions count: 262
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.un.basewidget.widget.simple.SimpleRefreshLayout.j.b():java.lang.Runnable");
        }

        @Override // java.lang.Runnable
        public void run() {
            SimpleRefreshLayout simpleRefreshLayout = SimpleRefreshLayout.this;
            if (simpleRefreshLayout.N0 != this || simpleRefreshLayout.C0.isFinishing) {
                return;
            }
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            double d = this.f5767i;
            double pow = Math.pow(this.f5768j, (currentAnimationTimeMillis - this.f5769k) / (1000 / this.f5766h));
            Double.isNaN(d);
            float f2 = (float) (d * pow);
            this.f5767i = f2;
            float f3 = f2 * ((((float) (currentAnimationTimeMillis - this.f5770l)) * 1.0f) / 1000.0f);
            if (Math.abs(f3) > 1.0f) {
                this.f5770l = currentAnimationTimeMillis;
                this.f5765g = (int) (this.f5765g + f3);
                if (SimpleRefreshLayout.this.d0 * this.f5765g > 0) {
                    SimpleRefreshLayout.this.A0.b(this.f5765g, true);
                    SimpleRefreshLayout.this.postDelayed(this, this.f5766h);
                    return;
                }
                SimpleRefreshLayout simpleRefreshLayout2 = SimpleRefreshLayout.this;
                simpleRefreshLayout2.N0 = null;
                simpleRefreshLayout2.A0.b(0, true);
                com.jd.lib.un.basewidget.widget.simple.e.b.b(SimpleRefreshLayout.this.x0.d(), (int) (-this.f5767i));
                if (!SimpleRefreshLayout.this.J0 || f3 <= 0.0f) {
                    return;
                }
                SimpleRefreshLayout.this.J0 = false;
                return;
            }
            SimpleRefreshLayout.this.N0 = null;
        }
    }

    /* loaded from: classes16.dex */
    public class k implements com.jd.lib.un.basewidget.widget.simple.c.e {
        public k() {
            SimpleRefreshLayout.this = r1;
        }

        @Override // com.jd.lib.un.basewidget.widget.simple.c.e
        public ValueAnimator a(int i2) {
            SimpleRefreshLayout simpleRefreshLayout = SimpleRefreshLayout.this;
            return simpleRefreshLayout.b0(i2, 0, simpleRefreshLayout.o, SimpleRefreshLayout.this.z);
        }

        @Override // com.jd.lib.un.basewidget.widget.simple.c.e
        @SuppressLint({"RestrictedApi"})
        public com.jd.lib.un.basewidget.widget.simple.c.e b(int i2, boolean z) {
            if (SimpleRefreshLayout.this.d0 == i2 && SimpleRefreshLayout.this.v0 == null && SimpleRefreshLayout.this.w0 == null) {
                return this;
            }
            SimpleRefreshLayout simpleRefreshLayout = SimpleRefreshLayout.this;
            int i3 = simpleRefreshLayout.d0;
            SimpleRefreshLayout.this.d0 = i2;
            if (z && SimpleRefreshLayout.this.D0.isDragging) {
                if (SimpleRefreshLayout.this.d0 > SimpleRefreshLayout.this.f5737g * SimpleRefreshLayout.this.v) {
                    SimpleRefreshLayout.this.A0.d(com.jd.lib.un.basewidget.widget.simple.a.c.ReleaseToRefresh);
                } else if ((-SimpleRefreshLayout.this.d0) <= SimpleRefreshLayout.this.f5739i * SimpleRefreshLayout.this.w || SimpleRefreshLayout.this.R) {
                    if (SimpleRefreshLayout.this.d0 >= 0 || SimpleRefreshLayout.this.R) {
                        if (SimpleRefreshLayout.this.d0 > 0) {
                            SimpleRefreshLayout.this.A0.d(com.jd.lib.un.basewidget.widget.simple.a.c.PullDownToRefresh);
                        }
                    } else {
                        SimpleRefreshLayout.this.A0.d(com.jd.lib.un.basewidget.widget.simple.a.c.PullUpToLoad);
                    }
                } else {
                    SimpleRefreshLayout.this.A0.d(com.jd.lib.un.basewidget.widget.simple.a.c.ReleaseToLoad);
                }
            }
            if (SimpleRefreshLayout.this.x0 != null) {
                Integer num = null;
                if (i2 >= 0 && SimpleRefreshLayout.this.v0 != null) {
                    SimpleRefreshLayout simpleRefreshLayout2 = SimpleRefreshLayout.this;
                    if (simpleRefreshLayout2.o0(simpleRefreshLayout2.F, SimpleRefreshLayout.this.v0)) {
                        num = Integer.valueOf(i2);
                    } else if (i3 < 0) {
                        num = 0;
                    }
                }
                if (i2 <= 0 && SimpleRefreshLayout.this.w0 != null) {
                    SimpleRefreshLayout simpleRefreshLayout3 = SimpleRefreshLayout.this;
                    if (simpleRefreshLayout3.o0(simpleRefreshLayout3.G, SimpleRefreshLayout.this.w0)) {
                        num = Integer.valueOf(i2);
                    } else if (i3 > 0) {
                        num = 0;
                    }
                }
                if (num != null) {
                    SimpleRefreshLayout.this.x0.g(num.intValue(), SimpleRefreshLayout.this.b0, SimpleRefreshLayout.this.c0);
                    boolean z2 = (SimpleRefreshLayout.this.D && SimpleRefreshLayout.this.v0 != null && SimpleRefreshLayout.this.v0.i() == com.jd.lib.un.basewidget.widget.simple.a.b.FIXED_BEHIND) || SimpleRefreshLayout.this.F0 != 0;
                    boolean z3 = (SimpleRefreshLayout.this.E && SimpleRefreshLayout.this.w0 != null && SimpleRefreshLayout.this.w0.i() == com.jd.lib.un.basewidget.widget.simple.a.b.FIXED_BEHIND) || SimpleRefreshLayout.this.G0 != 0;
                    if ((z2 && (num.intValue() >= 0 || i3 > 0)) || (z3 && (num.intValue() <= 0 || i3 < 0))) {
                        simpleRefreshLayout.invalidate();
                    }
                }
            }
            if ((i2 >= 0 || i3 > 0) && SimpleRefreshLayout.this.v0 != null) {
                int max = Math.max(i2, 0);
                int i4 = SimpleRefreshLayout.this.f5737g;
                int i5 = (int) (SimpleRefreshLayout.this.f5737g * SimpleRefreshLayout.this.t);
                float f2 = (max * 1.0f) / (SimpleRefreshLayout.this.f5737g == 0 ? 1 : SimpleRefreshLayout.this.f5737g);
                SimpleRefreshLayout simpleRefreshLayout4 = SimpleRefreshLayout.this;
                if ((simpleRefreshLayout4.n0(simpleRefreshLayout4.x) || (SimpleRefreshLayout.this.C0 == com.jd.lib.un.basewidget.widget.simple.a.c.RefreshFinish && !z)) && i3 != SimpleRefreshLayout.this.d0) {
                    if (SimpleRefreshLayout.this.v0.i() == com.jd.lib.un.basewidget.widget.simple.a.b.TRANSLATE) {
                        SimpleRefreshLayout.this.v0.getView().setTranslationY(SimpleRefreshLayout.this.d0);
                        if (SimpleRefreshLayout.this.F0 != 0 && SimpleRefreshLayout.this.y0 != null) {
                            SimpleRefreshLayout simpleRefreshLayout5 = SimpleRefreshLayout.this;
                            if (!simpleRefreshLayout5.o0(simpleRefreshLayout5.F, SimpleRefreshLayout.this.v0)) {
                                simpleRefreshLayout.invalidate();
                            }
                        }
                    } else if (SimpleRefreshLayout.this.v0.i() == com.jd.lib.un.basewidget.widget.simple.a.b.SCALE) {
                        SimpleRefreshLayout.this.v0.getView().requestLayout();
                    }
                    SimpleRefreshLayout.this.v0.o(z, f2, max, i4, i5);
                }
                if (i3 != SimpleRefreshLayout.this.d0 && SimpleRefreshLayout.this.q0 != null && (SimpleRefreshLayout.this.v0 instanceof com.jd.lib.un.basewidget.widget.simple.c.c)) {
                    SimpleRefreshLayout.this.q0.b((com.jd.lib.un.basewidget.widget.simple.c.c) SimpleRefreshLayout.this.v0, z, f2, max, i4, i5);
                }
            }
            if ((i2 <= 0 || i3 < 0) && SimpleRefreshLayout.this.w0 != null) {
                int i6 = -Math.min(i2, 0);
                int i7 = SimpleRefreshLayout.this.f5739i;
                int i8 = (int) (SimpleRefreshLayout.this.f5739i * SimpleRefreshLayout.this.u);
                float f3 = (i6 * 1.0f) / (SimpleRefreshLayout.this.f5739i != 0 ? SimpleRefreshLayout.this.f5739i : 1);
                SimpleRefreshLayout simpleRefreshLayout6 = SimpleRefreshLayout.this;
                if ((simpleRefreshLayout6.n0(simpleRefreshLayout6.y) || (SimpleRefreshLayout.this.C0 == com.jd.lib.un.basewidget.widget.simple.a.c.LoadFinish && !z)) && i3 != SimpleRefreshLayout.this.d0) {
                    if (SimpleRefreshLayout.this.w0.i() == com.jd.lib.un.basewidget.widget.simple.a.b.TRANSLATE) {
                        SimpleRefreshLayout.this.w0.getView().setTranslationY(SimpleRefreshLayout.this.d0);
                        if (SimpleRefreshLayout.this.G0 != 0 && SimpleRefreshLayout.this.y0 != null) {
                            SimpleRefreshLayout simpleRefreshLayout7 = SimpleRefreshLayout.this;
                            if (!simpleRefreshLayout7.o0(simpleRefreshLayout7.G, SimpleRefreshLayout.this.w0)) {
                                simpleRefreshLayout.invalidate();
                            }
                        }
                    } else if (SimpleRefreshLayout.this.w0.i() == com.jd.lib.un.basewidget.widget.simple.a.b.SCALE) {
                        SimpleRefreshLayout.this.w0.getView().requestLayout();
                    }
                    SimpleRefreshLayout.this.w0.o(z, f3, i6, i7, i8);
                }
                if (i3 != SimpleRefreshLayout.this.d0 && SimpleRefreshLayout.this.q0 != null && (SimpleRefreshLayout.this.w0 instanceof com.jd.lib.un.basewidget.widget.simple.c.b)) {
                    SimpleRefreshLayout.this.q0.k((com.jd.lib.un.basewidget.widget.simple.c.b) SimpleRefreshLayout.this.w0, z, f3, i6, i7, i8);
                }
            }
            return this;
        }

        @Override // com.jd.lib.un.basewidget.widget.simple.c.e
        @NonNull
        public com.jd.lib.un.basewidget.widget.simple.c.f c() {
            return SimpleRefreshLayout.this;
        }

        @Override // com.jd.lib.un.basewidget.widget.simple.c.e
        public com.jd.lib.un.basewidget.widget.simple.c.e d(@NonNull com.jd.lib.un.basewidget.widget.simple.a.c cVar) {
            switch (a.a[cVar.ordinal()]) {
                case 1:
                    SimpleRefreshLayout.this.s0();
                    return null;
                case 2:
                    if (!SimpleRefreshLayout.this.C0.isOpening) {
                        SimpleRefreshLayout simpleRefreshLayout = SimpleRefreshLayout.this;
                        if (simpleRefreshLayout.n0(simpleRefreshLayout.x)) {
                            SimpleRefreshLayout.this.q0(com.jd.lib.un.basewidget.widget.simple.a.c.PullDownToRefresh);
                            return null;
                        }
                    }
                    SimpleRefreshLayout.this.B0(com.jd.lib.un.basewidget.widget.simple.a.c.PullDownToRefresh);
                    return null;
                case 3:
                    SimpleRefreshLayout simpleRefreshLayout2 = SimpleRefreshLayout.this;
                    if (simpleRefreshLayout2.n0(simpleRefreshLayout2.y) && !SimpleRefreshLayout.this.C0.isOpening && !SimpleRefreshLayout.this.C0.isFinishing && (!SimpleRefreshLayout.this.R || !SimpleRefreshLayout.this.H)) {
                        SimpleRefreshLayout.this.q0(com.jd.lib.un.basewidget.widget.simple.a.c.PullUpToLoad);
                        return null;
                    }
                    SimpleRefreshLayout.this.B0(com.jd.lib.un.basewidget.widget.simple.a.c.PullUpToLoad);
                    return null;
                case 4:
                    if (!SimpleRefreshLayout.this.C0.isOpening) {
                        SimpleRefreshLayout simpleRefreshLayout3 = SimpleRefreshLayout.this;
                        if (simpleRefreshLayout3.n0(simpleRefreshLayout3.x)) {
                            SimpleRefreshLayout.this.q0(com.jd.lib.un.basewidget.widget.simple.a.c.PullDownCanceled);
                            SimpleRefreshLayout.this.s0();
                            return null;
                        }
                    }
                    SimpleRefreshLayout.this.B0(com.jd.lib.un.basewidget.widget.simple.a.c.PullDownCanceled);
                    return null;
                case 5:
                    SimpleRefreshLayout simpleRefreshLayout4 = SimpleRefreshLayout.this;
                    if (simpleRefreshLayout4.n0(simpleRefreshLayout4.y) && !SimpleRefreshLayout.this.C0.isOpening && (!SimpleRefreshLayout.this.R || !SimpleRefreshLayout.this.H)) {
                        SimpleRefreshLayout.this.q0(com.jd.lib.un.basewidget.widget.simple.a.c.PullUpCanceled);
                        SimpleRefreshLayout.this.s0();
                        return null;
                    }
                    SimpleRefreshLayout.this.B0(com.jd.lib.un.basewidget.widget.simple.a.c.PullUpCanceled);
                    return null;
                case 6:
                    if (!SimpleRefreshLayout.this.C0.isOpening) {
                        SimpleRefreshLayout simpleRefreshLayout5 = SimpleRefreshLayout.this;
                        if (simpleRefreshLayout5.n0(simpleRefreshLayout5.x)) {
                            SimpleRefreshLayout.this.q0(com.jd.lib.un.basewidget.widget.simple.a.c.ReleaseToRefresh);
                            return null;
                        }
                    }
                    SimpleRefreshLayout.this.B0(com.jd.lib.un.basewidget.widget.simple.a.c.ReleaseToRefresh);
                    return null;
                case 7:
                    SimpleRefreshLayout simpleRefreshLayout6 = SimpleRefreshLayout.this;
                    if (simpleRefreshLayout6.n0(simpleRefreshLayout6.y) && !SimpleRefreshLayout.this.C0.isOpening && !SimpleRefreshLayout.this.C0.isFinishing && (!SimpleRefreshLayout.this.R || !SimpleRefreshLayout.this.H)) {
                        SimpleRefreshLayout.this.q0(com.jd.lib.un.basewidget.widget.simple.a.c.ReleaseToLoad);
                        return null;
                    }
                    SimpleRefreshLayout.this.B0(com.jd.lib.un.basewidget.widget.simple.a.c.ReleaseToLoad);
                    return null;
                case 8:
                    if (!SimpleRefreshLayout.this.C0.isOpening) {
                        SimpleRefreshLayout simpleRefreshLayout7 = SimpleRefreshLayout.this;
                        if (simpleRefreshLayout7.n0(simpleRefreshLayout7.x)) {
                            SimpleRefreshLayout.this.q0(com.jd.lib.un.basewidget.widget.simple.a.c.RefreshReleased);
                            return null;
                        }
                    }
                    SimpleRefreshLayout.this.B0(com.jd.lib.un.basewidget.widget.simple.a.c.RefreshReleased);
                    return null;
                case 9:
                    if (!SimpleRefreshLayout.this.C0.isOpening) {
                        SimpleRefreshLayout simpleRefreshLayout8 = SimpleRefreshLayout.this;
                        if (simpleRefreshLayout8.n0(simpleRefreshLayout8.y)) {
                            SimpleRefreshLayout.this.q0(com.jd.lib.un.basewidget.widget.simple.a.c.LoadReleased);
                            return null;
                        }
                    }
                    SimpleRefreshLayout.this.B0(com.jd.lib.un.basewidget.widget.simple.a.c.LoadReleased);
                    return null;
                case 10:
                    SimpleRefreshLayout.this.A0();
                    return null;
                case 11:
                    SimpleRefreshLayout.this.z0();
                    return null;
                case 12:
                    if (SimpleRefreshLayout.this.C0 == com.jd.lib.un.basewidget.widget.simple.a.c.Refreshing) {
                        SimpleRefreshLayout.this.q0(com.jd.lib.un.basewidget.widget.simple.a.c.RefreshFinish);
                        return null;
                    }
                    return null;
                case 13:
                    if (SimpleRefreshLayout.this.C0 == com.jd.lib.un.basewidget.widget.simple.a.c.Loading) {
                        SimpleRefreshLayout.this.q0(com.jd.lib.un.basewidget.widget.simple.a.c.LoadFinish);
                        return null;
                    }
                    return null;
                default:
                    return null;
            }
        }

        @Override // com.jd.lib.un.basewidget.widget.simple.c.e
        public com.jd.lib.un.basewidget.widget.simple.c.e e(@NonNull com.jd.lib.un.basewidget.widget.simple.c.d dVar, int i2) {
            if (SimpleRefreshLayout.this.y0 == null && i2 != 0) {
                SimpleRefreshLayout.this.y0 = new Paint();
            }
            if (dVar.equals(SimpleRefreshLayout.this.v0)) {
                SimpleRefreshLayout.this.F0 = i2;
            } else if (dVar.equals(SimpleRefreshLayout.this.w0)) {
                SimpleRefreshLayout.this.G0 = i2;
            }
            return this;
        }
    }

    public SimpleRefreshLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void l0(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SimpleRefreshLayout);
        NestedScrollingChildHelper nestedScrollingChildHelper = this.q;
        int i2 = R.styleable.SimpleRefreshLayout_SimpleEnableNestedScrolling;
        nestedScrollingChildHelper.setNestedScrollingEnabled(obtainStyledAttributes.getBoolean(i2, nestedScrollingChildHelper.isNestedScrollingEnabled()));
        this.s = obtainStyledAttributes.getFloat(R.styleable.SimpleRefreshLayout_SimpleDragRate, this.s);
        this.t = obtainStyledAttributes.getFloat(R.styleable.SimpleRefreshLayout_SimpleHeaderMaxDragRate, this.t);
        this.u = obtainStyledAttributes.getFloat(R.styleable.SimpleRefreshLayout_SimpleFooterMaxDragRate, this.u);
        this.v = obtainStyledAttributes.getFloat(R.styleable.SimpleRefreshLayout_SimpleHeaderTriggerRate, this.v);
        this.w = obtainStyledAttributes.getFloat(R.styleable.SimpleRefreshLayout_SimpleFooterTriggerRate, this.w);
        this.x = obtainStyledAttributes.getBoolean(R.styleable.SimpleRefreshLayout_SimpleEnableRefresh, this.x);
        this.z = obtainStyledAttributes.getInt(R.styleable.SimpleRefreshLayout_SimpleReboundDuration, this.z);
        int i3 = R.styleable.SimpleRefreshLayout_SimpleEnableLoadMore;
        this.y = obtainStyledAttributes.getBoolean(i3, this.y);
        int i4 = R.styleable.SimpleRefreshLayout_SimpleHeaderHeight;
        this.f5737g = obtainStyledAttributes.getDimensionPixelOffset(i4, this.f5737g);
        int i5 = R.styleable.SimpleRefreshLayout_SimpleFooterHeight;
        this.f5739i = obtainStyledAttributes.getDimensionPixelOffset(i5, this.f5739i);
        this.A = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.SimpleRefreshLayout_SimpleHeaderInsetStart, this.A);
        this.B = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.SimpleRefreshLayout_SimpleFooterInsetStart, this.B);
        this.P = obtainStyledAttributes.getBoolean(R.styleable.SimpleRefreshLayout_SimpleDisableContentWhenRefresh, this.P);
        this.Q = obtainStyledAttributes.getBoolean(R.styleable.SimpleRefreshLayout_SimpleDisableContentWhenLoading, this.Q);
        int i6 = R.styleable.SimpleRefreshLayout_SimpleEnableHeaderTranslationContent;
        this.F = obtainStyledAttributes.getBoolean(i6, this.F);
        int i7 = R.styleable.SimpleRefreshLayout_SimpleEnableFooterTranslationContent;
        this.G = obtainStyledAttributes.getBoolean(i7, this.G);
        this.K = obtainStyledAttributes.getBoolean(R.styleable.SimpleRefreshLayout_SimpleEnableAutoLoadMore, this.K);
        this.I = obtainStyledAttributes.getBoolean(R.styleable.SimpleRefreshLayout_SimpleEnableOverScrollBounce, this.I);
        this.L = obtainStyledAttributes.getBoolean(R.styleable.SimpleRefreshLayout_SimpleEnablePureScrollMode, this.L);
        this.M = obtainStyledAttributes.getBoolean(R.styleable.SimpleRefreshLayout_SimpleEnableScrollContentWhenLoaded, this.M);
        this.N = obtainStyledAttributes.getBoolean(R.styleable.SimpleRefreshLayout_SimpleEnableScrollContentWhenRefreshed, this.N);
        this.O = obtainStyledAttributes.getBoolean(R.styleable.SimpleRefreshLayout_SimpleEnableLoadMoreWhenContentNotFull, this.O);
        this.H = obtainStyledAttributes.getBoolean(R.styleable.SimpleRefreshLayout_SimpleEnableFooterFollowWhenLoadFinished, this.H);
        this.D = obtainStyledAttributes.getBoolean(R.styleable.SimpleRefreshLayout_SimpleEnableClipHeaderWhenFixedBehind, this.D);
        this.E = obtainStyledAttributes.getBoolean(R.styleable.SimpleRefreshLayout_SimpleEnableClipFooterWhenFixedBehind, this.E);
        int i8 = R.styleable.SimpleRefreshLayout_SimpleEnableOverScrollDrag;
        this.J = obtainStyledAttributes.getBoolean(i8, this.J);
        this.W = obtainStyledAttributes.getResourceId(R.styleable.SimpleRefreshLayout_SimpleFixedHeaderViewId, this.W);
        this.a0 = obtainStyledAttributes.getResourceId(R.styleable.SimpleRefreshLayout_SimpleFixedFooterViewId, this.a0);
        this.b0 = obtainStyledAttributes.getResourceId(R.styleable.SimpleRefreshLayout_SimpleHeaderTranslationViewId, this.b0);
        this.c0 = obtainStyledAttributes.getResourceId(R.styleable.SimpleRefreshLayout_SimpleFooterTranslationViewId, this.c0);
        if (this.L && !obtainStyledAttributes.hasValue(i8)) {
            this.J = true;
        }
        this.S = this.S || obtainStyledAttributes.hasValue(i3);
        this.U = this.U || obtainStyledAttributes.hasValue(i6);
        this.V = this.V || obtainStyledAttributes.hasValue(i7);
        this.T = this.T || obtainStyledAttributes.hasValue(i2);
        this.f5738h = obtainStyledAttributes.hasValue(i4) ? com.jd.lib.un.basewidget.widget.simple.a.a.XmlLayoutUnNotify : this.f5738h;
        this.f5740j = obtainStyledAttributes.hasValue(i5) ? com.jd.lib.un.basewidget.widget.simple.a.a.XmlLayoutUnNotify : this.f5740j;
        int color = obtainStyledAttributes.getColor(R.styleable.SimpleRefreshLayout_SimpleAccentColor, 0);
        int color2 = obtainStyledAttributes.getColor(R.styleable.SimpleRefreshLayout_SimplePrimaryColor, 0);
        if (color2 != 0) {
            if (color != 0) {
                this.C = new int[]{color2, color};
            } else {
                this.C = new int[]{color2};
            }
        } else if (color != 0) {
            this.C = new int[]{0, color};
        }
        obtainStyledAttributes.recycle();
    }

    @SuppressLint({"RestrictedApi"})
    protected void A0() {
        d dVar = new d();
        q0(com.jd.lib.un.basewidget.widget.simple.a.c.RefreshReleased);
        ValueAnimator a2 = this.A0.a(this.f5737g);
        if (a2 != null) {
            a2.addListener(dVar);
        }
        com.jd.lib.un.basewidget.widget.simple.c.d dVar2 = this.v0;
        if (dVar2 != null) {
            int i2 = this.f5737g;
            dVar2.e(this, i2, (int) (this.t * i2));
        }
        com.jd.lib.un.basewidget.widget.simple.d.c cVar = this.q0;
        if (cVar != null) {
            com.jd.lib.un.basewidget.widget.simple.c.d dVar3 = this.v0;
            if (dVar3 instanceof com.jd.lib.un.basewidget.widget.simple.c.c) {
                int i3 = this.f5737g;
                cVar.g((com.jd.lib.un.basewidget.widget.simple.c.c) dVar3, i3, (int) (this.t * i3));
            }
        }
        if (a2 == null) {
            dVar.onAnimationEnd(null);
        }
    }

    protected void B0(com.jd.lib.un.basewidget.widget.simple.a.c cVar) {
        com.jd.lib.un.basewidget.widget.simple.a.c cVar2 = this.C0;
        if (cVar2.isDragging && cVar2.isHeader != cVar.isHeader) {
            q0(com.jd.lib.un.basewidget.widget.simple.a.c.None);
        }
        if (this.D0 != cVar) {
            this.D0 = cVar;
        }
    }

    protected boolean C0(Float f2) {
        float floatValue = f2 == null ? this.f5744n : f2.floatValue();
        if (Math.abs(floatValue) > this.f5742l) {
            int i2 = this.d0;
            if (i2 * floatValue < 0.0f) {
                com.jd.lib.un.basewidget.widget.simple.a.c cVar = this.C0;
                if (cVar.isOpening) {
                    if (cVar != this.D0) {
                        this.N0 = new j(floatValue).b();
                        return true;
                    }
                } else if (i2 > this.f5737g * this.v || (-i2) > this.f5739i * this.w) {
                    return true;
                }
            }
            if ((floatValue < 0.0f && ((this.I && (this.J || n0(this.y))) || ((this.C0 == com.jd.lib.un.basewidget.widget.simple.a.c.Loading && this.d0 >= 0) || (this.K && n0(this.y))))) || (floatValue > 0.0f && ((this.I && (this.J || n0(this.x))) || (this.C0 == com.jd.lib.un.basewidget.widget.simple.a.c.Refreshing && this.d0 <= 0)))) {
                this.L0 = false;
                this.m0.fling(0, 0, 0, (int) (-floatValue), 0, 0, -2147483647, Integer.MAX_VALUE);
                this.m0.computeScrollOffset();
                invalidate();
            }
        }
        return false;
    }

    protected ValueAnimator b0(int i2, int i3, Interpolator interpolator, int i4) {
        if (this.d0 != i2) {
            ValueAnimator valueAnimator = this.O0;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            this.N0 = null;
            ValueAnimator ofInt = ValueAnimator.ofInt(this.d0, i2);
            this.O0 = ofInt;
            ofInt.setDuration(i4);
            this.O0.setInterpolator(interpolator);
            this.O0.addListener(new e());
            this.O0.addUpdateListener(new f());
            this.O0.setStartDelay(i3);
            this.O0.start();
            return this.O0;
        }
        return null;
    }

    protected void c0(float f2) {
        if (this.O0 == null) {
            if (f2 > 0.0f && this.C0 == com.jd.lib.un.basewidget.widget.simple.a.c.Refreshing) {
                this.N0 = new i(f2, this.f5737g);
            } else if (f2 < 0.0f && (this.C0 == com.jd.lib.un.basewidget.widget.simple.a.c.Loading || ((this.H && this.R && n0(this.y)) || (this.K && !this.R && n0(this.y) && this.C0 != com.jd.lib.un.basewidget.widget.simple.a.c.Refreshing)))) {
                this.N0 = new i(f2, -this.f5739i);
            } else if (this.d0 == 0 && this.I) {
                this.N0 = new i(f2, 0);
            }
        }
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.view.View
    public void computeScroll() {
        float currY;
        if (this.m0.computeScrollOffset()) {
            int finalY = this.m0.getFinalY();
            if ((finalY < 0 && ((this.J || n0(this.x)) && this.x0.e())) || (finalY > 0 && ((this.J || n0(this.y)) && this.x0.i()))) {
                if (this.L0) {
                    if (Build.VERSION.SDK_INT >= 14) {
                        currY = finalY > 0 ? -this.m0.getCurrVelocity() : this.m0.getCurrVelocity();
                    } else {
                        currY = ((this.m0.getCurrY() - finalY) * 1.0f) / Math.max(this.m0.getDuration() - this.m0.timePassed(), 1);
                    }
                    c0(currY);
                }
                this.m0.forceFinished(true);
                return;
            }
            this.L0 = true;
            invalidate();
        }
    }

    public SimpleRefreshLayout d0(int i2) {
        e0(i2, true, false);
        return this;
    }

    /* JADX WARN: Code restructure failed: missing block: B:248:0x007f, code lost:
        if (r2.isFinishing == false) goto L251;
     */
    /* JADX WARN: Code restructure failed: missing block: B:250:0x0083, code lost:
        if (r2.isHeader == false) goto L251;
     */
    /* JADX WARN: Code restructure failed: missing block: B:256:0x0091, code lost:
        if (r2.isFinishing == false) goto L260;
     */
    /* JADX WARN: Code restructure failed: missing block: B:258:0x0095, code lost:
        if (r2.isFooter == false) goto L260;
     */
    /* JADX WARN: Code restructure failed: missing block: B:278:0x00c0, code lost:
        if (r6 != 3) goto L402;
     */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean dispatchTouchEvent(android.view.MotionEvent r23) {
        /*
            Method dump skipped, instructions count: 744
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.un.basewidget.widget.simple.SimpleRefreshLayout.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas, View view, long j2) {
        Paint paint;
        Paint paint2;
        com.jd.lib.un.basewidget.widget.simple.c.a aVar = this.x0;
        View view2 = aVar != null ? aVar.getView() : null;
        com.jd.lib.un.basewidget.widget.simple.c.d dVar = this.v0;
        if (dVar != null && dVar.getView() == view) {
            if (!n0(this.x)) {
                return true;
            }
            if (view2 != null) {
                int max = Math.max(view2.getTop() + view2.getPaddingTop() + this.d0, view.getTop());
                int i2 = this.F0;
                if (i2 != 0 && (paint2 = this.y0) != null) {
                    paint2.setColor(i2);
                    if (this.v0.i() == com.jd.lib.un.basewidget.widget.simple.a.b.SCALE) {
                        max = view.getBottom();
                    } else if (this.v0.i() == com.jd.lib.un.basewidget.widget.simple.a.b.TRANSLATE) {
                        max = view.getBottom() + this.d0;
                    }
                    canvas.drawRect(view.getLeft(), view.getTop(), view.getRight(), max, this.y0);
                }
                if (this.D && this.v0.i() == com.jd.lib.un.basewidget.widget.simple.a.b.FIXED_BEHIND) {
                    canvas.save();
                    canvas.clipRect(view.getLeft(), view.getTop(), view.getRight(), max);
                    boolean drawChild = super.drawChild(canvas, view, j2);
                    canvas.restore();
                    return drawChild;
                }
            }
        }
        com.jd.lib.un.basewidget.widget.simple.c.d dVar2 = this.w0;
        if (dVar2 != null && dVar2.getView() == view) {
            if (!n0(this.y)) {
                return true;
            }
            if (view2 != null) {
                int min = Math.min((view2.getBottom() - view2.getPaddingBottom()) + this.d0, view.getBottom());
                int i3 = this.G0;
                if (i3 != 0 && (paint = this.y0) != null) {
                    paint.setColor(i3);
                    if (this.w0.i() == com.jd.lib.un.basewidget.widget.simple.a.b.SCALE) {
                        min = view.getTop();
                    } else if (this.w0.i() == com.jd.lib.un.basewidget.widget.simple.a.b.TRANSLATE) {
                        min = view.getTop() + this.d0;
                    }
                    canvas.drawRect(view.getLeft(), min, view.getRight(), view.getBottom(), this.y0);
                }
                if (this.E && this.w0.i() == com.jd.lib.un.basewidget.widget.simple.a.b.FIXED_BEHIND) {
                    canvas.save();
                    canvas.clipRect(view.getLeft(), min, view.getRight(), view.getBottom());
                    boolean drawChild2 = super.drawChild(canvas, view, j2);
                    canvas.restore();
                    return drawChild2;
                }
            }
        }
        return super.drawChild(canvas, view, j2);
    }

    public SimpleRefreshLayout e0(int i2, boolean z, boolean z2) {
        postDelayed(new h(z, z2), i2 <= 0 ? 1L : i2);
        return this;
    }

    public SimpleRefreshLayout f0(int i2) {
        g0(i2, true);
        return this;
    }

    public SimpleRefreshLayout g0(int i2, boolean z) {
        if (this.C0 == com.jd.lib.un.basewidget.widget.simple.a.c.Refreshing && z) {
            t0(false);
        }
        postDelayed(new g(z), i2 <= 0 ? 1L : i2);
        return this;
    }

    @Override // com.jd.lib.un.basewidget.widget.simple.c.f
    public /* bridge */ /* synthetic */ ViewGroup getLayout() {
        k0();
        return this;
    }

    @Override // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public int getNestedScrollAxes() {
        return this.r.getNestedScrollAxes();
    }

    @Override // android.view.ViewGroup
    /* renamed from: h0 */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    @Override // android.view.ViewGroup
    /* renamed from: i0 */
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.View
    public boolean isNestedScrollingEnabled() {
        return this.q.isNestedScrollingEnabled();
    }

    @Override // android.view.ViewGroup
    /* renamed from: j0 */
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public SimpleRefreshLayout k0() {
        return this;
    }

    protected boolean m0(int i2) {
        if (i2 == 0) {
            if (this.O0 != null) {
                com.jd.lib.un.basewidget.widget.simple.a.c cVar = this.C0;
                if (cVar.isFinishing) {
                    return true;
                }
                if (cVar == com.jd.lib.un.basewidget.widget.simple.a.c.PullDownCanceled) {
                    this.A0.d(com.jd.lib.un.basewidget.widget.simple.a.c.PullDownToRefresh);
                } else if (cVar == com.jd.lib.un.basewidget.widget.simple.a.c.PullUpCanceled) {
                    this.A0.d(com.jd.lib.un.basewidget.widget.simple.a.c.PullUpToLoad);
                }
                this.O0.cancel();
                this.O0 = null;
            }
            this.N0 = null;
        }
        return this.O0 != null;
    }

    protected boolean n0(boolean z) {
        return z && !this.L;
    }

    protected boolean o0(boolean z, com.jd.lib.un.basewidget.widget.simple.c.d dVar) {
        return z || this.L || dVar == null || dVar.i() == com.jd.lib.un.basewidget.widget.simple.a.b.FIXED_BEHIND;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        com.jd.lib.un.basewidget.widget.simple.c.d dVar;
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            if (this.z0 == null) {
                this.z0 = new Handler();
            }
            List<com.jd.lib.un.basewidget.widget.simple.b.a> list = this.B0;
            if (list != null) {
                for (com.jd.lib.un.basewidget.widget.simple.b.a aVar : list) {
                    this.z0.postDelayed(aVar, aVar.f5778g);
                }
                this.B0.clear();
                this.B0 = null;
            }
            if (this.v0 == null) {
                w0(new BezierRadarHeader(getContext()));
            }
            if (this.w0 == null) {
                boolean z = this.y;
                u0(new BallPulseFooter(getContext()));
                this.y = z;
            } else {
                this.y = this.y || !this.S;
            }
            if (this.x0 == null) {
                int childCount = getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    View childAt = getChildAt(i2);
                    com.jd.lib.un.basewidget.widget.simple.c.d dVar2 = this.v0;
                    if ((dVar2 == null || childAt != dVar2.getView()) && ((dVar = this.w0) == null || childAt != dVar.getView())) {
                        this.x0 = new com.jd.lib.un.basewidget.widget.simple.wrapper.a(childAt);
                    }
                }
            }
            if (this.x0 == null) {
                int a2 = com.jd.lib.un.basewidget.widget.simple.e.a.a(20.0f);
                TextView textView = new TextView(getContext());
                textView.setTextColor(-39424);
                textView.setGravity(17);
                textView.setTextSize(20.0f);
                textView.setText("Empty");
                super.addView(textView, -1, -1);
                com.jd.lib.un.basewidget.widget.simple.wrapper.a aVar2 = new com.jd.lib.un.basewidget.widget.simple.wrapper.a(textView);
                this.x0 = aVar2;
                aVar2.getView().setPadding(a2, a2, a2, a2);
            }
            int i3 = this.W;
            View findViewById = i3 > 0 ? findViewById(i3) : null;
            int i4 = this.a0;
            View findViewById2 = i4 > 0 ? findViewById(i4) : null;
            this.x0.h(this.r0);
            this.x0.b(this.O);
            this.x0.f(this.A0, findViewById, findViewById2);
            if (this.d0 != 0) {
                q0(com.jd.lib.un.basewidget.widget.simple.a.c.None);
                com.jd.lib.un.basewidget.widget.simple.c.a aVar3 = this.x0;
                this.d0 = 0;
                aVar3.g(0, this.b0, this.c0);
            }
            if (!this.T && !isNestedScrollingEnabled()) {
                post(new b());
            }
        }
        int[] iArr = this.C;
        if (iArr != null) {
            com.jd.lib.un.basewidget.widget.simple.c.d dVar3 = this.v0;
            if (dVar3 != null) {
                dVar3.c(iArr);
            }
            com.jd.lib.un.basewidget.widget.simple.c.d dVar4 = this.w0;
            if (dVar4 != null) {
                dVar4.c(this.C);
            }
        }
        com.jd.lib.un.basewidget.widget.simple.c.a aVar4 = this.x0;
        if (aVar4 != null) {
            super.bringChildToFront(aVar4.getView());
        }
        com.jd.lib.un.basewidget.widget.simple.c.d dVar5 = this.v0;
        if (dVar5 != null && dVar5.i() != com.jd.lib.un.basewidget.widget.simple.a.b.FIXED_BEHIND) {
            super.bringChildToFront(this.v0.getView());
        }
        com.jd.lib.un.basewidget.widget.simple.c.d dVar6 = this.w0;
        if (dVar6 == null || dVar6.i() == com.jd.lib.un.basewidget.widget.simple.a.b.FIXED_BEHIND) {
            return;
        }
        super.bringChildToFront(this.w0.getView());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.A0.b(0, true);
        q0(com.jd.lib.un.basewidget.widget.simple.a.c.None);
        Handler handler = this.z0;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.z0 = null;
        }
        List<com.jd.lib.un.basewidget.widget.simple.b.a> list = this.B0;
        if (list != null) {
            list.clear();
            this.B0 = null;
        }
        this.S = true;
        this.T = true;
        this.N0 = null;
        ValueAnimator valueAnimator = this.O0;
        if (valueAnimator != null) {
            valueAnimator.removeAllListeners();
            this.O0.removeAllUpdateListeners();
            this.O0.cancel();
            this.O0 = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x0052  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onFinishInflate() {
        /*
            r11 = this;
            super.onFinishInflate()
            int r0 = super.getChildCount()
            r1 = 3
            if (r0 > r1) goto L9e
            r2 = -1
            r3 = 0
            r4 = 0
            r5 = -1
            r6 = 0
        Lf:
            r7 = 2
            r8 = 1
            if (r4 >= r0) goto L33
            android.view.View r9 = super.getChildAt(r4)
            boolean r10 = com.jd.lib.un.basewidget.widget.simple.e.b.c(r9)
            if (r10 == 0) goto L24
            if (r6 < r7) goto L21
            if (r4 != r8) goto L24
        L21:
            r5 = r4
            r6 = 2
            goto L30
        L24:
            boolean r7 = r9 instanceof com.jd.lib.un.basewidget.widget.simple.c.d
            if (r7 != 0) goto L30
            if (r6 >= r8) goto L30
            if (r4 <= 0) goto L2e
            r6 = 1
            goto L2f
        L2e:
            r6 = 0
        L2f:
            r5 = r4
        L30:
            int r4 = r4 + 1
            goto Lf
        L33:
            if (r5 < 0) goto L4d
            com.jd.lib.un.basewidget.widget.simple.wrapper.a r4 = new com.jd.lib.un.basewidget.widget.simple.wrapper.a
            android.view.View r6 = super.getChildAt(r5)
            r4.<init>(r6)
            r11.x0 = r4
            if (r5 != r8) goto L48
            if (r0 != r1) goto L46
            r1 = 0
            goto L4f
        L46:
            r1 = 0
            goto L4e
        L48:
            if (r0 != r7) goto L4d
            r1 = -1
            r7 = 1
            goto L4f
        L4d:
            r1 = -1
        L4e:
            r7 = -1
        L4f:
            r4 = 0
        L50:
            if (r4 >= r0) goto L9d
            android.view.View r5 = super.getChildAt(r4)
            if (r4 == r1) goto L8b
            if (r4 == r7) goto L65
            if (r1 != r2) goto L65
            com.jd.lib.un.basewidget.widget.simple.c.d r6 = r11.v0
            if (r6 != 0) goto L65
            boolean r6 = r5 instanceof com.jd.lib.un.basewidget.widget.simple.c.c
            if (r6 == 0) goto L65
            goto L8b
        L65:
            if (r4 == r7) goto L6d
            if (r7 != r2) goto L9a
            boolean r6 = r5 instanceof com.jd.lib.un.basewidget.widget.simple.c.b
            if (r6 == 0) goto L9a
        L6d:
            boolean r6 = r11.y
            if (r6 != 0) goto L78
            boolean r6 = r11.S
            if (r6 != 0) goto L76
            goto L78
        L76:
            r6 = 0
            goto L79
        L78:
            r6 = 1
        L79:
            r11.y = r6
            boolean r6 = r5 instanceof com.jd.lib.un.basewidget.widget.simple.c.b
            if (r6 == 0) goto L82
            com.jd.lib.un.basewidget.widget.simple.c.b r5 = (com.jd.lib.un.basewidget.widget.simple.c.b) r5
            goto L88
        L82:
            com.jd.lib.un.basewidget.widget.simple.wrapper.RefreshFooterWrapper r6 = new com.jd.lib.un.basewidget.widget.simple.wrapper.RefreshFooterWrapper
            r6.<init>(r5)
            r5 = r6
        L88:
            r11.w0 = r5
            goto L9a
        L8b:
            boolean r6 = r5 instanceof com.jd.lib.un.basewidget.widget.simple.c.c
            if (r6 == 0) goto L92
            com.jd.lib.un.basewidget.widget.simple.c.c r5 = (com.jd.lib.un.basewidget.widget.simple.c.c) r5
            goto L98
        L92:
            com.jd.lib.un.basewidget.widget.simple.wrapper.RefreshHeaderWrapper r6 = new com.jd.lib.un.basewidget.widget.simple.wrapper.RefreshHeaderWrapper
            r6.<init>(r5)
            r5 = r6
        L98:
            r11.v0 = r5
        L9a:
            int r4 = r4 + 1
            goto L50
        L9d:
            return
        L9e:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r1 = "\u6700\u591a\u53ea\u652f\u63013\u4e2a\u5b50View\uff0cMost only support three sub view"
            r0.<init>(r1)
            goto La7
        La6:
            throw r0
        La7:
            goto La6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.un.basewidget.widget.simple.SimpleRefreshLayout.onFinishInflate():void");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int i6;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        getPaddingBottom();
        int childCount = super.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = super.getChildAt(i7);
            com.jd.lib.un.basewidget.widget.simple.c.a aVar = this.x0;
            if (aVar != null && aVar.getView() == childAt) {
                View view = this.x0.getView();
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                int i8 = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + paddingLeft;
                int i9 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + paddingTop;
                view.layout(i8, i9, view.getMeasuredWidth() + i8, view.getMeasuredHeight() + i9);
            }
            com.jd.lib.un.basewidget.widget.simple.c.d dVar = this.v0;
            if (dVar != null && dVar.getView() == childAt) {
                View view2 = this.v0.getView();
                LayoutParams layoutParams2 = (LayoutParams) view2.getLayoutParams();
                int i10 = ((ViewGroup.MarginLayoutParams) layoutParams2).leftMargin;
                int i11 = ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin + this.A;
                int measuredWidth = view2.getMeasuredWidth() + i10;
                int measuredHeight = view2.getMeasuredHeight() + i11;
                if (this.v0.i() == com.jd.lib.un.basewidget.widget.simple.a.b.TRANSLATE) {
                    int i12 = this.f5737g;
                    i11 -= i12;
                    measuredHeight -= i12;
                }
                view2.layout(i10, i11, measuredWidth, measuredHeight);
            }
            com.jd.lib.un.basewidget.widget.simple.c.d dVar2 = this.w0;
            if (dVar2 != null && dVar2.getView() == childAt) {
                View view3 = this.w0.getView();
                LayoutParams layoutParams3 = (LayoutParams) view3.getLayoutParams();
                com.jd.lib.un.basewidget.widget.simple.a.b i13 = this.w0.i();
                int i14 = ((ViewGroup.MarginLayoutParams) layoutParams3).leftMargin;
                int measuredHeight2 = ((ViewGroup.MarginLayoutParams) layoutParams3).topMargin + getMeasuredHeight();
                int i15 = this.B;
                int i16 = measuredHeight2 - i15;
                if (i13 == com.jd.lib.un.basewidget.widget.simple.a.b.MATCH_LAYOUT) {
                    i16 = ((ViewGroup.MarginLayoutParams) layoutParams3).topMargin - i15;
                } else {
                    if (i13 != com.jd.lib.un.basewidget.widget.simple.a.b.FIXED_FRONT && i13 != com.jd.lib.un.basewidget.widget.simple.a.b.FIXED_BEHIND) {
                        if (i13 == com.jd.lib.un.basewidget.widget.simple.a.b.SCALE && this.d0 < 0) {
                            i6 = Math.max(n0(this.y) ? -this.d0 : 0, 0);
                        }
                    } else {
                        i6 = this.f5739i;
                    }
                    i16 -= i6;
                }
                view3.layout(i14, i16, view3.getMeasuredWidth() + i14, view3.getMeasuredHeight() + i16);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:164:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x01e7  */
    @Override // android.view.View
    @android.annotation.SuppressLint({"RestrictedApi"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onMeasure(int r18, int r19) {
        /*
            Method dump skipped, instructions count: 609
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.un.basewidget.widget.simple.SimpleRefreshLayout.onMeasure(int, int):void");
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onNestedFling(@NonNull View view, float f2, float f3, boolean z) {
        return this.q.dispatchNestedFling(f2, f3, z);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onNestedPreFling(@NonNull View view, float f2, float f3) {
        return (this.J0 && f3 > 0.0f) || C0(Float.valueOf(-f3)) || this.q.dispatchNestedPreFling(f2, f3);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onNestedPreScroll(@NonNull View view, int i2, int i3, @NonNull int[] iArr) {
        int i4 = this.s0;
        int i5 = 0;
        if (i3 * i4 > 0) {
            if (Math.abs(i3) > Math.abs(this.s0)) {
                int i6 = this.s0;
                this.s0 = 0;
                i5 = i6;
            } else {
                this.s0 -= i3;
                i5 = i3;
            }
            p0(this.s0);
            com.jd.lib.un.basewidget.widget.simple.a.c cVar = this.D0;
            if (cVar.isOpening || cVar == com.jd.lib.un.basewidget.widget.simple.a.c.None) {
                if (this.d0 > 0) {
                    this.A0.d(com.jd.lib.un.basewidget.widget.simple.a.c.PullDownToRefresh);
                } else {
                    this.A0.d(com.jd.lib.un.basewidget.widget.simple.a.c.PullUpToLoad);
                }
            }
        } else if (i3 > 0 && this.J0) {
            int i7 = i4 - i3;
            this.s0 = i7;
            p0(i7);
            i5 = i3;
        }
        this.q.dispatchNestedPreScroll(i2, i3 - i5, iArr, null);
        iArr[1] = iArr[1] + i5;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onNestedScroll(@NonNull View view, int i2, int i3, int i4, int i5) {
        this.q.dispatchNestedScroll(i2, i3, i4, i5, this.u0);
        int i6 = i5 + this.u0[1];
        if (i6 != 0) {
            if (this.J || ((i6 < 0 && n0(this.x)) || (i6 > 0 && n0(this.y)))) {
                if (this.D0 == com.jd.lib.un.basewidget.widget.simple.a.c.None) {
                    this.A0.d(i6 > 0 ? com.jd.lib.un.basewidget.widget.simple.a.c.PullUpToLoad : com.jd.lib.un.basewidget.widget.simple.a.c.PullDownToRefresh);
                }
                int i7 = this.s0 - i6;
                this.s0 = i7;
                p0(i7);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onNestedScrollAccepted(@NonNull View view, @NonNull View view2, int i2) {
        this.r.onNestedScrollAccepted(view, view2, i2);
        this.q.startNestedScroll(i2 & 2);
        this.s0 = this.d0;
        this.t0 = true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onStartNestedScroll(@NonNull View view, @NonNull View view2, int i2) {
        return (isEnabled() && isNestedScrollingEnabled() && (i2 & 2) != 0) && (this.J || n0(this.x) || n0(this.y));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onStopNestedScroll(@NonNull View view) {
        this.r.onStopNestedScroll(view);
        this.t0 = false;
        this.s0 = 0;
        r0();
        this.q.stopNestedScroll();
    }

    protected void p0(float f2) {
        com.jd.lib.un.basewidget.widget.simple.a.c cVar;
        com.jd.lib.un.basewidget.widget.simple.a.c cVar2 = this.C0;
        com.jd.lib.un.basewidget.widget.simple.a.c cVar3 = com.jd.lib.un.basewidget.widget.simple.a.c.Refreshing;
        if (cVar2 == cVar3 && f2 >= 0.0f) {
            int i2 = this.f5737g;
            if (f2 < i2) {
                this.A0.b((int) f2, true);
            } else {
                double d2 = (this.t - 1.0f) * i2;
                int max = Math.max((this.p * 4) / 3, getHeight());
                int i3 = this.f5737g;
                double d3 = max - i3;
                double max2 = Math.max(0.0f, (f2 - i3) * this.s);
                Double.isNaN(max2);
                double d4 = -max2;
                if (d3 == 0.0d) {
                    d3 = 1.0d;
                }
                Double.isNaN(d2);
                this.A0.b(((int) Math.min((1.0d - Math.pow(100.0d, d4 / d3)) * d2, max2)) + this.f5737g, true);
            }
        } else if (f2 < 0.0f && (cVar2 == com.jd.lib.un.basewidget.widget.simple.a.c.Loading || ((this.H && this.R && n0(this.y)) || (this.K && !this.R && n0(this.y))))) {
            int i4 = this.f5739i;
            if (f2 > (-i4)) {
                this.A0.b((int) f2, true);
            } else {
                double d5 = (this.u - 1.0f) * i4;
                int max3 = Math.max((this.p * 4) / 3, getHeight());
                int i5 = this.f5739i;
                double d6 = max3 - i5;
                double d7 = -Math.min(0.0f, (i5 + f2) * this.s);
                Double.isNaN(d7);
                double d8 = -d7;
                if (d6 == 0.0d) {
                    d6 = 1.0d;
                }
                Double.isNaN(d5);
                this.A0.b(((int) (-Math.min(d5 * (1.0d - Math.pow(100.0d, d8 / d6)), d7))) - this.f5739i, true);
            }
        } else if (f2 >= 0.0f) {
            double d9 = this.t * this.f5737g;
            double max4 = Math.max(this.p / 2, getHeight());
            double max5 = Math.max(0.0f, this.s * f2);
            Double.isNaN(max5);
            double d10 = -max5;
            if (max4 == 0.0d) {
                max4 = 1.0d;
            }
            Double.isNaN(d9);
            this.A0.b((int) Math.min(d9 * (1.0d - Math.pow(100.0d, d10 / max4)), max5), true);
        } else {
            double d11 = this.u * this.f5739i;
            double max6 = Math.max(this.p / 2, getHeight());
            double d12 = -Math.min(0.0f, this.s * f2);
            Double.isNaN(d12);
            double d13 = -d12;
            if (max6 == 0.0d) {
                max6 = 1.0d;
            }
            Double.isNaN(d11);
            this.A0.b((int) (-Math.min(d11 * (1.0d - Math.pow(100.0d, d13 / max6)), d12)), true);
        }
        if (!this.K || this.R || !n0(this.y) || f2 >= 0.0f || (cVar = this.C0) == cVar3 || cVar == com.jd.lib.un.basewidget.widget.simple.a.c.Loading || cVar == com.jd.lib.un.basewidget.widget.simple.a.c.LoadFinish) {
            return;
        }
        y0();
        if (this.Q) {
            this.N0 = null;
            this.A0.a(-this.f5739i);
        }
    }

    @Override // android.view.View
    public boolean post(@NonNull Runnable runnable) {
        Handler handler = this.z0;
        if (handler == null) {
            List<com.jd.lib.un.basewidget.widget.simple.b.a> list = this.B0;
            if (list == null) {
                list = new ArrayList<>();
            }
            this.B0 = list;
            list.add(new com.jd.lib.un.basewidget.widget.simple.b.a(runnable, 0L));
            return false;
        }
        return handler.post(new com.jd.lib.un.basewidget.widget.simple.b.a(runnable, 0L));
    }

    @Override // android.view.View
    public boolean postDelayed(@NonNull Runnable runnable, long j2) {
        if (j2 == 0) {
            new com.jd.lib.un.basewidget.widget.simple.b.a(runnable, 0L).run();
            return true;
        }
        Handler handler = this.z0;
        if (handler == null) {
            List<com.jd.lib.un.basewidget.widget.simple.b.a> list = this.B0;
            if (list == null) {
                list = new ArrayList<>();
            }
            this.B0 = list;
            list.add(new com.jd.lib.un.basewidget.widget.simple.b.a(runnable, j2));
            return false;
        }
        return handler.postDelayed(new com.jd.lib.un.basewidget.widget.simple.b.a(runnable, 0L), j2);
    }

    @SuppressLint({"RestrictedApi"})
    protected void q0(com.jd.lib.un.basewidget.widget.simple.a.c cVar) {
        com.jd.lib.un.basewidget.widget.simple.a.c cVar2 = this.C0;
        if (cVar2 != cVar) {
            this.C0 = cVar;
            this.D0 = cVar;
            com.jd.lib.un.basewidget.widget.simple.c.d dVar = this.v0;
            com.jd.lib.un.basewidget.widget.simple.c.d dVar2 = this.w0;
            com.jd.lib.un.basewidget.widget.simple.d.c cVar3 = this.q0;
            if (dVar != null) {
                dVar.m(this, cVar2, cVar);
            }
            if (dVar2 != null) {
                dVar2.m(this, cVar2, cVar);
            }
            if (cVar3 != null) {
                cVar3.m(this, cVar2, cVar);
            }
        }
    }

    protected void r0() {
        com.jd.lib.un.basewidget.widget.simple.a.c cVar = this.C0;
        com.jd.lib.un.basewidget.widget.simple.a.c cVar2 = com.jd.lib.un.basewidget.widget.simple.a.c.Loading;
        if (cVar != cVar2 && (!this.H || !this.R || this.d0 >= 0 || !n0(this.y))) {
            com.jd.lib.un.basewidget.widget.simple.a.c cVar3 = this.C0;
            com.jd.lib.un.basewidget.widget.simple.a.c cVar4 = com.jd.lib.un.basewidget.widget.simple.a.c.Refreshing;
            if (cVar3 == cVar4) {
                int i2 = this.d0;
                int i3 = this.f5737g;
                if (i2 > i3) {
                    this.A0.a(i3);
                    return;
                } else if (i2 < 0) {
                    this.A0.a(0);
                    return;
                } else {
                    return;
                }
            } else if (cVar3 == com.jd.lib.un.basewidget.widget.simple.a.c.PullDownToRefresh) {
                this.A0.d(com.jd.lib.un.basewidget.widget.simple.a.c.PullDownCanceled);
                return;
            } else if (cVar3 == com.jd.lib.un.basewidget.widget.simple.a.c.PullUpToLoad) {
                this.A0.d(com.jd.lib.un.basewidget.widget.simple.a.c.PullUpCanceled);
                return;
            } else if (cVar3 == com.jd.lib.un.basewidget.widget.simple.a.c.ReleaseToRefresh) {
                this.A0.d(cVar4);
                return;
            } else if (cVar3 == com.jd.lib.un.basewidget.widget.simple.a.c.ReleaseToLoad) {
                this.A0.d(cVar2);
                return;
            } else if (cVar3 == com.jd.lib.un.basewidget.widget.simple.a.c.LoadReleased) {
                if (this.O0 == null) {
                    this.A0.a(-this.f5739i);
                    return;
                }
                return;
            } else if (this.d0 != 0) {
                this.A0.a(0);
                return;
            } else {
                return;
            }
        }
        int i4 = this.d0;
        int i5 = this.f5739i;
        if (i4 < (-i5)) {
            this.A0.a(-i5);
        } else if (i4 > 0) {
            this.A0.a(0);
        }
    }

    protected void s0() {
        com.jd.lib.un.basewidget.widget.simple.a.c cVar = this.C0;
        com.jd.lib.un.basewidget.widget.simple.a.c cVar2 = com.jd.lib.un.basewidget.widget.simple.a.c.None;
        if (cVar != cVar2 && this.d0 == 0) {
            q0(cVar2);
        }
        if (this.d0 != 0) {
            this.A0.a(0);
        }
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z) {
        this.T = true;
        this.q.setNestedScrollingEnabled(z);
    }

    public SimpleRefreshLayout t0(boolean z) {
        this.R = z;
        com.jd.lib.un.basewidget.widget.simple.c.d dVar = this.w0;
        if ((dVar instanceof com.jd.lib.un.basewidget.widget.simple.c.b) && !((com.jd.lib.un.basewidget.widget.simple.c.b) dVar).a(z)) {
            System.out.println("Footer:" + this.w0 + " NoMoreData is not supported.(\u4e0d\u652f\u6301NoMoreData)");
        }
        return this;
    }

    public SimpleRefreshLayout u0(@NonNull com.jd.lib.un.basewidget.widget.simple.c.b bVar) {
        v0(bVar, -1, -2);
        return this;
    }

    public SimpleRefreshLayout v0(@NonNull com.jd.lib.un.basewidget.widget.simple.c.b bVar, int i2, int i3) {
        com.jd.lib.un.basewidget.widget.simple.c.d dVar = this.w0;
        if (dVar != null) {
            super.removeView(dVar.getView());
        }
        this.w0 = bVar;
        this.G0 = 0;
        this.I0 = false;
        this.f5740j = this.f5740j.unNotify();
        this.y = !this.S || this.y;
        if (this.w0.i() == com.jd.lib.un.basewidget.widget.simple.a.b.FIXED_BEHIND) {
            super.addView(this.w0.getView(), 0, new LayoutParams(i2, i3));
        } else {
            super.addView(this.w0.getView(), i2, i3);
        }
        return this;
    }

    public SimpleRefreshLayout w0(@NonNull com.jd.lib.un.basewidget.widget.simple.c.c cVar) {
        x0(cVar, -1, -2);
        return this;
    }

    public SimpleRefreshLayout x0(@NonNull com.jd.lib.un.basewidget.widget.simple.c.c cVar, int i2, int i3) {
        com.jd.lib.un.basewidget.widget.simple.c.d dVar = this.v0;
        if (dVar != null) {
            super.removeView(dVar.getView());
        }
        this.v0 = cVar;
        this.F0 = 0;
        this.H0 = false;
        this.f5738h = this.f5738h.unNotify();
        if (cVar.i() == com.jd.lib.un.basewidget.widget.simple.a.b.FIXED_BEHIND) {
            super.addView(this.v0.getView(), 0, new LayoutParams(i2, i3));
        } else {
            super.addView(this.v0.getView(), i2, i3);
        }
        return this;
    }

    @SuppressLint({"RestrictedApi"})
    protected void y0() {
        com.jd.lib.un.basewidget.widget.simple.a.c cVar = this.C0;
        com.jd.lib.un.basewidget.widget.simple.a.c cVar2 = com.jd.lib.un.basewidget.widget.simple.a.c.Loading;
        if (cVar != cVar2) {
            System.currentTimeMillis();
            this.J0 = true;
            q0(cVar2);
            com.jd.lib.un.basewidget.widget.simple.d.b bVar = this.p0;
            if (bVar != null) {
                bVar.c(this);
            } else if (this.q0 == null) {
                d0(2000);
            }
            com.jd.lib.un.basewidget.widget.simple.c.d dVar = this.w0;
            if (dVar != null) {
                int i2 = this.f5739i;
                dVar.h(this, i2, (int) (this.u * i2));
            }
            com.jd.lib.un.basewidget.widget.simple.d.c cVar3 = this.q0;
            if (cVar3 == null || !(this.w0 instanceof com.jd.lib.un.basewidget.widget.simple.c.b)) {
                return;
            }
            cVar3.c(this);
            int i3 = this.f5739i;
            this.q0.j((com.jd.lib.un.basewidget.widget.simple.c.b) this.w0, i3, (int) (this.u * i3));
        }
    }

    @SuppressLint({"RestrictedApi"})
    protected void z0() {
        c cVar = new c();
        q0(com.jd.lib.un.basewidget.widget.simple.a.c.LoadReleased);
        ValueAnimator a2 = this.A0.a(-this.f5739i);
        if (a2 != null) {
            a2.addListener(cVar);
        }
        com.jd.lib.un.basewidget.widget.simple.c.d dVar = this.w0;
        if (dVar != null) {
            int i2 = this.f5739i;
            dVar.e(this, i2, (int) (this.u * i2));
        }
        com.jd.lib.un.basewidget.widget.simple.d.c cVar2 = this.q0;
        if (cVar2 != null) {
            com.jd.lib.un.basewidget.widget.simple.c.d dVar2 = this.w0;
            if (dVar2 instanceof com.jd.lib.un.basewidget.widget.simple.c.b) {
                int i3 = this.f5739i;
                cVar2.f((com.jd.lib.un.basewidget.widget.simple.c.b) dVar2, i3, (int) (this.u * i3));
            }
        }
        if (a2 == null) {
            cVar.onAnimationEnd(null);
        }
    }

    public SimpleRefreshLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        com.jd.lib.un.basewidget.widget.simple.a.a aVar = com.jd.lib.un.basewidget.widget.simple.a.a.DefaultUnNotify;
        this.f5738h = aVar;
        this.f5740j = aVar;
        this.s = 0.5f;
        this.t = 2.5f;
        this.u = 2.5f;
        this.v = 1.0f;
        this.w = 1.0f;
        this.x = true;
        this.y = false;
        this.z = 300;
        this.D = true;
        this.E = true;
        this.F = true;
        this.G = true;
        this.H = false;
        this.I = true;
        this.J = false;
        this.K = true;
        this.L = false;
        this.M = true;
        this.N = true;
        this.O = true;
        this.P = false;
        this.Q = false;
        this.R = false;
        this.S = false;
        this.T = false;
        this.U = false;
        this.V = false;
        this.W = -1;
        this.a0 = -1;
        this.b0 = -1;
        this.c0 = -1;
        this.u0 = new int[2];
        this.A0 = new k();
        com.jd.lib.un.basewidget.widget.simple.a.c cVar = com.jd.lib.un.basewidget.widget.simple.a.c.None;
        this.C0 = cVar;
        this.D0 = cVar;
        this.F0 = 0;
        this.G0 = 0;
        this.J0 = false;
        this.K0 = 'n';
        this.L0 = false;
        this.M0 = null;
        super.setClipToPadding(false);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.f5737g = com.jd.lib.un.basewidget.widget.simple.e.a.a(60.0f);
        this.f5739i = com.jd.lib.un.basewidget.widget.simple.e.a.a(60.0f);
        this.f5741k = viewConfiguration.getScaledTouchSlop();
        this.f5742l = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f5743m = viewConfiguration.getScaledMaximumFlingVelocity();
        this.o = new AccelerateDecelerateInterpolator();
        this.p = UnDeviceInfo.getScreenHeight();
        this.q = new NestedScrollingChildHelper(this);
        this.r = new NestedScrollingParentHelper(this);
        this.m0 = new Scroller(context);
        this.n0 = VelocityTracker.obtain();
        l0(context, attributeSet);
    }

    /* loaded from: classes16.dex */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int a;
        public com.jd.lib.un.basewidget.widget.simple.a.b b;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.a = 0;
            this.b = null;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SimpleRefreshLayout_Layout);
            this.a = obtainStyledAttributes.getColor(R.styleable.SimpleRefreshLayout_Layout_layout_SimpleBackgroundColor, this.a);
            int i2 = R.styleable.SimpleRefreshLayout_Layout_layout_SimpleRefreshSpinner;
            if (obtainStyledAttributes.hasValue(i2)) {
                this.b = com.jd.lib.un.basewidget.widget.simple.a.b.values()[obtainStyledAttributes.getInt(i2, com.jd.lib.un.basewidget.widget.simple.a.b.TRANSLATE.ordinal())];
            }
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(int i2, int i3) {
            super(i2, i3);
            this.a = 0;
            this.b = null;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.a = 0;
            this.b = null;
        }
    }
}
