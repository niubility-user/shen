package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class SpuLimitVo {
    public String button;
    public List<SpuLimitBeanVo> spuLimitBeanVoList;
    public String title;

    public SpuLimitVo(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.title = jDJSONObject.optString("title");
        this.button = jDJSONObject.optString("button");
        JDJSONArray optJSONArray = jDJSONObject.optJSONArray("spuLimitBeanVoList");
        if (optJSONArray != null) {
            this.spuLimitBeanVoList = new ArrayList();
            for (int i2 = 0; i2 < optJSONArray.size(); i2++) {
                this.spuLimitBeanVoList.add(new SpuLimitBeanVo(optJSONArray.optJSONObject(i2)));
            }
        }
    }
}
