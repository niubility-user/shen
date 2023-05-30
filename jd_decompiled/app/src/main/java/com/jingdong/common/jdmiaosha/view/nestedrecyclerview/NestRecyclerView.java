package com.jingdong.common.jdmiaosha.view.nestedrecyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/* loaded from: classes5.dex */
public class NestRecyclerView extends RecyclerView {
    private FlingHelper helper;
    int[] mPosition;
    private OnNestFlingListener onFlingListener;
    private boolean startFling;
    private int totalDy;
    private int velocityY;

    public NestRecyclerView(Context context) {
        super(context);
        this.mPosition = new int[2];
        initView(context);
    }

    static /* synthetic */ int access$112(NestRecyclerView nestRecyclerView, int i2) {
        int i3 = nestRecyclerView.totalDy + i2;
        nestRecyclerView.totalDy = i3;
        return i3;
    }

    private int findFirstCompletelyVisibleItemPosition(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            ((StaggeredGridLayoutManager) layoutManager).findFirstCompletelyVisibleItemPositions(this.mPosition);
            int[] iArr = this.mPosition;
            return Math.min(iArr[0], iArr[1]);
        }
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
        }
        return 0;
    }

    private int findFirstVisibleItemPosition(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            ((StaggeredGridLayoutManager) layoutManager).findFirstVisibleItemPositions(this.mPosition);
            int[] iArr = this.mPosition;
            return Math.min(iArr[0], iArr[1]);
        } else if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        } else {
            return 0;
        }
    }

    private void initView(Context context) {
        this.helper = new FlingHelper(context);
        addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.jingdong.common.jdmiaosha.view.nestedrecyclerview.NestRecyclerView.1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
                if (i2 == 0) {
                    NestRecyclerView.this.velocityY = 0;
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
                if (NestRecyclerView.this.startFling) {
                    NestRecyclerView.this.totalDy = 0;
                }
                NestRecyclerView.access$112(NestRecyclerView.this, i3);
                if (NestRecyclerView.this.startFling) {
                    NestRecyclerView.this.startFling = false;
                }
                if (!NestRecyclerView.this.isChildOnTop(recyclerView) || NestRecyclerView.this.velocityY == 0) {
                    return;
                }
                double splineFlingDistance = NestRecyclerView.this.helper.getSplineFlingDistance(NestRecyclerView.this.velocityY);
                if (splineFlingDistance > Math.abs(NestRecyclerView.this.totalDy)) {
                    FlingHelper flingHelper = NestRecyclerView.this.helper;
                    double d = NestRecyclerView.this.totalDy;
                    Double.isNaN(d);
                    int velocityByDistance = flingHelper.getVelocityByDistance(splineFlingDistance + d);
                    if (NestRecyclerView.this.onFlingListener != null) {
                        NestRecyclerView.this.onFlingListener.onNestFling(0, -velocityByDistance);
                    }
                }
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public boolean fling(int i2, int i3) {
        boolean fling = super.fling(i2, i3);
        if (fling && i3 < 0) {
            this.startFling = true;
            this.velocityY = i3;
        } else {
            this.velocityY = 0;
        }
        return fling;
    }

    public boolean isChildOnTop(View view) {
        return !view.canScrollVertically(-1);
    }

    public boolean isReadyForPullStart() {
        try {
            View childAt = getChildAt(0);
            if ((childAt == null ? 0 : childAt.getHeight()) > getHeight()) {
                if ((childAt == null ? 0 : childAt.getTop()) != 0 || findFirstVisibleItemPosition(getLayoutManager()) != 0) {
                    return false;
                }
            } else if (findFirstCompletelyVisibleItemPosition(getLayoutManager()) != 0) {
                return false;
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScroll(View view, int i2, int i3, int i4, int i5) {
        scrollBy(0, i5);
    }

    public void setOnNestFlingListener(OnNestFlingListener onNestFlingListener) {
        this.onFlingListener = onNestFlingListener;
    }

    public NestRecyclerView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPosition = new int[2];
        initView(context);
    }

    public NestRecyclerView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mPosition = new int[2];
        initView(context);
    }
}
