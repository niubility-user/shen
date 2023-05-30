package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONObject;

/* loaded from: classes5.dex */
public class CartButtonInfo {
    public boolean canShow;
    public CartJumpInfo jumpInfo;
    public int jumpType;
    public String text;

    public CartButtonInfo(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.canShow = jDJSONObject.optBoolean("canShow");
        this.text = jDJSONObject.optString("text");
        this.jumpType = jDJSONObject.optInt("jumpType");
        JDJSONObject optJSONObject = jDJSONObject.optJSONObject("jumpInfo");
        if (optJSONObject != null) {
            this.jumpInfo = new CartJumpInfo(optJSONObject);
        }
    }
}
