package com.jingdong.common.entity.cart;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class CartJumpInfo {
    public Map<String, String> param;
    public String url;

    public CartJumpInfo(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.url = jDJSONObject.optString("url");
        JDJSONObject optJSONObject = jDJSONObject.optJSONObject("param");
        if (optJSONObject == null || optJSONObject.keySet() == null || optJSONObject.keySet().size() <= 0) {
            return;
        }
        this.param = new HashMap(optJSONObject.keySet().size());
        for (String str : optJSONObject.keySet()) {
            String optString = optJSONObject.optString(str);
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(optString)) {
                this.param.put(str, optString);
            }
        }
    }
}
