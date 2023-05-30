package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.model.entity.BubbleBannerEntity;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI;
import com.jingdong.app.mall.home.floor.view.view.subitem.BubbleBannerMiddleLayout;
import com.jingdong.app.mall.home.floor.view.widget.bubblebanner.BubbleBannerSmallLayout;
import com.jingdong.app.mall.home.r.f.a.g;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class MallFloorBubbleBannerV9 extends BaseMallFloor<g> implements IMallFloorUI {
    private f mMiddleSize;
    private BubbleBannerMiddleLayout mMiddleView;
    private int[] mSmallIds;
    private BubbleBannerSmallLayout[] mSmallList;
    private f[] mSmallViewSize;
    private int mUiStyle;

    public MallFloorBubbleBannerV9(Context context, int i2) {
        super(context);
        this.mSmallList = new BubbleBannerSmallLayout[4];
        this.mSmallIds = new int[]{R.id.mallfloor_item1, R.id.mallfloor_item2, R.id.mallfloor_item4, R.id.mallfloor_item5};
        this.mSmallViewSize = new f[4];
        this.mUiStyle = i2;
    }

    private void initFloorView() {
        RelativeLayout.LayoutParams u;
        if (this.mMiddleView == null) {
            this.mMiddleSize = new f(this.mUiStyle == BubbleBannerEntity.TYPE_08008_V936 ? R2.attr.addCallbackBufferEnable : R2.attr.arrowWidth, -1);
            BubbleBannerMiddleLayout bubbleBannerMiddleLayout = new BubbleBannerMiddleLayout(getContext());
            this.mMiddleView = bubbleBannerMiddleLayout;
            bubbleBannerMiddleLayout.setId(R.id.mallfloor_item3);
        }
        f.c(this.mMiddleView, this.mMiddleSize);
        if (this.mMiddleView.getParent() != this) {
            RelativeLayout.LayoutParams u2 = this.mMiddleSize.u(this.mMiddleView);
            u2.addRule(14);
            this.mMiddleView.setLayoutParams(u2);
            m.b(this, this.mMiddleView, -1);
        }
        for (int i2 = 0; i2 < 4; i2++) {
            BubbleBannerSmallLayout[] bubbleBannerSmallLayoutArr = this.mSmallList;
            if (bubbleBannerSmallLayoutArr[i2] == null) {
                bubbleBannerSmallLayoutArr[i2] = new BubbleBannerSmallLayout(getContext(), i2, this.mUiStyle);
                this.mSmallList[i2].setId(this.mSmallIds[i2]);
                if (i2 % 2 == 0) {
                    this.mSmallViewSize[i2] = new f(-1, this.mUiStyle == BubbleBannerEntity.TYPE_08008_V936 ? 150 : 144);
                    u = this.mSmallViewSize[i2].u(this.mSmallList[i2]);
                    u.addRule(10);
                } else {
                    this.mSmallViewSize[i2] = new f(-1, -1);
                    u = this.mSmallViewSize[i2].u(this.mSmallList[i2]);
                    u.addRule(3, this.mSmallIds[i2 - 1]);
                }
                if (i2 < 2) {
                    u.addRule(0, R.id.mallfloor_item3);
                } else {
                    u.addRule(1, R.id.mallfloor_item3);
                }
                addView(this.mSmallList[i2], u);
            } else {
                f.d(bubbleBannerSmallLayoutArr[i2], this.mSmallViewSize[i2], true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public void onRefreshViewOnMainThread() {
        super.onRefreshViewOnMainThread();
        initFloorView();
        setBackgroundColor(((g) this.mPresenter).f());
        ((g) this.mPresenter).S(this.mSmallList, this.mMiddleView);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public g createPresenter() {
        return new g();
    }
}
