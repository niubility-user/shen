package com.jd.lib.productdetail.core.entitys;

import android.text.TextUtils;
import com.jingdong.jdsdk.constant.CartConstant;

/* loaded from: classes15.dex */
public class PDMtaEntity {
    public String shopId;
    public String skuId;
    public String skuTag;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PDMtaEntity(String str, String str2, String str3) {
        this.skuId = str;
        this.skuTag = str2;
        this.shopId = str3;
    }

    public String getSearchParam(ProductDetailEntity productDetailEntity) {
        String str = productDetailEntity != null ? productDetailEntity.searchParam : "";
        if (TextUtils.isEmpty(str)) {
            return this.skuId;
        }
        return this.skuId + CartConstant.KEY_YB_INFO_LINK + str;
    }
}
