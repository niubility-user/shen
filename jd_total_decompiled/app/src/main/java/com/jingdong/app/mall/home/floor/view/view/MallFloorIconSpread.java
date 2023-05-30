package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import com.jingdong.app.mall.home.a;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.view.view.MallFloorIcon;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.r.f.a.o;

/* loaded from: classes4.dex */
public class MallFloorIconSpread extends MallFloorIcon {
    private int mDynamicHeight;
    private int mInitFloorHeight;

    public MallFloorIconSpread(Context context) {
        super(context);
    }

    private void updateFloorHeightByScroll(int i2, float f2) {
        int Y = (int) (((o) this.mPresenter).Y() * Math.max(0.0f, Math.min(i2 + f2, 1.0f)));
        if (Y == this.mDynamicHeight) {
            return;
        }
        this.mDynamicHeight = Y;
        notifyLayoutParamsChange();
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.MallFloorIcon
    protected int getClipRadius() {
        if (((o) this.mPresenter).W() != 0) {
            return d.d(12);
        }
        return 0;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public int getLayoutHeight() {
        return this.mInitFloorHeight + this.mDynamicHeight;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorTop
    public int getLayoutMaxHeight() {
        return this.mInitFloorHeight;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.view.MallFloorIcon
    public boolean needClip() {
        return ((o) this.mPresenter).W() != 0 || super.needClip();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.view.MallFloorIcon, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        MallFloorIcon.JDViewPagerWithGridView jDViewPagerWithGridView = this.mViewPager;
        jDViewPagerWithGridView.setCurrentItem(jDViewPagerWithGridView.getCurrentItem());
        updateFloorHeightByScroll(this.mViewPager.getCurrentItem(), 0.0f);
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.MallFloorIcon, androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i2, float f2, int i3) {
        super.onPageScrolled(i2, f2, i3);
        f.r0("lake", "position: " + i2 + " ,positionOffset: " + f2);
        updateFloorHeightByScroll(i2, f2);
        if (i2 + i3 > 0.1f) {
            a.p = true;
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.MallFloorIcon
    public synchronized void onRefreshViewInMainThread(boolean z) {
        if (((o) this.mPresenter).D0()) {
            this.mInitFloorHeight = ((o) this.mPresenter).l0();
            if (this.mDynamicHeight != 0) {
                this.mDynamicHeight = 0;
                notifyLayoutParamsChange();
                this.currentPagePosition = 0;
            }
            super.onRefreshViewInMainThread(z);
            this.mViewPager.setBackgroundColor(((o) this.mPresenter).W());
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.MallFloorIcon
    protected boolean useShadeColor() {
        return false;
    }

    public MallFloorIconSpread(Context context, int i2) {
        super(context, i2);
    }
}
