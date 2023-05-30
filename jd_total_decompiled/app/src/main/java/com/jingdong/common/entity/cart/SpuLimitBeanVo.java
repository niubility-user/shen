package com.jingdong.common.entity.cart;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes5.dex */
public class SpuLimitBeanVo {
    public List<HashMap<String, String>> allSku;
    public Integer limitMinNum;
    public String notice;
    public String spuId;
    public String strategyId;
    public String type;

    public SpuLimitBeanVo(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.strategyId = jDJSONObject.optString("strategyId");
        this.spuId = jDJSONObject.optString("spuId");
        this.notice = jDJSONObject.optString("notice");
        this.limitMinNum = Integer.valueOf(jDJSONObject.optInt("limitMinNum"));
        this.limitMinNum = Integer.valueOf(jDJSONObject.optInt("limitMinNum"));
        this.type = jDJSONObject.optString("type");
        JDJSONArray optJSONArray = jDJSONObject.optJSONArray("allSku");
        if (optJSONArray != null) {
            this.allSku = new ArrayList();
            for (int i2 = 0; i2 < optJSONArray.size(); i2++) {
                JDJSONObject jSONObject = optJSONArray.getJSONObject(i2);
                HashMap<String, String> hashMap = new HashMap<>();
                if (jSONObject != null && jSONObject.size() > 0) {
                    for (String str : jSONObject.keySet()) {
                        if (!TextUtils.isEmpty(str)) {
                            hashMap.put(str, jSONObject.optString(str, ""));
                        }
                    }
                }
                this.allSku.add(hashMap);
            }
        }
    }
}
