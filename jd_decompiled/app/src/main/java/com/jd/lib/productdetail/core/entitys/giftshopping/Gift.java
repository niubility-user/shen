package com.jd.lib.productdetail.core.entitys.giftshopping;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes15.dex */
public class Gift {
    public List<Sku> skus = new ArrayList();

    public Gift() {
    }

    public Gift(JDJSONObject jDJSONObject) {
        JDJSONArray optJSONArray;
        if (jDJSONObject == null || (optJSONArray = jDJSONObject.optJSONArray("skus")) == null || optJSONArray.size() <= 0) {
            return;
        }
        this.skus.clear();
        for (int i2 = 0; i2 < optJSONArray.size(); i2++) {
            this.skus.add(new Sku(optJSONArray.optJSONObject(i2)));
        }
    }
}
