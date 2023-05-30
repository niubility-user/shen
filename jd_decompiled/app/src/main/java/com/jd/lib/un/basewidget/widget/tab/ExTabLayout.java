package com.jd.lib.un.basewidget.widget.tab;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.util.Pools;
import androidx.core.view.GravityCompat;
import androidx.core.view.PointerIconCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TextViewCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.jd.lib.un.basewidget.R;
import com.jd.lib.un.utils.config.UnDeviceInfo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

@ViewPager.DecorView
/* loaded from: classes16.dex */
public class ExTabLayout extends HorizontalScrollView {
    private static final Pools.Pool<f> K = new Pools.SynchronizedPool(16);
    private final ArrayList<c> A;
    private c B;
    private ValueAnimator C;
    ViewPager D;
    private PagerAdapter E;
    private DataSetObserver F;
    private TabLayoutOnPageChangeListener G;
    private b H;
    private boolean I;
    private final Pools.Pool<g> J;

    /* renamed from: g  reason: collision with root package name */
    private final ArrayList<f> f5804g;

    /* renamed from: h  reason: collision with root package name */
    private f f5805h;

    /* renamed from: i  reason: collision with root package name */
    private final e f5806i;

    /* renamed from: j  reason: collision with root package name */
    int f5807j;

    /* renamed from: k  reason: collision with root package name */
    int f5808k;

    /* renamed from: l  reason: collision with root package name */
    int f5809l;

    /* renamed from: m  reason: collision with root package name */
    int f5810m;

    /* renamed from: n  reason: collision with root package name */
    int f5811n;
    ColorStateList o;
    float p;
    float q;
    float r;
    final int s;
    int t;
    private final int u;
    private final int v;
    private final int w;
    private int x;
    int y;
    int z;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface IndicatorGravity {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface Mode {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface TabGravity {
    }

    /* loaded from: classes16.dex */
    public static class TabLayoutOnPageChangeListener implements ViewPager.OnPageChangeListener {

        /* renamed from: g  reason: collision with root package name */
        private final WeakReference<ExTabLayout> f5812g;

        /* renamed from: h  reason: collision with root package name */
        private int f5813h;

        /* renamed from: i  reason: collision with root package name */
        private int f5814i;

        public TabLayoutOnPageChangeListener(ExTabLayout exTabLayout) {
            this.f5812g = new WeakReference<>(exTabLayout);
        }

        void d() {
            this.f5814i = 0;
            this.f5813h = 0;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i2) {
            this.f5813h = this.f5814i;
            this.f5814i = i2;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i2, float f2, int i3) {
            ExTabLayout exTabLayout = this.f5812g.get();
            if (exTabLayout != null) {
                int i4 = this.f5814i;
                exTabLayout.J(i2, f2, i4 != 2 || this.f5813h == 1, (i4 == 2 && this.f5813h == 0) ? false : true);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i2) {
            ExTabLayout exTabLayout = this.f5812g.get();
            if (exTabLayout == null || exTabLayout.u() == i2 || i2 >= exTabLayout.w()) {
                return;
            }
            int i3 = this.f5814i;
            exTabLayout.G(exTabLayout.v(i2), i3 == 0 || (i3 == 2 && this.f5813h == 0));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements ValueAnimator.AnimatorUpdateListener {
        a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            ExTabLayout.this.scrollTo(((Integer) valueAnimator.getAnimatedValue()).intValue(), 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes16.dex */
    public class b implements ViewPager.OnAdapterChangeListener {

        /* renamed from: g  reason: collision with root package name */
        private boolean f5816g;

        b() {
        }

        void a(boolean z) {
            this.f5816g = z;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnAdapterChangeListener
        public void onAdapterChanged(@NonNull ViewPager viewPager, @Nullable PagerAdapter pagerAdapter, @Nullable PagerAdapter pagerAdapter2) {
            ExTabLayout exTabLayout = ExTabLayout.this;
            if (exTabLayout.D == viewPager) {
                exTabLayout.H(pagerAdapter2, this.f5816g);
            }
        }
    }

    /* loaded from: classes16.dex */
    public interface c {
        void a(f fVar);

        void b(f fVar);

        void c(f fVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes16.dex */
    public class d extends DataSetObserver {
        d() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            ExTabLayout.this.B();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            ExTabLayout.this.B();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes16.dex */
    public class e extends LinearLayout {

        /* renamed from: g  reason: collision with root package name */
        private int f5818g;

        /* renamed from: h  reason: collision with root package name */
        private float f5819h;

        /* renamed from: i  reason: collision with root package name */
        private Drawable f5820i;

        /* renamed from: j  reason: collision with root package name */
        private int f5821j;

        /* renamed from: k  reason: collision with root package name */
        private int f5822k;

        /* renamed from: l  reason: collision with root package name */
        private int f5823l;

        /* renamed from: m  reason: collision with root package name */
        private final Paint f5824m;

        /* renamed from: n  reason: collision with root package name */
        int f5825n;
        float o;
        private int p;
        private int q;
        private int r;
        private ValueAnimator s;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes16.dex */
        public class a implements ValueAnimator.AnimatorUpdateListener {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ int f5826g;

            /* renamed from: h  reason: collision with root package name */
            final /* synthetic */ int f5827h;

            /* renamed from: i  reason: collision with root package name */
            final /* synthetic */ int f5828i;

            /* renamed from: j  reason: collision with root package name */
            final /* synthetic */ int f5829j;

            a(int i2, int i3, int i4, int i5) {
                this.f5826g = i2;
                this.f5827h = i3;
                this.f5828i = i4;
                this.f5829j = i5;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedFraction = valueAnimator.getAnimatedFraction();
                int i2 = this.f5826g;
                int i3 = this.f5827h;
                if (i2 < i3) {
                    e.this.k(com.jd.lib.un.basewidget.widget.tab.a.a(i2, i3, animatedFraction), com.jd.lib.un.basewidget.widget.tab.a.a(this.f5828i, this.f5829j, e.this.c(animatedFraction)));
                    return;
                }
                e eVar = e.this;
                eVar.k(com.jd.lib.un.basewidget.widget.tab.a.a(i2, i3, eVar.c(animatedFraction)), com.jd.lib.un.basewidget.widget.tab.a.a(this.f5828i, this.f5829j, animatedFraction));
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes16.dex */
        public class b extends AnimatorListenerAdapter {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ int f5831g;

            b(int i2) {
                this.f5831g = i2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                e eVar = e.this;
                eVar.f5825n = this.f5831g;
                eVar.o = 0.0f;
            }
        }

        e(Context context) {
            super(context);
            this.f5825n = -1;
            this.p = -1;
            this.q = -1;
            this.r = -1;
            setWillNotDraw(false);
            this.f5824m = new Paint();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float c(float f2) {
            float f3;
            if (f2 < 0.5d) {
                f3 = this.f5819h * f2;
            } else {
                f3 = (1.0f - f2) * this.f5819h;
            }
            return f2 + f3;
        }

        private void t() {
            int i2;
            int i3;
            View childAt = getChildAt(this.f5825n);
            if (childAt == null || childAt.getWidth() <= 0) {
                i2 = -1;
                i3 = -1;
            } else {
                int[] j2 = j(childAt);
                i3 = j2[0];
                i2 = j2[1];
                if (this.o > 0.0f && this.f5825n < getChildCount() - 1) {
                    int[] j3 = j(getChildAt(this.f5825n + 1));
                    int i4 = j3[0];
                    int i5 = j3[1];
                    float f2 = this.o;
                    i3 = com.jd.lib.un.basewidget.widget.tab.a.a(i3, i4, f2);
                    i2 = com.jd.lib.un.basewidget.widget.tab.a.a(i2, i5, c(f2));
                }
            }
            k(i3, i2);
        }

        void b(int i2, int i3) {
            int i4;
            int i5;
            ValueAnimator valueAnimator = this.s;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.s.cancel();
            }
            boolean z = ViewCompat.getLayoutDirection(this) == 1;
            View childAt = getChildAt(i2);
            if (childAt == null) {
                t();
                return;
            }
            int[] j2 = j(childAt);
            int i6 = j2[0];
            int i7 = j2[1];
            if (Math.abs(i2 - this.f5825n) <= 1) {
                i4 = this.q;
                i5 = this.r;
            } else {
                int r = ExTabLayout.this.r(24);
                i4 = (i2 >= this.f5825n ? !z : z) ? i6 - r : r + i7;
                i5 = i4;
            }
            if (i4 == i6 && i5 == i7) {
                return;
            }
            ValueAnimator valueAnimator2 = new ValueAnimator();
            this.s = valueAnimator2;
            valueAnimator2.setInterpolator(com.jd.lib.un.basewidget.widget.tab.a.a);
            valueAnimator2.setDuration(i3);
            valueAnimator2.setFloatValues(0.0f, 1.0f);
            valueAnimator2.addUpdateListener(new a(i4, i6, i5, i7));
            valueAnimator2.addListener(new b(i2));
            valueAnimator2.start();
        }

        boolean d() {
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                if (getChildAt(i2).getWidth() <= 0) {
                    return true;
                }
            }
            return false;
        }

        @Override // android.view.View
        public void draw(Canvas canvas) {
            super.draw(canvas);
        }

        View e(View view) {
            if (view instanceof g) {
                g gVar = (g) view;
                return gVar.f5840j != null ? gVar.f5840j : gVar;
            }
            return view;
        }

        View f(View view) {
            if (view instanceof g) {
                g gVar = (g) view;
                if (gVar.f5839i != null) {
                    return gVar.f5839i;
                }
                return gVar.f5842l != null ? gVar.f5842l : gVar;
            }
            return view;
        }

        int g(View view) {
            int i2 = this.f5822k;
            if (i2 < 0) {
                if (i2 != -4) {
                    if (i2 != -3) {
                        if (i2 != -2) {
                            return view.getHeight();
                        }
                        return i(view).getHeight();
                    }
                    return f(view).getHeight();
                }
                return e(view).getHeight();
            }
            return i2;
        }

        int h(View view) {
            int i2 = this.f5821j;
            if (i2 < 0) {
                if (i2 != -4) {
                    if (i2 != -3) {
                        if (i2 != -2) {
                            return view.getWidth();
                        }
                        return i(view).getWidth();
                    }
                    return f(view).getWidth();
                }
                return e(view).getWidth();
            }
            return i2;
        }

        View i(View view) {
            if (view instanceof g) {
                g gVar = (g) view;
                if (gVar.f5838h != null) {
                    return gVar.f5838h;
                }
                return gVar.f5841k != null ? gVar.f5841k : gVar;
            }
            return view;
        }

        int[] j(View view) {
            int h2 = h(view);
            int left = view.getLeft() + ((view.getWidth() - h2) / 2);
            return new int[]{left, left + h2};
        }

        void k(int i2, int i3) {
            if (i2 == this.q && i3 == this.r) {
                return;
            }
            this.q = i2;
            this.r = i3;
            ViewCompat.postInvalidateOnAnimation(this);
        }

        void l(int i2, float f2) {
            ValueAnimator valueAnimator = this.s;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.s.cancel();
            }
            this.f5825n = i2;
            this.o = f2;
            t();
        }

        void m(int i2) {
            if (this.f5824m.getColor() != i2) {
                this.f5824m.setColor(i2);
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        void n(Drawable drawable) {
            Drawable drawable2 = this.f5820i;
            if (drawable2 == null || drawable2 != drawable) {
                this.f5820i = drawable;
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        void o(int i2) {
            if (this.f5818g != i2) {
                this.f5818g = i2;
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        @Override // android.widget.LinearLayout, android.view.View
        protected void onDraw(Canvas canvas) {
            int i2;
            int i3;
            int i4;
            super.onDraw(canvas);
            int i5 = this.q;
            if (i5 < 0 || (i2 = this.r) <= i5) {
                return;
            }
            int g2 = g(getChildAt(this.f5825n));
            int i6 = this.f5818g;
            if (i6 == 0) {
                int i7 = this.f5823l;
                i3 = g2 + 0 + i7;
                i4 = i7 + 0;
            } else if (i6 != 1) {
                int height = getHeight() - g2;
                int height2 = getHeight();
                int i8 = this.f5823l;
                i4 = height - i8;
                i3 = height2 - i8;
            } else {
                i4 = (getHeight() / 2) - (g2 / 2);
                i3 = g2 + i4;
            }
            Drawable drawable = this.f5820i;
            if (drawable != null) {
                drawable.setBounds(i5, i4, i2, i3);
                this.f5820i.draw(canvas);
                return;
            }
            canvas.drawRect(i5, i4, i2, i3, this.f5824m);
        }

        @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
            super.onLayout(z, i2, i3, i4, i5);
            ValueAnimator valueAnimator = this.s;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.s.cancel();
                b(this.f5825n, Math.round((1.0f - this.s.getAnimatedFraction()) * ((float) this.s.getDuration())));
                return;
            }
            t();
        }

        @Override // android.widget.LinearLayout, android.view.View
        protected void onMeasure(int i2, int i3) {
            super.onMeasure(i2, i3);
            if (View.MeasureSpec.getMode(i2) != 1073741824) {
                return;
            }
            ExTabLayout exTabLayout = ExTabLayout.this;
            boolean z = true;
            if (exTabLayout.z == 1 && exTabLayout.y == 1) {
                int childCount = getChildCount();
                int i4 = 0;
                for (int i5 = 0; i5 < childCount; i5++) {
                    View childAt = getChildAt(i5);
                    if (childAt.getVisibility() == 0) {
                        i4 = Math.max(i4, childAt.getMeasuredWidth());
                    }
                }
                if (i4 <= 0) {
                    return;
                }
                if (i4 * childCount <= getMeasuredWidth() - (ExTabLayout.this.r(16) * 2)) {
                    boolean z2 = false;
                    for (int i6 = 0; i6 < childCount; i6++) {
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getChildAt(i6).getLayoutParams();
                        if (layoutParams.width != i4 || layoutParams.weight != 0.0f) {
                            layoutParams.width = i4;
                            layoutParams.weight = 0.0f;
                            z2 = true;
                        }
                    }
                    z = z2;
                } else {
                    ExTabLayout exTabLayout2 = ExTabLayout.this;
                    exTabLayout2.y = 0;
                    exTabLayout2.P(false);
                }
                if (z) {
                    super.onMeasure(i2, i3);
                }
            }
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onRtlPropertiesChanged(int i2) {
            super.onRtlPropertiesChanged(i2);
            if (Build.VERSION.SDK_INT >= 23 || this.p == i2) {
                return;
            }
            requestLayout();
            this.p = i2;
        }

        void p(int i2) {
            if (this.f5822k != i2) {
                this.f5822k = i2;
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        void q(int i2) {
            if (this.f5823l != i2) {
                this.f5823l = i2;
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        void r(float f2) {
            if (f2 >= 0.0f && f2 <= 1.0f) {
                if (this.f5819h != f2) {
                    this.f5819h = f2;
                    ViewCompat.postInvalidateOnAnimation(this);
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("stretch should be >=0 && <=1");
        }

        void s(int i2) {
            if (this.f5821j != i2) {
                this.f5821j = i2;
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }
    }

    /* loaded from: classes16.dex */
    public static final class f {
        private Drawable a;
        private CharSequence b;

        /* renamed from: c  reason: collision with root package name */
        private CharSequence f5833c;
        private int d = -1;

        /* renamed from: e  reason: collision with root package name */
        private View f5834e;

        /* renamed from: f  reason: collision with root package name */
        ExTabLayout f5835f;

        /* renamed from: g  reason: collision with root package name */
        g f5836g;

        f() {
        }

        @Nullable
        public CharSequence a() {
            return this.f5833c;
        }

        @Nullable
        public View b() {
            return this.f5834e;
        }

        @Nullable
        public Drawable c() {
            return this.a;
        }

        public int d() {
            return this.d;
        }

        @Nullable
        public CharSequence e() {
            return this.b;
        }

        public boolean f() {
            ExTabLayout exTabLayout = this.f5835f;
            if (exTabLayout != null) {
                return exTabLayout.u() == this.d;
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        void g() {
            this.f5835f = null;
            this.f5836g = null;
            this.a = null;
            this.b = null;
            this.f5833c = null;
            this.d = -1;
            this.f5834e = null;
        }

        public void h() {
            ExTabLayout exTabLayout = this.f5835f;
            if (exTabLayout != null) {
                exTabLayout.F(this);
                return;
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        @NonNull
        public f i(@Nullable CharSequence charSequence) {
            this.f5833c = charSequence;
            o();
            return this;
        }

        @NonNull
        public f j(@LayoutRes int i2) {
            k(LayoutInflater.from(this.f5836g.getContext()).inflate(i2, (ViewGroup) this.f5836g, false));
            return this;
        }

        @NonNull
        public f k(@Nullable View view) {
            this.f5834e = view;
            o();
            return this;
        }

        @NonNull
        public f l(@Nullable Drawable drawable) {
            this.a = drawable;
            o();
            return this;
        }

        void m(int i2) {
            this.d = i2;
        }

        @NonNull
        public f n(@Nullable CharSequence charSequence) {
            this.b = charSequence;
            o();
            return this;
        }

        void o() {
            g gVar = this.f5836g;
            if (gVar != null) {
                gVar.i();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class g extends LinearLayout {

        /* renamed from: g  reason: collision with root package name */
        private f f5837g;

        /* renamed from: h  reason: collision with root package name */
        private TextView f5838h;

        /* renamed from: i  reason: collision with root package name */
        private ImageView f5839i;

        /* renamed from: j  reason: collision with root package name */
        private View f5840j;

        /* renamed from: k  reason: collision with root package name */
        private TextView f5841k;

        /* renamed from: l  reason: collision with root package name */
        private ImageView f5842l;

        /* renamed from: m  reason: collision with root package name */
        private int f5843m;

        public g(Context context) {
            super(context);
            this.f5843m = 2;
            int i2 = ExTabLayout.this.s;
            if (i2 != 0) {
                ViewCompat.setBackground(this, AppCompatResources.getDrawable(context, i2));
            }
            ViewCompat.setPaddingRelative(this, ExTabLayout.this.f5807j, ExTabLayout.this.f5808k, ExTabLayout.this.f5809l, ExTabLayout.this.f5810m);
            setGravity(17);
            setOrientation(1);
            setClickable(true);
            ViewCompat.setPointerIcon(this, PointerIconCompat.getSystemIcon(getContext(), 1002));
        }

        private float f(Layout layout, int i2, float f2) {
            return layout.getLineWidth(i2) * (f2 / layout.getPaint().getTextSize());
        }

        private void j(@Nullable TextView textView, @Nullable ImageView imageView) {
            f fVar = this.f5837g;
            Drawable c2 = fVar != null ? fVar.c() : null;
            f fVar2 = this.f5837g;
            CharSequence e2 = fVar2 != null ? fVar2.e() : null;
            f fVar3 = this.f5837g;
            CharSequence a = fVar3 != null ? fVar3.a() : null;
            int i2 = 0;
            if (imageView != null) {
                if (c2 != null) {
                    imageView.setImageDrawable(c2);
                    imageView.setVisibility(0);
                    setVisibility(0);
                } else {
                    imageView.setVisibility(8);
                    imageView.setImageDrawable(null);
                }
                imageView.setContentDescription(a);
            }
            boolean z = !TextUtils.isEmpty(e2);
            if (textView != null) {
                if (z) {
                    textView.setText(e2);
                    textView.setVisibility(0);
                    setVisibility(0);
                } else {
                    textView.setVisibility(8);
                    textView.setText((CharSequence) null);
                }
                textView.setContentDescription(a);
            }
            if (imageView != null) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
                if (z && imageView.getVisibility() == 0) {
                    i2 = ExTabLayout.this.r(8);
                }
                if (i2 != marginLayoutParams.bottomMargin) {
                    marginLayoutParams.bottomMargin = i2;
                    imageView.requestLayout();
                }
            }
            TooltipCompat.setTooltipText(this, z ? null : a);
        }

        void g() {
            h(null);
            setSelected(false);
        }

        void h(@Nullable f fVar) {
            if (fVar != this.f5837g) {
                this.f5837g = fVar;
                i();
            }
        }

        final void i() {
            f fVar = this.f5837g;
            View b = fVar != null ? fVar.b() : null;
            if (b != null) {
                ViewParent parent = b.getParent();
                if (parent != this) {
                    if (parent != null) {
                        ((ViewGroup) parent).removeView(b);
                    }
                    addView(b);
                }
                this.f5840j = b;
                TextView textView = this.f5838h;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.f5839i;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.f5839i.setImageDrawable(null);
                }
                TextView textView2 = (TextView) b.findViewById(16908308);
                this.f5841k = textView2;
                if (textView2 != null) {
                    this.f5843m = TextViewCompat.getMaxLines(textView2);
                }
                this.f5842l = (ImageView) b.findViewById(16908294);
            } else {
                View view = this.f5840j;
                if (view != null) {
                    removeView(view);
                    this.f5840j = null;
                }
                this.f5841k = null;
                this.f5842l = null;
            }
            boolean z = false;
            if (this.f5840j == null) {
                if (this.f5839i == null) {
                    ImageView imageView2 = (ImageView) LayoutInflater.from(getContext()).inflate(R.layout.extab_icon, (ViewGroup) this, false);
                    addView(imageView2, 0);
                    this.f5839i = imageView2;
                }
                if (this.f5838h == null) {
                    TextView textView3 = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.extab_text, (ViewGroup) this, false);
                    addView(textView3);
                    this.f5838h = textView3;
                    this.f5843m = TextViewCompat.getMaxLines(textView3);
                }
                TextViewCompat.setTextAppearance(this.f5838h, ExTabLayout.this.f5811n);
                ColorStateList colorStateList = ExTabLayout.this.o;
                if (colorStateList != null) {
                    this.f5838h.setTextColor(colorStateList);
                }
                ExTabLayout exTabLayout = ExTabLayout.this;
                if (exTabLayout.q != exTabLayout.p) {
                    TextView textView4 = this.f5838h;
                    textView4.setTextSize(textView4.isSelected() ? ExTabLayout.this.q : ExTabLayout.this.p);
                }
                j(this.f5838h, this.f5839i);
            } else {
                TextView textView5 = this.f5841k;
                if (textView5 != null || this.f5842l != null) {
                    j(textView5, this.f5842l);
                }
            }
            if (fVar != null && fVar.f()) {
                z = true;
            }
            setSelected(z);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName(ActionBar.Tab.class.getName());
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName(ActionBar.Tab.class.getName());
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int i2, int i3) {
            Layout layout;
            int size = View.MeasureSpec.getSize(i2);
            int mode = View.MeasureSpec.getMode(i2);
            int x = ExTabLayout.this.x();
            if (x > 0 && (mode == 0 || size > x)) {
                i2 = View.MeasureSpec.makeMeasureSpec(ExTabLayout.this.t, Integer.MIN_VALUE);
            }
            super.onMeasure(i2, i3);
            if (this.f5838h != null) {
                getResources();
                float f2 = this.f5838h.isSelected() ? ExTabLayout.this.q : ExTabLayout.this.p;
                int i4 = this.f5843m;
                ImageView imageView = this.f5839i;
                boolean z = true;
                if (imageView == null || imageView.getVisibility() != 0) {
                    TextView textView = this.f5838h;
                    if (textView != null && textView.getLineCount() > 1) {
                        f2 = this.f5838h.isSelected() ? ExTabLayout.this.q : ExTabLayout.this.r;
                    }
                } else {
                    i4 = 1;
                }
                float textSize = this.f5838h.getTextSize();
                int lineCount = this.f5838h.getLineCount();
                int maxLines = TextViewCompat.getMaxLines(this.f5838h);
                if (f2 != textSize || (maxLines >= 0 && i4 != maxLines)) {
                    if (ExTabLayout.this.z == 1 && f2 > textSize && lineCount == 1 && ((layout = this.f5838h.getLayout()) == null || f(layout, 0, f2) > (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight())) {
                        z = false;
                    }
                    if (z) {
                        this.f5838h.setTextSize(0, f2);
                        this.f5838h.setMaxLines(i4);
                        super.onMeasure(i2, i3);
                    }
                }
            }
        }

        @Override // android.view.View
        public boolean performClick() {
            boolean performClick = super.performClick();
            if (this.f5837g != null) {
                if (!performClick) {
                    playSoundEffect(0);
                }
                this.f5837g.h();
                return true;
            }
            return performClick;
        }

        @Override // android.view.View
        public void setSelected(boolean z) {
            boolean z2 = isSelected() != z;
            super.setSelected(z);
            if (z2 && z && Build.VERSION.SDK_INT < 16) {
                sendAccessibilityEvent(4);
            }
            TextView textView = this.f5838h;
            if (textView != null) {
                textView.setSelected(z);
                if (z2) {
                    TextView textView2 = this.f5838h;
                    ExTabLayout exTabLayout = ExTabLayout.this;
                    textView2.setTextSize(0, z ? exTabLayout.q : exTabLayout.p);
                }
            }
            ImageView imageView = this.f5839i;
            if (imageView != null) {
                imageView.setSelected(z);
            }
            View view = this.f5840j;
            if (view != null) {
                view.setSelected(z);
            }
        }
    }

    /* loaded from: classes16.dex */
    public static class h implements c {
        private final ViewPager a;

        public h(ViewPager viewPager) {
            this.a = viewPager;
        }

        @Override // com.jd.lib.un.basewidget.widget.tab.ExTabLayout.c
        public void a(f fVar) {
        }

        @Override // com.jd.lib.un.basewidget.widget.tab.ExTabLayout.c
        public void b(f fVar) {
        }

        @Override // com.jd.lib.un.basewidget.widget.tab.ExTabLayout.c
        public void c(f fVar) {
            this.a.setCurrentItem(fVar.d());
        }
    }

    public ExTabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void E(int i2) {
        g gVar = (g) this.f5806i.getChildAt(i2);
        this.f5806i.removeViewAt(i2);
        if (gVar != null) {
            gVar.g();
            this.J.release(gVar);
        }
        requestLayout();
    }

    private void K(int i2) {
        int childCount = this.f5806i.getChildCount();
        if (i2 < childCount) {
            int i3 = 0;
            while (i3 < childCount) {
                this.f5806i.getChildAt(i3).setSelected(i3 == i2);
                i3++;
            }
        }
    }

    private void N(@Nullable ViewPager viewPager, boolean z, boolean z2) {
        ViewPager viewPager2 = this.D;
        if (viewPager2 != null) {
            TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener = this.G;
            if (tabLayoutOnPageChangeListener != null) {
                viewPager2.removeOnPageChangeListener(tabLayoutOnPageChangeListener);
            }
            b bVar = this.H;
            if (bVar != null) {
                this.D.removeOnAdapterChangeListener(bVar);
            }
        }
        c cVar = this.B;
        if (cVar != null) {
            D(cVar);
            this.B = null;
        }
        if (viewPager != null) {
            this.D = viewPager;
            if (this.G == null) {
                this.G = new TabLayoutOnPageChangeListener(this);
            }
            this.G.d();
            viewPager.addOnPageChangeListener(this.G);
            h hVar = new h(viewPager);
            this.B = hVar;
            a(hVar);
            PagerAdapter adapter = viewPager.getAdapter();
            if (adapter != null) {
                H(adapter, z);
            }
            if (this.H == null) {
                this.H = new b();
            }
            this.H.a(z);
            viewPager.addOnAdapterChangeListener(this.H);
            I(viewPager.getCurrentItem(), 0.0f, true);
        } else {
            this.D = null;
            H(null, false);
        }
        this.I = z2;
    }

    private void O(LinearLayout.LayoutParams layoutParams) {
        if (this.z == 1 && this.y == 0) {
            layoutParams.width = 0;
            layoutParams.weight = 1.0f;
            return;
        }
        layoutParams.width = -2;
        layoutParams.weight = 0.0f;
    }

    private void e(@NonNull TabItem tabItem) {
        f A = A();
        CharSequence charSequence = tabItem.f5845g;
        if (charSequence != null) {
            A.n(charSequence);
        }
        Drawable drawable = tabItem.f5846h;
        if (drawable != null) {
            A.l(drawable);
        }
        int i2 = tabItem.f5847i;
        if (i2 != 0) {
            A.j(i2);
        }
        if (!TextUtils.isEmpty(tabItem.getContentDescription())) {
            A.i(tabItem.getContentDescription());
        }
        b(A);
    }

    private void f(f fVar) {
        this.f5806i.addView(fVar.f5836g, fVar.d(), m());
    }

    private void g(View view) {
        if (view instanceof TabItem) {
            e((TabItem) view);
            return;
        }
        throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
    }

    private void h(int i2) {
        if (i2 == -1) {
            return;
        }
        if (getWindowToken() != null && ViewCompat.isLaidOut(this) && !this.f5806i.d()) {
            int scrollX = getScrollX();
            int j2 = j(i2, 0.0f);
            if (scrollX != j2) {
                s();
                this.C.setIntValues(scrollX, j2);
                this.C.start();
            }
            this.f5806i.b(i2, 300);
            return;
        }
        I(i2, 0.0f, true);
    }

    private void i() {
        ViewCompat.setPaddingRelative(this.f5806i, this.z == 0 ? Math.max(0, this.x - this.f5807j) : 0, 0, 0, 0);
        int i2 = this.z;
        if (i2 == 0) {
            this.f5806i.setGravity(GravityCompat.START);
        } else if (i2 == 1) {
            this.f5806i.setGravity(1);
        }
        P(true);
    }

    private int j(int i2, float f2) {
        if (this.z == 0) {
            View childAt = this.f5806i.getChildAt(i2);
            int i3 = i2 + 1;
            View childAt2 = i3 < this.f5806i.getChildCount() ? this.f5806i.getChildAt(i3) : null;
            int width = childAt != null ? childAt.getWidth() : 0;
            int width2 = childAt2 != null ? childAt2.getWidth() : 0;
            int left = (childAt.getLeft() + (width / 2)) - (getWidth() / 2);
            int i4 = (int) ((width + width2) * 0.5f * f2);
            return ViewCompat.getLayoutDirection(this) == 0 ? left + i4 : left - i4;
        }
        return 0;
    }

    private void k(f fVar, int i2) {
        fVar.m(i2);
        this.f5804g.add(i2, fVar);
        int size = this.f5804g.size();
        while (true) {
            i2++;
            if (i2 >= size) {
                return;
            }
            this.f5804g.get(i2).m(i2);
        }
    }

    private static ColorStateList l(int i2, int i3) {
        return new ColorStateList(new int[][]{HorizontalScrollView.SELECTED_STATE_SET, HorizontalScrollView.EMPTY_STATE_SET}, new int[]{i3, i2});
    }

    private LinearLayout.LayoutParams m() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
        O(layoutParams);
        return layoutParams;
    }

    private g n(@NonNull f fVar) {
        Pools.Pool<g> pool = this.J;
        g acquire = pool != null ? pool.acquire() : null;
        if (acquire == null) {
            acquire = new g(getContext());
        }
        acquire.h(fVar);
        acquire.setFocusable(true);
        acquire.setMinimumWidth(y());
        return acquire;
    }

    private void o(@NonNull f fVar) {
        for (int size = this.A.size() - 1; size >= 0; size--) {
            this.A.get(size).a(fVar);
        }
    }

    private void p(@NonNull f fVar) {
        for (int size = this.A.size() - 1; size >= 0; size--) {
            this.A.get(size).c(fVar);
        }
    }

    private void q(@NonNull f fVar) {
        for (int size = this.A.size() - 1; size >= 0; size--) {
            this.A.get(size).b(fVar);
        }
    }

    private void s() {
        if (this.C == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.C = valueAnimator;
            valueAnimator.setInterpolator(com.jd.lib.un.basewidget.widget.tab.a.a);
            this.C.setDuration(300L);
            this.C.addUpdateListener(new a());
        }
    }

    private int t() {
        int size = this.f5804g.size();
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 < size) {
                f fVar = this.f5804g.get(i2);
                if (fVar != null && fVar.c() != null && !TextUtils.isEmpty(fVar.e())) {
                    z = true;
                    break;
                }
                i2++;
            } else {
                break;
            }
        }
        return z ? 72 : 48;
    }

    private int y() {
        int i2 = this.u;
        if (i2 != -1) {
            return i2;
        }
        if (this.z == 0) {
            return this.w;
        }
        return 0;
    }

    private int z() {
        return Math.max(0, ((this.f5806i.getWidth() - getWidth()) - getPaddingLeft()) - getPaddingRight());
    }

    @NonNull
    public f A() {
        f acquire = K.acquire();
        if (acquire == null) {
            acquire = new f();
        }
        acquire.f5835f = this;
        acquire.f5836g = n(acquire);
        return acquire;
    }

    void B() {
        int currentItem;
        C();
        PagerAdapter pagerAdapter = this.E;
        if (pagerAdapter != null) {
            int count = pagerAdapter.getCount();
            for (int i2 = 0; i2 < count; i2++) {
                f A = A();
                A.n(this.E.getPageTitle(i2));
                d(A, false);
            }
            ViewPager viewPager = this.D;
            if (viewPager == null || count <= 0 || (currentItem = viewPager.getCurrentItem()) == u() || currentItem >= w()) {
                return;
            }
            F(v(currentItem));
        }
    }

    public void C() {
        for (int childCount = this.f5806i.getChildCount() - 1; childCount >= 0; childCount--) {
            E(childCount);
        }
        Iterator<f> it = this.f5804g.iterator();
        while (it.hasNext()) {
            f next = it.next();
            it.remove();
            next.g();
            K.release(next);
        }
        this.f5805h = null;
    }

    public void D(@NonNull c cVar) {
        this.A.remove(cVar);
    }

    void F(f fVar) {
        G(fVar, true);
    }

    void G(f fVar, boolean z) {
        f fVar2 = this.f5805h;
        if (fVar2 == fVar) {
            if (fVar2 != null) {
                o(fVar);
                h(fVar.d());
                return;
            }
            return;
        }
        int d2 = fVar != null ? fVar.d() : -1;
        if (z) {
            if ((fVar2 == null || fVar2.d() == -1) && d2 != -1) {
                I(d2, 0.0f, true);
            } else {
                h(d2);
            }
            if (d2 != -1) {
                K(d2);
            }
        }
        if (fVar2 != null) {
            q(fVar2);
        }
        this.f5805h = fVar;
        if (fVar != null) {
            p(fVar);
        }
    }

    void H(@Nullable PagerAdapter pagerAdapter, boolean z) {
        DataSetObserver dataSetObserver;
        PagerAdapter pagerAdapter2 = this.E;
        if (pagerAdapter2 != null && (dataSetObserver = this.F) != null) {
            pagerAdapter2.unregisterDataSetObserver(dataSetObserver);
        }
        this.E = pagerAdapter;
        if (z && pagerAdapter != null) {
            if (this.F == null) {
                this.F = new d();
            }
            pagerAdapter.registerDataSetObserver(this.F);
        }
        B();
    }

    public void I(int i2, float f2, boolean z) {
        J(i2, f2, z, true);
    }

    void J(int i2, float f2, boolean z, boolean z2) {
        int round = Math.round(i2 + f2);
        if (round < 0 || round >= this.f5806i.getChildCount()) {
            return;
        }
        if (z2) {
            this.f5806i.l(i2, f2);
        }
        ValueAnimator valueAnimator = this.C;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.C.cancel();
        }
        scrollTo(j(i2, f2), 0);
        if (z) {
            K(round);
        }
    }

    public void L(@Nullable ViewPager viewPager) {
        M(viewPager, true);
    }

    public void M(@Nullable ViewPager viewPager, boolean z) {
        N(viewPager, z, false);
    }

    void P(boolean z) {
        for (int i2 = 0; i2 < this.f5806i.getChildCount(); i2++) {
            View childAt = this.f5806i.getChildAt(i2);
            childAt.setMinimumWidth(y());
            O((LinearLayout.LayoutParams) childAt.getLayoutParams());
            if (z) {
                childAt.requestLayout();
            }
        }
    }

    public void a(@NonNull c cVar) {
        if (this.A.contains(cVar)) {
            return;
        }
        this.A.add(cVar);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view) {
        g(view);
    }

    public void b(@NonNull f fVar) {
        d(fVar, this.f5804g.isEmpty());
    }

    public void c(@NonNull f fVar, int i2, boolean z) {
        if (fVar.f5835f == this) {
            k(fVar, i2);
            f(fVar);
            if (z) {
                fVar.h();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
    }

    public void d(@NonNull f fVar, boolean z) {
        c(fVar, this.f5804g.size(), z);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.D == null) {
            ViewParent parent = getParent();
            if (parent instanceof ViewPager) {
                N((ViewPager) parent, true, true);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.I) {
            L(null);
            this.I = false;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0067, code lost:
        if (r1.getMeasuredWidth() != getMeasuredWidth()) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0072, code lost:
        if (r1.getMeasuredWidth() < getMeasuredWidth()) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0075, code lost:
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0076, code lost:
        r6 = r0;
     */
    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onMeasure(int r6, int r7) {
        /*
            r5 = this;
            int r0 = r5.t()
            int r0 = r5.r(r0)
            int r1 = r5.getPaddingTop()
            int r0 = r0 + r1
            int r1 = r5.getPaddingBottom()
            int r0 = r0 + r1
            int r1 = android.view.View.MeasureSpec.getMode(r7)
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = 1073741824(0x40000000, float:2.0)
            if (r1 == r2) goto L24
            if (r1 == 0) goto L1f
            goto L30
        L1f:
            int r7 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r3)
            goto L30
        L24:
            int r7 = android.view.View.MeasureSpec.getSize(r7)
            int r7 = java.lang.Math.min(r0, r7)
            int r7 = android.view.View.MeasureSpec.makeMeasureSpec(r7, r3)
        L30:
            int r0 = android.view.View.MeasureSpec.getSize(r6)
            int r1 = android.view.View.MeasureSpec.getMode(r6)
            if (r1 == 0) goto L49
            int r1 = r5.v
            if (r1 <= 0) goto L3f
            goto L47
        L3f:
            r1 = 56
            int r1 = r5.r(r1)
            int r1 = r0 - r1
        L47:
            r5.t = r1
        L49:
            super.onMeasure(r6, r7)
            int r6 = r5.getChildCount()
            r0 = 1
            if (r6 != r0) goto L97
            r6 = 0
            android.view.View r1 = r5.getChildAt(r6)
            int r2 = r5.z
            if (r2 == 0) goto L6a
            if (r2 == r0) goto L5f
            goto L77
        L5f:
            int r2 = r1.getMeasuredWidth()
            int r4 = r5.getMeasuredWidth()
            if (r2 == r4) goto L75
            goto L76
        L6a:
            int r2 = r1.getMeasuredWidth()
            int r4 = r5.getMeasuredWidth()
            if (r2 >= r4) goto L75
            goto L76
        L75:
            r0 = 0
        L76:
            r6 = r0
        L77:
            if (r6 == 0) goto L97
            int r6 = r5.getPaddingTop()
            int r0 = r5.getPaddingBottom()
            int r6 = r6 + r0
            android.view.ViewGroup$LayoutParams r0 = r1.getLayoutParams()
            int r0 = r0.height
            int r6 = android.widget.HorizontalScrollView.getChildMeasureSpec(r7, r6, r0)
            int r7 = r5.getMeasuredWidth()
            int r7 = android.view.View.MeasureSpec.makeMeasureSpec(r7, r3)
            r1.measure(r7, r6)
        L97:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.un.basewidget.widget.tab.ExTabLayout.onMeasure(int, int):void");
    }

    int r(int i2) {
        return Math.round(UnDeviceInfo.getDensity() * i2);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return z() > 0;
    }

    public int u() {
        f fVar = this.f5805h;
        if (fVar != null) {
            return fVar.d();
        }
        return -1;
    }

    @Nullable
    public f v(int i2) {
        if (i2 < 0 || i2 >= w()) {
            return null;
        }
        return this.f5804g.get(i2);
    }

    public int w() {
        return this.f5804g.size();
    }

    int x() {
        return this.t;
    }

    public ExTabLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f5804g = new ArrayList<>();
        this.t = Integer.MAX_VALUE;
        this.A = new ArrayList<>();
        this.J = new Pools.SimplePool(12);
        setHorizontalScrollBarEnabled(false);
        e eVar = new e(context);
        this.f5806i = eVar;
        super.addView(eVar, 0, new FrameLayout.LayoutParams(-2, -1));
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ExTabLayout, i2, R.style.LibraryTabLayout);
        int i3 = R.styleable.ExTabLayout_exTabIndicatorStretch;
        if (obtainStyledAttributes.hasValue(i3)) {
            eVar.r(obtainStyledAttributes.getFloat(i3, 0.0f));
        }
        if (Build.VERSION.SDK_INT >= 21) {
            int i4 = R.styleable.ExTabLayout_exTabIndicatorWidth;
            if (obtainStyledAttributes.getType(i4) == 16) {
                eVar.s(obtainStyledAttributes.getInt(i4, -1));
            } else {
                eVar.s(obtainStyledAttributes.getDimensionPixelSize(i4, 0));
            }
            int i5 = R.styleable.ExTabLayout_exTabIndicatorHeight;
            if (obtainStyledAttributes.getType(i5) == 16) {
                eVar.p(obtainStyledAttributes.getInt(i5, -1));
            } else {
                eVar.p(obtainStyledAttributes.getDimensionPixelSize(i5, 0));
            }
        } else {
            TypedValue typedValue = new TypedValue();
            int i6 = R.styleable.ExTabLayout_exTabIndicatorWidth;
            if (obtainStyledAttributes.getValue(i6, typedValue)) {
                if (typedValue.type == 16) {
                    eVar.s(obtainStyledAttributes.getInt(i6, -1));
                } else {
                    eVar.s(obtainStyledAttributes.getDimensionPixelSize(i6, 0));
                }
            } else {
                try {
                    eVar.s(obtainStyledAttributes.getInt(i6, -1));
                } catch (RuntimeException unused) {
                    this.f5806i.s(obtainStyledAttributes.getDimensionPixelSize(R.styleable.ExTabLayout_exTabIndicatorWidth, 0));
                }
            }
            int i7 = R.styleable.ExTabLayout_exTabIndicatorHeight;
            if (obtainStyledAttributes.getValue(i7, typedValue)) {
                if (typedValue.type == 16) {
                    this.f5806i.p(obtainStyledAttributes.getInt(i7, -1));
                } else {
                    this.f5806i.p(obtainStyledAttributes.getDimensionPixelSize(i7, 0));
                }
            } else {
                try {
                    this.f5806i.p(obtainStyledAttributes.getInt(i7, -1));
                } catch (RuntimeException unused2) {
                    this.f5806i.p(obtainStyledAttributes.getDimensionPixelSize(R.styleable.ExTabLayout_exTabIndicatorHeight, 0));
                }
            }
        }
        this.f5806i.q(obtainStyledAttributes.getDimensionPixelSize(R.styleable.ExTabLayout_exTabIndicatorPadding, 0));
        this.f5806i.n(obtainStyledAttributes.getDrawable(R.styleable.ExTabLayout_exTabIndicatorDrawable));
        this.f5806i.o(obtainStyledAttributes.getInt(R.styleable.ExTabLayout_exTabIndicatorGravity, 2));
        try {
            int i8 = R.styleable.ExTabLayout_exTabIndicatorColor;
            if (obtainStyledAttributes.hasValue(i8)) {
                this.f5806i.m(obtainStyledAttributes.getColor(i8, 0));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ExTabLayout_exTabPadding, 0);
        this.f5810m = dimensionPixelSize;
        this.f5809l = dimensionPixelSize;
        this.f5808k = dimensionPixelSize;
        this.f5807j = dimensionPixelSize;
        this.f5807j = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ExTabLayout_exTabPaddingStart, dimensionPixelSize);
        this.f5808k = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ExTabLayout_exTabPaddingTop, this.f5808k);
        this.f5809l = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ExTabLayout_exTabPaddingEnd, this.f5809l);
        this.f5810m = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ExTabLayout_exTabPaddingBottom, this.f5810m);
        this.f5811n = obtainStyledAttributes.getResourceId(R.styleable.ExTabLayout_exTabTextAppearance, R.style.LibraryTextAppearanceTab);
        int i9 = R.styleable.ExTabLayout_exTabTextColor;
        if (obtainStyledAttributes.hasValue(i9)) {
            this.o = obtainStyledAttributes.getColorStateList(i9);
        }
        int i10 = R.styleable.ExTabLayout_exTabSelectedTextColor;
        if (obtainStyledAttributes.hasValue(i10)) {
            int color = obtainStyledAttributes.getColor(i10, 0);
            ColorStateList colorStateList = this.o;
            this.o = l(colorStateList == null ? -16777216 : colorStateList.getDefaultColor(), color);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.ExTabLayout_exTabTextSize)) {
            this.p = obtainStyledAttributes.getDimensionPixelSize(r7, 0);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.ExTabLayout_exTabSelectedTextSize)) {
            this.q = obtainStyledAttributes.getDimensionPixelSize(r7, (int) this.p);
        } else {
            this.q = this.p;
        }
        this.u = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ExTabLayout_exTabMinWidth, -1);
        this.v = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ExTabLayout_exTabMaxWidth, -1);
        this.s = obtainStyledAttributes.getResourceId(R.styleable.ExTabLayout_exTabBackground, 0);
        this.x = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ExTabLayout_exTabContentStart, 0);
        this.z = obtainStyledAttributes.getInt(R.styleable.ExTabLayout_exTabMode, 1);
        this.y = obtainStyledAttributes.getInt(R.styleable.ExTabLayout_exTabGravity, 0);
        obtainStyledAttributes.recycle();
        Resources resources = getResources();
        this.r = resources.getDimensionPixelSize(R.dimen.design_tab_text_size_2line);
        this.w = resources.getDimensionPixelSize(R.dimen.design_tab_scrollable_min_width);
        i();
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, int i2) {
        g(view);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return generateDefaultLayoutParams();
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        g(view);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        g(view);
    }
}
