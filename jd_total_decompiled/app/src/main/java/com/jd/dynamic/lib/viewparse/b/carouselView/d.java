package com.jd.dynamic.lib.viewparse.b.carouselView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;
import com.jd.dynamic.lib.utils.DPIUtil;
import java.lang.reflect.Field;

/* loaded from: classes13.dex */
public class d extends ViewPager {

    /* renamed from: g  reason: collision with root package name */
    private int f2310g;

    /* renamed from: h  reason: collision with root package name */
    private int f2311h;

    /* renamed from: i  reason: collision with root package name */
    private int f2312i;

    /* renamed from: j  reason: collision with root package name */
    private float f2313j;

    /* renamed from: k  reason: collision with root package name */
    private float f2314k;
    @Nullable

    /* renamed from: l  reason: collision with root package name */
    private VelocityTracker f2315l;

    /* renamed from: m  reason: collision with root package name */
    public boolean f2316m;
    @Nullable

    /* renamed from: n  reason: collision with root package name */
    private a f2317n;
    private String o;
    private boolean p;
    private boolean q;
    private float r;
    private boolean s;

    /* loaded from: classes13.dex */
    public interface a extends ViewPager.OnPageChangeListener {
        @Nullable
        String a();

        void a(String str, boolean z);

        void a(boolean z);

        @Nullable
        View b();

        void b(boolean z);

        boolean c();

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        void onPageScrollStateChanged(int i2);

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        void onPageScrolled(int i2, float f2, int i3);

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        void onPageSelected(int i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class b implements ViewPager.OnPageChangeListener {
        b() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i2) {
            d.this.f2312i = i2;
            if (d.this.f2317n != null) {
                d.this.f2317n.onPageScrollStateChanged(i2);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i2, float f2, int i3) {
            if (d.this.f2317n != null) {
                d.this.f2317n.onPageScrolled(i2, f2, i3);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i2) {
            if (d.this.f2317n != null) {
                d.this.f2317n.onPageSelected(i2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class c implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ View f2319g;

        c(View view) {
            this.f2319g = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            Float f2 = (Float) valueAnimator.getAnimatedValue();
            View view = this.f2319g;
            if (view != null) {
                ViewCompat.setScaleX(view, f2.floatValue());
            }
            d dVar = d.this;
            dVar.x(dVar.r * f2.floatValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jd.dynamic.lib.viewparse.b.a.d$d  reason: collision with other inner class name */
    /* loaded from: classes13.dex */
    public class C0077d implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ View f2321g;

        C0077d(d dVar, View view) {
            this.f2321g = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            View view = this.f2321g;
            if (view != null) {
                ViewCompat.setScaleY(view, ((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class e extends AnimatorListenerAdapter {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f2322g;

        e(String str) {
            this.f2322g = str;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            animator.removeAllListeners();
            if (d.this.f2317n != null) {
                d.this.f2317n.a(this.f2322g, true);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (d.this.f2317n != null) {
                d.this.f2317n.a(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class f implements ViewTreeObserver.OnGlobalLayoutListener {
        f() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (Build.VERSION.SDK_INT >= 16) {
                d.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
            if (d.this.f2317n == null) {
                d.this.s = false;
                d.this.setBackgroundColor(-1);
                return;
            }
            d dVar = d.this;
            dVar.o = dVar.f2317n.a();
            d.this.s = false;
            d.this.setBackgroundColor(-1);
            d.this.f2317n.b(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class g implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ float f2325g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ float f2326h;

        g(float f2, float f3) {
            this.f2325g = f2;
            this.f2326h = f3;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            d.this.m((((floatValue - d.this.f2314k) / (this.f2325g - d.this.f2314k)) * (this.f2326h - d.this.f2313j)) + d.this.f2313j, floatValue);
            if (floatValue != d.this.f2314k || d.this.f2311h == 0) {
                return;
            }
            d.this.f2314k = 0.0f;
            d.this.f2313j = 0.0f;
            d.this.f2311h = 0;
            if (d.this.f2317n != null) {
                d.this.f2317n.b(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class h implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ float f2328g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ float f2329h;

        h(float f2, float f3) {
            this.f2328g = f2;
            this.f2329h = f3;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            d.this.m(floatValue, (((floatValue - d.this.f2313j) / (this.f2328g - d.this.f2313j)) * (this.f2329h - d.this.f2314k)) + d.this.f2314k);
            if (floatValue == d.this.f2313j) {
                d.this.f2314k = 0.0f;
                d.this.f2313j = 0.0f;
                d.this.f2311h = 0;
            }
        }
    }

    /* loaded from: classes13.dex */
    public static class i extends Scroller {
        private int a;

        public i(Context context, int i2) {
            super(context, new DecelerateInterpolator());
            this.a = 1;
            this.a = i2 * 2;
        }

        @Override // android.widget.Scroller
        public void startScroll(int i2, int i3, int i4, int i5, int i6) {
            super.startScroll(i2, i3, i4, i5, this.a);
        }
    }

    public d(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2311h = 0;
        this.f2316m = true;
        this.p = true;
        this.q = false;
        this.r = 1.0f;
        g(context);
    }

    private float a() {
        VelocityTracker velocityTracker = this.f2315l;
        if (velocityTracker != null) {
            velocityTracker.computeCurrentVelocity(1000);
            float yVelocity = this.f2315l.getYVelocity();
            l();
            return yVelocity;
        }
        return 0.0f;
    }

    private int b(float f2) {
        String lowerCase = Integer.toHexString((int) (Math.min(1.0f, Math.max(0.0f, f2)) * 255.0f)).toLowerCase();
        StringBuilder sb = new StringBuilder();
        sb.append("#");
        sb.append(lowerCase.length() < 2 ? "0" : "");
        sb.append(lowerCase);
        sb.append("ffffff");
        return Color.parseColor(sb.toString());
    }

    private void f(float f2, float f3) {
        ValueAnimator ofFloat;
        ValueAnimator.AnimatorUpdateListener hVar;
        this.f2311h = 2;
        float f4 = this.f2314k;
        if (f3 != f4) {
            ofFloat = ValueAnimator.ofFloat(f3, f4);
            ofFloat.setDuration(300L);
            hVar = new g(f3, f2);
        } else {
            float f5 = this.f2313j;
            if (f2 == f5) {
                return;
            }
            ofFloat = ValueAnimator.ofFloat(f2, f5);
            ofFloat.setDuration(300L);
            hVar = new h(f2, f3);
        }
        ofFloat.addUpdateListener(hVar);
        ofFloat.start();
    }

    private void g(Context context) {
        this.f2310g = ViewConfiguration.get(context).getScaledTouchSlop();
        setBackgroundColor(0);
        addOnPageChangeListener(new b());
        getViewTreeObserver().addOnGlobalLayoutListener(new f());
    }

    private void h(MotionEvent motionEvent) {
        if (this.f2315l == null) {
            this.f2315l = VelocityTracker.obtain();
        }
        this.f2315l.addMovement(motionEvent);
    }

    private void l() {
        VelocityTracker velocityTracker = this.f2315l;
        if (velocityTracker != null) {
            velocityTracker.clear();
            this.f2315l.recycle();
            this.f2315l = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m(float f2, float f3) {
        float f4;
        a aVar = this.f2317n;
        if (aVar == null || aVar.b() == null) {
            return;
        }
        this.f2311h = 1;
        float f5 = f2 - this.f2313j;
        float f6 = f3 - this.f2314k;
        float f7 = 1.0f;
        if (f6 > 0.0f) {
            f7 = 1.0f - (Math.abs(f6) / DPIUtil.getHeight(getContext()));
            f4 = 1.0f - (Math.abs(f6) / (DPIUtil.getHeight(getContext()) / 2));
        } else {
            f4 = 1.0f;
        }
        ViewCompat.setTranslationX(this.f2317n.b(), f5);
        ViewCompat.setTranslationY(this.f2317n.b(), f6);
        y(f7);
        x(f4);
        this.r = f4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x(float f2) {
        setBackgroundColor(b(f2));
    }

    private void y(float f2) {
        a aVar = this.f2317n;
        if (aVar == null || aVar.b() == null) {
            return;
        }
        float min = Math.min(Math.max(f2, 0.25f), 1.0f);
        ViewCompat.setScaleX(this.f2317n.b(), min);
        ViewCompat.setScaleY(this.f2317n.b(), min);
    }

    public void j(String str) {
        if (!this.p || this.q) {
            return;
        }
        this.q = true;
        a aVar = this.f2317n;
        if (aVar == null) {
            return;
        }
        View b2 = aVar.b();
        if (b2 == null) {
            this.f2317n.a(str, false);
            return;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(b2.getScaleX(), 0.0f);
        ofFloat.addUpdateListener(new c(b2));
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(b2.getScaleY(), 0.0f);
        ofFloat2.addUpdateListener(new C0077d(this, b2));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(300L);
        animatorSet.play(ofFloat).with(ofFloat2);
        animatorSet.addListener(new e(str));
        animatorSet.start();
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        try {
            if (this.f2316m) {
                int action = motionEvent.getAction() & 255;
                if (action == 0) {
                    this.f2313j = motionEvent.getRawX();
                    this.f2314k = motionEvent.getRawY();
                } else if (action == 2 && this.f2317n != null && motionEvent.getPointerCount() == 1) {
                    float rawY = motionEvent.getRawY() - this.f2314k;
                    float abs = Math.abs(motionEvent.getRawX() - this.f2313j);
                    float abs2 = Math.abs(rawY);
                    if (rawY <= 0.0f || Math.sqrt((abs * abs) + (abs2 * abs2)) < this.f2310g || abs2 <= abs || !this.f2317n.c()) {
                        return super.onInterceptTouchEvent(motionEvent);
                    }
                    this.f2317n.a(false);
                    return onTouchEvent(motionEvent);
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
        if (r0 != 3) goto L52;
     */
    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f2316m) {
            int action = motionEvent.getAction() & 255;
            int i2 = this.f2311h;
            if (i2 == 2) {
                return false;
            }
            if (action != 0) {
                if (action != 1) {
                    if (action == 2) {
                        h(motionEvent);
                        int rawY = (int) (motionEvent.getRawY() - this.f2314k);
                        if (rawY <= 50 && this.f2311h != 1) {
                            try {
                                return super.onTouchEvent(motionEvent);
                            } catch (Exception e2) {
                                e2.printStackTrace();
                                return false;
                            }
                        } else if (this.f2312i != 1 && (rawY > 50 || this.f2311h == 1)) {
                            m(motionEvent.getRawX(), motionEvent.getRawY());
                            return true;
                        }
                    }
                }
                if (i2 != 1) {
                    try {
                        return super.onTouchEvent(motionEvent);
                    } catch (Exception unused) {
                        return false;
                    }
                }
                float rawX = motionEvent.getRawX();
                float rawY2 = motionEvent.getRawY();
                if (a() < 500.0f && Math.abs(rawY2 - this.f2314k) <= DPIUtil.getHeight(getContext()) / 4) {
                    f(rawX, rawY2);
                } else if (this.f2317n != null) {
                    j("2");
                }
            } else {
                this.f2313j = motionEvent.getRawX();
                this.f2314k = motionEvent.getRawY();
                h(motionEvent);
            }
            try {
                return super.onTouchEvent(motionEvent);
            } catch (Exception unused2) {
                return false;
            }
        }
        return false;
    }

    public void v(@Nullable a aVar) {
        this.f2317n = aVar;
    }

    public void w(int i2) {
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            declaredField.set(this, new i(getContext(), i2));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
