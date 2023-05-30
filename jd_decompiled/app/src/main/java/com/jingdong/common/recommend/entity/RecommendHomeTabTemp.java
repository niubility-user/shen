package com.jingdong.common.recommend.entity;

import android.graphics.Color;
import com.jd.framework.json.anotation.JSONField;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class RecommendHomeTabTemp extends RecommendAdData {
    public String channelJumpUrl;
    public String description;
    public String descriptionFontColor;
    public String exposureJsonSourceValue;
    public String exposureSourceValue;
    public int[] firstTitleColors;
    public String isOpenApp = "1";
    public String nonWareBgImgUrl;
    public String nonWareTypeName;
    public ArrayList<String> nonWareTypeNameColorList;
    public String nonWareTypeNameFontColor;
    public String sourceValue;
    public String tagBgColor;
    public String tagFontColor;
    @JSONField(name = "subWareList")
    public List<RecommendProduct> wareList;
    public String wname;
    public String wnameFontColor;

    public void generateFirstTitleColor() {
        ArrayList<String> arrayList = this.nonWareTypeNameColorList;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        this.firstTitleColors = new int[this.nonWareTypeNameColorList.size()];
        for (int i2 = 0; i2 < this.nonWareTypeNameColorList.size(); i2++) {
            try {
                this.firstTitleColors[i2] = Color.parseColor(this.nonWareTypeNameColorList.get(i2));
            } catch (Exception e2) {
                if (OKLog.D) {
                    e2.printStackTrace();
                    return;
                }
                return;
            }
        }
    }
}
