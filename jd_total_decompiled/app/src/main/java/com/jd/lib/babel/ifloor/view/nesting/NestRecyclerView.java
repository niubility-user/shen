package com.jd.lib.babel.ifloor.view.nesting;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes13.dex */
public class NestRecyclerView extends RecyclerView {
    private FlingHelper helper;
    private OnNestFlingListener onFlingListener;
    private boolean startFling;
    private int totalDy;
    private int velocityY;

    public NestRecyclerView(Context context) {
        super(context);
        initView(context);
    }

    static /* synthetic */ int access$112(NestRecyclerView nestRecyclerView, int i2) {
        int i3 = nestRecyclerView.totalDy + i2;
        nestRecyclerView.totalDy = i3;
        return i3;
    }

    private void initView(Context context) {
        this.helper = new FlingHelper(context);
        addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.jd.lib.babel.ifloor.view.nesting.NestRecyclerView.1
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
                if (NestRecyclerView.this.velocityY == 0 || !NestRecyclerView.this.isChildOnTop(recyclerView)) {
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

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScroll(View view, int i2, int i3, int i4, int i5) {
        scrollBy(0, i5);
    }

    public void setOnNestFlingListener(OnNestFlingListener onNestFlingListener) {
        this.onFlingListener = onNestFlingListener;
    }

    public NestRecyclerView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    public NestRecyclerView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        initView(context);
    }
}
