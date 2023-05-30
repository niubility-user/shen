package com.jingdong.app.mall.bundle.styleinfoview.entitys;

import com.jd.framework.json.JDJSONObject;

/* loaded from: classes3.dex */
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
