package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONObject;

/* loaded from: classes5.dex */
public class CartSurveyInfo {
    public String answerUrl;
    public String shortCode;
    public long surveyId;

    public CartSurveyInfo(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.surveyId = jDJSONObject.optLong("surveyId");
        this.answerUrl = jDJSONObject.optString("answerUrl");
        this.shortCode = jDJSONObject.optString("shortCode");
    }
}
