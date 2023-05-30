package com.jingdong.app.mall.home.category.view;

import android.content.Context;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.jingdong.app.mall.home.category.adapter.CaAdapter;
import com.jingdong.app.mall.home.category.floor.CaLoadingFloor;
import com.jingdong.app.mall.home.category.floor.base.BaseCaFeeds;
import com.jingdong.app.mall.home.category.floor.base.BaseCaFloor;
import com.jingdong.app.mall.home.category.floor.decoration.CaFloatDecoration;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.o.a.e;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.o.a.k;
import com.jingdong.app.mall.home.widget.HomeDebugItemDecoration;

/* loaded from: classes4.dex */
public class CaRecycleView extends RecyclerView {

    /* renamed from: g  reason: collision with root package name */
    private CaContentLayout f8759g;

    /* renamed from: h  reason: collision with root package name */
    private StaggeredGridLayoutManager f8760h;

    /* renamed from: i  reason: collision with root package name */
    private CaFloatDecoration f8761i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends StaggeredGridLayoutManager {
        a(int i2, int i3) {
            super(i2, i3);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void addView(View view, int i2) {
            super.addView(view, i2);
        }

        @Override // androidx.recyclerview.widget.StaggeredGridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
            try {
                super.onLayoutChildren(recycler, state);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        @Override // androidx.recyclerview.widget.StaggeredGridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public int scrollVerticallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
            try {
                return super.scrollVerticallyBy(i2, recycler, state);
            } catch (Exception e2) {
                e2.printStackTrace();
                return 0;
            }
        }

        @Override // androidx.recyclerview.widget.StaggeredGridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i2) {
            c cVar = new c(CaRecycleView.this, recyclerView.getContext(), CaRecycleView.this.f8760h);
            cVar.setTargetPosition(i2);
            startSmoothScroll(cVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends RecyclerView.OnScrollListener {
        b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i2) {
            super.onScrollStateChanged(recyclerView, i2);
            if (i2 == 0) {
                CaRecycleView.this.h();
                CaRecycleView.this.f();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(@NonNull RecyclerView recyclerView, int i2, int i3) {
            super.onScrolled(recyclerView, i2, i3);
            CaRecycleView caRecycleView = CaRecycleView.this;
            caRecycleView.g(i3, caRecycleView.e());
        }
    }

    /* loaded from: classes4.dex */
    private class c extends LinearSmoothScroller {
        private StaggeredGridLayoutManager a;

        public c(CaRecycleView caRecycleView, Context context, StaggeredGridLayoutManager staggeredGridLayoutManager) {
            super(context);
            this.a = staggeredGridLayoutManager;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller
        public PointF computeScrollVectorForPosition(int i2) {
            return this.a.computeScrollVectorForPosition(i2);
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        protected int getVerticalSnapPreference() {
            return -1;
        }
    }

    public CaRecycleView(@NonNull Context context, CaContentLayout caContentLayout, RelativeLayout relativeLayout) {
        super(context);
        this.f8759g = caContentLayout;
        CaFloatDecoration caFloatDecoration = new CaFloatDecoration(relativeLayout);
        this.f8761i = caFloatDecoration;
        addItemDecoration(caFloatDecoration);
        if (k.v()) {
            addItemDecoration(new HomeDebugItemDecoration());
        }
        initView();
        e.a(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        RecyclerView.Adapter adapter = getAdapter();
        if (adapter instanceof CaAdapter) {
            if (getFirstVisibleItem() <= ((CaAdapter) adapter).q()) {
                this.f8760h.invalidateSpanAssignments();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        int loadingState;
        RecyclerView.Adapter adapter;
        int itemCount;
        com.jingdong.app.mall.home.category.floor.base.b lastView = com.jingdong.app.mall.home.n.a.C_LOADING.getLastView();
        if (!(lastView instanceof CaLoadingFloor) || (loadingState = ((CaLoadingFloor) lastView).getLoadingState()) == 3 || loadingState == 4 || loadingState == 2 || (adapter = getAdapter()) == null || (itemCount = adapter.getItemCount()) <= 1 || itemCount - getLastVisibleItem() >= 6) {
            return;
        }
        this.f8759g.n();
    }

    private void initView() {
        this.f8760h = new a(2, 1);
        addOnScrollListener(new b());
        this.f8760h.setGapStrategy(0);
        setLayoutManager(this.f8760h);
    }

    public int d() {
        int[] iArr = new int[2];
        this.f8760h.findFirstCompletelyVisibleItemPositions(iArr);
        return iArr[0] < iArr[1] ? iArr[0] : iArr[1];
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    public int e() {
        View childAt = getChildAt(0);
        if (childAt instanceof BaseCaFloor) {
            BaseCaFloor baseCaFloor = (BaseCaFloor) childAt;
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            f.n(layoutParams);
            int viewLayoutPosition = ((RecyclerView.LayoutParams) layoutParams).getViewLayoutPosition();
            RecyclerView.Adapter adapter = getAdapter();
            int q = adapter instanceof CaAdapter ? ((CaAdapter) adapter).q() : 0;
            if (viewLayoutPosition < q) {
                return baseCaFloor.s();
            }
            f.r0(this, "first=" + viewLayoutPosition + "  homeCount=" + q);
            return com.jingdong.app.mall.home.n.e.d() + ((viewLayoutPosition - q) * d.d(260));
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void g(int i2, int i3) {
        this.f8761i.b(i2, i3);
    }

    public int getFirstVisibleItem() {
        int[] iArr = new int[2];
        this.f8760h.findFirstVisibleItemPositions(iArr);
        return iArr[0] < iArr[1] ? iArr[0] : iArr[1];
    }

    public int getLastVisibleItem() {
        int[] iArr = new int[2];
        this.f8760h.findLastVisibleItemPositions(iArr);
        return iArr[0] > iArr[1] ? iArr[0] : iArr[1];
    }

    public void i() {
        this.f8761i.c();
    }

    public void j(CaAdapter caAdapter) {
        int q = caAdapter.q();
        int d = d();
        if (d < 0 || this.f8761i.a()) {
            k(q, CaFloatDecoration.f8689e);
        } else if (d >= q) {
            if (d > q) {
                k(q, CaFloatDecoration.f8689e);
                return;
            }
            for (int i2 = 0; i2 < getChildCount(); i2++) {
                View childAt = getChildAt(i2);
                if (childAt instanceof BaseCaFeeds) {
                    int top = childAt.getTop();
                    int i3 = CaFloatDecoration.f8689e;
                    if (top < i3) {
                        k(q, i3);
                        return;
                    }
                    return;
                }
            }
        }
    }

    public void k(int i2, int i3) {
        stopScroll();
        this.f8760h.scrollToPositionWithOffset(i2, i3);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
    }

    public void scrollToTop() {
        this.f8761i.c();
        k(Math.max(Math.min(getFirstVisibleItem(), 6), 0), 0);
        smoothScrollToPosition(0);
    }
}
