package com.jingdong.app.mall.bundle.productdetailcard.entity;

import com.jd.framework.json.JDJSONObject;

/* loaded from: classes3.dex */
public class PdCardFloorInfo {
    public String biz;
    public boolean common;
    public Object data;
    public String title;

    public PdCardFloorInfo(JDJSONObject jDJSONObject) {
        if (jDJSONObject != null) {
            this.common = jDJSONObject.optBoolean("common", false);
            this.biz = jDJSONObject.optString("biz");
            this.data = jDJSONObject.optJSONObject("data");
            this.title = jDJSONObject.optString("title");
        }
    }
}
