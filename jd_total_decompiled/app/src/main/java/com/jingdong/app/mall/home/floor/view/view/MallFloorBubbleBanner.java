package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI;
import com.jingdong.app.mall.home.floor.view.view.subitem.BubbleBannerLeftRightLayout;
import com.jingdong.app.mall.home.floor.view.view.subitem.BubbleBannerMiddleLayout;
import com.jingdong.app.mall.home.r.f.a.g;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class MallFloorBubbleBanner extends BaseMallFloor<g> implements IMallFloorUI {
    private BubbleBannerLeftRightLayout mLeftView;
    private f mMiddleSize;
    private BubbleBannerMiddleLayout mMiddleView;
    private BubbleBannerLeftRightLayout mRightView;
    private IBubbleBannerSmall[] mSmallViewList;
    private int uiStyle;

    public MallFloorBubbleBanner(Context context, int i2) {
        super(context);
        this.mSmallViewList = new BubbleBannerLeftRightLayout[2];
        this.uiStyle = i2;
    }

    private void initFloorView() {
        if (this.mMiddleView == null) {
            this.mMiddleSize = new f(R2.attr.applyMotionScene, -1);
            BubbleBannerMiddleLayout bubbleBannerMiddleLayout = new BubbleBannerMiddleLayout(getContext());
            this.mMiddleView = bubbleBannerMiddleLayout;
            bubbleBannerMiddleLayout.setId(R.id.mallfloor_item2);
        }
        f.c(this.mMiddleView, this.mMiddleSize);
        if (this.mMiddleView.getParent() != this) {
            RelativeLayout.LayoutParams u = this.mMiddleSize.u(this.mMiddleView);
            u.addRule(14);
            this.mMiddleView.setLayoutParams(u);
            m.b(this, this.mMiddleView, -1);
        }
        if (this.mLeftView == null) {
            this.mLeftView = new BubbleBannerLeftRightLayout(this.uiStyle, getContext(), 0);
        }
        if (this.mLeftView.getParent() != this) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(0, this.mMiddleView.getId());
            this.mLeftView.setLayoutParams(layoutParams);
            m.b(this, this.mLeftView, -1);
        }
        this.mSmallViewList[0] = this.mLeftView;
        if (this.mRightView == null) {
            this.mRightView = new BubbleBannerLeftRightLayout(this.uiStyle, getContext(), 2);
        }
        if (this.mRightView.getParent() != this) {
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams2.addRule(1, this.mMiddleView.getId());
            layoutParams2.addRule(11);
            this.mRightView.setLayoutParams(layoutParams2);
            m.b(this, this.mRightView, -1);
        }
        this.mSmallViewList[1] = this.mRightView;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomePause() {
        super.onHomePause();
        ((g) this.mPresenter).R();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor
    public void onHomeResume(int i2, int i3) {
        super.onHomeResume(i2, i3);
        ((g) this.mPresenter).Q();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public void onRefreshViewOnMainThread() {
        super.onRefreshViewOnMainThread();
        initFloorView();
        setBackgroundColor(((g) this.mPresenter).f());
        ((g) this.mPresenter).S(this.mSmallViewList, this.mMiddleView);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public g createPresenter() {
        return new g();
    }
}
