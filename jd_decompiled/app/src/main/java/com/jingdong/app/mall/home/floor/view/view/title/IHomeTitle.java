package com.jingdong.app.mall.home.floor.view.view.title;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Pair;
import android.view.View;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.i.s;
import com.jingdong.app.mall.home.floor.view.view.CategoryTabTitle;
import com.jingdong.app.mall.home.j;
import com.jingdong.app.mall.home.pullrefresh.BaseVerticalRefresh;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.app.mall.home.v.c.a;

/* loaded from: classes4.dex */
public abstract class IHomeTitle extends RelativeLayout implements d.b {
    public IHomeTitle(Context context) {
        super(context);
    }

    public abstract boolean addCategoryView(CategoryTabTitle categoryTabTitle);

    public abstract void addOverseasSwitchIcon(String str, String str2, int i2);

    public void addTitleResource(h hVar) {
    }

    public abstract void addTopTab();

    public abstract boolean afterRefresh();

    public abstract void beforeRefresh();

    public abstract void beforeSearchBoxWordRefresh();

    public abstract void bindFragment(JDHomeFragment jDHomeFragment);

    public abstract void changeSearchBarColorVarScrolling(int i2);

    public abstract boolean checkJumpNearby();

    public void checkTitleGray() {
        a.a(this);
        if (s.f9358e) {
            return;
        }
        com.jingdong.app.mall.home.floor.ctrl.h.N().b0(true);
    }

    public abstract void forceRefreshBarStatus();

    public abstract int getBarHeightShrink();

    public abstract int getBarHeightSpread();

    public abstract int getCurrentBarHeight();

    public abstract View getHomeTitleView();

    public int getMiniTopMatchTitleCode(String str) {
        return -1;
    }

    public abstract Pair<Bitmap, Matrix> getTopBitmap();

    public int getTopHeight() {
        return com.jingdong.app.mall.home.floor.ctrl.h.T();
    }

    public int getTopLogoScaleAnimPivotX() {
        return 0;
    }

    public abstract void hideTopTab();

    public abstract boolean isAnimating();

    public void isCheckHomeTab(boolean z) {
    }

    public abstract boolean isScrollFixed();

    public boolean needScrollTop() {
        return true;
    }

    public abstract boolean onBackPressed();

    public void onDarkThemeChange() {
    }

    public void onHomeStop() {
    }

    public abstract void onPause();

    public abstract void onPullOffset(BaseVerticalRefresh.l lVar, int i2, long j2);

    public abstract void onResume(boolean z);

    public abstract /* synthetic */ void onScreenChanged(int i2);

    public abstract void onTitleChanged();

    public abstract void onUiChanged(boolean z);

    public void playTitleResourceAnim() {
    }

    public void refreshPlanB(com.jingdong.app.mall.home.r.e.d dVar) {
    }

    public void refreshSearchBoxStyle(h hVar) {
    }

    public abstract void removeOverseasSwitchIcon();

    public void resetLogo() {
    }

    public abstract void setBitmap(Bitmap bitmap, Matrix matrix);

    public abstract void setSearchBarDataEntity(j.b bVar);

    public abstract void setSkinBitmap(Bitmap bitmap, Matrix matrix);

    public abstract void showOverseasBubbleTips();

    public void showPromotionIcon(h hVar) {
    }

    public void showSearchBarLeftIcon(h hVar) {
    }

    public void showTitleResourceNormally() {
    }
}
