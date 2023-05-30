package com.jingdong.common.recommend.entity;

import android.text.TextUtils;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;

/* loaded from: classes6.dex */
public class RecommendTab {
    public String broker_info;
    public int pos;
    public String redDotId;
    public String redDotImg;
    public String selectedSubTitleColor;
    public String selectedSubTitleDarkColor;
    public String selectedSubtitleImg;
    public String selectedTitleImg;
    public String seletedTitleColor;
    public String seletedTitleDarkColor;
    public int source;
    public String subtitle;
    public int tabId;
    public String title;
    public String unSelectedSubTitleColor;
    public String unSelectedSubTitleDarkColor;
    public String unSelectedTitleColor;
    public String unSelectedTitleDarkColor;
    public String unselectedSubtitleImg;
    public String unselectedTitleImg;
    public boolean isShowDot = false;
    public String displayMode = "0";

    public void setShowDot() {
        if (!TextUtils.isEmpty(this.redDotId)) {
            if (!this.redDotId.equals(SharedPreferencesUtil.getString(this.tabId + "", ""))) {
                this.isShowDot = true;
                return;
            }
        }
        this.isShowDot = false;
    }
}
