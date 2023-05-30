package com.jingdong.app.mall.bundle.styleinfoview.entitys.coupon;

import com.jd.framework.json.anotation.JSONField;

/* loaded from: classes.dex */
public class PdCouponRepositoryEntity {
    @JSONField(serialize = false)
    private String functionId = "coupon";
    @JSONField(serialize = false)
    private boolean isNotifyUser;
    private String skuId;

    public String getFunctionId() {
        return this.functionId;
    }

    public String getSkuId() {
        return this.skuId;
    }

    public boolean isNotifyUser() {
        return this.isNotifyUser;
    }

    public void setFunctionId(String str) {
        this.functionId = str;
    }

    public void setNotifyUser(boolean z) {
        this.isNotifyUser = z;
    }

    public void setSkuId(String str) {
        this.skuId = str;
    }
}
