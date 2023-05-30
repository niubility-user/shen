package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.view.adapter.TrendAdapter;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.o.a.b;
import com.jingdong.app.mall.home.r.f.a.z;

/* loaded from: classes4.dex */
public class MallFloorTrend extends BaseMallFloor<z> {
    private TrendAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private f mRecyclerSize;
    private RecyclerView mRecyclerView;
    private int mScrollFirst;
    private int mScrollLast;

    public MallFloorTrend(Context context) {
        super(context);
        this.mScrollFirst = Integer.MAX_VALUE;
        this.mScrollLast = Integer.MIN_VALUE;
        this.mContext = context;
        this.mRecyclerSize = new f(-2, -1);
        this.mAdapter = new TrendAdapter(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateScrollPosition() {
        int findFirstVisibleItemPosition = this.mLayoutManager.findFirstVisibleItemPosition();
        int findLastVisibleItemPosition = this.mLayoutManager.findLastVisibleItemPosition();
        this.mScrollFirst = Math.min(findFirstVisibleItemPosition, this.mScrollFirst);
        this.mScrollLast = Math.max(findLastVisibleItemPosition, this.mScrollLast);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomePause() {
        super.onHomePause();
        updateScrollPosition();
        ((z) this.mPresenter).P(this.mScrollFirst, this.mScrollLast);
        ((z) this.mPresenter).T();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomeRefresh() {
        super.onHomeRefresh();
        updateScrollPosition();
        ((z) this.mPresenter).P(this.mScrollFirst, this.mScrollLast);
        ((z) this.mPresenter).T();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor
    public void onHomeResume(int i2, int i3) {
        super.onHomeResume(i2, i3);
        ((z) this.mPresenter).U(isFloorDisplay());
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomeScrollStop(int i2, int i3) {
        super.onHomeScrollStop(i2, i3);
        if (((z) this.mPresenter).S() || !isFloorDisplay()) {
            return;
        }
        ((z) this.mPresenter).U(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public void onRefreshViewOnMainThread() {
        super.onRefreshViewOnMainThread();
        com.jingdong.app.mall.home.o.a.f.u0(new b() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorTrend.1
            @Override // com.jingdong.app.mall.home.o.a.b
            protected void safeRun() {
                ((z) ((BaseMallColorFloor) MallFloorTrend.this).mPresenter).U(MallFloorTrend.this.isFloorDisplay());
            }
        });
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null) {
            RecyclerView recyclerView2 = new RecyclerView(this.mContext);
            this.mRecyclerView = recyclerView2;
            recyclerView2.setAdapter(this.mAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext, 0, false);
            this.mLayoutManager = linearLayoutManager;
            this.mRecyclerView.setLayoutManager(linearLayoutManager);
            this.mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorTrend.2
                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView3, int i2) {
                    super.onScrollStateChanged(recyclerView3, i2);
                    if (i2 == 0) {
                        ((z) ((BaseMallColorFloor) MallFloorTrend.this).mPresenter).P(MallFloorTrend.this.mScrollFirst, MallFloorTrend.this.mScrollLast);
                        MallFloorTrend.this.mScrollFirst = Integer.MAX_VALUE;
                        MallFloorTrend.this.mScrollLast = Integer.MIN_VALUE;
                    }
                }

                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrolled(@NonNull RecyclerView recyclerView3, int i2, int i3) {
                    super.onScrolled(recyclerView3, i2, i3);
                    MallFloorTrend.this.updateScrollPosition();
                }
            });
            addView(this.mRecyclerView, this.mRecyclerSize.u(this.mRecyclerView));
        } else {
            f.c(recyclerView, this.mRecyclerSize);
        }
        this.mAdapter.setList(((z) this.mPresenter).R());
        this.mRecyclerView.scrollToPosition(0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public z createPresenter() {
        return new z();
    }
}
