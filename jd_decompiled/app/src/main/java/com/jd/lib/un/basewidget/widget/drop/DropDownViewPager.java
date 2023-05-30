package com.jd.lib.un.basewidget.widget.drop;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.UnLog;

/* loaded from: classes16.dex */
public class DropDownViewPager extends ViewPager {
    private String A;
    private boolean B;
    private boolean C;
    private float D;
    private boolean E;

    /* renamed from: g  reason: collision with root package name */
    private int f5685g;

    /* renamed from: h  reason: collision with root package name */
    private int f5686h;

    /* renamed from: i  reason: collision with root package name */
    private int f5687i;

    /* renamed from: j  reason: collision with root package name */
    private float f5688j;

    /* renamed from: k  reason: collision with root package name */
    private float f5689k;
    @Nullable

    /* renamed from: l  reason: collision with root package name */
    private VelocityTracker f5690l;

    /* renamed from: m  reason: collision with root package name */
    public boolean f5691m;
    @Nullable

    /* renamed from: n  reason: collision with root package name */
    private m f5692n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private float u;
    private float v;
    private float w;
    private float x;
    private float y;
    private float z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ View f5693g;

        a(View view) {
            this.f5693g = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            Float f2 = (Float) valueAnimator.getAnimatedValue();
            View view = this.f5693g;
            if (view != null) {
                ViewCompat.setScaleX(view, f2.floatValue());
            }
            DropDownViewPager dropDownViewPager = DropDownViewPager.this;
            dropDownViewPager.V(dropDownViewPager.D * f2.floatValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class b implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ View f5695g;

        b(DropDownViewPager dropDownViewPager, View view) {
            this.f5695g = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            View view = this.f5695g;
            if (view != null) {
                ViewCompat.setScaleY(view, ((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class c extends AnimatorListenerAdapter {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f5696g;

        c(String str) {
            this.f5696g = str;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            animator.removeAllListeners();
            if (DropDownViewPager.this.f5692n != null) {
                DropDownViewPager.this.f5692n.d(this.f5696g, true);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (DropDownViewPager.this.f5692n != null) {
                DropDownViewPager.this.f5692n.e(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class d implements ViewPager.OnPageChangeListener {
        d() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i2) {
            DropDownViewPager.this.f5687i = i2;
            if (DropDownViewPager.this.f5692n != null) {
                DropDownViewPager.this.f5692n.onPageScrollStateChanged(i2);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i2, float f2, int i3) {
            if (DropDownViewPager.this.f5692n != null) {
                DropDownViewPager.this.f5692n.onPageScrolled(i2, f2, i3);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i2) {
            if (DropDownViewPager.this.f5692n != null) {
                DropDownViewPager.this.f5692n.onPageSelected(i2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class e implements ViewTreeObserver.OnGlobalLayoutListener {
        e() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (Build.VERSION.SDK_INT >= 16) {
                DropDownViewPager.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
            if (DropDownViewPager.this.f5692n == null) {
                DropDownViewPager.this.E = false;
                DropDownViewPager.this.setBackgroundColor(-16777216);
                return;
            }
            DropDownViewPager dropDownViewPager = DropDownViewPager.this;
            dropDownViewPager.A = dropDownViewPager.f5692n.a();
            Bundle bundleExtra = DropDownViewPager.this.f5692n.getIntent() != null ? DropDownViewPager.this.f5692n.getIntent().getBundleExtra("EXTRA_VIEW_INFO") : null;
            if (bundleExtra == null || bundleExtra.getInt("left", -1) == -1) {
                DropDownViewPager.this.E = false;
                DropDownViewPager.this.setBackgroundColor(-16777216);
                DropDownViewPager.this.f5692n.c(true);
                return;
            }
            View f2 = DropDownViewPager.this.f5692n.f();
            if (f2 == null) {
                DropDownViewPager.this.E = false;
                DropDownViewPager.this.setBackgroundColor(-16777216);
                DropDownViewPager.this.f5692n.c(true);
                return;
            }
            DropDownViewPager.this.E = true;
            DropDownViewPager.this.o = bundleExtra.getInt("left", 0);
            DropDownViewPager.this.p = bundleExtra.getInt("top", 0);
            DropDownViewPager.this.q = bundleExtra.getInt("width", 0);
            DropDownViewPager.this.r = bundleExtra.getInt("height", 0);
            DropDownViewPager dropDownViewPager2 = DropDownViewPager.this;
            dropDownViewPager2.s = dropDownViewPager2.o + (DropDownViewPager.this.q / 2);
            DropDownViewPager dropDownViewPager3 = DropDownViewPager.this;
            dropDownViewPager3.t = dropDownViewPager3.p + (DropDownViewPager.this.r / 2);
            f2.getLocationOnScreen(new int[2]);
            DropDownViewPager.this.u = f2.getWidth();
            DropDownViewPager.this.v = f2.getHeight();
            DropDownViewPager.this.w = r1.q / DropDownViewPager.this.u;
            DropDownViewPager.this.x = r1.r / DropDownViewPager.this.v;
            float f3 = r0[0] + (DropDownViewPager.this.u / 2.0f);
            float f4 = r0[1] + (DropDownViewPager.this.v / 2.0f);
            DropDownViewPager.this.y = r2.s - f3;
            DropDownViewPager.this.z = r1.t - f4;
            f2.setTranslationX(DropDownViewPager.this.y);
            f2.setTranslationY(DropDownViewPager.this.z);
            f2.setScaleX(DropDownViewPager.this.w);
            f2.setScaleY(DropDownViewPager.this.x);
            DropDownViewPager.this.S(f2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class f implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ float f5700g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ float f5701h;

        f(float f2, float f3) {
            this.f5700g = f2;
            this.f5701h = f3;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            DropDownViewPager.this.W((((floatValue - DropDownViewPager.this.f5689k) / (this.f5700g - DropDownViewPager.this.f5689k)) * (this.f5701h - DropDownViewPager.this.f5688j)) + DropDownViewPager.this.f5688j, floatValue);
            if (floatValue != DropDownViewPager.this.f5689k || DropDownViewPager.this.f5686h == 0) {
                return;
            }
            DropDownViewPager.this.f5689k = 0.0f;
            DropDownViewPager.this.f5688j = 0.0f;
            DropDownViewPager.this.f5686h = 0;
            if (DropDownViewPager.this.f5692n != null) {
                DropDownViewPager.this.f5692n.c(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class g implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ float f5703g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ float f5704h;

        g(float f2, float f3) {
            this.f5703g = f2;
            this.f5704h = f3;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            DropDownViewPager.this.W(floatValue, (((floatValue - DropDownViewPager.this.f5688j) / (this.f5703g - DropDownViewPager.this.f5688j)) * (this.f5704h - DropDownViewPager.this.f5689k)) + DropDownViewPager.this.f5689k);
            if (floatValue == DropDownViewPager.this.f5688j) {
                DropDownViewPager.this.f5689k = 0.0f;
                DropDownViewPager.this.f5688j = 0.0f;
                DropDownViewPager.this.f5686h = 0;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class h implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ View f5706g;

        h(DropDownViewPager dropDownViewPager, View view) {
            this.f5706g = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            View view = this.f5706g;
            if (view != null) {
                ViewCompat.setX(view, ((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class i implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ View f5707g;

        i(DropDownViewPager dropDownViewPager, View view) {
            this.f5707g = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            View view = this.f5707g;
            if (view != null) {
                ViewCompat.setY(view, ((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class j implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ View f5708g;

        j(View view) {
            this.f5708g = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            Float f2 = (Float) valueAnimator.getAnimatedValue();
            View view = this.f5708g;
            if (view != null) {
                ViewCompat.setScaleX(view, f2.floatValue());
            }
            DropDownViewPager.this.V(f2.floatValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class k implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ View f5710g;

        k(DropDownViewPager dropDownViewPager, View view) {
            this.f5710g = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            View view = this.f5710g;
            if (view != null) {
                ViewCompat.setScaleY(view, ((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class l extends AnimatorListenerAdapter {
        l() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            animator.removeAllListeners();
            DropDownViewPager.this.B = true;
            if (DropDownViewPager.this.f5692n != null) {
                DropDownViewPager.this.f5692n.c(true);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            DropDownViewPager.this.B = false;
            if (DropDownViewPager.this.f5692n != null) {
                DropDownViewPager.this.f5692n.e(true);
            }
        }
    }

    /* loaded from: classes16.dex */
    public interface m {
        @Nullable
        String a();

        boolean b();

        void c(boolean z);

        void d(String str, boolean z);

        void e(boolean z);

        @Nullable
        View f();

        Intent getIntent();

        void onPageScrollStateChanged(int i2);

        void onPageScrolled(int i2, float f2, int i3);

        void onPageSelected(int i2);
    }

    public DropDownViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5686h = 0;
        this.f5691m = true;
        this.B = true;
        this.C = false;
        this.D = 1.0f;
        R(context);
    }

    private void N(MotionEvent motionEvent) {
        if (this.f5690l == null) {
            this.f5690l = VelocityTracker.obtain();
        }
        VelocityTracker velocityTracker = this.f5690l;
        if (velocityTracker == null || motionEvent == null) {
            return;
        }
        velocityTracker.addMovement(motionEvent);
    }

    private float O() {
        VelocityTracker velocityTracker = this.f5690l;
        if (velocityTracker != null) {
            velocityTracker.computeCurrentVelocity(1000);
            float yVelocity = this.f5690l.getYVelocity();
            T();
            return yVelocity;
        }
        return 0.0f;
    }

    private int P(float f2) {
        String lowerCase = Integer.toHexString((int) (Math.min(1.0f, Math.max(0.0f, f2)) * 255.0f)).toLowerCase();
        StringBuilder sb = new StringBuilder();
        sb.append("#");
        sb.append(lowerCase.length() < 2 ? "0" : "");
        sb.append(lowerCase);
        sb.append("000000");
        return Color.parseColor(sb.toString());
    }

    private void R(Context context) {
        this.f5685g = ViewConfiguration.get(context).getScaledTouchSlop();
        setBackgroundColor(0);
        addOnPageChangeListener(new d());
        getViewTreeObserver().addOnGlobalLayoutListener(new e());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void S(View view) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(view.getX(), 0.0f);
        ofFloat.addUpdateListener(new h(this, view));
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(view.getY(), 0.0f);
        ofFloat2.addUpdateListener(new i(this, view));
        ValueAnimator ofFloat3 = ValueAnimator.ofFloat(Math.max(this.w, this.x), 1.0f);
        ofFloat3.addUpdateListener(new j(view));
        ValueAnimator ofFloat4 = ValueAnimator.ofFloat(Math.max(this.w, this.x), 1.0f);
        ofFloat4.addUpdateListener(new k(this, view));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(300L);
        animatorSet.play(ofFloat).with(ofFloat2).with(ofFloat3).with(ofFloat4);
        animatorSet.addListener(new l());
        animatorSet.start();
    }

    private void T() {
        VelocityTracker velocityTracker = this.f5690l;
        if (velocityTracker != null) {
            velocityTracker.clear();
            this.f5690l.recycle();
            this.f5690l = null;
        }
    }

    private void U(float f2, float f3) {
        this.f5686h = 2;
        float f4 = this.f5689k;
        if (f3 != f4) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(f3, f4);
            ofFloat.setDuration(300L);
            ofFloat.addUpdateListener(new f(f3, f2));
            ofFloat.start();
            return;
        }
        float f5 = this.f5688j;
        if (f2 != f5) {
            ValueAnimator ofFloat2 = ValueAnimator.ofFloat(f2, f5);
            ofFloat2.setDuration(300L);
            ofFloat2.addUpdateListener(new g(f2, f3));
            ofFloat2.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void V(float f2) {
        setBackgroundColor(P(f2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void W(float f2, float f3) {
        float f4;
        m mVar = this.f5692n;
        if (mVar == null || mVar.f() == null) {
            return;
        }
        this.f5686h = 1;
        float f5 = f2 - this.f5688j;
        float f6 = f3 - this.f5689k;
        float f7 = 1.0f;
        if (f6 > 0.0f) {
            f7 = 1.0f - (Math.abs(f6) / DpiUtil.getHeight(getContext()));
            f4 = 1.0f - (Math.abs(f6) / (DpiUtil.getHeight(getContext()) / 2));
        } else {
            f4 = 1.0f;
        }
        ViewCompat.setTranslationX(this.f5692n.f(), f5);
        ViewCompat.setTranslationY(this.f5692n.f(), f6);
        X(f7);
        V(f4);
        this.D = f4;
    }

    private void X(float f2) {
        m mVar = this.f5692n;
        if (mVar == null || mVar.f() == null) {
            return;
        }
        float min = Math.min(Math.max(f2, 0.25f), 1.0f);
        ViewCompat.setScaleX(this.f5692n.f(), min);
        ViewCompat.setScaleY(this.f5692n.f(), min);
    }

    public void Q(String str) {
        if (!this.B || this.C) {
            return;
        }
        this.C = true;
        m mVar = this.f5692n;
        if (mVar == null) {
            return;
        }
        View f2 = mVar.f();
        if (f2 == null) {
            this.f5692n.d(str, false);
            return;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f2.getScaleX(), 0.0f);
        ofFloat.addUpdateListener(new a(f2));
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(f2.getScaleY(), 0.0f);
        ofFloat2.addUpdateListener(new b(this, f2));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(300L);
        animatorSet.play(ofFloat).with(ofFloat2);
        animatorSet.addListener(new c(str));
        animatorSet.start();
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        try {
            if (this.f5691m) {
                int action = motionEvent.getAction() & 255;
                if (action != 0) {
                    if (action != 1 && action == 2 && this.f5692n != null && motionEvent.getPointerCount() == 1) {
                        float rawY = motionEvent.getRawY() - this.f5689k;
                        float abs = Math.abs(motionEvent.getRawX() - this.f5688j);
                        float abs2 = Math.abs(rawY);
                        if (rawY > 0.0f && Math.sqrt((abs * abs) + (abs2 * abs2)) >= this.f5685g && abs2 > abs && this.f5692n.b()) {
                            if (UnLog.D) {
                                UnLog.d("DropDownViewPager", "\u4e0b\u6ed1");
                            }
                            this.f5692n.e(false);
                            return onTouchEvent(motionEvent);
                        }
                        return super.onInterceptTouchEvent(motionEvent);
                    }
                } else {
                    this.f5688j = motionEvent.getRawX();
                    this.f5689k = motionEvent.getRawY();
                }
                return super.onInterceptTouchEvent(motionEvent);
            }
            return false;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x001a, code lost:
        if (r0 != 3) goto L41;
     */
    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            boolean r0 = r4.f5691m
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            int r0 = r5.getAction()
            r0 = r0 & 255(0xff, float:3.57E-43)
            int r2 = r4.f5686h
            r3 = 2
            if (r2 != r3) goto L12
            return r1
        L12:
            if (r0 == 0) goto L8b
            r1 = 1
            if (r0 == r1) goto L4c
            if (r0 == r3) goto L1e
            r3 = 3
            if (r0 == r3) goto L4c
            goto L9a
        L1e:
            r4.N(r5)
            float r0 = r5.getRawY()
            float r2 = r4.f5689k
            float r0 = r0 - r2
            int r0 = (int) r0
            r2 = 50
            if (r0 > r2) goto L36
            int r3 = r4.f5686h
            if (r3 == r1) goto L36
            boolean r5 = super.onTouchEvent(r5)
            return r5
        L36:
            int r3 = r4.f5687i
            if (r3 == r1) goto L9a
            if (r0 > r2) goto L40
            int r0 = r4.f5686h
            if (r0 != r1) goto L9a
        L40:
            float r0 = r5.getRawX()
            float r5 = r5.getRawY()
            r4.W(r0, r5)
            return r1
        L4c:
            if (r2 == r1) goto L53
            boolean r5 = super.onTouchEvent(r5)
            return r5
        L53:
            float r0 = r5.getRawX()
            float r1 = r5.getRawY()
            float r2 = r4.O()
            r3 = 1140457472(0x43fa0000, float:500.0)
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 >= 0) goto L81
            float r2 = r4.f5689k
            float r2 = r1 - r2
            float r2 = java.lang.Math.abs(r2)
            android.content.Context r3 = r4.getContext()
            int r3 = com.jingdong.common.DpiUtil.getHeight(r3)
            int r3 = r3 / 4
            float r3 = (float) r3
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 <= 0) goto L7d
            goto L81
        L7d:
            r4.U(r0, r1)
            goto L9a
        L81:
            com.jd.lib.un.basewidget.widget.drop.DropDownViewPager$m r0 = r4.f5692n
            if (r0 == 0) goto L9a
            java.lang.String r0 = "2"
            r4.Q(r0)
            goto L9a
        L8b:
            float r0 = r5.getRawX()
            r4.f5688j = r0
            float r0 = r5.getRawY()
            r4.f5689k = r0
            r4.N(r5)
        L9a:
            boolean r5 = super.onTouchEvent(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.un.basewidget.widget.drop.DropDownViewPager.onTouchEvent(android.view.MotionEvent):boolean");
    }
}
