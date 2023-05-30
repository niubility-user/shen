package com.jingdong.app.mall.home.floor.view.view.title.tab;

import android.content.Context;
import android.text.TextUtils;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.model.entity.CategoryEntity;
import com.jingdong.app.mall.home.floor.view.view.title.HomeTitleNew;
import com.jingdong.app.mall.home.floor.view.view.title.tabbridge.HourlyGoBridge;
import com.jingdong.app.mall.home.floor.view.view.title.tabbridge.TitleLabelImg;
import com.jingdong.app.mall.home.floor.view.view.title.tabbridge.TitleLabelText;
import com.jingdong.app.mall.home.floor.view.view.title.tabnotice.TitleTabNoticeLayout;
import com.jingdong.app.mall.home.i;
import com.jingdong.app.mall.home.o.a.k;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.pdj.libcore.home.HourlyGoFragment;
import com.jingdong.pdj.libcore.watcher.HourlyGoObserverManager;

/* loaded from: classes4.dex */
public class TitleTabLayout extends RelativeLayout {
    public static final String TAG = TitleTabLayout.class.getSimpleName();
    private int currentPosition;
    private boolean longHide;
    private f mContentSize;
    private CategoryEntity.CaItem mCurrentInfo;
    private final HourlyGoBridge mHourlyGoBridge;
    private TitleLabelImg mLabelImg;
    private TitleLabelText mLabelText;
    private TitleTabItemContent mTabContent;
    private TitleTabNoticeLayout mTabNotice;
    private final TitleTabSkin mTitleTabSkin;
    private boolean mUseSpread;

    public TitleTabLayout(HomeTitleNew homeTitleNew, boolean z) {
        super(homeTitleNew.getContext());
        Context context = homeTitleNew.getContext();
        this.mUseSpread = z;
        com.jingdong.app.mall.home.o.a.f.G0(this);
        TitleTabItemContent titleTabItemContent = new TitleTabItemContent(context, this, z) { // from class: com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabLayout.1
            {
                TitleTabLayout.this = this;
            }

            @Override // com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabItemContent
            protected int getSelectPosition() {
                return TitleTabLayout.this.currentPosition;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabItemContent
            public boolean interceptTabClick(int i2) {
                if (i2 == TitleTabLayout.this.getShowPosition()) {
                    return true;
                }
                boolean interceptTabSelect = TitleTabLayout.this.mHourlyGoBridge.interceptTabSelect(i2);
                TitleTabLayout.this.mHourlyGoBridge.setInterceptPosition(interceptTabSelect, i2);
                return interceptTabSelect;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabItemContent
            public int onTabChanged(int i2) {
                super.onTabChanged(i2);
                if (TitleTabLayout.this.isVisible()) {
                    TitleTabLayout.this.onSelect(i2);
                }
                return getSelectPosition();
            }
        };
        this.mTabContent = titleTabItemContent;
        titleTabItemContent.setId(R.id.home_top_lbs_logo);
        this.mTabContent.setOrientation(0);
        f fVar = new f(-2, 56);
        this.mContentSize = fVar;
        fVar.J(4, 0, 4, 0);
        RelativeLayout.LayoutParams u = this.mContentSize.u(this.mTabContent);
        u.addRule(13);
        addView(this.mTabContent, u);
        TitleTabSkin titleTabSkin = new TitleTabSkin() { // from class: com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabLayout.2
            {
                TitleTabLayout.this = this;
            }

            @Override // com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabSkin
            protected int getCurrentPosition() {
                return TitleTabLayout.this.getShowPosition();
            }

            @Override // com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabSkin
            protected void notifyTabSkinBitmap(boolean z2) {
                TitleTabLayout.this.notifyTabSkinBitmap(z2);
                JDHomeFragment z0 = JDHomeFragment.z0();
                if (z0 != null) {
                    z0.Z0();
                }
            }
        };
        this.mTitleTabSkin = titleTabSkin;
        HourlyGoBridge hourlyGoBridge = new HourlyGoBridge(this, titleTabSkin);
        this.mHourlyGoBridge = hourlyGoBridge;
        TitleLabelImg titleLabelImg = new TitleLabelImg(context, hourlyGoBridge);
        this.mLabelImg = titleLabelImg;
        titleLabelImg.addLabel(this, this.mTabContent);
        TitleLabelText titleLabelText = new TitleLabelText(context, hourlyGoBridge);
        this.mLabelText = titleLabelText;
        titleLabelText.addLabel(this, this.mTabContent);
        this.mTabNotice = new TitleTabNoticeLayout(homeTitleNew);
        hourlyGoBridge.initEnd();
    }

    private boolean forceToHome() {
        boolean z = this.currentPosition != 0;
        if (z) {
            this.mTabContent.checkTabClick(0, false);
        }
        return z;
    }

    public int getShowPosition() {
        return checkTabPosition(this.currentPosition);
    }

    public static boolean isShowTopTab(TitleTabLayout titleTabLayout) {
        if (TitleTabManager.getInstance().getTitleTabInfo().getMastShowCount() <= 1) {
            return titleTabLayout != null && titleTabLayout.getVisibility() == 0;
        }
        return true;
    }

    private void loadHeadSkin() {
        int i2 = this.currentPosition;
        if (i2 == -2) {
            this.mTitleTabSkin.loadTabSkinBitmap(i2, TitleTabManager.getInstance().getTitleTabInfo().getTabCustom().getTabBgUrl());
        } else if (i2 == -3) {
            this.mTitleTabSkin.loadTabSkinBitmap(i2, TitleTabManager.getInstance().getTitleTabInfo().getTabPromotion().getTabBgUrl());
        } else if (i2 == -1) {
            this.mTitleTabSkin.loadTabSkinBitmap(i2, this.mHourlyGoBridge.getHourlyGoHeadUrl());
        } else {
            this.mTitleTabSkin.loadTabSkinBitmap(i2, null);
        }
    }

    private void tabInfoChanged(int i2) {
        TitleTabItemContent titleTabItemContent = this.mTabContent;
        if (titleTabItemContent == null) {
            return;
        }
        titleTabItemContent.changeTabText();
        checkSize();
        onSelect(i2);
        this.mTabContent.changeSelect(i2, true);
        this.mHourlyGoBridge.checkHourlyShow(false);
    }

    public void afterRefresh() {
        TitleTabItemContent titleTabItemContent = this.mTabContent;
        if (titleTabItemContent != null) {
            titleTabItemContent.setStyle(this.mContentSize.h());
        }
        this.mHourlyGoBridge.afterRefresh();
        bringToFront();
        checkTabState(true);
    }

    public void autoJumpNearby() {
        if (getShowPosition() == -1 || !TitleTabManager.getInstance().canShowHourlyGoTab()) {
            return;
        }
        this.mTabContent.checkTabClick(-1, false, true);
    }

    public void checkSize() {
        this.mTabContent.checkSize(getShowPosition());
        this.mContentSize.E(0, 13, 0, 0);
        this.mLabelImg.checkSize();
        this.mLabelText.checkSize();
        f.d(this.mTabContent, this.mContentSize, true);
    }

    public int checkTabPosition(int i2) {
        boolean z = i2 == -2 && TitleTabManager.getInstance().canShowCustomTab();
        boolean z2 = i2 == -1 && TitleTabManager.getInstance().canShowHourlyGoTab();
        boolean z3 = i2 == -3 && TitleTabManager.getInstance().canShowPromotion();
        if (z || z2 || z3) {
            return i2;
        }
        this.mTabContent.checkTabClick(0, false);
        return 0;
    }

    public boolean checkTabState(boolean z) {
        if (!this.longHide && TitleTabManager.getInstance().getTitleTabInfo().isCanShow()) {
            if (TitleTabManager.getInstance().canShowHourlyGoTab()) {
                this.mHourlyGoBridge.registerHourlyGoObserver();
                this.mHourlyGoBridge.setHourlyGoFloorId();
                d.c(true);
            } else {
                this.mHourlyGoBridge.unregisterHourlyGoObserver();
                d.c(false);
            }
            setVisibility(0);
            TitleTabManager.getInstance().checkHomeTabName();
            intTabInfo(z ? 0 : getShowPosition());
            return true;
        }
        forceToHome();
        setVisibility(8);
        d.c(false);
        this.mHourlyGoBridge.unregisterHourlyGoObserver();
        TitleTabManager.getInstance().checkHomeTabName();
        return false;
    }

    public TitleLabelImg getLabelImg() {
        return this.mLabelImg;
    }

    public TitleLabelText getLabelText() {
        return this.mLabelText;
    }

    public TitleTabItemContent getTabContent() {
        return this.mTabContent;
    }

    public TitleTabNoticeLayout getTabNotice() {
        return this.mTabNotice;
    }

    public void hideTabLong() {
        this.longHide = true;
        checkTabState(false);
    }

    public void intTabInfo(int i2) {
        tabInfoChanged(i2);
        TitleTabExpoUtil.postTopTabExpo();
    }

    public boolean isShowing() {
        return getParent() != null && getVisibility() == 0;
    }

    public boolean isVisible() {
        return getAlpha() > 0.4f && getVisibility() == 0;
    }

    protected boolean needScrollToTop() {
        return true;
    }

    public void notifyHourlyGoName() {
        this.mTabContent.notifyHourlyGoName();
    }

    public void notifyTabSkinBitmap(boolean z) {
    }

    public boolean onBackPressed() {
        if (this.currentPosition == 0) {
            return false;
        }
        HourlyGoBridge.dismissBubble();
        return this.mTabContent.interceptTabClick(0) || forceToHome();
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        if (TextUtils.equals(baseEvent.getType(), "home_refresh_top_skin")) {
            loadHeadSkin();
        }
        if (getVisibility() == 0) {
            this.mHourlyGoBridge.onEventMainThread(baseEvent);
        }
        String type = baseEvent.getType();
        type.hashCode();
        if (type.equals("home_pause")) {
            this.mTabNotice.onPause();
        }
    }

    public void onNoticeShow() {
        this.mLabelImg.release();
    }

    public void onResume() {
        if (!TitleTabManager.getInstance().isShowTab() || this.mTabContent == null) {
            return;
        }
        int showPosition = getShowPosition();
        if (this.currentPosition != showPosition) {
            tabInfoChanged(showPosition);
        }
        TitleTabExpoUtil.postTopTabExpo();
    }

    public void onSelect(int i2) {
        int checkTabPosition;
        CategoryEntity.CaItem caItem;
        this.mHourlyGoBridge.setInterceptPosition(false, i2);
        this.mTabContent.checkTabClick(i2, false);
        JDHomeFragment z0 = JDHomeFragment.z0();
        if (z0 == null || (checkTabPosition = checkTabPosition(i2)) == this.currentPosition) {
            return;
        }
        boolean z = -1 == checkTabPosition;
        if (k.w() || !z || HourlyGoFragment.isFragmentAvailable()) {
            TitleTabExpoUtil.postTabClick(checkTabPosition, this.mLabelImg);
            if (needScrollToTop()) {
                z0.k1();
            }
            if (checkTabPosition == -2) {
                this.currentPosition = -2;
                caItem = TitleTabManager.getInstance().getTitleTabInfo().getTabCustom().getCaItem();
            } else if (checkTabPosition == -3) {
                this.currentPosition = -3;
                caItem = TitleTabManager.getInstance().getTitleTabInfo().getTabPromotion().getCaItem();
            } else if (checkTabPosition == -1) {
                i.a();
                this.mLabelText.dismiss();
                this.currentPosition = -1;
                caItem = TitleTabManager.getInstance().getTitleTabInfo().getTabHourlyGo().getCaItem();
            } else {
                this.currentPosition = 0;
                caItem = TitleTabManager.getInstance().getTitleTabInfo().getTabHome().getCaItem();
            }
            CategoryEntity.CaItem caItem2 = this.mCurrentInfo;
            CategoryEntity.CaItem preTabInfo = caItem2 == null ? null : caItem2.getPreTabInfo();
            if (checkTabPosition == 0 && preTabInfo != null && preTabInfo.getPosition() > 0) {
                z0.c1(preTabInfo, preTabInfo.getPosition());
            } else {
                z0.c1(caItem, checkTabPosition);
            }
            this.mCurrentInfo = caItem;
            this.mTabContent.checkTabClick(checkTabPosition, true);
            loadHeadSkin();
        }
    }

    public void onTabClick(int i2, String str) {
        HourlyGoObserverManager.getInstance().setIntent(null, str);
        this.mTabContent.checkTabClick(checkTabPosition(i2), false);
    }

    public void showTab() {
        this.longHide = false;
        checkTabState(false);
    }
}
