package com.jd.lib.flexcube.layout.floor.banner.focus.layoutmanager;

import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.flexcube.layout.floor.banner.focus.layoutmanager.FlexFocusBannerLayoutManager;

/* loaded from: classes14.dex */
public class FlexFocusBannerCenterSnapHelper extends RecyclerView.OnFlingListener {
    RecyclerView a;
    Scroller b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f4293c = false;
    private final RecyclerView.OnScrollListener d = new a();

    /* loaded from: classes14.dex */
    class a extends RecyclerView.OnScrollListener {
        boolean a = false;

        a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
            super.onScrollStateChanged(recyclerView, i2);
            FlexFocusBannerLayoutManager flexFocusBannerLayoutManager = (FlexFocusBannerLayoutManager) recyclerView.getLayoutManager();
            FlexFocusBannerLayoutManager.a aVar = flexFocusBannerLayoutManager.o;
            if (aVar != null) {
                aVar.onPageScrollStateChanged(i2);
            }
            if (i2 == 0 && this.a) {
                this.a = false;
                if (!FlexFocusBannerCenterSnapHelper.this.f4293c) {
                    FlexFocusBannerCenterSnapHelper.this.f4293c = true;
                    FlexFocusBannerCenterSnapHelper.this.c(flexFocusBannerLayoutManager, aVar);
                    return;
                }
                FlexFocusBannerCenterSnapHelper.this.f4293c = false;
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
            if (i2 == 0 && i3 == 0) {
                return;
            }
            this.a = true;
        }
    }

    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) throws IllegalStateException {
        RecyclerView recyclerView2 = this.a;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            destroyCallbacks();
        }
        this.a = recyclerView;
        if (recyclerView != null) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof FlexFocusBannerLayoutManager) {
                setupCallbacks();
                this.b = new Scroller(this.a.getContext(), new DecelerateInterpolator());
                FlexFocusBannerLayoutManager flexFocusBannerLayoutManager = (FlexFocusBannerLayoutManager) layoutManager;
                c(flexFocusBannerLayoutManager, flexFocusBannerLayoutManager.o);
            }
        }
    }

    void c(FlexFocusBannerLayoutManager flexFocusBannerLayoutManager, FlexFocusBannerLayoutManager.a aVar) {
        int o = flexFocusBannerLayoutManager.o();
        if (o != 0) {
            if (flexFocusBannerLayoutManager.getOrientation() == 1) {
                this.a.smoothScrollBy(0, o);
            } else {
                this.a.smoothScrollBy(o, 0);
            }
        } else {
            this.f4293c = false;
        }
        if (aVar != null) {
            aVar.onPageSelected(flexFocusBannerLayoutManager.g());
        }
    }

    void destroyCallbacks() {
        this.a.removeOnScrollListener(this.d);
        this.a.setOnFlingListener(null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnFlingListener
    public boolean onFling(int i2, int i3) {
        FlexFocusBannerLayoutManager flexFocusBannerLayoutManager = (FlexFocusBannerLayoutManager) this.a.getLayoutManager();
        if (flexFocusBannerLayoutManager == null || this.a.getAdapter() == null) {
            return false;
        }
        if (flexFocusBannerLayoutManager.j() || !(flexFocusBannerLayoutManager.f4297g == flexFocusBannerLayoutManager.k() || flexFocusBannerLayoutManager.f4297g == flexFocusBannerLayoutManager.l())) {
            int minFlingVelocity = this.a.getMinFlingVelocity();
            this.b.fling(0, 0, i2, i3, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            if (flexFocusBannerLayoutManager.d == 1 && Math.abs(i3) > minFlingVelocity) {
                int g2 = flexFocusBannerLayoutManager.g();
                int finalY = (int) ((this.b.getFinalY() / flexFocusBannerLayoutManager.f4304n) / flexFocusBannerLayoutManager.i());
                this.a.smoothScrollToPosition(flexFocusBannerLayoutManager.getReverseLayout() ? g2 - finalY : g2 + finalY);
                return true;
            }
            if (flexFocusBannerLayoutManager.d == 0 && Math.abs(i2) > minFlingVelocity) {
                int g3 = flexFocusBannerLayoutManager.g();
                int finalX = (int) ((this.b.getFinalX() / flexFocusBannerLayoutManager.f4304n) / flexFocusBannerLayoutManager.i());
                this.a.smoothScrollToPosition(flexFocusBannerLayoutManager.getReverseLayout() ? g3 - finalX : g3 + finalX);
            }
            return true;
        }
        return false;
    }

    void setupCallbacks() throws IllegalStateException {
        if (this.a.getOnFlingListener() == null) {
            this.a.addOnScrollListener(this.d);
            this.a.setOnFlingListener(this);
            return;
        }
        throw new IllegalStateException("An instance of OnFlingListener already set.");
    }
}
