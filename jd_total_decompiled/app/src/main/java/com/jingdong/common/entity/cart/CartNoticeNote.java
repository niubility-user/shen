package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONObject;

/* loaded from: classes5.dex */
public class CartNoticeNote {
    public String businessType;
    public String noticeContext;
    public String noticeUrl;
    public int urlType;

    public CartNoticeNote(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.noticeContext = jDJSONObject.optString("noticeContext");
        this.noticeUrl = jDJSONObject.optString("noticeUrl");
        this.urlType = jDJSONObject.optInt("urlType");
        this.businessType = jDJSONObject.optString("businessType");
    }
}
