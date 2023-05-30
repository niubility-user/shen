package com.jingdong.common.recommend.entity;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class RecommendHomeTabs {
    public boolean animationSwitchOfTabs;
    public String leftClickBgImg;
    public String leftClickDartBgImg;
    public String localTabAB = "A";
    public ArrayList<RecommendTab> recommendTabList;
    public String rightClickBgImg;
    public String rightClickDarkBgImg;
    public String selectedColor;
    public String slidingSpreadColor;
    public String slidingSpreadDarkColor;
    public String slidingUnSpreadColor;
    public String slidingUnSpreadDarkColor;

    public RecommendHomeTabs(String str, ArrayList<RecommendTab> arrayList, boolean z) {
        this.selectedColor = str;
        this.recommendTabList = arrayList;
        this.animationSwitchOfTabs = z;
    }

    public String getLocalTabAB() {
        return this.localTabAB;
    }

    public void handlePos() {
        ArrayList<RecommendTab> arrayList = this.recommendTabList;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < this.recommendTabList.size(); i2++) {
            RecommendTab recommendTab = this.recommendTabList.get(i2);
            if (recommendTab != null) {
                recommendTab.pos = i2;
            }
        }
    }

    public void setLocalTabAB(String str) {
        this.localTabAB = str;
    }

    public void setMutiTabString(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            JDJSONObject parseObject = JDJSON.parseObject(str);
            this.rightClickBgImg = parseObject.optString("selectedTitleImg");
            this.leftClickBgImg = parseObject.optString("unSelectedTitleImg");
            this.rightClickDarkBgImg = parseObject.optString("darkSelectedTitleImg");
            this.leftClickDartBgImg = parseObject.optString("darkUnSelectedTitleImg");
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
    }

    public void setRecommendTabColors(ArrayList<String> arrayList) {
        if (arrayList != null) {
            try {
                if (arrayList.size() < 6 || this.recommendTabList == null) {
                    return;
                }
                this.slidingSpreadColor = arrayList.get(0);
                this.slidingUnSpreadColor = arrayList.get(1);
                String str = arrayList.get(2);
                String str2 = arrayList.get(3);
                String str3 = arrayList.get(4);
                String str4 = arrayList.get(5);
                for (int i2 = 0; i2 < this.recommendTabList.size(); i2++) {
                    RecommendTab recommendTab = this.recommendTabList.get(i2);
                    if (recommendTab != null) {
                        recommendTab.unSelectedTitleColor = str;
                        recommendTab.unSelectedSubTitleColor = str2;
                        recommendTab.seletedTitleColor = str3;
                        recommendTab.selectedSubTitleColor = str4;
                    }
                }
            } catch (Exception e2) {
                if (OKLog.D) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public void setRecommendTabDarkColors(ArrayList<String> arrayList) {
        if (arrayList != null) {
            try {
                if (arrayList.size() < 6 || this.recommendTabList == null) {
                    return;
                }
                this.slidingSpreadDarkColor = arrayList.get(0);
                this.slidingUnSpreadDarkColor = arrayList.get(1);
                String str = arrayList.get(2);
                String str2 = arrayList.get(3);
                String str3 = arrayList.get(4);
                String str4 = arrayList.get(5);
                for (int i2 = 0; i2 < this.recommendTabList.size(); i2++) {
                    RecommendTab recommendTab = this.recommendTabList.get(i2);
                    if (recommendTab != null) {
                        recommendTab.unSelectedTitleDarkColor = str;
                        recommendTab.unSelectedSubTitleDarkColor = str2;
                        recommendTab.seletedTitleDarkColor = str3;
                        recommendTab.selectedSubTitleDarkColor = str4;
                    }
                }
            } catch (Exception e2) {
                if (OKLog.D) {
                    e2.printStackTrace();
                }
            }
        }
    }
}
