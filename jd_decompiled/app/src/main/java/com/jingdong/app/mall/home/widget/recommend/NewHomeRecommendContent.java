package com.jingdong.app.mall.home.widget.recommend;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.floor.common.i.g;
import com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.app.mall.home.widget.HomeRecycleView;
import com.jingdong.app.mall.home.widget.HomeRecyclerAdapter;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.recommend.ui.RecommendChildRecyclerView;
import com.jingdong.common.recommend.ui.RecommendContentLayout;
import com.jingdong.jdsdk.utils.DPIUtil;

/* loaded from: classes4.dex */
public class NewHomeRecommendContent extends FrameLayout implements com.jingdong.app.mall.home.widget.b {

    /* renamed from: g  reason: collision with root package name */
    private final int f11073g;

    /* renamed from: h  reason: collision with root package name */
    private HomeRecommend f11074h;

    /* renamed from: i  reason: collision with root package name */
    private HomeRecycleView f11075i;

    /* renamed from: j  reason: collision with root package name */
    private RecyclerView f11076j;

    /* renamed from: k  reason: collision with root package name */
    private int f11077k;

    /* renamed from: l  reason: collision with root package name */
    private int f11078l;

    /* renamed from: m  reason: collision with root package name */
    private int f11079m;

    /* loaded from: classes4.dex */
    class a extends com.jingdong.app.mall.home.o.a.b {
        a() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            NewHomeRecommendContent newHomeRecommendContent = NewHomeRecommendContent.this;
            newHomeRecommendContent.f11078l = newHomeRecommendContent.f11075i.getWidth();
        }
    }

    /* loaded from: classes4.dex */
    class b extends HomeRecommend {
        b(RecyclerView recyclerView, BaseActivity baseActivity) {
            super(recyclerView, baseActivity);
        }

        @Override // com.jingdong.common.recommend.ui.homerecommend.HomeRecommendContentLayout, com.jingdong.common.recommend.ui.RecommendContentLayout
        public int getContentHeight() {
            return NewHomeRecommendContent.this.f11077k > 0 ? NewHomeRecommendContent.this.f11077k : super.getContentHeight();
        }
    }

    /* loaded from: classes4.dex */
    class c extends RecyclerView.OnScrollListener {
        c() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
            super.onScrollStateChanged(recyclerView, i2);
            NewHomeRecommendContent.this.m(recyclerView, i2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
            super.onScrolled(recyclerView, i2, i3);
            NewHomeRecommendContent.this.m(recyclerView, -1);
        }
    }

    /* loaded from: classes4.dex */
    class d implements RecommendContentLayout.OnRecommendChangeListener {
        d() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i2) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i2, float f2, int i3) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i2) {
        }

        @Override // com.jingdong.common.recommend.ui.RecommendContentLayout.OnRecommendChangeListener
        public void onPageSelected(RecommendChildRecyclerView recommendChildRecyclerView) {
            NewHomeRecommendContent.this.n(recommendChildRecyclerView, 0, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class e implements Runnable {
        e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (NewHomeRecommendContent.this.f11075i.getLastVisibleItem() != NewHomeRecommendContent.this.f11075i.getTotalItemCount() - 1) {
                return;
            }
            NewHomeRecommendContent.this.f11075i.f11013h.set(true);
            NewHomeRecommendContent.this.f11075i.scrollToPosition(NewHomeRecommendContent.this.f11075i.getLastVisibleItem());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class f implements Runnable {
        f(NewHomeRecommendContent newHomeRecommendContent) {
        }

        @Override // java.lang.Runnable
        public void run() {
            JDHomeFragment z0 = JDHomeFragment.z0();
            if (z0 != null) {
                z0.W0();
            }
        }
    }

    public NewHomeRecommendContent(HomeRecycleView homeRecycleView, IHomeTitle iHomeTitle, BaseActivity baseActivity) {
        super(baseActivity);
        this.f11073g = DPIUtil.dip2px(9.0f);
        this.f11079m = com.jingdong.app.mall.home.v.b.a();
        this.f11075i = homeRecycleView;
        homeRecycleView.post(new a());
        b bVar = new b(this.f11075i, baseActivity);
        this.f11074h = bVar;
        bVar.setTopSpace(iHomeTitle == null ? -1 : iHomeTitle.getBarHeightShrink());
        this.f11074h.setFromType(9);
        this.f11074h.setOnScrollListener(new c());
        this.f11074h.setOnPageChangeListener(new d());
        addView(this.f11074h);
    }

    private int f(RecyclerView recyclerView) {
        if (recyclerView != null) {
            try {
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof StaggeredGridLayoutManager) {
                    int[] iArr = new int[2];
                    ((StaggeredGridLayoutManager) layoutManager).findFirstVisibleItemPositions(iArr);
                    return Math.min(iArr[0], iArr[1]);
                } else if (layoutManager instanceof GridLayoutManager) {
                    return ((GridLayoutManager) layoutManager).findFirstVisibleItemPosition();
                } else {
                    if (layoutManager instanceof LinearLayoutManager) {
                        return ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                    }
                    return -1;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    private int h() {
        if (com.jingdong.app.mall.home.v.d.a.f()) {
            int a2 = g.a();
            if (a2 < 0) {
                a2 = 24;
            }
            return DpiUtil.dip2px(getContext(), a2);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m(RecyclerView recyclerView, int i2) {
        n(recyclerView, i2, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(RecyclerView recyclerView, int i2, boolean z) {
        this.f11076j = recyclerView;
        JDHomeFragment z0 = JDHomeFragment.z0();
        if (z0 == null) {
            return;
        }
        boolean z2 = false;
        if (i2 == 0 && this.f11074h != null && getTop() > this.f11074h.getTopSpace() && getTop() < this.f11074h.getHeight()) {
            z2 = true;
        }
        z0.X0(i2, z2, z);
    }

    public int g() {
        int f2 = f(this.f11076j);
        if (f2 <= 0) {
            return 0;
        }
        int i2 = RecommendViewUtil.homeTopSpace;
        if (i2 <= 0 || getTop() <= i2 + 10) {
            double d2 = f2 / 2;
            Double.isNaN(d2);
            return (int) (d2 + 0.5d);
        }
        return 0;
    }

    @Override // com.jingdong.app.mall.home.widget.b
    public View getContentView() {
        return this;
    }

    public int i() {
        return g() * com.jingdong.app.mall.home.floor.common.d.d(510);
    }

    public HomeRecommend j() {
        return this.f11074h;
    }

    public synchronized void k(com.jingdong.app.mall.home.r.e.d dVar) {
        if (dVar == null) {
            return;
        }
        h hVar = dVar.mParentModel;
        int d2 = hVar == null ? 0 : com.jingdong.app.mall.home.floor.common.d.d(hVar.b);
        HomeRecommend homeRecommend = this.f11074h;
        if (homeRecommend != null) {
            int i2 = this.f11073g;
            homeRecommend.setRecyclerViewPadding(i2, d2, i2, 0);
        }
    }

    public boolean l() {
        return !this.f11074h.isTop() && this.f11075i.getLastVisibleItem() == this.f11075i.getTotalItemCount() - 1;
    }

    public void o() {
        HomeRecommend homeRecommend = this.f11074h;
        if (homeRecommend != null) {
            homeRecommend.uploadExoTabMta();
            this.f11074h.uploadExpoData();
        }
    }

    @Override // com.jingdong.app.mall.home.widget.b
    public void onPreInitView(com.jingdong.app.mall.home.r.e.d dVar, boolean z) {
    }

    @Override // com.jingdong.app.mall.home.widget.b
    public void onReleaseView() {
    }

    @Override // com.jingdong.app.mall.home.widget.b
    public void onUseView() {
    }

    @Override // com.jingdong.app.mall.home.widget.b
    public void onViewBind(com.jingdong.app.mall.home.r.e.d dVar) {
        boolean z = dVar.isCacheData;
        JDHomeFragment z0 = JDHomeFragment.z0();
        if (z0 != null) {
            z0.S0();
        }
        HomeRecommend homeRecommend = this.f11074h;
        if (homeRecommend != null) {
            homeRecommend.onViewBind();
        }
    }

    @Override // com.jingdong.app.mall.home.widget.b
    public void onViewRecycle() {
        HomeRecommend homeRecommend = this.f11074h;
        if (homeRecommend != null) {
            homeRecommend.onViewRecycle();
        }
    }

    public void p() {
        HomeRecommend homeRecommend = this.f11074h;
        if (homeRecommend != null) {
            homeRecommend.onBackToHome();
        }
    }

    public void q() {
        HomeRecommend homeRecommend = this.f11074h;
        if (homeRecommend != null) {
            homeRecommend.uploadExoTabMta();
            this.f11074h.uploadExpoData();
        }
        RecyclerView recyclerView = this.f11076j;
        if (recyclerView != null) {
            recyclerView.stopScroll();
        }
    }

    public void r(int i2, int i3, int i4) {
        int h2 = i2 - h();
        this.f11077k = i4 - h2;
        this.f11074h.setTopSpace(h2);
        RecyclerView.Adapter adapter = this.f11075i.getAdapter();
        if (adapter instanceof HomeRecyclerAdapter) {
            ((HomeRecyclerAdapter) adapter).r(i4);
        }
        if (i3 != this.f11078l) {
            this.f11074h.viewToTop();
        }
        this.f11078l = i3;
        ViewGroup.LayoutParams layoutParams = this.f11074h.getLayoutParams();
        if (layoutParams == null || layoutParams.height == this.f11077k) {
            return;
        }
        this.f11074h.setLayoutParams(layoutParams);
        if (getParent() == null) {
            return;
        }
        int top = getTop();
        if (l() || (top > 0 && top < h2)) {
            u();
        }
        this.f11079m = com.jingdong.app.mall.home.v.b.a();
    }

    public void s(int i2) {
        int h2 = i2 - h();
        boolean n2 = this.f11075i.n();
        this.f11077k = this.f11075i.getHeight() - h2;
        ViewGroup.LayoutParams layoutParams = this.f11074h.getLayoutParams();
        if (layoutParams == null || layoutParams.height == this.f11077k) {
            return;
        }
        this.f11074h.setTopSpace(h2);
        this.f11074h.setLayoutParams(layoutParams);
        if (n2 || l()) {
            u();
        }
        this.f11079m = com.jingdong.app.mall.home.v.b.a();
    }

    public void t() {
        HomeRecommend homeRecommend = this.f11074h;
        if (homeRecommend != null) {
            homeRecommend.onViewDetached();
        }
    }

    public void u() {
        if (this.f11079m == com.jingdong.app.mall.home.v.b.a() && this.f11075i.i() >= com.jingdong.app.mall.home.floor.ctrl.h.T() && this.f11075i.getLastVisibleItem() == this.f11075i.getTotalItemCount() - 1) {
            post(new e());
            postDelayed(new f(this), 50L);
        }
    }
}
