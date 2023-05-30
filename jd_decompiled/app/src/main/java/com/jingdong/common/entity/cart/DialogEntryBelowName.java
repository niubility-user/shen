package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONObject;

/* loaded from: classes5.dex */
public class DialogEntryBelowName {
    public String accessibleText;
    public String anchorKey;
    public String text;

    public DialogEntryBelowName(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.text = jDJSONObject.optString("text");
        this.accessibleText = jDJSONObject.optString("accessibleText");
        this.anchorKey = jDJSONObject.optString("anchorKey");
    }
}
