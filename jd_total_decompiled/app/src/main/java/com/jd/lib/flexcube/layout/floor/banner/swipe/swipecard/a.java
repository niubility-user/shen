package com.jd.lib.flexcube.layout.floor.banner.swipe.swipecard;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

/* loaded from: classes14.dex */
public class a implements View.OnTouchListener {

    /* renamed from: g  reason: collision with root package name */
    private final float f4350g;

    /* renamed from: h  reason: collision with root package name */
    private final float f4351h;

    /* renamed from: i  reason: collision with root package name */
    private final int f4352i;

    /* renamed from: j  reason: collision with root package name */
    private final b f4353j;

    /* renamed from: k  reason: collision with root package name */
    private final Object f4354k;

    /* renamed from: l  reason: collision with root package name */
    private float f4355l;

    /* renamed from: m  reason: collision with root package name */
    private float f4356m;

    /* renamed from: n  reason: collision with root package name */
    private float f4357n;
    private View o;
    private boolean p = false;
    private float q = (float) Math.cos(Math.toRadians(45.0d));
    private float r;
    private float s;
    private int t;
    private long u;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jd.lib.flexcube.layout.floor.banner.swipe.swipecard.a$a  reason: collision with other inner class name */
    /* loaded from: classes14.dex */
    public class C0146a extends AnimatorListenerAdapter {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ boolean f4358g;

        C0146a(boolean z) {
            this.f4358g = z;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f4358g) {
                a.this.f4353j.onCardExited(true);
                a.this.f4353j.leftExit(a.this.f4354k);
            } else {
                a.this.f4353j.onCardExited(false);
                a.this.f4353j.rightExit(a.this.f4354k);
            }
            a.this.p = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes14.dex */
    public interface b {
        void leftExit(Object obj);

        void onCardExited(boolean z);

        void onClick(MotionEvent motionEvent, View view, Object obj);

        void rightExit(Object obj);

        void swipe(boolean z);
    }

    public a(View view, Object obj, b bVar) {
        this.o = null;
        this.o = view;
        this.f4350g = view.getX();
        this.f4351h = view.getY();
        this.f4352i = view.getWidth();
        view.getHeight();
        this.f4354k = obj;
        ((ViewGroup) view.getParent()).getWidth();
        this.f4353j = bVar;
        this.t = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
    }

    private float d() {
        int i2 = this.f4352i;
        return (i2 / this.q) - i2;
    }

    private boolean f(MotionEvent motionEvent) {
        float abs = Math.abs(this.r - this.f4356m);
        float abs2 = Math.abs(this.s - this.f4357n);
        int i2 = this.t;
        if (abs <= i2 && abs2 <= i2 && abs - abs2 >= -2.0f && System.currentTimeMillis() - this.u < ViewConfiguration.getJumpTapTimeout()) {
            float f2 = this.f4356m;
            float f3 = this.f4350g;
            if (f2 > f3 && f2 < f3 + this.f4352i) {
                this.f4353j.onClick(motionEvent, this.o, this.f4354k);
            }
        } else if (abs > this.t && abs >= abs2) {
            this.f4353j.swipe(this.f4355l < 0.0f);
        }
        return false;
    }

    public void e(boolean z, float f2, long j2) {
        this.p = true;
        this.o.animate().setDuration(j2).setInterpolator(new LinearInterpolator()).translationX(z ? (-this.f4352i) - d() : 0.0f).translationY(this.f4351h).setListener(new C0146a(z)).start();
    }

    public void g(long j2) {
        if (this.p) {
            return;
        }
        e(true, this.f4351h, j2);
    }

    public void h(long j2) {
        if (this.p) {
            return;
        }
        e(false, this.f4351h, j2);
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        try {
            int action = motionEvent.getAction();
            if (action != 0) {
                if (action != 1) {
                    if (action == 2) {
                        float x = motionEvent.getX();
                        float y = motionEvent.getY();
                        if (this.u <= 0) {
                            this.u = System.currentTimeMillis();
                            this.f4356m = x;
                            this.f4357n = y;
                        }
                        this.f4355l = x - this.f4356m;
                    } else if (action != 3) {
                    }
                }
                this.r = motionEvent.getX();
                this.s = motionEvent.getY();
                f(motionEvent);
                this.u = 0L;
            } else {
                float x2 = motionEvent.getX();
                float y2 = motionEvent.getY();
                this.f4356m = x2;
                this.f4357n = y2;
                this.u = System.currentTimeMillis();
                this.f4355l = 0.0f;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return true;
    }
}
