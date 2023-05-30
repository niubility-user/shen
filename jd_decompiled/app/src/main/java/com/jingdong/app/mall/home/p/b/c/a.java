package com.jingdong.app.mall.home.p.b.c;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.animation.LinearInterpolator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class a {
    private final Handler a;
    private final ArrayList<com.jingdong.app.mall.home.p.b.c.b> b;

    /* renamed from: c  reason: collision with root package name */
    private final ArrayList<com.jingdong.app.mall.home.p.b.c.b> f10504c;
    private final ArrayList<com.jingdong.app.mall.home.p.b.c.b> d;

    /* renamed from: e  reason: collision with root package name */
    private final ArrayList<com.jingdong.app.mall.home.p.b.c.b> f10505e;

    /* renamed from: f  reason: collision with root package name */
    private ValueAnimator f10506f;

    /* renamed from: g  reason: collision with root package name */
    private final AtomicBoolean f10507g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f10508h;

    /* renamed from: i  reason: collision with root package name */
    private int f10509i;

    /* renamed from: j  reason: collision with root package name */
    private int f10510j;

    /* renamed from: k  reason: collision with root package name */
    private final Runnable f10511k;

    /* renamed from: l  reason: collision with root package name */
    private final AtomicBoolean f10512l;

    /* renamed from: com.jingdong.app.mall.home.p.b.c.a$a  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    class RunnableC0313a implements Runnable {
        RunnableC0313a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.q();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements ValueAnimator.AnimatorUpdateListener {
        b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            if (intValue < 2900) {
                return;
            }
            int max = Math.max(intValue - 3000, 0);
            if (a.this.f10512l.get()) {
                Iterator it = a.this.b.iterator();
                while (it.hasNext()) {
                    ((com.jingdong.app.mall.home.p.b.c.b) it.next()).e(true, max);
                }
                return;
            }
            Iterator it2 = a.this.d.iterator();
            while (it2.hasNext()) {
                ((com.jingdong.app.mall.home.p.b.c.b) it2.next()).e(false, max);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c extends com.jingdong.app.mall.home.r.a.d {

        /* renamed from: g  reason: collision with root package name */
        long f10515g;

        c() {
        }

        @Override // com.jingdong.app.mall.home.r.a.d, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            this.f10515g = SystemClock.elapsedRealtime();
            if (a.this.f10512l.get()) {
                a.f(a.this, 1);
                a.g(a.this, 2);
                Iterator it = a.this.b.iterator();
                while (it.hasNext()) {
                    ((com.jingdong.app.mall.home.p.b.c.b) it.next()).d(true);
                }
                return;
            }
            a.i(a.this, 1);
            a.j(a.this, 2);
            Iterator it2 = a.this.d.iterator();
            while (it2.hasNext()) {
                ((com.jingdong.app.mall.home.p.b.c.b) it2.next()).d(false);
            }
        }

        @Override // com.jingdong.app.mall.home.r.a.d
        protected void onEnd(Animator animator, boolean z) {
            if (a.this.f10512l.get()) {
                Iterator it = a.this.b.iterator();
                while (it.hasNext()) {
                    ((com.jingdong.app.mall.home.p.b.c.b) it.next()).c(true);
                }
            } else {
                Iterator it2 = a.this.d.iterator();
                while (it2.hasNext()) {
                    ((com.jingdong.app.mall.home.p.b.c.b) it2.next()).c(false);
                }
            }
            if (z) {
                return;
            }
            if (a.this.f10509i == 0 && !a.this.f10504c.isEmpty()) {
                a.this.b.addAll(a.this.f10504c);
                a.this.f10504c.clear();
            }
            if (a.this.f10510j == 0 && !a.this.f10505e.isEmpty()) {
                a.this.d.addAll(a.this.f10505e);
                a.this.f10505e.clear();
            }
            if (!a.this.f10512l.get() || a.this.d.size() <= 0) {
                if (!a.this.f10512l.get() && a.this.b.size() > 0) {
                    a.this.f10512l.set(true);
                }
            } else {
                a.this.f10512l.set(false);
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (a.this.f10507g.get() || elapsedRealtime - this.f10515g < 3000) {
                return;
            }
            a.this.f10506f.start();
        }
    }

    /* loaded from: classes4.dex */
    private static class d {
        static a a = new a(null);
    }

    /* synthetic */ a(RunnableC0313a runnableC0313a) {
        this();
    }

    static /* synthetic */ int f(a aVar, int i2) {
        int i3 = aVar.f10509i + i2;
        aVar.f10509i = i3;
        return i3;
    }

    static /* synthetic */ int g(a aVar, int i2) {
        int i3 = aVar.f10509i % i2;
        aVar.f10509i = i3;
        return i3;
    }

    static /* synthetic */ int i(a aVar, int i2) {
        int i3 = aVar.f10510j + i2;
        aVar.f10510j = i3;
        return i3;
    }

    static /* synthetic */ int j(a aVar, int i2) {
        int i3 = aVar.f10510j % i2;
        aVar.f10510j = i3;
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        if (this.f10504c.size() == 0 && this.f10505e.size() == 0) {
            this.a.postDelayed(this.f10511k, 3500L);
            return;
        }
        this.b.addAll(this.f10504c);
        this.f10504c.clear();
        this.d.addAll(this.f10505e);
        this.f10505e.clear();
        this.f10512l.set(this.b.size() > 0);
        this.f10506f.start();
        this.f10508h = true;
    }

    public static a r() {
        return d.a;
    }

    private void s() {
        ValueAnimator ofInt = ValueAnimator.ofInt(0, 3500);
        this.f10506f = ofInt;
        ofInt.addUpdateListener(new b());
        this.f10506f.addListener(new c());
        this.f10506f.setDuration(3500L);
        this.f10506f.setInterpolator(new LinearInterpolator());
    }

    public void o(com.jingdong.app.mall.home.p.b.c.b bVar) {
        if (this.d.contains(bVar) || this.f10505e.contains(bVar)) {
            return;
        }
        this.f10505e.add(bVar);
    }

    public void p(com.jingdong.app.mall.home.p.b.c.b bVar) {
        if (this.b.contains(bVar) || this.f10504c.contains(bVar)) {
            return;
        }
        this.f10504c.add(bVar);
    }

    public void t() {
        this.f10507g.set(true);
        this.a.removeCallbacksAndMessages(null);
    }

    public void u() {
        this.f10507g.set(false);
        if (this.f10506f.isRunning()) {
            return;
        }
        if (this.f10508h) {
            this.f10506f.start();
        } else {
            this.a.postDelayed(this.f10511k, 3500L);
        }
    }

    public void v() {
        if (this.f10506f.isRunning()) {
            this.f10506f.cancel();
        }
        this.b.clear();
        this.f10505e.clear();
        this.d.clear();
        this.f10505e.clear();
        this.f10508h = false;
        this.f10509i = 0;
        this.f10510j = 0;
        this.a.removeCallbacksAndMessages(null);
    }

    public void w(boolean z) {
        if (z) {
            v();
        } else {
            q();
        }
    }

    private a() {
        this.a = new Handler(Looper.getMainLooper());
        this.b = new ArrayList<>(2);
        this.f10504c = new ArrayList<>(2);
        this.d = new ArrayList<>(2);
        this.f10505e = new ArrayList<>(2);
        this.f10507g = new AtomicBoolean(false);
        this.f10509i = 0;
        this.f10510j = 0;
        this.f10511k = new RunnableC0313a();
        this.f10512l = new AtomicBoolean(true);
        s();
    }
}
