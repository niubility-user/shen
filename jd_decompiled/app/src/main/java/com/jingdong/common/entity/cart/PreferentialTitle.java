package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONObject;
import com.unionpay.tsmservice.mi.data.Constant;

/* loaded from: classes5.dex */
public class PreferentialTitle {
    public String bgImg;
    public String bgImgUrl;
    public String fontColor;
    public String label;
    public String subTitle;
    public String title;

    public PreferentialTitle(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.title = jDJSONObject.optString("title");
        this.subTitle = jDJSONObject.optString("subTitle");
        this.bgImg = jDJSONObject.optString("bgImg");
        this.bgImgUrl = jDJSONObject.optString("bgImgUrl");
        this.fontColor = jDJSONObject.optString("fontColor");
        this.label = jDJSONObject.optString(Constant.KEY_PROMOTION_LABEL);
    }
}
