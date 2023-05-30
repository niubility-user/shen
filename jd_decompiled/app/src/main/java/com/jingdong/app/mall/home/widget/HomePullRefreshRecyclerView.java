package com.jingdong.app.mall.home.widget;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.pullrefresh.BaseLoadingView;
import com.jingdong.app.mall.home.pullrefresh.BaseVerticalRefresh;

/* loaded from: classes4.dex */
public class HomePullRefreshRecyclerView extends BaseVerticalRefresh<RecyclerView> {
    private HomeRecycleView G;
    private boolean H;
    private boolean I;
    private IHomeTitle J;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ long f11007g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ long f11008h;

        a(long j2, long j3) {
            this.f11007g = j2;
            this.f11008h = j3;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            HomePullRefreshRecyclerView.this.p0(this.f11007g, this.f11008h);
        }
    }

    /* loaded from: classes4.dex */
    class b implements BaseVerticalRefresh.j {
        final /* synthetic */ BaseVerticalRefresh.l a;

        b(BaseVerticalRefresh.l lVar) {
            this.a = lVar;
        }

        @Override // com.jingdong.app.mall.home.pullrefresh.BaseVerticalRefresh.j
        public void onSmoothScrollFinished() {
            HomePullRefreshRecyclerView.this.h(this.a);
        }
    }

    /* loaded from: classes4.dex */
    static /* synthetic */ class c {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[BaseVerticalRefresh.g.values().length];
            a = iArr;
            try {
                iArr[BaseVerticalRefresh.g.MANUAL_REFRESH_ONLY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[BaseVerticalRefresh.g.PULL_FROM_END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[BaseVerticalRefresh.g.PULL_FROM_START.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public HomePullRefreshRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.H = true;
        this.I = true;
        this.F = BaseVerticalRefresh.l.RESET;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p0(long j2, long j3) {
        if (!f.p0() && j2 <= 0) {
            BaseVerticalRefresh.l s = s();
            BaseVerticalRefresh.l lVar = BaseVerticalRefresh.l.RESET;
            if (s != lVar) {
                return;
            }
            IHomeTitle iHomeTitle = this.J;
            if (iHomeTitle != null) {
                iHomeTitle.onPullOffset(lVar, 0, j3);
            }
            W(0);
            return;
        }
        f.F0(new a(j2, j3), j2);
    }

    /*  JADX ERROR: NullPointerException in pass: RegionMakerVisitor
        java.lang.NullPointerException
        */
    @Override // com.jingdong.app.mall.home.pullrefresh.BaseVerticalRefresh
    protected boolean A() {
        /*
            r6 = this;
            T extends android.view.View r0 = r6.p
            androidx.recyclerview.widget.RecyclerView r0 = (androidx.recyclerview.widget.RecyclerView) r0
            androidx.recyclerview.widget.RecyclerView$LayoutManager r0 = r0.getLayoutManager()
            r1 = 0
            if (r0 == 0) goto L52
            T extends android.view.View r2 = r6.p
            androidx.recyclerview.widget.RecyclerView r2 = (androidx.recyclerview.widget.RecyclerView) r2
            androidx.recyclerview.widget.RecyclerView$Adapter r2 = r2.getAdapter()
            if (r2 == 0) goto L52
            boolean r2 = r0 instanceof androidx.recyclerview.widget.LinearLayoutManager
            if (r2 != 0) goto L1a
            goto L52
        L1a:
            T extends android.view.View r2 = r6.p     // Catch: java.lang.Throwable -> L52
            androidx.recyclerview.widget.RecyclerView r2 = (androidx.recyclerview.widget.RecyclerView) r2     // Catch: java.lang.Throwable -> L52
            android.view.View r2 = r2.getChildAt(r1)     // Catch: java.lang.Throwable -> L52
            if (r2 != 0) goto L26
            r3 = 0
            goto L2a
        L26:
            int r3 = r2.getHeight()     // Catch: java.lang.Throwable -> L52
        L2a:
            T extends android.view.View r4 = r6.p     // Catch: java.lang.Throwable -> L52
            androidx.recyclerview.widget.RecyclerView r4 = (androidx.recyclerview.widget.RecyclerView) r4     // Catch: java.lang.Throwable -> L52
            int r4 = r4.getHeight()     // Catch: java.lang.Throwable -> L52
            r5 = 1
            if (r3 <= r4) goto L49
            if (r2 != 0) goto L39
            r2 = 0
            goto L3d
        L39:
            int r2 = r2.getTop()     // Catch: java.lang.Throwable -> L52
        L3d:
            if (r2 != 0) goto L48
            androidx.recyclerview.widget.LinearLayoutManager r0 = (androidx.recyclerview.widget.LinearLayoutManager) r0     // Catch: java.lang.Throwable -> L52
            int r0 = r0.findFirstVisibleItemPosition()     // Catch: java.lang.Throwable -> L52
            if (r0 != 0) goto L48
            r1 = 1
        L48:
            return r1
        L49:
            androidx.recyclerview.widget.LinearLayoutManager r0 = (androidx.recyclerview.widget.LinearLayoutManager) r0     // Catch: java.lang.Throwable -> L52
            int r0 = r0.findFirstCompletelyVisibleItemPosition()     // Catch: java.lang.Throwable -> L52
            if (r0 != 0) goto L52
            r1 = 1
        L52:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.home.widget.HomePullRefreshRecyclerView.A():boolean");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.pullrefresh.BaseVerticalRefresh
    public void C(int i2, float f2) {
        super.C(i2, f2);
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.BaseVerticalRefresh
    protected void J(boolean z, BaseVerticalRefresh.l lVar) {
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
            z2 = f2.i(lVar == BaseVerticalRefresh.l.MANUAL_REFRESHING);
        }
        if (this.f10551n.showFooterLoadingLayout() && a2 != null) {
            z2 |= a2.i(lVar == BaseVerticalRefresh.l.MANUAL_REFRESHING);
        }
        if (u()) {
            if (z2) {
                return;
            }
            g(false);
        } else if (!z || z2) {
            if (z2) {
                return;
            }
            h(lVar);
        } else {
            b bVar = new b(lVar);
            int i2 = c.a[this.o.ordinal()];
            if (i2 != 1 && i2 != 2) {
                i0((-this.f10545h.getHeaderSize()) + this.f10545h.d(), this.f10545h.g(), 0L, this.f10545h.i(), bVar);
            } else {
                k0(this.f10545h.k(), bVar);
            }
        }
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.BaseVerticalRefresh
    public void M(BaseVerticalRefresh.l lVar) {
        boolean P0 = JDHomeFragment.P0();
        HomeRecycleView homeRecycleView = this.G;
        if (homeRecycleView != null) {
            homeRecycleView.t(P0 || BaseVerticalRefresh.l.RESET != lVar);
        }
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.BaseVerticalRefresh
    public void T() {
        super.T();
        p0(200L, 240L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.pullrefresh.BaseVerticalRefresh
    public void i0(int i2, long j2, long j3, float f2, BaseVerticalRefresh.j jVar) {
        if (s() == BaseVerticalRefresh.l.MANUAL_REFRESHING) {
            com.jingdong.app.mall.home.pullrefresh.a aVar = this.f10545h;
            if (aVar instanceof com.jingdong.app.mall.home.pulltorefresh.a) {
                i2 = -((com.jingdong.app.mall.home.pulltorefresh.a) aVar).m();
            }
        }
        super.i0(i2, j2, j3, f2, jVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.pullrefresh.BaseVerticalRefresh
    public void k() {
        super.k();
        p0(0L, 0L);
    }

    public void o0(IHomeTitle iHomeTitle) {
        this.J = iHomeTitle;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.pullrefresh.BaseVerticalRefresh, android.view.View
    public void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.onScrollChanged(i2, i3, i4, i5);
        IHomeTitle iHomeTitle = this.J;
        if (iHomeTitle != null) {
            iHomeTitle.onPullOffset(s(), -i3, 240L);
        }
    }

    public boolean q0() {
        return this.I && BaseVerticalRefresh.l.RESET == this.F && (this.f10549l == 0.0f || (((SystemClock.elapsedRealtime() - this.B) > 1000L ? 1 : ((SystemClock.elapsedRealtime() - this.B) == 1000L ? 0 : -1)) > 0));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.pullrefresh.BaseVerticalRefresh
    /* renamed from: r0  reason: merged with bridge method [inline-methods] */
    public HomeRecycleView j(Context context, AttributeSet attributeSet) {
        HomeRecycleView homeRecycleView = new HomeRecycleView(context);
        this.G = homeRecycleView;
        return homeRecycleView;
    }

    public void s0(boolean z) {
        this.H = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.pullrefresh.BaseVerticalRefresh
    public float t(int i2, MotionEvent motionEvent) {
        float t = super.t(i2, motionEvent);
        return t > ((float) d.d(100)) ? d.d(100) : t;
    }

    public void t0() {
        super.T();
        p0(20L, 240L);
    }

    public void u0(boolean z) {
        this.I = z;
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.BaseVerticalRefresh
    public boolean w() {
        return this.I;
    }

    @Override // com.jingdong.app.mall.home.pullrefresh.BaseVerticalRefresh
    public boolean x() {
        return this.H && super.x();
    }

    /*  JADX ERROR: NullPointerException in pass: RegionMakerVisitor
        java.lang.NullPointerException
        */
    @Override // com.jingdong.app.mall.home.pullrefresh.BaseVerticalRefresh
    protected boolean z() {
        /*
            r4 = this;
            T extends android.view.View r0 = r4.p
            androidx.recyclerview.widget.RecyclerView r0 = (androidx.recyclerview.widget.RecyclerView) r0
            androidx.recyclerview.widget.RecyclerView$LayoutManager r0 = r0.getLayoutManager()
            r1 = 0
            if (r0 == 0) goto L31
            T extends android.view.View r2 = r4.p
            androidx.recyclerview.widget.RecyclerView r2 = (androidx.recyclerview.widget.RecyclerView) r2
            androidx.recyclerview.widget.RecyclerView$Adapter r2 = r2.getAdapter()
            if (r2 == 0) goto L31
            boolean r2 = r0 instanceof androidx.recyclerview.widget.LinearLayoutManager
            if (r2 != 0) goto L1a
            goto L31
        L1a:
            androidx.recyclerview.widget.LinearLayoutManager r0 = (androidx.recyclerview.widget.LinearLayoutManager) r0     // Catch: java.lang.Throwable -> L31
            int r0 = r0.findLastCompletelyVisibleItemPosition()     // Catch: java.lang.Throwable -> L31
            T extends android.view.View r2 = r4.p     // Catch: java.lang.Throwable -> L31
            androidx.recyclerview.widget.RecyclerView r2 = (androidx.recyclerview.widget.RecyclerView) r2     // Catch: java.lang.Throwable -> L31
            androidx.recyclerview.widget.RecyclerView$Adapter r2 = r2.getAdapter()     // Catch: java.lang.Throwable -> L31
            int r2 = r2.getItemCount()     // Catch: java.lang.Throwable -> L31
            r3 = 1
            int r2 = r2 - r3
            if (r0 != r2) goto L31
            r1 = 1
        L31:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.home.widget.HomePullRefreshRecyclerView.z():boolean");
    }
}
