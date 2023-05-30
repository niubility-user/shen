package com.jingdong.common.recommend.ui.homerecommend;

import com.jingdong.common.recommend.entity.RecommendHomeTabs;

/* loaded from: classes6.dex */
public interface HomeTabInterface {
    void dealTabDynamicHeight(boolean z, boolean z2);

    boolean hasSubTitle();

    void onDeepDarkChanged();

    void onTextScaleModeChanged();

    void onWidthSizeChange();

    void setHomeTab(RecommendHomeTabs recommendHomeTabs);
}
