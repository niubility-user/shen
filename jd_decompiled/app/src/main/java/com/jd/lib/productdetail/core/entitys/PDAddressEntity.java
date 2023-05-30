package com.jd.lib.productdetail.core.entitys;

import com.jd.framework.json.JDJSONObject;

/* loaded from: classes15.dex */
public class PDAddressEntity {
    public int flag;
    public JDJSONObject jsonObject;
    public boolean success;

    public PDAddressEntity(int i2, boolean z, JDJSONObject jDJSONObject) {
        this.flag = i2;
        this.success = z;
        this.jsonObject = jDJSONObject;
    }
}
