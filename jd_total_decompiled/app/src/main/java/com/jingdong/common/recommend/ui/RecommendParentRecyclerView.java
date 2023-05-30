package com.jingdong.common.recommend.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingParent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes6.dex */
public class RecommendParentRecyclerView extends RecyclerView implements NestedScrollingParent {
    private AtomicBoolean canScrollVertically;
    private float lastY;
    private FlingHelperUtil mFlingHelper;
    private LinearLayoutManager mLayoutManager;
    private int mMaxDistance;
    private RecommendContentLayout recommendContentLayout;
    private boolean startFling;
    private int totalDy;
    private int velocityY;

    public RecommendParentRecyclerView(Context context) {
        this(context, null);
    }

    static /* synthetic */ int access$112(RecommendParentRecyclerView recommendParentRecyclerView, int i2) {
        int i3 = recommendParentRecyclerView.totalDy + i2;
        recommendParentRecyclerView.totalDy = i3;
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchChildFling() {
        int i2;
        if (isScrollEnd() && (i2 = this.velocityY) != 0) {
            double splineFlingDistance = this.mFlingHelper.getSplineFlingDistance(i2);
            int i3 = this.totalDy;
            if (splineFlingDistance > i3) {
                FlingHelperUtil flingHelperUtil = this.mFlingHelper;
                double d = i3;
                Double.isNaN(d);
                childFling(flingHelperUtil.getVelocityByDistance(splineFlingDistance - d));
            }
        }
        this.totalDy = 0;
        this.velocityY = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RecommendContentLayout getRecommendChild() {
        View childAt = getChildAt(getChildCount() - 1);
        if (childAt == null || !(childAt instanceof RecommendContentLayout)) {
            return null;
        }
        return (RecommendContentLayout) childAt;
    }

    public void childFling(int i2) {
        RecommendContentLayout recommendContentLayout = this.recommendContentLayout;
        if (recommendContentLayout != null) {
            recommendContentLayout.fling(0, i2);
        }
    }

    public void clearCurrentFocus() {
        View currentFocus;
        try {
            if (!(getContext() instanceof Activity) || (currentFocus = ((Activity) getContext()).getCurrentFocus()) == null) {
                return;
            }
            currentFocus.clearFocus();
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedPreFling(float f2, float f3) {
        RecommendContentLayout recommendChild = getRecommendChild();
        if (isScrollEnd() && recommendChild != null && !recommendChild.isTop()) {
            childFling((int) f3);
            return true;
        }
        return super.dispatchNestedPreFling(f2, f3);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent != null && motionEvent.getAction() == 0) {
            this.velocityY = 0;
            stopScroll();
        }
        if (motionEvent != null && motionEvent.getActionMasked() != 2) {
            this.lastY = 0.0f;
        }
        try {
            return super.dispatchTouchEvent(motionEvent);
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
            return false;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public boolean fling(int i2, int i3) {
        int abs = Math.abs(i3);
        int i4 = this.mMaxDistance;
        if (i4 > 8888 && abs > i4) {
            i3 = (i4 * abs) / i3;
        }
        boolean fling = super.fling(i2, i3);
        if (fling && i3 > 0) {
            this.startFling = true;
            this.velocityY = i3;
        } else {
            this.velocityY = 0;
        }
        return fling;
    }

    public boolean isScrollEnd() {
        return true ^ canScrollVertically(1);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onNestedFling(View view, float f2, float f3, boolean z) {
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onNestedPreFling(View view, float f2, float f3) {
        RecommendContentLayout recommendChild = getRecommendChild();
        boolean z = f3 > 0.0f && !isScrollEnd();
        boolean z2 = f3 < 0.0f && recommendChild != null && recommendChild.isTop();
        if (z || z2) {
            fling(0, (int) f3);
            return true;
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onNestedPreScroll(View view, int i2, int i3, int[] iArr) {
        RecommendContentLayout recommendChild = getRecommendChild();
        boolean z = i3 > 0 && !isScrollEnd();
        boolean z2 = i3 < 0 && recommendChild != null && recommendChild.isTop();
        if (z || z2) {
            scrollBy(0, i3);
            iArr[1] = i3;
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onStartNestedScroll(View view, View view2, int i2) {
        return view2 != null && (view2 instanceof RecommendChildRecyclerView);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.lastY == 0.0f) {
            this.lastY = motionEvent.getY();
        }
        if (isScrollEnd() && this.recommendContentLayout != null) {
            this.canScrollVertically.set(false);
            this.recommendContentLayout.onScroll((int) (this.lastY - motionEvent.getY()));
        }
        if (motionEvent.getActionMasked() == 1) {
            this.canScrollVertically.set(true);
        }
        this.lastY = motionEvent.getY();
        try {
            return super.onTouchEvent(motionEvent);
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
            return false;
        }
    }

    public void refreshLayoutManager() {
        LinearLayoutManager linearLayoutManager = this.mLayoutManager;
        Parcelable onSaveInstanceState = linearLayoutManager != null ? linearLayoutManager.onSaveInstanceState() : null;
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext()) { // from class: com.jingdong.common.recommend.ui.RecommendParentRecyclerView.2
            @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
            public void addDisappearingView(View view) {
                try {
                    super.addDisappearingView(view);
                } catch (Exception e2) {
                    if (OKLog.D) {
                        e2.printStackTrace();
                    }
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
            public void addView(View view, int i2) {
                try {
                    super.addView(view, i2);
                } catch (Exception e2) {
                    if (OKLog.D) {
                        e2.printStackTrace();
                    }
                }
            }

            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return RecommendParentRecyclerView.this.canScrollVertically.get() || RecommendParentRecyclerView.this.recommendContentLayout == null || RecommendParentRecyclerView.this.recommendContentLayout.isTop();
            }

            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                try {
                    super.onLayoutChildren(recycler, state);
                } catch (Exception e2) {
                    if (OKLog.D) {
                        e2.printStackTrace();
                    }
                }
            }

            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public int scrollVerticallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
                try {
                    return super.scrollVerticallyBy(i2, recycler, state);
                } catch (Exception e2) {
                    if (OKLog.D) {
                        e2.printStackTrace();
                        return 0;
                    }
                    return 0;
                }
            }

            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean supportsPredictiveItemAnimations() {
                return false;
            }
        };
        this.mLayoutManager = linearLayoutManager2;
        if (onSaveInstanceState != null) {
            linearLayoutManager2.onRestoreInstanceState(onSaveInstanceState);
        }
        this.mLayoutManager.setItemPrefetchEnabled(false);
        this.mLayoutManager.setAutoMeasureEnabled(false);
        this.mLayoutManager.setOrientation(1);
        setLayoutManager(this.mLayoutManager);
    }

    public void setRecommendContentLayout(RecommendContentLayout recommendContentLayout) {
        this.recommendContentLayout = recommendContentLayout;
    }

    public RecommendParentRecyclerView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RecommendParentRecyclerView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.canScrollVertically = new AtomicBoolean(true);
        refreshLayoutManager();
        FlingHelperUtil flingHelperUtil = new FlingHelperUtil(getContext());
        this.mFlingHelper = flingHelperUtil;
        this.mMaxDistance = flingHelperUtil.getVelocityByDistance(DPIUtil.getHeight() * 4);
        setOverScrollMode(2);
        addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.jingdong.common.recommend.ui.RecommendParentRecyclerView.1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i3) {
                if (i3 == 0) {
                    RecommendParentRecyclerView.this.dispatchChildFling();
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i3, int i4) {
                if (RecommendParentRecyclerView.this.startFling) {
                    RecommendParentRecyclerView.this.totalDy = 0;
                    RecommendParentRecyclerView.this.startFling = false;
                }
                RecommendParentRecyclerView.access$112(RecommendParentRecyclerView.this, i4);
                if (RecommendParentRecyclerView.this.isScrollEnd() || RecommendParentRecyclerView.this.getRecommendChild() == null) {
                    return;
                }
                RecommendParentRecyclerView.this.getRecommendChild().allChildToTop();
            }
        });
    }
}
