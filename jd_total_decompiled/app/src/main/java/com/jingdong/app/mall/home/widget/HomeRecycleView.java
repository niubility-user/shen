package com.jingdong.app.mall.home.widget;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingParent2;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.jingdong.app.mall.home.floor.view.baseui.IMallFloorTop;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.o.a.k;
import com.jingdong.app.mall.home.o.a.l;
import com.jingdong.app.mall.home.widget.recommend.HomeRecyclerViewAccessibilityDelegate;
import com.jingdong.app.mall.home.widget.recommend.NewHomeRecommendContent;
import com.jingdong.common.recommend.ScrollDispatchHelper;
import com.jingdong.common.recommend.ui.FlingHelperUtil;
import com.jingdong.common.recommend.ui.homerecommend.HomeChildRecyclerView;
import com.jingdong.common.recommend.ui.homerecommend.HomeRecommendContentLayout;
import com.jingdong.jdsdk.utils.DPIUtil;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class HomeRecycleView extends RecyclerView implements ScrollDispatchHelper.ScrollDispatchParent, NestedScrollingParent2 {

    /* renamed from: l */
    private static Handler f11010l = new Handler(Looper.getMainLooper());

    /* renamed from: m */
    private static AtomicBoolean f11011m = new AtomicBoolean(false);

    /* renamed from: g */
    private LinearLayoutManager f11012g;

    /* renamed from: h */
    public AtomicBoolean f11013h;

    /* renamed from: i */
    private ScrollDispatchHelper f11014i;

    /* renamed from: j */
    private int f11015j;

    /* renamed from: k */
    private boolean f11016k;

    /* loaded from: classes4.dex */
    public class a extends LinearLayoutManager {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Context context) {
            super(context);
            HomeRecycleView.this = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void addDisappearingView(View view) {
            try {
                super.addDisappearingView(view);
            } catch (Exception e2) {
                e2.printStackTrace();
                HomeRecycleView.this.q();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void addView(View view, int i2) {
            try {
                super.addView(view, i2);
            } catch (Exception e2) {
                e2.printStackTrace();
                HomeRecycleView.this.q();
            }
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean canScrollVertically() {
            return !HomeRecycleView.f11011m.get();
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean isAutoMeasureEnabled() {
            return false;
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public int scrollVerticallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
            try {
                return super.scrollVerticallyBy(i2, recycler, state);
            } catch (Exception e2) {
                e2.printStackTrace();
                return 0;
            }
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean supportsPredictiveItemAnimations() {
            return false;
        }
    }

    /* loaded from: classes4.dex */
    public class b extends LinearLayoutManager {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(Context context) {
            super(context);
            HomeRecycleView.this = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void addDisappearingView(View view) {
            try {
                super.addDisappearingView(view);
            } catch (Exception e2) {
                e2.printStackTrace();
                HomeRecycleView.this.q();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void addView(View view, int i2) {
            try {
                super.addView(view, i2);
            } catch (Exception e2) {
                e2.printStackTrace();
                HomeRecycleView.this.q();
            }
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean canScrollVertically() {
            return !HomeRecycleView.f11011m.get();
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean isAutoMeasureEnabled() {
            return false;
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
            try {
                super.onLayoutChildren(recycler, state);
            } catch (Exception e2) {
                l.j(e2);
                e2.printStackTrace();
            }
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public int scrollVerticallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
            try {
                return super.scrollVerticallyBy(i2, recycler, state);
            } catch (Exception e2) {
                e2.printStackTrace();
                return 0;
            }
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean supportsPredictiveItemAnimations() {
            return false;
        }
    }

    /* loaded from: classes4.dex */
    public class c extends com.jingdong.app.mall.home.o.a.b {
        c(HomeRecycleView homeRecycleView) {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            HomeRecycleView.f11011m.set(false);
        }
    }

    /* loaded from: classes4.dex */
    public class d extends com.jingdong.app.mall.home.o.a.b {
        d() {
            HomeRecycleView.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            HomeRecycleView.this.l(0);
        }
    }

    /* loaded from: classes4.dex */
    public class e extends com.jingdong.app.mall.home.o.a.b {
        e() {
            HomeRecycleView.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            if (HomeRecycleView.this.c() > 1) {
                HomeRecycleView.this.scrollToPosition(0);
            }
        }
    }

    public HomeRecycleView(Context context) {
        this(context, null);
    }

    public static boolean j() {
        return f11011m.get();
    }

    public void q() {
        RecyclerView.Adapter adapter = getAdapter();
        if (adapter instanceof HomeRecyclerAdapter) {
            ((HomeRecyclerAdapter) adapter).z();
        }
    }

    private void r() {
        f.F0(new e(), 100L);
    }

    private void unUseAnimator() {
        setLayoutTransition(null);
        RecyclerView.ItemAnimator itemAnimator = getItemAnimator();
        if (itemAnimator != null) {
            itemAnimator.setAddDuration(0L);
            itemAnimator.setChangeDuration(0L);
            itemAnimator.setMoveDuration(0L);
            itemAnimator.setRemoveDuration(0L);
            f.n(itemAnimator);
            ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addChildrenForAccessibility(ArrayList<View> arrayList) {
        try {
            super.addChildrenForAccessibility(arrayList);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public int c() {
        return this.f11012g.findFirstCompletelyVisibleItemPosition();
    }

    public void clearCurrentFocus() {
        try {
            Context context = getContext();
            f.n(context);
            View currentFocus = ((Activity) context).getCurrentFocus();
            if (currentFocus != null) {
                currentFocus.clearFocus();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public View d() {
        return getChildAt(getChildCount() - 1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, androidx.core.view.NestedScrollingChild2
    public boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2, int i4) {
        ScrollDispatchHelper scrollDispatchHelper = this.f11014i;
        if (scrollDispatchHelper == null || !scrollDispatchHelper.dispatchNestedPreScroll(this, i2, i3, iArr, iArr2, i4)) {
            return super.dispatchNestedPreScroll(i2, i3, iArr, iArr2, i4);
        }
        return true;
    }

    public HomeRecommendContentLayout e() {
        View d2 = d();
        if (d2 instanceof NewHomeRecommendContent) {
            return ((NewHomeRecommendContent) d2).j();
        }
        return null;
    }

    public HomeRecommendContentLayout f() {
        RecyclerView.Adapter adapter = getAdapter();
        if (adapter instanceof HomeRecyclerAdapter) {
            return ((HomeRecyclerAdapter) adapter).o();
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public boolean fling(int i2, int i3) {
        int abs = Math.abs(i3);
        int i4 = this.f11015j;
        if (i4 > 8888 && abs > i4) {
            i3 = (i4 * abs) / i3;
        }
        return super.fling(i2, i3);
    }

    public int g() {
        RecyclerView.Adapter adapter = getAdapter();
        if (adapter instanceof HomeRecyclerAdapter) {
            return ((HomeRecyclerAdapter) adapter).n();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-1, -2);
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchParent
    public ScrollDispatchHelper.ScrollDispatchChild getChildView() {
        return f();
    }

    public int getLastVisibleItem() {
        return this.f11012g.findLastVisibleItemPosition();
    }

    @Override // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public int getNestedScrollAxes() {
        return 0;
    }

    public int getTotalItemCount() {
        return this.f11012g.getItemCount();
    }

    public int h() {
        View d2 = d();
        if (d2 instanceof NewHomeRecommendContent) {
            return ((NewHomeRecommendContent) d2).g();
        }
        return 0;
    }

    public int i() {
        View childAt = getChildAt(0);
        if (childAt == null || (childAt instanceof NewHomeRecommendContent)) {
            return 0;
        }
        int i2 = -childAt.getTop();
        if (childAt instanceof IMallFloorTop) {
            IMallFloorTop iMallFloorTop = (IMallFloorTop) childAt;
            i2 = Math.min(i2, iMallFloorTop.getLayoutMaxHeight()) + iMallFloorTop.getLayoutTop();
        }
        View d2 = d();
        return d2 instanceof NewHomeRecommendContent ? i2 + ((NewHomeRecommendContent) d2).i() : i2;
    }

    public boolean k() {
        return getLastVisibleItem() >= getTotalItemCount() - 1;
    }

    public void l(int i2) {
        HomeRecommendContentLayout e2 = e();
        if (e2 != null) {
            e2.onScrollChanged(i2);
        }
    }

    public void m() {
        l(0);
    }

    public boolean n() {
        HomeRecommendContentLayout f2 = f();
        return f2 != null && f2.getChildTop() <= f2.getTopSpace();
    }

    public void o() {
        LinearLayoutManager linearLayoutManager = this.f11012g;
        Parcelable onSaveInstanceState = linearLayoutManager != null ? linearLayoutManager.onSaveInstanceState() : null;
        a aVar = new a(getContext());
        this.f11012g = aVar;
        if (onSaveInstanceState != null) {
            aVar.onRestoreInstanceState(onSaveInstanceState);
        }
        this.f11012g.setOrientation(1);
        setLayoutManager(this.f11012g);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        ScrollDispatchHelper scrollDispatchHelper = this.f11014i;
        if (scrollDispatchHelper == null || !scrollDispatchHelper.isChildConsumeTouch(motionEvent)) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        if (l.q()) {
            super.onLayout(z, i2, i3, i4, i5);
            return;
        }
        try {
            super.onLayout(z, i2, i3, i4, i5);
        } catch (Exception e2) {
            l.j(e2);
            e2.printStackTrace();
            q();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public void onMeasure(int i2, int i3) {
        try {
            super.onMeasure(i2, i3);
        } catch (Exception e2) {
            e2.printStackTrace();
            q();
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onNestedFling(View view, float f2, float f3, boolean z) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onNestedPreFling(View view, float f2, float f3) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onNestedPreScroll(View view, int i2, int i3, int[] iArr) {
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedPreScroll(@NonNull View view, int i2, int i3, @NonNull int[] iArr, int i4) {
        if (l.r() && (view instanceof HomeChildRecyclerView) && i4 == 1 && !canScrollVertically(-1)) {
            ((RecyclerView) view).stopScroll();
        }
        ScrollDispatchHelper scrollDispatchHelper = this.f11014i;
        if (scrollDispatchHelper != null) {
            scrollDispatchHelper.onNestedPreScroll(view, i2, i3, iArr);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onNestedScroll(View view, int i2, int i3, int i4, int i5) {
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScroll(@NonNull View view, int i2, int i3, int i4, int i5, int i6) {
        ScrollDispatchHelper scrollDispatchHelper = this.f11014i;
        if (scrollDispatchHelper != null) {
            scrollDispatchHelper.onNestedScroll(view, i2, i3, i4, i5, i6);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onNestedScrollAccepted(View view, View view2, int i2) {
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScrollAccepted(@NonNull View view, @NonNull View view2, int i2, int i3) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void onScrolled(int i2, int i3) {
        super.onScrolled(i2, i3);
        HomeRecommendContentLayout f2 = f();
        if (f2 != null) {
            f2.onParentScroll(i3);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        try {
            super.onSizeChanged(i2, i3, i4, i5);
        } catch (Exception e2) {
            f.s0(this, e2);
        }
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public boolean onStartNestedScroll(@NonNull View view, @NonNull View view2, int i2, int i3) {
        ScrollDispatchHelper scrollDispatchHelper = this.f11014i;
        return scrollDispatchHelper != null && scrollDispatchHelper.onStartNestedScroll(view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onStopNestedScroll(View view) {
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onStopNestedScroll(@NonNull View view, int i2) {
    }

    public void p() {
        LinearLayoutManager linearLayoutManager = this.f11012g;
        Parcelable onSaveInstanceState = linearLayoutManager != null ? linearLayoutManager.onSaveInstanceState() : null;
        b bVar = new b(getContext());
        this.f11012g = bVar;
        if (onSaveInstanceState != null) {
            bVar.onRestoreInstanceState(onSaveInstanceState);
        }
        this.f11012g.setOrientation(1);
        setLayoutManager(this.f11012g);
    }

    public void refreshLayoutManager() {
        if (this.f11016k) {
            o();
        } else {
            p();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
    }

    public void s() {
        try {
            HomeRecommendContentLayout f2 = f();
            if (f2 != null) {
                f2.viewToTop();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager == this.f11012g) {
            super.setLayoutManager(layoutManager);
        }
    }

    public void t(boolean z) {
        f11010l.removeCallbacksAndMessages(null);
        if (!z) {
            f11010l.postDelayed(new c(this), 270L);
        } else {
            f11011m.set(true);
        }
    }

    public void u(int i2) {
        stopScroll();
        if (i2 == 0) {
            s();
            r();
        }
        scrollToPosition(i2);
    }

    public void v(boolean z, int i2, int i3) {
        stopScroll();
        if (z) {
            s();
        }
        this.f11012g.scrollToPositionWithOffset(i2, i3);
        f.E0(new d());
    }

    public HomeRecycleView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HomeRecycleView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f11013h = new AtomicBoolean(true);
        setAccessibilityDelegateCompat(new HomeRecyclerViewAccessibilityDelegate(this));
        unUseAnimator();
        if (k.v()) {
            addItemDecoration(new HomeDebugItemDecoration());
        }
        this.f11016k = l.s();
        addItemDecoration(new HomeOffsetItemDecoration());
        this.f11014i = new ScrollDispatchHelper(this, getContext());
        this.f11015j = new FlingHelperUtil(getContext()).getVelocityByDistance(DPIUtil.getHeight() * 4);
        refreshLayoutManager();
        com.jingdong.app.mall.home.o.a.e.a(this);
    }
}
