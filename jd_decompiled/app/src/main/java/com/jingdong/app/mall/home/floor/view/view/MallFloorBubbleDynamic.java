package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI;
import com.jingdong.app.mall.home.floor.view.view.subitem.BubbleDynamicLeftRightLayout;
import com.jingdong.app.mall.home.floor.view.view.subitem.BubbleDynamicMiddleLayout;
import com.jingdong.app.mall.home.r.f.a.i;

/* loaded from: classes4.dex */
public class MallFloorBubbleDynamic extends BaseMallFloor<i> implements IMallFloorUI {
    private BubbleDynamicLeftRightLayout leftLayout;
    private BubbleDynamicMiddleLayout middleLayout;
    private BubbleDynamicLeftRightLayout rightLayout;

    public MallFloorBubbleDynamic(Context context) {
        super(context);
    }

    private void initFloorView() {
        f fVar = new f(((i) this.mPresenter).Q(), -1);
        BubbleDynamicMiddleLayout bubbleDynamicMiddleLayout = this.middleLayout;
        if (bubbleDynamicMiddleLayout == null) {
            BubbleDynamicMiddleLayout bubbleDynamicMiddleLayout2 = new BubbleDynamicMiddleLayout(getContext());
            this.middleLayout = bubbleDynamicMiddleLayout2;
            bubbleDynamicMiddleLayout2.setId(R.id.mallfloor_item2);
            RelativeLayout.LayoutParams u = fVar.u(this.middleLayout);
            u.addRule(14);
            addView(this.middleLayout, u);
        } else {
            f.c(bubbleDynamicMiddleLayout, fVar);
        }
        f fVar2 = new f(-1, -1);
        BubbleDynamicLeftRightLayout bubbleDynamicLeftRightLayout = this.leftLayout;
        if (bubbleDynamicLeftRightLayout == null) {
            BubbleDynamicLeftRightLayout bubbleDynamicLeftRightLayout2 = new BubbleDynamicLeftRightLayout(getContext());
            this.leftLayout = bubbleDynamicLeftRightLayout2;
            RelativeLayout.LayoutParams u2 = fVar2.u(bubbleDynamicLeftRightLayout2);
            u2.addRule(0, this.middleLayout.getId());
            addView(this.leftLayout, u2);
        } else {
            f.c(bubbleDynamicLeftRightLayout, fVar2);
        }
        f fVar3 = new f(-1, -1);
        BubbleDynamicLeftRightLayout bubbleDynamicLeftRightLayout3 = this.rightLayout;
        if (bubbleDynamicLeftRightLayout3 == null) {
            BubbleDynamicLeftRightLayout bubbleDynamicLeftRightLayout4 = new BubbleDynamicLeftRightLayout(getContext());
            this.rightLayout = bubbleDynamicLeftRightLayout4;
            RelativeLayout.LayoutParams u3 = fVar3.u(bubbleDynamicLeftRightLayout4);
            u3.addRule(1, this.middleLayout.getId());
            addView(this.rightLayout, u3);
            return;
        }
        f.c(bubbleDynamicLeftRightLayout3, fVar3);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomePause() {
        super.onHomePause();
        ((i) this.mPresenter).S();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor
    public void onHomeResume(int i2, int i3) {
        super.onHomeResume(i2, i3);
        ((i) this.mPresenter).R();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public void onRefreshViewOnMainThread() {
        super.onRefreshViewOnMainThread();
        initFloorView();
        setBackgroundColor(((i) this.mPresenter).f());
        ((i) this.mPresenter).T(this.leftLayout, this.middleLayout, this.rightLayout);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public i createPresenter() {
        return new i();
    }
}
