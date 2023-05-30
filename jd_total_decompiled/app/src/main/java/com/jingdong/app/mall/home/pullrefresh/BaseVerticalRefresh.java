package com.jingdong.app.mall.home.pullrefresh;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.annotation.Px;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.common.i.t;
import com.jingdong.app.mall.home.floor.view.view.MallFloorBanner;
import com.jingdong.common.utils.UnRefreshSoundPlay;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public abstract class BaseVerticalRefresh<T extends View> extends RelativeLayout {
    private boolean A;
    protected long B;
    private long C;
    private float D;
    private float E;
    protected l F;

    /* renamed from: g */
    protected int f10544g;

    /* renamed from: h */
    protected com.jingdong.app.mall.home.pullrefresh.a f10545h;

    /* renamed from: i */
    private float f10546i;

    /* renamed from: j */
    private float f10547j;

    /* renamed from: k */
    private float f10548k;

    /* renamed from: l */
    protected float f10549l;

    /* renamed from: m */
    protected boolean f10550m;

    /* renamed from: n */
    protected g f10551n;
    protected g o;
    protected T p;
    protected FrameLayout q;
    private RelativeLayout.LayoutParams r;
    private boolean s;
    private i<T> t;
    private h<T> u;
    private BaseVerticalRefresh<T>.k v;
    private Interpolator w;
    private ArrayList<Float> x;
    private boolean y;
    private boolean z;

    /* loaded from: classes4.dex */
    public class a implements Runnable {
        a() {
            BaseVerticalRefresh.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            BaseVerticalRefresh.this.W(0);
        }
    }

    /* loaded from: classes4.dex */
    public class b implements j {
        final /* synthetic */ l a;

        b(l lVar) {
            BaseVerticalRefresh.this = r1;
            this.a = lVar;
        }

        @Override // com.jingdong.app.mall.home.pullrefresh.BaseVerticalRefresh.j
        public void onSmoothScrollFinished() {
            BaseVerticalRefresh.this.h(this.a);
        }
    }

    /* loaded from: classes4.dex */
    class c implements Runnable {
        c() {
            BaseVerticalRefresh.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            BaseVerticalRefresh.this.requestLayout();
        }
    }

    /* loaded from: classes4.dex */
    public class d implements Runnable {
        d() {
            BaseVerticalRefresh.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            BaseLoadingView f2 = BaseVerticalRefresh.this.f10545h.f();
            if (f2 != null) {
                ViewGroup.LayoutParams layoutParams = f2.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = BaseVerticalRefresh.this.f10544g;
                    f2.setLayoutParams(layoutParams);
                }
            }
            FrameLayout r = BaseVerticalRefresh.this.r();
            if (r != null) {
                ViewGroup.LayoutParams layoutParams2 = r.getLayoutParams();
                if (!(layoutParams2 instanceof RelativeLayout.LayoutParams)) {
                    RelativeLayout.LayoutParams layoutParams3 = BaseVerticalRefresh.this.r;
                    BaseVerticalRefresh baseVerticalRefresh = BaseVerticalRefresh.this;
                    layoutParams3.topMargin = -baseVerticalRefresh.f10544g;
                    baseVerticalRefresh.r.addRule(3, R.id.home_pull_to_refresh_header);
                    r.setLayoutParams(BaseVerticalRefresh.this.r);
                    return;
                }
                RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) layoutParams2;
                layoutParams4.topMargin = -BaseVerticalRefresh.this.f10544g;
                layoutParams4.addRule(3, R.id.home_pull_to_refresh_header);
                r.setLayoutParams(layoutParams4);
            }
        }
    }

    /* loaded from: classes4.dex */
    public class e extends com.jingdong.app.mall.home.o.a.b {
        e() {
            BaseVerticalRefresh.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            BaseVerticalRefresh.this.k();
        }
    }

    /* loaded from: classes4.dex */
    public static /* synthetic */ class f {
        static final /* synthetic */ int[] a;
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[g.values().length];
            b = iArr;
            try {
                iArr[g.PULL_FROM_END.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[g.PULL_FROM_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[g.MANUAL_REFRESH_ONLY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[g.BOTH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[l.values().length];
            a = iArr2;
            try {
                iArr2[l.RESET.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[l.PULL_TO_REFRESH.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[l.RELEASE_TO_REFRESH.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[l.REFRESHING.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[l.MANUAL_REFRESHING.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                a[l.OVERSCROLLING.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                a[l.REFRESH_COMPLETE.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    /* loaded from: classes4.dex */
    public enum g {
        DISABLED(0),
        PULL_FROM_START(1),
        PULL_FROM_END(2),
        BOTH(3),
        MANUAL_REFRESH_ONLY(4);
        
        public static final g PULL_DOWN_TO_REFRESH;
        public static final g PULL_UP_TO_REFRESH;
        private int mIntValue;

        static {
            g gVar = PULL_FROM_START;
            g gVar2 = PULL_FROM_END;
            PULL_DOWN_TO_REFRESH = gVar;
            PULL_UP_TO_REFRESH = gVar2;
        }

        g(int i2) {
            this.mIntValue = i2;
        }

        public static g a() {
            return PULL_FROM_START;
        }

        static g c(int i2) {
            for (g gVar : values()) {
                if (i2 == gVar.b()) {
                    return gVar;
                }
            }
            return a();
        }

        int b() {
            return this.mIntValue;
        }

        boolean d() {
            return (this == DISABLED || this == MANUAL_REFRESH_ONLY) ? false : true;
        }

        public boolean showFooterLoadingLayout() {
            return this == PULL_FROM_END || this == BOTH || this == MANUAL_REFRESH_ONLY;
        }

        public boolean showHeaderLoadingLayout() {
            return this == PULL_FROM_START || this == BOTH;
        }
    }

    /* loaded from: classes4.dex */
    public interface h<V extends View> {
        void onPullEvent(BaseVerticalRefresh<V> baseVerticalRefresh, l lVar, g gVar);
    }

    /* loaded from: classes4.dex */
    public interface i<V extends View> {
        void a(BaseVerticalRefresh<V> baseVerticalRefresh, l lVar);
    }

    /* loaded from: classes4.dex */
    public interface j {
        void onSmoothScrollFinished();
    }

    /* loaded from: classes4.dex */
    public final class k implements Runnable {

        /* renamed from: g */
        private final Interpolator f10557g;

        /* renamed from: h */
        private final int f10558h;

        /* renamed from: i */
        private final int f10559i;

        /* renamed from: j */
        private final long f10560j;

        /* renamed from: k */
        private j f10561k;

        /* renamed from: l */
        private boolean f10562l = true;

        /* renamed from: m */
        private long f10563m = -1;

        /* renamed from: n */
        private int f10564n = -1;
        private float o;

        public k(int i2, int i3, long j2, float f2, j jVar) {
            BaseVerticalRefresh.this = r3;
            this.o = 1.0f;
            this.f10559i = i2;
            this.f10558h = i3;
            this.f10557g = r3.w;
            this.f10560j = j2;
            this.f10561k = jVar;
            this.o = f2;
        }

        public void b() {
            this.f10562l = false;
            BaseVerticalRefresh.this.removeCallbacks(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f10563m == -1) {
                this.f10563m = System.currentTimeMillis();
            } else {
                int round = this.f10559i - Math.round((this.f10559i - this.f10558h) * this.f10557g.getInterpolation(((float) Math.max(Math.min(((System.currentTimeMillis() - this.f10563m) * 1000) / this.f10560j, 1000L), 0L)) / 1000.0f));
                this.f10564n = round;
                BaseVerticalRefresh.this.V(round, this.o);
            }
            if (this.f10562l && this.f10558h != this.f10564n) {
                com.jingdong.app.mall.home.pullrefresh.b.a(BaseVerticalRefresh.this, this);
                return;
            }
            j jVar = this.f10561k;
            if (jVar != null) {
                jVar.onSmoothScrollFinished();
            }
        }
    }

    /* loaded from: classes4.dex */
    public enum l {
        RESET(0),
        PULL_TO_REFRESH(1),
        RELEASE_TO_REFRESH(2),
        REFRESHING(8),
        MANUAL_REFRESHING(9),
        OVERSCROLLING(16),
        REFRESH_COMPLETE(17);
        
        private int mIntValue;

        l(int i2) {
            this.mIntValue = i2;
        }

        static l b(int i2) {
            for (l lVar : values()) {
                if (i2 == lVar.a()) {
                    return lVar;
                }
            }
            return RESET;
        }

        int a() {
            return this.mIntValue;
        }
    }

    public BaseVerticalRefresh(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, null);
    }

    private void F(float f2, boolean z, boolean z2) {
        com.jingdong.app.mall.home.pullrefresh.a aVar = this.f10545h;
        if (aVar == null) {
            return;
        }
        BaseLoadingView f3 = aVar.f();
        BaseLoadingView a2 = this.f10545h.a();
        if (f.b[this.o.ordinal()] != 1) {
            if (f3 != null) {
                f3.d(f2, z, z2);
            }
        } else if (a2 != null) {
            a2.d(f2, z, z2);
        }
    }

    private void G(int i2) {
        com.jingdong.app.mall.home.pullrefresh.a aVar;
        BaseLoadingView f2;
        if (this.f10544g <= 0 || (aVar = this.f10545h) == null || (f2 = aVar.f()) == null) {
            return;
        }
        f2.e(i2);
    }

    private void P() {
        float f2;
        int round;
        int k2;
        int V;
        if (this.f10545h == null) {
            return;
        }
        float f3 = this.f10548k;
        float f4 = this.f10547j;
        float f5 = f3 - f4;
        if (!B()) {
            f2 = f5;
        } else if (f5 < 0.0f) {
            return;
        } else {
            f2 = -f5;
        }
        BaseLoadingView f6 = this.f10545h.f();
        BaseLoadingView a2 = this.f10545h.a();
        int[] iArr = f.b;
        if (iArr[this.o.ordinal()] != 1) {
            float min = Math.min(f2, 0.0f);
            if (B()) {
                round = Math.round(min);
            } else {
                round = Math.round(min / this.f10545h.h());
            }
            k2 = this.f10545h.getHeaderSize();
            if (B()) {
                round = -(this.f10545h.getHeaderSize() + round);
            }
        } else {
            round = Math.round(Math.max(f5, 0.0f) / this.f10545h.h());
            k2 = this.f10545h.k();
        }
        if (B()) {
            V = V(round, this.f10545h.h());
        } else {
            V = V(round, 1.0f);
        }
        if (B()) {
            if (V >= 0) {
                I();
            }
        } else if (V != 0) {
            float abs = Math.abs(V) / k2;
            if (iArr[this.o.ordinal()] != 1) {
                if (f6 != null) {
                    f6.b(abs);
                    f6.c(f4);
                }
            } else if (a2 != null) {
                a2.b(abs);
                a2.c(f4);
            }
            float f7 = -V;
            F(V, f7 > this.f10545h.b(), f7 > this.f10545h.j());
            if (u()) {
                this.F = l.RELEASE_TO_REFRESH;
                return;
            }
            l lVar = this.F;
            l lVar2 = l.PULL_TO_REFRESH;
            if (lVar != lVar2 && k2 >= Math.abs(V)) {
                f0(lVar2, new boolean[0]);
            } else if (this.F != lVar2 || k2 >= Math.abs(V)) {
            } else {
                f0(l.RELEASE_TO_REFRESH, new boolean[0]);
            }
        }
    }

    private void c(Context context, T t) {
        FrameLayout frameLayout = new FrameLayout(context);
        this.q = frameLayout;
        frameLayout.addView(t, -1, -1);
        this.q.setId(R.id.home_pull_to_refresh_wrapper);
        e(this.q, this.r);
    }

    private final void h0(int i2, long j2) {
        j0(i2, j2, 0L, null);
    }

    private void i(MotionEvent motionEvent) {
        if (u() && SystemClock.elapsedRealtime() - this.C <= 120) {
            float rawX = motionEvent.getRawX();
            float rawY = motionEvent.getRawY();
            if (Math.abs(rawX - this.D) > 10.0f || Math.abs(rawY - this.E) > 10.0f) {
                return;
            }
            com.jingdong.app.mall.home.widget.b lastCreateView = t.CAROUSELFIGURE_BANNER.getLastCreateView();
            if (lastCreateView instanceof MallFloorBanner) {
                MallFloorBanner mallFloorBanner = (MallFloorBanner) lastCreateView;
                float layoutTop = mallFloorBanner.getLayoutTop() - this.f10549l;
                float layoutHeight = mallFloorBanner.getLayoutHeight() + layoutTop;
                if (Log.D) {
                    Log.d("SimpleVerticalPullToRefreshBase", "checkAutoClick: top = " + layoutTop + "upY= " + rawY + "bottom= " + layoutHeight);
                }
                if (layoutTop >= rawY || rawY >= layoutHeight) {
                    return;
                }
                this.A = true;
                mallFloorBanner.autoPullClick();
                g(true);
            }
        }
    }

    private RelativeLayout.LayoutParams n(boolean z) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        FrameLayout r = r();
        if (r != null) {
            if (z) {
                layoutParams.addRule(10);
                layoutParams.topMargin = this.f10544g;
                ViewGroup.LayoutParams layoutParams2 = r.getLayoutParams();
                if (layoutParams2 instanceof RelativeLayout.LayoutParams) {
                    RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) layoutParams2;
                    layoutParams3.topMargin = -this.f10544g;
                    layoutParams3.addRule(3, R.id.home_pull_to_refresh_header);
                    r.setLayoutParams(layoutParams3);
                } else {
                    RelativeLayout.LayoutParams layoutParams4 = this.r;
                    layoutParams4.topMargin = -this.f10544g;
                    layoutParams4.addRule(3, R.id.home_pull_to_refresh_header);
                    r.setLayoutParams(this.r);
                }
            } else {
                layoutParams.addRule(8);
                layoutParams.addRule(3, r.getId());
            }
        }
        return layoutParams;
    }

    private int o() {
        return com.jingdong.app.mall.home.floor.common.d.d(480);
    }

    private void v(Context context, AttributeSet attributeSet) {
        if (context == null) {
            return;
        }
        if (this.f10545h == null) {
            this.f10545h = new com.jingdong.app.mall.home.pulltorefresh.a();
        }
        T j2 = j(context, attributeSet);
        this.p = j2;
        c(context, j2);
        X(this.f10545h.c(context, g.PULL_FROM_START));
        l0();
    }

    private boolean y() {
        int i2 = f.b[this.f10551n.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 4) {
                    return false;
                }
                return z() || A();
            }
            return A();
        }
        return z();
    }

    protected abstract boolean A();

    public final boolean B() {
        l lVar = this.F;
        return lVar == l.REFRESHING || lVar == l.MANUAL_REFRESHING;
    }

    public void C(int i2, float f2) {
    }

    protected void D(Bundle bundle) {
    }

    protected void E(Bundle bundle) {
    }

    protected void H() {
        BaseLoadingView f2;
        if (this.f10545h == null) {
            return;
        }
        int i2 = f.b[this.o.ordinal()];
        if (i2 == 1) {
            BaseLoadingView a2 = this.f10545h.a();
            if (a2 != null) {
                a2.g();
            }
        } else if (i2 == 2 && (f2 = this.f10545h.f()) != null) {
            O();
            f2.g();
        }
    }

    public final void I() {
        if (B() && w()) {
            f0(l.REFRESH_COMPLETE, new boolean[0]);
            f0(l.RESET, new boolean[0]);
            N();
        }
    }

    protected void J(boolean z, l lVar) {
        boolean z2;
        com.jingdong.app.mall.home.pullrefresh.a aVar = this.f10545h;
        if (aVar == null) {
            return;
        }
        BaseLoadingView f2 = aVar.f();
        BaseLoadingView a2 = this.f10545h.a();
        if (!this.f10551n.showHeaderLoadingLayout() || f2 == null) {
            z2 = false;
        } else {
            z2 = f2.i(lVar == l.MANUAL_REFRESHING);
        }
        if (this.f10551n.showFooterLoadingLayout() && a2 != null) {
            z2 |= a2.i(lVar == l.MANUAL_REFRESHING);
        }
        if (z) {
            b bVar = z2 ? null : new b(lVar);
            int i2 = f.b[this.o.ordinal()];
            if (i2 != 1 && i2 != 3) {
                i0((-this.f10545h.getHeaderSize()) + this.f10545h.d(), this.f10545h.g(), 0L, this.f10545h.i(), bVar);
            } else {
                k0(this.f10545h.k(), bVar);
            }
        } else if (!z2) {
            h(lVar);
        }
        if (z2) {
            I();
        }
    }

    protected void K() {
        BaseLoadingView f2;
        if (this.f10545h == null) {
            return;
        }
        int i2 = f.b[this.o.ordinal()];
        if (i2 != 1) {
            if (i2 == 2 && (f2 = this.f10545h.f()) != null) {
                f2.j();
                return;
            }
            return;
        }
        BaseLoadingView a2 = this.f10545h.a();
        if (a2 != null) {
            a2.j();
        }
    }

    protected void L() {
        this.B = SystemClock.elapsedRealtime();
        this.y = false;
        this.A = false;
        this.f10548k = -1.0f;
        this.z = true;
        this.f10550m = false;
        this.s = true;
        com.jingdong.app.mall.home.a.s.g(true);
        com.jingdong.app.mall.home.pullrefresh.a aVar = this.f10545h;
        if (aVar != null) {
            BaseLoadingView f2 = aVar.f();
            if (f2 != null) {
                f2.k();
            }
            BaseLoadingView a2 = this.f10545h.a();
            if (a2 != null) {
                a2.k();
            }
        }
        g0(0);
    }

    public void M(l lVar) {
    }

    public void N() {
        if (JDHomeFragment.O0()) {
            UnRefreshSoundPlay.getInstance(JdSdk.getInstance().getApplicationContext()).playRefreshEnd();
        }
    }

    public void O() {
        if (JDHomeFragment.O0() && this.z) {
            this.z = false;
            UnRefreshSoundPlay.getInstance(JdSdk.getInstance().getApplicationContext()).playRefreshStart();
        }
    }

    protected void Q() {
        com.jingdong.app.mall.home.pullrefresh.a aVar;
        BaseLoadingView f2;
        this.B = SystemClock.elapsedRealtime();
        if (this.o != g.PULL_FROM_START || (aVar = this.f10545h) == null || (f2 = aVar.f()) == null) {
            return;
        }
        f2.h();
    }

    protected final void R() {
        int i2;
        com.jingdong.app.mall.home.pullrefresh.a aVar = this.f10545h;
        if (aVar == null) {
            return;
        }
        BaseLoadingView f2 = aVar.f();
        BaseLoadingView a2 = this.f10545h.a();
        int d2 = (int) ((com.jingdong.app.mall.home.o.a.f.r(getContext()) == null ? com.jingdong.app.mall.home.floor.common.d.d(R2.attr.motionEffect_translationX) : r2.getHeight()) * 1.2f);
        int paddingLeft = getPaddingLeft();
        getPaddingTop();
        int paddingRight = getPaddingRight();
        getPaddingBottom();
        int i3 = 0;
        if (!this.f10551n.showHeaderLoadingLayout() || f2 == null) {
            i2 = 0;
        } else {
            f2.l(d2);
            i2 = -d2;
        }
        if (this.f10551n.showFooterLoadingLayout() && a2 != null) {
            a2.l(d2);
            i3 = -d2;
        }
        setPadding(paddingLeft, i2, paddingRight, i3);
    }

    protected final void S(int i2, int i3) {
        ViewGroup.LayoutParams layoutParams = this.q.getLayoutParams();
        com.jingdong.app.mall.home.o.a.f.n(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        if (layoutParams2.height != i3) {
            layoutParams2.height = i3;
            this.q.requestLayout();
        }
    }

    public void T() {
        f0(l.RESET, new boolean[0]);
    }

    public void U(boolean z) {
        if (!z && !u() && this.f10549l >= (-com.jingdong.app.mall.home.floor.common.d.d(480))) {
            k();
        } else {
            com.jingdong.app.mall.home.o.a.f.F0(new e(), 600L);
        }
    }

    public final int V(int i2, float f2) {
        if (this.f10545h == null) {
            return i2;
        }
        int round = Math.round(o() * f2);
        int min = Math.min(round, Math.max(-round, i2));
        com.jingdong.app.mall.home.floor.ctrl.h.N().j0(min);
        C(min, f2);
        if (Log.D) {
            Log.d("SimpleVerticalPullToRefreshBase", "setHeaderScroll: " + min);
        }
        BaseLoadingView f3 = this.f10545h.f();
        BaseLoadingView a2 = this.f10545h.a();
        if (this.s) {
            if (min < 0 && f3 != null) {
                f3.setVisibility(0);
            } else if (min <= 0 || a2 == null) {
                if (f3 != null) {
                    f3.setVisibility(4);
                }
                if (a2 != null) {
                    a2.setVisibility(4);
                }
            } else {
                a2.setVisibility(0);
            }
        }
        scrollTo(0, min);
        return min;
    }

    public final void W(int i2) {
        V(i2, 1.0f);
    }

    public void X(BaseLoadingView baseLoadingView) {
        com.jingdong.app.mall.home.pullrefresh.a aVar = this.f10545h;
        if (aVar == null || baseLoadingView == null) {
            return;
        }
        BaseLoadingView f2 = aVar.f();
        if (f2 != null) {
            removeView(f2);
        }
        int i2 = R.id.home_pull_to_refresh_header;
        View findViewById = findViewById(i2);
        if (findViewById != null) {
            removeView(findViewById);
        }
        baseLoadingView.setId(i2);
        RelativeLayout.LayoutParams n2 = n(true);
        baseLoadingView.setLayoutParams(n2);
        e(baseLoadingView, n2);
        this.f10545h.e(baseLoadingView);
        R();
    }

    public final void Y(g gVar) {
        if (gVar != this.f10551n) {
            if (Log.D) {
                Log.d("SimpleVerticalPullToRefreshBase", "Setting mode to: " + gVar);
            }
            this.f10551n = gVar;
            l0();
        }
    }

    public void Z(h<T> hVar) {
        this.u = hVar;
    }

    public final void a0(i<T> iVar) {
        this.t = iVar;
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (Log.D) {
            Log.d("SimpleVerticalPullToRefreshBase", "addView: " + view.getClass().getSimpleName());
        }
        T q = q();
        if (q instanceof ViewGroup) {
            ((ViewGroup) q).addView(view, i2, layoutParams);
            return;
        }
        throw new UnsupportedOperationException("Refreshable View is not a ViewGroup so can't addView");
    }

    public void b0(com.jingdong.app.mall.home.pullrefresh.a aVar) {
        if (aVar != null) {
            this.f10545h = aVar;
        }
    }

    public void c0(int i2) {
        if (this.f10545h == null || this.f10544g == i2) {
            return;
        }
        this.f10544g = i2;
        post(new d());
    }

    protected final void d(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i2, layoutParams);
    }

    public final void d0() {
        e0(true);
    }

    protected final void e(View view, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, -1, layoutParams);
    }

    public final void e0(boolean z) {
        if (B() || this.y) {
            return;
        }
        f0(l.MANUAL_REFRESHING, z);
    }

    public void f(float f2, float f3) {
        if (this.f10545h == null) {
            return;
        }
        this.y = true;
        this.f10550m = true;
        f0(l.PULL_TO_REFRESH, new boolean[0]);
        this.f10548k = 0.0f;
        this.f10547j = 0.0f + f3;
        P();
    }

    final void f0(l lVar, boolean... zArr) {
        this.F = lVar;
        if (Log.D) {
            Log.d("SimpleVerticalPullToRefreshBase", "State: " + this.F.name());
        }
        M(this.F);
        switch (f.a[this.F.ordinal()]) {
            case 1:
                L();
                break;
            case 2:
                H();
                break;
            case 3:
                K();
                break;
            case 4:
            case 5:
                J(zArr[0], this.F);
                break;
            case 6:
                postDelayed(new a(), 500L);
                break;
            case 7:
                Q();
                break;
        }
        h<T> hVar = this.u;
        if (hVar != null) {
            hVar.onPullEvent(this, this.F, this.o);
        }
    }

    public void g(boolean z) {
        if (this.F != l.RESET) {
            if (!this.A && !z) {
                T();
            } else {
                U(z);
            }
        }
        this.y = false;
    }

    protected final void g0(int i2) {
        h0(i2, this.f10545h.g());
    }

    public void h(l lVar) {
        i<T> iVar = this.t;
        if (iVar != null) {
            iVar.a(this, lVar);
        }
    }

    public void i0(int i2, long j2, long j3, float f2, j jVar) {
        BaseVerticalRefresh<T>.k kVar = this.v;
        if (kVar != null) {
            kVar.b();
        }
        int scrollY = getScrollY();
        if (scrollY != i2) {
            if (this.w == null) {
                this.w = new DecelerateInterpolator();
            }
            BaseVerticalRefresh<T>.k kVar2 = new k(scrollY, i2, j2, f2, jVar);
            this.v = kVar2;
            if (j3 > 0) {
                postDelayed(kVar2, j3);
            } else {
                post(kVar2);
            }
        }
    }

    protected abstract T j(Context context, AttributeSet attributeSet);

    protected final void j0(int i2, long j2, long j3, j jVar) {
        i0(i2, j2, j3, 1.0f, jVar);
    }

    public void k() {
        l lVar = l.RESET;
        this.F = lVar;
        BaseLoadingView m2 = m();
        if (m2 != null) {
            m2.setAlpha(0.0f);
        }
        scrollTo(0, 0);
        f0(lVar, new boolean[0]);
    }

    public final void k0(int i2, j jVar) {
        j0(i2, this.f10545h.g(), 0L, jVar);
    }

    public float l() {
        com.jingdong.app.mall.home.pullrefresh.a aVar = this.f10545h;
        if (aVar == null) {
            return 2.0f;
        }
        return aVar.h();
    }

    protected void l0() {
        BaseLoadingView baseLoadingView;
        com.jingdong.app.mall.home.pullrefresh.a aVar = this.f10545h;
        BaseLoadingView baseLoadingView2 = null;
        if (aVar != null) {
            baseLoadingView2 = aVar.f();
            baseLoadingView = this.f10545h.a();
        } else {
            baseLoadingView = null;
        }
        if (baseLoadingView2 != null) {
            if (this == baseLoadingView2.getParent()) {
                removeView(baseLoadingView2);
            }
            if (this.f10551n.showHeaderLoadingLayout()) {
                d(baseLoadingView2, 0, n(true));
            }
        }
        if (baseLoadingView != null) {
            if (this == baseLoadingView.getParent()) {
                removeView(baseLoadingView);
            }
            if (this.f10551n.showFooterLoadingLayout()) {
                e(baseLoadingView, n(false));
            }
        }
        R();
        g gVar = this.f10551n;
        if (gVar == g.BOTH) {
            gVar = g.PULL_FROM_START;
        }
        this.o = gVar;
    }

    public BaseLoadingView m() {
        com.jingdong.app.mall.home.pullrefresh.a aVar = this.f10545h;
        if (aVar == null) {
            return null;
        }
        return aVar.f();
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (x()) {
            int action = motionEvent.getAction();
            int actionIndex = motionEvent.getActionIndex();
            if (action != 3 && action != 1) {
                if (action == 0 || !this.f10550m) {
                    if (action != 0) {
                        if (action == 2 && y()) {
                            float t = t(actionIndex, motionEvent);
                            float x = motionEvent.getX();
                            float y = motionEvent.getY(actionIndex) - this.f10547j;
                            float abs = Math.abs(x - this.f10546i) * 2.0f;
                            float abs2 = Math.abs(y);
                            if (B()) {
                                y = abs2;
                            }
                            if (!this.f10551n.showHeaderLoadingLayout() || y < 1.0f || abs2 <= abs || !A()) {
                                if (this.f10551n.showFooterLoadingLayout() && y <= -1.0f && abs2 > abs && z()) {
                                    this.f10547j = t;
                                    this.f10550m = true;
                                    if (this.f10551n == g.BOTH) {
                                        this.o = g.PULL_FROM_END;
                                    }
                                }
                            } else if (y < 5.0f) {
                                return false;
                            } else {
                                this.f10547j += t;
                                this.f10550m = true;
                                if (this.f10551n == g.BOTH) {
                                    this.o = g.PULL_FROM_START;
                                }
                            }
                            this.f10546i = x;
                        }
                    } else if (this.f10550m && this.y) {
                        this.x.clear();
                        this.x.add(Float.valueOf(this.f10548k));
                    } else {
                        this.x.clear();
                        this.x.add(Float.valueOf(motionEvent.getY()));
                        if (y()) {
                            float y2 = motionEvent.getY();
                            this.f10548k = y2;
                            this.f10547j = y2;
                            this.f10546i = motionEvent.getX();
                            this.f10550m = false;
                        }
                    }
                    return this.f10550m;
                }
                return true;
            }
            this.f10550m = false;
            return false;
        }
        return false;
    }

    @Override // android.view.View
    protected final void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            Y(g.c(bundle.getInt("ptr_mode", 0)));
            this.o = g.c(bundle.getInt("ptr_current_mode", 0));
            super.onRestoreInstanceState(bundle.getParcelable("ptr_super"));
            l b2 = l.b(bundle.getInt("ptr_state", 0));
            if (b2 == l.REFRESHING || b2 == l.MANUAL_REFRESHING) {
                f0(b2, true);
            }
            D(bundle);
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    @Override // android.view.View
    protected final Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        E(bundle);
        bundle.putInt("ptr_state", this.F.a());
        bundle.putInt("ptr_mode", this.f10551n.b());
        bundle.putInt("ptr_current_mode", this.o.b());
        bundle.putParcelable("ptr_super", super.onSaveInstanceState());
        return bundle;
    }

    @Override // android.view.View
    public void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.onScrollChanged(i2, i3, i4, i5);
        com.jingdong.app.mall.home.pullrefresh.a aVar = this.f10545h;
        if (aVar == null) {
            return;
        }
        BaseLoadingView f2 = aVar.f();
        BaseLoadingView a2 = this.f10545h.a();
        if (f2 != null) {
            f2.n(this.F);
            f2.f(i2, (-i3) + this.f10544g);
        }
        if (a2 != null) {
            a2.f(i2, -i3);
        }
    }

    @Override // android.view.View
    protected final void onSizeChanged(int i2, int i3, int i4, int i5) {
        if (Log.D) {
            Log.d("SimpleVerticalPullToRefreshBase", String.format("onSizeChanged. W: %d, H: %d", Integer.valueOf(i2), Integer.valueOf(i3)));
        }
        super.onSizeChanged(i2, i3, i4, i5);
        R();
        S(i2, i3);
        post(new c());
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (x()) {
            if (B()) {
                return true;
            }
            if (motionEvent.getAction() != 0 || motionEvent.getEdgeFlags() == 0) {
                int actionIndex = motionEvent.getActionIndex();
                int action = motionEvent.getAction();
                if (action != 0) {
                    if (action != 1) {
                        if (action != 2) {
                            if (action != 3) {
                                if (action == 5) {
                                    this.x.add(Float.valueOf(motionEvent.getY()));
                                    return true;
                                } else if (action == 6) {
                                    if (actionIndex < this.x.size()) {
                                        this.x.remove(actionIndex);
                                    }
                                    return true;
                                }
                            }
                        } else if (this.f10550m) {
                            int pointerCount = motionEvent.getPointerCount();
                            float t = t(0, motionEvent);
                            if (pointerCount > 1) {
                                t = Math.max(t, t(1, motionEvent));
                            }
                            this.f10547j += t;
                            this.f10546i = motionEvent.getX();
                            P();
                            return true;
                        }
                    }
                    this.x.clear();
                    i(motionEvent);
                    if (this.f10550m) {
                        this.f10550m = false;
                        if (this.F == l.RELEASE_TO_REFRESH && this.t != null) {
                            f0(l.REFRESHING, true);
                            return true;
                        } else if (B()) {
                            g0(0);
                            I();
                            return true;
                        } else {
                            f0(l.RESET, new boolean[0]);
                            return true;
                        }
                    } else if (this.f10549l == 0.0f) {
                        l lVar = this.F;
                        l lVar2 = l.RESET;
                        if (lVar != lVar2) {
                            f0(lVar2, new boolean[0]);
                        }
                    }
                } else {
                    this.x.clear();
                    if (this.f10550m && this.y) {
                        this.C = SystemClock.elapsedRealtime();
                        this.D = motionEvent.getRawX();
                        this.E = motionEvent.getRawY();
                        this.x.add(Float.valueOf(this.f10548k));
                        return true;
                    }
                    this.x.add(Float.valueOf(motionEvent.getY()));
                    if (y()) {
                        float y = motionEvent.getY();
                        this.f10548k = y;
                        this.f10547j = y;
                        this.f10546i = motionEvent.getX();
                        return true;
                    }
                }
                if ((motionEvent.getAction() & 5) == 5) {
                    this.x.add(Float.valueOf(motionEvent.getY(actionIndex)));
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public com.jingdong.app.mall.home.pullrefresh.a p() {
        return this.f10545h;
    }

    public final T q() {
        return this.p;
    }

    public FrameLayout r() {
        return this.q;
    }

    public final l s() {
        return this.F;
    }

    @Override // android.view.View
    public void scrollTo(@Px int i2, @Px int i3) {
        super.scrollTo(i2, i3);
        this.f10549l = i3;
        G(i3);
    }

    public float t(int i2, MotionEvent motionEvent) {
        if (i2 >= this.x.size()) {
            return 0.0f;
        }
        Float f2 = this.x.get(i2);
        float y = motionEvent.getY(i2);
        if (f2 == null) {
            this.x.add(i2, Float.valueOf(y));
        } else {
            this.x.set(i2, Float.valueOf(y));
        }
        if (!this.y || motionEvent.getAction() != 2) {
            return y - (f2 != null ? f2.floatValue() : 0.0f);
        }
        this.y = false;
        return 0.0f;
    }

    public boolean u() {
        return 0.0f == this.f10548k;
    }

    protected boolean w() {
        return true;
    }

    public boolean x() {
        return this.f10551n.d();
    }

    protected abstract boolean z();

    public BaseVerticalRefresh(Context context, AttributeSet attributeSet, com.jingdong.app.mall.home.pullrefresh.a aVar) {
        super(context, attributeSet);
        this.f10544g = 0;
        this.f10548k = -1.0f;
        this.f10550m = false;
        this.f10551n = g.a();
        this.r = new RelativeLayout.LayoutParams(-1, -1);
        this.s = true;
        this.x = new ArrayList<>();
        this.y = false;
        this.z = false;
        this.F = l.RESET;
        b0(aVar);
        v(context, attributeSet);
    }
}
