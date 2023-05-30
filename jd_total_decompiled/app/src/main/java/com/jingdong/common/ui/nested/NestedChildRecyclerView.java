package com.jingdong.common.ui.nested;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.ScrollDispatchHelper;
import com.jingdong.common.recommend.ui.FlingHelperUtil;
import com.jingdong.jdsdk.utils.DPIUtil;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes6.dex */
public class NestedChildRecyclerView extends RecyclerView implements ScrollDispatchHelper.ScrollDispatchChild {
    private AtomicBoolean isScrollTop;
    private FlingHelperUtil mFlingHelper;
    private int mMaxDistance;
    private RecyclerView parentRecyclerView;
    private boolean startFling;
    private int totalDy;
    private int velocityY;

    public NestedChildRecyclerView(@NonNull Context context) {
        super(context);
        this.isScrollTop = new AtomicBoolean(false);
    }

    private void dispatchParentFling() {
        int i2;
        if (this.parentRecyclerView != null) {
            if (isTop() && (i2 = this.velocityY) != 0) {
                double splineFlingDistance = this.mFlingHelper.getSplineFlingDistance(i2);
                if (splineFlingDistance > Math.abs(this.totalDy)) {
                    FlingHelperUtil flingHelperUtil = this.mFlingHelper;
                    double d = this.totalDy;
                    Double.isNaN(d);
                    this.parentRecyclerView.fling(0, -flingHelperUtil.getVelocityByDistance(splineFlingDistance + d));
                }
            }
            this.totalDy = 0;
            this.velocityY = 0;
        }
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void allChildToTop() {
        scrollToTop();
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public boolean canChildScrollVertically(int i2) {
        return canScrollVertically(i2);
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void childScrollBy(int i2, int i3) {
        scrollBy(i2, i3);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent != null && motionEvent.getAction() == 0) {
            this.velocityY = 0;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public ViewParent getChildParent() {
        return getParent();
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public int getChildTop() {
        return getTop();
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public int getTopSpace() {
        return 0;
    }

    public boolean isTop() {
        return !canScrollVertically(-1);
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void onParentScroll(int i2) {
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void onSelfScroll(int i2) {
        if (this.startFling) {
            this.totalDy = 0;
            this.startFling = false;
        }
        this.totalDy += i2;
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void scrollStateChange(int i2) {
    }

    public void scrollToTop() {
        this.isScrollTop.set(true);
        scrollToPosition(0);
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void setTabSpreadState(boolean z) {
    }

    @Override // com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void setTopSpace(int i2) {
    }

    public NestedChildRecyclerView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isScrollTop = new AtomicBoolean(false);
    }

    public NestedChildRecyclerView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.isScrollTop = new AtomicBoolean(false);
    }

    public NestedChildRecyclerView(RecyclerView recyclerView, @NonNull BaseActivity baseActivity) {
        super(baseActivity);
        this.isScrollTop = new AtomicBoolean(false);
        this.parentRecyclerView = recyclerView;
        FlingHelperUtil flingHelperUtil = new FlingHelperUtil(getContext());
        this.mFlingHelper = flingHelperUtil;
        this.mMaxDistance = flingHelperUtil.getVelocityByDistance(DPIUtil.getHeight() * 4);
    }
}
