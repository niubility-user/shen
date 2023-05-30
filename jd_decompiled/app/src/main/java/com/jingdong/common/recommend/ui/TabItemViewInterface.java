package com.jingdong.common.recommend.ui;

import com.jingdong.common.recommend.entity.RecommendTab;

/* loaded from: classes6.dex */
public interface TabItemViewInterface {
    void onDeepDarkChanged(boolean z);

    void onTextScaleModeChanged();

    void onWidthSizeChange();

    void setChangeProgress(float f2);

    void setHasSplitLine(boolean z);

    void setHasSubTitle(boolean z);

    void setRecommendTab(RecommendTab recommendTab);

    void setTabSelect(boolean z);

    void setWH(int i2, int i3);

    void startTabAni(boolean z, boolean z2);
}
